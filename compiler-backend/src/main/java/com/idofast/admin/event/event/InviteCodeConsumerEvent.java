package com.idofast.admin.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/1/1 12:16 上午
 */
public class InviteCodeConsumerEvent extends ApplicationEvent
{
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public InviteCodeConsumerEvent(Object source)
    {
        super(source);
    }
}
