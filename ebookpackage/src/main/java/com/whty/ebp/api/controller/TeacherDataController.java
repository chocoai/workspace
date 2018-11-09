package com.whty.ebp.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.exception.BusinessException;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.ebp.api.utils.PrintResult;
import com.whty.ebp.manage.model.UserOptLog;
import com.whty.ebp.manage.service.UserOptLogService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/teacherController")
public class TeacherDataController {
	private static final Logger logger = LoggerFactory.getLogger(TeacherDataController.class);
	@Autowired
	private UserOptLogService userOptLogService;

	/**
	 * 数据上报接口
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/dataUpload", method = RequestMethod.POST)
	@ResponseBody
	public void dataUpload(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		// System.out.println(body);
		logger.info(body);
		Map map = new HashMap();
		// List<EbpApp> list = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			map = checkDataUploadParam(body);

			userOptLogService.saveUserOptLog(map);

		} catch (IOException e) {
			resultMap.put("result", Constants.SUCCESS_CODE);
			resultMap.put("resultDesc", "success");
			PrintResult.printWriter(response, JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
			return;
			// PrintResult.getFailNeedShowResult(e.getMessage(), response);

		} catch (BusinessException e) {
			resultMap.put("result", Constants.SUCCESS_CODE);
			resultMap.put("resultDesc", "fail");
			PrintResult.printWriter(response, JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
			return;
			// PrintResult.getFailNeedShowResult(e.getMessage(), response);
		}

		resultMap.put("result", Constants.SUCCESS_CODE);
		resultMap.put("resultDesc", "success");
		PrintResult.printWriter(response, JSONObject.fromObject(resultMap).toString());
		// PrintResult.getSuccessResult(list, response);
	}

	private Map checkDataUploadParam(String body) throws IOException, BusinessException {

		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map para = new HashMap();
		
		
		para.put("data", reqJson.get("data"));

		para.put("systemVersion", reqJson.get("systemVersion"));
		para.put("terminalType", reqJson.get("terminalType"));
		para.put("mobileBrand", reqJson.get("mobileBrand"));
		para.put("mobileID", reqJson.get("mobileID"));
		para.put("applyVersion", reqJson.get("applyVersion"));
		para.put("exceptionTime", reqJson.get("exceptionTime"));
		para.put("exceptionInfo", reqJson.get("exceptionInfo"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("courseNum", reqJson.optString("courseNum"));
		para.put("userSessionId", reqJson.optString("userSessionId"));
		para.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
		para.put("userId", reqJson.optString("userId"));
		
		para.put("startTime", reqJson.get("startTime"));
		para.put("endTime", reqJson.get("endTime"));
		
		para.put("platformCode", reqJson.get("platformCode"));
		return para;
	}

	/**
	 * 获取移动讲台数据上传次数
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/dataNum", method = RequestMethod.POST)
	@ResponseBody
	public void dataNum(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {

		Map map = new HashMap();
		// List<EbpApp> list = null;
		List<UserOptLog> list = null;
		try {
			// System.out.println("dataNum" + body);
			map = checkdataNumParam(body);

			list = userOptLogService.getDataNum2(map);

			int num = 0;

			for (UserOptLog bean : list) {
				if (bean.getNum() != null) {
					num = num + bean.getNum();
				}
			}

			Map<String, Object> resultMap = new HashMap<String, Object>(3);
			resultMap.put("result", Constants.SUCCESS_CODE);
			resultMap.put("resultDesc", "success");

			resultMap.put("num", num);

			PrintResult.printWriter(response, JSONObject.fromObject(resultMap).toString());
		} catch (IOException e) {
			e.printStackTrace();
			PrintResult.getFailNeedShowResult(e.getMessage(), response);

		} catch (BusinessException e) {
			e.printStackTrace();
			PrintResult.getFailNeedShowResult(e.getMessage(), response);
		}

		// for (UserOptLog bean : list) {
		// Map<String,Object> param = new HashMap<String, Object>();
		//
		// param.put("dataType", bean.getDataType());
		// param.put("num", bean.getNum());
		// data.add(param);
		// }
		// resultMap.put("data", data);

	}

	private Map checkdataNumParam(String body) throws IOException, BusinessException {

		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map para = new HashMap();
		para.put("myDate", reqJson.get("myDate"));

		para.put("userSessionId", reqJson.get("userSessionId"));
		para.put("platformCode", reqJson.get("platformCode"));
		para.put("loginPlatformCode", reqJson.get("loginPlatformCode"));
		para.put("userId", reqJson.get("userId"));

		para.put("dataType", reqJson.get("dataType"));
		// para.put("dataType", reqJson.get("dataType"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		return para;
	}

}
