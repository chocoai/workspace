package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.api.model.EbpUserClass;
import com.whty.assis.api.service.UserClassService;
import com.whty.assis.api.utils.LoginUtil;
import com.whty.assis.api.utils.PrintResult;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.common.util.CommonFunction;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/userClass")
public class UserClassController extends BaseController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(UserClassController.class);
	// private static Logger logger =
	// Logger.getLogger(UserClassController.class);
	@Autowired
	UserClassService userClassService;

	@RequestMapping(value = "importCardNumber", method = RequestMethod.GET)
	@ResponseBody
	public void importCardNumber(HttpServletRequest request, HttpServletResponse response) {

		try {
			// userClassService.importCardNumber();
			// userClassService.getAllCardNumberData();
			userClassService.getAllClassInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "ExportToAAM", method = RequestMethod.GET)
	@ResponseBody
	public void ExportToAAM(HttpServletRequest request, HttpServletResponse response) {

		try {
			userClassService.ExportToAAM();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取内容
	 * 
	 * @param request
	 * @param response
	 * @param param
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getContent", method = RequestMethod.POST)
	@ResponseBody
	public void getContent(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		try {
			// 检查参数
			Map para = new HashMap();
			para = checkParamGetContent(body);
			String usessionId = (String) para.get("usessionId");
			String platformCode = (String) para.get("platformCode");
			Map map = LoginUtil.get(usessionId, platformCode);
			if ((Boolean) map.get("success")) {
				EbpUserClass userClass = userClassService.findUserClass(map);
				PrintResult.getSuccessResult(userClass, response);
			} else {
				PrintResult.getErrorResult("sessionId失效", response);
			}

		} catch (Exception e) {
			// 捕捉异常构造返回信息
			logger.error("获取内容" + e.getMessage(), e);
			PrintResult.getErrorResult(e.getMessage().toString(), response);
		}
	}

	/**
	 * 更新内容
	 * 
	 * @param request
	 * @param response
	 * @param param
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updateContent", method = RequestMethod.POST)
	@ResponseBody
	public void updateContent(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		try {
			// 检查参数
			Map para = new HashMap();
			para = checkParamUpdateContent(body);
			userClassService.updateUserClass(para);
			PrintResult.getSuccessResult("", response);
		} catch (Exception e) {
			// 捕捉异常构造返回信息
			logger.error("更新内容" + e.getMessage(), e);
			PrintResult.getErrorResult(e.getMessage().toString(), response);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map checkParamUpdateContent(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		// logger.info(reqJson.toString());
		Map para = new HashMap();
		para.put("userId", reqJson.get("userId"));
		para.put("platformCode", reqJson.get("platformCode"));
		para.put("content", reqJson.get("content"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map checkParamGetContent(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		// logger.info(reqJson.toString());
		Map para = new HashMap();
		para.put("usessionId", reqJson.get("usessionId"));
		para.put("platformCode", reqJson.get("platformCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	/**
	 * 获取内容
	 * 
	 * @param request
	 * @param response
	 * @param param
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getVirtualClass", method = RequestMethod.POST)
	@ResponseBody
	public void getVirtualClass(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		try {
			// 检查参数
			Map para = new HashMap();
			para = checkParamGetVirtualClass(body);
			EbpUserClass virtualClass = userClassService.findUserVirtualClass(para);
			PrintResult.getSuccessResult(virtualClass, response);
		} catch (Exception e) {
			// 捕捉异常构造返回信息
			logger.error("获取内容" + e.getMessage(), e);
			PrintResult.getErrorResult(e.getMessage().toString(), response);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map checkParamGetVirtualClass(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		logger.info(reqJson.toString());
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

}
