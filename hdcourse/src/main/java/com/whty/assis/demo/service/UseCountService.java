package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.ui.Model;

import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthUsageCount;
import com.whty.assis.demo.model.UserMonthUsageCount;
import com.whty.page.util.HandlerResult;

public interface UseCountService {

	List<AreaMonthUsageCount> getAreaMonthUsageCount(Map<String, Object> param);

	List<OrgMonthUsageCount> getOrgMonthUsageCount(Map<String, Object> param);

	public HandlerResult queryAreaMonthUsageCount(Map<String, Object> map);

	public HandlerResult queryOrgMonthUsageCount(Map<String, Object> map);

	public HandlerResult queryUserMonthUsageCount(Map<String, Object> paramMap);

	public List<Map> getOrgListByAreaCode(String areaCode);

	public List<UserMonthUsageCount> getUserMonthUsageCount(Map<String, Object> map);

	// public HandlerResult getUserCountDataGroupByUser(Map<String,Object>
	// paramMap);

	public HandlerResult queryAreaMonthUsageCountDataByArea(Map<String, Object> paramMap);

	/**
	 * 获取区域、学校、用户统计数据
	 * 
	 * @return
	 */
	public void addCountDataToAttribute(Model model);

	public void addAreaCountDataToAttribute(Model model, Map<String, Object> param);

	public void addOrgCountDataToAttribute(Model model, Map<String, Object> param);

	public void addUserCountDataToAttribute(Model model, Map<String, Object> param);

	List<AreaMonthUsageCount> getAreaMonthUsageCountData(Map<String, Object> param);

	List<OrgMonthUsageCount> getOrgMonthUsageCountEcharts(Map<String, Object> param);

	public HSSFWorkbook exportAreaCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public HSSFWorkbook exportOrgCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public HSSFWorkbook exportUserCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;

	List<UserMonthUsageCount> getUserMonthUsageCountChartsData(Map<String, Object> param);

	HandlerResult getOrgCountDataGroupByOrg(Map<String, Object> paramMap);

	HandlerResult queryUserCountDataGroupByUser(Map<String, Object> paramMap);

	Map<String, Object> getPlatformCode(Map<String, Object> param);

	Map<String, Object> getTaUserInfo(Map<String, Object> param);

}
