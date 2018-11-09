/**
 * 
 */
package com.whty.assis.basicdata.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年4月6日
 */
public class DeviceMonitorLog extends BaseModel {

	private static final long serialVersionUID = -5997183176919325219L;

	private Integer deviceId;
	private String path;
	private String ip;
	private String aliIotId;
	private String aliDeviceName;
	private String aliProductKey;
	private String clientVersion;
	private String aliDeviceSecret;

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAliIotId() {
		return aliIotId;
	}

	public void setAliIotId(String aliIotId) {
		this.aliIotId = aliIotId;
	}

	public String getAliDeviceName() {
		return aliDeviceName;
	}

	public void setAliDeviceName(String aliDeviceName) {
		this.aliDeviceName = aliDeviceName;
	}

	public String getAliProductKey() {
		return aliProductKey;
	}

	public void setAliProductKey(String aliProductKey) {
		this.aliProductKey = aliProductKey;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getAliDeviceSecret() {
		return aliDeviceSecret;
	}

	public void setAliDeviceSecret(String aliDeviceSecret) {
		this.aliDeviceSecret = aliDeviceSecret;
	}

}
