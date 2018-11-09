package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.TerminalUseHistory;
import com.whty.page.util.HandlerResult;

public interface TerminalUseHistoryService {

	List<Map> getOrgListByAreaCode(String string);

	List<TerminalUseHistory> getTerminalUseHistoryCount(Map<String, Object> param);

	List<TerminalUseHistory> getTerminalUseHistoryCountByOrg(Map<String, Object> param);

	List<TerminalUseHistory> getTerminalUseHistoryCountByAreaCode(Map<String, Object> param);

	List<TerminalUseHistory> getTerminalUseHistoryCountByProvinceCode(Map<String, Object> param);

	HandlerResult getTerminalUseHistoryCountPage(Map<String, Object> param);

	HandlerResult getTerminalUseHistoryCountByOrgPage(Map<String, Object> param);

	HandlerResult getTerminalUseHistoryCountByAreaCodePage(Map<String, Object> param);

	HandlerResult getTerminalUseHistoryCountByProvinceCodePage(Map<String, Object> param);

}
