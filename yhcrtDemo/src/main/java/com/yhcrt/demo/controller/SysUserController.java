package com.yhcrt.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.demo.model.SysUser;
import com.yhcrt.demo.service.SysUserService;
import com.yhcrt.demo.util.Constant;
import com.yhcrt.demo.util.ExtJSBaseParameter;
import com.yhcrt.demo.util.Item;
import com.yhcrt.demo.util.ListView;
import com.yhcrt.demo.util.MD5;
import com.yhcrt.demo.util.QueryResult;
import com.yhcrt.demo.util.SystemCache;


/**
 * @author fengkun
 * @email 231788364@qq.com
 */
@Controller
@RequestMapping("/sys/sysuser")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;

	@RequestMapping("/login")
	@ResponseBody
	public String login(SysUser sysUserModel, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		SysUser sysUser = sysUserService.getByUserName(sysUserModel.getUserName());
		if (sysUser == null || sysUser.getRole() == 0) { // 用户名有误或已被禁用
			result.put("result", -1);
			return JSONObject.toJSONString(result);
		}
		if (!sysUser.getPassword().equals(MD5.crypt(sysUserModel.getPassword()))) { // 密码错误
			result.put("result", -2);
			return JSONObject.toJSONString(result);
		}
		sysUser.setLastLoginTime(new Date());
		sysUserService.update(sysUser);
		request.getSession().setAttribute(Constant.SESSION_SYS_USER, sysUser);
		result.put("result", 1);
		return JSONObject.toJSONString(result);
	}

	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getSession().getAttribute(Constant.SESSION_SYS_USER) == null) {
			return "";
		} else {
			return "index";
		}
	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute(Constant.SESSION_SYS_USER);
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath+"/login.jsp");
	}

	@RequestMapping("/resetPassword")
	@ResponseBody
	public String resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName = request.getParameter("userName");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		Map<String, Object> result = new HashMap<String, Object>();
		SysUser sysUser = sysUserService.getByUserName(userName);
		if (!sysUser.getPassword().equals(MD5.crypt(oldPassword))) {
			result.put("result", -2);
			return JSONObject.toJSONString(result);
		}
		result.put("result", 1);
		sysUser.setPassword(MD5.crypt(newPassword));
		sysUserService.update(sysUser);
		return JSONObject.toJSONString(result);
	}

	@RequestMapping("/externalVerifySysUser")
	public String externalVerifySysUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SysUser sysUser = sysUserService.getByUserNameAndPassword( username, MD5.crypt(password));
		if (null == sysUser) {
			return "{success:false}";
		} else {
			return "{success:true}";
		}
	}

	@RequestMapping(value = "/getRoleName",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getRoleName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONArray jsonArray = new JSONArray();
		for (Map.Entry<String, Item> roleMap : SystemCache.DICTIONARY.get("SYSUSER_ROLE").getItems().entrySet()) {
			Item item = roleMap.getValue();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ItemText", item.getValue());
			jsonObject.put("ItemValue", item.getKey());
			jsonArray.add(jsonObject);
		}
		JSONObject resultJSONObject = new JSONObject();
		resultJSONObject.put("list", jsonArray);
		return JSONObject.toJSONString(resultJSONObject);
	}

	@RequestMapping(value = "/saveSysUser", method = { RequestMethod.POST, RequestMethod.GET },produces = "application/json;charset=utf-8")
	@ResponseBody
	public String doSave(SysUser entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		SysUser checkuserName = sysUserService.getByUserName(entity.getUserName());
		if (null != checkuserName && null == entity.getId()) {
			parameter.setSuccess(false);
		} else {
			if (Constant.CMD_EDIT.equals(parameter.getCmd())) {
				entity.setLastLoginTime(checkuserName.getLastLoginTime());
				sysUserService.updateBySelected(entity);
			} else if (Constant.CMD_NEW.equals(parameter.getCmd())) {
				entity.setPassword(MD5.crypt(entity.getPassword()));
				sysUserService.insert(entity);
			}
			parameter.setCmd(Constant.CMD_EDIT);
			parameter.setSuccess(true);
		}
		return JSONObject.toJSONString(parameter);
	}
	
	@RequestMapping(value = "/authorityList", method = { RequestMethod.POST, RequestMethod.GET })
	public String authorityList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "authority/authorityManager";
	}
	
	@RequestMapping(value = "/userList", method = { RequestMethod.POST, RequestMethod.GET })
	public String userList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "user/user";
	}

	@RequestMapping(value = "/getSysUser",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getSysUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		SysUser sysUser = new SysUser();
		String userName = request.getParameter("userName");
		if (StringUtils.isNoneBlank(userName)) {
			sysUser.setUserName(userName);
		}
		String realName = request.getParameter("realName");
		if (StringUtils.isNotBlank(realName)) {
			sysUser.setRealName(realName);
		}
		String role = request.getParameter("role");
		if (StringUtils.isNotBlank(role)) {
			sysUser.setRole(Short.valueOf(role));
		}
		sysUser.setFirstResult(firstResult);
		sysUser.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		sysUser.setSortedConditions(sortedCondition);
		QueryResult<SysUser> queryResult = sysUserService.doPaginationQuery(sysUser);
		List<SysUser> YhcrtList = sysUserService.getSysUserList(queryResult.getResultList());
		ListView<SysUser> YhcrtListView = new ListView<SysUser>();
		YhcrtListView.setData(YhcrtList);
		YhcrtListView.setTotalRecord(queryResult.getTotalCount());
		return JSONObject.toJSONString(YhcrtListView);
	}

	@RequestMapping("/deleteSysUser")
	public String deleteSysUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		if (Arrays.asList(ids).contains(Long.valueOf("1"))) {
			return "{success:false,msg:'删除项包含超级管理员，超级管理员不能删除！'}";
		} else {
			boolean flag = sysUserService.deleteByPK(ids);
			if (flag) {
				return "{success:true}";
			} else {
				return "{success:false}";
			}
		}
	}

	@RequestMapping(value = "/getRoleNameList",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getRoleNameList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysUser> roleNameList = new ArrayList<SysUser>();
		for (Map.Entry<String, Item> roleMap : SystemCache.DICTIONARY.get("SYSUSER_ROLE").getItems().entrySet()) {
			Item item = roleMap.getValue();
			SysUser sysUser = new SysUser();
			sysUser.setRole(Short.valueOf(item.getKey()));
			sysUser.setRoleName(item.getValue());
			roleNameList.add(sysUser);
		}
		ListView<SysUser> roleNameListView = new ListView<SysUser>();
		roleNameListView.setData(roleNameList);
		roleNameListView.setTotalRecord(Long.valueOf(roleNameList.size()));	
		return JSONObject.toJSONString(roleNameListView);
	}
	
	@RequestMapping("/getIndexData")
	@ResponseBody
	public String getIndexData(HttpServletRequest request, HttpServletResponse response) throws IOException {
          JSONObject json = new JSONObject();
          json.put("jrld", 123);
          json.put("jrjt", 98);
          json.put("dbgd", 32);
          json.put("ybgd", 12);
          
          return json.toJSONString();
	}

}
