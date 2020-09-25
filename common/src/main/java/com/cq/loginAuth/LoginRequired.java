package com.cq.loginAuth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName : LoginRequired
 * @Description : 接口权限校验注解类
 * @Author : WXD
 * @Date: 2020-09-24 11:31
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    /**
     * 接口的描述
     * @return
     */
    String description();

    /**
     * 接口权限
     * @return
     */
    String auth() default "";
}
