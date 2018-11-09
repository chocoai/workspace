package com.whty.mxbj.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void printText(HttpServletResponse response, String text) {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().print(text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	protected void printJson(HttpServletResponse response, Object data) {

		String jsonString = JSONObject.fromObject(data).toString();

		logger.info(">>>>>> response json：" + jsonString);

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
