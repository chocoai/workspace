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
import com.whty.assis.devicemanage.model.UrlBlackListRule;
import com.whty.assis.devicemanage.service.UrlBlackListRuleService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 网络黑名单规则
 * 
 * @author zhangzheng
 * @date 2018年4月21日
 */
@Controller
@RequestMapping("/manage/urlBlackListRule")
public class UrlBlackListRuleController extends BaseController {

	@Autowired
	private UrlBlackListRuleService urlBlackListRuleService;

	
	@RequestMapping(value = "/updateUrlBlackList")
	public void updateUrlBlackList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		
		String result  = urlBlackListRuleService.updateUrlBlackList(request);
		printText(response, result);
	}
	
	@RequestMapping(value = "/detailUrlBlackList")
	public void detailUrlBlackList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		
		UrlBlackListRule bean  = urlBlackListRuleService.detailUrlBlackList(request);
		printJson(response, bean);
	}
	
	@RequestMapping(value = "/deleteUrlBlackList")
	public void deleteUrlBlackList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String id = request.getParameter("id");
		String result = urlBlackListRuleService.deleteUrlBlackListRule(Integer.valueOf(id));
		printText(response, result);
	}
	
	@RequestMapping(value = "/saveUrlBlackList")
	public void saveUrlBlackList(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String result = urlBlackListRuleService.saveUrlBlackListRule(request);
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

		HandlerResult handlerResult = urlBlackListRuleService.queryUrlBlackListRulePage(paramMap);

		page.setPagination(true);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "urlBlackListRule.list";
	}

}
