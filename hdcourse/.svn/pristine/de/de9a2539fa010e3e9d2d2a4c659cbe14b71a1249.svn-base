package com.whty.assis.demo.controller;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.Ebp_licence_org;
import com.whty.assis.demo.service.LicenceOrgService;
import com.whty.common.util.GUIDGenerator;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONObject;

/**
 * 授权机构信息管理controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manage/licenceOrg")
public class LicenceOrgController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(LicenceOrgController.class);

	@Autowired
	private LicenceOrgService licenceOrgService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("授权机构列表页数据加载");
		}
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("school_name", request.getParameter("school_name"));
		paramMap.put("status", "0");
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
		HandlerResult handlerResult = licenceOrgService.queryLicenceOrgPage(paramMap);

		model.addAttribute("licenceOrgList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		licenceOrgService.setCountDataTOModel(model);
		return "licenceOrg/licenceOrgList";
	}

	/**
	 * 查看授权机构详情
	 */
	@RequestMapping("/detail")
	public void detail(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("授权机构详情数据加载");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String id = request.getParameter("id");
		Map map = new HashMap();
		map.put("id", id);
		List<Ebp_licence_org> licenceOrgList = licenceOrgService.queryLicenceOrg(map);
		Ebp_licence_org licenceOrg = licenceOrgList.get(0);
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(licenceOrg).toString());
	}

	/**
	 * 新增授权机构信息
	 */
	@RequestMapping("/save")
	public void save(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("新增授权机构信息");
		}

		String contract_number = request.getParameter("contract_number");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("contract_number", contract_number);
		List<Ebp_licence_org> lst = licenceOrgService.queryLicenceOrg(paramMap);

		if (lst != null && lst.size() > 0) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.write("合同号已经存在!");
			return;
		}

		String school_name = request.getParameter("school_name");
		String school_address = request.getParameter("school_address");
		String contacts_name = request.getParameter("contacts_name");
		String contacts_sex = request.getParameter("contacts_sex");
		String contacts_cellphone = request.getParameter("contacts_cellphone");
		String contacts_phone = request.getParameter("contacts_phone");
		String contacts_qq = request.getParameter("contacts_qq");
		String contacts_email = request.getParameter("contacts_email");
		String contacts_address = request.getParameter("contacts_address");
		String managers_name = request.getParameter("managers_name");
		String managers_cellphone = request.getParameter("managers_cellphone");
		String managers_email = request.getParameter("managers_email");

		Date create_time = new Date();

		Ebp_licence_org licenceOrg = new Ebp_licence_org();
		licenceOrg.setId(GUIDGenerator.getUUID32());
		licenceOrg.setContacts_address(contacts_address);
		licenceOrg.setContacts_cellphone(contacts_cellphone);
		licenceOrg.setContacts_email(contacts_email);
		licenceOrg.setContacts_name(contacts_name);
		licenceOrg.setContacts_phone(contacts_phone);
		licenceOrg.setContacts_qq(contacts_qq);
		licenceOrg.setContacts_sex(contacts_sex);
		licenceOrg.setContract_number(contract_number);
		licenceOrg.setCreate_time(create_time);
		licenceOrg.setManagers_cellphone(managers_cellphone);
		licenceOrg.setManagers_email(managers_email);
		licenceOrg.setManagers_name(managers_name);
		licenceOrg.setSchool_address(school_address);
		licenceOrg.setSchool_name(school_name);
		licenceOrg.setStatus("0");
		licenceOrg.setUpdate_time(create_time);

		licenceOrgService.saveLicenceOrg(licenceOrg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 修改授权机构信息
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("修改授权机构信息");
		}
		String id = request.getParameter("id");
		String school_name = request.getParameter("school_name");
		String school_address = request.getParameter("school_address");
		String contacts_name = request.getParameter("contacts_name");
		String contacts_sex = request.getParameter("contacts_sex");
		String contacts_cellphone = request.getParameter("contacts_cellphone");
		String contacts_phone = request.getParameter("contacts_phone");
		String contacts_qq = request.getParameter("contacts_qq");
		String contacts_email = request.getParameter("contacts_email");
		String contacts_address = request.getParameter("contacts_address");
		String managers_name = request.getParameter("managers_name");
		String managers_cellphone = request.getParameter("managers_cellphone");
		String managers_email = request.getParameter("managers_email");
		String contract_number = request.getParameter("contract_number");
		Ebp_licence_org licenceOrg = new Ebp_licence_org();
		licenceOrg.setId(id);
		licenceOrg.setContacts_address(contacts_address);
		licenceOrg.setContacts_cellphone(contacts_cellphone);
		licenceOrg.setContacts_email(contacts_email);
		licenceOrg.setContacts_name(contacts_name);
		licenceOrg.setContacts_phone(contacts_phone);
		licenceOrg.setContacts_qq(contacts_qq);
		licenceOrg.setContacts_sex(contacts_sex);
		licenceOrg.setContract_number(contract_number);
		licenceOrg.setManagers_cellphone(managers_cellphone);
		licenceOrg.setManagers_email(managers_email);
		licenceOrg.setManagers_name(managers_name);
		licenceOrg.setSchool_address(school_address);
		licenceOrg.setSchool_name(school_name);
		licenceOrg.setUpdate_time(new Date());

		licenceOrgService.updateLicenceOrg(licenceOrg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}

	/**
	 * 删除授权机构信息
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("删除授权机构信息");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String ids = request.getParameter("ids");
		if (StringUtils.isNotEmpty(ids)) {
			List idList = Arrays.asList(ids.split(","));
			licenceOrgService.deleteLicenceOrg(idList);
		}
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}
}
