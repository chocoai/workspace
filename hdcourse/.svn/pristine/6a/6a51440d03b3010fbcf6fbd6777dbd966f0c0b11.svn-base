package com.whty.common.cache.data;

import org.springframework.beans.factory.annotation.Autowired;

import com.whty.assis.api.controller.DataController;
import com.whty.assis.manage.service.BasePropertyService;

/*
 * 获取缓存基础数据
 */
public class GetCacheBaseData {

	@Autowired
	private static BasePropertyService basePropertyService;

	/*
	 * 获取属性配置表属性值
	 */
	public static String getPropertyValue(String propertyKey, String platformCode) {
		String propertyValue = null;
		try {
			propertyValue = CacheDataUtils.getData("BaseProperty", propertyKey + "_" + platformCode);
			// if(propertyValue==null){
			// DataController.set.add(platformCode);
			// }
			// System.out.println("-----------------------取缓存数据--BaseProperty--"
			// + propertyKey + "_" + platformCode + ":"
			// + propertyValue + "---------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}

}
