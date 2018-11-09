package com.whty.assis.api.respvo;

public class CheckNewSoft {

	private String id;
	private String softName;
	private String versionCode;
	private String updateContent;
	private String forceUpdate;// 是否强制更新(0:否，1：是)
	private String userUpdate;// 是否指定用户更新(0:否，1：是)
	private String fileUrl;
	private String allUserUpgrade;// 是否指定全部用户升级(0：否，1：是)

	public String getAllUserUpgrade() {
		return allUserUpgrade;
	}

	public void setAllUserUpgrade(String allUserUpgrade) {
		this.allUserUpgrade = allUserUpgrade;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public String getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(String forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

}
