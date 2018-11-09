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

import com.fxzhj.model.Port;
import com.fxzhj.service.PortService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 设备电量表
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "/port")
public class PortController {
	
	@Autowired
	private PortService portService;
	
	@RequestMapping(value = "queryPort")
	@ResponseBody
	public Map<String, Object> queryPort(String deviceId, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		List<Port> list =portService.queryPort(deviceId);
		PageInfo<Port> pageList = null;
		if(list!=null&&list.size()>0){
			pageList = new PageInfo<Port>(list);
		}
		
		if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
			map.put("total", pageList.getTotal());
			map.put("rows", pageList.getList());
		}else{
			map.put("total", 0);
			map.put("rows", new ArrayList<Port>());
		}
		return map;
	} 
	
	/**
	 * 根据id修改状态
	 * @param id
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updatePort")
	@ResponseBody
	public Map<String, Object> updatePort(String id, String status, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(status)) {
			try {
				portService.updatePort(Long.parseLong(id),Integer.parseInt(status));
				map.put("state", "SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("state", "ERROR");
			}
		}else{
			map.put("state", "FALSE");
			return map;
		}
		return map;
	}

}
