package com.fxzhj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 界面菜单
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {
	
	@RequestMapping(value = "login")
	public String login(){
		return "login.jsp";
	}

}
