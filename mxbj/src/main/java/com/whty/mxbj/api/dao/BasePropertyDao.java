package com.whty.mxbj.api.dao;

import java.util.List;
import java.util.Map;

import com.whty.mxbj.api.model.BaseProperty;
import com.whty.mxbj.base.dao.IBaseDao;

public interface BasePropertyDao extends IBaseDao<BaseProperty> {

	String getPropertyValue(Map<String, Object> map);

	List<BaseProperty> find(Map<String, Object> map);

	List<BaseProperty> queryBaseProperty(Map<String, Object> para);

	void addBaseProperty(BaseProperty baseProperty);

	void updateBaseProperty(BaseProperty baseProperty);

	void deleteBaseProperty(Map<String, Object> para);

}
