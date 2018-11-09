package com.whty.assis.sysrole.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.manage.model.ManageUserInfo;
import com.whty.assis.sysrole.model.SysRole;
import com.whty.assis.sysrole.service.SysroleService;
import com.whty.common.util.GUIDGenerator;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 版本管理
 * 
 * @author zhanggz
 */
@Controller
@RequestMapping("/manage/sysrole")
public class SysRoleController1 extends BaseController {

	@Autowired
	private SysroleService sysroleService;

	/**
	 * 跳转到角色列表管理
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("角色列表页数据加载");
		}
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String role_name = request.getParameter("role_name");
		paramMap.put("role_name", StringUtils.isNotEmpty(role_name) ? role_name.trim() : role_name);
		paramMap.put("status", request.getParameter("status"));
		paramMap.put("startTime", request.getParameter("startTime"));
		paramMap.put("endTime", request.getParameter("endTime"));

		PageContext page = PageContext.getContext();

		// 请自行验证
		if (null == currentPage || StringUtils.isNotEmpty(search_type)) {
			page.setCurrentPage(1);
			page.setPageSize(10);
			page.setTotalPage(0);
			page.setTotalRows(0);
		} else {
			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			page.setTotalPage(Integer.parseInt(totalPage));
		}
		page.setPagination(true);
		HandlerResult handlerResult = sysroleService.querySysRole(paramMap);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "sysrole.list";
	}

	/**
	 * 新增和编辑
	 */
	@RequestMapping(value = "/save")
	public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("角色新增和编辑");
		}
		String id = request.getParameter("id");
		String role_name = request.getParameter("role_name");
		String role_comment = request.getParameter("role_comment");

		SysRole sysRole = new SysRole();
		sysRole.setId(id);
		sysRole.setRole_name(role_name);
		sysRole.setRole_comment(role_comment);

		if (StringUtils.isEmpty(id)) {
			sysRole.setId(GUIDGenerator.getGUID());
			sysroleService.addSysRole(sysRole);
		} else {
			sysRole.setUpdate_time(new Date());
			sysroleService.updateSysRole(sysRole);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 启用和禁用
	 */
	@RequestMapping(value = "/enableAndDisable")
	public void enableAndDisable(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("角色启用和禁用");
		}
		String ids = request.getParameter("ids");
		String status = request.getParameter("status");

		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("status", status);
		paramap.put("idList", Arrays.asList(ids.split(",")));

		sysroleService.updateSysRoleStatus(paramap);

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
			logger.debug("角色删除");
		}
		String ids = request.getParameter("ids");

		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("idList", Arrays.asList(ids.split(",")));

		sysroleService.deleteSysRole(paramap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 根据角色查询模块列表
	 */
	@RequestMapping(value = "/queryModular")
	public void queryModular(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("根据角色查询模块列表");
		}
		String role_id = request.getParameter("role_id");

		List<Map<String,Object>> modularList = sysroleService.queryModularByRoleId(role_id);
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("role_id", role_id);
		List<Map<String,Object>> buttonList = sysroleService.queryButtonByRoleId(paramap);

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = null;
		for (Map<String,Object> map : modularList) {
			jsonObj = new JSONObject();
			jsonObj.put("id", map.get("id"));
			jsonObj.put("pId", map.get("parent_id"));
			jsonObj.put("name", map.get("modular_name"));
			jsonObj.put("isParent", "0".equals(map.get("is_parent").toString()));
			jsonObj.put("checked", "1".equals(map.get("checked").toString()));
			jsonObj.put("type", "modular");
			jsonArray.add(jsonObj);
		}

		for (Map<String,Object> map : buttonList) {
			jsonObj = new JSONObject();
			jsonObj.put("id", map.get("id"));
			jsonObj.put("pId", map.get("modular_id"));
			jsonObj.put("name", map.get("button_name"));
			jsonObj.put("checked", "1".equals(map.get("checked").toString()));
			jsonObj.put("type", "button");
			jsonArray.add(jsonObj);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(jsonArray.toString());
	}

	/**
	 * 根据角色ID和模块ID获取按钮列表
	 */
	@RequestMapping(value = "/queryButton")
	public void queryButton(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("根据角色ID和模块ID获取按钮列表");
		}
		String role_id = request.getParameter("role_id");
		String modular_id = request.getParameter("modular_id");

		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("role_id", role_id);
		paramap.put("modular_id", modular_id);

		List<Map<String,Object>> buttonList = sysroleService.queryButtonByRoleId(paramap);

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = null;
		for (Map<String,Object> map : buttonList) {
			jsonObj = new JSONObject();
			jsonObj.put("id", map.get("id"));
			jsonObj.put("pId", map.get("modular_id"));
			jsonObj.put("name", map.get("button_name"));
			jsonObj.put("checked", "1".equals(map.get("checked").toString()));
			jsonArray.add(jsonObj);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(jsonArray.toString());
	}

	/**
	 * 保存角色对应的模块和按钮权限信息
	 */
	@RequestMapping(value = "/saveRolePermission")
	public void saveRolePermission(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("保存角色对应的模块和按钮权限信息");
		}
		String role_id = request.getParameter("role_id");
		String modularIds = request.getParameter("modularIds");
		String buttonIds = request.getParameter("buttonIds");

		List<Map<String,Object>> roleModularList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> roleButtonList = new ArrayList<Map<String,Object>>();
		if (StringUtils.isNotEmpty(modularIds)) {
			String[] mids = modularIds.split(",");
			for (int i = 0; i < mids.length; i++) {
				Map<String,Object> map = new HashMap<String,Object> ();
				map.put("id", GUIDGenerator.getGUID());
				map.put("role_id", role_id);
				map.put("modular_id", mids[i]);
				roleModularList.add(map);
			}
		}

		if (StringUtils.isNotEmpty(buttonIds)) {
			String[] bids = buttonIds.split(",");
			for (int i = 0; i < bids.length; i++) {
				String[] id = bids[i].split("_");
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", GUIDGenerator.getGUID());
				map.put("role_id", role_id);
				map.put("modular_id", id[0]);
				map.put("button_id", id[1]);
				roleButtonList.add(map);
			}
		}

		sysroleService.saveRolePermission(role_id, roleModularList, roleButtonList);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 查询角色列表
	 */
	@RequestMapping(value = "/queryUserRole")
	public void queryUserRole(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("查询角色列表");
		}
		String user_id = request.getParameter("user_id");

		List<Map<String,Object>> list = sysroleService.querySysRoleByUserId(user_id);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(JSONArray.fromObject(list).toString());
	}

	/**
	 * 授予角色
	 */
	@RequestMapping(value = "/grantRole")
	public void grantRole(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("查询角色列表");
		}
		String user_ids = request.getParameter("user_ids");
		String role_ids = request.getParameter("role_ids");

		List<String> userIdList = new ArrayList<String>();
		List<String> roleIdList = new ArrayList<String>();
		if (StringUtils.isNotEmpty(user_ids)) {
			userIdList = Arrays.asList(user_ids.split(","));
		}
		if (StringUtils.isNotEmpty(role_ids)) {
			roleIdList = Arrays.asList(role_ids.split(","));
		}

		sysroleService.grantRole(userIdList, roleIdList);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 查询当前用户在当前模块下的按钮列表
	 */
	@RequestMapping(value = "/queryCurrUserButtons")
	public void queryCurrUserButtons(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("查询当前用户在当前模块下的按钮列表");
		}
		ManageUserInfo mUser = (ManageUserInfo) request.getSession().getAttribute("manageUser");
		String modular_id = request.getParameter("modular_id");

		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("user_id", mUser.getId());
		paramap.put("modular_id", modular_id);

		List<Map<String,Object>> buttonList = sysroleService.queryButtonByCurrUser(paramap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(JSONArray.fromObject(buttonList).toString());
	}

}
