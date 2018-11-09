package com.whty.ebp.api.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.exception.BusinessException;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.ebp.api.utils.PrintResult;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.AppBlackList;
import com.whty.ebp.manage.model.NetWorkBlackList;
import com.whty.ebp.manage.service.AppBlackListService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/api/apiAppBlackList")
public class ApiAppBlackListController extends BaseController {
	@Autowired
	private AppBlackListService AppBlackListService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			map = checkListParam(body);
			List<AppBlackList> list = AppBlackListService.apiQuery(map);
			List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
			for (AppBlackList bean : list) {
				Map<String, Object> newWorkAddress = new HashMap<String, Object>();
				newWorkAddress.put("pkg", bean.getPkg());
				ls.add(newWorkAddress);
			}
			resultMap.put("pkg", ls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("result", Constants.SUCCESS_CODE);
		resultMap.put("resultDesc", "success");
		PrintResult.printWriter(response, JSONObject.fromObject(resultMap).toString());
	}

	private Map checkListParam(String body) throws IOException, BusinessException {

		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map para = new HashMap();

		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("platformCode", reqJson.optString("platformCode"));
		para.put("modelCode", reqJson.optString("modelCode"));

		return para;
	}
}
