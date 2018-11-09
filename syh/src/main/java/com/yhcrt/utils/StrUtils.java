package com.yhcrt.utils;

/**
 * 用于处理字符串
 * @author kejunzhong
 * 2017年5月24日
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class StrUtils {
	
	/**
	 * 以“，”分割字符串
	 * @param str
	 * @return
	 */
	public static String[] getSplit(String str) {
		String[] sourceStrArray=str.split(",");
		return sourceStrArray;
	}
	
}
