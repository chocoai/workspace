package com.yhcrt.iHealthCloud.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

public class YwImage {

	@JSONField(serialize = false)
	private Integer imgId;
	
	@JSONField(serialize = false)
	private Integer refId;
	
	@JSONField(serialize = false)
	private String moduleCode;// service代表服务主图，service_editor代表服务详情里的图，goods代表商品主图，goods_editor代表商品详情里的图
	
	@JSONField(serialize = false)
	private String imgType;

	private String imgPath;
	
	@JSONField(serialize = false)
	private String pathPrefix;
	
	@JSONField(serialize = false)
	private String realPath;
	
	@JSONField(serialize = false)
	private String uploadTime;
	
	@JSONField(serialize = false)
	private String uploadUser;
	
	@JSONField(serialize = false)
	private Integer status;
	
	@JSONField(serialize = false)
	private String cext1;
	
	@JSONField(serialize = false)
	private String cext2;
	
	@JSONField(serialize = false)
	private String cext3;
	
	@JSONField(serialize = false)
	private Integer iext1;
	
	@JSONField(serialize = false)
	private Integer iext2;
	
	@JSONField(serialize = false)
	private Date dext1;
	
	@JSONField(serialize = false)
	private Date dext2;

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode == null ? null : moduleCode.trim();
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType == null ? null : imgType.trim();
	}

	public String getPathPrefix() {
		return pathPrefix;
	}

	public void setPathPrefix(String pathPrefix) {
		this.pathPrefix = pathPrefix == null ? null : pathPrefix.trim();
	}

	public String getImgPath() {
		if (StringUtils.isBlank(pathPrefix)) {
			return imgPath;
		}
		return pathPrefix + imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath == null ? null : imgPath.trim();
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath == null ? null : realPath.trim();
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime == null ? null : uploadTime.trim();
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser == null ? null : uploadUser.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCext1() {
		return cext1;
	}

	public void setCext1(String cext1) {
		this.cext1 = cext1 == null ? null : cext1.trim();
	}

	public String getCext2() {
		return cext2;
	}

	public void setCext2(String cext2) {
		this.cext2 = cext2 == null ? null : cext2.trim();
	}

	public String getCext3() {
		return cext3;
	}

	public void setCext3(String cext3) {
		this.cext3 = cext3 == null ? null : cext3.trim();
	}

	public Integer getIext1() {
		return iext1;
	}

	public void setIext1(Integer iext1) {
		this.iext1 = iext1;
	}

	public Integer getIext2() {
		return iext2;
	}

	public void setIext2(Integer iext2) {
		this.iext2 = iext2;
	}

	public Date getDext1() {
		return dext1;
	}

	public void setDext1(Date dext1) {
		this.dext1 = dext1;
	}

	public Date getDext2() {
		return dext2;
	}

	public void setDext2(Date dext2) {
		this.dext2 = dext2;
	}
}