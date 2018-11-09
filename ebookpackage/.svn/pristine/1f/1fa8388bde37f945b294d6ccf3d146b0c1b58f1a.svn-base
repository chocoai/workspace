package com.whty.ebp.manage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whty.common.util.GUIDGenerator;
import com.whty.ebp.manage.model.FlatModel;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface FlatModelService {

	HandlerResult queryFlatModelPage(Map<String, Object> paramMap, PageContext page);

	FlatModel queryById(String id);

	void save(FlatModel flatModel);

	List<FlatModel> listByCondition(Map<String, Object> param);

	void update(FlatModel flatModel);

	public void saveAppFlatModel(Map<String, Object> param);

	List<Map<String, Object>> queryFlatModelByAppId(Map<String, Object> param);

	void delete(String id);

	void valModel(String trim);
	
}
