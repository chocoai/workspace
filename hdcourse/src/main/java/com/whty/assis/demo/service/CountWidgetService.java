package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.whty.assis.demo.model.WidgetHistory;

public interface CountWidgetService {

	List<Map> getOrgListByAreaCode(Map<String, Object> param);

	List<WidgetHistory> getWidget(Map<String, Object> param);

	List<WidgetHistory> getWidgetCount(Map<String, Object> param);

	List<WidgetHistory> getWidgetHistoryCount(Map<String, Object> param);

	List<WidgetHistory> getAllRanking(Map<String, Object> paramMap);

	List<WidgetHistory> getUserRankingByOrg(Map<String, Object> paramMap);

	List<WidgetHistory> getOrgRankingByAreaCode(Map<String, Object> paramMap);

	List<WidgetHistory> getAreaCodeRankingByProvinceCode(Map<String, Object> paramMap);

	void addCountDataToAttribute(Model model);

	void addAreaCountDataToAttribute(Model model, Map<String, Object> paramMap);

}
