package com.whty.ebp.manage.model;

import java.io.Serializable;
import java.util.Date;

public class DerivativeAppApi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String app_name;
	private String derivative_type;
	private String version_code;
	private String inter_version_code;
	private Integer file_size;
	private Date create_time;
	private Date update_time;
	private String status;
	private String can_update;
	private String description;
	private String apk_package_name;
	private String baiduBosUrl;
	private String icon_url;
	private String tmp_file_path;

	private String flatModel;
	private String platformCode;

	private String platform_codes;
	private String flat_model_ids;

	private String product_ico_url;
	private String product_name;
	private String product_id;
	private String product_type;

	private String md5;

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getDerivative_type() {
		return derivative_type;
	}

	public void setDerivative_type(String derivative_type) {
		this.derivative_type = derivative_type;
	}

	public String getVersion_code() {
		return version_code;
	}

	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}

	public String getInter_version_code() {
		return inter_version_code;
	}

	public void setInter_version_code(String inter_version_code) {
		this.inter_version_code = inter_version_code;
	}

	public Integer getFile_size() {
		return file_size;
	}

	public void setFile_size(Integer file_size) {
		this.file_size = file_size;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCan_update() {
		return can_update;
	}

	public void setCan_update(String can_update) {
		this.can_update = can_update;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApk_package_name() {
		return apk_package_name;
	}

	public void setApk_package_name(String apk_package_name) {
		this.apk_package_name = apk_package_name;
	}

	public String getBaiduBosUrl() {
		return baiduBosUrl;
	}

	public void setBaiduBosUrl(String baiduBosUrl) {
		this.baiduBosUrl = baiduBosUrl;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getTmp_file_path() {
		return tmp_file_path;
	}

	public void setTmp_file_path(String tmp_file_path) {
		this.tmp_file_path = tmp_file_path;
	}

	public String getFlatModel() {
		return flatModel;
	}

	public void setFlatModel(String flatModel) {
		this.flatModel = flatModel;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getPlatform_codes() {
		return platform_codes;
	}

	public void setPlatform_codes(String platform_codes) {
		this.platform_codes = platform_codes;
	}

	public String getFlat_model_ids() {
		return flat_model_ids;
	}

	public void setFlat_model_ids(String flat_model_ids) {
		this.flat_model_ids = flat_model_ids;
	}

	public String getProduct_ico_url() {
		return icon_url;
	}

	public String getProduct_name() {
		return app_name;
	}

	public String getProduct_id() {
		return id;
	}

	public String getProduct_type() {
		return "2";
	}

}
