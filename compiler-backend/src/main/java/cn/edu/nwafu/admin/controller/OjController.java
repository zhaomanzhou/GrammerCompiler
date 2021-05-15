package cn.edu.nwafu.admin.controller;

import cn.edu.nwafu.admin.config.context.RequestContext;
import cn.edu.nwafu.admin.controller.vo.response.JudgeResult;
import cn.edu.nwafu.admin.controller.vo.response.TestCaseVo;
import cn.edu.nwafu.admin.domain.Problem;
import cn.edu.nwafu.admin.repository.ProblemRepository;
import cn.edu.nwafu.def.Parser;
import cn.edu.nwafu.oj.CompilerConst;
import cn.edu.nwafu.oj.mq.MqProducer;
import cn.edu.nwafu.parser.ParseException;
import com.idofast.common.response.ServerResponse;
import com.idofast.common.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/11 12:12 上午
 */
@RestController()
@RequestMapping("/oj")
@CrossOrigin
@Slf4j
public class OjController
{

    @Autowired
    private CompilerConst compilerConst;
    
    @Autowired
    private MqProducer mqProducer;


    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/judge")
    public ServerResponse<String> judgeCode(Long problemId, String code) throws IOException, BusinessException
    {
        final Long userId = RequestContext.getCurrentUser().getId();

        File userCodeFolder = new File(compilerConst.getUserFolder() + userId + "/" + problemId + "/");
        if(!userCodeFolder.exists())
        {
            final boolean newFile = userCodeFolder.mkdirs();
            if(!newFile)
            {
                throw new BusinessException("用户空间文件夹创建失败");
            }
        }
        FileOutputStream fos = new FileOutputStream(new File(userCodeFolder, "user.c"));
        fos.write(code.getBytes());
        fos.close();

        final String uuid = UUID.randomUUID().toString();

        final boolean b = mqProducer.judgeCode(userId, problemId, uuid);
        if(!b){
            throw new BusinessException("Mq消息发送失败");
        }
        return ServerResponse.success(uuid);

    }

    @GetMapping("/status")
    public ServerResponse<JudgeResult> getProblemStatus(String key)
    {
        final String status =(String) redisTemplate.opsForHash().get(key, "status");
        if(status == null || !status.equals("ok"))
        {
            JudgeResult judgeResult = new JudgeResult();
            judgeResult.setOk(false);
            return ServerResponse.success(judgeResult);
        }

        final JudgeResult judgeResult = (JudgeResult) redisTemplate.opsForHash().get(key, "result");
        return ServerResponse.success(judgeResult);

    }
    @GetMapping("/getDef")
    //获取该题目对应的c语言def
    public ServerResponse<String> getStructureDef(Long id)
    {
        try
        {
            final byte[] bytes = Files.readAllBytes(Paths.get(compilerConst.getProblemFolder(), id + "/def.c"));
            return ServerResponse.success(new String(bytes));
        } catch (IOException e)
        {
            return ServerResponse.success("");
        }
    }

    @PostMapping("/updateDef")
    public ServerResponse<String> updateStructureDef(Long id, String content) throws IOException
    {

        final Parser parser = new Parser(new ByteArrayInputStream(content.getBytes()));
        try
        {
            parser.compilation_unit();
        } catch (ParseException e)
        {
            log.info(e.getMessage());
            String s = e.getMessage();
            return ServerResponse.success(s);
        }

        File folder = new File(compilerConst.getProblemFolder(), id + "");
        if(!folder.exists()){
            folder.mkdirs();
        }

        File defFile = new File(folder, "def.c");

        FileOutputStream fos = new FileOutputStream(defFile);
        fos.write(content.getBytes());
        fos.close();


        return ServerResponse.success();
    }

    @RequestMapping("/testcase")
    public ServerResponse<Object> getTestCases(Long id) throws IOException
    {
        List<TestCaseVo> list = new ArrayList<>();

        File folder = new File(compilerConst.getProblemFolder(), id + "");
        if(!folder.exists()){
            folder.mkdirs();
            return ServerResponse.success(list);
        }


        final File[] files = folder.listFiles();
        for(File file: files)
        {
            if(file.isDirectory())
            {
                TestCaseVo vo = new TestCaseVo();
                vo.setId(file.getName());
                vo.setIn(new String(Files.readAllBytes(Paths.get(file.getAbsolutePath(), "a.in"))));
                vo.setOut(new String(Files.readAllBytes(Paths.get(file.getAbsolutePath(), "a.out"))));
                list.add(vo);
            }
        }

        return ServerResponse.success(list);

    }

    @PostMapping("/deleteCase")
    public ServerResponse<Object> deleteCase(Long problemId, String caseId) throws IOException
    {
        File f = new File(compilerConst.getProblemFolder() + problemId +"/" + caseId);
        if(!f.exists()){
            return ServerResponse.error("该测试用例不存在");
        }
        Runtime.getRuntime().exec("rm -rf " + f.getAbsolutePath());
        return ServerResponse.success();
    }

    @PostMapping("/updateCase")
    public ServerResponse<Object> updateCase(Long problemId, String caseId, String in, String out) throws IOException
    {
        File f = new File(compilerConst.getProblemFolder() + problemId +"/" + caseId);
        if(!f.exists()){
            return ServerResponse.error("该测试用例不存在");
        }
        FileOutputStream fos = new FileOutputStream(new File(f,"a.in"));
        fos.write(in.getBytes());
        fos.close();

        fos = new FileOutputStream(new File(f,"a.out"));
        fos.write(out.getBytes());
        fos.close();
        return ServerResponse.success();
    }


    @PostMapping("/addCase")
    public ServerResponse<Object> addCase(Long problemId,  String in, String out) throws IOException, BusinessException
    {

        final Optional<Problem> byId = problemRepository.findById(problemId);
        if(byId.isEmpty()){
            throw new BusinessException("该题目不存在");
        }
        final Problem problem = byId.get();
        problem.setCaseNum(problem.getCaseNum() + 1);
        problemRepository.save(problem);

        File f = new File(compilerConst.getProblemFolder() + problemId +"/" + problem.getCaseNum());

        if(!f.exists()){
            f.mkdirs();
        }



        FileOutputStream fos = new FileOutputStream(new File(f,"a.in"));
        fos.write(in.getBytes());
        fos.close();

        fos = new FileOutputStream(new File(f,"a.out"));
        fos.write(out.getBytes());
        fos.close();
        return ServerResponse.success();
    }

}
