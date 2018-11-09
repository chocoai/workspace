/**
 * @Title:   SysResController.java 
 * @Package: com.yhcrt.healthcloud.system.controller  
 * @Description: 权限资源管理
 * @author: rpf
 * @date: 2017年5月15日 
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
import com.yhcrt.healthcloud.system.entity.SysRes;
import com.yhcrt.healthcloud.system.service.SysResService;
import com.yhcrt.healthcloud.util.DateUtil;

/**
 * @ClassName: SysResController
 * @Description:权限资源管理
 * @version V1.0
 * @author rpf
 * @date: 2017年5月15日
 */
@Controller
@RequestMapping("/res")
public class SysResController {

	@Autowired
	private SysResService sysResService;

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
		String parentResId = request.getParameter("parentId");
		if (StringUtils.isBlank(parentResId)) {
			SysRes rootRes = sysResService.getSysResRootNode();
			parentResId = rootRes.getResId().toString();
		}
		List<SysRes> resList = sysResService.getChildResByParentId(parentResId);
		modelMap.put("resList", resList);
		modelMap.put("parentResId", parentResId);
		return "res/list";
	}
	
	/**
	 * 根据父节点ID查询所有子节点资源
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getChildRes", method = RequestMethod.GET)
	public String getChildRes(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentResId = request.getParameter("parentId");
		if (StringUtils.isBlank(parentResId)) {
			SysRes rootRes = sysResService.getSysResRootNode();
			parentResId = rootRes.getResId().toString();
		}
		List<SysRes> resList = sysResService.getChildResByParentId(parentResId);
		modelMap.put("resList", resList);
		modelMap.put("parentResId", parentResId);
		return "res/res_list";
	}

	/**
	 * 添加资源预处理
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentResId = request.getParameter("parentId");
		SysRes parentRes;
		if (StringUtils.isNotBlank(parentResId)) {
			parentRes = sysResService.getSysResByResId(Integer.valueOf(parentResId));
			parentRes = parentRes == null ? sysResService.getSysResRootNode() : parentRes;
		} else {
			parentRes = sysResService.getSysResRootNode();
		}
		modelMap.put("res", new SysRes());
		modelMap.put("parentRes", parentRes);
		return "res/add";
	}

	/**
	 * 新增、修改资源
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param res
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, SysRes res,
			RedirectAttributes attr) {
		String resId = request.getParameter("resId");
		if (StringUtils.isNotBlank(resId)) {
			SysRes sysRes = sysResService.getSysResByResId(Integer.valueOf(resId));
			BeanUtils.copyProperties(res, sysRes, "createTime", "createUser", "status", "resCode");
			sysResService.updateByPrimaryKey(sysRes);
		} else {
			res.setCreateTime(DateUtil.getDateTime());
			res.setStatus(Constants.STATUS_NORMAL);
			String resCode;
			// 查询当前的资源编码
			SysRes parentRes = sysResService.getSysResByResId(Integer.valueOf(res.getParentResId()));
			String lastResCode = sysResService.getLastResCodeByParentId(res.getParentResId());
			if (StringUtils.isNotBlank(lastResCode)) {
				resCode = (Integer.parseInt(lastResCode) + 1) + "";
			} else {
				resCode = parentRes.getResCode() + sysResService.getSysResRootNode().getResCode();
			}
			res.setResCode(resCode);
			sysResService.insert(res);
		}
		attr.addAttribute("parentId", res.getParentResId());
		return "redirect:list";
	}

	/**
	 * 删除资源
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
		String resId = request.getParameter("resId");
		String parentId = "";
		if (StringUtils.isNotBlank(resId)) {
			SysRes res = sysResService.getSysResByResId(Integer.valueOf(resId));
			parentId = res != null ? res.getParentResId() : parentId;
			// 物理删除
			// sysResService.deleteByPrimaryKey(Integer.valueOf(resId));
			// 逻辑删除，更新数据状态标识位，不删除数据库中的记录
			res.setStatus(Constants.STATUS_DISABLE);
			sysResService.updateByPrimaryKey(res);
			
		}
		attr.addAttribute("parentId", parentId);
		return "redirect:list";
	}

	/**
	 * 编辑资源预处理
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String resId = request.getParameter("resId");
		if (StringUtils.isNotBlank(resId)) {
			SysRes res = sysResService.getSysResByResId(Integer.valueOf(resId));
			modelMap.put("res", res);
			modelMap.put("parentRes", res.getParentSysRes());
		}
		return "res/add";
	}

	/**
	 * 查询资源树状图
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping(value = "/getResTree", method = RequestMethod.POST)
	public void getResTree(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try {
			List<SysRes> resList = sysResService.listAllSysRes();
			JSONArray jsonArray = new JSONArray();
			for (SysRes res : resList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", res.getResId());
				jsonObj.put("name", res.getResName());
				jsonObj.put("pId", res.getParentResId());
				
				jsonArray.add(jsonObj);
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
