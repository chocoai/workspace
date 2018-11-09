package com.whty.ebp.api.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whty.common.util.Constants;

public class PrintResult {
	private static final Logger logger = LoggerFactory.getLogger(PrintResult.class);

	protected final static SerializerFeature[] features = { SerializerFeature.SortField,
			SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,
			SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat };

	private final static SerializerFeature[] featuresNoNull = { SerializerFeature.SortField,
			SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat };

	/**
	 * 成功返回
	 * 
	 * @param data
	 *            成功数据
	 * @param response
	 * @throws Exception
	 */
	public static void getSuccessResult(Object data, HttpServletResponse response) {

		Result result = new Result(Constants.SUCCESS);
		result.setDesc("success");
		result.setData(data);
		String json = null;
		try {
			json = JSONObject.toJSONString(result, features);

		} catch (Exception e) {
			e.printStackTrace();
		}
		printWriter(response, json);
	}

	/**
	 * 成功返回,null值不输出
	 * 
	 * @param data
	 * @param response
	 */
	public static void getSuccessResultNoNull(Object data, HttpServletResponse response) {

		Result result = new Result(Constants.SUCCESS);
		result.setDesc("success");
		result.setData(data);
		String json = JSONObject.toJSONString(result, featuresNoNull);
		printWriter(response, json);
	}

	/**
	 * 成功返回
	 * 
	 * @param data
	 *            成功数据
	 * @param response
	 * @throws Exception
	 */
	public static void getSuccessPageResult(Object data, int totalPage, HttpServletResponse response) {

		ResultPage result = new ResultPage(Constants.SUCCESS);
		result.setDesc("");
		result.setData(data);
		result.setTotalPage(totalPage);
		String json = JSONObject.toJSONString(result, features);
		printWriter(response, json);
	}

	/**
	 * 错误返回
	 * 
	 * @param data
	 *            错误信息
	 * @param response
	 * @throws Exception
	 */
	public static void getErrorResult(Object data, HttpServletResponse response) {
		Result result = new Result(Constants.FAIL);
		if (data != null) {
			result.setDesc(data.toString());
		} else {
			result.setDesc("error");
		}
		result.setData("");
		String json = JSONObject.toJSONString(result, features);
		printWriter(response, json);
	}

	/**
	 * 错误返回，需要提示给用户看
	 * 
	 * @param data
	 * @param response
	 */
	public static void getFailNeedShowResult(Object data, HttpServletResponse response) {
		Result result = new Result(Constants.FAIL_NEED_SHOW);
		if (data != null) {
			result.setDesc(data.toString());
		}
		result.setData("");
		String json = JSONObject.toJSONString(result, features);
		printWriter(response, json);
	}

	public static void printWriter(HttpServletResponse response, String json) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		if (logger.isDebugEnabled()) {
			logger.debug("response info:" + json);
		}
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
