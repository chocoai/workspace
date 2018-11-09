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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.UserMonthUsageCount;
import com.whty.assis.demo.model.WidgetHistory;
import com.whty.assis.demo.service.AreaService;
import com.whty.assis.demo.service.WidgetHistoryService;
import com.whty.common.excel.PoiUtil;
import com.whty.common.util.BigDecimalUtils;
import com.whty.page.PageContext;

import net.sf.json.JSONArray;

/**
 * 功能使用统计
 * 
 * @author zhangzheng
 *
 */
@Controller
@RequestMapping("/manage/widgetCount")
public class WidgetCountController extends BaseController {

	@Autowired
	private AreaService areaService;

	@Autowired
	private WidgetHistoryService widgetHistoryService;

	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String org = request.getParameter("org");
		String start_time = request.getParameter("start_time");
		if (province != null && !"".equals(province)) {
			paramMap.put("provinceCode", province);
		}

		if (city != null && !"".equals(city)) {
			paramMap.put("cityCode", city);
		}

		if (org != null && !"".equals(org)) {
			paramMap.put("orgId", org);
		}

		List<WidgetHistory> currentMonthWidgetHistoryList = new ArrayList<WidgetHistory>();
		List<WidgetHistory> lastMonthWidgetHistoryList = new ArrayList<WidgetHistory>();
		// 查询条件 区域查询

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// 上月数据
		cal.add(Calendar.MONTH, -1);
		int preYear = cal.get(Calendar.YEAR);
		int preMonth = cal.get(Calendar.MONTH) + 1;// 上月

