package com.sky.controller.interceptor;

import com.sky.exception.GraceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {
    /**
     * 拦截请求，访问controller之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userId = request.getHeader("userId");
        String token = request.getHeader("token");

//        if (!StringUtils.hasLength(userId) || !StringUtils.hasLength(token)) {
//            log.error("用户信息校验不通过，信息不完整");
//            GraceException.display("用户信息校验不通过，信息不完整");
//            return false;
//        }
//
//        if (!userId.equalsIgnoreCase("1001") || !token.equalsIgnoreCase("1qaz2wsx")) {
//            log.error("用户权限不通过");
//            GraceException.display("用户权限不通过");
//            return false;
//        }

        return true;
    }

    /**
     * 请求controller之后，渲染视图之前
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 请求controller之后，渲染视图之后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
