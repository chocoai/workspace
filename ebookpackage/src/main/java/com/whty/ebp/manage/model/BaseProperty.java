package com.whty.ebp.manage.model;

import java.io.Serializable;
import java.util.Date;

public class BaseProperty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;// VARCHAR2(32) not null,
	private String property_key;// VARCHAR2(32) not null,
	private String property_value;// VARCHAR2(132) not null,
	private String status;// VARCHAR2(2) default 0 not null,
	private String description;// VARCHAR2(132),
	private Date create_time;// DATE default sysdate,
	private String platform_code;// VARCHAR2(8) not null,
	private String platform_name;// VARCHAR2(32),
	private Date update_time;// DATE default sysdate,
	private String property_type_name;//

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProperty_key() {
		return property_key;
	}

	public void setProperty_key(String property_key) {
		this.property_key = property_key;
	}

	public String getProperty_value() {
		return property_value;
	}

	public void setProperty_value(String property_value) {
		this.property_value = property_value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getPlatform_code() {
		return platform_code;
	}

	public void setPlatform_code(String platform_code) {
		this.platform_code = platform_code;
	}

	public String getPlatform_name() {
		return platform_name;
	}

	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getProperty_type_name() {
		return property_type_name;
	}

	public void setProperty_type_name(String property_type_name) {
		this.property_type_name = property_type_name;
	}

}
