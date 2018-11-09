package com.yhcrt.iHealthCloud.util;

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

}
