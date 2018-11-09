package com.yhcrt.utils;

import java.util.UUID;
/**
 * 生成UUID
 * @author kejunzhong
 * 2017年5月9日
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}
