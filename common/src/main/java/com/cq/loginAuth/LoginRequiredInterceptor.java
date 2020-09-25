package com.cq.loginAuth;

import jodd.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;

/**
 * @ClassName : LoginRequiredInterceptor
 * @Description : 自定义接口权限校验的拦截
 * @Author : WXD
 * @Date: 2020-09-24 11:35
 */
@Slf4j
public class LoginRequiredInterceptor implements HandlerInterceptor {
    /**
     * 接口没有使用LoginRequired注释，或是注释权限不是GLOBAL的全部禁止
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截前拦截");
        HandlerMethod methodHandle = (HandlerMethod) handler;
        LoginRequired annotation = methodHandle.getMethod().getAnnotation(LoginRequired.class);
        if (ObjectUtils.isEmpty(annotation) || !annotation.auth().equals(AuthContext.GLOBAL)) {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print("该接口没有访问权限");

            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
