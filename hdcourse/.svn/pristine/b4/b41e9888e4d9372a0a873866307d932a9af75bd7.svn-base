/**
 * 
 */
package com.whty.assis.demo.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.OverseasEbpSoftLicence;
import com.whty.assis.demo.model.Overseas_Ebp_licence_org;
import com.whty.assis.demo.service.OverseasEbpSoftLicenceService;
import com.whty.assis.demo.service.OverseasLicenceOrgService;
import com.whty.common.excel.PoiUtil;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.TimeUtils;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年5月17日
 */
@Controller
@RequestMapping("/manage/overseasLicenceOrg")
public class OverseasLicenceOrgController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(LicenceOrgController.class);

	@Autowired
	private OverseasLicenceOrgService overseasLicenceOrgService;

	@Autowired
	private OverseasEbpSoftLicenceService softService;

	@RequestMapping(value = "/exportSearchExcel2")
	public void exportSearchExcel2(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String ids = request.getParameter("ids");

			if (ids != null) {
				List idList = Arrays.asList(ids.split(","));
				paramMap.put("idList", idList);
			} else {
				paramMap.put("orgId", request.getParameter("ORG_ID"));
				paramMap.put("schoolName", request.getParameter("SCHOOL_NAME"));
				paramMap.put("licence_code", request.getParameter("licence_code"));
				paramMap.put("status", request.getParameter("status"));
				paramMap.put("startTime", request.getParameter("startTime"));
				paramMap.put("endTime", request.getParameter("endTime"));

			}

			// // 表头
			// String[] tableHeader = { "激活码|licence_code",
			// "允许连接平板数|max_use_count", "是否绑定|status",
			// "授权时限|licenceTerm", "录入时间|create_time" };

			String[] tableHeader = { "激活码|licence_code", "允许连接平板数|max_use_count", "授权起至日期|date", "是否绑定|status",
					"录入时间|create_time", "批次号|batch_number", "合同编号|contract_number" };

			// 文件名
			String filename = "授权码.xls";

			Map cmap = null;
			List reportList = new ArrayList();

			List<OverseasEbpSoftLicence> list = softService.querySoftLicence(paramMap);
			list = convert(list);
			// 填充
			for (int i = 0; i < list.size(); i++) {
				cmap = new HashMap();
				OverseasEbpSoftLicence bean = list.get(i);
				cmap = new HashMap();
				cmap.put("licence_code", bean.getLicence_code());
				cmap.put("max_use_count", bean.getMax_use_count());

				cmap.put("contract_number", bean.getContract_number());

				StringBuffer dateSb = new StringBuffer();

				dateSb.append(CommonFunction.getDateSampleString(bean.getStart_time())).append("至");

				if (bean.getEnd_time() == null) {
					dateSb.append("永久");
				} else {
					dateSb.append(CommonFunction.getDateSampleString(bean.getEnd_time()));
				}

				cmap.put("date", dateSb.toString());

				String status = null;

				if (bean.getStatus() == 0) {
					status = "未绑定";
				}
				if (bean.getStatus() == 1) {
					status = "已绑定";
				}
				if (bean.getStatus() == 2) {
					status = "已注销";
				}

				cmap.put("status", status);
				cmap.put("licenceTerm", bean.getLimit_day());
				cmap.put("create_time", CommonFunction.getDateSampleString(bean.getCreate_time()));
				cmap.put("batch_number", bean.getBatch_number());
				reportList.add(cmap);
			}

			String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");

			ServletOutputStream os = null;
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName);
			os = response.getOutputStream();
			HSSFWorkbook wb = new HSSFWorkbook();
			PoiUtil.createExcelSheet(wb, tableHeader, reportList);
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String ids = request.getParameter("ids");

			List idList = Arrays.asList(ids.split(","));

			paramMap.put("idList", idList);

			// 表头
			String[] tableHeader = { "激活码|licence_code", "允许连接平板数|max_use_count", "授权起至日期|date", "是否绑定|status",
					"授权时限|licenceTerm", "录入时间|create_time", "批次号|batch_number", "合同编号|contract_number" };
			// 文件名
			String filename = "licence.xls";

			Map cmap = null;
			List reportList = new ArrayList();

			List<OverseasEbpSoftLicence> list = softService.querySoftLicence(paramMap);
			list = convert(list);
			// 填充
			for (int i = 0; i < list.size(); i++) {
				cmap = new HashMap();
				OverseasEbpSoftLicence bean = list.get(i);
				cmap = new HashMap();
				cmap.put("licence_code", bean.getLicence_code());
				cmap.put("max_use_count", bean.getMax_use_count());
				cmap.put("contract_number", bean.getContract_number());
				StringBuffer dateSb = new StringBuffer();

				dateSb.append(CommonFunction.getDateSampleString(bean.getStart_time())).append("至");

				if (bean.getEnd_time() == null) {
					dateSb.append("永久");
				} else {
					dateSb.append(CommonFunction.getDateSampleString(bean.getEnd_time()));
				}

				cmap.put("date", dateSb.toString());

				String status = null;

				if (bean.getStatus() == 0) {
					status = "未绑定";
				}
				if (bean.getStatus() == 1) {
					status = "已绑定";
				}
				if (bean.getStatus() == 2) {
					status = "已注销";
				}

				cmap.put("status", status);
				cmap.put("licenceTerm", bean.getLicenceTerm());
				cmap.put("create_time", CommonFunction.getDateSampleString(bean.getCreate_time()));

				reportList.add(cmap);
			}

			String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");

			ServletOutputStream os = null;
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName);
			os = response.getOutputStream();
			HSSFWorkbook wb = new HSSFWorkbook();
			PoiUtil.createExcelSheet(wb, tableHeader, reportList);
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/exportSearchExcel")
	public void exportSearchExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orgId", request.getParameter("ORG_ID"));
			paramMap.put("schoolName", request.getParameter("SCHOOL_NAME"));
			paramMap.put("licence_code", request.getParameter("licence_code"));
			paramMap.put("status", request.getParameter("status"));
			paramMap.put("startTime", request.getParameter("startTime"));
			paramMap.put("endTime", request.getParameter("endTime"));

			// 表头
			String[] tableHeader = { "激活码|licence_code", "允许连接平板数|max_use_count", "是否绑定|status", "授权时限|licenceTerm",
					"录入时间|create_time" };
			// 文件名
			String filename = "授权码.xls";

			Map cmap = null;
			List reportList = new ArrayList();

			List<OverseasEbpSoftLicence> list = softService.querySoftLicence(paramMap);
			list = convert(list);
			// 填充
			for (int i = 0; i < list.size(); i++) {
				cmap = new HashMap();
				OverseasEbpSoftLicence bean = list.get(i);
				cmap = new HashMap();
				cmap.put("licence_code", bean.getLicence_code());
				cmap.put("max_use_count", bean.getMax_use_count());
				StringBuffer dateSb = new StringBuffer();

				dateSb.append(CommonFunction.getDateSampleString(bean.getStart_time())).append("至");

				if (bean.getEnd_time() == null) {
					dateSb.append("永久");
				} else {
					dateSb.append(CommonFunction.getDateSampleString(bean.getEnd_time()));
				}

				cmap.put("date", dateSb.toString());

				String status = null;

				if (bean.getStatus() == 0) {
					status = "未绑定";
				}
				if (bean.getStatus() == 1) {
					status = "已绑定";
				}
				if (bean.getStatus() == 2) {
					status = "已注销";
				}

				cmap.put("status", status);
				cmap.put("licenceTerm", bean.getLimit_day());
				cmap.put("create_time", CommonFunction.getDateSampleString(bean.getCreate_time()));

				reportList.add(cmap);
			}

			String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");

			ServletOutputStream os = null;
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName);
			os = response.getOutputStream();
			HSSFWorkbook wb = new HSSFWorkbook();
			PoiUtil.createExcelSheet(wb, tableHeader, reportList);
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到激活码授权管理
	 */
	@RequestMapping(value = "/softLicenceList")
	public String softLicenceList(HttpServletRequest request, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("激活码授权管理列表页数据加载");
		}
		// String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgId", request.getParameter("ORG_ID"));
		paramMap.put("schoolName", request.getParameter("SCHOOL_NAME"));
		paramMap.put("licence_code", request.getParameter("licence_code"));
		paramMap.put("status2", request.getParameter("status2"));
		paramMap.put("status", request.getParameter("status2"));
		paramMap.put("startTime", request.getParameter("startTime"));
		paramMap.put("endTime", request.getParameter("endTime"));

		if (request.getParameter("batch_number") != null) {
			paramMap.put("batch_number", request.getParameter("batch_number").trim());
		}

		PageContext page = PageContext.getContext();

		// 请自行验证
		if (null == currentPage) {
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
		HandlerResult handlerResult = softService.querySoftLicencePage(paramMap);
		List<OverseasEbpSoftLicence> preparationList = handlerResult.getResultList();
		if (preparationList != null && preparationList.size() > 0) {
			handlerResult.setResultList(convert(preparationList));
		}
		model.addAttribute("softLicenceList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "overseasLicenceOrg/ebpSoftLicenceList";
	}

	/**
	 * @category计算授权时限
	 * @return
	 */
	private List<OverseasEbpSoftLicence> convert(List<OverseasEbpSoftLicence> resource) throws Exception {
		List<OverseasEbpSoftLicence> newList = new ArrayList<OverseasEbpSoftLicence>();
		for (OverseasEbpSoftLicence g : resource) {
			Date startTime = g.getStart_time();
			Date endTime = g.getEnd_time();
			String term = Constants.LICENCE_LICENCETERM;
			if (startTime != null && endTime != null) {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(endTime);
				gc.add(2, 1);
				endTime = gc.getTime();
				// System.out.println(g.getId() + ":" + g.getStart_time() + ":"
				// + g.getEnd_time());
				term = CommonFunction.calculateStartAndEndTime(startTime, endTime, Constants.LICENCE_LICENCETERM_MM)
						+ "个月";
			}
			g.setLicenceTerm(term);
			newList.add(g);
		}
		return newList;
	}

	/**
	 * 保存修改
	 */
	@RequestMapping(value = "/saveEbpSoftLicence")
	public void saveEbpSoftLicence(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("新增或修改授权激活码");
		}
		String id = request.getParameter("id");
		String createCount = request.getParameter("createCount");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String max_use_count = request.getParameter("max_use_count");
		String org_id = request.getParameter("org_id");

		String ebp_auth = request.getParameter("ebp_auth");
		String sdp_auth = request.getParameter("sdp_auth");
		String am_auth = request.getParameter("am_auth");

		OverseasEbpSoftLicence soft = new OverseasEbpSoftLicence();
		soft.setCreate_time(new Date());
		soft.setStart_time(CommonFunction.getDateTimeString(start_time));
		if (StringUtils.isNotEmpty(end_time)) {
			soft.setEnd_time(TimeUtils.string2Date(end_time + " 23:59:59", TimeUtils.STR_DATETIME_PATTERN_LONG));
		}
		soft.setMax_use_count(Integer.parseInt(max_use_count));
		soft.setOrg_id(org_id);

		if (StringUtils.isNotEmpty(ebp_auth)) {
			soft.setEbp_auth(ebp_auth);
		}

		if (StringUtils.isNotEmpty(sdp_auth)) {
			soft.setSdp_auth(sdp_auth);
		}

		if (StringUtils.isNotEmpty(am_auth)) {
			soft.setAm_auth(am_auth);
		}

		soft.setBatch_number(GUIDGenerator.getUUID32());

		if (StringUtils.isEmpty(createCount))
			createCount = "1";
		if (StringUtils.isEmpty(id)) {

			List<String> list = new ArrayList<String>();
			for (int i = 0; i < Integer.parseInt(createCount); i++) {

				OverseasEbpSoftLicence bean = new OverseasEbpSoftLicence();
				soft.setId(GUIDGenerator.getUUID32());
				bean = softService.saveEbpSoftLicence(soft);
				list.add(bean.getId());
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.write(StringUtils.join(list, ","));

		} else {
			String status = request.getParameter("status");
			soft = new OverseasEbpSoftLicence();
			soft.setId(id);

			if (StringUtils.isNotEmpty(ebp_auth)) {
				soft.setEbp_auth(ebp_auth);
			}

			if (StringUtils.isNotEmpty(sdp_auth)) {
				soft.setSdp_auth(sdp_auth);
			}

			if (StringUtils.isNotEmpty(am_auth)) {
				soft.setAm_auth(am_auth);
			}

			if (StringUtils.isNotEmpty(status))
				soft.setStatus(Integer.parseInt(status));
			soft.setMax_use_count(Integer.parseInt(max_use_count));
			softService.updateEbpSoftLicence(soft);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.write("success");
		}

	}

	/**
	 * 查看详情
	 */
	@RequestMapping(value = "/detailEbpSoftLicence")
	public void detailEbpSoftLicence(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("授权激活码详情数据加载");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id))
			return;

		OverseasEbpSoftLicence softObj = softService.getSoftLicenceById(id);
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(softObj).toString());
	}

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
		HandlerResult handlerResult = overseasLicenceOrgService.queryLicenceOrgPage(paramMap);

		model.addAttribute("licenceOrgList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		overseasLicenceOrgService.setCountDataTOModel(model);
		return "overseasLicenceOrg/licenceOrgList";
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
		List<Overseas_Ebp_licence_org> licenceOrgList = overseasLicenceOrgService.queryLicenceOrg(map);
		Overseas_Ebp_licence_org licenceOrg = licenceOrgList.get(0);
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
		List<Overseas_Ebp_licence_org> lst = overseasLicenceOrgService.queryLicenceOrg(paramMap);

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

		Overseas_Ebp_licence_org licenceOrg = new Overseas_Ebp_licence_org();
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

		overseasLicenceOrgService.saveLicenceOrg(licenceOrg);
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
		Overseas_Ebp_licence_org licenceOrg = new Overseas_Ebp_licence_org();
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

		overseasLicenceOrgService.updateLicenceOrg(licenceOrg);
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
			overseasLicenceOrgService.deleteLicenceOrg(idList);
		}
		PrintWriter writer = response.getWriter();
		writer.write("success");
	}
}
