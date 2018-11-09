package com.whty.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class MD5Filter {

	public static String getMd5ByFile(File file) {
		String value = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
			while (value.length() < 32) {
				value = "0" + value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	/*
	 * 根据文件路径生成MD5
	 */
	public static String getMd5ByPath(String path) {
		String md5 = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return md5;
	}

	public static void main(String[] args) {
		String v = MD5Filter.getMd5ByFile(new File("e:\\test\\Preparation.Update.dll"));
		System.out.println(v.length() + "--------" + v);
		v = MD5Filter.getMd5ByFile(new File("e:\\test\\aa\\Preparation.Update.dll"));
		System.out.println(v.length() + "--------" + v);

		// try {
		// FileInputStream fis= new
		// FileInputStream("e:\\test\\Preparation.Play.exe");
		// v = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
		// System.out.println(v.length()+"--------"+v);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}