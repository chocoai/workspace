package com.whty.page.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.whty.assis.manage.model.ManageUserInfo;
import com.whty.assis.sysrole.service.SysroleService;
import com.whty.page.util.ServiceLocator;

public class PermissionTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private static String TAG_BUTTON = "button";
	private static String TAG_A = "a";
	private static String PERMISSION_MODULAR = "permission_modular_";

	protected ServiceLocator service = ServiceLocator.getInstance();

	/* html标签名字 ：按钮 button 链接：a */
	private String name;

	/* html标签值 */
	private String value;

	/* 触发事件的函数名称 */
	private String method;

	/* 样式 */
	private String style;

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * 标签初始方法
	 */
	public int doStartTag() {

		return SKIP_BODY;
	}

	/**
	 * 标签结束方法
	 */
	@SuppressWarnings("unchecked")
	public int doEndTag() {
		/******** 获取cookie中的modularId ********/
		String modularId = null;
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if ("modular_id".equals(c.getName())) {
				modularId = c.getValue();
				break;
			}
		}
		/******** 获取session 中按钮权限信息 若无则查询数据库 ********/
		if (StringUtils.isNotBlank(modularId)) {
			String sessionKey = PERMISSION_MODULAR + modularId;
			HttpSession session = pageContext.getSession();
			List<String> buttonNameList = (List<String>) session.getAttribute(sessionKey);
			ManageUserInfo mUser = (ManageUserInfo) session.getAttribute("manageUser");
			// 用户不存在则表示未登录
			if (null == buttonNameList && null != mUser) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", mUser.getId());
				param.put("modularId", modularId);
				SysroleService sysroleService = (SysroleService) ServiceLocator.getService("sysroleService");
				buttonNameList = sysroleService.queryButtonNamesByUserId(param);
				session.setAttribute(sessionKey, buttonNameList);
			}
			JspWriter out = pageContext.getOut();
			try {
				out.write(transformContent(buttonNameList));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return EVAL_PAGE;
	}

	/**
	 * 释放资源
	 */
	public void release() {
		super.release();
	}

	private String transformContent(List<String> names) {
		StringBuffer content = new StringBuffer("");
		for (String str : names) {
			if (str.equals(value)) {
				if (TAG_BUTTON.equals(name)) {
					content.append("<input type=\"" + name + "\" ").append("value=\"" + value + "\" ")
							.append("class=\"" + style + "\" ").append("onclick=\"" + method + "\"/>");
				} else if (TAG_A.equals(name)) {
					content.append("<a href=\"javascript:void(0);\" ").append("class=\"" + style + "\" ")
							.append("onclick=\"" + method + "\">").append(value + "</a>");
				}
			}
		}

		return content.toString();
	}

}
