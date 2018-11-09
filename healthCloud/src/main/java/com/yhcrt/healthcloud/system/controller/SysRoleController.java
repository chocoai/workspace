/**
 * @Title:   SysRoleController.java 
 * @Package: com.yhcrt.healthcloud.system.controller  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月26日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.system.controller;

import java.util.Arrays;
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
import com.yhcrt.healthcloud.system.entity.ResRole;
import com.yhcrt.healthcloud.system.entity.SysRes;
import com.yhcrt.healthcloud.system.entity.SysRole;
import com.yhcrt.healthcloud.system.service.ResRoleService;
import com.yhcrt.healthcloud.system.service.SysResService;
import com.yhcrt.healthcloud.system.service.SysRoleService;
import com.yhcrt.healthcloud.util.DateUtil;

/**
 * @ClassName: SysRoleController
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年5月26日
 */

@Controller
@RequestMapping("/role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysResService sysResService;
	@Autowired
	private ResRoleService resRoleService;

	/**
	 * 查询所有的角色
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<SysRole> roleList = sysRoleService.listAllSysRole();
		modelMap.put("roleList", roleList);
		return "role/list";
	}

	/**
	 * 添加角色预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<SysRole> roleList = sysRoleService.listAllSysRole();
		modelMap.put("roleList", roleList);
		return "role/add";
	}

	/**
	 * 添加、修改角色
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, SysRole role,
			Integer[] childIds) {
		if (childIds != null) {
			String ids = Arrays.toString(childIds);
			ids = ids.substring(1, ids.length() - 1);
			if (StringUtils.isBlank(ids)) {
				ids = null;
			}
			role.setIsChild(ids);
		}
		String roleId = request.getParameter("roleId");
		if (StringUtils.isNotBlank(roleId)) {
			SysRole sysRole = sysRoleService.getSysRoleByRoleId(Integer.valueOf(roleId));
			BeanUtils.copyProperties(role, sysRole, "createTime", "createUser", "status");
			sysRoleService.updateByRoleId(sysRole);
		} else {
			role.setCreateTime(DateUtil.getDateTime());
			role.setStatus(Constants.STATUS_NORMAL);
			sysRoleService.insert(role);
		}
		return "redirect:list";
	}

	/**
	 * 修改角色预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String roleId = request.getParameter("roleId");
		SysRole role = new SysRole();
		if (StringUtils.isNotBlank(roleId)) {
			role = sysRoleService.getSysRoleByRoleId(Integer.valueOf(roleId));
			modelMap.put("role", role);
		}
		List<SysRole> roleList = sysRoleService.listAllSysRole();
		if (roleList != null && roleList.size() > 0) {
			for (SysRole sysRole : roleList) {
				if (StringUtils.isNotBlank(role.getIsChild())) {
					String[] ids = role.getIsChild().split(",");
					for (String id : ids) {
						if (Integer.parseInt(id.trim()) == sysRole.getRoleId()) {
							sysRole.setStatus(999); // 表示已经勾选
						}
					}
				}
			}
		}
		modelMap.put("roleList", roleList);
		return "role/add";
	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RedirectAttributes attr) {
		String roleId = request.getParameter("roleId");
		if (StringUtils.isNotBlank(roleId)) {
			// 物理删除
			// sysRoleService.deleteByRoleId(Integer.valueOf(roleId));
			// 逻辑删除，更新数据状态标识位，不删除数据库中的记录
			SysRole role = sysRoleService.getSysRoleByRoleId(Integer.valueOf(roleId));
			role.setStatus(Constants.STATUS_DISABLE);
			sysRoleService.updateByRoleId(role);
		}
		return "redirect:list";
	}

	@RequestMapping(value = "/authorization", method = RequestMethod.GET)
	public String authorizationPre(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String roleId = request.getParameter("roleId");
		if (StringUtils.isNotBlank(roleId)) {
			List<ResRole> resRoles = resRoleService.ListByRoleId(Integer.valueOf(roleId));
			List<SysRes> resList = sysResService.listAllSysRes();
			JSONArray jsonArray = new JSONArray();
			for (SysRes res : resList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", res.getResId());
				jsonObj.put("name", res.getResName());
				jsonObj.put("pId", res.getParentResId());
				for (ResRole resRole : resRoles) {
					if (resRole.getResId().intValue() == res.getResId().intValue()) {
						jsonObj.put("checked", true);
						break;
					}
				}
				jsonArray.add(jsonObj);
			}
			modelMap.put("roleId", roleId);
			modelMap.put("resTree", jsonArray);
		}

		return "role/authorization";
	}

	@RequestMapping(value = "/authorization", method = RequestMethod.POST)
	public String authorization(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String roleId = request.getParameter("roleId");
		String resId = request.getParameter("resIds");
		String[] resIds = resId.split(",");
		resRoleService.insert(roleId, resIds);
		return "redirect:list";
	}

}
