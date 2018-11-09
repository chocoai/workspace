package com.whty.ebp.manage.controller;

import java.io.File;
import java.io.IOException;
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

import com.whty.common.util.CommonFunction;
import com.whty.common.util.Plupload;
import com.whty.common.util.PluploadUtil;
import com.whty.common.util.SysConfig;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.ApkInfo;
import com.whty.ebp.manage.model.AppBlackList;
import com.whty.ebp.manage.model.Browser;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.model.WhiteList;
import com.whty.ebp.manage.service.AppNetBlackService;
import com.whty.ebp.manage.service.BrowserService;
import com.whty.ebp.manage.service.WhiteListService;
import com.whty.ebp.manage.utils.ApkUtils;
import com.whty.page.PageContext;
import com.whty.page.request.PageRequest;
import com.whty.page.util.HandlerResult;

@Controller
@RequestMapping(value = "/manage/appNetBlackList")
public class AppNetBlackListController extends BaseController {

	@Autowired
	private AppNetBlackService appNetBlackService;

	@RequestMapping(value = "/appBlackListPlatformInfo")
	public void appBlackListPlatformInfo(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String appBlackListId = request.getParameter("id");
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("appBlackListId", appBlackListId);

		List<Map<String, Object>> resultList = appNetBlackService.queryPlatformCodeByAppBlackListId(params);
		Map resultMap = new HashMap();
		resultMap.put("list", resultList);

		printJson(response, resultMap);

	}
	
	/**
	 * 上传 后添加的方法
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload")
	public void uploadInfo(Plupload plupload, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		plupload.setRequest(request);

		String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("app_upload_dir")
				+ CommonFunction.getDateSampleString(new Date()) + File.separator;
		File file = new File(uploadDir);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}

		response.setCharacterEncoding("UTF-8");

		try {
			// 上传文件
			PluploadUtil.upload(plupload, new File(uploadDir));
			// 判断文件是否上传成功（被分成块的文件是否全部上传完成）
			if (PluploadUtil.isUploadFinish(plupload)) {
				System.out.println(plupload.getName() + "-----------上传完毕");
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ApkInfo apkInfo = new ApkUtils().getApkInfo(uploadDir + plupload.getRename());
		printJson(response, apkInfo);
	}

	/**
	 * 查看详情
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public void detail(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			printJson(response, appNetBlackService.queryById(id));
		} else {
			printText(response, "error");
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(@ModelAttribute("appBlackList") AppBlackList appBlackList, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		appNetBlackService.save(appBlackList);
		printText(response, "success");
	}

	/**
	 * 修改状态
	 */
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public void updateStatus(@ModelAttribute("appBlackList") AppBlackList appBlackList, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		appBlackList.setUpdateTime(new Date());
		appNetBlackService.updateStatus(appBlackList);

		printText(response, "success");
	}
	
	/**
	 * 修改属性
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public void update(@ModelAttribute("appBlackList") AppBlackList appBlackList, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		appBlackList.setUpdateTime(new Date());
		appNetBlackService.update(appBlackList);

		printText(response, "success");
	}

	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		appNetBlackService.delete(id);

		printText(response, "success");
	}

	/**
	 * 标签列表
	 * 
	 * @param request
	 * @param pageRequest
	 * @param model
	 * @param productName
	 * @param productType
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletResponse response, HttpServletRequest request, PageRequest pageRequest, Model model,
			String productName, String productType) {
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
//		String version = appNetBlackService.queryLabelVersion();
		page.setPagination(true);
		HandlerResult handlerResult = appNetBlackService.queryAppNetBlackPage(paramMap, page);
//		model.addAttribute("version", version);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "appNetBlack/list";
	}
}
