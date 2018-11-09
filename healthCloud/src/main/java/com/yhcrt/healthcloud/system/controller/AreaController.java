
/**
 * @Title:   AreaController.java 
 * @Package: com.yhcrt.healthcloud.system.controller  
 * @Description: 权限资源管理
 * @author: rpf
 * @date: 2017年9月21日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */

package com.yhcrt.healthcloud.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.entity.Area;
import com.yhcrt.healthcloud.system.service.AreaService;
import com.yhcrt.healthcloud.system.service.SysSequenceService;
import com.yhcrt.healthcloud.util.DateUtil;

/**
 * @ClassName: AreaController
 * @Description:区域管理
 * @version V1.0
 * @author rpf
 * @date: 2017年9月21日
 */
@Controller
@RequestMapping("/area")
public class AreaController {

	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private AreaService areaService;

	/**
	 * 查看资源列表
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentAreaId = request.getParameter("parentId");
		if (StringUtils.isBlank(parentAreaId)) {
			Area rootArea = areaService.getAreaRootNode();
			parentAreaId = rootArea.getAreaId().toString();
		}
		modelMap.put("parentAreaId", parentAreaId);
		return "area/list";
	}

	/**
	 * 根据父节点ID查询所有子节点资源
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getChildArea", method = RequestMethod.GET)
	public String getChildRes(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentAreaId = request.getParameter("parentId");
		if (StringUtils.isBlank(parentAreaId)) {
			Area rootArea = areaService.getAreaRootNode();
			parentAreaId = rootArea.getAreaId().toString();
		}
		List<Area> areas = areaService.getChildAreaByParentId(Integer.parseInt(parentAreaId));
		modelMap.put("areas", areas);
		modelMap.put("parentAreaId", parentAreaId);
		return "area/area_list";
	}

	/**
	 * 获取区域树状图
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping(value = "/getAreaTree", method = RequestMethod.POST)
	public void getResTree(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try {
			List<Area> areas = areaService.getAllArea();
			JSONArray jsonArray = new JSONArray();
			for (Area area : areas) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", area.getAreaId());
				jsonObj.put("name", area.getAreaName());
				jsonObj.put("pId", area.getParentId());
				jsonArray.add(jsonObj);
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加区域
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentAreaId = request.getParameter("parentId");
		Area parentArea;
		if (StringUtils.isNotBlank(parentAreaId)) {
			parentArea = areaService.getAreaById(Integer.parseInt(parentAreaId));
			parentArea = parentArea == null ? areaService.getAreaRootNode() : parentArea;
		} else {
			parentArea = areaService.getAreaRootNode();
		}
		modelMap.put("area", new Area());
		modelMap.put("parentArea", parentArea);
		return "area/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Area area,
			RedirectAttributes attr) {
		String areaId = request.getParameter("areaId");
		if (StringUtils.isNotBlank(areaId)) {
			Area areaBean = areaService.getAreaById(Integer.parseInt(areaId));
			BeanUtils.copyProperties(area, areaBean, "createTime", "status");
			areaBean.setFullName(areaService.getAreaFullName(area.getParentId(), area.getAreaName()));
			areaService.update(areaBean);
		} else {
			area.setAreaId(sysSequenceService.getSequenceValue(Constants.SequenceName.AREA));
			area.setFullName(areaService.getAreaFullName(area.getParentId(), area.getAreaName()));
			area.setCreateTime(DateUtil.getDateTime());
			area.setStatus(Constants.STATUS_NORMAL);
			areaService.insert(area);
		}
		attr.addAttribute("parentId", area.getParentId());
		return "redirect:list";
	}
	
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String areaId = request.getParameter("areaId");
		if (StringUtils.isNotBlank(areaId)) {
			Area area = areaService.getAreaById(Integer.parseInt(areaId));
			modelMap.put("area", area);
			modelMap.put("parentArea", area.getParentArea());
		}
		return "area/add";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RedirectAttributes attr) {
		String areaId = request.getParameter("areaId");
		String parentId = "";
		if (StringUtils.isNotBlank(areaId)) {
			Area area = areaService.getAreaById(Integer.parseInt(areaId));
			parentId = area != null ? area.getParentId().toString() : parentId;
			// 逻辑删除，更新数据状态标识位，不删除数据库中的记录
			area.setStatus(Constants.STATUS_DISABLE);
			areaService.update(area);
		}
		attr.addAttribute("parentId", parentId);
		return "redirect:list";
	}
	
	/**
	 * 查询区域树
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/showAreaTree", method = RequestMethod.GET)
	public String showOrgTree(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try {
			String areaId = request.getParameter("areaId");
			if (StringUtils.isBlank(areaId)) {
				areaId = areaService.getAreaRootNode().getAreaId().toString();
			}
			List<Area> areas = areaService.getAllArea();
			JSONArray jsonArray = new JSONArray();
			for (Area area : areas) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", area.getAreaId());
				jsonObj.put("name", area.getFullName());
				jsonObj.put("pId", area.getParentId());
				jsonArray.add(jsonObj);
			}
			modelMap.put("areaTree", jsonArray);
			modelMap.put("areaId", areaId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "area/area_tree";
	}

}
