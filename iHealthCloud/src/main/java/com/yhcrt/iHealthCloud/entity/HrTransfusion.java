package com.yhcrt.iHealthCloud.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class HrTransfusion {
	
	@JSONField(serialize = false)
	private Integer cId;
	
	@JSONField(serialize = false)
	private Integer recordId;

	private String transfusionName;

	private String transfusionTime;
	
	@JSONField(serialize = false)
	private Integer status;

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getTransfusionName() {
		return transfusionName;
	}

	public void setTransfusionName(String transfusionName) {
		this.transfusionName = transfusionName == null ? null : transfusionName.trim();
	}

	public String getTransfusionTime() {
		return transfusionTime;
	}

	public void setTransfusionTime(String transfusionTime) {
		this.transfusionTime = transfusionTime == null ? null : transfusionTime.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}