package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.ui.Model;

import com.whty.assis.demo.model.AreaMonthUseTakingCount;
import com.whty.assis.demo.model.OrgMonthUseTakingCount;
import com.whty.assis.demo.model.UserMonthUseTakingCount;
import com.whty.page.util.HandlerResult;

public interface CountUseTakingService {

	void addCountDataToAttribute(Model model);

	void addAreaCountDataToAttribute(Model model, Map<String, Object> paramMap);

	HandlerResult queryAreaMonthUseTakingDataGroupByArea(Map<String, Object> paramMap);

	void addOrgCountDataToAttribute(Model model, Map<String, Object> paramMap);

	List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountData(Map<String, Object> param);

	HandlerResult queryOrgUseTakingDataGroupByOrg(Map<String, Object> paramMap);

	List<Map> getOrgListByAreaCode(Map<String, Object> param);

	void addUserCountDataToAttribute(Model model, Map<String, Object> paramMap);

	HandlerResult queryUserUseTakingDataGroupByUser(Map<String, Object> paramMap);

	HSSFWorkbook exportAreaActivityCountExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	List<OrgMonthUseTakingCount> getOrgMonthUseTakingCountEcharts(Map<String, Object> param);

	List<UserMonthUseTakingCount> getUserMonthUseTakingCountEcharts(Map<String, Object> param);

	List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountEcharts(Map<String, Object> param);

	HSSFWorkbook exportOrgTakingCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;

	HSSFWorkbook exportUserTakingCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
