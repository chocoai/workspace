package com.whty.assis.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.dao.TerminalUseHistoryDao;
import com.whty.assis.demo.model.TerminalUseHistory;
import com.whty.assis.demo.service.TerminalUseHistoryService;
import com.whty.page.util.HandlerResult;

@Service
public class TerminalUseHistoryServiceImpl implements TerminalUseHistoryService {

	@Autowired
	private TerminalUseHistoryDao terminalUseHistoryDao;

	@Override
	public List<Map> getOrgListByAreaCode(String string) {
		return terminalUseHistoryDao.getOrgListByAreaCode(string);
	}

	@Override
	public List<TerminalUseHistory> getTerminalUseHistoryCount(Map<String, Object> param) {
		return terminalUseHistoryDao.getTerminalUseHistoryCount(param);
	}

	@Override
	public List<TerminalUseHistory> getTerminalUseHistoryCountByOrg(Map<String, Object> param) {
		return terminalUseHistoryDao.getTerminalUseHistoryCountByOrg(param);
	}

	@Override
	public List<TerminalUseHistory> getTerminalUseHistoryCountByAreaCode(Map<String, Object> param) {
		return terminalUseHistoryDao.getTerminalUseHistoryCountByAreaCode(param);
	}

	@Override
	public List<TerminalUseHistory> getTerminalUseHistoryCountByProvinceCode(Map<String, Object> param) {
		return terminalUseHistoryDao.getTerminalUseHistoryCountByProvinceCode(param);
	}

	@Override
	public HandlerResult getTerminalUseHistoryCountPage(Map<String, Object> param) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(terminalUseHistoryDao.getTerminalUseHistoryCount(param));
		return rs;
	}

	@Override
	public HandlerResult getTerminalUseHistoryCountByOrgPage(Map<String, Object> param) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(terminalUseHistoryDao.getTerminalUseHistoryCountByOrg(param));
		return rs;
	}

	@Override
	public HandlerResult getTerminalUseHistoryCountByAreaCodePage(Map<String, Object> param) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(terminalUseHistoryDao.getTerminalUseHistoryCountByAreaCode(param));
		return rs;
	}

	@Override
	public HandlerResult getTerminalUseHistoryCountByProvinceCodePage(Map<String, Object> param) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(terminalUseHistoryDao.getTerminalUseHistoryCountByProvinceCode(param));
		return rs;
	}

}
