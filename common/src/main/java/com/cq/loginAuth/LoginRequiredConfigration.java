package com.cq.loginAuth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : LoginRequiredConfigration
 * @Description : 拦截器配置
 * @Author : WXD
 * @Date: 2020-09-24 11:39
 */
@Configuration
public class LoginRequiredConfigration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginRequiredInterceptor()).addPathPatterns("/**");
    }
}
