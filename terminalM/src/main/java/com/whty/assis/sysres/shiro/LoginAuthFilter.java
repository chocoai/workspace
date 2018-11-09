package com.whty.assis.sysres.shiro;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.whty.assis.sysres.model.SysModular;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.assis.sysres.service.SysUserService;


/* @Description: shiro登录拦截
 * @version	1.0		2017年6月20日
 * @author zjd
*/
public class LoginAuthFilter extends AccessControlFilter{

	@Autowired
	private SysUserService sysUserService;

    /* 是否通过请求
     * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
	@Override
    protected boolean isAccessAllowed(ServletRequest arg0, ServletResponse arg1, Object arg2) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        TaManageUserInfo loginUser = (TaManageUserInfo) subject.getSession().getAttribute("loginUser");
        if(subject.isRemembered()){
            if(null == loginUser){
            	TaManageUserInfo user = sysUserService.selectByAccount((String) subject.getPrincipal());
                subject.getSession().setAttribute("loginUser", user);
            }
        }
        if(null == loginUser){
            return false;
        }
        /*HttpServletRequest request = (HttpServletRequest) arg0;
        String requestType = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(requestType)){//判断是不是Ajax请求
            return subject.isAuthenticated()||subject.isRemembered();
        }else{
        	List<SysModular> modularList = (List<SysModular>) subject.getSession().getAttribute("modularList");
        	String requestUri = request.getRequestURI();
    		String contextPath = request.getContextPath();
    		String url = requestUri.substring(contextPath.length()).replace("/", "");
    		if(modularList!=null && modularList.size()>0){
    			for(SysModular sysModular :modularList){
    				if(sysModular.getModularPath().replace("/", "").equals(url)){
    					return subject.isAuthenticated()||subject.isRemembered();
    				}
    			}
    		}
        }
        return false;*/
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
