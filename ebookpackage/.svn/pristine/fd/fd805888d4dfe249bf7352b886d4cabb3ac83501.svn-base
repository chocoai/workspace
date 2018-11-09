package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.AppBlackList;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface AppBlackListService {

	HandlerResult queryAppBlackListPage(Map<String, Object> paramMap, PageContext page);

	void delete(String id);

	void update(AppBlackList appBlackList);

	void updateStatus(AppBlackList appBlackList);

	void save(AppBlackList appBlackList);

	AppBlackList queryById(String id);

//	List<AppBlackList> query(Map<String, Object> map);

	List<Map<String, Object>> queryPlatformCodeByAppBlackListId(Map<String, Object> params);

	List<AppBlackList> apiQuery(Map<String, Object> map);

	List<Map<String, Object>> apiQueryMap(Map<String, Object> map);

}