		if (start_time != null && !"".equals(start_time)) {
			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONDAY, month - 1);
			cal.add(Calendar.MONTH, -1);
			preYear = cal.get(Calendar.YEAR);
			preMonth = cal.get(Calendar.MONTH) + 1;// 上月

		}

		if (paramMap.get("provinceCode") == null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			currentMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 找到所有区域的统计

			param.put("year", preYear);
			param.put("month", preMonth);
			lastMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);
		} else if (paramMap.get("orgId") != null && StringUtils.isNotEmpty(paramMap.get("orgId").toString())) {// 机构查询
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("areaCode", paramMap.get("cityCode"));
			param.put("orgId", paramMap.get("orgId"));
			currentMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 学校下所有用户的统计

			// 上月数据
			param.put("year", preYear);
			param.put("month", preMonth);
			// param.put("areaCode", paramMap.get("cityCode"));
			// param.put("orgId", paramMap.get("orgId"));
			lastMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 区域下所有学校的统计
		} else if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {// 查询所有学校
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("areaCode", paramMap.get("cityCode"));
			currentMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 区域下所有学校的统计

			// 上月数据
			param.put("year", preYear);
			param.put("month", preMonth);
			// param.put("areaCode", paramMap.get("cityCode"));
			lastMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 区域下所有学校的统计

		} else if (paramMap.get("provinceCode") != null
				&& StringUtils.isNotEmpty(paramMap.get("provinceCode").toString())) {
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("provinceCode", paramMap.get("provinceCode"));
			currentMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 找到所有区域的统计

			// 上月数据
			param.put("year", preYear);
			param.put("month", preMonth);
			// param.put("provinceCode", paramMap.get("provinceCode"));
			lastMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);
		}

		for (WidgetHistory currentMonth : currentMonthWidgetHistoryList) {
			for (WidgetHistory lastMonth : lastMonthWidgetHistoryList) {
				if (currentMonth.getWidgetId() != null && lastMonth.getWidgetId() != null) {
					if (currentMonth.getWidgetId().equals(lastMonth.getWidgetId())) {// 计算增长率
						currentMonth.setPreviousUseCount(lastMonth.getUseCount().toString());

						BigDecimal current = new BigDecimal(currentMonth.getUseCount());
						BigDecimal last = new BigDecimal(lastMonth.getUseCount());

						currentMonth.setRate(BigDecimalUtils.getPrettyNumber(current.subtract(last)
								.divide(last, 2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).toString())
								+ "%");

					}
				}
			}
		}
		Set<String> userSet = new HashSet<String>();

		String filename = "控件使用次数统计";
		String[] tableHeader = { "区域|widgetName", "使用次数|useCount", "上期使用次数|previousUseCount", "增长率|rate",
				"描述|description" };
		List reportList = new ArrayList();
		Map cmap = null;

		int i = 0;

		for (WidgetHistory bean : currentMonthWidgetHistoryList) {
			cmap = new HashMap();

			cmap.put("widgetName", bean.getWidgetName());
			cmap.put("useCount", bean.getUseCount());
			cmap.put("previousUseCount", bean.getPreviousUseCount());
			cmap.put("rate", bean.getRate());
			cmap.put("description", bean.getDescription());

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

	/**
	 * 查询区域下的所有学校列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrg")
	public void queryOrg(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String areaCode = request.getParameter("areaCode");
		List<Map> list = widgetHistoryService.getOrgListByAreaCode(areaCode);

		Map resultMap = new HashMap();
		resultMap.put("list", list);
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/chartsData")
	public void chatesData(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String provinceCode = request.getParameter("province");
		String cityCode = request.getParameter("city");
		String orgId = request.getParameter("org");
		String start_time = request.getParameter("start_time");
		List<WidgetHistory> widgetHistoryList = new ArrayList<WidgetHistory>();
		List<Map<String, Object>> resultDate = new ArrayList<Map<String, Object>>();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if (start_time != null && !"".equals(start_time)) {
			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("year", year);
		paramMap.put("month", month);

		if (provinceCode == null || "".equals(provinceCode)) {// 所有区域的排名
			widgetHistoryList = widgetHistoryService.getAllRanking(paramMap);
		} else if (orgId != null && StringUtils.isNotEmpty(orgId)) {// 该学校所有老师的排名
			paramMap.put("orgId", orgId);
			widgetHistoryList = widgetHistoryService.getUserRankingByOrg(paramMap);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {// 该区域下所有学校的排名
			paramMap.put("cityCode", cityCode);
			widgetHistoryList = widgetHistoryService.getOrgRankingByAreaCode(paramMap);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			paramMap.put("parentId", provinceCode);
			widgetHistoryList = widgetHistoryService.getAreaCodeRankingByProvinceCode(paramMap);
		}

		Collections.sort(widgetHistoryList, new Comparator<WidgetHistory>() {
			@Override
			public int compare(WidgetHistory bean1, WidgetHistory bean2) {
				if (bean2.getUseCount() != null && bean1.getUseCount() != null) {
					return bean2.getUseCount().compareTo(bean1.getUseCount());
				} else {
					return 0;
				}
			}
		});

		if (widgetHistoryList.size() <= 10) {
			for (WidgetHistory bean : widgetHistoryList) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("name", bean.getAreaName());
				param.put("value", bean.getUseCount());
				resultDate.add(param);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				WidgetHistory bean = widgetHistoryList.get(i);
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("name", bean.getAreaName());
				param.put("value", bean.getUseCount());
				resultDate.add(param);
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

	/**
	 * 功能使用统计页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
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

		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));
		}

		// 查询学校
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			List<Map> list = widgetHistoryService.getOrgListByAreaCode(paramMap.get("cityCode").toString());
			model.addAttribute("orgList", JSONArray.fromObject(list));
		}

		List<WidgetHistory> currentMonthWidgetHistoryList = new ArrayList<WidgetHistory>();
		List<WidgetHistory> lastMonthWidgetHistoryList = new ArrayList<WidgetHistory>();
		// 查询条件 区域查询

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		cal.add(Calendar.MONTH, -1);
		int preYear = cal.get(Calendar.YEAR);
		int preMonth = cal.get(Calendar.MONTH) + 1;

		if (paramMap.get("start_time") != null && !"".equals(paramMap.get("start_time"))) {
			String start_time = (String) paramMap.get("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);// 想要的月份减1

			cal.add(Calendar.MONTH, -1);
			preYear = cal.get(Calendar.YEAR);
			preMonth = cal.get(Calendar.MONTH) + 1;

			model.addAttribute("start_time");
		}

		if (paramMap.get("provinceCode") == null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			currentMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 找到所有区域的统计

			// 上月数据
			param.put("year", preYear);
			param.put("month", preMonth);
			lastMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);
		} else if (paramMap.get("orgId") != null && StringUtils.isNotEmpty(paramMap.get("orgId").toString())) {// 机构查询
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("areaCode", paramMap.get("cityCode"));
			param.put("orgId", paramMap.get("orgId"));
			currentMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 学校下所有用户的统计

			// 上月数据
			param.put("year", preYear);
			param.put("month", preMonth);
			param.put("areaCode", paramMap.get("cityCode"));
			param.put("orgId", paramMap.get("orgId"));
			lastMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 区域下所有学校的统计
		} else if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {// 查询所有学校
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("areaCode", paramMap.get("cityCode"));
			currentMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 区域下所有学校的统计

			// 上月数据
			param.put("year", preYear);
			param.put("month", preMonth);
			param.put("areaCode", paramMap.get("cityCode"));
			lastMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 区域下所有学校的统计

		} else if (paramMap.get("provinceCode") != null
				&& StringUtils.isNotEmpty(paramMap.get("provinceCode").toString())) {
			// 当月数据
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("year", year);
			param.put("month", month);
			param.put("provinceCode", paramMap.get("provinceCode"));
			currentMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);// 找到所有区域的统计

			// 上月数据
			param.put("year", preYear);
			param.put("month", preMonth);
			param.put("provinceCode", paramMap.get("provinceCode"));
			lastMonthWidgetHistoryList = widgetHistoryService.getWidgetHistoryCount(param);
		}

		for (WidgetHistory currentMonth : currentMonthWidgetHistoryList) {
			for (WidgetHistory lastMonth : lastMonthWidgetHistoryList) {
				if (currentMonth.getWidgetId() != null && lastMonth.getWidgetId() != null) {
					if (currentMonth.getWidgetId().equals(lastMonth.getWidgetId())) {// 计算增长率
						currentMonth.setPreviousUseCount(lastMonth.getUseCount().toString());

						BigDecimal current = new BigDecimal(currentMonth.getUseCount());
						BigDecimal last = new BigDecimal(lastMonth.getUseCount());

						currentMonth.setRate(BigDecimalUtils.getPrettyNumber(current.subtract(last)
								.divide(last, 2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).toString())
								+ "%");

					}
				}
			}
		}

		Collections.sort(currentMonthWidgetHistoryList, new Comparator<WidgetHistory>() {
			@Override
			public int compare(WidgetHistory bean1, WidgetHistory bean2) {
				if (bean2.getUseCount() != null && bean1.getUseCount() != null) {
					return bean2.getUseCount().compareTo(bean1.getUseCount());
				} else {
					return 0;
				}
			}
		});

		model.addAttribute("currentMonthWidgetHistoryList", currentMonthWidgetHistoryList);
		model.addAllAttributes(paramMap);
		return "widgetCount/list";
	}

}
