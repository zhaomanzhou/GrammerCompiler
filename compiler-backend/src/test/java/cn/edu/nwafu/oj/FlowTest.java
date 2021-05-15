package cn.edu.nwafu.oj;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/5/11 1:32 上午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class FlowTest
{

    @Autowired
    private Flow flow;

    @Test
    void judge()
    {
        System.out.println(flow.judge(10L, 2L));
    }
}