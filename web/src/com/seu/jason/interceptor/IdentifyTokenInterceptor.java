package com.seu.jason.interceptor;

import com.seu.jason.service.IUserManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ToZhu on 2015/10/29.
 */
public class IdentifyTokenInterceptor extends HandlerInterceptorAdapter {

    @Resource(name="userManager")
    IUserManager userManager;

    public void setUserManager(IUserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String uri = request.getRequestURI();
//
//        if( uri!=null && uri.trim().equals("/eshop/user") && request.getMethod().equals(RequestMethod.POST.name()))
//            return true;
//
//        String token = request.getHeader("token");
//        if(token == null)
//            return false;
//
//        int user_id = userManager.findUserByToken(token);
//        if(user_id <1){
//            return false;
//        }else{
//            return true;
//        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
