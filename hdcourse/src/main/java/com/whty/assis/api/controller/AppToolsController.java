/**
 * 
 */
package com.whty.assis.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.api.service.AppToolsService;
import com.whty.assis.api.utils.PrintResult;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.manage.model.Modular;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年8月7日
 */
@Controller
@RequestMapping("/api/appTools")
public class AppToolsController extends BaseController {

	@Autowired
	private AppToolsService appToolsService;

	@RequestMapping(value = "/getTools")
	public void getTools(HttpServletResponse response, HttpServletRequest request, @RequestBody String body) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			// 检查参数
			Map<String, Object> para = new HashMap<String, Object>();
			para = appToolsService.checkgetTools(body);
			JSONArray jsonArray = appToolsService.getTools(para);

			result.put("result", "000000");
			result.put("data", jsonArray);
			printJson(response, JSONObject.fromObject(result));
		} catch (Exception e) {

			PrintResult.getErrorResult(e.getMessage().toString(), response);
		}
	}

}