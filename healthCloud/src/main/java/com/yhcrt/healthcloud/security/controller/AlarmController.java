/**
 * @Title:   AlarmController.java 
 * @Package: com.yhcrt.healthcloud.security.controller  
 * @Description: 
 * @author: rpf
 * @date: 2017年12月21日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.security.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.healthcloud.common.DataTable;
import com.yhcrt.healthcloud.device.entity.DsSosLinkman;
import com.yhcrt.healthcloud.device.service.SosLinkmanService;
import com.yhcrt.healthcloud.security.entity.Alarm;
import com.yhcrt.healthcloud.security.service.AlarmService;

/**
 * @ClassName: AlarmController
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年12月21日
 */
@Controller
@RequestMapping("/alarm")
public class AlarmController {

	@Autowired
	private AlarmService alarmService;
	@Autowired
	private SosLinkmanService linkmanService;
	
	@RequestMapping(value = "/toList")
	public String toList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		
		String memberName = request.getParameter("memberName");
		String imei = request.getParameter("imei");
		String alarmType = request.getParameter("alarmType");
		String alarmTime = request.getParameter("alarmTime");
		String isRead = request.getParameter("isRead");
		
		modelMap.put("memberName", memberName);
		modelMap.put("imei", imei);
		modelMap.put("alarmType", StringUtils.isBlank(alarmType) ? "" : alarmType);
		modelMap.put("alarmTime", alarmTime);
		modelMap.put("isRead", StringUtils.isBlank(isRead) ? "" : isRead);
		
		return "/alarm/list";
	}

	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		
		String memberName = request.getParameter("memberName");
		String imei = request.getParameter("imei");
		String alarmType = request.getParameter("alarmType");
		String alarmTime = request.getParameter("alarmTime");
		String isRead = request.getParameter("isRead");
		
		try {
			DataTable dataTable = new DataTable(request);
			PageHelper.startPage(dataTable.getPageNum(), dataTable.getPageSize());
			List<Alarm> alarms = alarmService.listAlarm(memberName, imei, alarmTime, isRead, alarmType);
			PageInfo<Alarm> pageInfo = new PageInfo<Alarm>(alarms);
			//封装数据给DataTables  
			dataTable.setDraw(dataTable.getDraw());  
			dataTable.setData(pageInfo.getList());
			dataTable.setRecordsTotal((int)pageInfo.getTotal());    
			dataTable.setRecordsFiltered(dataTable.getRecordsTotal()); 
			String jsonString = JSON.toJSONString(dataTable);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/viewAlarmDetail")
	public String viewAlarmDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String alarmId = request.getParameter("alarmId");
		Alarm alarm = alarmService.getAlarmById(alarmId);
		alarmService.updateAlarmStatus(alarmId);
		modelMap.put("alarm", alarm);
		return "/alarm/alarm";
	}
	
	@RequestMapping(value = "/viewSOSLinkMan")
	public String viewSOSLinkMan(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String imei = request.getParameter("imei");
		List<DsSosLinkman> linkmans = linkmanService.listByArgs(imei);
		modelMap.put("linkmans", linkmans);
		return "/alarm/linkmans";
	}
	
	@RequestMapping(value = "/countUnreadAlarm")
	public void countUnreadAlarm(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try {
			Integer unreadAlarmNum = alarmService.countUnreadAlarm();
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("unreadAlarmNum", unreadAlarmNum);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
