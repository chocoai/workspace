/**
 * 
 */
package com.whty.assis.devicemanage.controller;

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
import com.whty.assis.devicemanage.model.AppNetBlacklist;
import com.whty.assis.devicemanage.service.AppNetBlacklistService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 应用网络黑名单
 * 
 * @author zhangzheng
 * @date 2018年6月14日
 */
@Controller
@RequestMapping("/manage/appNetBlackList")
public class AppNetBlacklistController extends BaseController {

	@Autowired
	private AppNetBlacklistService appNetBlacklistService;

	@RequestMapping(value = "/updateAppNetBlackList")
	public void updateAppNetBlackList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String result = appNetBlacklistService.updateAppNetBlacklist(request);
		printText(response, result);
	}

	@RequestMapping(value = "/detailAppNetBlackList")
	public void detailAppNetBlackList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		AppNetBlacklist bean = appNetBlacklistService.detailAppNetBlacklist(request);
		printJson(response, bean);
	}

	@RequestMapping(value = "/deleteAppNetBlackList")
	public void deleteAppNetBlackList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String id = request.getParameter("id");
		String result = appNetBlacklistService.deleteAppNetBlacklist(Integer.valueOf(id));
		printText(response, result);
	}

	@RequestMapping(value = "/saveAppNetBlackList")
	public void saveAppNetBlackList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String result = appNetBlacklistService.saveAppNetBlacklist(request);
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

		HandlerResult handlerResult = appNetBlacklistService.queryAppNetBlacklistPage(paramMap);

		page.setPagination(true);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "appNetBlacklist.list";
	}

}
