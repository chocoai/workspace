package com.whty.assis.basicdata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.TerminalDeviceTypeDao;
import com.whty.assis.basicdata.model.TerminalDeviceType;
import com.whty.assis.basicdata.service.TerminalDeviceTypeService;
import com.whty.page.util.HandlerResult;

@Service
public class TerminalDeviceTypeServiceImpl implements TerminalDeviceTypeService {
	@Autowired
	private TerminalDeviceTypeDao terminalDeviceTypeDao;

	@Override
	public void saveTerminalDeviceType(TerminalDeviceType bean) {
		terminalDeviceTypeDao.save(bean);
	}

	@Override
	public List<TerminalDeviceType> listByCondition(Map<String, Object> paramMap) {
		return terminalDeviceTypeDao.listByCondition(paramMap);
	}

	@Override
	public HandlerResult queryTerminalDeviceTypePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(terminalDeviceTypeDao.listByCondition(paramMap));
		return rs;
	}

	@Override
	public TerminalDeviceType getById(Integer id){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		return terminalDeviceTypeDao.listByCondition(paramMap).get(0);
	}
	
	@Override
	public void updateTerminalDeviceType(TerminalDeviceType bean) {
		terminalDeviceTypeDao.update(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.TerminalDeviceTypeService#
	 * listTerminalType()
	 */
	@Override
	public List<Map<String, Object>> listTerminalType() {
		return terminalDeviceTypeDao.listTerminalType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.TerminalDeviceTypeService#
	 * listTerminalUserTypeCount()
	 */
	@Override
	public List<TerminalDeviceType> listTerminalUserTypeCount() {
		return terminalDeviceTypeDao.listTerminalUserTypeCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.TerminalDeviceTypeService#
	 * deletTerminalDeviceType(java.lang.String)
	 */
	@Override
	public void deletTerminalDeviceType(Integer id) {
		terminalDeviceTypeDao.deleteById(id);

	}
}
