package com.whty.assis.api.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EbpUserClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private String userId;
	private String content;
	private String type;
	private String platformCode;
	private String contentReal;
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getContentReal() {
		return contentReal;
	}

	public void setContentReal(String contentReal) {
		this.contentReal = contentReal;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

}