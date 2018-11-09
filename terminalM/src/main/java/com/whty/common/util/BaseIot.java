/**
 * 
 */
package com.whty.common.util;

import com.aliyun.iot.client.IotClient;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.RpcAcsRequest;

/**
 * @author zhangzheng
 * @date 2018年4月3日
 */
public class BaseIot {
	
	private static DefaultAcsClient client;

	static {
		client = IotClient.getClient();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static AcsResponse executeTest(RpcAcsRequest request) {
		AcsResponse response = null;
		try {
			response = client.getAcsResponse(request);
		} catch (Exception e) {
			LogUtil.print("执行失败：e:" + e.getMessage());
		}
		return response;
	}
}
