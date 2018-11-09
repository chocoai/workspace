package com.whty.mxbj.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

public class CryptoServer {
	// 秘钥算法: RSA非对称加密算法
	private static final String KEY_ALGORITHM = "RSA";

	// BASE64编码
	private static Base64 base64 = new Base64();

	// 签名算法
	private static final String SIGN_ALGORITHM = "SHA1withRSA";

	// 私钥对象
	private PrivateKey privateKey;

	private static CryptoServer instance = null;

	private CryptoServer(String appSecret) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		KeySpec keySpec = new PKCS8EncodedKeySpec(base64.decode(appSecret));
		privateKey = keyFactory.generatePrivate(keySpec);
	}

	public static CryptoServer getInstance(String appSecret) {
		try {
			if (instance != null) {
				return instance;
			}
			instance = new CryptoServer(appSecret);
			return instance;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author xiongxiaofei
	 * @Date 2017/6/1
	 * @Desc 私钥生成数字签名
	 */
	public String signature(String key) {
		try {
			Signature signature = Signature.getInstance(SIGN_ALGORITHM);
			signature.initSign(privateKey);
			signature.update(key.getBytes());
			byte[] data = signature.sign();
			return base64.encodeToString(data);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
