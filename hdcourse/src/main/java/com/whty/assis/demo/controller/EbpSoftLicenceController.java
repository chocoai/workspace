package com.whty.assis.demo.controller;

import java.io.IOException;
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

import com.whty.assis.manage.dao.HdCodeDao;
import com.whty.assis.manage.dao.HdCodeListenDao;
import com.whty.assis.manage.dao.ModularDao;
import com.whty.assis.manage.model.HdCode;
import com.whty.assis.manage.model.HdCodeListen;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.EbpSoftLicence;
import com.whty.assis.demo.model.ManageUserInfo;
import com.whty.assis.demo.service.EbpSoftLicenceService;
import com.whty.common.excel.PoiUtil;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.GUIDGenerator;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 软件验证码
 * 
 * @author zhangc
 */
@Controller
@RequestMapping("/manage/licenceOrg")
public class EbpSoftLicenceController extends BaseController {

	@Autowired
	private EbpSoftLicenceService softService;
	@Autowired
	private HdCodeListenDao hdCodeListenDao;
	@Autowired
	private HdCodeDao hdCodeDao;

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

			List<EbpSoftLicence> list = softService.querySoftLicence(paramMap);
			list = convert(list);
			// 填充
			for (int i = 0; i < list.size(); i++) {
				cmap = new HashMap();
				EbpSoftLicence bean = list.get(i);
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

			List<EbpSoftLicence> list = softService.querySoftLicence(paramMap);
			list = convert(list);
			// 填充
			for (int i = 0; i < list.size(); i++) {
				cmap = new HashMap();
				EbpSoftLicence bean = list.get(i);
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

			List<EbpSoftLicence> list = softService.querySoftLicence(paramMap);
			list = convert(list);
			// 填充
			for (int i = 0; i < list.size(); i++) {
				cmap = new HashMap();
				EbpSoftLicence bean = list.get(i);
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
		List<EbpSoftLicence> preparationList = handlerResult.getResultList();
		if (preparationList != null && preparationList.size() > 0) {
			handlerResult.setResultList(convert(preparationList));
		}
		model.addAttribute("softLicenceList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "licenceOrg/ebpSoftLicenceList";
	}

	/**
	 * @category计算授权时限
	 * @return
	 */
	private List<EbpSoftLicence> convert(List<EbpSoftLicence> resource) throws Exception {
		List<EbpSoftLicence> newList = new ArrayList<EbpSoftLicence>();
		for (EbpSoftLicence g : resource) {
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
		ManageUserInfo mui = (ManageUserInfo) request.getSession().getAttribute("manageUser");
		String manageUser = mui.getUser_name();
		String id = request.getParameter("id");
		String createCount = request.getParameter("createCount");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String max_use_count = request.getParameter("max_use_count");
		String org_id = request.getParameter("org_id");

		String ebp_auth = request.getParameter("ebp_auth");
		String sdp_auth = request.getParameter("sdp_auth");
		String am_auth = request.getParameter("am_auth");
		String codeIds = request.getParameter("codeIds");
		String[] codeIds2 = new String[0];
		if (codeIds != null && codeIds.length() != 0) {
			codeIds2 = codeIds.split(",");
		}

		EbpSoftLicence soft = new EbpSoftLicence();
		soft.setCreate_time(new Date());
		soft.setStart_time(CommonFunction.getDateTimeString(start_time));
		if (StringUtils.isNotEmpty(end_time)) {
			soft.setEnd_time(CommonFunction.getDateTimeString(end_time));
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

				EbpSoftLicence bean = new EbpSoftLicence();
				soft.setId(GUIDGenerator.getUUID32());
				soft.setCreator(manageUser);
				bean = softService.saveEbpSoftLicence(soft);
				if (codeIds2 != null && codeIds2.length != 0) {
					hdCodeListenDao.insert(soft.getLicence_code(), codeIds2);
				}
				list.add(bean.getId());
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.write(StringUtils.join(list, ","));

		} else {
			String status = request.getParameter("status");
			soft = softService.getById(id);
			soft.setLast_editor(manageUser);
			soft.setCreate_time(new Date());
			soft.setStart_time(CommonFunction.getDateTimeString(start_time));
			if (StringUtils.isNotEmpty(end_time)) {
				soft.setEnd_time(CommonFunction.getDateTimeString(end_time));
			}

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
			hdCodeListenDao.delete(soft.getLicence_code());
			if (codeIds2 != null && codeIds2.length != 0) {
				hdCodeListenDao.insert(soft.getLicence_code(), codeIds2);
			}

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

		EbpSoftLicence softObj = softService.getSoftLicenceById(id);
		PrintWriter writer = response.getWriter();
		writer.write(JSONObject.fromObject(softObj).toString());
	}

	/**
	 * 编码功能树形图
	 */
	@ResponseBody
	@RequestMapping(value = "/getCodeTree")
	public void getCodeTree(HttpServletRequest request, HttpServletResponse response, String listenCode)
			throws IOException {
		List<HdCode> hdCodes = hdCodeDao.getListTree();
		List<HdCodeListen> hdCodeListens = hdCodeListenDao.getList(listenCode);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = null;
		for (HdCode hdCode : hdCodes) {
			jsonObj = new JSONObject();
			jsonObj.put("id", hdCode.getId());
			jsonObj.put("pId", hdCode.getPid());
			jsonObj.put("name", hdCode.getName());
			if (hdCodeListens != null && hdCodeListens.size() > 0) {
				for (HdCodeListen HdCodeListen : hdCodeListens) {
					if (HdCodeListen.getCodeId().equals(hdCode.getId())) {
						jsonObj.put("checked", true);
						break;
					}
				}
			}
			jsonArray.add(jsonObj);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write(jsonArray.toString());
	}

}
