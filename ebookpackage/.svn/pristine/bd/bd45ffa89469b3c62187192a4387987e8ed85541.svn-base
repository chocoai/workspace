package com.whty.ebp.manage.controller;

import java.util.ArrayList;
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

import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.NetWorkBlackList;
import com.whty.ebp.manage.service.NetWorkBlackListService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;


/**
 * 网络黑名单列表
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping(value = "/manage/netWorkBlackList")
public class NetWorkBlackListController extends BaseController{

	@Autowired
	private NetWorkBlackListService netWorkBlackListService;

	@RequestMapping(value = "/del")
	public void del(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");

		netWorkBlackListService.delete(id);

		printText(response, "success");

	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public void updateStatus(@ModelAttribute("netWorkBlackList") NetWorkBlackList netWorkBlackList, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		netWorkBlackList.setUpdateTime(new Date());
		netWorkBlackListService.updateStatus(netWorkBlackList);

		printText(response, "success");
	}
	
	
	@RequestMapping(value = "/netWorkBlackListPlatformInfo")
	public void netWorkBlackListPlatformInfo(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String netWorkBlackListId = request.getParameter("id");
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("netWorkBlackListId", netWorkBlackListId);

		List<Map<String, Object>> resultList = netWorkBlackListService.queryPlatformCodeByNetWorkBlackListId(params);
		
		if(resultList ==null)
			resultList = new ArrayList<Map<String, Object>>();
		
		Map resultMap = new HashMap();
		resultMap.put("list", resultList);

		printJson(response, resultMap);

	}
	
	
	@RequestMapping(value = "/queryNetWrokBlackListFlatMode")
	public void queryNetWrokBlackListFlatMode(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String netWorkBlackListId = request.getParameter("id");
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("netWorkBlackListId", netWorkBlackListId);

		List<Map<String, Object>> resultList = netWorkBlackListService.queryFlatModelByNetWorkBlackListId(params);
		Map resultMap = new HashMap();
		resultMap.put("list", resultList);

		printJson(response, resultMap);

	}

	@RequestMapping(value = "/queryNetWrokBlackListPlatformCode")
	public void queryNetWrokBlackListPlatformCode(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String netWorkBlackListId = request.getParameter("netWorkBlackListId");
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("netWorkBlackListId", netWorkBlackListId);

		List<Map<String, Object>> resultList = netWorkBlackListService.queryPlatformCodeByNetWorkBlackListId(params);
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
			printJson(response, netWorkBlackListService.loadById(id));
		} else {
			printText(response, "error");
		}
	}

	/**
	 *
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
		HandlerResult handlerResult = netWorkBlackListService.queryNetWorkBlackListPage(paramMap, page);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "netWorkBlackList/list";
	}

	/**
	 * 创建版本
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(@ModelAttribute("netWorkBlackList") NetWorkBlackList netWorkBlackList, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("modelCode", flatModel.getModelCode());
//
//		List<FlatModel> lst = flatModelService.listByCondition(param);
//
//		if (lst == null || lst.size() != 0) {
//			printText(response, "fail");
//			return;
//		}

//		netWorkBlackList.setId(GUIDGenerator.getGUID());
//		netWorkBlackList.setCreateTime(new Date());
		netWorkBlackListService.save(netWorkBlackList);
		printText(response, "success");
	}

	/**
	 * 更新版本
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public void update(@ModelAttribute("netWorkBlackList") NetWorkBlackList netWorkBlackList, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		netWorkBlackList.setUpdateTime(new Date());

		netWorkBlackListService.update(netWorkBlackList);
		printText(response, "success");
	}

	
	
	
	
}
