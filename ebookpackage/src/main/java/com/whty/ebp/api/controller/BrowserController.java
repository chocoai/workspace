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
import com.whty.ebp.api.utils.PrintResult;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.Browser;
import com.whty.ebp.manage.service.BrowserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/browser")
public class BrowserController extends BaseController {

	@Autowired
	private BrowserService browserService;
	
	@RequestMapping(value = "/whiteList", method = RequestMethod.POST)
	@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			map = checkListParam(body);
		//	map.put("modelCode", "S2W");
			List<Browser> list = browserService.queryWhiteList(map);  
			StringBuilder sb = new StringBuilder();
			String str = browserService.queryWhiteVersion();
			for (Browser bean : list) {
				sb.append(bean.getAddress()+";");
			}
			resultMap.put("version", str);
			resultMap.put("list", sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		resultMap.put("mac", "qqq");
		PrintResult.printWriter(response, JSONObject.fromObject(resultMap).toString());
	}
	
	@RequestMapping(value = "/label", method = RequestMethod.POST)
	@ResponseBody
	public void label(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			map = checkListParam(body);
		//	map.put("modelCode", "S2W");
			List<Browser> list = browserService.queryLabel(map); 
			List<Map<String,Object>> ls = new ArrayList<Map<String,Object>>();
			String str = browserService.queryLabelVersion();
			for (Browser bean : list) {
				Map<String,Object> label = new HashMap<String, Object>();
				label.put("name", bean.getName());
				label.put("address", bean.getAddress());
				label.put("logo", "");
				label.put("groupId", bean.getGroupId());
				label.put("groupName", bean.getGroupName());
				ls.add(label);
			}
			resultMap.put("version", str);
			resultMap.put("appTagList", ls);
		} catch (Exception e) {
			e.printStackTrace();
		} 
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
		para.put("modelCode", reqJson.optString("flatModel"));
		
		return para;
	}
}
