package com.yhcrt.healthcloud.system.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.service.SysUserService;
import com.yhcrt.healthcloud.util.Md5PwdEncoder;

@Controller
public class LoginController {

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void check(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		JSONObject jsonObj = new JSONObject();
		try {
			password = Md5PwdEncoder.encodePassword(password);
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			SysUser user = sysUserService.selectByUserCode(username);
			subject.getSession().setAttribute("loginUser", user);
			jsonObj.put("result", true);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("message", e.getMessage());
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("message", e.getMessage());
		} catch (DisabledAccountException e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("message", e.getMessage());
		} catch (AuthenticationException e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("message", e.getMessage());
		}
		
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

}
