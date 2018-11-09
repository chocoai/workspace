package com.whty.assis.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.manage.model.BaseProperty;

public interface BasePropertyDao extends IBaseDao<BaseProperty> {

	/**
	 * 根据查询条件查询
	 * 
	 * @param para
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<BaseProperty> queryBaseProperty(Map para);

	/**
	 * 添加
	 * 
	 * @param baseProperty
	 */
	public void addBaseProperty(BaseProperty baseProperty);

	/**
	 * 更新
	 * 
	 * @param baseProperty
	 */
	public void updateBaseProperty(BaseProperty baseProperty);
	
	public BaseProperty selectByPlatformCode(String platformCode);

	/**
	 * 删除
	 * 
	 * @param para
	 */
	@SuppressWarnings("rawtypes")
	public void deleteBaseProperty(Map para);

	@SuppressWarnings("rawtypes")
	List<BaseProperty> getPlatformProperty(Map map);

	@SuppressWarnings("rawtypes")
	List<BaseProperty> getBasePropertyByKeyAndPlatformCode(Map map);

	@SuppressWarnings("rawtypes")
	String getPropertyValue(Map map);

	@SuppressWarnings("rawtypes")
	public List getPlatformList();
}
