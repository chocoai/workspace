package com.yhcrt.weihu.common.security.encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import com.yhcrt.weihu.common.security.encoder.PwdEncoder;

/**
 * MD5密码加密
 * 
 * @author tom
 * 
 */
public class Md5PwdEncoder implements PwdEncoder {
	public String encodePassword(String rawPass) {
		return encodePassword(rawPass, defaultSalt);
	}

	public String encodePassword(String rawPass, String salt) {
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

	public boolean isPasswordValid(String encPass, String rawPass) {
		return isPasswordValid(encPass, rawPass, defaultSalt);
	}

	public boolean isPasswordValid(String encPass, String rawPass, String salt) {
		if (encPass == null) {
			return false;
		}
		String pass2 = encodePassword(rawPass, salt);
		return encPass.equals(pass2);
	}

	protected final MessageDigest getMessageDigest() {
		String algorithm = "MD5";
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("No such algorithm ["
					+ algorithm + "]");
		}
	}

	/**
	 * Used by subclasses to extract the password and salt from a merged
	 * <code>String</code> created using
	 * {@link #mergePasswordAndSalt(String,Object,boolean)}.
	 * <p>
	 * The first element in the returned array is the password. The second
	 * element is the salt. The salt array element will always be present, even
	 * if no salt was found in the <code>mergedPasswordSalt</code> argument.
	 * </p>
	 * 
	 * @param mergedPasswordSalt
	 *            as generated by <code>mergePasswordAndSalt</code>
	 * 
	 * @return an array, in which the first element is the password and the
	 *         second the salt
	 * 
	 * @throws IllegalArgumentException
	 *             if mergedPasswordSalt is null or empty.
	 */
	protected String mergePasswordAndSalt(String password, Object salt,
			boolean strict) {
		if (password == null) {
			password = "";
		}
		if (strict && (salt != null)) {
			if ((salt.toString().lastIndexOf("{") != -1)
					|| (salt.toString().lastIndexOf("}") != -1)) {
				throw new IllegalArgumentException(
						"Cannot use { or } in salt.toString()");
			}
		}
		if ((salt == null) || "".equals(salt)) {
			return password;
		} else {
			return password + "{" + salt.toString() + "}";
		}
	}

	/**
	 * 混淆码。防止破解。
	 */
	private String defaultSalt;

	/**
	 * 获得混淆码
	 * 
	 * @return
	 */
	public String getDefaultSalt() {
		return defaultSalt;
	}

	/**
	 * 设置混淆码
	 * 
	 * @param salt
	 */
	public void setDefaultSalt(String defaultSalt) {
		this.defaultSalt = defaultSalt;
	}
}
