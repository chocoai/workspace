package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.whty.assis.demo.model.AreaMonthUseTakingCount;
import com.whty.assis.demo.model.OrgMonthUseTakingCount;
import com.whty.assis.demo.model.UserMonthUseTakingCount;
import com.whty.page.util.HandlerResult;

public interface UseTakingCountService {

	HandlerResult queryAreaMonthUseTakingDataGroupByArea(Map<String, Object> paramMap);

	HandlerResult queryOrgUseTakingDataGroupByOrg(Map<String, Object> paramMap);

	HandlerResult queryUserUseTakingDataGroupByUser(Map<String, Object> paramMap);

	void addCountDataToAttribute(Model model);

	void addAreaCountDataToAttribute(Model model, Map<String, Object> paramMap);

	void addOrgCountDataToAttribute(Model model, Map<String, Object> paramMap);

	void addUserCountDataToAttribute(Model model, Map<String, Object> paramMap);

	List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountEcharts(Map<String, Object> param);

	List<OrgMonthUseTakingCount> getOrgMonthUseTakingCountEcharts(Map<String, Object> param);

	List<UserMonthUseTakingCount> getUserMonthUseTakingCountEcharts(Map<String, Object> param);

	List<Map> getOrgListByAreaCode(String string);

	List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountData(Map<String, Object> param);

}
