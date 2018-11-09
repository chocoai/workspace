package com.yhcrt.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * MD5密码加密
 */
public class Md5PwdUtil{
	
	public static void main(String[] args) {
		System.out.println(encodePassword("123456"));
	}
	/**
	 * 密码加密
	 * 
	 * @param rawPass
	 *            未加密密码，null作为空串
	 * @return 加密后密码
	 */
    public static  String encodePassword(String rawPass) {
    	return encodePassword(rawPass,null);
    }
	/**
	 * 密码加密
	 * 
	 * @param rawPass
	 *            未加密密码，null作为空串
	 * @param salt
	 *            混淆码
	 * @return
	 */
    public static String encodePassword(String rawPass, String salt) {
		String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
		MessageDigest messageDigest = getMessageDigest();
		byte[] digest;
		try {
		    digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		    throw new IllegalStateException("UTF-8 not supported!");
		}
	return new String(Hex.encodeHex(digest));
    }
	/**
	 * 验证密码是否正确
	 * 
	 * @param encPass
	 *            加密密码
	 * @param rawPass
	 *            未加密密码，null作为空串
	 * @return true:密码正确；false:密码错误
	 */
    public boolean isPasswordValid(String encPass, String rawPass) {
		return isPasswordValid(encPass, rawPass, null);
    }

	/**
	 * 验证密码是否正确
	 * 
	 * @param encPass
	 *            加密密码
	 * @param rawPass
	 *            未加密密码，null作为空串
	 * @param salt
	 *            混淆码
	 * @return true:密码正确；false:密码错误
	 */
	public boolean isPasswordValid(String encPass, String rawPass, String salt) {
		if (encPass == null) {
			return false;
		}
		String pass2 = encodePassword(rawPass, salt);
		return encPass.equals(pass2);
	}

    protected final static MessageDigest getMessageDigest() {
		String algorithm = "MD5";
		try {
		    return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
		    throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
		}
    }

    protected static String mergePasswordAndSalt(String password, Object salt, boolean strict) {
		if (password == null) {
		    password = "";
		}
		if (strict && (salt != null)) {
		    if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {
			throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
		    }
		}
		if ((salt == null) || "".equals(salt)) {
		    return password;
		} else {
		    return password + "{" + salt.toString() + "}";
		}
    }
}
