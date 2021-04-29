package com.idofast.admin.annotation;


import com.idofast.common.enums.RoleEnum;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.annotation.*;

@Target({ElementType.METHOD}) // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AuthRole
{

    RoleEnum role() default RoleEnum.PLAIN;

    RoleEnum value() default RoleEnum.PLAIN;
    /**
     * 用来确定没有登录后跳到哪里
     * 如果有值，则使用returnUrl做为跳转，否则根据业务跳到指定url
     * @return
     */
    String returnUrl() default "unauth";
}
