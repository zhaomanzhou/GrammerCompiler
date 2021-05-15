package cn.edu.nwafu.admin.config.interceptor;

import cn.edu.nwafu.admin.domain.User;
import cn.edu.nwafu.admin.annotation.AuthRole;

import com.idofast.common.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaomanzhou
 * @date 2020/3/19 10:39 下午
 * 该拦截器必须在LoginInterceptor拦截器之后配置
 * 否则无法获取RequestAttribute里的curUser
 */
@Slf4j
@Component
public class AuthorityInterceptor extends HandlerInterceptorAdapter
{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (handler instanceof HandlerMethod)
        {

            HandlerMethod method = (HandlerMethod) handler;
            // 获取方法上有没有打注解
            AuthRole authRole = method.getMethodAnnotation(AuthRole.class);
            // 不为null表示该方法打了注解需要校验是否登录了
            if (authRole != null)
            {

                // 先获取当前请求的请求参数
                String query = StringUtils.isNotEmpty(request.getQueryString()) ? ("?" + request.getQueryString()) : "";

                // 先获取当前请求的请求完整url
                String callBackUrl = request.getRequestURL().toString() + query;
                log.debug("当前请求的url：{}", callBackUrl);
                log.debug("当前请求需要登录后才能请求，开始检验是否登录！");

                User user = (User) request.getAttribute("curUser");
                if (user == null)
                {
                    request.setAttribute("reason", "代码错误，请先将该接口加入登陆拦截器内再使用角色注解");
                    request.getRequestDispatcher("/error/unauth").forward(request, response);
                    return false;
                }

                log.debug("校验完毕，用户已登录，开始校验权限");
                RoleEnum requiredRole = authRole.role();
                RoleEnum actualRole = user.getRole();
                if (actualRole.getCode() <= requiredRole.getCode())
                {
                    request.setAttribute("currentUser", user);
                    log.info("用户{}, 成功访问了{}接口", user.getEmail(), callBackUrl);
                    return true;
                }

                log.warn("用户{}进行了越权行为, 访问{}接口，开始进行重定向", user.getEmail(), callBackUrl);

                // 如果没有登录
                // 校验是否配置了自定义的跳转地址
                String redirectUrl = StringUtils.isNotEmpty(authRole.returnUrl()) ? authRole.returnUrl() : "/error/unauth";

                response.sendRedirect(redirectUrl);

                return false;
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        super.postHandle(request, response, handler, modelAndView);
    }

}
