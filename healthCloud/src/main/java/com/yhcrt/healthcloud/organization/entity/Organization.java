package com.yhcrt.healthcloud.organization.entity;

import org.apache.commons.lang3.StringUtils;

public class Organization {

	private Integer orgId;

	private String orgName;

	private String orgCode;

	private Integer parentOrgId;

	private String orgType;

	private String orgNature;

	private String orgTag;

	private String orgLogo;

	private String titleImg;

	private String areaId;

	private String area;

	private Double longitude;

	private Double latitude;

	private String lnglat;

	private String linkman;

	private String phoneNumber;

	private String tel;

	private String email;

	private String fax;

	private String address;

	private String postcode;

	private Double orgScore;

	private String orgIntro;

	private String createUser;

	private String createTime;

	private String updateTime;

	private Integer orderNum;

	private Integer status;

	private String remark;

	private String content;
	
	private Integer isSmallpro;

	private Organization parentOrg;
	
	private String orgFacility;	//机构设施
	
    public String getOrgFacility() {
		return orgFacility;
	}

	public void setOrgFacility(String orgFacility) {
		this.orgFacility = orgFacility;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName == null ? null : orgName.trim();
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

	public Integer getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Integer parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType == null ? null : orgType.trim();
	}

	public String getOrgNature() {
		return orgNature;
	}

	public void setOrgNature(String orgNature) {
		this.orgNature = orgNature == null ? null : orgNature.trim();
	}

	public String getOrgTag() {
		return orgTag;
	}

	public void setOrgTag(String orgTag) {
		this.orgTag = orgTag == null ? null : orgTag.trim();
	}

	public String getOrgLogo() {
		return orgLogo;
	}

	public void setOrgLogo(String orgLogo) {
		this.orgLogo = orgLogo == null ? null : orgLogo.trim();
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg == null ? null : titleImg.trim();
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId == null ? null : areaId.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getLnglat() {
		if (getLongitude() != null && getLatitude() != null) {
			return getLongitude() + "," + getLatitude();
		}
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman == null ? null : linkman.trim();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax == null ? null : fax.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode == null ? null : postcode.trim();
	}

	public Double getOrgScore() {
		return orgScore;
	}

	public void setOrgScore(Double orgScore) {
		this.orgScore = orgScore;
	}

	public String getOrgIntro() {
		return orgIntro;
	}

	public void setOrgIntro(String orgIntro) {
		this.orgIntro = orgIntro == null ? null : orgIntro.trim();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime == null ? null : updateTime.trim();
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
	
	public Integer getIsSmallpro() {
		return isSmallpro;
	}

	public void setIsSmallpro(Integer isSmallpro) {
		this.isSmallpro = isSmallpro;
	}

	public Organization getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Organization parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getPhoneNumberView() {
		if (StringUtils.isNotBlank(getPhoneNumber())) {
			return getPhoneNumber().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		}
		return "";
	}

	public String getTelView() {
		if (StringUtils.isNotBlank(getTel())) {
			return getTel().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		}
		return "";
	}

	public String getFaxView() {
		if (StringUtils.isNotBlank(getFax())) {
			return getFax().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		}
		return "";
	}
}