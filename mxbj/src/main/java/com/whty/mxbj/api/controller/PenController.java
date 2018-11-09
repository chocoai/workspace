package com.whty.mxbj.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.mxbj.api.service.PenService;
import com.whty.mxbj.base.controller.BaseController;
import com.whty.mxbj.common.constants.ResultConstants;
import com.whty.mxbj.common.constants.SysConstants;

//@RestController
@RequestMapping("api/pen")
@Controller
public class PenController extends BaseController{

	@Autowired
	private PenService penService;

	private Map<String, Object> resultMap;

	@RequestMapping(value = "getConfig", method = RequestMethod.GET)
	public void getConfig(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> param = new HashMap<String, Object>();
		resultMap = new HashMap<String,Object>();
		param.put("notesVersion", SysConstants.NOTES_VERSION);
		param.put("noteIds", SysConstants.NOTES_IDS);
		List<Map<String, Object>> noteTemplate = penService.getNoteTemplate();
		param.put("noteList", noteTemplate);
		printJson(response,resultMap);
//		return JSONObject.fromObject(param).toString();
	}

	@RequestMapping(value = "bind", method = RequestMethod.POST)
	public void bind(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;
		resultMap = new HashMap<String, Object>();
		try {
			map = penService.bindCheckParam(body);
			resultMap = penService.bind(map);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response,resultMap);
//		return JSONObject.fromObject(resultMap).toString();
	}

	@RequestMapping(value = "unbind", method = RequestMethod.POST)
	public void unbind(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;
		resultMap = new HashMap<String, Object>();

		try {
			map = penService.unbindCheckParam(body);
			penService.unbind(map);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response,resultMap);
//		return JSONObject.fromObject(resultMap).toString();
	}

}
