/**
 * 
 */
package com.whty.assis.devicemanage.controller;

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
import com.whty.assis.devicemanage.model.AppWhitelistRule;
import com.whty.assis.devicemanage.service.AppWhitelistRuleService;
import com.whty.assis.mall.model.AppInfo;
import com.whty.assis.mall.service.AppInfoService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 应用白名单规则
 * @author zhangzheng
 * @date   2018年5月9日
 */
@Controller
@RequestMapping("/manage/appWhitelistRule")
public class AppWhitelistRuleController extends BaseController{

	
	@Autowired
	private AppWhitelistRuleService appWhitelistRuleService;
	
	@Autowired
	private AppInfoService appInfoService;
	
	
	@RequestMapping(value = "/findAppList")
	public void findAppList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String appName = request.getParameter("appName");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("appName", appName);
		List<AppInfo> appInfoList = appInfoService.listByParam(param);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", appInfoList);
		
		printJson(response, resultMap);
	}
	
	@RequestMapping(value = "/updateAppWhitelistRule")
	public void updateAppWhitelistRule(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String result  = appWhitelistRuleService.updateAppWhitelistRule(request);
		printText(response, result);
	}
	
	@RequestMapping(value = "/detailAppWhitelistRule")
	public void detailAppWhitelistRule(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		
		AppWhitelistRule bean  = appWhitelistRuleService.detailAppWhitelistRule(request);
		printJson(response, bean);
	}
	
	@RequestMapping(value = "/deleteAppWhitelistRule")
	public void deleteAppWhitelistRule(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String id = request.getParameter("id");
		String result = appWhitelistRuleService.deleteAppWhitelistRule(Integer.valueOf(id));
		printText(response, result);
	}
	
	@RequestMapping(value = "/saveAppWhitelistRule")
	public void saveAppWhitelistRule(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String result = appWhitelistRuleService.saveAppWhitelistRule(request);
		printText(response, result);
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");// 区域id

		if (terminalDeviceTypeId != null) {
			paramMap.put("terminalDeviceTypeId", terminalDeviceTypeId);
		}

		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		PageContext page = PageContext.getContext();

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
		page.setPagination(false);

		HandlerResult handlerResult = appWhitelistRuleService.queryAppWhitelistRulePage(paramMap);

		page.setPagination(true);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "appWhitelistRule.list";
	}
}
