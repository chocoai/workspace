package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.api.model.EbpClassStudent;
import com.whty.assis.api.service.ClassStudentService;
import com.whty.assis.api.utils.PrintResult;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.common.util.CommonFunction;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/classStudent")
@Repository("ebookpackage")
public class ClassStudentController extends BaseController {

	@Autowired
	ClassStudentService classStudentService;

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
			classStudentService.updateClassStudent(para);
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
		para.put("classId", reqJson.get("classId"));
		para.put("platformCode", reqJson.get("platformCode"));
		para.put("content", reqJson.get("content"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getContent", method = RequestMethod.POST)
	@ResponseBody
	public void getContent(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		try {
			// 检查参数
			Map para = new HashMap();
			para = checkParamGetContent(body);

			// ClassStudent classStudent = new ClassStudent();
			EbpClassStudent classStudent = new EbpClassStudent();

			if ("0".equals(para.get("classType").toString())) {
				classStudent = classStudentService.findEduClassStudent(para);
			} else if ("1".equals(para.get("classType").toString())) {
				classStudent = classStudentService.findSelfClassStudent(para);
			}

			PrintResult.getSuccessResult(classStudent, response);
		} catch (Exception e) {
			// 捕捉异常构造返回信息
			logger.error("获取内容" + e.getMessage(), e);
			PrintResult.getErrorResult(e.getMessage().toString(), response);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkParamGetContent(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		logger.info(reqJson.toString());

		Map para = new HashMap();
		para.put("classId", reqJson.get("classId"));
		para.put("platformCode", reqJson.get("platformCode"));
		para.put("classType", reqJson.get("classType"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		if (!"0".equals(para.get("classType").toString()) && !"1".equals(para.get("classType").toString())) {
			throw new BusinessException("参数classType的值只能为0或1");
		}
		return para;
	}

}
