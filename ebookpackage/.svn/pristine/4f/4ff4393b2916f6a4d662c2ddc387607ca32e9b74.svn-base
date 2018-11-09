package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.PlatformInfo;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface PlatformInfoService {

	public void save(PlatformInfo bean);

	public void delete(String platformCode);

	HandlerResult queryPlatformInfo(Map<String, Object> paramMap, PageContext page);

	public void update(PlatformInfo bean);

	public PlatformInfo loadById(String platformCode);

	List<PlatformInfo> listByCondition(Map<String, Object> param);
	
	List<Map<String,Object>> queryPlatformInfoByAppId(Map<String,Object> params);

	public List<Map<String, Object>> queryPlatformInfoByAppWhiteListId(Map<String, Object> param);

	public List<Map<String, Object>> queryPlatformInfoByDerivativeEbpAppId(Map<String, Object> param);
}
