package com.whty.assis.basicdata.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.model.DictData;
import com.whty.assis.basicdata.model.DictType;
import com.whty.assis.basicdata.service.DictDataService;
import com.whty.assis.basicdata.service.DictTypeService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 字典管理
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/dict")
public class DictController extends BaseController {

	@Autowired
	private DictTypeService dictTypeService;

	@Autowired
	private DictDataService dictDataService;

	/**
	 * 跳转到按钮管理
	 */
	@RequestMapping(value = "/editDictData")
	public void editDictData(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String name = request.getParameter("name");
		Integer id = Integer.valueOf(request.getParameter("id").toString());

		DictData dictData = new DictData();
		dictData.setId(id);
		dictData.setUpdateTime(new Date());
		dictData.setValue(name);

		dictDataService.updateDictData(dictData);
		printText(response, "success");
	}

	/**
	 * 跳转到按钮管理
	 */
	@RequestMapping(value = "/editDictType")
	public void editDictType(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String name = request.getParameter("name");
		Integer id = Integer.valueOf(request.getParameter("id").toString());
		DictType dictType = new DictType();
		dictType.setId(id);
		dictType.setUpdateTime(new Date());
		dictType.setName(name);
		dictTypeService.updateDictType(dictType);
		printText(response, "success");
	}

	/**
	 * 跳转到按钮管理
	 */
	@RequestMapping(value = "/	saveDictData")
	public void saveDictData(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String value = request.getParameter("value");
		String typeId = request.getParameter("dictTypeId");

		DictData dictData = new DictData();
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		
		dictData.setTypeId(Integer.valueOf(typeId));
		dictData.setValue(value);
		dictData.setCreateTime(new Date());
		dictData.setUpdateTime(new Date());
		dictData.setCreator(mUser.getId());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("typeId", typeId);
		List<DictData> dictDatalst = dictDataService.listByCondition(param);

		int maxKey = 0;

		for (DictData bean : dictDatalst) {
			if (bean.getName() > maxKey) {
				maxKey = bean.getName();
			}
		}
		maxKey = maxKey + 1;
		dictData.setName(maxKey);

		dictDataService.saveDictData(dictData);

		printText(response, "success");
	}

	/**
	 * 跳转到按钮管理
	 */
	@RequestMapping(value = "/saveDictType")
	public void saveDictType(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
			String name = request.getParameter("name");
			TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
			DictType dictType = new DictType();
			dictType.setCreator(mUser.getId());
			dictType.setName(name);
			dictType.setCreateTime(new Date());
			dictType.setUpdateTime(new Date());
			dictTypeService.saveDictType(dictType);

			printText(response, "success");
	}

	/**
	 * 跳转到按钮管理
	 */
	@RequestMapping(value = "/dictTypelist")
	public String dictTypelist(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("school_name", request.getParameter("school_name"));
		paramMap.put("status", "0");

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
		page.setPagination(true);
		HandlerResult handlerResult = dictTypeService.queryDictTypePage(paramMap);

		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "dict.typeList";
	}

	/**
	 * 跳转到按钮管理
	 */
	@RequestMapping(value = "/dictDatalist")
	public String dictDatalist(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String dictTypeId = request.getParameter("dictTypeId");

		paramMap.put("typeId", dictTypeId);

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

		page.setPagination(true);
		HandlerResult handlerResult = dictDataService.queryDictDataPage(paramMap);

		model.addAttribute("typeId", dictTypeId);

		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "dict.dataList";
	}

}
