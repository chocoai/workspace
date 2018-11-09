package com.whty.assis.demo.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BigDecimalTypeHandler;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.TerminalUseHistory;
import com.whty.assis.demo.model.WidgetHistory;
import com.whty.assis.demo.service.AreaService;
import com.whty.assis.demo.service.CountTerminalUseService;
import com.whty.assis.demo.service.TerminalUseHistoryService;
import com.whty.common.excel.PoiUtil;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

/**
 * 终端使用统计
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/countTerminalUse")
public class CountTerminalUseController extends BaseController {

	@Autowired
	private CountTerminalUseService countTerminalUseService;

	@Autowired
	private AreaService areaService;

	/**
	 * 终端使用统计页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		PageContext page = PageContext.getContext();

		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> paramMap = this.getParameter(request);

		// 查询省列表
		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
		page.setPagination(false);
		List<Map> provinceList = areaService.queryArea(areaMap);
		model.addAttribute("provinceList", JSONArray.fromObject(provinceList));

		// //查询市列表
		if (paramMap.get("provinceCode") != null && StringUtils.isNotEmpty(paramMap.get("provinceCode").toString())) {
			areaMap.put("parentId", paramMap.get("provinceCode").toString());
			areaMap.put("levelId", 2);
			List<Map> cityList = areaService.queryArea(areaMap);
			model.addAttribute("cityList", JSONArray.fromObject(cityList));
		}

		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));
		}

		// 查询学校
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			List<Map> list = countTerminalUseService.getOrgListByAreaCode(paramMap.get("cityCode").toString());
			model.addAttribute("orgList", JSONArray.fromObject(list));
		}

		// List<TerminalUseHistory> terminalUseHistoryList = new
		// ArrayList<TerminalUseHistory>();

		int dzsbCount = 0;
		int dzsbAvgCount = 0;
		int dzsbMaxCount = 0;

		int zzhbCount = 0;
		int zzhbAvgCount = 0;
		int zzhbMaxCount = 0;

		int smbCount = 0;
		int smbAvgCount = 0;
		int smbMaxCount = 0;

		int dtqCount = 0;
		int dtqAvgCount = 0;
		int dtqMaxCount = 0;

		// 查询条件 区域查询

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if (paramMap.get("start_time") != null && !"".equals(paramMap.get("start_time"))) {
			String start_time = (String) paramMap.get("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			model.addAttribute("start_time");
		}

		// int year = 2016;
		// int month = 11;

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

		HandlerResult handlerResult = new HandlerResult();
		List<TerminalUseHistory> terminalUseHistoryList = new ArrayList<TerminalUseHistory>();

		if (paramMap.get("provinceCode") == null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			// page.setPagination(false);
			handlerResult = countTerminalUseService.getTerminalUseHistoryCountPage(param);
			page.setPagination(false);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCount(param);
			page.setPagination(true);
		} else if (paramMap.get("orgId") != null && StringUtils.isNotEmpty(paramMap.get("orgId").toString())) {// 机构查询
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("orgId", paramMap.get("orgId"));
			page.setPagination(false);
			handlerResult = countTerminalUseService.getTerminalUseHistoryCountByOrgPage(param);// 学校下所有用户的统计
			page.setPagination(true);
			// page.setPagination(false);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByOrg(param);
			// page.setPagination(true);
		} else if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {// 查询所有学校
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("areaCode", paramMap.get("cityCode"));
			// page.setPagination(false);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByAreaCode(param);
			// page.setPagination(true);
			page.setPagination(false);
			handlerResult = countTerminalUseService.getTerminalUseHistoryCountByAreaCodePage(param);// 区域下所有学校的统计
			page.setPagination(true);
		} else if (paramMap.get("provinceCode") != null
				&& StringUtils.isNotEmpty(paramMap.get("provinceCode").toString())) {
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("provinceCode", paramMap.get("provinceCode"));
			// page.setPagination(false);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByProvinceCode(param);
			// page.setPagination(true);
			page.setPagination(false);
			handlerResult = countTerminalUseService.getTerminalUseHistoryCountByProvinceCodePage(param);// 找到所有区域的统计
			page.setPagination(true);
		}

		Collections.sort(handlerResult.getResultList(), new Comparator<TerminalUseHistory>() {
			@Override
			public int compare(TerminalUseHistory bean1, TerminalUseHistory bean2) {
				return bean2.getCountCreateTime().compareTo(bean1.getCountCreateTime());
			}
		});
		page.setPagination(false);
		for (TerminalUseHistory bean : terminalUseHistoryList) {
			dzsbCount = dzsbCount + bean.getDzsbLinkNum().intValue();

			zzhbCount = zzhbCount + bean.getZzhbLinkNum().intValue();

			smbCount = smbCount + bean.getSmbLinkNum().intValue();

			dtqCount = dtqCount + bean.getDtqLinkNum().intValue();

			if (bean.getDzsbLinkNum() > dzsbMaxCount) {
				dzsbMaxCount = bean.getDzsbLinkNum();
			}

			if (bean.getZzhbLinkNum() > zzhbMaxCount) {
				zzhbMaxCount = bean.getZzhbLinkNum();
			}

			if (bean.getSmbLinkNum() > smbMaxCount) {
				smbMaxCount = bean.getSmbLinkNum();
			}

			if (bean.getDtqLinkNum() > dtqMaxCount) {
				dtqMaxCount = bean.getDtqLinkNum();
			}
		}

		if (terminalUseHistoryList.size() != 0) {
			BigDecimal dzsbAvgBigDecimal = new BigDecimal(dzsbCount)
					.divide(new BigDecimal(terminalUseHistoryList.size()), 2, BigDecimal.ROUND_HALF_EVEN);
			BigDecimal zzhbAvgBigDecimal = new BigDecimal(zzhbCount)
					.divide(new BigDecimal(terminalUseHistoryList.size()), 2, BigDecimal.ROUND_HALF_EVEN);
			BigDecimal smbAvgBigDecimal = new BigDecimal(smbCount).divide(new BigDecimal(terminalUseHistoryList.size()),
					2, BigDecimal.ROUND_HALF_EVEN);
			BigDecimal dtqAvgBigDecimal = new BigDecimal(dtqCount).divide(new BigDecimal(terminalUseHistoryList.size()),
					2, BigDecimal.ROUND_HALF_EVEN);
			dzsbAvgCount = dzsbAvgBigDecimal.intValue();
			zzhbAvgCount = zzhbAvgBigDecimal.intValue();
			smbAvgCount = smbAvgBigDecimal.intValue();
			dtqAvgCount = dtqAvgBigDecimal.intValue();
		}

		model.addAttribute("dzsbCount", dzsbCount);
		model.addAttribute("dzsbAvgCount", dzsbAvgCount);
		model.addAttribute("dzsbMaxCount", dzsbMaxCount);

		model.addAttribute("zzhbCount", zzhbCount);
		model.addAttribute("zzhbAvgCount", zzhbAvgCount);
		model.addAttribute("zzhbMaxCount", zzhbMaxCount);

		model.addAttribute("smbCount", smbCount);
		model.addAttribute("smbAvgCount", smbAvgCount);
		model.addAttribute("smbMaxCount", smbMaxCount);

		model.addAttribute("dtqCount", dtqCount);
		model.addAttribute("dtqAvgCount", dtqAvgCount);
		model.addAttribute("dtqMaxCount", dtqMaxCount);

		model.addAttribute("terminalUseHistoryList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		// page.setPagination(false);

		// 循环，合并日期，并且给默认值
		model.addAllAttributes(paramMap);
		return "terminalUseCount/list";
	}

	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> paramMap = this.getParameter(request);
		List<TerminalUseHistory> terminalUseHistoryList = new ArrayList<TerminalUseHistory>();

		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String org = request.getParameter("org");

		if (province != null && !"".equals(province)) {
			paramMap.put("provinceCode", province);
		}

		if (city != null && !"".equals(city)) {
			paramMap.put("cityCode", city);
		}

		if (org != null && !"".equals(org)) {
			paramMap.put("orgId", org);
		}

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if (request.getParameter("start_time") != null && !"".equals(request.getParameter("start_time"))) {
			String start_time = request.getParameter("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			// model.addAttribute("start_time");
		}

		if (paramMap.get("provinceCode") == null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			// page.setPagination(false);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCount(param);
		} else if (paramMap.get("orgId") != null && StringUtils.isNotEmpty(paramMap.get("orgId").toString())) {// 机构查询
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("orgId", paramMap.get("orgId"));
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByOrg(param);
			// page.setPagination(true);
		} else if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {// 查询所有学校
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("areaCode", paramMap.get("cityCode"));
			// page.setPagination(false);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByAreaCode(param);

		} else if (paramMap.get("provinceCode") != null
				&& StringUtils.isNotEmpty(paramMap.get("provinceCode").toString())) {
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("provinceCode", paramMap.get("provinceCode"));
			// page.setPagination(false);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByProvinceCode(param);
		}

		Collections.sort(terminalUseHistoryList, new Comparator<TerminalUseHistory>() {
			@Override
			public int compare(TerminalUseHistory bean1, TerminalUseHistory bean2) {
				return bean2.getCountCreateTime().compareTo(bean1.getCountCreateTime());
			}
		});

		Set<String> userSet = new HashSet<String>();

		String filename = "终端使用统计";
		String[] tableHeader = { "电子书包(次/天)|dzsbLinkNum", "掌中黑板(次/天)|zzhbLinkNum", "学生数码笔(次/天)|smbLinkNum",
				"答题器(次/天)|dtqLinkNum", "时间|countCreateTime" };
		List reportList = new ArrayList();
		Map cmap = null;

		int i = 0;

		for (TerminalUseHistory bean : terminalUseHistoryList) {
			cmap = new HashMap();

			cmap.put("dzsbLinkNum", bean.getDzsbLinkNum());
			cmap.put("zzhbLinkNum", bean.getZzhbLinkNum());
			cmap.put("smbLinkNum", bean.getSmbLinkNum());
			cmap.put("dtqLinkNum", bean.getDtqLinkNum());
			cmap.put("countCreateTime", bean.getCountCreateTime());

			reportList.add(cmap);
		}

		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		ServletOutputStream os = null;
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		PoiUtil.createExcelSheet(wb, tableHeader, reportList);
		wb.write(os);
		os.flush();
		os.close();

	}

	@RequestMapping(value = "/getChartsData")
	public void getChartsData(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String provinceCode = request.getParameter("province");
		String cityCode = request.getParameter("city");
		String orgId = request.getParameter("org");
		String start_time = request.getParameter("start_time");
		List<TerminalUseHistory> terminalUseHistoryList = new ArrayList<TerminalUseHistory>();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if (start_time != null && !"".equals(start_time)) {

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			// model.addAttribute("start_time");
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("year", year);
		param.put("month", month);

		if (provinceCode == null || "".equals(provinceCode)) {// 所有区域的排名
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCount(param);
		} else if (orgId != null && StringUtils.isNotEmpty(orgId)) {// 该学校所有老师的排名
			param.put("orgId", orgId);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByOrg(param);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {// 该区域下所有学校的排名
			param.put("areaCode", cityCode);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByAreaCode(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByProvinceCode(param);
		}

		Collections.sort(terminalUseHistoryList, new Comparator<TerminalUseHistory>() {
			@Override
			public int compare(TerminalUseHistory bean1, TerminalUseHistory bean2) {
				return bean2.getCountCreateTime().compareTo(bean1.getCountCreateTime());
			}
		});

		int size = 0;
		if (terminalUseHistoryList.size() <= 10) {
			size = terminalUseHistoryList.size();
		} else {
			size = 10;
		}
		String[] date = new String[size];

		if (terminalUseHistoryList.size() <= 10) {
			for (int i = 0; i < terminalUseHistoryList.size(); i++) {
				TerminalUseHistory bean = terminalUseHistoryList.get(i);
				date[i] = String.valueOf(bean.getCountCreateTime());
			}
		} else {
			for (int i = 0; i < 10; i++) {
				TerminalUseHistory bean = terminalUseHistoryList.get(i);
				date[i] = String.valueOf(bean.getCountCreateTime());
			}
		}

		JSONArray json = JSONArray.fromObject(date);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();

	}

	@RequestMapping(value = "/chartsData")
	public void chatesData(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String provinceCode = request.getParameter("province");
		String cityCode = request.getParameter("city");
		String orgId = request.getParameter("org");
		String start_time = request.getParameter("start_time");
		List<TerminalUseHistory> terminalUseHistoryList = new ArrayList<TerminalUseHistory>();
		List<Map<String, Object>> resultDate = new ArrayList<Map<String, Object>>();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if (start_time != null && !"".equals(start_time)) {

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			// model.addAttribute("start_time");
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("year", year);
		param.put("month", month);

		if (provinceCode == null || "".equals(provinceCode)) {// 所有区域的排名
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCount(param);
		} else if (orgId != null && StringUtils.isNotEmpty(orgId)) {// 该学校所有老师的排名
			param.put("orgId", orgId);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByOrg(param);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {// 该区域下所有学校的排名
			param.put("areaCode", cityCode);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByAreaCode(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			terminalUseHistoryList = countTerminalUseService.getTerminalUseHistoryCountByProvinceCode(param);
		}

		Collections.sort(terminalUseHistoryList, new Comparator<TerminalUseHistory>() {
			@Override
			public int compare(TerminalUseHistory bean1, TerminalUseHistory bean2) {
				return bean2.getCountCreateTime().compareTo(bean1.getCountCreateTime());
			}
		});

		int size = 0;

		if (terminalUseHistoryList.size() <= 10) {
			size = terminalUseHistoryList.size();
		} else {
			size = 10;
		}

		String[] dzsb = new String[size];
		String[] zzhb = new String[size];
		String[] smb = new String[size];
		String[] dtq = new String[size];
		String[] date = new String[size];

		if (terminalUseHistoryList.size() <= 10) {
			for (int i = 0; i < terminalUseHistoryList.size(); i++) {
				TerminalUseHistory bean = terminalUseHistoryList.get(i);
				dzsb[i] = String.valueOf(bean.getDzsbLinkNum());
				zzhb[i] = String.valueOf(bean.getZzhbLinkNum());
				smb[i] = String.valueOf(bean.getSmbLinkNum());
				dtq[i] = String.valueOf(bean.getDtqLinkNum());
				date[i] = String.valueOf(bean.getCountCreateTime());
			}
		} else {
			for (int i = 0; i < 10; i++) {
				TerminalUseHistory bean = terminalUseHistoryList.get(i);
				dzsb[i] = String.valueOf(bean.getDzsbLinkNum());
				zzhb[i] = String.valueOf(bean.getZzhbLinkNum());
				smb[i] = String.valueOf(bean.getSmbLinkNum());
				dtq[i] = String.valueOf(bean.getDtqLinkNum());
				date[i] = String.valueOf(bean.getCountCreateTime());
			}
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "电子书包");
		params.put("type", "line");
		params.put("data", dzsb);
		resultDate.add(params);

		params = new HashMap<String, Object>();
		params.put("name", "掌中黑板");
		params.put("type", "line");
		params.put("data", zzhb);
		resultDate.add(params);

		params = new HashMap<String, Object>();
		params.put("name", "学生数码笔");
		params.put("type", "line");
		params.put("data", smb);
		resultDate.add(params);

		params = new HashMap<String, Object>();
		params.put("name", "答题器");
		params.put("type", "line");
		params.put("data", dtq);
		resultDate.add(params);

		JSONArray json = JSONArray.fromObject(resultDate);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();

	}

	private Map<String, Object> getParameter(HttpServletRequest request) {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (request.getParameter("provinceCode") != null
				&& StringUtils.isNotEmpty(request.getParameter("provinceCode"))) {
			String provinceCode = request.getParameter("provinceCode");
			paraMap.put("provinceCode", provinceCode);
			paraMap.put("areaid", provinceCode.substring(0, 2));
		}
		if (request.getParameter("cityCode") != null && StringUtils.isNotEmpty(request.getParameter("cityCode"))) {
			String cityCode = request.getParameter("cityCode");
			paraMap.put("cityCode", cityCode);
			paraMap.put("areaid", cityCode.substring(0, 4));

		}
		if (request.getParameter("areaCode") != null && StringUtils.isNotEmpty(request.getParameter("areaCode"))) {
			String areaCode = request.getParameter("areaCode");
			paraMap.put("areaCode", areaCode);
			paraMap.put("areaid", areaCode);
		}

		if (request.getParameter("org") != null && StringUtils.isNotEmpty(request.getParameter("org"))) {
			paraMap.put("orgId", request.getParameter("org"));
		}

		if (request.getParameter("start_time") != null && StringUtils.isNotEmpty(request.getParameter("start_time"))) {
			paraMap.put("start_time", request.getParameter("start_time"));
		}

		return paraMap;
	}

}
