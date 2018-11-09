package com.yhcrt.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;


/**
 * LogoutFilter
 */
public class UserLogoutFilter extends LogoutFilter {

    public static final String USER_LOG_OUT_FLAG = "/login/logout";

    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
	subject.logout();
	return USER_LOG_OUT_FLAG;
    }

 

}
