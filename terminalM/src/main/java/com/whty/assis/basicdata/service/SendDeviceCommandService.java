/**
 * 
 */
package com.whty.assis.basicdata.service;

import com.whty.assis.basicdata.model.DeviceInfo;

/**
 * @author zhangzheng
 * @date 2018年6月18日
 */
public interface SendDeviceCommandService {

	public void sendCommand(DeviceInfo bean);

	public void sendCommand(DeviceInfo bean, String excuteTime, String sendText);
}
