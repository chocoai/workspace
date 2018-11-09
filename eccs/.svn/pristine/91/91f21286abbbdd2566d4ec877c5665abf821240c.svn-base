package com.smart.util;

import java.io.File;

public class AppUtil {

	public static final Integer DGBZ = 6; // 底稿编制

	public static final String SUPERADMIN = "administrator"; // 超级管理员帐号

	/**
	 * 创建文件夹
	 * 
	 * @param dir
	 */
	public static void createDir(String dir) {
		File dirTemp = new File(dir);
		if (!dirTemp.exists()) {
			dirTemp.mkdirs();
		}
	}

	/**
	 * 生成序列编号
	 * 
	 * @param lastId
	 *            最近的兄弟节点
	 * @return
	 */
	public static String createMenuId(String pid, String lastId) {
		if (StringUtil.isBlank(lastId)) {
			return pid.equals("0") ? "001" : pid + "001";
		} else {
			String startCode = lastId.substring(0, lastId.length() - 3);
			String endCode = lastId.substring(lastId.length() - 3);
			Integer num = Integer.parseInt(endCode) + 1;
			if (num < 10) { // 一位数
				return startCode + "00" + num;
			} else if (num < 100) { // 二位数
				return startCode + "0" + num;
			} else { // 三位数
				return startCode + num;
			}
		}
	}
}
