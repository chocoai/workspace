package com.whty.assis.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.service.SoftService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 开机信息
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/widget")
public class WidgetController extends BaseController {

	@Autowired
	private SoftService softService;

	/**
	 * 跳转到版本管理
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("版本列表页数据加载");
		}
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("machineInfo", request.getParameter("machineInfo"));
		paramMap.put("widgetName", request.getParameter("widgetName"));
		paramMap.put("startTime", request.getParameter("startTime"));
		paramMap.put("endTime", request.getParameter("endTime"));

		PageContext page = PageContext.getContext();

		// 请自行验证
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
		page.setPagination(true);
		HandlerResult handlerResult = softService.queryWidgetPage(paramMap);
		model.addAttribute("widgetList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "widget/list";
	}
}
