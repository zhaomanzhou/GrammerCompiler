package com.idofast.admin.config.context;

import com.idofast.admin.constant.ContextConstant;
import com.idofast.admin.domain.User;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime
 */
public class RequestContext implements ContextConstant
{
    public static User getCurrentUser()
    {
        return (User) ThreadLocalCache.get(USER);
    }

    public static String getToken()
    {
        return (String) ThreadLocalCache.get(TOKEN);
    }

    public static Integer getNamespace()
    {
        return (Integer) ThreadLocalCache.get(NAMESPACE);
    }
}
