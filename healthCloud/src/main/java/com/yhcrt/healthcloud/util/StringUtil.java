package com.yhcrt.healthcloud.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * String 工具类 Created by shuzheng on 2016/12/07.
 */
public class StringUtil {

	private static Pattern linePattern = Pattern.compile("_(\\w)");
	private static Pattern humpPattern = Pattern.compile("[A-Z]");

	/**
	 * 下划线转驼峰
	 * 
	 * @param str
	 * @return
	 */
	public static String lineToHump(String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);

		str = sb.toString();
		str = str.substring(0, 1).toUpperCase() + str.substring(1);

		return str;
	}

	/**
	 * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
	 * 
	 * @param str
	 * @return
	 */
	public static String humpToLine(String str) {
		return str.replaceAll("[A-Z]", "_$0").toLowerCase();
	}

	/**
	 * 驼峰转下划线,效率比上面高
	 * 
	 * @param str
	 * @return
	 */
	public static String humpToLine2(String str) {
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		if (Character.isUpperCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuffer()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	/**
	 * object转String
	 * 
	 * @param object
	 * @return
	 */
	public static String getString(Object object) {
		return getString(object, "");
	}

	public static String getString(Object object, String defaultValue) {
		if (null == object) {
			return defaultValue;
		}
		try {
			return object.toString();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * object转Integer
	 * 
	 * @param object
	 * @return
	 */
	public static int getInt(Object object) {
		return getInt(object, -1);
	}

	public static int getInt(Object object, Integer defaultValue) {
		if (null == object) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(object.toString());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * object转Boolean
	 * 
	 * @param object
	 * @return
	 */
	public static boolean getBoolean(Object object) {
		return getBoolean(object, false);
	}

	public static boolean getBoolean(Object object, Boolean defaultValue) {
		if (null == object) {
			return defaultValue;
		}
		try {
			return Boolean.parseBoolean(object.toString());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * @Title: getRegPhone
	 * @Description: 将手机号中间四位屏蔽
	 * @param PhoneNo
	 * @return 屏蔽后的手机号
	 */
	public static String getRegPhone(String PhoneNo) {
		String RegPhone = PhoneNo.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		return RegPhone;
	}

	/**
	 * @Title: isEmpty
	 * @Description: 判断字符串是否为空
	 * @param userCode
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	/**
	 * 根据第三个"/"分隔
	 * 
	 * @param str
	 * @return
	 */
	public static String extractString(String str) {
		for (int i = 0; i < 3; i++) {
			str = str.substring(str.indexOf("/") + 1);
		}
		return str;
	}

	/**
	 * 将Integer数组转化为list集合
	 * 
	 * @param ids
	 * @return
	 */
	public static List<Integer> toArray(Integer[] ids) {
		List<Integer> userList = new ArrayList<Integer>();
		Collections.addAll(userList, ids);
		return userList;
	}
	
	/**
	 * 支付宝退款 标识一次退款请求
	 * 采用时间戳
	 * @return
	 */
	public static String getOutRequestNo() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	

}
