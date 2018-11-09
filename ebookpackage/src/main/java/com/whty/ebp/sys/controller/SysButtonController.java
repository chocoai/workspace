package com.whty.ebp.sys.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.common.util.GUIDGenerator;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.sys.model.SysButton;
import com.whty.ebp.sys.service.SysButtonService;


/**
 * 按钮管理
 * @author zhangc
 */
@Controller
@RequestMapping("/sys/button")
public class SysButtonController extends BaseController {

	@Autowired
	private SysButtonService buttonService;
	
	/**
	 * 跳转到按钮管理
	 */
	@RequestMapping(value="/init")
	public String init(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("跳转到按钮管理页面");
		}
		return "sys/button";
	}
	
	/**
	 * 查询按钮列表
	 */
	@RequestMapping(value="/buttonList")
	public void buttonList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("按钮管理列表数据加载");
		}
		String modularId = request.getParameter("MODULAR_ID");
		List<SysButton> buttonList = new ArrayList<SysButton>();
		if(StringUtils.isNotEmpty(modularId))
		{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("modularId", modularId);
	
			buttonList = buttonService.queryButtonByModular(paramMap);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write(JSONArray.fromObject(buttonList).toString());
	}
	
	/**
	 * 修改菜单按钮
	 */
	@RequestMapping(value="/saveButton")
	public void saveButton(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("修改菜单按钮");
		}
		String id = request.getParameter("id");
		String buttonName = request.getParameter("buttonName");
		String sortNum = request.getParameter("sortNum");
		String status = request.getParameter("status");
		String modularId = request.getParameter("modularId");
		
		SysButton  button = new SysButton();
		button.setButtonName(buttonName);
		if(StringUtils.isNotEmpty(sortNum)) button.setSortNum(Integer.parseInt(sortNum));
		button.setStatus(status);
		button.setModularId(modularId);

		if(StringUtils.isEmpty(id)){
			button.setId(GUIDGenerator.getGUID());
			buttonService.addButton(button);
		}
		else
		{
			button.setId(id);
			buttonService.updateButton(button);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("按钮删除");
		}
		String id = request.getParameter("id");
		
		buttonService.deleteButton(id);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}
}
