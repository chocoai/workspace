package com.yhcrt.healthcloud.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.service.SysUserService;

/* @Description: shiro登录拦截
 * @version	1.0		2017年6月20日
 * @author jimmy
*/
public class rememberAuthFilter extends AccessControlFilter{

    @Autowired
    SysUserService sysUserService;

    /* 是否通过请求
     * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest arg0, ServletResponse arg1, Object arg2) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        SysUser loginUser = (SysUser) subject.getSession().getAttribute("loginUser");
        if(subject.isRemembered()){
            if(null == loginUser){
                SysUser user = sysUserService.selectByUserCode((String) subject.getPrincipal());
                subject.getSession().setAttribute("loginUser", user);
            }
        }
        if(null == loginUser){
            return false;
        }
        return subject.isAuthenticated()||subject.isRemembered();
    }

    /* 登录拒绝时
     * @see org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected boolean onAccessDenied(ServletRequest arg0, ServletResponse arg1) throws Exception {
        redirectToLogin(arg0, arg1);
        return false;
    }

}
