package com.whty.ebp.manage.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.AppBlackList;

public interface AppBlackListDao extends IBaseDao<AppBlackList>{

	void saveAppBlackListPlatformInfo(Map<String, Object> aa);

	void saveAppBlackListFlatModel(Map<String, Object> aa);

	List<AppBlackList> queryAppBlackListByModelCode(Map<String, Object> map);

	List<AppBlackList> queryAppBlackListByPlatformCode(Map<String, Object> map);

	List<AppBlackList> queryAppBlackLst(Map<String, Object> map);

	void deleteAppBlackListPlatformCodeByAppBlackListId(String id);

	void deleteAppBlackListFlatCodeByBlackListId(String id);

	List<Map<String,Object>> queryPlatformCodeByAppBlackListId(Map<String, Object> params);

	List<AppBlackList> apiQuery(Map<String, Object> map);

	List<Map<String, Object>> apiQueryMap(Map<String, Object> map);

	List<Map<String, Object>> queryAppBlackListByModelCodeMap(Map<String, Object> map);

	List<Map<String, Object>> queryAppBlackListByPlatformCodeMap(Map<String, Object> map);

	List<Map<String,Object>> queryAppBlackListByModelCodeAndPlatformCodeMap(Map<String, Object> map);

}
