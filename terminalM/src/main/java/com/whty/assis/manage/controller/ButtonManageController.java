package com.whty.assis.manage.controller;

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

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.manage.model.Button;
import com.whty.assis.manage.service.ButtonService;
import com.whty.common.util.GUIDGenerator;

/**
 * 按钮管理
 * 
 * @author zhangc
 */
@Controller
@RequestMapping("/manage/buttonManage")
public class ButtonManageController extends BaseController {

	@Autowired
	private ButtonService buttonService;

	/**
	 * 跳转到按钮管理
	 */
	@RequestMapping(value = "/init")
	public String init(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("跳转到按钮管理页面");
		}
		return "baseProperty/buttonManage";
	}

	/**
	 * 查询按钮列表
	 */
	@RequestMapping(value = "/buttonList")
	public void buttonList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("按钮管理列表数据加载");
		}
		String modularId = request.getParameter("MODULAR_ID");
		List<Button> buttonList = new ArrayList<Button>();
		if (StringUtils.isNotEmpty(modularId)) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("modularId", modularId);

			buttonList = buttonService.queryButtonByModular(paramMap);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write(JSONArray.fromObject(buttonList).toString());
	}

	/**
	 * 跳转到按钮管理
	 *//*
		 * @RequestMapping(value="/buttonList") public String
		 * buttonList(HttpServletRequest request, Model model) throws Exception
		 * { if (logger.isDebugEnabled()) { logger.debug("按钮管理列表页数据加载"); }
		 * String modularId = request.getParameter("MODULAR_ID");
		 * if(StringUtils.isNotEmpty(modularId)) { String currentPage =
		 * request.getParameter("currPage"); String pageSize =
		 * request.getParameter("pageSize"); String totalPage =
		 * request.getParameter("totalPage");
		 * 
		 * Map<String, Object> paramMap = new HashMap<String, Object>();
		 * paramMap.put("modularId", modularId); paramMap.put("modularName",
		 * request.getParameter("MODULAR_NAME")); paramMap.put("tId",
		 * request.getParameter("tId")); PageContext page =
		 * PageContext.getContext();
		 * 
		 * //请自行验证 if (null == currentPage) { page.setCurrentPage(1);
		 * page.setPageSize(10); page.setTotalPage(0); page.setTotalRows(0); }
		 * else { page.setCurrentPage(Integer.parseInt(currentPage));
		 * page.setPageSize(Integer.parseInt(pageSize));
		 * page.setTotalPage(Integer.parseInt(totalPage)); }
		 * page.setPagination(true); HandlerResult handlerResult =
		 * buttonService.queryButton(paramMap); model.addAttribute("buttonList",
		 * handlerResult.getResultList()); model.addAttribute("listJson",
		 * JSONArray.fromObject(handlerResult.getResultList()));
		 * model.addAttribute("page", page); model.addAllAttributes(paramMap);
		 * page.setPagination(false); } return "baseProperty/buttonManage"; }
		 */
	/**
	 * 修改菜单按钮
	 */
	@RequestMapping(value = "/saveButton")
	public void saveButton(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("修改菜单按钮");
		}
		String id = request.getParameter("id");
		String buttonName = request.getParameter("buttonName");
		String sortNum = request.getParameter("sortNum");
		String status = request.getParameter("status");
		String modularId = request.getParameter("modularId");

		Button button = new Button();
		button.setButtonName(buttonName);
		if (StringUtils.isNotEmpty(sortNum))
			button.setSortNum(Integer.parseInt(sortNum));
		button.setStatus(status);
		button.setModularId(modularId);

		if (StringUtils.isEmpty(id)) {
			button.setId(GUIDGenerator.getGUID());
			buttonService.addButton(button);
		} else {
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
	@RequestMapping(value = "/delete")
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
