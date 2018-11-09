package com.whty.assis.demo.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigdata.assis.manage.model.COUCount;
import com.bigdata.assis.manage.model.HomeCount;
import com.bigdata.assis.manage.service.CountCOUService;
import com.bigdata.assis.manage.service.CountHomeService;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.WidgetHistory;
import com.whty.assis.demo.service.AreaService;
import com.whty.assis.demo.service.CountWidgetService;
import com.whty.common.excel.PoiUtil;
import com.whty.common.util.BigDecimalUtils;
import com.whty.common.util.TimeUtil;
import com.whty.common.util.TimeUtils;
import com.whty.page.PageContext;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/manage/countHome")
public class CountHomeController extends BaseController {

	@Autowired
	private AreaService areaService;

	@Autowired
	private CountCOUService countCOUService;

	@Autowired
	private CountHomeService countHomeService;

	/**
	 * 统计首页
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model, String provinceCode, String cityCode) throws Exception {
		Map<String, Object> paramMap = this.getParameter(request);

		// 查询省列表
		Map<String, Object> areaMap = new HashMap<String, Object>();
		areaMap.put("levelId", 1);
		List<Map> provinceList = areaService.queryArea(areaMap);
		model.addAttribute("provinceList", JSONArray.fromObject(provinceList));

		// //查询市列表
		if (paramMap.get("PROV_CODE") != null && StringUtils.isNotEmpty(paramMap.get("PROV_CODE").toString())) {
			areaMap.put("parentId", paramMap.get("PROV_CODE").toString());
			areaMap.put("levelId", 2);
			List<Map> cityList = areaService.queryArea(areaMap);
			model.addAttribute("cityList", JSONArray.fromObject(cityList));
		}

		// PageContext page = PageContext.getContext();
		// page.setPagination(false);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = simpleDateFormat.format(new Date());
		model.addAttribute("currentTime", currentTime);
		// page.setPagination(true);

		Map<String, Object> param = new HashMap<String, Object>();
		HomeCount currentBasicIndexes = new HomeCount();
		HomeCount lastBasicIndexes = new HomeCount();
		if (paramMap.get("PROV_CODE") != null) {
			param.put("PROV_CODE", paramMap.get("PROV_CODE"));
			if (paramMap.get("CITY_CODE") != null) {
				param.put("CITY_CODE", paramMap.get("CITY_CODE"));
			}
			// 省市区域统计数据
			// COUCount cou = countCOUService.getCityHomeCount(param);
			// model.addAttribute("couList",cou);

			// 省市基本指标
			List<HomeCount> list = countHomeService.getCityBasicIndexes(param);

			if (list.size() > 0) {
				currentBasicIndexes.setADD_LOGIN_NEW_NUM(list.get(0).getADD_LOGIN_NEW_NUM());
				currentBasicIndexes.setADD_LOGIN_USER_NUM(list.get(0).getADD_LOGIN_USER_NUM());
				currentBasicIndexes.setALL_LOGIN_USER_NUM(list.get(0).getALL_LOGIN_USER_NUM());
				currentBasicIndexes.setADD_LOGIN_NUM(list.get(0).getADD_LOGIN_NUM());
				currentBasicIndexes.setALL_LOGIN_NUM(list.get(0).getALL_LOGIN_NUM());
				currentBasicIndexes.setADD_LOGIN_VALID_NUM(list.get(0).getADD_LOGIN_VALID_NUM());
				currentBasicIndexes.setALL_LOGIN_VALID_NUM(list.get(0).getALL_LOGIN_VALID_NUM());
				currentBasicIndexes.setADD_LOGIN_COUR_NUM(list.get(0).getADD_LOGIN_COUR_NUM());
				currentBasicIndexes.setALL_LOGIN_COUR_NUM(list.get(0).getALL_LOGIN_COUR_NUM());

				lastBasicIndexes.setADD_LOGIN_NEW_NUM(list.get(1).getADD_LOGIN_NEW_NUM());
				lastBasicIndexes.setADD_LOGIN_USER_NUM(list.get(1).getADD_LOGIN_USER_NUM());
				lastBasicIndexes.setALL_LOGIN_USER_NUM(list.get(1).getALL_LOGIN_USER_NUM());
				lastBasicIndexes.setADD_LOGIN_NUM(list.get(1).getADD_LOGIN_NUM());
				lastBasicIndexes.setALL_LOGIN_NUM(list.get(1).getALL_LOGIN_NUM());
				lastBasicIndexes.setADD_LOGIN_VALID_NUM(list.get(1).getADD_LOGIN_VALID_NUM());
				lastBasicIndexes.setALL_LOGIN_VALID_NUM(list.get(1).getALL_LOGIN_VALID_NUM());
				lastBasicIndexes.setADD_LOGIN_COUR_NUM(list.get(1).getADD_LOGIN_COUR_NUM());
				lastBasicIndexes.setALL_LOGIN_COUR_NUM(list.get(1).getALL_LOGIN_COUR_NUM());
			}

		} else {
			// 全部区域统计数据
			COUCount cou = countCOUService.getHomeCount();
			model.addAttribute("couList", cou);
			// 基本指标
			List<HomeCount> list = countHomeService.getBasicIndexes();
			if (list.size() > 0) {
				currentBasicIndexes.setADD_LOGIN_NEW_NUM(list.get(0).getADD_LOGIN_NEW_NUM());
				currentBasicIndexes.setADD_LOGIN_USER_NUM(list.get(0).getADD_LOGIN_USER_NUM());
				currentBasicIndexes.setALL_LOGIN_USER_NUM(list.get(0).getALL_LOGIN_USER_NUM());
				currentBasicIndexes.setADD_LOGIN_NUM(list.get(0).getADD_LOGIN_NUM());
				currentBasicIndexes.setALL_LOGIN_NUM(list.get(0).getALL_LOGIN_NUM());
				currentBasicIndexes.setADD_LOGIN_VALID_NUM(list.get(0).getADD_LOGIN_VALID_NUM());
				currentBasicIndexes.setALL_LOGIN_VALID_NUM(list.get(0).getALL_LOGIN_VALID_NUM());
				currentBasicIndexes.setADD_LOGIN_COUR_NUM(list.get(0).getADD_LOGIN_COUR_NUM());
				currentBasicIndexes.setALL_LOGIN_COUR_NUM(list.get(0).getALL_LOGIN_COUR_NUM());

				lastBasicIndexes.setADD_LOGIN_NEW_NUM(list.get(1).getADD_LOGIN_NEW_NUM());
				lastBasicIndexes.setADD_LOGIN_USER_NUM(list.get(1).getADD_LOGIN_USER_NUM());
				lastBasicIndexes.setALL_LOGIN_USER_NUM(list.get(1).getALL_LOGIN_USER_NUM());
				lastBasicIndexes.setADD_LOGIN_NUM(list.get(1).getADD_LOGIN_NUM());
				lastBasicIndexes.setALL_LOGIN_NUM(list.get(1).getALL_LOGIN_NUM());
				lastBasicIndexes.setADD_LOGIN_VALID_NUM(list.get(1).getADD_LOGIN_VALID_NUM());
				lastBasicIndexes.setALL_LOGIN_VALID_NUM(list.get(1).getALL_LOGIN_VALID_NUM());
				lastBasicIndexes.setADD_LOGIN_COUR_NUM(list.get(1).getADD_LOGIN_COUR_NUM());
				lastBasicIndexes.setALL_LOGIN_COUR_NUM(list.get(1).getALL_LOGIN_COUR_NUM());
			}

		}

		model.addAttribute("currentBasicIndexes", currentBasicIndexes);
		model.addAttribute("lastBasicIndexes", lastBasicIndexes);
		model.addAllAttributes(paramMap);
		model.addAttribute("PROV_CODE", provinceCode);
		model.addAttribute("CITY_CODE", cityCode);
		return "countHome/list";
	}

	private Map<String, Object> getParameter(HttpServletRequest request) {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (request.getParameter("provinceCode") != null
				&& StringUtils.isNotEmpty(request.getParameter("provinceCode"))) {
			String provinceCode = request.getParameter("provinceCode");
			paraMap.put("PROV_CODE", provinceCode);
			paraMap.put("areaid", provinceCode.substring(0, 2));
		}
		if (request.getParameter("cityCode") != null && StringUtils.isNotEmpty(request.getParameter("cityCode"))) {
			String cityCode = request.getParameter("cityCode");
			paraMap.put("CITY_CODE", cityCode);
			paraMap.put("areaid", cityCode.substring(0, 4));

		}

		if (request.getParameter("start_time") != null && StringUtils.isNotEmpty(request.getParameter("start_time"))) {
			paraMap.put("start_time", request.getParameter("start_time"));
		}
		return paraMap;
	}

	/**
	 * 各项指标数据统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getChart")
	public void getChart(HttpServletRequest request, HttpServletResponse response, String basicId, String dateId,
			Date endTime, String PROV_CODE, String CITY_CODE) throws Exception {
		Map<String, Object> paramMap = this.getParameter(request);
		List<HomeCount> countIndexList = new ArrayList<HomeCount>();
		Map<String, Object> param = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String end_time = sdf.format(endTime);
		int beforeDays = 0;
		if (PROV_CODE != "") {
			param.put("PROV_CODE", PROV_CODE);
			param.put("CITY_CODE", CITY_CODE);
			if (dateId.equals("d1")) {
				countIndexList = countHomeService.getCityIndexesMonthCount(param);
			} else {
				if (dateId.equals("d2")) {
					beforeDays = 7;
				}
				if (dateId.equals("d3")) {
					beforeDays = 15;
				}
				if (dateId.equals("d4")) {
					beforeDays = 30;
				}
				String start_time = TimeUtil.getDate(end_time, beforeDays);
				param.put("start_time", start_time);
				param.put("end_time", end_time);
				countIndexList = countHomeService.getCityIndexesDateCount(param);
			}
		} else {
			if (dateId.equals("d1")) {
				countIndexList = countHomeService.getAllIndexesMonthCount(param);
			} else {
				if (dateId.equals("d2")) {
					beforeDays = 7;
				}
				if (dateId.equals("d3")) {
					beforeDays = 15;
				}
				if (dateId.equals("d4")) {
					beforeDays = 30;
				}
				String start_time = TimeUtil.getDate(end_time, beforeDays);
				param.put("start_time", start_time);
				param.put("end_time", end_time);
				countIndexList = countHomeService.getAllIndexesDateCount(param);
			}
		}
		for (HomeCount homeCount : countIndexList) {
			homeCount.setCreateTime(homeCount.getOP_TIME());
		}

		JSONArray json = JSONArray.fromObject(countIndexList);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();

	}

	/**
	 * 有效课程6项指标数据统计
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getChart2")
	public void getChart2(HttpServletRequest request, HttpServletResponse response, String dateId, Date endTime,
			String PROV_CODE, String CITY_CODE) throws Exception {
		Map<String, Object> paramMap = this.getParameter(request);
		List<HomeCount> countIndexList = new ArrayList<HomeCount>();
		Map<String, Object> param = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String end_time = sdf.format(endTime);
		int beforeDays = 0;
		if (PROV_CODE != "") {
			param.put("PROV_CODE", PROV_CODE);
			param.put("CITY_CODE", CITY_CODE);
			if (dateId.equals("d1")) {
				countIndexList = countHomeService.getCityValidIndexesMonthCount(param);
			} else {
				if (dateId.equals("d2")) {
					beforeDays = 7;
				}
				if (dateId.equals("d3")) {
					beforeDays = 15;
				}
				if (dateId.equals("d4")) {
					beforeDays = 30;
				}
				String start_time = TimeUtil.getDate(end_time, beforeDays);
				param.put("start_time", start_time);
				param.put("end_time", end_time);
				countIndexList = countHomeService.getCityValidIndexesDateCount(param);
			}
		} else {
			if (dateId.equals("d1")) {
				countIndexList = countHomeService.getAllValidIndexesMonthCount(param);
			} else {
				if (dateId.equals("d2")) {
					beforeDays = 7;
				}
				if (dateId.equals("d3")) {
					beforeDays = 15;
				}
				if (dateId.equals("d4")) {
					beforeDays = 30;
				}
				String start_time = TimeUtil.getDate(end_time, beforeDays);
				param.put("start_time", start_time);
				param.put("end_time", end_time);
				countIndexList = countHomeService.getAllValidIndexesDateCount(param);
			}
		}
		if (countIndexList != null && countIndexList.size() > 0) {
			for (HomeCount homeCount : countIndexList) {
				homeCount.setCreateTime(homeCount.getOP_TIME());
			}
		}

		JSONArray json = JSONArray.fromObject(countIndexList);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();

	}
}
