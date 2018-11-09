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
import com.whty.assis.demo.dao.CountUseTakingDao;
import com.whty.assis.demo.dao.UseTakingCountDao;
import com.whty.assis.demo.model.Area;
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.AreaMonthUseTakingCount;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthUseTakingCount;
import com.whty.assis.demo.model.UserMonthActivityCount;
import com.whty.assis.demo.model.UserMonthUseTakingCount;
import com.whty.assis.demo.service.CountUseTakingService;
import com.whty.assis.demo.service.UseTakingCountService;
import com.whty.common.excel.PoiExcelUtils;
import com.whty.common.util.BigDecimalUtils;
import com.whty.common.util.CommonFunction;
import com.whty.page.util.HandlerResult;

@Service
public class CountUseTakingServiceImpl implements CountUseTakingService {

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private CountUseTakingDao countUseTakingDao;

	@Override
	public void addCountDataToAttribute(Model model) {
		List<AreaMonthUseTakingCount> useCountList = countUseTakingDao.getCountData();
		long areaCount = 0;
		long orgCount = 0;
		long userCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					areaCount = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					orgCount = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					userCount = useCountList.get(i).getUseTaking();
				}
			}
		}
		model.addAttribute("areaCount", areaCount);
		model.addAttribute("orgCount", orgCount);
		model.addAttribute("userCount", userCount);
	}

	@Override
	public void addAreaCountDataToAttribute(Model model, Map<String, Object> paramMap) {
		List<AreaMonthUseTakingCount> useCountList = countUseTakingDao.getAreaCountData(paramMap);
		long currentAreaCount = 0;
		Long useTakingTotalCount = null;
		long orgTotalCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					currentAreaCount = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					useTakingTotalCount = new Long(useCountList.get(i).getUseTaking());
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					orgTotalCount = useCountList.get(i).getUseTaking();
				}
			}
		}
		model.addAttribute("currentAreaCount", currentAreaCount);
		model.addAttribute("useTakingToalCount", CommonFunction.formatDuring(useTakingTotalCount));
		model.addAttribute("orgTotalCount", orgTotalCount);

	}

	@Override
	public HandlerResult queryAreaMonthUseTakingDataGroupByArea(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(countUseTakingDao.getAreaUseTakingDataGroupByArea(paramMap));
		return handlerResult;
	}

	@Override
	public void addOrgCountDataToAttribute(Model model, Map<String, Object> param) {
		List<OrgMonthUseTakingCount> useCountList = countUseTakingDao.getOrgUseTakingData(param);
		long orgNum = 0;
		Long orgTakingTotal = null;
		long userTotalCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					orgNum = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					orgTakingTotal = new Long(useCountList.get(i).getUseTaking());
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					userTotalCount = useCountList.get(i).getUseTaking();
				}
			}
		}
		model.addAttribute("orgNum", orgNum);
		model.addAttribute("orgTakingTotal", CommonFunction.formatDuring(orgTakingTotal));
		model.addAttribute("userTotalCount", userTotalCount);
	}

	@Override
	public List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountData(Map<String, Object> param) {
		return countUseTakingDao.getAreaMonthUseTakingCountData(param);
	}

	@Override
	public List<Map> getOrgListByAreaCode(Map<String, Object> param) {
		return countUseTakingDao.getOrgListByAreaCode(param);
	}

	@Override
	public void addUserCountDataToAttribute(Model model, Map<String, Object> paramMap) {
		List<UserMonthUseTakingCount> useCountList = countUseTakingDao.getUserUseTakingData(paramMap);
		long orgNum = 0;
		Long loginTakingTotal = null;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					orgNum = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					loginTakingTotal = new Long(useCountList.get(i).getUseTaking());
				}
			}

		}
		model.addAttribute("userTotal", orgNum);
		model.addAttribute("loginTakingTotal", CommonFunction.formatDuring(loginTakingTotal));
	}

	@Override
	public HandlerResult queryUserUseTakingDataGroupByUser(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(countUseTakingDao.getUserUseTakingDataGroupByUser(paramMap));
		return handlerResult;
	}

	@Override
	public HandlerResult queryOrgUseTakingDataGroupByOrg(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(countUseTakingDao.getOrgUseTakingDataGroupByOrg(paramMap));
		return handlerResult;
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
		List<AreaMonthUseTakingCount> currentList = countUseTakingDao.getAreaUseTakingDataGroupByArea(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		List<AreaMonthUseTakingCount> preList = countUseTakingDao.getAreaUseTakingDataGroupByArea(paramMap);

		List<AreaMonthUseTakingCount> list = new ArrayList<AreaMonthUseTakingCount>();

		for (AreaMonthUseTakingCount currentBean : currentList) {
			AreaMonthUseTakingCount bean = new AreaMonthUseTakingCount();

			bean.setAreaCode(currentBean.getAreaCode());
			bean.setAreaName(currentBean.getAreaName());
			bean.setUseTaking(currentBean.getUseTaking());
			bean.setOrgNum(currentBean.getOrgNum());
			bean.setPlatformName(time);

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

		String[] tableHeader = { "区域@areaName", "在线时长@useTakingStr", "上月在线时长@previousUseTakingStr", "日期@platformName",
				"增长率@rate", "学校数量@orgNum" };

		return PoiExcelUtils.createExcel2ExportByAreaUseActivity("区域使用时长统计", provinceName, tableHeader, list, time);

	}

	@Override
	public List<OrgMonthUseTakingCount> getOrgMonthUseTakingCountEcharts(Map<String, Object> param) {
		return countUseTakingDao.getOrgMonthUseTakingCountEcharts(param);
	}

	@Override
	public List<UserMonthUseTakingCount> getUserMonthUseTakingCountEcharts(Map<String, Object> param) {
		return countUseTakingDao.getUserMonthUseTakingCountEcharts(param);
	}

	@Override
	public List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountEcharts(Map<String, Object> param) {
		return countUseTakingDao.getAreaMonthUseTakingCountEcharts(param);
	}

	@Override
	public HSSFWorkbook exportOrgTakingCountExcel(HttpServletRequest request, HttpServletResponse response)
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
		List<OrgMonthUseTakingCount> currentList = countUseTakingDao.getOrgUseTakingDataGroupByOrg(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		List<OrgMonthUseTakingCount> preList = countUseTakingDao.getOrgUseTakingDataGroupByOrg(paramMap);

		List<OrgMonthUseTakingCount> list = new ArrayList<OrgMonthUseTakingCount>();

		for (OrgMonthUseTakingCount currentBean : currentList) {
			OrgMonthUseTakingCount bean = new OrgMonthUseTakingCount();

			bean.setAreaCode(currentBean.getAreaCode());
			bean.setAreaName(currentBean.getAreaName());
			bean.setUseTaking(currentBean.getUseTaking());
			bean.setPlatformName(time);
			bean.setOrgName(currentBean.getOrgName());

			bean.setUserNum(currentBean.getUserNum());

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

		String[] tableHeader = { "区域@areaName", "学校名称@orgName", "在线时长@useTakingStr", "上月在线时长@previousUseTakingStr",
				"日期@platformName", "增长率@rate", "老师数量@userNum" };

		return PoiExcelUtils.createExcel2ExportByOrgUseActivity("学校使用时长统计", provinceName, cityName, tableHeader, list,
				time);
	}

	@Override
	public HSSFWorkbook exportUserTakingCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String province = request.getParameter("province");
		String cityCode = request.getParameter("cityCode");
		String orgName = request.getParameter("orgName");
		String orgId = request.getParameter("orgId");
		String provinceName = null;
		String cityName = null;

		if (orgId != null && !"".equals(orgId)) {
			paramMap.put("orgId", orgId);
		}

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

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);

			paramMap.put("year", year);
			paramMap.put("month", month);
			time = year + "\\" + month;
		}
		// 当月或者查询的条件的月份的数据
		List<UserMonthUseTakingCount> currentList = countUseTakingDao.getUserUseTakingDataGroupByUser(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		List<UserMonthUseTakingCount> preList = countUseTakingDao.getUserUseTakingDataGroupByUser(paramMap);

		List<UserMonthUseTakingCount> list = new ArrayList<UserMonthUseTakingCount>();

		for (UserMonthUseTakingCount currentBean : currentList) {
			UserMonthUseTakingCount bean = new UserMonthUseTakingCount();

			bean.setAreaCode(currentBean.getAreaCode());
			bean.setAreaName(currentBean.getAreaName());
			bean.setUseTaking(currentBean.getUseTaking());
			bean.setPlatformName(time);
			bean.setOrgName(currentBean.getOrgName());
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

					break;
				}
			}
			list.add(bean);
		}

		String[] tableHeader = { "区域@areaName", "学校名称@orgName", "老师姓名@userName", "在线时长@useTakingStr",
				"上月在线时长@previousUseTakingStr", "日期@platformName", "增长率@rate", };

		return PoiExcelUtils.createExcel2ExportByUseActivity("用户使用时长统计", provinceName, cityName, orgName, tableHeader,
				list, time);
	}

}
