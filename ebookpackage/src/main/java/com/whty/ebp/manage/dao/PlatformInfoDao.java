package com.whty.ebp.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.PlatformInfo;

public interface PlatformInfoDao extends IBaseDao<PlatformInfo>{

	void saveAppPlatformInfo(Map<String, Object> aa);

	void deleteByEbpAppId(String id);

	List<Map<String,Object>> queryPlatformInfoByAppId(Map<String,Object> params);
	
	List<Map<String, Object>> queryPlatformInfoByAppWhiteListId(Map<String, Object> params);

	void saveAppWhiteListPlatformInfo(Map<String,Object> aa);
	
	void deleteByWhiteListId(String id);

	void saveDerivativeAppPlatformInfo(Map<String, Object> aa);

	void deleteByDerivativeEbpAppId(String id);

	List<Map<String, Object>> queryPlatformInfoByDerivativeEbpAppId(Map<String, Object> param);
	
}
