package com.fxzhj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fxzhj.model.Elevator;
import com.fxzhj.service.ElevatorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 电梯信息
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "/elevator")
public class ElevatorController {
	
	@Autowired
	private ElevatorService elevatorService; 
	
	/**
	 * 跳转电梯界面
	 * @return
	 */
	@RequestMapping(value = "/elevatorJsp")
	public String elevatorJsp(){
		return "elevator.jsp";
	}
	
	/**
	 * 根据条件查询小区电梯
	 * @param elevator
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryElevator")
	@ResponseBody
	public Map<String, Object> queryElevator(Elevator elevator, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		List<Elevator> list =elevatorService.queryElevator(elevator);
		PageInfo<Elevator> pageList = null;
		if(list!=null&&list.size()>0){
			pageList = new PageInfo<Elevator>(list);
		}
		
		if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
			map.put("total", pageList.getTotal());
			map.put("rows", pageList.getList());
		}else{
			map.put("total", 0);
			map.put("rows", new ArrayList<Elevator>());
		}
		return map;
	}
	
	/**
	 * 根据id判断是新增还是修改
	 * @param elevator
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveElevator")
	@ResponseBody
	public Map<String, Object> saveElevator(Elevator elevator, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String json = request.getParameter("data");
		if(StringUtil.isNotEmpty(json)){
			elevator=JSON.parseObject(json,Elevator.class);
			elevator.setCreaterUser(null);
			try {
				if(elevator.getId() == null){
					elevator.setCreateTime(new Date());
					//添加重复验证
					int count = elevatorService.countByNameOrCode(elevator);
					if(count == 0){
						elevatorService.add(elevator);
						map.put("state","SUCCESS");
					}else{
						map.put("state","SAME");
					}
				}else{
					//添加重复验证
					int count = elevatorService.countExId(elevator);
					if(count == 0){
						elevatorService.update(elevator);
						map.put("state","SUCCESS");
					}else{
						map.put("state","SAME");
					}
				}
				map.put("state","SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("state","ERROR");
			}
		}else{
			map.put("state","FALSE");
		}
		return map;	
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="delElevator")
	@ResponseBody
	public Map<String, Object> delElevator(String ids,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String[] idStr = ids.split(",");
			int[] idArr=new int[idStr.length];
			for (int i = 0; i < idStr.length; i++) {
	        	if(StringUtil.isNotEmpty(idStr[i])){
	        		idArr[i]=Integer.parseInt(idStr[i]); 
	        	}
	        }
	        if(idArr.length>0){
	        	elevatorService.batchDelete(idArr); 
	        	map.put("state","SUCCESS");
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();
	        map.put("state","FALSE");
	    }
		return map;
	}

}
