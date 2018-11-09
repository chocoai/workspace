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

import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.PlatformInfo;
import com.whty.ebp.manage.service.PlatformInfoService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Controller
@RequestMapping("/manage/platformInfo")
public class PlatformInfoController extends BaseController {

	@Autowired
	private PlatformInfoService platformInfoService;

	@RequestMapping(value = "/del")
	public void del(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		platformInfoService.delete(id);
		printText(response, "success");
	}

	
	@RequestMapping(value="whiteListPlatformInfo")
	public void whiteListPlatformInfo(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("appWhiteListId", id);
		
		List<Map<String,Object>> map=  platformInfoService.queryPlatformInfoByAppWhiteListId(param);
		Map resultMap = new HashMap();
		resultMap.put("list", map);
		printJson(response, resultMap);
	}
	
	
	@RequestMapping(value="derivativeEbpAppPlatformInfo")
	public void derivativeEbpAppPlatformInfo(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("derivativeEbpAppId", id);
		
		List<Map<String,Object>> map=  platformInfoService.queryPlatformInfoByDerivativeEbpAppId(param);
		Map resultMap = new HashMap();
		resultMap.put("list", map);
		printJson(response, resultMap);
	}
	
	@RequestMapping(value="ebpAppPlatformInfo")
	public void ebpAppPlatformInfo(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("ebpAppId", id);
		
		List<Map<String,Object>> map=  platformInfoService.queryPlatformInfoByAppId(param);
		Map resultMap = new HashMap();
		resultMap.put("list", map);
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
			printJson(response, platformInfoService.loadById(id));
		} else {
			printText(response, "error");
		}
	}

	@RequestMapping(value = "/queryAllPlatformInfo")
	public void queryAllPlatformInfo(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		List<PlatformInfo>  list =  platformInfoService.listByCondition(new HashMap<String,Object>());
		
		Map resultMap = new HashMap();
		resultMap.put("list", list);
		printJson(response, resultMap);
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
		HandlerResult handlerResult = platformInfoService.queryPlatformInfo(paramMap, page);
		model.addAttribute("platformInfoList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "platformInfo/list";
	}

	/**
	 * 创建版本
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(@ModelAttribute("platformInfo") PlatformInfo platformInfo, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if (platformInfoService.loadById(platformInfo.getPlatformCode()) != null) {
			printText(response, "平台编码不能重复!");
		} else {
			platformInfoService.save(platformInfo);
			printText(response, "success");
		}
	}

	/**
	 * 更新版本
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public void update(@ModelAttribute("platformInfo") PlatformInfo platformInfo, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		platformInfo.setUpdateTime(new Date());

		platformInfoService.update(platformInfo);
		printText(response, "success");
	}

}
