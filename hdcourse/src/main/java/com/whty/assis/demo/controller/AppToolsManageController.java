/**
 * 
 */
package com.whty.assis.demo.controller;

import java.util.Arrays;
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

import com.whty.assis.api.model.AppTools;
import com.whty.assis.api.service.AppToolsService;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.dao.IdDao;
import com.whty.common.util.SysConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年8月8日
 */
@Controller
@RequestMapping(value = "/manage/appTools")
public class AppToolsManageController extends BaseController {

	@Autowired
	private AppToolsService appToolsService;

	@Autowired
	private IdDao idDao;

	@RequestMapping(value = "/addAppTools")
	public void addAppTools(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		AppTools appTools = new AppTools();

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", "t_app_tools");
		Integer id = idDao.getId(idParam);

		appTools.setId(id);
		appTools.setAppName(request.getParameter("appName"));
		appTools.setParentId(Integer.valueOf(request.getParameter("parentId")));
		appTools.setIsParent(Integer.parseInt(request.getParameter("isParent")));
		appToolsService.addAppTools(appTools);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write(JSONObject.fromObject(appTools).toString());
	}

	/**
	 * 更新模块
	 */
	@RequestMapping(value = "/updateAppTools")
	public void updateAppTools(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		// Map<String,Object> paramap = new HashMap<String,Object>();
		// paramap.put("id", request.getParameter("id"));
		// paramap.put("modularName", request.getParameter("modularName"));
		// paramap.put("modularPath", request.getParameter("modularPath"));
		// paramap.put("status", request.getParameter("status"));
		// paramap.put("modularSort", request.getParameter("modularSort"));
		// paramap.put("imgcss", request.getParameter("imgcss"));
		appToolsService.updateAppTools(request);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write("success");
	}

	/**
	 * 删除模块
	 */
	@RequestMapping(value = "/deleteAppTools")
	public void deleteAppTools(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String ids = request.getParameter("ids");
		if (StringUtils.isNotEmpty(ids)) {
			appToolsService.deleteAppTools(Arrays.asList(ids.split(",")));
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write("success");
	}

	/**
	 * 跳转到应用
	 */
	@RequestMapping(value = "/list")
	public String save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return "appTools/list";
	}

	/**
	 * 获取菜单树
	 */
	@RequestMapping(value = "/appToolsTree")
	public void modularTree(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		List<AppTools> list = appToolsService.listByCondition(param);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = null;
		JSONObject modularMap = new JSONObject();
		for (AppTools appTools : list) {
			jsonObj = new JSONObject();
			jsonObj.put("id", appTools.getId());
			jsonObj.put("pId", appTools.getParentId());
			jsonObj.put("name", appTools.getAppName() + "(" + appTools.getModelCode() + ")");
			jsonObj.put("subFileExt", appTools.getSubFileExt() == null ? "" : appTools.getSubFileExt());
			jsonObj.put("version", appTools.getVersion() == null ? "" : appTools.getVersion());
			jsonObj.put("downUrl", appTools.getDownUrl() == null ? "" : appTools.getDownUrl());
			jsonObj.put("icon", appTools.getIcon() == null ? "" : appTools.getIcon());
			jsonObj.put("clickIcon", appTools.getClickIcon() == null ? "" : appTools.getClickIcon());
			jsonObj.put("fileSize", appTools.getFileSize() == null ? "" : appTools.getFileSize());
			jsonObj.put("description", appTools.getDescription() == null ? "" : appTools.getDescription());
			jsonObj.put("subjectId", appTools.getSubjectId() == null ? "" : appTools.getSubjectId());
			jsonObj.put("isFolder", appTools.isFolder());
			jsonObj.put("modelCode", appTools.getModelCode() == null ? "" : appTools.getModelCode());
			jsonObj.put("sorNum", appTools.getSortNum() == null ? "" : appTools.getSortNum());
			jsonObj.put("needlogin", appTools.isNeedlogin());

			jsonObj.put("status", appTools.getStatus());

			jsonObj.put("hdktVersion", appTools.getHdktVersion());
			jsonObj.put("status", appTools.getStatus());
			jsonObj.put("isParent", appTools.getIsParent() == 0);
			jsonArray.add(jsonObj);
			modularMap.put(appTools.getId(), JSONObject.fromObject(appTools));
		}
		JSONObject resultObj = new JSONObject();
		resultObj.put("zNodes", jsonArray);
		resultObj.put("modularMap", modularMap);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write(resultObj.toString());
	}

}
