package com.whty.ebp.base.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whty.ebp.api.utils.PrintResult;


/**
 * Controller的超类
 * 
 * @author shijiapeng
 * @date 2015年8月11日 上午11:02:17
 */
public class BaseController {
	//json转换配置
	private final static SerializerFeature[] features = {
		SerializerFeature.SortField,
		SerializerFeature.DisableCircularReferenceDetect
     };
	
	protected Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 输出json
	 * @param response
	 * @param data
	 */
	protected void printJson(HttpServletResponse response, Object data)  {
		
		String json = JSONObject.toJSONString(data, features);
		if (logger.isInfoEnabled()) {
			logger.info(">>>>>> response json：" + json);
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 输出文本
	 */
	protected void printText(HttpServletResponse response, String text)  {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().print(text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	@ExceptionHandler(Exception.class)   //在Controller类中添加该注解方法即可(注意：添加到某个controller，只针对该controller起作用)  
    public void exceptionHandler(Exception ex,HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		logger.error(ex.getMessage(), ex);
		
		if(ex instanceof HttpMediaTypeNotSupportedException){
			PrintResult.getErrorResult("只支持  application/json;charset=utf-8  格式数据, 请设置 Content-Type", response);
			 
		} else if(ex instanceof HttpMessageNotReadableException){
			PrintResult.getErrorResult("json格式错误:" + ex.getMessage(), response);
			
		} else if(ex instanceof UnrecognizedPropertyException){
			PrintResult.getErrorResult("json字段格式错误:" + ex.getMessage(), response);
			
		} else {
			PrintResult.getErrorResult(ex.toString(), response);
		}
		
	}
	
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
						valueStr +=value + ",";
					}
					if(StringUtils.isNotEmpty(valueStr)){
						returnMap.put(name, valueStr.substring(0,valueStr.length() - 1));
					}
				} else if(StringUtils.isNotEmpty(valueObj.toString())){
					returnMap.put(name, valueObj.toString());
				}
			}
		}
		return returnMap;
	}
}
