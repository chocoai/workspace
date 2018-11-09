package com.yhcrt.healthcloud.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DictUtil {

	/**
	 * 获取字段值
	 */
	public static String viewByCode(String type, String code) {
		String result = "";
		if (StringUtils.isNotBlank(code)) {
			List<Item> list = DictSet.get(type);
			for (Item item : list) {
				if (item.getKey().equals(code)) {
					result = item.getValue();
					break;
				}
			}
		}
		return result;
	}

	public static final String AREA_TYPE = "area_type";

	public static final String REGION = "region";

	public static final String ALARM_TYPE = "alarm_type";
	
	public static final String IS_READ = "is_read";
	
	public static final String APP_CODE = "apply_name";
	
	public static final String SERVICE_CATEGORY = "service_category";
	
	public static final String SERVICE_TYPE = "service_type";
	
	public static final String PROVIDER_CATEGORY = "provider_category";
	
	public static final String PROVIDER_TYPE = "provider_type";
	
	public static final String RES_TYPE = "res_type";
	
	public static final String IS_GLOBAL = "is_global";
	
	public static final String EMP_SPECIALTY = "emp_specialty";
	
	public static final String DOC_SPECIALTY = "doc_specialty";
	

}
