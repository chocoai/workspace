package com.whty.assis.basicdata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.whty.common.util.HMACSHA1;
import com.whty.common.util.SysConfig;
import com.whty.common.util.TimeUtils;

import net.sf.json.JSONObject;

public class Command implements Serializable {

	private static final long serialVersionUID = -7731022729255494908L;

	private String action;
	private String type;
	private String deviceCode;
	private String version;
	private String timeStamp;
	private String delayTime;
	private String mac;
	private String message;
	private String token;
	private String param;

	private String excuteTime;

	public Command(String action, String type, String deviceCode, String version, String timeStamp, String excuteTime,
			String delayTime, String mac, String message, String param) {

		this.action = action;
		this.type = type;
		this.deviceCode = deviceCode;
		this.version = version;
		this.timeStamp = timeStamp;
		this.delayTime = delayTime;
		this.mac = mac;
		this.message = message;
		this.param = param;
		this.excuteTime = excuteTime;
		Calendar cal = Calendar.getInstance();
		this.token = Long.toString(cal.getTimeInMillis());
	}

	public String getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(String excuteTime) {
		this.excuteTime = excuteTime;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}

	public String toString() {

		if (this.delayTime == null) {
			this.delayTime = "";
		}

		if (this.action == null) {
			this.action = "";
		}

		if (this.type == null) {
			this.type = "";
		}

		if (this.deviceCode == null) {
			this.deviceCode = "";
		}

		if (this.version == null) {
			this.version = "";
		}

		if (this.mac == null) {
			this.mac = "";
		}

		if (this.timeStamp == null) {
			this.timeStamp = "";
		}

		if (this.message == null) {
			this.message = "";
		}

		if (this.token == null) {
			this.token = "";
		}

		if (this.param == null) {
			this.param = "";
		}

		if (this.excuteTime == null) {
			this.excuteTime = "";
		}

		return String.format(
				"{\\\"action\\\":\\\"%s\\\",\\\"type\\\":\\\"%s\\\",\\\"deviceCode\\\":\\\"%s\\\",\\\"version\\\":\\\"%s\\\",\\\"mac\\\":"
						+ "\\\"%s\\\",\\\"excuteTime\\\":\\\"%s\\\",\\\"delayTime\\\":\\\"%s\\\","
						+ "\\\"timeStamp\\\":\\\"%s\\\",\\\"message\\\":\\\"%s\\\",\\\"token\\\":\\\"%s\\\",\\\"param\\\":%s}",
				this.action, this.type, this.deviceCode, this.version, this.mac, this.excuteTime, this.delayTime,
				this.timeStamp, this.message, this.token, this.param);

	}

	public static void main(String[] args) throws Exception {

		String timeStamp = TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_2);
		String mac = HMACSHA1.bytesToHexString(HMACSHA1.HmacSHA1Encrypt(timeStamp, 1));
		String action = SysConfig.getStrValue("mqtt.server.command.screen_shot");
		String type = SysConfig.getStrValue("mqtt.server.command.type");
		String version = SysConfig.getStrValue("mqtt.server.command.version");

		JSONObject ss = new JSONObject();
		ss.put("key", "msessage");
		ss.put("value", "111245");

		// JSONObject[] sss = new JSONObject();
		List<JSONObject> s = new ArrayList<JSONObject>();

		s.add(ss);

		Command command = new Command(action, type, null, version, 1 + timeStamp, null, null, mac, null, s.toString());

		System.out.println(command.toString());

	}

	public String toJsonString() {
		JSONObject json = new JSONObject();
		json.put("action", this.action);
		json.put("type", this.type);
		json.put("deviceCode", this.deviceCode);
		json.put("version", this.version);
		json.put("mac", this.mac);
		json.put("delayTime", this.delayTime);
		json.put("timeStamp", this.timeStamp);
		json.put("message", this.message);
		json.put("token", this.token);
		json.put("param", this.param);
		return json.toString();
	}

}
