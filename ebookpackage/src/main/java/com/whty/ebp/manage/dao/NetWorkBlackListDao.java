package com.whty.ebp.manage.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.NetWorkBlackList;

public interface NetWorkBlackListDao extends IBaseDao<NetWorkBlackList> {

	NetWorkBlackList loadById(String id);

	void saveNetWorkBlackListPlatformInfo(Map<String, Object> aa);

	void saveNetWorkBlackListFlatModel(Map<String, Object> aa);

	void deleteNetWorkBlackListFlatModeByBlackListId(String id);

	void deleteNetWorkBlackListPlatformCodeByBlackListId(String id);

	List<Map<String, Object>> queryFlatModelByNetWorkBlackListId(Map<String, Object> params);

	List<Map<String, Object>> queryPlatformCodeByNetWorkBlackListId(Map<String, Object> params);

	List<NetWorkBlackList> apiQuery(Map<String, Object> map);

	Collection<? extends NetWorkBlackList> queryNetWorkBlackListByModelCode(Map<String, Object> map);

	Collection<? extends NetWorkBlackList> queryNetWorkBlackListByPlatformCode(Map<String, Object> map);

	List<Map<String, Object>> apiQueryMap(Map<String, Object> map);

	List<Map<String, Object>> queryNetWorkBlackListByModelCodeMap(Map<String, Object> map);

	List<Map<String, Object>> queryNetWorkBlackListByPlatformCodeMap(Map<String, Object> map);

	List<Map<String, Object>> queryNetWorkBlackListByPlatformCodeAndModelCode(Map<String, Object> map);

}
