package com.whty.assis.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.manage.dao.BasePropertyDao;
import com.whty.assis.manage.model.BaseProperty;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.cache.data.CacheDataUtils;
import com.whty.common.util.CommonFunction;
import com.whty.page.util.HandlerResult;

@Service
public class BasePropertyServiceImpl implements BasePropertyService {

	@Autowired
	private BasePropertyDao basePropertyDao;

	/*
	 * 根据属性key和平台编码获取属性值
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getPropertyValue(String propertyKey, String platformCode) {
		String propertyValue = null;
		try {
			propertyValue = CacheDataUtils.getData("BaseProperty", propertyKey + "_" + platformCode);
			if (propertyValue == null || propertyValue.trim().equals("")) {
				// 查询数据库
				Map map = new HashMap();
				map.put("property_key", propertyKey);
				map.put("platform_code", platformCode);
				propertyValue = basePropertyDao.getPropertyValue(map);
				if (null != propertyValue) {
					CacheDataUtils.setData("BaseProperty", propertyKey + "_" + platformCode, propertyValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}
}
