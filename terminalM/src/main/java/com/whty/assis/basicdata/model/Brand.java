package com.whty.assis.basicdata.model;

import com.whty.assis.base.model.BaseModel;

/**
 * 品牌型号
 * 
 * @author zhangzheng
 * 
 */
public class Brand extends BaseModel {

	private static final long serialVersionUID = -8554481061704834011L;
	private Integer terminalDeviceTypeId;
	private String brandName;
	private String modelName;
	private String vendor;
	private Integer creator;
	private String creatorName;

	private String memo;

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getTerminalDeviceTypeId() {
		return terminalDeviceTypeId;
	}

	public void setTerminalDeviceTypeId(Integer terminalDeviceTypeId) {
		this.terminalDeviceTypeId = terminalDeviceTypeId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

}
