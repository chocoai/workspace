package com.whty.assis.api.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;

/**
 * 心跳接口
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/heartBeat")
public class HeartBeatController extends BaseController {

	@RequestMapping(value = "/check")
	public void check(HttpServletRequest request, HttpServletResponse response) {
		// String tycsResult ="000001";
		// Map<String,Object> resultMap = new HashMap<String, Object>();
		// try {
		// String json =
		// HttpUtils.getInstance().httpGet(SysConfig.getStrValue("tycs.heartbeats.url"));
		// JSONObject result = JSONObject.fromObject(json);
		// if ("000000".equals(result.get("result"))) {
		// tycsResult="000000";
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// resultMap.put("result", "000000");
		// resultMap.put("resultMsg", "success");
		// resultMap.put("tycsResult", tycsResult);
		// printJson(response, new Date().getTime());

		printText(response, Long.toString(new Date().getTime() / 1000));
	}
}
