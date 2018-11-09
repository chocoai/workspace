package com.whty.assis.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.service.InstallLogService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 运维安装log
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/installLog")
public class InstallLogController extends BaseController {

	@Autowired
	private InstallLogService installLogService;

	@RequestMapping(value = "export")
	public void export(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HSSFWorkbook wb = installLogService.exportExcel(request, response);

		ServletOutputStream os = null;

		String filename = "运营统计";
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		if (request.getParameter("startTime") != null && StringUtils.isNotEmpty(request.getParameter("startTime"))) {
			paramMap.put("startTime", request.getParameter("startTime"));
			model.addAttribute("startTime", request.getParameter("startTime"));

		}

		if (request.getParameter("endTime") != null && StringUtils.isNotEmpty(request.getParameter("endTime"))) {
			paramMap.put("endTime", request.getParameter("endTime"));
			model.addAttribute("endTime", request.getParameter("endTime"));
		}

		if (request.getParameter("area") != null && StringUtils.isNotEmpty(request.getParameter("area"))) {
			paramMap.put("area", request.getParameter("area"));
			model.addAttribute("area", request.getParameter("area"));
		}

		if (request.getParameter("school") != null && StringUtils.isNotEmpty(request.getParameter("school"))) {
			paramMap.put("school", request.getParameter("school"));
			model.addAttribute("school", request.getParameter("school"));
		}

		if (request.getParameter("userName") != null && StringUtils.isNotEmpty(request.getParameter("userName"))) {
			paramMap.put("userName", request.getParameter("userName"));
			model.addAttribute("userName", request.getParameter("userName"));
		}

		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
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
		HandlerResult preHandlerResult = installLogService.queryInstallLog(paramMap);
		page.setPagination(false);
		model.addAttribute("page", page);

		model.addAttribute("installLogList", preHandlerResult.getResultList());
		return "installLog/list";
	}

}
