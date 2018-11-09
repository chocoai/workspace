package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.NetWorkBlackList;
import com.whty.ebp.manage.model.WhiteList;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface NetWorkBlackListService {

	void save(NetWorkBlackList netWorkBlackList);

	HandlerResult queryNetWorkBlackListPage(Map<String, Object> paramMap, PageContext page);

	NetWorkBlackList loadById(String id);

	List<Map<String, Object>> queryFlatModelByNetWorkBlackListId(Map<String, Object> params);

	void delete(String id);

	void update(NetWorkBlackList netWorkBlackList);

	void updateStatus(NetWorkBlackList netWorkBlackList);

	List<Map<String, Object>> queryPlatformCodeByNetWorkBlackListId(Map<String, Object> params);

	List<NetWorkBlackList> apiQuery(Map<String, Object> map);

	List<Map<String, Object>> apiQueryMap(Map<String, Object> map);

}
