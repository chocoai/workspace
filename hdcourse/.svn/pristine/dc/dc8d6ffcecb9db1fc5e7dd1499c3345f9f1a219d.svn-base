package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.WidgetHistory;

public interface WidgetHistoryDao {

	void saveWidgetHistory(WidgetHistory bean);

	List<WidgetHistory> getWidgetHistoryCount(Map<String, Object> param);

	List<WidgetHistory> getWidgetHistoryByAreaCount(Map<String, Object> param);

	List<WidgetHistory> getWidgetHistoryByOrgCount(Map<String, Object> param);

	List<WidgetHistory> getAllRanking(Map<String, Object> param);

	List<WidgetHistory> getAreaCodeRankingByProvinceCode(Map<String, Object> param);

	List<WidgetHistory> getOrgRankingByAreaCode(Map<String, Object> param);

	List<WidgetHistory> getUserRankingByOrg(Map<String, Object> param);

	List<Map> getOrgListByAreaCode(String areaCode);
}
