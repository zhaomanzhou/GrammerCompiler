package cn.edu.nwafu.oj.io;

import com.idofast.common.response.error.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 4:36 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class compileHandlerTest
{
    @Autowired
    private CompileHandler compileHandler;

    @Test
    void judge() throws IOException, InterruptedException, BusinessException
    {
        compileHandler.compile("/Users/zhaomanzhou/IdeaProjects/GrammerCompiler/afile/user/1/1/");
    }
}