package com.whty.assis.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.dao.CountTerminalDao;
import com.whty.assis.demo.dao.TerminalUseHistoryDao;
import com.whty.assis.demo.model.TerminalUseHistory;
import com.whty.assis.demo.service.CountTerminalUseService;
import com.whty.assis.demo.service.TerminalUseHistoryService;
import com.whty.page.util.HandlerResult;

@Service
public class CountTerminalUseServiceImpl implements CountTerminalUseService {

	@Autowired
	private CountTerminalDao countTerminalDao;

	@Override
	public List<Map> getOrgListByAreaCode(String string) {
		return countTerminalDao.getOrgListByAreaCode(string);
	}

	@Override
	public HandlerResult getTerminalUseHistoryCountPage(Map<String, Object> param) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(countTerminalDao.getTerminalUseHistoryCount(param));
		return rs;
	}

	@Override
	public List<TerminalUseHistory> getTerminalUseHistoryCount(Map<String, Object> param) {
		return countTerminalDao.getTerminalUseHistoryCount(param);
	}

	@Override
	public HandlerResult getTerminalUseHistoryCountByOrgPage(Map<String, Object> param) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(countTerminalDao.getTerminalUseHistoryCountByOrg(param));
		return rs;
	}

	@Override
	public List<TerminalUseHistory> getTerminalUseHistoryCountByOrg(Map<String, Object> param) {
		return countTerminalDao.getTerminalUseHistoryCountByOrg(param);
	}

	@Override
	public List<TerminalUseHistory> getTerminalUseHistoryCountByAreaCode(Map<String, Object> param) {
		return countTerminalDao.getTerminalUseHistoryCountByAreaCode(param);
	}

	@Override
	public HandlerResult getTerminalUseHistoryCountByAreaCodePage(Map<String, Object> param) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(countTerminalDao.getTerminalUseHistoryCountByAreaCode(param));
		return rs;
	}

	@Override
	public List<TerminalUseHistory> getTerminalUseHistoryCountByProvinceCode(Map<String, Object> param) {
		return countTerminalDao.getTerminalUseHistoryCountByProvinceCode(param);
	}

	@Override
	public HandlerResult getTerminalUseHistoryCountByProvinceCodePage(Map<String, Object> param) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(countTerminalDao.getTerminalUseHistoryCountByProvinceCode(param));
		return rs;
	}

}
