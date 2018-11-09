package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.WidgetHistory;

public interface WidgetHistoryService {
	void saveWidgetHistory(WidgetHistory bean);

	List<WidgetHistory> getWidgetHistoryCount(Map<String, Object> param);

	List<WidgetHistory> getAllRanking(Map<String, Object> param);

	List<WidgetHistory> getAreaCodeRankingByProvinceCode(Map<String, Object> param);

	List<WidgetHistory> getOrgRankingByAreaCode(Map<String, Object> param);

	List<WidgetHistory> getUserRankingByOrg(Map<String, Object> param);

	List<Map> getOrgListByAreaCode(String areaCode);

}
