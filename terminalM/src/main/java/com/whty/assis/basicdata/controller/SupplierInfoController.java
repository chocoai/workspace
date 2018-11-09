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
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.dao.DbViewDao;
import com.whty.assis.basicdata.model.Brand;
import com.whty.assis.basicdata.model.DbView;
import com.whty.assis.basicdata.model.SupplierInfo;
import com.whty.assis.basicdata.service.SupplierInfoService;
import com.whty.common.util.ChineseCharToEn;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 供应商管理
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/supplierInfo")
public class SupplierInfoController extends BaseController {

	@Autowired
	private SupplierInfoService supplierInfoService;

	@Autowired
	private DbViewDao dbViewDao;

	@RequestMapping(value = "/querySupplierInfo")
	public void querySupplierInfo(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(name="pageValue",defaultValue="1")Integer page,Model model)
			throws Exception {
		Map<String, Object> paraMap = this.getParameterMap(request);
		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");
		String name = request.getParameter("name");
		String supplierNum = request.getParameter("supplierNum");
		if (terminalDeviceTypeId != null) {
			paraMap.put("terminalDeviceTypeId", terminalDeviceTypeId);
		}
		paraMap.put("name", name);
		paraMap.put("supplierNum", supplierNum);
		PageHelper.startPage(page, 10);
		List<SupplierInfo> supplierInfos =supplierInfoService.listByCondition(paraMap);
		PageInfo<SupplierInfo> p = new PageInfo<SupplierInfo>(supplierInfos);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", supplierInfos);
		resultMap.put("pageNum", p.getPageNum());
		resultMap.put("pages", p.getPages());
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/saveSupplierInfo")
	public void saveSupplierInfo(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		String result = supplierInfoService.saveSupplierInfo(request);
		printText(response, result);
	}

	@RequestMapping(value = "/detailSupplierInfo")
	public void detailSupplierInfo(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String id = request.getParameter("id");
		SupplierInfo bean = supplierInfoService.loadById(Integer.valueOf(id));
		printJson(response, bean);
	}

	@RequestMapping(value = "/updateSupplierInfo")
	public void updateSupplierInfo(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		String contactTel = request.getParameter("contactTel");

		String id = request.getParameter("id");
		String memo = request.getParameter("memo");

		SupplierInfo bean = new SupplierInfo();
		bean.setId(Integer.valueOf(id));
		bean.setName(name);
		bean.setAddress(address);
		bean.setContact(contact);
		bean.setContactTel(contactTel);
		bean.setUpdateTime(new Date());
		bean.setMemo(memo);

		ChineseCharToEn cte = new ChineseCharToEn();
		bean.setSupplierNum(cte.getAllFirstLetter(name));

		supplierInfoService.updateSupplierInfo(bean);
		String result = "success";
		printText(response, result);

	}

	@RequestMapping(value = "/deleteSupplierInfo")
	public void deleteSupplierInfo(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String id = request.getParameter("id");

		String result = supplierInfoService.deleteSupplierInfo(Integer.valueOf(id));
		printText(response, result);
	}

	@RequestMapping(value = "/list")
	private String list(HttpServletRequest request, HttpServletResponse response, Model model) {
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
		page.setPagination(true);

		List<DbView> terminalDeviceTypeTree = dbViewDao.listTerminalDeviceTypeTree();

		model.addAttribute("terminalDeviceTypeId", terminalDeviceTypeId);
		model.addAttribute("terminalDeviceTypeTree", terminalDeviceTypeTree);
		model.addAllAttributes(paramMap);
		return "supplierInfo.list";

	}

}
