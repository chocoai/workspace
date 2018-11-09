package com.whty.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckMobileAndEmail {

	/**
	 * 验证邮箱地址是否正确 @param email @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return [0-9]{5,9}
	 */
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 检查是否数字
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isNum(String number) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("[0-9]*");
			Matcher m = p.matcher(number);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static void main(String[] args) {
		System.out.println("email:" + checkEmail("wujunbo007@163.com"));
		System.out.println("mobile:" + isMobileNO("18827378849"));
		System.out.println("mobile:" + isNum("1882737884"));
	}
}
