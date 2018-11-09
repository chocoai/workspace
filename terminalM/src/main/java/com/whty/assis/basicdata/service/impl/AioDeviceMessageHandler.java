/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.Map;

import com.whty.assis.basicdata.service.DeviceMessageHandler;
import com.whty.assis.iot.IotConsumerMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年5月28日
 */
public class AioDeviceMessageHandler implements DeviceMessageHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.DeviceMessageHandler#handler(com.whty.
	 * test.IotConsumerMessage)
	 */
	@Override
	public void handler(IotConsumerMessage iotConsumerMessage) {
		Map<String, Object> valueMap = iotConsumerMessage.getValue();

		// Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (key.equals("sEventContent")) {
				JSONObject sEventContent = JSONObject.fromObject(value);

				String statisBeginDateTime = sEventContent.optString("statisBeginDateTime");
				int statisDuration = sEventContent.optInt("statisDuration");

				JSONArray statisResult = sEventContent.optJSONArray("statisResult");

				for (int i = 0; i < statisResult.size(); i++) {

					JSONObject appResult = statisResult.optJSONObject(i);

					String appName = appResult.optString("appName");
					String appVersion = appResult.optString("appVersion");
					int useDuration = appResult.optInt("useDuration");
					
					
					
				}

			}

		}
	}
}
