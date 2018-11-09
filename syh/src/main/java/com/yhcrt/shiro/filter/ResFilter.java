package com.yhcrt.shiro.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.yhcrt.entity.system.SysUser;
import com.yhcrt.shiro.utils.ShiroFilterUtils;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.LoggerUtils;

/**
 * 
 * 权限校验 Filter
 * 
 */
public class ResFilter extends AuthorizationFilter {
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		//判断是否为系统管理员
		Subject subject = getSubject(request, response);
		SysUser sysUser = (SysUser)subject.getPrincipal();
		if(sysUser.getState().equals(Constants.Middle_02)){//用户状态为-2的为管理员用户
			return Boolean.TRUE;
		}
		
		//先判断带参数的权限判断
		if(null != mappedValue){
			String[] arra = (String[])mappedValue;
			for (String permission : arra) {
				if(subject.isPermitted(permission)){
					return Boolean.TRUE;
				}
			}
		}
		HttpServletRequest httpRequest = ((HttpServletRequest)request);
		
		String uri = httpRequest.getRequestURI();//获取URI
		String basePath = httpRequest.getContextPath();//获取basePath
		if(null != uri && uri.startsWith(basePath)){
			uri = uri.replace(basePath, "");
		}
		if(subject.isPermitted(uri)){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws IOException {
		
			Subject subject = getSubject(request, response);  
	        if (null == subject.getPrincipal()) {//表示没有登录，重定向到登录页面  
	            saveRequest(request);  
	            WebUtils.issueRedirect(request, response, getLoginUrl());  
	        } else {  
	            if (StringUtils.hasText(getUnauthorizedUrl())) {//如果有未授权页面(即是否定义无权限的页面)跳转过去  
	            	if(ShiroFilterUtils.isAjax(request)){
	        			Map<String,String> resultMap = new HashMap<String, String>();
	        			LoggerUtils.debug(getClass(), "当前用户没有权限，并且是Ajax请求！");
	        			resultMap.put("states", "404");
	        			resultMap.put("message", "\u5F53\u524D\u7528\u6237\u6CA1\u6709\u6743\u9650");//当前用户没有权限！
	        			ShiroFilterUtils.out(response, resultMap);
	        		}else{
	        			WebUtils.issueRedirect(request, response, getUnauthorizedUrl());  
	        		}
	            } else {//否则返回401未授权状态码  
	        			WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);  
	            }  
	        }  
		return Boolean.FALSE;
	}
}
