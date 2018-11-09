package com.whty.assis.manage.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.whty.common.util.MD5Filter;

public class DESEDEUtil {
	// 算法名称
	public static final String KEY_ALGORITHM = "DES";
	// 算法名称/加密模式/填充方式
	// DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
	public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";
	// public static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

	/**
	 * 
	 * 生成密钥key对象
	 * 
	 * @param KeyStr
	 *            密钥字符串
	 * @return 密钥对象
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 */
	private static SecretKey keyGenerator(byte[] keyStr) throws Exception {
		// byte input[] = HexString2Bytes(keyStr);
		DESedeKeySpec desKey = new DESedeKeySpec(keyStr);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		return securekey;
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return 加密后的数据
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		Key deskey = keyGenerator(key);
		// 实例化Cipher对象，它用于完成实际的加密操作
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		SecureRandom random = new SecureRandom();
		// 初始化Cipher对象，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] results = cipher.doFinal(data);
		// 该部分是为了与加解密在线测试网站（http://tripledes.online-domain-tools.com/）的十六进制结果进行核对
		// for (int i = 0; i < results.length; i++) {
		// System.out.print(results[i] + " ");
		// }
		return results;
		// System.out.println();
		// 执行加密操作。加密后的结果通常都会用Base64编码进行传输
		// return Base64.encodeBase64String(results);
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return 解密后的数据
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		Key deskey = keyGenerator(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化Cipher对象，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		return cipher.doFinal(data);
		// 执行解密操作
		// return new String(cipher.doFinal(Base64.decodeBase64(data)));
	}

	/**
	 * byte数组转换成16进制字符串
	 * 
	 * @param src
	 * @return
	 */
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

	// 从十六进制字符串到字节数组转换
	public static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	public static byte[] change(byte[] bytes) {
		byte temp;
		byte[] temps = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			temp = bytes[i];
			temps[i] = (byte) (~temp);
		}
		return temps;
	}

	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		// byte[] byte_3 = new byte[byte_1.length + byte_2.length + 8];
		// System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		// System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		// System.arraycopy(byte_1, 0, byte_3, byte_1.length + byte_2.length,
		// 8);

		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	public static void main(String[] args) throws Exception {
		// String source = "amigoxie";
		// System.out.println("原文: " + source);
		// String key = "A1B2C3D4E5F60708";
		// String encryptData = encrypt(source, key);
		// System.out.println("加密后: " + encryptData);
		// String decryptData = decrypt(encryptData, key);
		// System.out.println("解密后: " + decryptData);

		byte[] b = new byte[] { 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79 };
		System.out.println("b: " + new String(Base64.encodeBase64(b), "UTF-8"));
		// Random random = new Random(new Date().getTime());

		final byte[] GK = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB,
				(byte) 0xDD, 0x55, 0x66, 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38 }; // 16字节密钥

		byte[] left = encrypt(b, GK);

		byte[] right = change(left);// 左半取反

		byte[] vk = byteMerger(b, right);

		byte[] aa = "noIs-HFMs-Bp5z".getBytes("utf-8");
		String tt = new String(aa, "utf-8");

		System.out.println("ttmd5");
		// System.out.println(MD5Filter.getMD5ByString(tt));

		byte[] identifycode = encrypt(tt.getBytes(), vk);

		System.out.println("identifycode: " + new String(Base64.encodeBase64(identifycode), "UTF-8"));

		byte[] licenceCode2 = decrypt(identifycode, vk);
		System.out.println();
		System.out.println(new String(licenceCode2));

		// byte[] key=new
		// BASE64Decoder().decodeBuffer("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4");
		// byte[] rand = (new BASE64Decoder()).decodeBuffer("EO+POSElt28=");
		// // String randR = (new BASE64Encoder()).encode(rand);
		// byte[] left = encrypt(rand, GK);
		// byte[] right = change(left);// 左半取反
		// // byte[] vk = byteMerger(left, right);
		// byte[] vk = byteMerger(rand, right);
		// byte[] licenceCode = decrypt((new
		// BASE64Decoder()).decodeBuffer("jjJM9zSJjBU="), vk);
		// String lic = new String(licenceCode, "utf-8");
		// System.out.println(lic);
		//
		// byte[] lics = lic.getBytes("utf-8");
		// MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// // 使用指定的字节更新摘要
		// mdInst.update(lics);
		// // 获得密文
		// byte[] md = mdInst.digest();
		// String md5 = (new BASE64Encoder()).encode(md);
		// System.out.println("");

	}
}
