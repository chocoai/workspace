package com.whty.assis.api.dao;

import java.util.List;
import java.util.Map;

public interface WidgetLogDao {

	List<Map<String, Object>> queryWidget(Map<String, Object> param);

	List<Map<String, Object>> queryTerminal(Map<String, Object> param);

	void saveFuncDemo(Map<String, Object> param);

	/**
	 * 
	 */
	int test();
}
