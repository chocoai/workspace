package com.whty.assis.demo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.whty.assis.demo.dao.AreaDao;
import com.whty.assis.demo.dao.UserActivityDao;
import com.whty.assis.demo.model.Area;
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.model.UserMonthActivityCount;
import com.whty.assis.demo.service.UserActivityService;
import com.whty.common.excel.PoiExcelUtils;
import com.whty.common.util.BigDecimalUtils;
import com.whty.common.util.CommonFunction;
import com.whty.page.util.HandlerResult;

@Service
public class UserActivityServiceImpl implements UserActivityService {

	@Autowired
	private UserActivityDao userActivityDao;

	@Autowired
	private AreaDao areaDao;

	@Override
	public HandlerResult queryUserMonthActivityCountPage(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(userActivityDao.queryUserMonthActivityCount(paramMap));
		return handlerResult;
	}

	@Override
	public void addCountDataToAttribute(Model model) {
		List<AreaMonthActivityCount> useCountList = userActivityDao.getCountData();
		int areaCount = 0;
		int orgCount = 0;
		int userCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				areaCount = useCountList.get(i).getLoginTaking();
			}
			if (i == 1) {
				orgCount = useCountList.get(i).getLoginTaking();
			}
			if (i == 2) {
				userCount = useCountList.get(i).getLoginTaking();
			}
		}
		model.addAttribute("areaCount", areaCount);
		model.addAttribute("orgCount", orgCount);
		model.addAttribute("userCount", userCount);

	}

	@Override
	public HandlerResult queryAreaMonthActivityCountPage(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(userActivityDao.queryAreaMonthActivityCount(paramMap));
		return handlerResult;
	}

	@Override
	public HandlerResult queryOrgMonthActivityCountPage(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(userActivityDao.queryOrgMonthActivityCount(paramMap));
		return handlerResult;
	}

	@Override
	public List<Map> getOrgListByAreaCode(String string) {
		return userActivityDao.getOrgListByAreaCode(string);
	}

	@Override
	public List<AreaMonthActivityCount> getAreaMonthActivityCount(Map<String, Object> param) {
		return userActivityDao.queryAreaMonthActivityCount(param);
	}

	@Override
	public List<OrgMonthActivityCount> getOrgMonthActivityCount(Map<String, Object> param) {
		return userActivityDao.queryOrgMonthActivityCount(param);
	}

	@Override
	public List<UserMonthActivityCount> getUserMonthActivityCount(Map<String, Object> param) {
		return userActivityDao.queryUserMonthActivityCount(param);
	}

	@Override
	public List<AreaMonthActivityCount> getAreaMonthActivityCountData(Map<String, Object> param) {
		return userActivityDao.queryUserMonthActivityCountData(param);
	}

	@Override
	public HSSFWorkbook exportOrgActivityCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String province = request.getParameter("province");
		String cityCode = request.getParameter("cityCode");
		String orgName = request.getParameter("orgName");

		String provinceName = null;
		String cityName = null;

		if (orgName != null && !"".equals(orgName)) {
			paramMap.put("orgName", orgName);
		}

		if (cityCode != null && !"".equals(cityCode)) {
			paramMap.put("cityCode", cityCode);

			Map<String, Object> areaParam = new HashMap<String, Object>();
			areaParam.put("areaCode", province);
			List<Area> list = areaDao.getArea(areaParam);
			if (list != null && list.size() == 1) {
				cityName = list.get(0).getAreaName();
			}
		} else {
			if (province != null && !"".equals(province)) {
				paramMap.put("provinceCode", province);

				Map<String, Object> areaParam = new HashMap<String, Object>();
				areaParam.put("areaCode", province);
				List<Area> list = areaDao.getArea(areaParam);

				if (list != null && list.size() == 1) {
					provinceName = list.get(0).getAreaName();
				}
			}
		}

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String time = year + "\\" + month;

		if (request.getParameter("start_time") != null && !"".equals(request.getParameter("start_time"))) {
			String start_time = request.getParameter("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);

			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;// 上月

			time = year + "\\" + month;
		}

		paramMap.put("year", year);
		paramMap.put("month", month);

		// 当月或者查询的条件的月份的数据
		// List<AreaMonthActivityCount> currentList =
		// userActivityDao.getAreaActivityDataGroupByArea(paramMap);

		List<OrgMonthActivityCount> currentList = userActivityDao.getOrgActivityDataGroupByOrg(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		List<OrgMonthActivityCount> preList = userActivityDao.getOrgActivityDataGroupByOrg(paramMap);

		List<OrgMonthActivityCount> list = new ArrayList<OrgMonthActivityCount>();

		for (OrgMonthActivityCount currentBean : currentList) {
			OrgMonthActivityCount bean = new OrgMonthActivityCount();

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

		String[] tableHeader = { "区域@areaName", "学校@orgName", "使用时长@loginTakingStr", "日期@platformName",
				"上次使用时长@previousLoginTakingStr", "增长率@rate" };

		return PoiExcelUtils.createExcel2ExportByOrgUseActivity("学校活跃度统计", provinceName, cityName, tableHeader, list,
				time);
	}

	@Override
	public HSSFWorkbook exportAreaActivityCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String province = request.getParameter("province");

		String provinceName = null;

		if (province != null && !"".equals(province)) {
			paramMap.put("provinceCode", province);

			Map<String, Object> areaParam = new HashMap<String, Object>();
			areaParam.put("areaCode", province);
			List<Area> list = areaDao.getArea(areaParam);

			if (list != null && list.size() == 1) {
				provinceName = list.get(0).getAreaName();
			}
		}

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		String time = year + "\\" + month;

		paramMap.put("year", year);
		paramMap.put("month", month);

		if (request.getParameter("start_time") != null && !"".equals(request.getParameter("start_time"))) {
			String start_time = request.getParameter("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);

			paramMap.put("year", year);
			paramMap.put("month", month);
			time = year + "\\" + month;
		}
		// 当月或者查询的条件的月份的数据
		List<AreaMonthActivityCount> currentList = userActivityDao.getAreaActivityDataGroupByArea(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		List<AreaMonthActivityCount> preList = userActivityDao.getAreaActivityDataGroupByArea(paramMap);

		List<AreaMonthActivityCount> list = new ArrayList<AreaMonthActivityCount>();

		for (AreaMonthActivityCount currentBean : currentList) {
			AreaMonthActivityCount bean = new AreaMonthActivityCount();

			bean.setOrgName(currentBean.getOrgName());
			bean.setAreaName(currentBean.getAreaName());
			bean.setLoginTaking(currentBean.getLoginTaking());
			bean.setPlatformName(time);
			bean.setOrgLoginTakingRanking(currentBean.getOrgLoginTakingRanking());

			for (AreaMonthActivityCount preBean : preList) {
				if (preBean.getAreaCode().equals(currentBean.getAreaCode())) {// 比较上月数据
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

		String[] tableHeader = { "区域@areaName", "学校@orgName", "活跃度@loginTakingStr", "日期@platformName",
				"上月活跃度@previousLoginTakingStr", "增长率@rate", "学校数量@orgLoginTakingRanking" };

		return PoiExcelUtils.createExcel2ExportByAreaUseActivity("区域活跃度统计", provinceName, tableHeader, list, time);

	}

	@Override
	public void addAreaCountDataToAttribute(Model model, Map<String, Object> paramMap) {
		List<AreaMonthActivityCount> useCountList = userActivityDao.getAreaActivityData(paramMap);
		int currentAreaCount = 0;
		long loginTotalCount = 0;
		int orgTotalCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					currentAreaCount = useCountList.get(i).getLoginTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					loginTotalCount = useCountList.get(i).getLoginTaking();
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					orgTotalCount = useCountList.get(i).getLoginTaking();
				}
			}
		}
		model.addAttribute("currentAreaCount", currentAreaCount);
		model.addAttribute("loginToalCount", CommonFunction.formatDuring(loginTotalCount));
		model.addAttribute("orgTotalCount", orgTotalCount);
	}

	@Override
	public List<OrgMonthActivityCount> getOrgMonthActivityCountEcharts(Map<String, Object> param) {
		return userActivityDao.getOrgMonthActivityCountEcharts(param);
	}

	@Override
	public void addOrgCountDataToAttribute(Model model, Map<String, Object> paramMap) {
		List<OrgMonthActivityCount> useCountList = userActivityDao.getOrgActivityData(paramMap);
		int orgNum = 0;
		long orgTakingTotal = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					orgNum = useCountList.get(i).getLoginTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					orgTakingTotal = useCountList.get(i).getLoginTaking();
				}
			}
		}
		model.addAttribute("orgNum", orgNum);
		model.addAttribute("orgTakingTotal", CommonFunction.formatDuring(orgTakingTotal));
	}

	@Override
	public HSSFWorkbook exportUserActivityCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String province = request.getParameter("province");
		String cityCode = request.getParameter("cityCode");
		String orgName = request.getParameter("orgName");

		String provinceName = null;
		String cityName = null;

		if (orgName != null && !"".equals(orgName)) {
			paramMap.put("orgName", orgName);
		}

		if (cityCode != null && !"".equals(cityCode)) {
			paramMap.put("cityCode", cityCode);

			Map<String, Object> areaParam = new HashMap<String, Object>();
			areaParam.put("areaCode", cityCode);
			List<Area> list = areaDao.getArea(areaParam);
			if (list != null && list.size() == 1) {
				cityName = list.get(0).getAreaName();
			}

			Map<String, Object> areaParam2 = new HashMap<String, Object>();
			areaParam2.put("areaCode", province);
			List<Area> list2 = areaDao.getArea(areaParam2);

			if (list2 != null && list2.size() == 1) {
				provinceName = list2.get(0).getAreaName();
			}

		} else {
			if (province != null && !"".equals(province)) {
				paramMap.put("provinceCode", province);

				Map<String, Object> areaParam = new HashMap<String, Object>();
				areaParam.put("areaCode", province);
				List<Area> list = areaDao.getArea(areaParam);

				if (list != null && list.size() == 1) {
					provinceName = list.get(0).getAreaName();
				}
			}
		}

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		String time = year + "\\" + month;

		paramMap.put("year", year);
		paramMap.put("month", month);

		if (request.getParameter("start_time") != null && !"".equals(request.getParameter("start_time"))) {
			String start_time = request.getParameter("start_time");

			year = Integer.valueOf(start_time.subSequence(0, 4).toString());
			month = Integer.valueOf(start_time.substring(5, start_time.length()).toString());

			paramMap.put("year", year);
			paramMap.put("month", month);

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);
			time = year + "\\" + month;
		}
		// 当月或者查询的条件的月份的数据
		List<UserMonthActivityCount> currentList = userActivityDao.getUserActivityDataGroupByUser(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		List<UserMonthActivityCount> preList = userActivityDao.getUserActivityDataGroupByUser(paramMap);

		List<UserMonthActivityCount> list = new ArrayList<UserMonthActivityCount>();

		for (UserMonthActivityCount currentBean : currentList) {
			UserMonthActivityCount bean = new UserMonthActivityCount();

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

		String[] tableHeader = { "区域@areaName", "学校@orgName", "老师@userName", "活跃度@loginTakingStr", "日期@platformName",
				"上月活跃度@previousLoginTakingStr", "增长率@rate" };

		return PoiExcelUtils.createExcel2ExportByUseActivity("用户活跃度统计", provinceName, cityName, orgName, tableHeader,
				list, time);
	}

	@Override
	public void addUserCountDataToAttribute(Model model, Map<String, Object> paramMap) {
		List<UserMonthActivityCount> useCountList = userActivityDao.getUserActivityData(paramMap);
		int orgNum = 0;
		long loginTakingTotal = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					orgNum = useCountList.get(i).getLoginTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					loginTakingTotal = useCountList.get(i).getLoginTaking();
				}
			}

		}
		model.addAttribute("userTotal", orgNum);
		model.addAttribute("loginTakingTotal", CommonFunction.formatDuring(loginTakingTotal));
	}

	@Override
	public List<UserMonthActivityCount> getUserMonthActivityCountEcharts(Map<String, Object> param) {
		return userActivityDao.getUserMonthActivityCountEcharts(param);
	}

	@Override
	public HandlerResult queryAreaActivityDataGroupByArea(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(userActivityDao.getAreaActivityDataGroupByArea(paramMap));
		return handlerResult;
	}

	@Override
	public HandlerResult queryOrgActivityDataGroupByOrg(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(userActivityDao.getOrgActivityDataGroupByOrg(paramMap));
		return handlerResult;
	}

	@Override
	public HandlerResult queryUserActivityDataGroupByUser(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(userActivityDao.getUserActivityDataGroupByUser(paramMap));
		return handlerResult;
	}
}
