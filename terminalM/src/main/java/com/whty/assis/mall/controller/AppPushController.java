/**
 * 
 */
package com.whty.assis.mall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.mall.service.AppPushService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 引用推送
 * 
 * @author zhangzheng
 * @date 2018年4月18日
 */
@Controller
@RequestMapping(value = "/manage/appPush")
public class AppPushController extends BaseController {

	@Autowired
	private AppPushService appPushService;

	
	/**
	 * 重新推送app
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/rePushApp")
	private void rePushApp(HttpServletRequest request, HttpServletResponse response, Model model) {
		String result = appPushService.rePushApp(request);
		printText(response, result);
	}
	
	
	/**
	 * 删除推送的应用
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/deleteAppPush")
	private void deleteAppPush(HttpServletRequest request, HttpServletResponse response, Model model) {
		String result = appPushService.deleteAppPush(request);
		printText(response, result);
	}
	
	@RequestMapping(value = "/saveAppPush")
	private void saveAppPush(HttpServletRequest request, HttpServletResponse response, Model model) {
		String result = appPushService.saveAppPush(request);
		printText(response, result);
	}

	@RequestMapping(value = "/addPage")
	private String addPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "appPush.addPage";
	}

	@RequestMapping(value = "/list")
	private String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String appName = request.getParameter("appName");

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		if (StringUtils.isNotEmpty(appName)) {
			paramMap.put("appName", appName);
			model.addAttribute("appName", appName);
		}

		if(StringUtils.isNotEmpty(startTime)){
			paramMap.put("pushStartTime", startTime);
			model.addAttribute("startTime", startTime);
		}
		
		if(StringUtils.isNotEmpty(endTime)){
			paramMap.put("pushEndTime", endTime);
			model.addAttribute("endTime", endTime);
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

		HandlerResult pageList = appPushService.queryAppPushPage(paramMap);

		model.addAttribute("list", pageList.getResultList());

		return "appPush.list";
	}
}
