/**
 * @Title:   DashboardController.java 
 * @Package: com.yhcrt.healthcloud.base.controller  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月17日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yhcrt.healthcloud.system.entity.SysRes;
import com.yhcrt.healthcloud.system.service.SysResService;

@Controller
public class DashboardController {

	@Autowired
	private SysResService sysResService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "redirect:main";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "main";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String resId = request.getParameter("resId");
		SysRes res = sysResService.getSysResByResId(Integer.parseInt(resId));
		return "redirect:" + res.getResUrl();
	}

}