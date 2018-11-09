package com.smart.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.smart.model.Res;
import com.smart.model.User;
import com.smart.util.SpringUtil;

@Component
public class UserInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 4521257123188870984L;

	private static final Logger logger = Logger
			.getLogger(UserInterceptor.class);

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);
		Map<String, Object> session = actionContext.getSession();
		String requestURI = request.getRequestURI(); // 请求地址
		// String referer = request.getHeader("referer"); //来源地址

		/** 登陆判断 **/
		User user = (User) session.get("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.htm");
			return null;
		}

		/** 权限判断 **/
		String flag = SpringUtil.getConfig("security").trim();
		Map<String, Res> myResMap = (Map<String, Res>) session.get("resMap");
		if ("true".equals(flag)) {
			if (myResMap.get(requestURI) == null) {
				logger.info("无权限访问：" + requestURI);
				response.sendRedirect(request.getContextPath() + "/error.htm");
				return null;
			}
		}

		return invocation.invoke();
	}

	/**
	 * 如果是ajax请求的话返回-1 一般请求就返回到登录页面
	 * 
	 * @param uri
	 * @param response
	 * @return
	 */
	/*
	 * private String redirect(String uri, HttpServletResponse response) throws
	 * Exception { if (uri.toLowerCase().indexOf("ajax") != -1) {
	 * response.getWriter().write("-1"); return null; } else {
	 * response.sendRedirect("/relogin.htm"); return null; // return "relogin";
	 * } }
	 */
}
