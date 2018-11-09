package com.whty.common.util;

import java.util.HashMap;
import java.util.Map;

public class HeaderUtil {
	/**
	 * 判断手机的操作系统 IOS/android/windows phone/BlackBerry
	 * 
	 * @param UA
	 * @return
	 */
	public static Map<String,String> getMobilOS(String UA) {
		Map<String,String> map = new HashMap<String,String>();
		UA=UA.toUpperCase();
		if (UA == null) {
			return null;
		}
		String iosString = " LIKE MAC OS X";
		String androidString = "ANDROID";
		if (UA.indexOf(iosString) != -1) {// IOS 判断字符串
			map.put("deviceType", "iPhone");
			System.out.println(iosString);
		} else if (UA.contains(androidString)) { // Android 判断
			map.put("deviceType", "android");
			System.out.println(androidString);
		}
		return map;
	}
}
