package com.yhcrt.healthcloud.device.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.device.entity.CallRecord;
import com.yhcrt.healthcloud.device.service.CallRecordService;

@Controller
@RequestMapping("/callRecord")
public class CallRecordController extends BaseController {

	@Autowired
	private CallRecordService callRecordService;
	
	/**
	 * 呼叫列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		
		String callName = request.getParameter("callName");
		String phoneNo = request.getParameter("phoneNo");
		String callType = request.getParameter("callType");
		String createTime = request.getParameter("createTime");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNoneBlank(callName)){
			callName = callName.trim();
			map.put("callName", callName);
		}
		if(StringUtils.isNoneBlank(phoneNo)){
			phoneNo = phoneNo.trim();
			map.put("phoneNo", phoneNo);
		}
		if(StringUtils.isNoneBlank(callType)){
			callType = callType.trim();
			map.put("callType", callType);
		}
		if(StringUtils.isNoneBlank(createTime)){
			createTime = createTime.trim();
			map.put("createTime", createTime);
		}
		List<CallRecord> list = callRecordService.queryList(map);
		modelMap.put("list", list);
		modelMap.put("callName", callName);
		modelMap.put("phoneNo", phoneNo);
		modelMap.put("callType", callType);
		modelMap.put("createTime", createTime);
		
		return "/alarm/call_list";
	}

	/**
	 * 保存呼叫记录
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addCallRecord")
	@ResponseBody
	public JSONObject addCallRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		JSONObject map = new JSONObject();
		String phoneNo = request.getParameter("phoneNo");
		String callType = request.getParameter("callType");
		if (StringUtils.isBlank(phoneNo)) {
			map.put("state", "-2");
			map.put("msg", "参数为空!");
			return map;
		}
		try {
			callRecordService.insert(phoneNo,callType);
			map.put("state", "1");
			map.put("phoneNo", phoneNo);
			map.put("msg", "保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "-1");
			map.put("msg", "保存异常!");
		}
		return map;
	}
	
	/**
	 * 接听电话操作
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/connectCall")
	@ResponseBody
	public JSONObject connectCall(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		JSONObject map = new JSONObject();
		String phoneNo = request.getParameter("phoneNo");
		if (StringUtils.isNotBlank(phoneNo)) {
			try {
				callRecordService.connectCall(phoneNo);
				map.put("state", "1");
				map.put("msg", "修改成功!");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("state", "0");
				map.put("msg", "修改失败!");
			}
		} else {
			map.put("state", "-2");
			map.put("msg", "参数为空!");
		}
		return map;
	}
	
	
	
	/**
	 * 挂断电话操作
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/disConnectCall")
	@ResponseBody
	public JSONObject disConnectCall(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		JSONObject map = new JSONObject();
		String phoneNo = request.getParameter("phoneNo");
		if (StringUtils.isNotBlank(phoneNo)) {
			try {
				callRecordService.disConnectCall(phoneNo);
				map.put("state", "1");
				map.put("msg", "修改成功!");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("state", "0");
				map.put("msg", "修改失败!");
			}
		} else {
			map.put("state", "-2");
			map.put("msg", "参数为空!");
		}
		return map;
	}

}
