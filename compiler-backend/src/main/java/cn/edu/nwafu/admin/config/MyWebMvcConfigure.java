package cn.edu.nwafu.admin.config;

import cn.edu.nwafu.admin.config.arguementresolver.CurUserMethodArgumentResolver;
import cn.edu.nwafu.admin.config.interceptor.AuthorityInterceptor;
import cn.edu.nwafu.admin.config.interceptor.CorsInterceptor;
import cn.edu.nwafu.admin.config.interceptor.LoginInterceptor;
import cn.edu.nwafu.admin.config.interceptor.RequestContextInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MyWebMvcConfigure implements WebMvcConfigurer
{

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private CorsInterceptor corsInterceptor;

    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    @Autowired
    private RequestContextInterceptor requestContextInterceptor;
    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(corsInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/detail/token")
                .excludePathPatterns("/user/vcode/register")
                .excludePathPatterns("/error/**")

                .addPathPatterns("/notice/**")
                .excludePathPatterns("/notice/detail/**")

                .addPathPatterns("/bundle/**")

                .addPathPatterns("/order/**")

                .addPathPatterns("/oj/**")

                .addPathPatterns("/pay/**")
                .excludePathPatterns("/pay/callback")

        ;

        registry.addInterceptor(requestContextInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(authorityInterceptor)
                .addPathPatterns("/**");

    }


    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }

    @Bean
    public CurUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurUserMethodArgumentResolver();
    }
}
