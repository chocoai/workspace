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

import com.whty.assis.demo.model.Area;
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.AreaMonthUseTakingCount;
import com.whty.assis.demo.model.OrgMonthUseTakingCount;
import com.whty.assis.demo.model.UserMonthActivityCount;
import com.whty.assis.demo.model.UserMonthUseTakingCount;
import com.whty.assis.demo.service.AreaService;
import com.whty.assis.demo.service.CountUseTakingService;
import com.whty.assis.demo.service.UseTakingCountService;
import com.whty.common.util.BigDecimalUtils;
import com.whty.common.util.CommonFunction;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

/**
 * 使用时长统计
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/countUseTaking")
public class CountUseTakingController {

	@Autowired
	private CountUseTakingService countUseTakingService;

	@Autowired
	private AreaService areaService;

	/**
	 * 跳转到页面
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model) throws Exception {
		return "activityCount/index";
	}

	/**
	 * 区域统计页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/areaCount")
	public String areaCount(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> paramMap = this.getParameter(request);

		// 查询省列表
		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
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
		countUseTakingService.addCountDataToAttribute(model);

		countUseTakingService.addAreaCountDataToAttribute(model, paramMap);

		page.setPagination(true);
		// HandlerResult handlerResult =
		// useCountService.queryAreaMonthUsageCount(paramMap);
		String time = new StringBuffer().append(year).append("/").append(month).toString();

		HandlerResult currentHandlerResult = countUseTakingService.queryAreaMonthUseTakingDataGroupByArea(paramMap);
		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		page.setPagination(false);
		HandlerResult preHandlerResult = countUseTakingService.queryAreaMonthUseTakingDataGroupByArea(paramMap);

		List<AreaMonthUseTakingCount> currentList = currentHandlerResult.getResultList();
		List<AreaMonthUseTakingCount> preList = preHandlerResult.getResultList();
		List<AreaMonthUseTakingCount> list = new ArrayList<AreaMonthUseTakingCount>();
		for (AreaMonthUseTakingCount currentBean : currentList) {
			AreaMonthUseTakingCount bean = new AreaMonthUseTakingCount();
			bean.setAreaCode(currentBean.getAreaCode());
			bean.setAreaName(currentBean.getAreaName());
			bean.setUseTaking(currentBean.getUseTaking());
			bean.setOrgNum(currentBean.getOrgNum());

			if (currentBean.getUseTaking() == 0) {
				bean.setUseTaking(0);
				bean.setUseTakingStr("0");
			} else {
				bean.setUseTakingStr(CommonFunction.formatDuring(new Long(bean.getUseTaking())));
			}

			for (AreaMonthUseTakingCount preBean : preList) {
				if (preBean.getAreaCode().equals(currentBean.getAreaCode())) {// 比较上月数据
					bean.setPreviousUseTaking(preBean.getUseTaking());

					if (bean.getPreviousUseTaking() == 0) {
						bean.setPreviousUseTaking(0);
						bean.setPreviousUseTakingStr("0");
					} else {
						bean.setPreviousUseTakingStr(
								CommonFunction.formatDuring(new Long(bean.getPreviousUseTaking())));
					}

					long rateNum = bean.getUseTaking() - bean.getPreviousUseTaking();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseTaking());
					String rate = BigDecimalUtils.calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
			}
			list.add(bean);
		}

		model.addAttribute("areaCountList", list);
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		model.addAttribute("time", time);
		return "useTakingCount/areaCount";
	}

	/**
	 * 学校统计页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
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
		List<Map> cityList = null;
		// //查询市列表
		if (paramMap.get("provinceCode") != null && StringUtils.isNotEmpty(paramMap.get("provinceCode").toString())) {
			areaMap.put("parentId", paramMap.get("provinceCode").toString());
			areaMap.put("levelId", 2);
			cityList = areaService.queryArea(areaMap);
			model.addAttribute("cityList", JSONArray.fromObject(cityList));
		}
		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));

			if (cityList == null) {

				if (paramMap.get("provinceCode") == null) {
					Map<String, Object> qq = new HashMap<String, Object>();
					qq.put("areaCode", paramMap.get("cityCode").toString());
					List<Area> ss = areaService.getArea(qq);
					if (ss != null && ss.size() > 0) {
						model.addAttribute("provinceCode", ss.get(0).getParentId());

						paramMap.put("provinceCode", ss.get(0).getParentId());

					}
				}
				areaMap.put("parentId", paramMap.get("provinceCode").toString());
				areaMap.put("levelId", 2);
				cityList = areaService.queryArea(areaMap);
				model.addAttribute("cityList", JSONArray.fromObject(cityList));
			}
			model.addAttribute("cityCode", paramMap.get("cityCode"));
		}

		if (paramMap.get("orgName") != null && !"".equals(paramMap.get("orgName").toString().trim())) {
			paramMap.put("orgName", paramMap.get("orgName"));
			model.addAttribute("orgName");
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
		String time = year + "/" + month;
		countUseTakingService.addCountDataToAttribute(model);

		countUseTakingService.addOrgCountDataToAttribute(model, paramMap);

		page.setPagination(true);
		HandlerResult currentHandlerResult = countUseTakingService.queryOrgUseTakingDataGroupByOrg(paramMap);
		page.setPagination(false);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		HandlerResult preHandlerResult = countUseTakingService.queryOrgUseTakingDataGroupByOrg(paramMap);

		List<OrgMonthUseTakingCount> currentList = currentHandlerResult.getResultList();
		List<OrgMonthUseTakingCount> preList = preHandlerResult.getResultList();

		List<OrgMonthUseTakingCount> list = new ArrayList<OrgMonthUseTakingCount>();

		for (OrgMonthUseTakingCount currentBean : currentList) {
			OrgMonthUseTakingCount bean = new OrgMonthUseTakingCount();
			bean.setOrgId(currentBean.getOrgId());
			bean.setOrgName(currentBean.getOrgName());
			bean.setAreaName(currentBean.getAreaName());
			bean.setUseTaking(currentBean.getUseTaking());
			bean.setUserNum(currentBean.getUserNum());
			bean.setPlatformName(time);
			bean.setAreaCode(currentBean.getAreaCode());
			if (currentBean.getUseTaking() == 0) {
				bean.setUseTaking(0);
				bean.setUseTakingStr("0");
			} else {
				bean.setUseTakingStr(CommonFunction.formatDuring(new Long(bean.getUseTaking())));
			}

			for (OrgMonthUseTakingCount preBean : preList) {
				if (preBean.getOrgId().equals(currentBean.getOrgId())) {// 比较上月数据
					bean.setPreviousUseTaking(preBean.getUseTaking());

					if (bean.getPreviousUseTaking() == 0) {
						bean.setPreviousUseTaking(0);
						bean.setPreviousUseTakingStr("0");
					} else {
						bean.setPreviousUseTakingStr(
								CommonFunction.formatDuring(new Long(bean.getPreviousUseTaking())));
					}
					long rateNum = bean.getUseTaking() - bean.getPreviousUseTaking();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseTaking());
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

		return "useTakingCount/orgCount";
	}

	/**
	 * 用户统计页面
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

		List<Map> cityList = null;
		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));

			if (cityList == null) {

				if (paramMap.get("provinceCode") == null) {
					Map<String, Object> qq = new HashMap<String, Object>();
					qq.put("areaCode", paramMap.get("cityCode").toString());
					List<Area> ss = areaService.getArea(qq);
					if (ss != null && ss.size() > 0) {
						model.addAttribute("provinceCode", ss.get(0).getParentId());

						paramMap.put("provinceCode", ss.get(0).getParentId());

					}
				}
				areaMap.put("parentId", paramMap.get("provinceCode").toString());
				areaMap.put("levelId", 2);
				cityList = areaService.queryArea(areaMap);
				model.addAttribute("cityList", JSONArray.fromObject(cityList));
			}
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

		// 查询学校
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			paramMap.put("areaCode", paramMap.get("cityCode").toString());

			List<Map> list = countUseTakingService.getOrgListByAreaCode(paramMap);
			model.addAttribute("orgList", JSONArray.fromObject(list));
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
		countUseTakingService.addCountDataToAttribute(model);
		countUseTakingService.addUserCountDataToAttribute(model, paramMap);
		String time = year + "/" + month;

		page.setPagination(true);
		HandlerResult currentHandlerResult = countUseTakingService.queryUserUseTakingDataGroupByUser(paramMap);
		page.setPagination(false);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		HandlerResult preHandlerResult = countUseTakingService.queryUserUseTakingDataGroupByUser(paramMap);

		List<UserMonthUseTakingCount> currentList = currentHandlerResult.getResultList();
		List<UserMonthUseTakingCount> preList = preHandlerResult.getResultList();

		List<UserMonthUseTakingCount> list = new ArrayList<UserMonthUseTakingCount>();

		for (UserMonthUseTakingCount currentBean : currentList) {
			UserMonthUseTakingCount bean = new UserMonthUseTakingCount();

			bean.setOrgId(currentBean.getOrgId());
			bean.setUserId(currentBean.getUserId());

			bean.setOrgName(currentBean.getOrgName());
			bean.setAreaName(currentBean.getAreaName());
			bean.setUseTaking(currentBean.getUseTaking());

			bean.setPlatformName(time);
			bean.setUserName(currentBean.getUserName());

			if (currentBean.getUseTaking() == 0) {
				bean.setUseTaking(0);
				bean.setUseTakingStr("0");
			} else {
				bean.setUseTakingStr(CommonFunction.formatDuring(new Long(bean.getUseTaking())));
			}

			for (UserMonthUseTakingCount preBean : preList) {
				if (preBean.getUserId().equals(currentBean.getUserId())) {// 比较上月数据
					bean.setPreviousUseTaking(preBean.getUseTaking());
					if (bean.getPreviousUseTaking() == 0) {
						bean.setPreviousUseTaking(0);
						bean.setPreviousUseTakingStr("0");
					} else {
						bean.setPreviousUseTakingStr(
								CommonFunction.formatDuring(new Long(bean.getPreviousUseTaking())));
					}

					long rateNum = bean.getUseTaking() - bean.getPreviousUseTaking();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseTaking());
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

		return "useTakingCount/userCount";
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

	@RequestMapping(value = "/exportUserTakingCountExcel")
	public void exportUserTakingCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFWorkbook wb = countUseTakingService.exportUserTakingCountExcel(request, response);

		ServletOutputStream os = null;

		String filename = "用户使用时长统计";
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

	@RequestMapping(value = "/exportOrgTakingCountExcel")
	public void exportOrgTakingCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFWorkbook wb = countUseTakingService.exportOrgTakingCountExcel(request, response);

		ServletOutputStream os = null;

		String filename = "学校使用时长统计";
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

	@RequestMapping(value = "/exportAreaActivityCountExcel")
	public void exportAreaActivityCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HSSFWorkbook wb = countUseTakingService.exportAreaActivityCountExcel(request, response);

		ServletOutputStream os = null;

		String filename = "区域使用时长统计";
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
	@RequestMapping(value = "/getUseTakingCountLegendData")
	public void getUseTakingCountLegendData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String provinceCode = request.getParameter("province");

		String cityCode = request.getParameter("city");

		String orgId = request.getParameter("org");
		List<AreaMonthUseTakingCount> areaMonthUseTakingCountList = new ArrayList<AreaMonthUseTakingCount>();

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
			areaMonthUseTakingCountList = countUseTakingService.getAreaMonthUseTakingCountData(param);
		} else if (orgId != null && StringUtils.isNotEmpty(orgId)) {
			param.put("orgId", orgId);
			areaMonthUseTakingCountList = countUseTakingService.getAreaMonthUseTakingCountData(param);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {
			param.put("cityCode", cityCode);
			areaMonthUseTakingCountList = countUseTakingService.getAreaMonthUseTakingCountData(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			areaMonthUseTakingCountList = countUseTakingService.getAreaMonthUseTakingCountData(param);
		}

		Collections.sort(areaMonthUseTakingCountList, new Comparator<AreaMonthUseTakingCount>() {
			@Override
			public int compare(AreaMonthUseTakingCount bean1, AreaMonthUseTakingCount bean2) {
				// if (bean2.getUseTaking() != null && bean1.getUseTaking() !=
				// null) {
				return (int) (bean2.getUseTaking() - bean1.getUseTaking());
				// } else {
				// return 0;
				// }
			}
		});

		String[] legendData = new String[10];

		if (areaMonthUseTakingCountList.size() <= 10) {
			for (int i = 0; i < areaMonthUseTakingCountList.size(); i++) {
				AreaMonthUseTakingCount bean = areaMonthUseTakingCountList.get(i);
				legendData[i] = bean.getAreaName();
			}
		} else {
			for (int i = 0; i < 10; i++) {
				AreaMonthUseTakingCount bean = areaMonthUseTakingCountList.get(i);
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

	/**
	 * 区域统计图表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAreaUseTakingChartsData")
	public void getAreaUseTakingChartsData(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String provinceCode = request.getParameter("province");

		List<AreaMonthUseTakingCount> areaMonthUseTakingCountList = new ArrayList<AreaMonthUseTakingCount>();

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
			areaMonthUseTakingCountList = countUseTakingService.getAreaMonthUseTakingCountData(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			areaMonthUseTakingCountList = countUseTakingService.getAreaMonthUseTakingCountData(param);
		}

		// String[] legendData = new String[areaMonthActivityCountList.size()];

		Collections.sort(areaMonthUseTakingCountList, new Comparator<AreaMonthUseTakingCount>() {
			@Override
			public int compare(AreaMonthUseTakingCount bean1, AreaMonthUseTakingCount bean2) {
				return (int) (bean2.getUseTaking() - bean1.getUseTaking());
			}
		});

		String[] legendData = new String[10];

		if (areaMonthUseTakingCountList.size() <= 10) {
			for (int i = 0; i < areaMonthUseTakingCountList.size(); i++) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				AreaMonthUseTakingCount bean = areaMonthUseTakingCountList.get(i);
				legendData[i] = bean.getAreaName();
				dataMap.put("name", bean.getAreaName());
				dataMap.put("value", bean.getUseTaking());
				data.add(dataMap);

			}
		} else {
			for (int i = 0; i < 10; i++) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				AreaMonthUseTakingCount bean = areaMonthUseTakingCountList.get(i);
				legendData[i] = bean.getAreaName();
				dataMap.put("name", bean.getAreaName());
				dataMap.put("value", bean.getUseTaking());
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

	/*
	 * 学校统计图表
	 */
	@RequestMapping(value = "/getOrgUseTakingChartsData")
	public void getOrgUseTakingChartsData(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String provinceCode = request.getParameter("province");
		String cityCode = request.getParameter("city");
		String orgName = request.getParameter("orgName");
		List<OrgMonthUseTakingCount> orgMonthUseTakingCountList = new ArrayList<OrgMonthUseTakingCount>();

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
		if (orgName != null && StringUtils.isNotEmpty(orgName)) {// 所有区域的排名
			param.put("orgName", orgName);
			orgMonthUseTakingCountList = countUseTakingService.getOrgMonthUseTakingCountEcharts(param);
		}
		if (provinceCode == null || "".equals(provinceCode)) {// 所有区域的排名
			orgMonthUseTakingCountList = countUseTakingService.getOrgMonthUseTakingCountEcharts(param);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {
			param.put("cityCode", cityCode);
			orgMonthUseTakingCountList = countUseTakingService.getOrgMonthUseTakingCountEcharts(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			orgMonthUseTakingCountList = countUseTakingService.getOrgMonthUseTakingCountEcharts(param);
		}

		Collections.sort(orgMonthUseTakingCountList, new Comparator<OrgMonthUseTakingCount>() {
			@Override
			public int compare(OrgMonthUseTakingCount bean1, OrgMonthUseTakingCount bean2) {
				return (int) (bean2.getUseTaking() - bean1.getUseTaking());
			}
		});

		if (orgMonthUseTakingCountList.size() <= 10) {
			for (int i = 0; i < orgMonthUseTakingCountList.size(); i++) {
				OrgMonthUseTakingCount bean = orgMonthUseTakingCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName());
				resultparam.put("value", bean.getUseTaking());
				resultparam.put("loginTakingStr", bean.getUseTakingStr());
				resultDate.add(resultparam);

			}
		} else {
			for (int i = 0; i < 10; i++) {
				OrgMonthUseTakingCount bean = orgMonthUseTakingCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName());
				resultparam.put("value", bean.getUseTaking());
				resultparam.put("loginTakingStr", bean.getUseTakingStr());
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
	 * 用户统计图表
	 */
	@RequestMapping(value = "/getUserUseTakingChartsData")
	public void getUserActivityChartsData(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		String provinceCode = request.getParameter("province");
		String cityCode = request.getParameter("city");
		String orgId = request.getParameter("org");

		List<UserMonthUseTakingCount> userMonthUseTakingCountList = new ArrayList<UserMonthUseTakingCount>();

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
			userMonthUseTakingCountList = countUseTakingService.getUserMonthUseTakingCountEcharts(param);
		} else if (orgId != null && StringUtils.isNotEmpty(orgId)) {
			param.put("orgId", orgId);
			userMonthUseTakingCountList = countUseTakingService.getUserMonthUseTakingCountEcharts(param);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {
			param.put("cityCode", cityCode);
			userMonthUseTakingCountList = countUseTakingService.getUserMonthUseTakingCountEcharts(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			userMonthUseTakingCountList = countUseTakingService.getUserMonthUseTakingCountEcharts(param);
		}

		Collections.sort(userMonthUseTakingCountList, new Comparator<UserMonthUseTakingCount>() {
			@Override
			public int compare(UserMonthUseTakingCount bean1, UserMonthUseTakingCount bean2) {
				return (int) (bean2.getUseTaking() - bean1.getUseTaking());
			}
		});

		if (userMonthUseTakingCountList.size() <= 10) {
			for (int i = 0; i < userMonthUseTakingCountList.size(); i++) {
				UserMonthUseTakingCount bean = userMonthUseTakingCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName() + " " + bean.getUserName());
				resultparam.put("value", bean.getUseTaking());
				resultDate.add(resultparam);

			}
		} else {
			for (int i = 0; i < 10; i++) {
				UserMonthUseTakingCount bean = userMonthUseTakingCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName() + " " + bean.getUserName());
				resultparam.put("value", bean.getUseTaking());
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

}
