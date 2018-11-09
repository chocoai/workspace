package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.basicdata.model.DictType;
import com.whty.page.util.HandlerResult;

public interface DictTypeService {

	public void saveDictType(DictType dictType);

	public List<DictType> listByCondition(Map<String, Object> paramMap);

	public HandlerResult queryDictTypePage(Map<String, Object> paramMap);

	public void updateDictType(DictType dictType);
	
}
