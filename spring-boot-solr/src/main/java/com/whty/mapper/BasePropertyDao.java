package com.whty.mapper;

import com.whty.entity.BaseProperty;

import java.util.List;
import java.util.Map;

public interface BasePropertyDao{

	String getPropertyValue(Map<String, Object> map);

	List<BaseProperty> find(Map<String, Object> map);

	List<BaseProperty> queryBaseProperty(Map<String, Object> para);

	void addBaseProperty(BaseProperty baseProperty);

	void updateBaseProperty(BaseProperty baseProperty);

	void deleteBaseProperty(Map<String, Object> para);

}
