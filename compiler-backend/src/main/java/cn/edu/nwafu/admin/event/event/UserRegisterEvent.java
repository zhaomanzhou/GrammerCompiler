package cn.edu.nwafu.admin.event.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/30 11:06 下午
 */
public class UserRegisterEvent extends ApplicationEvent
{
    /**
     * 用户的Id
     */
    @Getter
    private Long id;

    public UserRegisterEvent(Object source, Long id)
    {
        super(source);
        this.id = id;
    }
}
