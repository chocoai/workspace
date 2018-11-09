package com.whty.ebp.manage.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.ebp.api.controller.NewProductAppController;

public class MD5Utils {
	private static final Logger LOGGER = LoggerFactory.getLogger(NewProductAppController.class);

	/**
	 * 获取单个文件的MD5值！
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[10240];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException ex) {
				LOGGER.error("关闭输入输出流异常", ex);
			}
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		String result = bigInt.toString(16);
		int length = result.length();
		if (length < 32) {
			for (int i = 0; i < (32 - length); i++) {
				result = "0" + result;
			}
		}
		return result;
	}

}
