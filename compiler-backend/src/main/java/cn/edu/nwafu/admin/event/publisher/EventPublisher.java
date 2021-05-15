package cn.edu.nwafu.admin.event.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/30 8:10 下午
 */

@Component
@Slf4j
public class EventPublisher implements ApplicationContextAware
{

    private ApplicationContext applicationContext;

    public void publishEvent(ApplicationEvent event)
    {
        applicationContext.publishEvent(event);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }
}
