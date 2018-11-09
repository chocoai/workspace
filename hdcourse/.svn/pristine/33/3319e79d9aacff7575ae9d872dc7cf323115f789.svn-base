package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.AreaMonthUseTakingCount;
import com.whty.assis.demo.model.OrgMonthUseTakingCount;
import com.whty.assis.demo.model.UserMonthUseTakingCount;

public interface CountUseTakingDao extends IBaseDao<AreaMonthUseTakingCount> {

	List<AreaMonthUseTakingCount> getCountData();

	List<AreaMonthUseTakingCount> getAreaCountData(Map<String, Object> param);

	List getAreaUseTakingDataGroupByArea(Map<String, Object> paramMap);

	List<OrgMonthUseTakingCount> getOrgUseTakingData(Map<String, Object> param);

	List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountData(Map<String, Object> param);

	List getOrgUseTakingDataGroupByOrg(Map<String, Object> paramMap);

	List<Map> getOrgListByAreaCode(Map<String, Object> string);

	List<UserMonthUseTakingCount> getUserUseTakingData(Map<String, Object> paramMap);

	List getUserUseTakingDataGroupByUser(Map<String, Object> paramMap);

	List<AreaMonthActivityCount> getAreaActivityDataGroupByArea(Map<String, Object> paramMap);

	List<OrgMonthUseTakingCount> getOrgMonthUseTakingCountEcharts(Map<String, Object> param);

	List<UserMonthUseTakingCount> getUserMonthUseTakingCountEcharts(Map<String, Object> param);

	List getAreaUseTakingDataGroupByOrg(Map<String, Object> paramMap);

	List getAreaUseTakingDataGroupByUser(Map<String, Object> paramMap);

	List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountEcharts(Map<String, Object> param);

}
