package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.ui.Model;

import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.model.UserMonthActivityCount;
import com.whty.page.util.HandlerResult;

public interface UserActivityService {

	HandlerResult queryUserMonthActivityCountPage(Map<String, Object> paramMap);

	void addCountDataToAttribute(Model model);

	HandlerResult queryAreaMonthActivityCountPage(Map<String, Object> paramMap);

	HandlerResult queryOrgMonthActivityCountPage(Map<String, Object> paramMap);

	List<Map> getOrgListByAreaCode(String string);

	List<AreaMonthActivityCount> getAreaMonthActivityCount(Map<String, Object> param);

	List<OrgMonthActivityCount> getOrgMonthActivityCount(Map<String, Object> param);

	List<UserMonthActivityCount> getUserMonthActivityCount(Map<String, Object> param);

	List<AreaMonthActivityCount> getAreaMonthActivityCountData(Map<String, Object> param);

	HSSFWorkbook exportAreaActivityCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	HSSFWorkbook exportOrgActivityCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;

	HSSFWorkbook exportUserActivityCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	void addAreaCountDataToAttribute(Model model, Map<String, Object> paramMap);

	List<OrgMonthActivityCount> getOrgMonthActivityCountEcharts(Map<String, Object> param);

	void addOrgCountDataToAttribute(Model model, Map<String, Object> paramMap);

	public void addUserCountDataToAttribute(Model model, Map<String, Object> paramMap);

	List<UserMonthActivityCount> getUserMonthActivityCountEcharts(Map<String, Object> param);

	HandlerResult queryAreaActivityDataGroupByArea(Map<String, Object> paramMap);

	HandlerResult queryOrgActivityDataGroupByOrg(Map<String, Object> paramMap);

	HandlerResult queryUserActivityDataGroupByUser(Map<String, Object> paramMap);

}
