/**
 * 
 */
package com.whty.assis.basicdata.service.impl.devicecommand;

import java.util.Date;
import java.util.Random;

import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.InvokeThingServiceRequest;
import com.aliyuncs.iot.model.v20180120.InvokeThingServiceResponse;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailRequest;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.whty.assis.basicdata.model.Command;
import com.whty.assis.basicdata.model.DeviceCommandLog;
import com.whty.assis.basicdata.model.DeviceInfo;
import com.whty.assis.basicdata.service.SendDeviceCommandService;
import com.whty.common.thread.DeviceCommandLogThread;
import com.whty.common.util.HMACSHA1;
import com.whty.common.util.SysConfig;
import com.whty.common.util.TimeUtils;

/**
 * @author zhangzheng
 * @date 2018年6月18日
 */
public class SendMessageCommandServiceImpl implements SendDeviceCommandService {

	public void sendCommand(DeviceInfo bean, String excuteTime, String sendText) {
		String regionId = SysConfig.getStrValue("iot.regionId");
		String accessKeyID = SysConfig.getStrValue("user.accessKeyID");
		String accessKeySecret = SysConfig.getStrValue("user.accessKeySecret");
		String domain = SysConfig.getStrValue("iot.domain");

		try {
			IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
			DefaultProfile.addEndpoint(regionId, regionId, bean.getTerminalProductKey(), domain);
			DefaultAcsClient client = new DefaultAcsClient(profile);

			QueryDeviceDetailRequest queryDeviceDetailRequest = new QueryDeviceDetailRequest();
			queryDeviceDetailRequest.setProductKey(bean.getAliProductKey());
			queryDeviceDetailRequest.setDeviceName(bean.getAliDeviceName());
			queryDeviceDetailRequest.setIotId(bean.getAliIotId());

			QueryDeviceDetailResponse registerDeviceResponse = client.getAcsResponse(queryDeviceDetailRequest);

			if (registerDeviceResponse != null && registerDeviceResponse.getSuccess() != false) {
				String status = registerDeviceResponse.getData().getStatus();

				// 设备在线才发送命令
				if ("online".equals(status.toLowerCase())) {
					InvokeThingServiceRequest invokeThingServiceRequest = new InvokeThingServiceRequest();
					invokeThingServiceRequest.setProductKey(bean.getProvinceCode());
					invokeThingServiceRequest.setIotId(bean.getAliIotId());
					invokeThingServiceRequest.setDeviceName(bean.getDeviceName());
					invokeThingServiceRequest.setIdentifier("commandService");

					Random random = new Random(new Date().getTime());
					int keyIndex = random.nextInt(5) % (5 - 1 + 1) + 1;

					String timeStamp = TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_2);
					String mac = HMACSHA1.bytesToHexString(HMACSHA1.HmacSHA1Encrypt(timeStamp, keyIndex));
					String action = SysConfig.getStrValue("mqtt.server.command.broadcast_message");
					String type = SysConfig.getStrValue("mqtt.server.command.type");
					String version = SysConfig.getStrValue("mqtt.server.command.version");

					String para = String.format(
							"[{\\\"key\\\":\\\"delayTime\\\",\\\"value\\\":\\\"%s\\\"},{\\\"key\\\":\\\"excuteTime\\\",\\\"value\\\":\\\"%s\\\"},{\\\"key\\\":\\\"message\\\",\\\"value\\\":\\\"%s\\\"}]",
							0, excuteTime, sendText);
					Command command = new Command(action, type, null, version, keyIndex + timeStamp, null, null, mac,
							null, para);
					String str = null;
					str = String.format("{%s:\"%s\"}", "commandService", command.toString());
					invokeThingServiceRequest.setArgs(str);

					InvokeThingServiceResponse invokeThingServiceResponse = client
							.getAcsResponse(invokeThingServiceRequest);

					if (invokeThingServiceResponse != null && invokeThingServiceResponse.getSuccess() != false) {
						LogUtil.print("调用命令推送成功！ "
								+ com.alibaba.fastjson.JSONObject.toJSONString(invokeThingServiceResponse));
					} else {
						LogUtil.error("调用命令推送失败！！requestId:" + invokeThingServiceResponse.getRequestId() + "原因："
								+ invokeThingServiceResponse.getErrorMessage());
					}
					DeviceCommandLog deviceCommandLog = new DeviceCommandLog();
					deviceCommandLog.setDeviceId(bean.getId());
					deviceCommandLog.setRequestText(str);
					deviceCommandLog
							.setResponseText(com.alibaba.fastjson.JSONObject.toJSONString(invokeThingServiceResponse));
					DeviceCommandLogThread.add(deviceCommandLog);
				}
			}
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.SendDeviceCommandService#sendCommand(com
	 * .whty.assis.basicdata.model.DeviceInfo)
	 */
	@Override
	public void sendCommand(DeviceInfo bean) {

	}

}
