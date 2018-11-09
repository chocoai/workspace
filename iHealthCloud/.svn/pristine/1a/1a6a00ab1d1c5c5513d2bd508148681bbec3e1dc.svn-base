package com.yhcrt.iHealthCloud.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;

/**
 * app集成H5过滤器
 * 
 * @author ql
 */
public class AppFilter implements Filter {

	public FilterConfig config;
	@Autowired
	MemberMapper memberMapper;

	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest hrequest = (HttpServletRequest) request;
			// HttpServletResponseWrapper wrapper = new
			// HttpServletResponseWrapper((HttpServletResponse) response);
			String query = hrequest.getQueryString();
			if (query != null && !query.equals("")) {
				if (query.indexOf("&") != -1) {
					Map<String, String> map = new HashMap<String, String>();
					String array[] = query.split("&");
					for (String value : array) {
						String val[] = value.split("=");
						for (int i = 0; i < val.length; i++) {
							map.put(val[0], val[1]);
						}
					}
					if (map != null) {
						if (map.get("u") != null) {
							hrequest.setAttribute("a", map.get("a"));
							hrequest.setAttribute("u", map.get("u"));
							Member user = (Member) hrequest.getSession().getAttribute("memberSession");// 判断用户是否登录
							if (user == null) {
								Member memberDto = memberMapper
										.selectByUserId(Integer.parseInt(map.get("u").toString()));
								if (null != memberDto) {
									hrequest.getSession().setAttribute("memberSession", memberDto);
								}
							} else {
								if (map.get("i") != null && map.get("i").equals("1")) {
									Member memberDto = memberMapper
											.selectByUserId(Integer.parseInt(map.get("u").toString()));
									if (null != memberDto) {
										hrequest.getSession().setAttribute("memberSession", memberDto);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
		return;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
		ServletContext sc = config.getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(sc);

		if (cxt != null && cxt.getBean("memberMapper") != null && memberMapper == null)
			memberMapper = (MemberMapper) cxt.getBean("memberMapper");
	}
}
