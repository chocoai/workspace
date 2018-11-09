package com.whty.wfd.page.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class TUser {
	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private String account;

	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 
	 */
	private Date updateTime;

	/**
	 * 
	 */
	private String userType;

	/**
	 * aam用户主键
	 */
	private String personId;

	/**
	 * 
	 */
	private String platformCode;

	/**
	 * 
	 */
	private String loginPlatformCode;

	/**
	 * 
	 */
	private Integer schoolId;

	/**
	 * 头像地址
	 */
	private String logoUrl;

	/**
	 * aam学校id
	 */
	private String orgaId;

	private String identityId;

	private String receiveMessage;

	public String getReceiveMessage() {
		return receiveMessage;
	}

	public void setReceiveMessage(String receiveMessage) {
		this.receiveMessage = receiveMessage;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getOrgaId() {
		return orgaId;
	}

	public void setOrgaId(String orgaId) {
		this.orgaId = orgaId;
	}

	public String getLogoUrl() {
		if (StringUtils.isEmpty(logoUrl)) {
			return "http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_narmal.png";
		} else {
			return logoUrl;
		}
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 
	 * @return account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	/**
	 * 
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * @return update_time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 
	 * @return user_type
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * 
	 * @param userType
	 */
	public void setUserType(String userType) {
		this.userType = userType == null ? null : userType.trim();
	}

	/**
	 * aam用户主键
	 * 
	 * @return person_id aam用户主键
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * aam用户主键
	 * 
	 * @param personId
	 *            aam用户主键
	 */
	public void setPersonId(String personId) {
		this.personId = personId == null ? null : personId.trim();
	}

	/**
	 * 
	 * @return platform_code
	 */
	public String getPlatformCode() {
		return platformCode;
	}

	/**
	 * 
	 * @param platformCode
	 */
	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode == null ? null : platformCode.trim();
	}

	/**
	 * 
	 * @return login_platform_code
	 */
	public String getLoginPlatformCode() {
		return loginPlatformCode;
	}

	/**
	 * 
	 * @param loginPlatformCode
	 */
	public void setLoginPlatformCode(String loginPlatformCode) {
		this.loginPlatformCode = loginPlatformCode == null ? null : loginPlatformCode.trim();
	}

	/**
	 * 
	 * @return school_id
	 */
	public Integer getSchoolId() {
		return schoolId;
	}

	/**
	 * 
	 * @param schoolId
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
}