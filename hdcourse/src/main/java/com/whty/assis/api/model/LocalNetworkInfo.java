package com.whty.assis.api.model;

import java.io.Serializable;

public class LocalNetworkInfo implements Serializable {

	private static final long serialVersionUID = 2682705102715069764L;
	private String guid;
	private String ip;
	private String ssid;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

}
