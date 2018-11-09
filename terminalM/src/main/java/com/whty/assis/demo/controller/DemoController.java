package com.whty.assis.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;

@Controller
@RequestMapping("/manage/demo")
public class DemoController extends BaseController{

	
	/**
	 * 跳转到按钮管理
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("跳转到按钮管理页面");
		}
		System.out.println("1");
		return "demo.list";
	}
	
}
