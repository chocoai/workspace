package com.yhcrt.demo.controller;

import java.io.IOException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.demo.model.Authority;
import com.yhcrt.demo.model.RoleAuthority;
import com.yhcrt.demo.service.AuthorityService;
import com.yhcrt.demo.service.RoleAuthorityService;
import com.yhcrt.demo.util.Constant;
import com.yhcrt.demo.util.ExtJSBaseParameter;
import com.yhcrt.demo.util.ListView;
import com.yhcrt.demo.util.QueryResult;



/**
 * @author fengkun
 * @email 231788364@qq.com
 */
@Controller
@RequestMapping("/sys/authority")
public class AuthorityController {

	@Resource
	private AuthorityService authorityService;
	@Resource
	private RoleAuthorityService roleAuthorityService;

	@RequestMapping(value ="/getAuthority",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getAuthority(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Short role = Short.valueOf(request.getParameter("globalRoleId"));
		List<Authority> mainMenuList = authorityService.queryByRole(role);
		List<JSONObject> resultList = new ArrayList<JSONObject>();
		for (int i = 0; i < mainMenuList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", mainMenuList.get(i).getId());
			jsonObject.put("sortOrder", mainMenuList.get(i).getSortOrder());
			jsonObject.put("menuCode", mainMenuList.get(i).getMenuCode());
			jsonObject.put("text", mainMenuList.get(i).getMenuName());
			jsonObject.put("menuConfig", mainMenuList.get(i).getMenuConfig());
			jsonObject.put("buttons", mainMenuList.get(i).getButtons());
			jsonObject.put("expanded", mainMenuList.get(i).getExpanded());
			jsonObject.put("checked", mainMenuList.get(i).getChecked());
			jsonObject.put("leaf", mainMenuList.get(i).getLeaf());
			jsonObject.put("url", mainMenuList.get(i).getUrl());
			jsonObject.put("iconCls", mainMenuList.get(i).getIconCls());
			JSONArray jsonArray = new JSONArray();
			List<Authority> childrenMenuList = authorityService.queryChildrenByParentIdAndRole(mainMenuList.get(i).getId(), role);
			for (int j = 0; j < childrenMenuList.size(); j++) {
				JSONObject childrenJsonObject = new JSONObject();
				String buttons = authorityService.querySurfaceAuthorityList(roleAuthorityService.queryByRole(role), childrenMenuList.get(j).getId(), childrenMenuList.get(j).getButtons());
				childrenJsonObject.put("id", childrenMenuList.get(j).getId());
				childrenJsonObject.put("sortOrder", childrenMenuList.get(j).getSortOrder());
				childrenJsonObject.put("menuCode", childrenMenuList.get(j).getMenuCode());
				childrenJsonObject.put("text", childrenMenuList.get(j).getMenuName());
				childrenJsonObject.put("menuConfig", childrenMenuList.get(j).getMenuConfig());
				childrenJsonObject.put("buttons", buttons);
				childrenJsonObject.put("expanded", childrenMenuList.get(j).getExpanded());
				childrenJsonObject.put("checked", childrenMenuList.get(j).getChecked());
				childrenJsonObject.put("leaf", childrenMenuList.get(j).getLeaf());
				childrenJsonObject.put("url", childrenMenuList.get(j).getUrl());
				childrenJsonObject.put("iconCls", childrenMenuList.get(j).getIconCls());
				jsonArray.add(childrenJsonObject);
			}
			jsonObject.put("children", jsonArray);
			resultList.add(jsonObject);
		}
		return JSONArray.toJSONString(resultList);
	}

	@RequestMapping(value="/getAuthorizationList",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getAuthorizationList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleParam = request.getParameter("role");
		List<String> authorityIdList = new ArrayList<String>();
		if (roleParam != null) {
			List<RoleAuthority> roleAuthorityList = roleAuthorityService.queryByRole(Short.valueOf(roleParam));
			for (RoleAuthority roleAuthority : roleAuthorityList) {
				authorityIdList.add(roleAuthority.getAuthorityId());
			}
		}
		List<Authority> mainMenuList = authorityService.queryByParentId(null);
		List<JSONObject> resultList = new ArrayList<JSONObject>();
		for (int i = 0; i < mainMenuList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", mainMenuList.get(i).getId());
			jsonObject.put("text", mainMenuList.get(i).getMenuName());
			jsonObject.put("expanded", mainMenuList.get(i).getExpanded());
			if (authorityIdList.contains(mainMenuList.get(i).getId().toString())) {
				jsonObject.put("checked", true);
			} else {
				jsonObject.put("checked", false);
			}
			jsonObject.put("leaf", mainMenuList.get(i).getLeaf());
			JSONArray jsonArray = new JSONArray();
			List<Authority> childrenMenuList = authorityService.queryByParentId(mainMenuList.get(i).getId());
			for (int j = 0; j < childrenMenuList.size(); j++) {
				JSONObject childrenJsonObject = new JSONObject();
				childrenJsonObject.put("id", childrenMenuList.get(j).getId());
				childrenJsonObject.put("text", childrenMenuList.get(j).getMenuName());
				childrenJsonObject.put("expanded", childrenMenuList.get(j).getExpanded());
				if (authorityIdList.contains(childrenMenuList.get(j).getId().toString())) {
					childrenJsonObject.put("checked", true);
				} else {
					childrenJsonObject.put("checked", false);
				}

				if (childrenMenuList.get(j).getButtons().length() == 0) {
					childrenJsonObject.put("leaf", true);
				} else {
					childrenJsonObject.put("leaf", false);
				}
				JSONArray buttonJSONArray = new JSONArray();
				String[] buttons = childrenMenuList.get(j).getButtons().split(",");
				for (int z = 0; z < buttons.length; z++) {
					if (StringUtils.isBlank(buttons[z])) {
						continue;
					}
					JSONObject buttonChildrenJSONObject = new JSONObject();
					buttonChildrenJSONObject.put("id", childrenMenuList.get(j).getId() + buttons[z]);
					String buttonText = null;
					if (buttons[z].equalsIgnoreCase("Add")) {
						buttonText = "添加";
					} else if (buttons[z].equalsIgnoreCase("Edit")) {
						buttonText = "修改";
					} else if (buttons[z].equalsIgnoreCase("Delete")) {
						buttonText = "删除";
					} else if (buttons[z].equalsIgnoreCase("View")) {
						buttonText = "查看";
					} else if (buttons[z].equalsIgnoreCase("Import")) {
						buttonText = "导入";
					} else if (buttons[z].equalsIgnoreCase("Query")) {
						buttonText = "查询";
					} else if (buttons[z].equalsIgnoreCase("Disarm")) {
						buttonText = "解除";
					} else if (buttons[z].equalsIgnoreCase("Export")) {
						buttonText = "导出";
					}
					buttonChildrenJSONObject.put("text", buttonText);
					buttonChildrenJSONObject.put("expanded", true);

					if (authorityIdList.contains(childrenMenuList.get(j).getId() + buttons[z])) {
						buttonChildrenJSONObject.put("checked", true);
					} else {
						buttonChildrenJSONObject.put("checked", false);
					}

					buttonChildrenJSONObject.put("leaf", true);
					buttonJSONArray.add(buttonChildrenJSONObject);
				}
				childrenJsonObject.put("children", buttonJSONArray);

				jsonArray.add(childrenJsonObject);
			}
			jsonObject.put("children", jsonArray);
			resultList.add(jsonObject);
		}
		return JSONArray.toJSONString(resultList);
	}

	@RequestMapping(value = "/getAuthorityTreePicker",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getAuthorityTreePicker(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Short role = Short.valueOf(request.getParameter("roleId"));
		List<Authority> mainMenuList = authorityService.queryByRole(role);
		List<JSONObject> resultList = new ArrayList<JSONObject>();
		for (int i = 0; i < mainMenuList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", mainMenuList.get(i).getId());
			jsonObject.put("sortOrder", mainMenuList.get(i).getSortOrder());
			jsonObject.put("menuCode", mainMenuList.get(i).getMenuCode());
			jsonObject.put("text", mainMenuList.get(i).getMenuName());
			jsonObject.put("menuConfig", mainMenuList.get(i).getMenuConfig());
			jsonObject.put("buttons", mainMenuList.get(i).getButtons());
			jsonObject.put("expanded", true);
			jsonObject.put("checked", mainMenuList.get(i).getChecked());
			jsonObject.put("leaf", mainMenuList.get(i).getLeaf());
			jsonObject.put("url", mainMenuList.get(i).getUrl());
			jsonObject.put("iconCls", mainMenuList.get(i).getIconCls());

			JSONArray jsonArray = new JSONArray();
			List<Authority> childrenMenuList = authorityService.queryChildrenByParentIdAndRole(mainMenuList.get(i).getId(), role);
			for (int j = 0; j < childrenMenuList.size(); j++) {
				JSONObject childrenJsonObject = new JSONObject();

				String buttons = authorityService.querySurfaceAuthorityList(roleAuthorityService.queryByRole(role), childrenMenuList.get(j).getId(), childrenMenuList.get(j).getButtons());

				childrenJsonObject.put("id", childrenMenuList.get(j).getId());
				childrenJsonObject.put("sortOrder", childrenMenuList.get(j).getSortOrder());
				childrenJsonObject.put("menuCode", childrenMenuList.get(j).getMenuCode());
				childrenJsonObject.put("text", childrenMenuList.get(j).getMenuName());
				childrenJsonObject.put("menuConfig", childrenMenuList.get(j).getMenuConfig());
				childrenJsonObject.put("buttons", buttons);
				childrenJsonObject.put("expanded", true);
				childrenJsonObject.put("checked", childrenMenuList.get(j).getChecked());
				childrenJsonObject.put("leaf", childrenMenuList.get(j).getLeaf());
				childrenJsonObject.put("url", childrenMenuList.get(j).getUrl());
				childrenJsonObject.put("iconCls", childrenMenuList.get(j).getIconCls());
				jsonArray.add(childrenJsonObject);
			}
			jsonObject.put("children", jsonArray);
			resultList.add(jsonObject);
		}
		return JSONArray.toJSONString(resultList);
	}


	@RequestMapping(value = "/saveAuthority", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String doSave(Authority entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		Authority checkMenuCode = authorityService.getByMenuCode(entity.getMenuCode());
		if (null != checkMenuCode && null == entity.getId()) {
			parameter.setSuccess(false);
		} else {
			if (entity.getChecked() == false) {
				entity.setChecked(null);
			}
			if (Constant.CMD_EDIT.equals(parameter.getCmd())) {
				authorityService.updateBySelected(entity);
			} else if (Constant.CMD_NEW.equals(parameter.getCmd())) {
				authorityService.insert(entity);
			}
			parameter.setCmd(Constant.CMD_EDIT);
			parameter.setSuccess(true);
		}
		return JSONObject.toJSONString(parameter);
	}

	@RequestMapping(value = "/getAuthorityPagination", method = { RequestMethod.POST, RequestMethod.GET },produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getAuthorityPagination(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("start"));
		Integer maxResults = Integer.valueOf(request.getParameter("limit"));
		String sortedObject = null;
		String sortedValue = null;
		ObjectMapper mapper = new ObjectMapper();
		List<LinkedHashMap<String, Object>> sortedList = mapper.readValue(request.getParameter("sort"), List.class);
		for (int i = 0; i < sortedList.size(); i++) {
			Map<String, Object> map = sortedList.get(i);
			sortedObject = (String) map.get("property");
			sortedValue = (String) map.get("direction");
		}
		Authority authority = new Authority();
		authority.setFirstResult(firstResult);
		authority.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		authority.setSortedConditions(sortedCondition);
		QueryResult<Authority> queryResult = authorityService.doPaginationQuery(authority);
		ListView<Authority> authorityListView = new ListView<Authority>();
		authorityListView.setData(queryResult.getResultList());
		authorityListView.setTotalRecord(queryResult.getTotalCount());
		return JSONObject.toJSONString(authorityListView);
	}

	@RequestMapping("/deleteAuthority")
	@ResponseBody
	public String deleteAuthority(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = authorityService.deleteByPK(ids);
		if (flag) {
			return "{success:true}";
		} else {
			return "{success:false}";
		}
	}

}
