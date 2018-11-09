package com.whty.assis.demo.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.whty.assis.demo.model.EbpSoftLicence;
import com.whty.assis.demo.service.EbpSoftLicenceService;
import com.whty.common.util.GUIDGenerator;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Controller
@RequestMapping("/manage/licenceCode")
public class LicenceCodeController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(LicenceCodeController.class);

	@Autowired
	private EbpSoftLicenceService ebpSoftLicenceService;

	@RequestMapping("/cancel")
	public void cancel(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("ids");
		ebpSoftLicenceService.cancelLicenceCode(ids);
		printText(response, "000000");
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

		paramMap.put("batch_number", request.getParameter("batch_number"));
		paramMap.put("licence_code", request.getParameter("licence_code"));
		paramMap.put("status", request.getParameter("status2"));

		paramMap.put("contract_number", request.getParameter("contract_number"));

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
		HandlerResult handlerResult = ebpSoftLicenceService.queryLicenceCode(paramMap);

		model.addAttribute("licenceCodeList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		// licenceOrgService.setCountDataTOModel(model);
		return "licenceCode/list";
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
		// String start_time = request.getParameter("start_time");
		// String end_time = request.getParameter("end_time");
		String max_use_count = request.getParameter("max_use_count");
		String org_id = request.getParameter("org_id");

		String ebp_auth = request.getParameter("ebp_auth");
		String sdp_auth = request.getParameter("sdp_auth");
		String am_auth = request.getParameter("am_auth");

		EbpSoftLicence soft = new EbpSoftLicence();
		soft.setCreate_time(new Date());
		// soft.setStart_time(CommonFunction.getDateTimeString(start_time));
		// if (StringUtils.isNotEmpty(end_time)) {
		// soft.setEnd_time(CommonFunction.getDateTimeString(end_time));
		// }
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
				bean = ebpSoftLicenceService.saveEbpSoftLicence(soft);
				list.add(bean.getId());
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.write(StringUtils.join(list, ","));

		} else {
			String status = request.getParameter("status");
			soft = new EbpSoftLicence();
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
			ebpSoftLicenceService.updateEbpSoftLicence(soft);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.write("success");
		}

	}

}
