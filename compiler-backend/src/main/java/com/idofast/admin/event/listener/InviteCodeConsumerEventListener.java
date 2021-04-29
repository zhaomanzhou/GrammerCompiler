package com.idofast.admin.event.listener;

import com.idofast.admin.event.event.InviteCodeConsumerEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/1/1 12:06 上午
 * 邀请码生效后进行返现奖励
 */
public class InviteCodeConsumerEventListener implements ApplicationListener<InviteCodeConsumerEvent>
{
    @Async
    @Override
    public void onApplicationEvent(InviteCodeConsumerEvent event)
    {

    }
}
