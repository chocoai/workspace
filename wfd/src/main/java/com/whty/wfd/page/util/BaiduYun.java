package com.whty.wfd.page.util;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;

import java.util.Calendar;

/**
 * \* User: zjd \* Date: 2018/9/26 \* Time: 14:29 \* Description: \
 */
public class BaiduYun {

	private static String ak = "08da4fd5cf644d458c950b09bdb95666";
	private static String sk = "45876e2c7caf404a8e3f269d7aabb835";
	private static String bucketname = "whty";
	private static String pathProductName = "hdcourse";
	private static String pathVersion = "test";
	private static String baiduBosUrl = "http://whty.bj.bcebos.com/";

	public static String upload(byte[] imageByte, String uploadType) {
		Calendar cal = Calendar.getInstance();
		StringBuffer bosStringBuffer = new StringBuffer();
		bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
				.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH)).append(cal.get(Calendar.DAY_OF_MONTH))
				.append("/").append(cal.getTime().getTime());
		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ak, sk));
		BosClient client = new BosClient(config);
		client.putObject(bucketname, bosStringBuffer + uploadType, imageByte);// 上传到百度云
		String bosAddress = baiduBosUrl + bosStringBuffer + uploadType;// 图标地址
		System.out.println(bosAddress);
		return bosAddress;
	}
}