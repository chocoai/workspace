package com.whty.ebp.manage.model;

import java.util.Date;

import com.whty.common.util.SysConfig;

public class Product {

	private String id;
	private String productName;
	private String productType;
	private String description;
	private String qrCodePath;
	private Date createTime;
	private String status;
	private Date updateTime;
	private String icoPath;
	private String icoOldName;
	private String icoNewName;

	private String productCode;

	private String ebookpackageCode;// 电子书包平台代码

	public String getEbookpackageCode() {
		return ebookpackageCode;
	}

	public void setEbookpackageCode(String ebookpackageCode) {
		this.ebookpackageCode = ebookpackageCode;
	}

	/*
	 * 非数据库字段
	 */
	private String qrCodeUrl;
	private String app_download_url;

	public String getApp_download_url() {
		app_download_url = SysConfig.getStrValue("app_download_url") + id;
		return app_download_url;
	}

	public void setApp_download_url(String app_download_url) {
		this.app_download_url = app_download_url;
	}

	public String getQrCodeUrl() {
		if (null != qrCodePath && !qrCodePath.trim().equals("")) {
			qrCodeUrl = SysConfig.getStrValue("file_path_http")
					+ qrCodePath.replace(SysConfig.getStrValue("file_path_pre"), "");
			qrCodeUrl = qrCodeUrl.replace("\\", "/");
		}
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIcoPath() {
		return icoPath;
	}

	public void setIcoPath(String icoPath) {
		this.icoPath = icoPath;
	}

	public String getIcoOldName() {
		return icoOldName;
	}

	public void setIcoOldName(String icoOldName) {
		this.icoOldName = icoOldName;
	}

	public String getIcoNewName() {
		return icoNewName;
	}

	public void setIcoNewName(String icoNewName) {
		this.icoNewName = icoNewName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

}
