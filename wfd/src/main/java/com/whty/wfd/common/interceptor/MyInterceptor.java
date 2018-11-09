package com.whty.wfd.common.interceptor;

import com.github.pagehelper.util.StringUtil;
import com.whty.wfd.common.utils.CookiesUtil;
import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.service.PersonCenterService;
import com.whty.wfd.page.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * \* User: zjd \* Date: 2018/8/18 \* Time: 9:47 \* Description: \
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

	@Autowired
	private TUserMapper tUserMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		try {
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			if (tUser != null) {
				return true;
			} else {
				String userId = CookiesUtil.getCookieValue(request, "userObj");
				if (StringUtil.isNotEmpty(userId)) {
					tUser = tUserMapper.selectByPrimaryKey(Integer.parseInt(userId));
					request.getSession().setAttribute("userObj", tUser);
					return true;
				}
			}
		} catch (Exception e) {
			response.sendRedirect("error.html");
			return false;
		}
		response.sendRedirect("error.html");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}