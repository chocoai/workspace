package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.TerminalUseHistory;
import com.whty.assis.demo.model.TerminalUseLog;

public interface TerminalUseHistoryDao {
	List<TerminalUseLog> getTerminalUseLog(Map<String, Object> param);

	void saveTerminalUseHistory(TerminalUseHistory bean);

	List<TerminalUseLog> getTerminalUseLogCountData(Map<String, Object> param);

	void updateTerminalUseLog(TerminalUseLog bean);

	List<Map> getOrgListByAreaCode(String string);

	List<TerminalUseHistory> getTerminalUseHistoryCount(Map<String, Object> param);

	List<TerminalUseHistory> getTerminalUseHistoryCountByOrg(Map<String, Object> param);

	List<TerminalUseHistory> getTerminalUseHistoryCountByAreaCode(Map<String, Object> param);

	List<TerminalUseHistory> getTerminalUseHistoryCountByProvinceCode(Map<String, Object> param);
}
