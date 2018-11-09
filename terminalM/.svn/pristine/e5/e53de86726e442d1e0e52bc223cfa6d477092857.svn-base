package com.whty.assis.basicdata.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.DictTypeDao;
import com.whty.assis.basicdata.model.DictType;
import com.whty.assis.basicdata.service.DictTypeService;
import com.whty.page.util.HandlerResult;

@Service
public class DictTypeServiceImpl implements DictTypeService {

	@Autowired
	private DictTypeDao dictTypeDao;

	@Override
	public void saveDictType(DictType dictType) {
		dictTypeDao.save(dictType);
	}

	@Override
	public List<DictType> listByCondition(Map<String, Object> paramMap) {
		return dictTypeDao.listByCondition(paramMap);
	}

	@Override
	public HandlerResult queryDictTypePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(dictTypeDao.listByCondition(paramMap));
		return rs;
	}

	@Override
	public void updateDictType(DictType dictType) {
		dictTypeDao.update(dictType);
	}

}
