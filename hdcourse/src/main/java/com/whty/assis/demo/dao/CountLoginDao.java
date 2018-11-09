package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthUsageCount;
import com.whty.assis.demo.model.UserMonthUsageCount;

public interface CountLoginDao {

	List<AreaMonthUsageCount> getCountData();

	List<AreaMonthUsageCount> getAreaCountData(Map<String, Object> param);

	List<AreaMonthUsageCount> getAreaCountDataGroupByArea(Map<String, Object> param);

	List<OrgMonthUsageCount> getOrgCountData(Map<String, Object> param);

	List<OrgMonthUsageCount> getOrgCountDataGroupByOrg(Map<String, Object> param);

	List<Map> getOrgListByAreaCode(Map<String, Object> param);

	List<UserMonthUsageCount> getUserCountData(Map<String, Object> param);

	List<UserMonthUsageCount> getUserCountDataGroupByUser(Map<String, Object> paramMap);

	List<AreaMonthUsageCount> getAreaMonthUsageCountData(Map<String, Object> param);

	List<OrgMonthUsageCount> getOrgMonthUsageCountEcharts(Map<String, Object> param);

	Map<String, Object> getPlatformCodeByUserAndOrgId(Map<String, Object> param);

	Map<String, Object> getTaUserInfo(Map<String, Object> param);

	List<UserMonthUsageCount> getUserMonthUsageCountChartsData(Map<String, Object> param);

	// List<AreaMonthUsageCount> getAreaMonthUsageCount(Map<String, Object>
	// param);
	//
	// List<OrgMonthUsageCount> getOrgMonthUsageCount(Map<String, Object>
	// param);
	//
	// List<UserMonthUsageCount> getUserMonthUsageCount(Map<String, Object>
	// param);
	//

	// List<UserMonthUsageCount> getUserCountDataGroupByUser();

}
