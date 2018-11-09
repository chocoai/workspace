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

import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthUsageCount;
import com.whty.assis.demo.model.UserMonthUsageCount;
import com.whty.assis.demo.service.AreaService;
import com.whty.assis.demo.service.UseCountService;
import com.whty.common.cache.data.GetCacheBaseData;
import com.whty.common.util.BigDecimalUtils;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 使用次数统计
 */
@Controller
@RequestMapping("/manage/useCount")
public class UseCountController extends BaseController {

	@Autowired
	private UseCountService useCountService;

	@Autowired
	private AreaService areaService;

	/**
	 * 跳转到页面
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model) throws Exception {

		return "useCount/index";
	}

	@RequestMapping(value = "/userInfo")
	public void userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String userId = request.getParameter("userId");
		String orgId = request.getParameter("orgId");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("orgId", orgId);

		Map<String, Object> sss = useCountService.getPlatformCode(param);
		String platformCode = sss.get("PLATFORM_CODE").toString();

		param.put("platformCode", platformCode);
		Map<String, Object> taUserInfoMap = useCountService.getTaUserInfo(param);

		String requestUrl = GetCacheBaseData.getPropertyValue("platform_url", platformCode) + "/user/" + userId;

		String json = HttpUtils.getInstance().httpGet(requestUrl);

		JSONObject result = JSONObject.fromObject(json);
		if ("000000".equals(result.get("result"))) {
			JSONObject userInfoJson = result.optJSONObject("userinfo");
			resultMap.put("organame", userInfoJson.optString("organame"));
			resultMap.put("provicename", userInfoJson.optString("provicename"));
			resultMap.put("cityname", userInfoJson.optString("cityname"));
			resultMap.put("platformName", userInfoJson.optString("platformName"));
			resultMap.put("regiTime", userInfoJson.optString("regiTime").toString());
			resultMap.put("mobnum", userInfoJson.optString("mobnum"));
			resultMap.put("email", userInfoJson.optString("email"));
			resultMap.put("name", userInfoJson.optString("name"));
			resultMap.put("gender", userInfoJson.optString("gender"));
			resultMap.put("birthday", userInfoJson.optString("birthday"));
			resultMap.put("address", userInfoJson.optString("address"));
			resultMap.put("nickname", userInfoJson.optString("nickname"));
			resultMap.put("userAccount", taUserInfoMap.get("USER_ACCOUNT"));
			resultMap.put("loginCount", taUserInfoMap.get("LOGIN_COUNT"));
			resultMap.put("lastTime", taUserInfoMap.get("LAST_TIME").toString());
		}
		printJson(response, resultMap);
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
		List<Map> list = useCountService.getOrgListByAreaCode(areaCode);

		Map resultMap = new HashMap();
		resultMap.put("list", list);
		printJson(response, resultMap);
	}

	/**
	 * 区域统计页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/areaCount")
	public String areaCount(HttpServletRequest request, HttpServletResponse response, Model model) {
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
		useCountService.addCountDataToAttribute(model);

		useCountService.addAreaCountDataToAttribute(model, paramMap);

		page.setPagination(true);
		// HandlerResult handlerResult =
		// useCountService.queryAreaMonthUsageCount(paramMap);
		String time = new StringBuffer().append(year).append("/").append(month).toString();
		HandlerResult currentHandlerResult = useCountService.queryAreaMonthUsageCountDataByArea(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		page.setPagination(false);
		HandlerResult preHandlerResult = useCountService.queryAreaMonthUsageCountDataByArea(paramMap);

		List<AreaMonthUsageCount> currentList = currentHandlerResult.getResultList();
		List<AreaMonthUsageCount> preList = preHandlerResult.getResultList();
		List<AreaMonthUsageCount> list = new ArrayList<AreaMonthUsageCount>();
		for (AreaMonthUsageCount currentBean : currentList) {
			AreaMonthUsageCount bean = new AreaMonthUsageCount();
			bean.setAreaCode(currentBean.getAreaCode());
			bean.setAreaName(currentBean.getAreaName());
			bean.setUseCount(currentBean.getUseCount());
			bean.setOrgUseRanking(currentBean.getOrgUseRanking());
			for (AreaMonthUsageCount preBean : preList) {
				if (preBean.getAreaCode().equals(currentBean.getAreaCode())) {// 比较上月数据
					bean.setPreviousUseCount(preBean.getUseCount());
					int rateNum = bean.getUseCount() - bean.getPreviousUseCount();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseCount());
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
		return "useCount/areaCount";
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

		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));
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

		// 默认当月的数据
		paramMap.put("year", year);
		paramMap.put("month", month);

		if (paramMap.get("orgName") != null && !"".equals(paramMap.get("orgName").toString().trim())) {
			paramMap.put("orgName", paramMap.get("orgName"));
			model.addAttribute("orgName");
		}

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
		useCountService.addCountDataToAttribute(model);
		useCountService.addOrgCountDataToAttribute(model, paramMap);

		page.setPagination(true);

		HandlerResult handlerResult = useCountService.getOrgCountDataGroupByOrg(paramMap);
		page.setPagination(false);

		String time = new StringBuffer().append(year).append("/").append(month).toString();
		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		page.setPagination(false);
		HandlerResult preHandlerResult = useCountService.getOrgCountDataGroupByOrg(paramMap);

		List<OrgMonthUsageCount> currentList = handlerResult.getResultList();
		List<OrgMonthUsageCount> preList = preHandlerResult.getResultList();

		List<OrgMonthUsageCount> list = new ArrayList<OrgMonthUsageCount>();

		for (OrgMonthUsageCount currentBean : currentList) {
			OrgMonthUsageCount bean = new OrgMonthUsageCount();
			bean.setOrgId(currentBean.getOrgId());
			bean.setOrgName(currentBean.getOrgName());
			bean.setAreaName(currentBean.getAreaName());
			bean.setUseCount(currentBean.getUseCount());
			bean.setPlatformName(time);

			for (OrgMonthUsageCount preBean : preList) {
				if (preBean.getOrgId().equals(currentBean.getOrgId())) {// 比较上月数据
					bean.setPreviousUseCount(preBean.getUseCount());
					int rateNum = bean.getUseCount() - bean.getPreviousUseCount();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseCount());
					String rate = BigDecimalUtils.calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
			}
			list.add(bean);
		}

		model.addAttribute("orgCountList", list);
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		model.addAttribute("time", time);
		return "useCount/orgCount";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/userCount")
	public String userCount(HttpServletRequest request, HttpServletResponse response, Model model) {

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

		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));
		}

		// 查询学校
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			List<Map> list = useCountService.getOrgListByAreaCode(paramMap.get("cityCode").toString());
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
		useCountService.addCountDataToAttribute(model);
		useCountService.addUserCountDataToAttribute(model, paramMap);

		page.setPagination(true);

		// if(paramMap.get("cityCode")!=null){
		// paramMap.remove("provinceCode");
		// }

		String requestUrl = GetCacheBaseData.getPropertyValue("platform_url", "330621");

		HandlerResult currentHandlerResult = useCountService.queryUserCountDataGroupByUser(paramMap);

		page.setPagination(false);

		String time = new StringBuffer().append(year).append("/").append(month).toString();
		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		page.setPagination(false);
		HandlerResult preHandlerResult = useCountService.queryUserCountDataGroupByUser(paramMap);

		List<UserMonthUsageCount> currentList = currentHandlerResult.getResultList();

		List<UserMonthUsageCount> preList = preHandlerResult.getResultList();

		List<UserMonthUsageCount> list = new ArrayList<UserMonthUsageCount>();

		for (UserMonthUsageCount currentBean : currentList) {
			UserMonthUsageCount bean = new UserMonthUsageCount();
			bean.setUserId(currentBean.getUserId());
			bean.setOrgId(currentBean.getOrgId());
			bean.setOrgName(currentBean.getOrgName());
			bean.setAreaName(currentBean.getAreaName());
			bean.setUseCount(currentBean.getUseCount());
			bean.setPlatformName(time);
			bean.setUserName(currentBean.getUserName());

			for (UserMonthUsageCount preBean : preList) {
				if (preBean.getUserId().equals(currentBean.getUserId())) {// 比较上月数据
					bean.setPreviousUseCount(preBean.getUseCount());
					int rateNum = bean.getUseCount() - bean.getPreviousUseCount();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseCount());
					String rate = BigDecimalUtils.calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
			}
			list.add(bean);
		}

		model.addAttribute("userCountList", list);
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		model.addAttribute("time", time);

		return "useCount/userCount";
	}

	// @RequestMapping(value = "/exportOrgUseCountExcel")
	// public void exportOrgUseCountExcel(HttpServletRequest request,
	// HttpServletResponse response) {
	// try {
	// Map<String, Object> paramMap = this.getParameter(request);
	// // 表头
	// String[] tableHeader = { "机构名称|orgName", "区域|areaName", "用户名|userName",
	// "平台|platformName", "增长率|rate",
	// "上月使用次数|previousUseCount", "使用次数|useCount", "年|year", "月|month" };
	// // 文件名
	// String filename = "学校月使用次数.xls";
	//
	// Map cmap = null;
	// List reportList = new ArrayList();
	//
	// List<OrgMonthUsageCount> list =
	// useCountService.getOrgMonthUsageCount(paramMap);
	//
	// // 填充
	// for (int i = 0; i < list.size(); i++) {
	// cmap = new HashMap();
	// OrgMonthUsageCount bean = list.get(i);
	// cmap.put("orgName", bean.getOrgName());
	//
	// cmap.put("rate", bean.getRate());
	// cmap.put("previousUseCount", bean.getPreviousUseCount());
	// cmap.put("useCount", bean.getUseCount());
	// cmap.put("year", bean.getYear());
	// cmap.put("month", bean.getMonth());
	// cmap.put("areaName", bean.getAreaName());
	// reportList.add(cmap);
	// }
	//
	// String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
	//
	// ServletOutputStream os = null;
	// response.setContentType("application/vnd.ms-excel");
	// response.addHeader("Content-disposition", "attachment" + ";filename=" +
	// fileName);
	// os = response.getOutputStream();
	// HSSFWorkbook wb = new HSSFWorkbook();
	// PoiUtil.createExcelSheet(wb, tableHeader, reportList);
	// wb.write(os);
	// os.flush();
	// os.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 导出区域月报表
	 * 
	 * @param request
	 * @param response
	 */
	// @RequestMapping(value = "/exportAreaUseCountExcel")
	// public void exportAreaUseCountExcel(HttpServletRequest request,
	// HttpServletResponse response) {
	// try {
	// Map<String, Object> paramMap = this.getParameter(request);
	//
	// // 表头
	// String[] tableHeader = { "机构名称|orgName", "区域名称|areaName",
	// "使用次数|useCount", "年|year", "月|month", "增长率|rate",
	// "上月使用次数|previousUseCount", "平台|platformName" };
	// // 文件名
	// String filename = "区域月使用次数.xls";
	//
	// // HandlerResult handlerResult =
	// // digitalPenLicenceService.queryDigitalPenLicencePage(paramMap);
	// Map cmap = null;
	// List reportList = new ArrayList();
	// // DigitalPenLicence digitalPenLicence = null;
	//
	// List<AreaMonthUsageCount> list =
	// useCountService.getAreaMonthUsageCount(paramMap);
	//
	// // 填充
	// for (int i = 0; i < list.size(); i++) {
	// cmap = new HashMap();
	// AreaMonthUsageCount bean = list.get(i);
	// cmap.put("orgName", bean.getOrgName());
	// cmap.put("areaName", bean.getAreaName());
	// cmap.put("useCount", bean.getUseCount());
	// cmap.put("year", bean.getYear());
	// cmap.put("month", bean.getMonth());
	// cmap.put("rate", bean.getRate());
	// cmap.put("previousUseCount", bean.getPreviousUseCount());
	//
	// reportList.add(cmap);
	// }
	//
	// String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
	//
	// ServletOutputStream os = null;
	// response.setContentType("application/vnd.ms-excel");
	// response.addHeader("Content-disposition", "attachment" + ";filename=" +
	// fileName);
	// os = response.getOutputStream();
	// HSSFWorkbook wb = new HSSFWorkbook();
	// PoiUtil.createExcelSheet(wb, tableHeader, reportList);
	// wb.write(os);
	// os.flush();
	// os.close();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

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
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/areaList")
	public String areaList(HttpServletRequest request, Model model) throws Exception {

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

		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));
		}

		if (paramMap.get("orgName") != null && StringUtils.isNotEmpty(paramMap.get("orgName").toString())) {
			model.addAttribute("orgName", paramMap.get("orgName").toString());
		}

		if (paramMap.get("start_time") != null && !"".equals(paramMap.get("start_time"))) {
			String start_time = (String) paramMap.get("start_time");

			int year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			int month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			paramMap.put("year", year);
			paramMap.put("month", month);
			model.addAttribute("start_time");
		}

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
		page.setPagination(true);
		HandlerResult handlerResult = useCountService.queryAreaMonthUsageCount(paramMap);

		model.addAttribute("useCountList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);
		return "useCount/areaList";
	}

	/**
	 * 按学校统计
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/orgList")
	public String orgList(HttpServletRequest request, Model model) throws Exception {

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

		// //查询区列表
		if (paramMap.get("cityCode") != null && StringUtils.isNotEmpty(paramMap.get("cityCode").toString())) {
			areaMap.put("parentId", paramMap.get("cityCode").toString());
			areaMap.put("levelId", 3);
			List<Map> areaList = areaService.queryArea(areaMap);
			model.addAttribute("areaList", JSONArray.fromObject(areaList));
		}

		if (paramMap.get("orgName") != null && StringUtils.isNotEmpty(paramMap.get("orgName").toString())) {
			model.addAttribute("orgName", paramMap.get("orgName").toString());
		}

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
		page.setPagination(true);
		HandlerResult handlerResult = useCountService.queryOrgMonthUsageCount(paramMap);

		model.addAttribute("useCountList", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		page.setPagination(false);

		return "useCount/orgList";
	}

	/**
	 * 区域统计图表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAreaCounteLgendData")
	public void getAreaCountLegendData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String provinceCode = request.getParameter("province");

		List<AreaMonthUsageCount> areaMonthUsageCountList = new ArrayList<AreaMonthUsageCount>();

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

		if (provinceCode == null || "".equals(provinceCode)) {// 所有区域的排名
			areaMonthUsageCountList = useCountService.getAreaMonthUsageCountData(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			areaMonthUsageCountList = useCountService.getAreaMonthUsageCountData(param);
		}

		Collections.sort(areaMonthUsageCountList, new Comparator<AreaMonthUsageCount>() {
			@Override
			public int compare(AreaMonthUsageCount bean1, AreaMonthUsageCount bean2) {
				return bean2.getUseCount().compareTo(bean1.getUseCount());
			}
		});

		String[] legendData = new String[10];

		if (areaMonthUsageCountList.size() <= 10) {
			for (int i = 0; i < areaMonthUsageCountList.size(); i++) {
				AreaMonthUsageCount bean = areaMonthUsageCountList.get(i);
				legendData[i] = String.valueOf(bean.getAreaName());
			}
		} else {
			for (int i = 0; i < 10; i++) {
				AreaMonthUsageCount bean = areaMonthUsageCountList.get(i);
				legendData[i] = String.valueOf(bean.getAreaName());
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
	@RequestMapping(value = "/getAreaCountChartsData")
	public void getAreaCountChartsData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String provinceCode = request.getParameter("province");

		List<AreaMonthUsageCount> areaMonthUsageCountList = new ArrayList<AreaMonthUsageCount>();

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
			areaMonthUsageCountList = useCountService.getAreaMonthUsageCountData(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			areaMonthUsageCountList = useCountService.getAreaMonthUsageCountData(param);
		}

		Collections.sort(areaMonthUsageCountList, new Comparator<AreaMonthUsageCount>() {
			@Override
			public int compare(AreaMonthUsageCount bean1, AreaMonthUsageCount bean2) {
				return bean2.getUseCount().compareTo(bean1.getUseCount());
			}
		});

		// String[] legendData = new String[areaMonthUsageCountList.size()];

		String[] legendData = new String[10];

		if (areaMonthUsageCountList.size() <= 10) {
			for (int i = 0; i < areaMonthUsageCountList.size(); i++) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				AreaMonthUsageCount bean = areaMonthUsageCountList.get(i);
				legendData[i] = bean.getAreaName();
				dataMap.put("name", bean.getAreaName());
				dataMap.put("value", bean.getUseCount().toString());
				data.add(dataMap);

			}
		} else {
			for (int i = 0; i < 10; i++) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				AreaMonthUsageCount bean = areaMonthUsageCountList.get(i);
				legendData[i] = bean.getAreaName();
				dataMap.put("name", bean.getAreaName());
				dataMap.put("value", bean.getUseCount().toString());
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
	@RequestMapping(value = "/getOrgCountChartsData")
	public void getOrgCountChartsData(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String provinceCode = request.getParameter("province");
		String cityCode = request.getParameter("city");
		String orgName = request.getParameter("orgName");

		List<OrgMonthUsageCount> orgMonthUsageCountList = new ArrayList<OrgMonthUsageCount>();

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
			orgMonthUsageCountList = useCountService.getOrgMonthUsageCountEcharts(param);
		} else if (provinceCode == null || "".equals(provinceCode)) {
			orgMonthUsageCountList = useCountService.getOrgMonthUsageCountEcharts(param);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {
			param.put("cityCode", cityCode);
			orgMonthUsageCountList = useCountService.getOrgMonthUsageCountEcharts(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			orgMonthUsageCountList = useCountService.getOrgMonthUsageCountEcharts(param);
		}

		Collections.sort(orgMonthUsageCountList, new Comparator<OrgMonthUsageCount>() {
			@Override
			public int compare(OrgMonthUsageCount bean1, OrgMonthUsageCount bean2) {
				return bean2.getUseCount().compareTo(bean1.getUseCount());
			}
		});

		if (orgMonthUsageCountList.size() <= 10) {
			for (int i = 0; i < orgMonthUsageCountList.size(); i++) {
				OrgMonthUsageCount bean = orgMonthUsageCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName());
				resultparam.put("value", bean.getUseCount());
				resultDate.add(resultparam);

			}
		} else {
			for (int i = 0; i < 10; i++) {
				OrgMonthUsageCount bean = orgMonthUsageCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName());
				resultparam.put("value", bean.getUseCount());
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
	@RequestMapping(value = "/getUseCountChartsData")
	public void getUseCountChartsData(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		Map<String, Object> paramMap = this.getParameter(request);
		String provinceCode = request.getParameter("province");
		String cityCode = request.getParameter("city");
		String orgId = request.getParameter("org");

		List<UserMonthUsageCount> userMonthUsageCountList = new ArrayList<UserMonthUsageCount>();

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
			userMonthUsageCountList = useCountService.getUserMonthUsageCountChartsData(param);
		} else if (orgId != null && StringUtils.isNotEmpty(orgId)) {
			param.put("orgId", orgId);
			userMonthUsageCountList = useCountService.getUserMonthUsageCountChartsData(param);
		} else if (cityCode != null && StringUtils.isNotEmpty(cityCode)) {
			param.put("cityCode", cityCode);
			userMonthUsageCountList = useCountService.getUserMonthUsageCountChartsData(param);
		} else if (provinceCode != null && StringUtils.isNotEmpty(provinceCode)) {// 本省份下所有区域的排名
			param.put("provinceCode", provinceCode);
			userMonthUsageCountList = useCountService.getUserMonthUsageCountChartsData(param);
		}

		Collections.sort(userMonthUsageCountList, new Comparator<UserMonthUsageCount>() {
			@Override
			public int compare(UserMonthUsageCount bean1, UserMonthUsageCount bean2) {
				return bean2.getUseCount().compareTo(bean1.getUseCount());
			}
		});

		if (userMonthUsageCountList.size() <= 10) {
			for (int i = 0; i < userMonthUsageCountList.size(); i++) {
				UserMonthUsageCount bean = userMonthUsageCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName() + " " + bean.getUserName());
				resultparam.put("value", bean.getUseCount());
				resultDate.add(resultparam);

			}
		} else {
			for (int i = 0; i < 10; i++) {
				UserMonthUsageCount bean = userMonthUsageCountList.get(i);
				Map<String, Object> resultparam = new HashMap<String, Object>();
				resultparam.put("name", bean.getOrgName() + " " + bean.getUserName());
				resultparam.put("value", bean.getUseCount());
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

	@RequestMapping(value = "/exportOrgCountExcel")
	public void exportOrgCountExcel(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		HSSFWorkbook wb = useCountService.exportOrgCountExcel(request, response);
		ServletOutputStream os = null;

		String filename = "学校使用次数统计";
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");

		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

	@RequestMapping(value = "/exportUserCountExcel")
	public void exportUserCountExcel(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		HSSFWorkbook wb = useCountService.exportUserCountExcel(request, response);

		ServletOutputStream os = null;

		String filename = "用户使用次数统计";
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();

	}

	@RequestMapping(value = "/exportAreaCountExcel")
	public void exportAreaCountExcel(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		HSSFWorkbook wb = useCountService.exportAreaCountExcel(request, response);

		ServletOutputStream os = null;

		String filename = "区域使用次数统计";
		String fileName = new String(filename.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

}
