package com.whty.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HMACSHA1 {
	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";

	private static Map<Integer, String> keyMap;

	static {
		keyMap = new HashMap<Integer, String>();
		keyMap.put(1, SysConfig.getStrValue("mqtt.server.hmac.key1"));
		keyMap.put(2, SysConfig.getStrValue("mqtt.server.hmac.key2"));
		keyMap.put(3, SysConfig.getStrValue("mqtt.server.hmac.key3"));
		keyMap.put(4, SysConfig.getStrValue("mqtt.server.hmac.key4"));
		keyMap.put(5, SysConfig.getStrValue("mqtt.server.hmac.key5"));
	}

	/*
	 * 展示了一个生成指定算法密钥的过程 初始化HMAC密钥
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * public static String initMacKey() throws Exception { //得到一个 指定算法密钥的密钥生成器
	 * KeyGenerator KeyGenerator keyGenerator
	 * =KeyGenerator.getInstance(MAC_NAME); //生成一个密钥 SecretKey secretKey
	 * =keyGenerator.generateKey(); return null; }
	 */

	/**
	 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 * 
	 * @param encryptText
	 *            被签名的字符串
	 * @param encryptKey
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] HmacSHA1Encrypt(String encryptText, Integer keyIndex) throws Exception {
		String encryptKey = keyMap.get(keyIndex);

		byte[] data = encryptKey.getBytes(ENCODING);
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = Mac.getInstance(MAC_NAME);
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);

		byte[] text = encryptText.getBytes(ENCODING);
		// 完成 Mac 操作
		return mac.doFinal(text);
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static byte[] HmacSHA1Encrypt2(String encryptText, String encryptKey) throws Exception {
		byte[] data = encryptKey.getBytes(ENCODING);
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = Mac.getInstance(MAC_NAME);
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);

		byte[] text = encryptText.getBytes(ENCODING);
		// 完成 Mac 操作
		return mac.doFinal(text);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(bytesToHexString(HmacSHA1Encrypt2("11111", "2")));
	}
}
