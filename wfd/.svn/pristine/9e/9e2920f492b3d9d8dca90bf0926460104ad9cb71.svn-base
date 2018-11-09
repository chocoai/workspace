package com.whty.wfd.page.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * \* User: zjd \* Date: 2018/8/17 \* Time: 14:53 \* Description: \
 */
public class TimeUtils {

	SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static String getTimeStr(Date date1, Date date2) {
		long num1 = date1.getTime();
		long num2 = date2.getTime();
		String time = "刚刚";
		if (num1 - num2 < 1000 * 60 * 60) {// 60分钟
			time = (int) ((num1 - num2) / (1000 * 60)) + "分钟前";
		} else if (num1 - num2 >= 1000 * 60 * 60 && num1 - num2 < 1000 * 60 * 60 * 24) {// 大于1小时，小于24小时
			time = (int) ((num1 - num2) / (1000 * 60 * 60)) + "小时前";
		} else if (num1 - num2 >= 1000 * 60 * 60 * 24) {
			time = (int) ((num1 - num2) / (1000 * 60 * 60 * 24)) + "天前";
		}
		return time;
	}
}