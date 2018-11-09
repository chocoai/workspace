package com.whty.assis.demo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.whty.assis.demo.dao.AreaDao;
import com.whty.assis.demo.dao.UseCountDao;
import com.whty.assis.demo.model.Area;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthUsageCount;
import com.whty.assis.demo.model.UserMonthUsageCount;
import com.whty.assis.demo.service.UseCountService;
import com.whty.common.excel.PoiExcelUtils;
import com.whty.common.util.BigDecimalUtils;
import com.whty.page.util.HandlerResult;

@Service
public class UseCountServiceImpl implements UseCountService {

	private static Logger log = Logger.getLogger(UseCountServiceImpl.class);

	@Autowired
	private UseCountDao useCountDao;

	@Autowired
	private AreaDao areaDao;

	@Override
	public List<AreaMonthUsageCount> getAreaMonthUsageCount(Map<String, Object> param) {
		return useCountDao.getAreaMonthUsageCount(param);
	}

	@Override
	public List<OrgMonthUsageCount> getOrgMonthUsageCount(Map<String, Object> param) {
		return useCountDao.getOrgMonthUsageCount(param);
	}

	@Override
	public HandlerResult queryAreaMonthUsageCount(Map<String, Object> map) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(useCountDao.getAreaMonthUsageCount(map));
		return handlerResult;
	}

	@Override
	public HandlerResult queryOrgMonthUsageCount(Map<String, Object> map) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(useCountDao.getOrgMonthUsageCount(map));
		return handlerResult;
	}

	@Override
	public HandlerResult queryUserMonthUsageCount(Map<String, Object> map) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(useCountDao.getUserMonthUsageCount(map));
		return handlerResult;
	}

	@Override
	public List<Map> getOrgListByAreaCode(String areaCode) {
		return useCountDao.getOrgListByAreaCode(areaCode);
	}

	@Override
	public List<UserMonthUsageCount> getUserMonthUsageCount(Map<String, Object> map) {
		return useCountDao.getUserMonthUsageCount(map);
	}

	@Override
	public void addOrgCountDataToAttribute(Model model, Map<String, Object> param) {
		List<OrgMonthUsageCount> orgMonthUsageCountList = useCountDao.getOrgCountData(param);
		int orgNum = 0;
		int orgUseTotal = 0;

		for (int i = 0; i < orgMonthUsageCountList.size(); i++) {
			if (i == 0) {
				if (orgMonthUsageCountList.get(i) != null) {
					orgNum = orgMonthUsageCountList.get(i).getUseCount();
				}
			}
			if (i == 1) {
				if (orgMonthUsageCountList.get(i) != null) {
					orgUseTotal = orgMonthUsageCountList.get(i).getUseCount();
				}
			}
		}
		model.addAttribute("orgNum", orgNum);
		model.addAttribute("orgUseTotal", orgUseTotal);
	}

	@Override
	public void addAreaCountDataToAttribute(Model model, Map<String, Object> param) {
		List<AreaMonthUsageCount> useCountList = useCountDao.getAreaCountData(param);
		int areaNum = 0;
		int areaUseTotal = 0;
		int orgTotal = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					areaNum = useCountList.get(i).getUseCount();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					areaUseTotal = useCountList.get(i).getUseCount();
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					orgTotal = useCountList.get(i).getUseCount();
				}
			}
		}

		model.addAttribute("areaNum", areaNum);
		model.addAttribute("areaUseTotal", areaUseTotal);
		model.addAttribute("orgTotal", orgTotal);
	}

	@Override
	public void addCountDataToAttribute(Model model) {
		List<AreaMonthUsageCount> useCountList = useCountDao.getCountData();
		int areaCount = 0;
		int orgCount = 0;
		int userCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					areaCount = useCountList.get(i).getUseCount();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					orgCount = useCountList.get(i).getUseCount();
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					userCount = useCountList.get(i).getUseCount();
				}
			}
		}
		model.addAttribute("areaCount", areaCount);
		model.addAttribute("orgCount", orgCount);
		model.addAttribute("userCount", userCount);
	}

	@Override
	public List<AreaMonthUsageCount> getAreaMonthUsageCountData(Map<String, Object> param) {
		return useCountDao.getAreaMonthUsageCountData(param);
	}

	@Override
	public List<OrgMonthUsageCount> getOrgMonthUsageCountEcharts(Map<String, Object> param) {
		return useCountDao.getOrgMonthUsageCountEcharts(param);
	}

	@Override
	public HSSFWorkbook exportAreaCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String province = request.getParameter("province");

		String title = "全部";

		if (province != null || !"".equals(province)) {
			paramMap.put("provinceCode", province);

			Map<String, Object> areaParam = new HashMap<String, Object>();
			areaParam.put("areaCode", province);
			List<Area> list = areaDao.getArea(areaParam);

			if (list != null && list.size() == 1) {
				title = list.get(0).getAreaName();
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
		List<AreaMonthUsageCount> currentList = useCountDao.getAreaCountDataGroupByArea(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		List<AreaMonthUsageCount> preList = useCountDao.getAreaCountDataGroupByArea(paramMap);

		List<AreaMonthUsageCount> list = new ArrayList<AreaMonthUsageCount>();

		for (AreaMonthUsageCount currentBean : currentList) {
			AreaMonthUsageCount bean = new AreaMonthUsageCount();
			bean.setAreaName(currentBean.getAreaName());

			bean.setUseCount(currentBean.getUseCount());
			bean.setOrgUseRanking(currentBean.getOrgUseRanking());
			bean.setPlatformName(time);
			for (AreaMonthUsageCount preBean : preList) {
				if (preBean.getAreaCode().equals(currentBean.getAreaCode())) {
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

		// Set<String> userSet = new HashSet<String>();

		// PoiExcelUtils.createExcel2Export(sheetName, title, headers, dataList)

		// String filename = "区域月统计";
		String[] tableHeader = { "区域名称@areaName", "使用次数@useCount", "日期@platformName", "上次使用次数@previousUseCount",
				"学校数量@orgUseRanking", "增长率@rate" };

		return PoiExcelUtils.createExcel2ExportByAreaUseCount("区域使用次数统计", title, tableHeader, list, time);

	}

	@Override
	public HSSFWorkbook exportOrgCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

			paramMap.put("year", year);
			paramMap.put("month", month);

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1);

			time = year + "\\" + month;
		}
		// 当月或者查询的条件的月份的数据
		List<OrgMonthUsageCount> currentList = useCountDao.getOrgCountDataGroupByOrg(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		List<OrgMonthUsageCount> preList = useCountDao.getOrgCountDataGroupByOrg(paramMap);

		List<OrgMonthUsageCount> list = new ArrayList<OrgMonthUsageCount>();

		for (OrgMonthUsageCount currentBean : currentList) {
			OrgMonthUsageCount bean = new OrgMonthUsageCount();

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

		String[] tableHeader = { "区域@areaName", "学校@orgName", "使用次数@useCount", "日期@platformName",
				"上次使用次数@previousUseCount", "增长率@rate" };

		return PoiExcelUtils.createExcel2ExportByOrgUseCount("使用次数统计", provinceName, cityName, tableHeader, list, time);

	}

	@Override
	public HandlerResult getOrgCountDataGroupByOrg(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(useCountDao.getOrgCountDataGroupByOrg(paramMap));
		return handlerResult;
	}

	@Override
	public HandlerResult queryAreaMonthUsageCountDataByArea(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(useCountDao.getAreaCountDataGroupByArea(paramMap));
		return handlerResult;
	}

	@Override
	public HandlerResult queryUserCountDataGroupByUser(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(useCountDao.getUserCountDataGroupByUser(paramMap));
		return handlerResult;
	}

	@Override
	public HSSFWorkbook exportUserCountExcel(HttpServletRequest request, HttpServletResponse response)
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
		List<UserMonthUsageCount> currentList = useCountDao.getUserCountDataGroupByUser(paramMap);

		// 上月的数据
		cal.add(Calendar.MONTH, -1);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;// 上月

		paramMap.put("year", year);
		paramMap.put("month", month);

		List<UserMonthUsageCount> preList = useCountDao.getUserCountDataGroupByUser(paramMap);

		List<UserMonthUsageCount> list = new ArrayList<UserMonthUsageCount>();

		for (UserMonthUsageCount currentBean : currentList) {
			UserMonthUsageCount bean = new UserMonthUsageCount();

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

		String[] tableHeader = { "区域@areaName", "学校@orgName", "老师@userName", "使用次数@useCount", "日期@platformName",
				"上次使用次数@previousUseCount", "增长率@rate" };

		return PoiExcelUtils.createExcel2ExportByUseCount("用户使用次数统计", provinceName, cityName, orgName, tableHeader,
				list, time);
	}

	@Override
	public List<UserMonthUsageCount> getUserMonthUsageCountChartsData(Map<String, Object> param) {
		return useCountDao.getUserMonthUsageCountChartsData(param);
	}

	@Override
	public void addUserCountDataToAttribute(Model model, Map<String, Object> param) {
		List<UserMonthUsageCount> useCountList = useCountDao.getUserCountData(param);
		int userNum = 0;
		int userTotal = 0;

		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					userNum = useCountList.get(i).getUseCount();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					userTotal = useCountList.get(i).getUseCount();
				}
			}
		}
		model.addAttribute("userNum", userNum);
		model.addAttribute("userTotal", userTotal);
	}

	@Override
	public Map<String, Object> getPlatformCode(Map<String, Object> param) {
		return useCountDao.getPlatformCodeByUserAndOrgId(param);
	}

	@Override
	public Map<String, Object> getTaUserInfo(Map<String, Object> param) {
		return useCountDao.getTaUserInfo(param);
	}

}
