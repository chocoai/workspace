package com.whty.assis.basicdata.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.DictDataDao;
import com.whty.assis.basicdata.model.DictData;
import com.whty.assis.basicdata.service.DictDataService;
import com.whty.page.util.HandlerResult;

@Service
public class DictDataServiceImpl implements DictDataService {

	@Autowired
	private DictDataDao dictDataDao;

	@Override
	public void saveDictData(DictData dictData) {
		dictDataDao.save(dictData);
	}

	@Override
	public List<DictData> listByCondition(Map<String, Object> paramMap) {
		return dictDataDao.listByCondition(paramMap);
	}

	@Override
	public void updateDictData(DictData dictData) {
		dictDataDao.update(dictData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DictDataService#queryDictDataPage(java.
	 * util.Map)
	 */
	@Override
	public HandlerResult queryDictDataPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(dictDataDao.listByCondition(paramMap));
		return rs;
	}

}
