package com.whty.assis.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.dao.WidgetHistoryDao;
import com.whty.assis.demo.model.WidgetHistory;
import com.whty.assis.demo.service.WidgetHistoryService;

@Service
public class WidgetHistoryServiceImpl implements WidgetHistoryService {

	@Autowired
	private WidgetHistoryDao widgetHistoryDao;

	@Override
	public void saveWidgetHistory(WidgetHistory bean) {
		widgetHistoryDao.saveWidgetHistory(bean);
	}

	@Override
	public List<WidgetHistory> getWidgetHistoryCount(Map<String, Object> param) {
		return widgetHistoryDao.getWidgetHistoryCount(param);
	}

	@Override
	public List<WidgetHistory> getAllRanking(Map<String, Object> param) {
		return widgetHistoryDao.getAllRanking(param);
	}

	@Override
	public List<WidgetHistory> getAreaCodeRankingByProvinceCode(Map<String, Object> param) {
		return widgetHistoryDao.getAreaCodeRankingByProvinceCode(param);
	}

	@Override
	public List<WidgetHistory> getOrgRankingByAreaCode(Map<String, Object> param) {
		return widgetHistoryDao.getOrgRankingByAreaCode(param);
	}

	@Override
	public List<WidgetHistory> getUserRankingByOrg(Map<String, Object> param) {
		return widgetHistoryDao.getUserRankingByOrg(param);
	}

	@Override
	public List<Map> getOrgListByAreaCode(String areaCode) {
		return widgetHistoryDao.getOrgListByAreaCode(areaCode);
	}

}
