package cn.edu.nwafu.oj;

import cn.edu.nwafu.admin.controller.vo.response.JudgeResult;
import cn.edu.nwafu.oj.io.CompileHandler;
import cn.edu.nwafu.oj.io.JudgeHandler;
import cn.edu.nwafu.oj.st.PreHandler;
import cn.edu.nwafu.oj.st.StructureCompution;
import cn.edu.nwafu.parser.ParseException;
import cn.edu.nwafu.utils.Result;
import com.idofast.common.response.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/11 1:25 上午
 */
@Component
@Slf4j
public class Flow
{
    @Autowired
    private CompilerConst compilerConst;

    @Autowired
    private StructureCompution structureCompution;

    @Autowired
    private CompileHandler compileHandler;

    @Autowired
    private PreHandler preHandler;

    @Autowired
    private JudgeHandler judgeHandler;

    public JudgeResult judge(Long userId, Long problemId)
    {

        String userFolder = compilerConst.getUserFolder() + userId + "/" + problemId + "/";
        String problemFolder = compilerConst.getProblemFolder() + problemId + "/";

        JudgeResult judgeResult = new JudgeResult();
        judgeResult.setOk(true);
        judgeResult.setCurrent(0);
        log.info("开始判题，用户ID：{}, 题目ID：{}", userId, problemId);
        try
        {
            compileHandler.compile(userFolder);
            judgeResult.setCompile(Result.ofSuccess());
        } catch (Exception e)
        {
            e.printStackTrace();
            final String replace = e.getMessage().replace(compilerConst.getUserFolder() + userId + "/" + problemId + "/", "");
            judgeResult.setCompile(Result.ofFalse(replace));
            judgeResult.setMsg(replace);
            return judgeResult;
        }


        try
        {
            preHandler.prehandle(userFolder);
        } catch (Exception e)
        {
            e.printStackTrace();
            judgeResult.setCompile(Result.ofFalse(e.getMessage()));
            judgeResult.setMsg(e.getMessage());
            return judgeResult;
        }


        judgeResult.setCurrent(1);
        try
        {
            final Result compute = structureCompution.compute(problemFolder, userFolder);
            if(!compute.isSuccess())
            {
                judgeResult.setStructure(compute);
                judgeResult.setMsg(compute.getMsg());
                return judgeResult;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            judgeResult.setStructure(Result.ofFalse(e.getMessage()));
            judgeResult.setMsg(e.getMessage());
            return judgeResult;
        }

        judgeResult.setCurrent(2);
        try
        {
            judgeHandler.judge(userFolder, problemFolder);
            judgeResult.setJudge(Result.ofSuccess());
        } catch (Exception e)
        {
            e.printStackTrace();
            judgeResult.setJudge(Result.ofFalse(e.getMessage()));
            judgeResult.setMsg(e.getMessage());
            return judgeResult;
        }

        judgeResult.setCurrent(3);
        return judgeResult;
    }
}
