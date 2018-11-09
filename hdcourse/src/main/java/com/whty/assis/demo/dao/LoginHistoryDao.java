package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.AreaMonthUseTakingCount;
import com.whty.assis.demo.model.LoginHistory;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthUseTakingCount;
import com.whty.assis.demo.model.UserMonthActivityCount;
import com.whty.assis.demo.model.UserMonthUsageCount;
import com.whty.assis.demo.model.UserMonthUseTakingCount;

public interface LoginHistoryDao extends IBaseDao<LoginHistory> {

	public void addLoginHistory(LoginHistory bean);

	public List<LoginHistory> getOrgUseCountByCurrentMonth(Map<String, Object> parm);

	public List<AreaMonthUsageCount> getAreaMonthUsageCount(Map<String, Object> parm);

	void updateAreaMonthUsageCount(AreaMonthUsageCount bean);

	void saveAreaMonthUsageCount(AreaMonthUsageCount bean);

	public List<LoginHistory> getUserCountByCurrentMonth(Map<String, Object> parm);

	public List<OrgMonthUsageCount> getOrgMonthUsageCount(Map<String, Object> param);

	public void saveOrgMonthUsageCount(OrgMonthUsageCount bean);

	public void updateOrgMonthUsageCount(OrgMonthUsageCount bean);

	public void saveLoginHistoryFiling(LoginHistory bean);

	public void updateAreaMonthActivityCount(AreaMonthActivityCount bean);

	public List<OrgMonthActivityCount> getOrgMonthActivityCount(Map<String, Object> param);

	public void saveOrgMonthActivityCount(OrgMonthActivityCount bean);

	public void updateOrgMonthActivityCount(OrgMonthActivityCount bean);

	public List<AreaMonthActivityCount> getAreaMonthActivityCount(Map<String, Object> param);

	public void saveAreaMonthActivityCount(AreaMonthActivityCount bean);

	public List<LoginHistory> getAreaUseCountByCurrentMonth(Map<String, Object> parm);

	public List<LoginHistory> getTeacherUseCountByCurrentMonth(Map<String, Object> parm);

	public List<UserMonthUsageCount> getUserMonthUsageCount(Map<String, Object> parm);

	public void saveUserMonthUsageCount(UserMonthUsageCount bean);

	public void updateUserMonthUsageCount(UserMonthUsageCount bean);

	public List<UserMonthActivityCount> getUserMonthActivityCount(Map<String, Object> param);

	public void saveUserMonthActivityCount(UserMonthActivityCount bean);

	public void updateUserMonthActivityCount(UserMonthActivityCount bean);

	public List<OrgMonthActivityCount> getOrgActivityCount(Map<String, Object> param);

	public void updateOrgRanking(AreaMonthUsageCount bean);

	public List<OrgMonthUsageCount> countOrgMonthUsage(Map<String, Object> param);

	public void deleteOrgMonthUsage(Map<String, Object> param);

	public void deleteOrgMonthActivity(Map<String, Object> param);

	public void deleteAreaMonthActivity(Map<String, Object> param);

	public List<LoginHistory> getUserActivityCountByCurrentMonth(Map<String, Object> parm);

	public List<OrgMonthActivityCount> countOrgMonthActivity(Map<String, Object> param);

	public List<AreaMonthActivityCount> countAreaMonthActivity(Map<String, Object> param);

	public List<LoginHistory> getOrgUseTakingByCurrentMonth(Map<String, Object> parm);

	public void saveAreaMonthUseTakingCount(AreaMonthUseTakingCount bean);

	public void updateAreaMonthUseTakingCount(AreaMonthUseTakingCount bean);

	public List<OrgMonthUseTakingCount> getOrgMonthUseTakingCount(Map<String, Object> param);

	public void updateOrgMonthUseTakingCount(OrgMonthUseTakingCount bean);

	public void saveOrgMonthUseTakingCount(OrgMonthUseTakingCount bean);

	public List<UserMonthUseTakingCount> getUserMonthUseTakingCount(Map<String, Object> param);

	public List<LoginHistory> getUserUseTakingByCurrentMonth(Map<String, Object> parm);

	public void updateUserMonthUseTakingCount(UserMonthUseTakingCount bean);

	public void saveUserMonthUseTakingCount(UserMonthUseTakingCount bean);

	public List<AreaMonthUseTakingCount> getAreaMonthUseTakingCount(Map<String, Object> param);

}
