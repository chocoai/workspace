package com.whty.assis.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.common.util.SysConfig;

@Controller
@RequestMapping("/appDownLoad")
public class AppDownLoadController {

	@RequestMapping(value = "/down")
	public void down(HttpServletResponse response, HttpServletRequest request) {
		try {
			response.sendRedirect(SysConfig.getStrValue("newHdcourseDownUrl"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
