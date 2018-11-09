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
import com.whty.ebp.manage.model.FlatModel;
import com.whty.ebp.manage.service.FlatModelService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 平板型号管理
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping(value = "/manage/flatModel")
public class FlatModelController extends BaseController {

	@Autowired
	private FlatModelService flatModelService;

	@RequestMapping(value = "getAllFlatModel")
	public void getAllFlatModel(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<FlatModel> beanlst = flatModelService.listByCondition(new HashMap<String, Object>());
		Map resultMap = new HashMap();
		resultMap.put("list", beanlst);
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/del")
	public void del(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");

		flatModelService.delete(id);

		printText(response, "success");

	}

	@RequestMapping(value = "/queryFlatModeCode")
	public void queryFlatModelCode(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ebpAppId = request.getParameter("ebpAppId");
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("ebpAppId", ebpAppId);

		List<Map<String, Object>> resultList = flatModelService.queryFlatModelByAppId(params);
		Map resultMap = new HashMap();
		resultMap.put("list", resultList);

		printJson(response, resultMap);

	}

	/**
	 * 查看详情
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public void detail(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			printJson(response, flatModelService.queryById(id));
		} else {
			printText(response, "error");
		}
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
		HandlerResult handlerResult = flatModelService.queryFlatModelPage(paramMap, page);
		model.addAttribute("modelList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "flatModel/list";
	}

	/**
	 * 创建版本
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(@ModelAttribute("flatModel") FlatModel flatModel, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("modelCode", flatModel.getModelCode());

		List<FlatModel> lst = flatModelService.listByCondition(param);

		if (lst == null || lst.size() != 0) {
			printText(response, "fail");
			return;
		}

		flatModel.setId(GUIDGenerator.getGUID());
		flatModel.setCreateTime(new Date());
		flatModelService.save(flatModel);
		printText(response, "success");
	}

	/**
	 * 更新版本
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public void update(@ModelAttribute("flatModel") FlatModel flatModel, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		flatModel.setUpdateTime(new Date());

		flatModelService.update(flatModel);
		printText(response, "success");
	}

}
