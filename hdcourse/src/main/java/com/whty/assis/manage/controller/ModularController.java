package com.whty.assis.manage.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.manage.model.Modular;
import com.whty.assis.manage.service.ModularService;
import com.whty.common.util.GUIDGenerator;

@Controller
@RequestMapping(value = "/manage/modular")
public class ModularController extends BaseController {

	@Autowired
	private ModularService modularService;

	/**
	 * 跳转到菜单管理页面
	 */
	@RequestMapping(value = "/list")
	public String save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		return "modular/list";
	}

	/**
	 * 获取菜单树
	 */
	@RequestMapping(value = "/modularTree")
	public void modularTree(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		List<Modular> list = modularService.queryAllModular();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = null;
		JSONObject modularMap = new JSONObject();
		for (Modular modular : list) {
			jsonObj = new JSONObject();
			jsonObj.put("id", modular.getId());
			jsonObj.put("pId", modular.getParentId());
			jsonObj.put("name", modular.getModularName());
			jsonObj.put("isParent", modular.getIsParent() == 0);
			jsonArray.add(jsonObj);
			modularMap.put(modular.getId(), JSONObject.fromObject(modular));
		}
		JSONObject resultObj = new JSONObject();
		resultObj.put("zNodes", jsonArray);
		resultObj.put("modularMap", modularMap);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write(resultObj.toString());
	}

	/**
	 * 新增模块
	 */
	@RequestMapping(value = "/addModular")
	public void addModular(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Modular modular = new Modular();
		modular.setId(GUIDGenerator.getGUID());
		modular.setModularName(request.getParameter("modularName"));
		modular.setParentId(request.getParameter("parentId"));
		modular.setIsParent(Integer.parseInt(request.getParameter("isParent")));
		modular.setStatus(1);
		modularService.addModular(modular);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write(JSONObject.fromObject(modular).toString());
	}

	/**
	 * 更新模块
	 */
	@RequestMapping(value = "/updateModular")
	public void updateModular(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> paramap = new HashMap<String, Object>();
		paramap.put("id", request.getParameter("id"));
		paramap.put("modularName", request.getParameter("modularName"));
		paramap.put("modularPath", request.getParameter("modularPath"));
		paramap.put("status", request.getParameter("status"));
		paramap.put("modularSort", request.getParameter("modularSort"));
		paramap.put("imgcss", request.getParameter("imgcss"));
		modularService.updateModular(paramap);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write("success");
	}

	/**
	 * 删除模块
	 */
	@RequestMapping(value = "/deleteModular")
	public void deleteModular(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String ids = request.getParameter("ids");
		if (StringUtils.isNotEmpty(ids)) {
			modularService.deleteModular(Arrays.asList(ids.split(",")));
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write("success");
	}
}