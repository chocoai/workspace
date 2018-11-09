package com.whty.wfd.base.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// protected SessionInfo sessionInfo;

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

	// protected Logger logger = Logger.getLogger(this.getClass());

	// org.apache.log4j.Logger bbb =
	// org.apache.log4j.Logger.getLogger(this.getClass());
	// protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// /**
	// * 输出json
	// *
	// * @param response
	// * @param data
	// */
	// protected void printJson(HttpServletResponse response, Object data) {
	//
	// String jsonString = JSONObject.fromObject(data).toString();
	//// if (logger.isInfoEnabled()) {
	//// logger.info(">>>>>> response json：" + jsonString);
	//// }
	//
	// response.setCharacterEncoding("utf-8");
	// response.setContentType("application/json; charset=utf-8");
	// try {
	// response.getWriter().print(jsonString);
	// } catch (IOException e) {
	// logger.error(e.getMessage(), e);
	// }
	// }

	// /**
	// * 输出文本
	// */
	// protected void printText(HttpServletResponse response, String text) {
	//
	// response.setCharacterEncoding("utf-8");
	// response.setContentType("text/html; charset=utf-8");
	// try {
	// response.getWriter().print(text);
	// } catch (IOException e) {
	// logger.error(e.getMessage(), e);
	// }
	// }

	/**
	 * 从request中获得参数Map，并返回可读的Map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	protected Map<String, Object> getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null != valueObj) {
				if (valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					String valueStr = "";
					for (String value : values) {
						valueStr += value + ",";
					}
					if (StringUtils.isNotEmpty(valueStr)) {
						returnMap.put(name, valueStr.substring(0, valueStr.length() - 1));
					}
				} else if (StringUtils.isNotEmpty(valueObj.toString())) {
					returnMap.put(name, valueObj.toString());
				}
			}
		}
		return returnMap;
	}
}
