package cn.edu.nwafu.admin.config.interceptor;

import cn.edu.nwafu.admin.domain.User;
import cn.edu.nwafu.admin.config.context.ThreadLocalCache;
import cn.edu.nwafu.admin.constant.ContextConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/18 11:57 下午
 */
@Component
public class RequestContextInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        User curUser = (User) request.getAttribute("curUser");
        String token = request.getHeader(ContextConstant.TOKEN);
        String namespace = request.getHeader(ContextConstant.NAMESPACE);
        ThreadLocalCache.set(ContextConstant.TOKEN, token);
        ThreadLocalCache.set(ContextConstant.NAMESPACE, namespace);
        ThreadLocalCache.set(ContextConstant.USER, curUser);
        return super.preHandle(request, response, handler);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        ThreadLocalCache.remove(ContextConstant.NAMESPACE);
        ThreadLocalCache.remove(ContextConstant.USER);
        ThreadLocalCache.remove(ContextConstant.TOKEN);
        super.afterCompletion(request, response, handler, ex);
    }

}
