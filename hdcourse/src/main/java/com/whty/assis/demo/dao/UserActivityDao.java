package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.model.UserMonthActivityCount;
import com.whty.assis.demo.model.UserMonthUsageCount;

public interface UserActivityDao {

	List<UserMonthActivityCount> queryUserMonthActivityCount(Map<String, Object> paramMap);

	List<AreaMonthActivityCount> queryAreaMonthActivityCount(Map<String, Object> paramMap);

	List<OrgMonthActivityCount> queryOrgMonthActivityCount(Map<String, Object> paramMap);

	List<Map> getOrgListByAreaCode(String string);

	List<AreaMonthActivityCount> getCountData();

	List<AreaMonthActivityCount> queryUserMonthActivityCountData(Map<String, Object> param);

	List<AreaMonthActivityCount> getAreaActivityDataGroupByArea(Map<String, Object> paramMap);

	List<AreaMonthActivityCount> getAreaActivityData(Map<String, Object> paramMap);

	List<OrgMonthActivityCount> getOrgActivityData(Map<String, Object> paramMap);

	List<OrgMonthActivityCount> getOrgActivityDataGroupByOrg(Map<String, Object> paramMap);

	List<OrgMonthActivityCount> getOrgMonthActivityCountEcharts(Map<String, Object> param);

	List<UserMonthActivityCount> getUserActivityDataGroupByUser(Map<String, Object> paramMap);

	List<UserMonthActivityCount> getUserActivityData(Map<String, Object> paramMap);

	List<UserMonthActivityCount> getUserMonthActivityCountEcharts(Map<String, Object> param);
}
