package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.LoginHistory;
import com.whty.assis.demo.model.WidgetLog;

public interface LoginHistoryService {

	void clearScanCodeData();

	void countTask();

	List<LoginHistory> saveWidgetLogToHistory(List<WidgetLog> widgetLogList);

	List<LoginHistory> getOrgUseCountByCurrentMonth();

	List<AreaMonthUsageCount> getAreaMonthUsageCount(Map<String, Object> param);

	void updateAreaMonthUsageCount(AreaMonthUsageCount bean);

	void saveAreaMonthUsageCount(AreaMonthUsageCount bean);

}
