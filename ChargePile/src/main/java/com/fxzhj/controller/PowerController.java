package com.fxzhj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxzhj.model.Power;
import com.fxzhj.service.PowerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 设备电量表
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "/power")
public class PowerController {
	
	@Autowired
	private PowerService powerService;
	
	//设备电量报表界面 
	@RequestMapping(value = "powerJsp")
	public String powerJsp(){
		return "power.jsp";
	}
	
	/**
	 * 根据条件查询设备用电量
	 * @param power
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryPower")
	@ResponseBody
	public Map<String, Object> queryPower(Power power, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		List<Power> list =powerService.queryPower(power);
		PageInfo<Power> pageList = null;
		if(list!=null&&list.size()>0){
			pageList = new PageInfo<Power>(list);
		}
		
		if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
			map.put("total", pageList.getTotal());
			map.put("rows", pageList.getList());
		}else{
			map.put("total", 0);
			map.put("rows", new ArrayList<Power>());
		}
		return map;
	}
	
	/**
	 * 根据设备id查询设备明细电量
	 * @param deviceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryByDid")
	@ResponseBody
	public Map<String, Object> queryByDid(String deviceId, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		List<Power> list =powerService.queryByDid(deviceId);
		PageInfo<Power> pageList = null;
		if(list!=null&&list.size()>0){
			pageList = new PageInfo<Power>(list);
		}
		
		if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
			map.put("total", pageList.getTotal());
			map.put("rows", pageList.getList());
		}else{
			map.put("total", 0);
			map.put("rows", new ArrayList<Power>());
		}
		return map;
	} 

}
