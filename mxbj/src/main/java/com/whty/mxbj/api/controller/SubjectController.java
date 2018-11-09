package com.whty.mxbj.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.mxbj.api.service.SubjectService;
import com.whty.mxbj.base.controller.BaseController;
import com.whty.mxbj.common.constants.ResultConstants;

//@RestController
@RequestMapping("api/subject")
@Controller
public class SubjectController extends BaseController{

	private Map<String, Object> resultMap;

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) {
		subjectService.synTask();
	}

	// @RequestMapping(value = "test", method = RequestMethod.GET)
	// public void test(HttpServletRequest request, HttpServletResponse
	// response) {
	// subjectService.synTask();
	// }

	@RequestMapping(value = "getSubjectInfos")
	public void getSubjectInfos(HttpServletRequest request, HttpServletResponse response) {
		resultMap = new HashMap<String, Object>();
		try {
			resultMap = subjectService.getSubjectInfos();
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response,resultMap);
//		return JSONObject.fromObject(resultMap).toString();
	}

}
