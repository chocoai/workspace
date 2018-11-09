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
import com.whty.assis.basicdata.dao.DbViewDao;
import com.whty.assis.basicdata.model.DbView;
import com.whty.assis.devicemanage.model.UseTimeRule;
import com.whty.assis.devicemanage.service.UseTimeRuleService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 使用时段
 * 
 * @author zhangzheng
 * @date 2018年4月21日
 */
@Controller
@RequestMapping("/manage/useTimeRule")
public class UseTimeRuleController extends BaseController {

	@Autowired
	private UseTimeRuleService useTimeRuleService;

	@Autowired
	private DbViewDao dbViewDao;

	@RequestMapping(value = "/updateUseTimeRule")
	public void updateUseTimeRule(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String result = useTimeRuleService.updateUseTimeRule(request);
		printText(response, result);
	}

	@RequestMapping(value = "/detailUseTimeRule")
	public void detailUseTimeRule(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		UseTimeRule bean = useTimeRuleService.detailUseTimeRule(request);
		printJson(response, bean);
	}

	@RequestMapping(value = "/deleteUseTimeRule")
	public void deleteUseTimeRule(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String id = request.getParameter("id");
		String result = useTimeRuleService.deleteUseTimeRule(Integer.valueOf(id));
		printText(response, result);
	}

	@RequestMapping(value = "/saveUseTimeRule")
	public void saveUseTimeRule(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String result = useTimeRuleService.saveUseTimeRule(request);
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

		HandlerResult handlerResult = useTimeRuleService.queryUseTimeRulePage(paramMap);

		List<DbView> useTimeTypeList = dbViewDao.listUseTimeType();

		page.setPagination(true);
		model.addAttribute("list", handlerResult.getResultList());

		model.addAttribute("useTimeTypeList", useTimeTypeList);
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "useTimeRule.list";
	}

}
