package com.whty.assis.api.utils;

import java.util.Map;

public class RSACoderTest {

	private static String publicKey;
	private static String privateKey;

	public static void setUp() throws Exception {
		Map<String, Object> keyMap = RSACoder.initKey();

		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		System.err.println("公钥: \n\r" + publicKey);
		System.err.println("私钥： \n\r" + privateKey);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

		testSign();

	}

	private static void testSign() {
		try {
			System.err.println("私钥加密——公钥解密");
			String inputStr = "sign";
			byte[] data = inputStr.getBytes();

			byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
			String encodedDataStr = RSACoder.encryptByPrivateKey(inputStr, privateKey);
			System.out.println("加密后：\r\n" + encodedData);
			System.out.println("加密后：\r\n" + encodedDataStr);

			byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);

			String outputStr = new String(decodedData);
			System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);

			System.err.println("私钥签名——公钥验证签名");
			// 产生签名
			String sign = RSACoder.sign(encodedData, privateKey);
			System.err.println("签名:\r" + sign);

			// 验证签名
			boolean status = RSACoder.verify(encodedData, publicKey, sign);
			System.err.println("状态:\r" + status);
			System.out.println(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
