package com.yhcrt.demo.core;

import java.io.IOException;




import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhcrt.demo.model.SysUser;
import com.yhcrt.demo.util.Constant;

/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String contextPath = request.getContextPath();
		String url = request.getRequestURI();
		url = url.replace(contextPath, "");
		
		String vsResuqestBy = request.getHeader("Request-By");
		String redirectUri = contextPath + "/login.jsp";
		//如果是Ext的ajax请求则返回响应{"timeout":true,"redirectUri":"/ServletContext/login.jsp"}
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constant.SESSION_SYS_USER);
		if(vsResuqestBy != null && "Ext".endsWith(vsResuqestBy) && sysUser == null){
		    response.getWriter().write("{\"timeout\":true,\"redirectUri\":\""+redirectUri+"\"}");
		    return;
		}
		
		if(url.indexOf("/login.jsp") > -1 || url.contains("/static") || url.contains("/login")) {
			filterChain.doFilter(servletRequest, servletResponse);
		    return;
		}
		
	    if (sysUser == null ) { // 跳转到登陆页面
	    	response.sendRedirect(contextPath+"/login.jsp");
	    }else {  // 已经登陆,继续此次请求   
		    filterChain.doFilter(request, response);
	    }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
