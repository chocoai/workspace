package com.whty.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil {
	// 算法名称
	public static final String KEY_ALGORITHM = "DES";
	// 算法名称/加密模式/填充方式
	// DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
	public static final String CIPHER_ALGORITHM = "DESede/ECB/NoPadding";
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
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("AES");
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

	public static byte[] encrypt2(byte[] data, byte[] key) throws Exception {
		SecretKeySpec aesKey = new SecretKeySpec(data, "AES");
		Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

		aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] results = aesCipher.doFinal(data);
		// 该部分是为了与加解密在线测试网站（http://tripledes.online-domain-tools.com/）的十六进制结果进行核对
		for (int i = 0; i < results.length; i++) {
			System.out.print(results[i] + " ");
		}
		return results;

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
		for (int i = 0; i < results.length; i++) {
			System.out.print(results[i] + " ");
		}
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

	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
		byte[] byte_3 = new byte[byte_1.length + byte_2.length + 8];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		System.arraycopy(byte_1, 0, byte_3, byte_1.length + byte_2.length, 8);
		return byte_3;
	}

	public static byte[] ByteXOR(byte[] parameter1, byte[] parameter2) {
		if (parameter1.length != parameter2.length) {
			System.out.println("不能进行模二加！");
			return null;
		}
		byte[] ByteXOR = new byte[parameter1.length];
		byte temp3;
		for (int i = 0; i < parameter1.length; i++) {
			byte temp1 = parameter1[i];
			byte temp2 = parameter2[i];
			temp3 = (byte) (temp1 ^ temp2);
			ByteXOR[i] = temp3;
		}
		return ByteXOR;
	}

	public static byte[] aesEncrypt(byte[] sSrc, byte[] sKey) throws Exception {

		SecretKey skeySpec = new SecretKeySpec(sKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// "算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		return cipher.doFinal(sSrc);

		// return new
		// Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	private static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
		byte ret = (byte) (_b0 | _b1);
		return ret;
	}

	public static int byteArrayToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
	}

	public static byte[] HexString2Bytes2(String src) {
		byte[] ret = new byte[6];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < 6; ++i) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		// String source = "amigoxie";
		// System.out.println("原文: " + source);
		// String key = "18110910";
		// String encryptData = encrypt(source, key);
		// System.out.println("加密后: " + encryptData);
		// String decryptData = decrypt(encryptData, key);
		// System.out.println("解密后: " + decryptData);

		// byte[] b = new byte[8];
		//
		// Random random = new Random(new Date().getTime());
		// byte[] aa = "111".getBytes("utf-8");
		// String tt = new String(aa, "utf-8");
		//
		// random.nextBytes(b);
		//
		// final byte[] GK = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40,
		// 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB,
		// (byte) 0xDD, 0x55, 0x66, 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
		// 0x40, 0x38 }; // 16字节密钥

		/*
		 * byte[] left = encrypt(b, GK);
		 * 
		 * byte[] right = change(left);// 左半取反
		 * 
		 * byte[] vk = byteMerger(left, right);
		 * 
		 * byte[] identifycode = encrypt("noIs-HFMs-Bp5z0".getBytes(),vk);
		 * byte[] licenceCode2 = decrypt(identifycode,vk);
		 * System.out.println(new String(licenceCode2));
		 */
		// byte[] key=new
		// BASE64Decoder().decodeBuffer("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4");
		// byte[] rand = (new BASE64Decoder()).decodeBuffer("4b3fUc+Qs1w=");
		// String randR = (new BASE64Encoder()).encode(rand);
		// byte[] left = encrypt(rand, GK);
		// byte[] right = change(left);// 左半取反
		// byte[] vk = byteMerger(left, right);
		// byte[] licenceCode = decrypt((new
		// BASE64Decoder()).decodeBuffer("5CDIZFEpzjzz5VOW2+YTNg=="), vk);
		// String lic = new String(licenceCode, "utf-8");
		// System.out.println(lic);

		// List<String> ss = new ArrayList<String>();
		// ss.add("1");
		// ss.add("2");
		// ss.add("3");
		// byte a = 03;
		// int index = Integer.valueOf(a);
		// System.out.println(index);
		// System.out.println(ss.get(index - 1));

		String randomStr = "F14A685D34FD362A516CEEF0963CEF05";

		String s = randomStr.substring(randomStr.length() - 2, randomStr.length());

		int index = Integer.valueOf(s);

		String versionStr = "1.0.160721";

		String newVersionStr = MD5(versionStr);

		System.out.println(newVersionStr);

		byte[] tempVersion = ByteXOR(HexString2Bytes(randomStr), HexString2Bytes(newVersionStr));

		System.out.println(bytesToHexString(tempVersion).toUpperCase());

		String macStr = "B0A595AAF1CA384735A722931D7F1124";

		byte[] mac = aesEncrypt(tempVersion, HexString2Bytes(macStr));

		System.out.println(bytesToHexString(mac).toUpperCase());

	}

}
