package com.whty.assis.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.manage.model.BaseProperty;
import com.whty.page.util.HandlerResult;

public interface BasePropertyService {

	List<BaseProperty> find(Map<String, Object> hashMap);

	@SuppressWarnings("rawtypes")
	public HandlerResult queryBaseProperty(Map para);

	@SuppressWarnings("rawtypes")
	public List<BaseProperty> queryAllBaseProperty(Map para);

	public BaseProperty loadBaseProById(String id);

	public void addBaseProperty(BaseProperty baseProperty);

	public void updateBaseProperty(BaseProperty baseProperty);

	@SuppressWarnings("rawtypes")
	public void deleteBaseProperty(Map para);

	@SuppressWarnings("rawtypes")
	BaseProperty getPlatformProperty(Map map) throws Exception;

	BaseProperty getBasePropertyByKeyAndPlatformCode(String propertyKey, String platformCode) throws Exception;

	String getPropertyValue(String propertyKey, String platformCode);

	@SuppressWarnings("rawtypes")
	List<BaseProperty> queryBasePropertyList(Map hashMap);

	public List getPlatformList();

	public Map<String, Object> getUserName(String getUserkeyInfoStr);

}