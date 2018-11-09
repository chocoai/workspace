/**
 * 
 */
package com.whty.assis.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangzheng
 * @date 2018年8月7日
 */
public class AppTools implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7397730671967037321L;

	private Integer id;
	private String appName;
	private Integer sortNum;
	private String downUrl;
	private String icon;
	private String clickIcon;
	private Integer fileSize;
	private String description;
	private String subjectId;
	private boolean isFolder;
	private String modelCode;
	private Date createTime;
	private Date updateTime;
	private Integer parentId;
	private Integer isParent;
	private String subFileExt;
	private String version;
	private boolean isNeedlogin;
	private Integer status;
	private String typeName;
	private String behaviorcode;
	private String hdktVersion;

	public String getHdktVersion() {
		return hdktVersion;
	}

	public void setHdktVersion(String hdktVersion) {
		this.hdktVersion = hdktVersion;
	}

	public String getBehaviorcode() {
		return behaviorcode;
	}

	public void setBehaviorcode(String behaviorcode) {
		this.behaviorcode = behaviorcode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isNeedlogin() {
		return isNeedlogin;
	}

	public void setNeedlogin(boolean isNeedlogin) {
		this.isNeedlogin = isNeedlogin;
	}

	public String getSubFileExt() {
		return subFileExt;
	}

	public void setSubFileExt(String subFileExt) {
		this.subFileExt = subFileExt;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getClickIcon() {
		return clickIcon;
	}

	public void setClickIcon(String clickIcon) {
		this.clickIcon = clickIcon;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

}