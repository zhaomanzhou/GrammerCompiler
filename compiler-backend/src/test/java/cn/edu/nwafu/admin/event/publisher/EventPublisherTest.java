package cn.edu.nwafu.admin.event.publisher;

import cn.edu.nwafu.admin.event.event.UserRegisterEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/30 8:13 下午
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EventPublisherTest
{

    @Autowired
    private EventPublisher eventPublisher;

    @Test
    public void publishEvent() throws InterruptedException
    {
        UserRegisterEvent event = new UserRegisterEvent(this, 1L);
        eventPublisher.publishEvent(event);
        System.out.println("发布后");
    }
}