package com.yhcrt.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * 获取本机IP地址
 * @author kejunzhong
 * 2017年5月9日
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class PublicUtil {

	public static void main(String[] args) {
		System.out.println("本机的ip=" + PublicUtil.getIp());
	}

	public static String getPorjectPath() {
		String nowpath = "";
		nowpath = System.getProperty("user.dir") + "/";

		return nowpath;
	}

	/**
	 * 获取本机ip
	 * @return
	 */
	public static String getIp() {
		String ip = "";
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
			// System.out.println("本机的ip=" + ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return ip;
	}

}