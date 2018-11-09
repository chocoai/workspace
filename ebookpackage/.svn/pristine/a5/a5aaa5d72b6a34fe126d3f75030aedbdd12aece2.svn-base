package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.AppBlackList;
import com.whty.ebp.manage.model.Browser;
import com.whty.ebp.manage.model.WhiteList;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface AppNetBlackService {
	
	HandlerResult queryAppNetBlackPage(Map<String, Object> paraMap, PageContext page);
	
	List<Map<String, Object>> queryPlatformCodeByAppBlackListId(Map<String, Object> params);

	void delete(String id);

	void update(AppBlackList appBlackList);

	void updateStatus(AppBlackList appBlackList);

	void save(AppBlackList appBlackList);

	AppBlackList queryById(String id);

//	List<AppBlackList> apiQuery(Map<String, Object> map);

	List<Map<String, Object>> apiQueryMap(Map<String, Object> map);

}
