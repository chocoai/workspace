package com.whty.mxbj.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.mxbj.api.service.PenFirmvareService;
import com.whty.mxbj.base.controller.BaseController;
import com.whty.mxbj.common.constants.ResultConstants;


@Controller
@RequestMapping("api/penFirmvare")
public class PenFirmvareController extends BaseController{

	@Autowired
	private PenFirmvareService penFirmvareService;
	
	private Map<String, Object> resultMap;
	
	/**
	 * 获取最新版本的固件型号
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "getNewVersion", method = RequestMethod.POST)
	public void getNewVersion(HttpServletRequest request, HttpServletResponse response, Model model,@RequestBody String body) {
		
		Map<String, Object> map;
		resultMap = new HashMap<String, Object>();
		try {
			map = penFirmvareService.getNewVersionCheckParam(body);
			resultMap = penFirmvareService.getNewVersion(map);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response,resultMap);
	}
}
