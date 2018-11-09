package com.whty.ebp.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.util.GUIDGenerator;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.DerivativeAppLine;
import com.whty.ebp.manage.model.FlatModel;
import com.whty.ebp.manage.service.DerivativeAppLineService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 衍生升级线路管理
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping(value = "/manage/derivativeAppLine")
public class DerivativeAppLineController extends BaseController {

	@Autowired
	private DerivativeAppLineService derivativeAppLineSerivce;

	/**
	 * 创建版本
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(@ModelAttribute("flatModel") DerivativeAppLine derivativeAppLine, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		derivativeAppLine.setId(GUIDGenerator.getGUID());
		derivativeAppLine.setCreateTime(new Date());
		derivativeAppLineSerivce.save(derivativeAppLine);

		printText(response, "success");
	}

	/**
	 * 平板型号管理
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("平板型号");
		}
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fileType", request.getParameter("fileType"));
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
		HandlerResult handlerResult = derivativeAppLineSerivce.queryDerivativeAppLinePage(paramMap, page);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "derivativeAppLine/list";
	}

}
