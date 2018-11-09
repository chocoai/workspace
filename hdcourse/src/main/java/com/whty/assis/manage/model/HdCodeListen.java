package com.whty.assis.manage.model;

import java.io.Serializable;

public class HdCodeListen implements Serializable {

	private static final long serialVersionUID = -586702171685638886L;

	private Integer id;
	private Integer codeId;
	private String licenceCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public String getLicenceCode() {
		return licenceCode;
	}

	public void setLicenceCode(String licenceCode) {
		this.licenceCode = licenceCode;
	}
}