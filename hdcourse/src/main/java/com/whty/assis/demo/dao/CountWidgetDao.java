package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.WidgetHistory;

public interface CountWidgetDao {

	List<Map> getOrgListByAreaCode(Map<String, Object> param);

	List<WidgetHistory> getWidget(Map<String, Object> param);

	List<WidgetHistory> getWidgetCount(Map<String, Object> param);

	List<WidgetHistory> getWidgetHistoryCount(Map<String, Object> param);

	List<WidgetHistory> getAllRanking(Map<String, Object> paramMap);

	List<WidgetHistory> getUserRankingByOrg(Map<String, Object> paramMap);

	List<WidgetHistory> getOrgRankingByAreaCode(Map<String, Object> paramMap);

	List<WidgetHistory> getAreaCodeRankingByProvinceCode(Map<String, Object> paramMap);

	List<WidgetHistory> getCountData();

	List<WidgetHistory> getAreaCountData(Map<String, Object> paramMap);
}
