package cn.edu.nwafu.oj.st;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/10 1:36 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class PreHandlerTest
{
    @Autowired
    private PreHandler preHandler;

    @Test
    void prehandle() throws IOException, InterruptedException
    {
        preHandler.prehandle("/Users/zhaomanzhou/IdeaProjects/GrammerCompiler/afile/user/1/1/");
    }
}