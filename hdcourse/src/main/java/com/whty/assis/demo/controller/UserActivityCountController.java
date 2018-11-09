package com.whty.assis.demo.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.model.UserMonthActivityCount;
import com.whty.assis.demo.service.AreaService;
import com.whty.assis.demo.service.UserActivityService;
import com.whty.common.util.BigDecimalUtils;
import com.whty.common.util.CommonFunction;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

/**
 * 用户活跃度统计
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/userActivityCount")
public class UserActivityCountController extends BaseController {

	@Autowired
	private UserActivityService userActivityService;

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/exportAreaActivityCountExcel")
	public void exportAreaActivityCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HSSFWorkbook wb = userActivityService.exportAreaActivityCountExcel(request, response);

		ServletOutputStream os = null;

		String filename = "区域活跃度统计";
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

	@RequestMapping(value = "/exportUserActivityCountExcel")
	public void exportUserActivityCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFWorkbook wb = userActivityService.exportUserActivityCountExcel(request, response);

		ServletOutputStream os = null;

		String filename = "用户活跃度统计";
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();

	}

	@RequestMapping(value = "/exportOrgActivityCountExcel")
	public void exportOrgActivityCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFWorkbook wb = userActivityService.exportOrgActivityCountExcel(request, response);

		ServletOutputStream os = null;

		String filename = "学校活跃度统计";
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

	/**
	 * 区域统计图表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getActivityCountLegendData")
	public void getActivityCountLegendData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String provinceCode = request.getParameter("province");

		List<AreaMonthActivityCount> areaMonthActivityCountList = new ArrayList<AreaMonthActivityCount>();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("year", year);
		param.put("month", month);

		if (request.getParameter("start_time") != null && !"".equals(request.getParameter("start_time"))) {
			String start_time = request.getParameter("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			param.put("year", year);
			param.put("month", month);
		}

		if (provinceCode == null || "".equals(provinceCode)) {// 所有区域的排名
			areaMonthActivityCountList = userActivityService.getAreaMonthActivityCountData(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			areaMonthActivityCountList = userActivityService.getAreaMonthActivityCountData(param);
		}

		Collections.sort(areaMonthActivityCountList, new Comparator<AreaMonthActivityCount>() {
			@Override
			public int compare(AreaMonthActivityCount bean1, AreaMonthActivityCount bean2) {
				return bean2.getLoginTaking().compareTo(bean1.getLoginTaking());
			}
		});

		String[] legendData = new String[10];

		if (areaMonthActivityCountList.size() <= 10) {
			for (int i = 0; i < areaMonthActivityCountList.size(); i++) {
				AreaMonthActivityCount bean = areaMonthActivityCountList.get(i);
				legendData[i] = bean.getAreaName();
			}
		} else {
			for (int i = 0; i < 10; i++) {
				AreaMonthActivityCount bean = areaMonthActivityCountList.get(i);
				legendData[i] = bean.getAreaName();
			}
		}

		JSONArray json = JSONArray.fromObject(legendData);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

	/*
	 * 用户统计图表
	 */
	@RequestMapping(value = "/getUserActivityChartsData")
	public void getUserActivityChartsData(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		String provinceCode = request.getParameter("province");
		String cityCode = request.getParameter("city");
		String orgId = request.getParameter("org");

		List<UserMonthActivityCount> userMonthActivityCountList = new ArrayList<UserMonthActivityCount>();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		Map<String, Object> param = new HashMap<String, Object>();

		if (request.getParameter("start_time") != null && !"".equals(request.getParameter("start_time"))) {
			String start_time = request.getParameter("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			param.put("year", year);
			param.put("month", month);
		}

		param.put("year", year);
		param.put("month", month);

		List<Map<String, Object>> resultDate = new ArrayList<Map<String, Object>>();

		if (provinceCode == null || "".equals(provinceCode)) {// 所有区域的排名
			userMonthActivityCountList = userActivityService.getUserMonthActivityCountEcharts(param);
		} else if (orgId != null && StringUtils.isNotEmpty(orgId)) {
			param.put("orgId", orgId);
			userMonthActivityCountList = userActivityService.getUserMonthActivityCountEcharts(param);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {
			param.put("cityCode", cityCode);
			userMonthActivityCountList = userActivityService.getUserMonthActivityCountEcharts(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			userMonthActivityCountList = userActivityService.getUserMonthActivityCountEcharts(param);
		}

		Collections.sort(userMonthActivityCountList, new Comparator<UserMonthActivityCount>() {
			@Override
			public int compare(UserMonthActivityCount bean1, UserMonthActivityCount bean2) {
				return bean2.getLoginTaking().compareTo(bean1.getLoginTaking());
			}
		});

		if (userMonthActivityCountList.size() <= 10) {
			for (int i = 0; i < userMonthActivityCountList.size(); i++) {
				UserMonthActivityCount bean = userMonthActivityCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName() + " " + bean.getUserName());
				resultparam.put("value", bean.getLoginTaking());
				resultDate.add(resultparam);

			}
		} else {
			for (int i = 0; i < 10; i++) {
				UserMonthActivityCount bean = userMonthActivityCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName() + " " + bean.getUserName());
				resultparam.put("value", bean.getLoginTaking());
				resultDate.add(resultparam);
			}
		}

		Collections.reverse(resultDate);
		JSONArray json = JSONArray.fromObject(resultDate);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();

	}

	/*
	 * 学校统计图表
	 */
	@RequestMapping(value = "/getOrgActivityChartsData")
	public void getOrgActivityChartsData(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String provinceCode = request.getParameter("province");
		String cityCode = request.getParameter("city");

		List<OrgMonthActivityCount> orgMonthActivityCountList = new ArrayList<OrgMonthActivityCount>();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		Map<String, Object> param = new HashMap<String, Object>();

		if (request.getParameter("start_time") != null && !"".equals(request.getParameter("start_time"))) {
			String start_time = request.getParameter("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			param.put("year", year);
			param.put("month", month);
		}

		param.put("year", year);
		param.put("month", month);

		List<Map<String, Object>> resultDate = new ArrayList<Map<String, Object>>();

		if (provinceCode == null || "".equals(provinceCode)) {// 所有区域的排名
			orgMonthActivityCountList = userActivityService.getOrgMonthActivityCountEcharts(param);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {
			param.put("cityCode", cityCode);
			orgMonthActivityCountList = userActivityService.getOrgMonthActivityCountEcharts(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			orgMonthActivityCountList = userActivityService.getOrgMonthActivityCountEcharts(param);
		}

		Collections.sort(orgMonthActivityCountList, new Comparator<OrgMonthActivityCount>() {
			@Override
			public int compare(OrgMonthActivityCount bean1, OrgMonthActivityCount bean2) {
				return bean2.getLoginTaking().compareTo(bean1.getLoginTaking());
			}
		});

		if (orgMonthActivityCountList.size() <= 10) {
			for (int i = 0; i < orgMonthActivityCountList.size(); i++) {
				OrgMonthActivityCount bean = orgMonthActivityCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName());
				resultparam.put("value", bean.getLoginTaking());
				resultparam.put("loginTakingStr", bean.getLoginTakingStr());
				resultDate.add(resultparam);

			}
		} else {
			for (int i = 0; i < 10; i++) {
				OrgMonthActivityCount bean = orgMonthActivityCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName());
				resultparam.put("value", bean.getLoginTaking());
				resultparam.put("loginTakingStr", bean.getLoginTakingStr());
				resultDate.add(resultparam);
			}
		}

		Collections.reverse(resultDate);
		JSONArray json = JSONArray.fromObject(resultDate);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

	/**
	 * 区域统计图表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAreaActivityChartsData")
	public void getAreaActivityChartsData(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String provinceCode = request.getParameter("province");

		List<AreaMonthActivityCount> areaMonthActivityCountList = new ArrayList<AreaMonthActivityCount>();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		Map<String, Object> param = new HashMap<String, Object>();

		if (request.getParameter("start_time") != null && !"".equals(request.getParameter("start_time"))) {
			String start_time = request.getParameter("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			param.put("year", year);
			param.put("month", month);
		}

		param.put("year", year);
		param.put("month", month);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		if (provinceCode == null || "".equals(provinceCode)) {// 所有区域的排名
			areaMonthActivityCountList = userActivityService.getAreaMonthActivityCountData(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			areaMonthActivityCountList = userActivityService.getAreaMonthActivityCountData(param);
		}

		// String[] legendData = new String[areaMonthActivityCountList.size()];

		Collections.sort(areaMonthActivityCountList, new Comparator<AreaMonthActivityCount>() {
			@Override
			public int compare(AreaMonthActivityCount bean1, AreaMonthActivityCount bean2) {
				return bean2.getLoginTaking().compareTo(bean1.getLoginTaking());
			}
		});

		String[] legendData = new String[10];

		if (areaMonthActivityCountList.size() <= 10) {
			for (int i = 0; i < areaMonthActivityCountList.size(); i++) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				AreaMonthActivityCount bean = areaMonthActivityCountList.get(i);
				legendData[i] = bean.getAreaName();
				dataMap.put("name", bean.getAreaName());
				dataMap.put("value", bean.getLoginTaking());
				data.add(dataMap);

			}
		} else {
			for (int i = 0; i < 10; i++) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				AreaMonthActivityCount bean = areaMonthActivityCountList.get(i);
				legendData[i] = bean.getAreaName();
				dataMap.put("name", bean.getAreaName());
				dataMap.put("value", bean.getLoginTaking().toString());
				data.add(dataMap);
			}
		}

		JSONArray json = JSONArray.fromObject(data);
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

		if (request.getParameter("orgName") != null && StringUtils.isNotEmpty(request.getParameter("orgName"))) {
			paraMap.put("orgName", request.getParameter("orgName"));
		}

		return paraMap;
	}

	/**
	 * 区域统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/areaCount")
	public String areaCount(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> paramMap = this.getParameter(request);
		PageContext page = PageContext.getContext();
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

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if (paramMap.get("start_time") != null && !"".equals(paramMap.get("start_time"))) {
			String start_time = (String) paramMap.get("start_time");
			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);

			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;// 上月

			model.addAttribute("start_time");
		}

		paramMap.put("year", year);
		paramMap.put("month", month);

		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

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

		String time = year + "/" + month;
		userActivityService.addCountDataToAttribute(model);

		userActivityService.addAreaCountDataToAttribute(model, paramMap);

		page.setPagination(true);
		HandlerResult currentHandlerResult = userActivityService.queryAreaActivityDataGroupByArea(paramMap);
		page.setPagination(false);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		HandlerResult preHandlerResult = userActivityService.queryAreaActivityDataGroupByArea(paramMap);

		List<AreaMonthActivityCount> list = new ArrayList<AreaMonthActivityCount>();

		List<AreaMonthActivityCount> currentList = currentHandlerResult.getResultList();
		List<AreaMonthActivityCount> preList = preHandlerResult.getResultList();

		for (AreaMonthActivityCount currentBean : currentList) {
			AreaMonthActivityCount bean = new AreaMonthActivityCount();
			bean.setAreaCode(currentBean.getAreaCode());
			bean.setOrgName(currentBean.getOrgName());
			bean.setAreaName(currentBean.getAreaName());
			bean.setLoginTaking(currentBean.getLoginTaking());
			// bean.setPlatformName(time);
			bean.setOrgLoginTakingRanking(currentBean.getOrgLoginTakingRanking());

			if (bean.getLoginTaking() == null) {
				bean.setLoginTaking(0);
				bean.setLoginTakingStr("0");
			} else {
				bean.setLoginTakingStr(CommonFunction.formatDuring(new Long(bean.getLoginTaking())));
			}

			for (AreaMonthActivityCount preBean : preList) {
				if (preBean.getAreaCode().equals(currentBean.getAreaCode())) {// 比较上月数据
					bean.setPreviousLoginTaking(preBean.getLoginTaking());

					if (bean.getPreviousLoginTaking() == null) {
						bean.setPreviousLoginTaking(0);
						bean.setPreviousLoginTakingStr("0");
					} else {
						bean.setPreviousLoginTakingStr(
								CommonFunction.formatDuring(new Long(bean.getPreviousLoginTaking())));
					}

					int rateNum = bean.getLoginTaking() - bean.getPreviousLoginTaking();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousLoginTaking());
					String rate = BigDecimalUtils.calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
			}
			list.add(bean);
		}

		model.addAttribute("areaCountList", list);
		model.addAttribute("page", page);
		model.addAttribute("time", time);
		model.addAllAttributes(paramMap);
		return "userActivityCount/areaCount";
	}

	@RequestMapping(value = "/orgCount")
	public String orgCount(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> paramMap = this.getParameter(request);
		PageContext page = PageContext.getContext();
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

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if (paramMap.get("start_time") != null && !"".equals(paramMap.get("start_time"))) {
			String start_time = (String) paramMap.get("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);

			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;// 上月
			paramMap.put("year", year);
			paramMap.put("month", month);
			model.addAttribute("start_time");
		}

		// 默认当月的数据
		paramMap.put("year", year);
		paramMap.put("month", month);

		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));
		}

		if (paramMap.get("orgName") != null && !"".equals(paramMap.get("orgName").toString().trim())) {
			paramMap.put("orgName", paramMap.get("orgName"));
			model.addAttribute("orgName");
		}

		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

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
		String time = year + "/" + month;
		userActivityService.addCountDataToAttribute(model);

		userActivityService.addOrgCountDataToAttribute(model, paramMap);

		page.setPagination(true);
		HandlerResult currentHandlerResult = userActivityService.queryOrgActivityDataGroupByOrg(paramMap);
		page.setPagination(false);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		HandlerResult preHandlerResult = userActivityService.queryOrgActivityDataGroupByOrg(paramMap);

		List<OrgMonthActivityCount> currentList = currentHandlerResult.getResultList();
		List<OrgMonthActivityCount> preList = preHandlerResult.getResultList();

		List<OrgMonthActivityCount> list = new ArrayList<OrgMonthActivityCount>();

		for (OrgMonthActivityCount currentBean : currentList) {
			OrgMonthActivityCount bean = new OrgMonthActivityCount();

			bean.setOrgId(currentBean.getOrgId());
			bean.setOrgName(currentBean.getOrgName());
			bean.setAreaName(currentBean.getAreaName());
			bean.setLoginTaking(currentBean.getLoginTaking());
			bean.setPlatformName(time);
			bean.setClassRanking(currentBean.getClassRanking());

			for (OrgMonthActivityCount preBean : preList) {
				if (preBean.getOrgId().equals(currentBean.getOrgId())) {// 比较上月数据
					bean.setPreviousLoginTaking(preBean.getLoginTaking());

					if (bean.getLoginTaking() == null) {
						bean.setLoginTaking(0);
						bean.setLoginTakingStr("0");
					} else {
						bean.setLoginTakingStr(CommonFunction.formatDuring(new Long(bean.getLoginTaking())));
					}

					if (bean.getPreviousLoginTaking() == null) {
						bean.setPreviousLoginTaking(0);
						bean.setPreviousLoginTakingStr("0");
					} else {
						bean.setPreviousLoginTakingStr(
								CommonFunction.formatDuring(new Long(bean.getPreviousLoginTaking())));
					}
					int rateNum = bean.getLoginTaking() - bean.getPreviousLoginTaking();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousLoginTaking());
					String rate = BigDecimalUtils.calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
			}
			list.add(bean);
		}

		model.addAttribute("orgCountList", list);
		model.addAttribute("page", page);
		model.addAttribute("time", time);
		model.addAllAttributes(paramMap);

		return "userActivityCount/orgCount";

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userCount")
	public String userCount(HttpServletRequest request, HttpServletResponse response, Model model) {
		PageContext page = PageContext.getContext();
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
			List<Map> list = userActivityService.getOrgListByAreaCode(paramMap.get("cityCode").toString());
			model.addAttribute("orgList", JSONArray.fromObject(list));
		}

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if (paramMap.get("start_time") != null && !"".equals(paramMap.get("start_time"))) {
			String start_time = (String) paramMap.get("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);

			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;// 上月

			paramMap.put("year", year);
			paramMap.put("month", month);
			model.addAttribute("start_time");
		}

		// 默认当月的数据
		paramMap.put("year", year);
		paramMap.put("month", month);

		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

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
		userActivityService.addCountDataToAttribute(model);
		userActivityService.addUserCountDataToAttribute(model, paramMap);
		String time = year + "/" + month;

		page.setPagination(true);
		HandlerResult currentHandlerResult = userActivityService.queryUserActivityDataGroupByUser(paramMap);
		page.setPagination(false);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		HandlerResult preHandlerResult = userActivityService.queryUserActivityDataGroupByUser(paramMap);

		List<UserMonthActivityCount> currentList = currentHandlerResult.getResultList();
		List<UserMonthActivityCount> preList = preHandlerResult.getResultList();

		List<UserMonthActivityCount> list = new ArrayList<UserMonthActivityCount>();

		for (UserMonthActivityCount currentBean : currentList) {
			UserMonthActivityCount bean = new UserMonthActivityCount();

			bean.setOrgId(currentBean.getOrgId());
			bean.setUserId(currentBean.getUserId());

			bean.setOrgName(currentBean.getOrgName());
			bean.setAreaName(currentBean.getAreaName());
			bean.setLoginTaking(currentBean.getLoginTaking());
			bean.setPlatformName(time);
			bean.setUserName(currentBean.getUserName());
			bean.setRanking(currentBean.getRanking());

			for (UserMonthActivityCount preBean : preList) {
				if (preBean.getUserId().equals(currentBean.getUserId())) {// 比较上月数据
					bean.setPreviousLoginTaking(currentBean.getLoginTaking());

					if (bean.getLoginTaking() == null) {
						bean.setLoginTaking(0);
						bean.setLoginTakingStr("0");
					} else {
						bean.setLoginTakingStr(CommonFunction.formatDuring(new Long(bean.getLoginTaking())));
					}

					if (bean.getPreviousLoginTaking() == null) {
						bean.setPreviousLoginTaking(0);
						bean.setPreviousLoginTakingStr("0");
					} else {
						bean.setPreviousLoginTakingStr(
								CommonFunction.formatDuring(new Long(bean.getPreviousLoginTaking())));
					}

					int rateNum = bean.getLoginTaking() - bean.getPreviousLoginTaking();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousLoginTaking());
					String rate = BigDecimalUtils.calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
			}
			list.add(bean);
		}

		model.addAttribute("time", time);
		model.addAttribute("userCountList", list);
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);

		return "userActivityCount/userCount";
	}

}
