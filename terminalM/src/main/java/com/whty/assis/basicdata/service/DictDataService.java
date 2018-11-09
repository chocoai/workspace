package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.basicdata.model.DictData;
import com.whty.page.util.HandlerResult;

public interface DictDataService {
	
	public void saveDictData(DictData dictData);
	
	public List<DictData> listByCondition(Map<String, Object> paramMap);
	
	public void updateDictData(DictData dictData);

	public HandlerResult queryDictDataPage(Map<String, Object> paramMap);

}
