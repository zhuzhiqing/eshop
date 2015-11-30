package com.seu.jason.interceptor;

import com.seu.jason.auth.AuthPassport;
import com.seu.jason.service.IUserManager;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ToZhu on 2015/10/29.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Resource(name="userManager")
    IUserManager userManager;

    public void setUserManager(IUserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){

            AuthPassport authPassport = ((HandlerMethod)handler).getMethodAnnotation(AuthPassport.class);

            //没有声明需要权限，或者不声明不验证权限
            if(authPassport == null || authPassport.validate() == false)
                return true;
            else{
                //这里实现权限验证逻辑
                String token = request.getHeader("token");
                if(token == null) {
                    response.setStatus(401);
                    return false;
                }

                AuthPassport.SecrityType secrityType = authPassport.secrityType();
                if(secrityType.equals(AuthPassport.SecrityType.PUBLIC)) {               //访问公共资源
                    int user_id = userManager.findUserByToken(token);
                    if (user_id > 0)
                        return true;
                    else {
                        response.setStatus(401);
                        return false;
                    }
                }
                else  {         //if(secrityType.equals(AuthPassport.SecrityType.PRIVATE))
                        //访问私有子夜
                    // response.setStatus(403);
                    return true;
                }
            }
        }
        else {
            return true;
        }
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
