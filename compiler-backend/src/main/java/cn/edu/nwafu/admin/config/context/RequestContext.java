package cn.edu.nwafu.admin.config.context;

import cn.edu.nwafu.admin.domain.User;
import cn.edu.nwafu.admin.constant.ContextConstant;

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
