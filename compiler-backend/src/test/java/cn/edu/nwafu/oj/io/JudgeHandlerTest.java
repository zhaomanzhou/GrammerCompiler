package cn.edu.nwafu.oj.io;

import cn.edu.nwafu.admin.exception.JudgeException;
import cn.edu.nwafu.oj.CompilerConst;
import com.idofast.common.response.error.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 6:33 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class JudgeHandlerTest
{

    @Autowired
    private JudgeHandler judgeHandler;

    @Autowired
    private CompilerConst compilerConst;
    @Test
    void judge() throws BusinessException, IOException, JudgeException, InterruptedException
    {
        judgeHandler.judge(compilerConst.getUserFolder() + "1/1/", compilerConst.getProblemFolder() + "1/");
    }
}