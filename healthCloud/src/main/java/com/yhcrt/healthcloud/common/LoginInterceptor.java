package com.yhcrt.healthcloud.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/* @Description: 登录拦截器
 * @version	1.0		2017年6月1日
 * @author jimmy
*/
public class LoginInterceptor implements HandlerInterceptor {

    /* Handler执行完之后调用的方法
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    /* Handler执行完之后，ModelAndView返回之前调用的方法
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    /* Handler执行之前调用的方法
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
            Object arg2) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        Object frontUser = request.getSession().getAttribute("member");
        if(user!=null||frontUser!=null){
            return true;
        }
        response.sendRedirect("/healthCloud/login");
        return false;
    }

}
