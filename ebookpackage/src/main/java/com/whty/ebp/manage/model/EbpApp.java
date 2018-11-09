package com.whty.ebp.manage.model;

import java.io.Serializable;
import java.util.Date;

import com.whty.common.util.CommonFunction;
import com.whty.common.util.SysConfig;

public class EbpApp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4799472833727172825L;

	private String id;// VARCHAR2(32) not null,
	private String app_name;// VARCHAR2(64) not null,
	private String product_id;// VARCHAR2(32) not null,
	private String version_code;// VARCHAR2(32) not null,
	private String inter_version_code;// VARCHAR2(32) not null,
	private String description;// BLOB,
	private String file_path;// VARCHAR2(128) not null,
	private String old_file_name;// VARCHAR2(64),
	private long file_size;// NUMBER,
	private String new_file_name;// VARCHAR2(64),
	private Date create_time;// DATE not null,
	private Date update_time;// DATE not null,
	private String status;// NUMBER(1) default 0 not null,0有效；1无效
	private String can_update;// NUMBER(1),是否可更新，0否；1是
	private String area_code;// VARCHAR2(32),
	private String use_doc_path;// VARCHAR2(128),
	private String old_use_doc_name;// VARCHAR2(64),
	private String new_use_doc_name;// VARCHAR2(64),
	private long use_doc_file_size;//
	private String apk_package_name;// VARCHAR2(64),
	private String ebookpackage_code;// 电子书包平台代码
	private String flat_model_ids;
	private String platform_codes;
	private String flatModel;
	private String platformCode;
	private String baiduBosUrl;
	private String iconUrl;

	private String md5;

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getBaiduBosUrl() {
		return baiduBosUrl;
	}

	public void setBaiduBosUrl(String baiduBosUrl) {
		this.baiduBosUrl = baiduBosUrl;
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

	public String getEbookpackage_code() {
		return ebookpackage_code;
	}

	public void setEbookpackage_code(String ebookpackage_code) {
		this.ebookpackage_code = ebookpackage_code;
	}

	/*
	 * 非数据库字段
	 */
	private String product_name;
	private String product_code;
	private String product_ico_url;
	private String product_type;

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getApk_package_name() {
		return apk_package_name;
	}

	public void setApk_package_name(String apk_package_name) {
		this.apk_package_name = apk_package_name;
	}

	public String getProduct_ico_url() {

		// if ("5".equals(product_type)) {
		// product_ico_url = iconUrl;
		//
		// if (null == product_ico_url) {
		// product_ico_url = SysConfig.getStrValue("file_path_http")
		// + product_ico_url.replace(SysConfig.getStrValue("file_path_pre"),
		// "");
		// }

		// } else {
		if (null != product_ico_url)

			if (product_ico_url.contains("http")) {
				return product_ico_url;
			} else {
				product_ico_url = SysConfig.getStrValue("file_path_http")
						+ product_ico_url.replace(SysConfig.getStrValue("file_path_pre"), "");
			}

		// }

		return product_ico_url;
	}

	public void setProduct_ico_url(String product_ico_url) {
		this.product_ico_url = product_ico_url;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getOld_file_name() {
		return old_file_name;
	}

	public void setOld_file_name(String old_file_name) {
		this.old_file_name = old_file_name;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public String getNew_file_name() {
		return new_file_name;
	}

	public void setNew_file_name(String new_file_name) {
		this.new_file_name = new_file_name;
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

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getUse_doc_path() {
		return use_doc_path;
	}

	public void setUse_doc_path(String use_doc_path) {
		this.use_doc_path = use_doc_path;
	}

	public String getOld_use_doc_name() {
		return old_use_doc_name;
	}

	public void setOld_use_doc_name(String old_use_doc_name) {
		this.old_use_doc_name = old_use_doc_name;
	}

	public String getNew_use_doc_name() {
		return new_use_doc_name;
	}

	public void setNew_use_doc_name(String new_use_doc_name) {
		this.new_use_doc_name = new_use_doc_name;
	}

	public long getUse_doc_file_size() {
		return use_doc_file_size;
	}

	public void setUse_doc_file_size(long use_doc_file_size) {
		this.use_doc_file_size = use_doc_file_size;
	}

	public String getCreateTimeString() {
		return CommonFunction.getTimeSampleString(this.create_time);
	}

	public String getUpdateTimeString() {
		return CommonFunction.getTimeSampleString(this.update_time);
	}

}
