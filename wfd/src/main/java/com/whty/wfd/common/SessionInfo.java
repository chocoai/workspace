package com.whty.wfd.common;

import java.io.Serializable;

import net.sf.json.JSONArray;

public class SessionInfo implements Serializable {

	private static final long serialVersionUID = 8642346228346134755L;
	private String userId;
	private String userType;
	private String userName;
	private String orgId;
	private String orgName;
	private String platformCode;
	private String platformName;
	private String areaCode;
	private String areaName;
	private String userPic;
	private String defaultClassId;// 默认班级
	private String defalutCalssName;// 默认班级名称
	private String usesessionid;
	private JSONArray orgaidentity;
	private String avatarM; // 用户图标中
	private String avatarS; // 用户图标小
	private String avatarB; // 用户图标大
	private String spaceAddress;// 个人空间主页
	private int schoolId;
	private String teacherNum;

	public String getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	public int getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getDefaultClassId() {
		return defaultClassId;
	}

	public void setDefaultClassId(String defaultClassId) {
		this.defaultClassId = defaultClassId;
	}

	public String getDefalutCalssName() {
		return defalutCalssName;
	}

	public void setDefalutCalssName(String defalutCalssName) {
		this.defalutCalssName = defalutCalssName;
	}

	public String getUsesessionid() {
		return usesessionid;
	}

	public void setUsesessionid(String usesessionid) {
		this.usesessionid = usesessionid;
	}

	public JSONArray getOrgaidentity() {
		return orgaidentity;
	}

	public void setOrgaidentity(JSONArray orgaidentity) {
		this.orgaidentity = orgaidentity;
	}

	public String getAvatarM() {
		return avatarM;
	}

	public void setAvatarM(String avatarM) {
		this.avatarM = avatarM;
	}

	public String getAvatarS() {
		return avatarS;
	}

	public void setAvatarS(String avatarS) {
		this.avatarS = avatarS;
	}

	public String getAvatarB() {
		return avatarB;
	}

	public void setAvatarB(String avatarB) {
		this.avatarB = avatarB;
	}

	public String getSpaceAddress() {
		return spaceAddress;
	}

	public void setSpaceAddress(String spaceAddress) {
		this.spaceAddress = spaceAddress;
	}

}
