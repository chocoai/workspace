/**
 * 
 */
package com.whty.assis.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.base.controller.BaseController;

import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年8月14日
 */
@Controller
@RequestMapping("/manageUser2")
public class ManageUserController2 extends BaseController {

	@ResponseBody
	@RequestMapping(value = "/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String password) throws Exception {
		String code = "1";

		String code2 = request.getParameter("code");
		// 验证验证码
		String sessionCode = request.getSession().getAttribute("code").toString();
		if (code2 != null && !"".equals(code2) && sessionCode != null && !"".equals(sessionCode)) {
			if (code2.equalsIgnoreCase(sessionCode)) {
				response.getWriter().println("验证通过！");
			} else {
				code = "4";
			}
		} else {
			code = "4";
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", code);
		return jsonObject.toString();
	}
}
