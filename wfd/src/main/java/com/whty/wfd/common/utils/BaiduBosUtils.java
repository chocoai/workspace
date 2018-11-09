package com.whty.wfd.common.utils;

import java.util.Calendar;

public class BaiduBosUtils {

	/**
	 * 根据文件名生成bos的key
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getKey(String userId, String fileName) {
		StringBuffer bosStringBuffer = new StringBuffer();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);

		bosStringBuffer.append("zhkt").append(SysConfigUtils.getStrValue("projectName")).append("/").append(year)
				.append("/").append(month).append("/").append(date).append("/").append(userId).append("/")
				.append(fileName);

		return bosStringBuffer.toString();
	}

}
