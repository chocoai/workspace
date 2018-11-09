package com.whty.mxbj.api.interceptor;

import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.whty.mxbj.common.utils.TimeUtils;

public class RequestInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (HandlerMethod.class.equals(handler.getClass())) {
			String path = request.getContextPath();
			String scheme = request.getScheme();
			String serverName = request.getServerName();
			int port = request.getServerPort();
			String basePath = scheme + "://" + serverName + ":" + port + path;

			HandlerMethod method = (HandlerMethod) handler;
			Object controller = method.getBean();

			System.out.println("mxbj action report ------------" + TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT)
					+ "---------------------");
			System.out.println("Controller   :" + controller.getClass());
			System.out.println("Method       :" + method.getMethod());
			System.out.println("Path         :" + basePath);
			System.out.println("Interceptor  :" + this.getClass());
			System.out.println("-----------------------------------------------------------------------");
		}
		return true;
	}

}
