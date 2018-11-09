package com.yhcrt.iHealthCloud.entity;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

public class ServiceProvider {
    private Integer providerId;

    @JSONField(serialize=false)
    private Integer orgId;

    private String providerName;

    private String providerType;

    private String providerNature;

    private String providerTag;

    private String providerLogo;

    private String titleImg;

    @JSONField(serialize=false)
    private String areaId;

    private String area;

    private Double longitude;

    private Double latitude;

    private String linkman;

    private String phoneNumber;

    private String tel;

    private String email;

    private String fax;

    private String address;

    private String postcode;

    private String businessLicense;

    private String licenseNo;

    private Double providerScore;

    private String providerIntro;

    @JSONField(serialize=false)
    private String createUser;

    @JSONField(serialize=false)
    private String createTime;

    @JSONField(serialize=false)
    private String updateTime;

    @JSONField(serialize=false)
    private Integer orderNum;

    @JSONField(serialize=false)
    private Integer status;

    @JSONField(serialize=false)
    private String remark;

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getProviderName() {
    	if (providerName.length()> 12) {
    		providerName = providerName.substring(0, 10) + "......";
		}
        return providerName;
    }
    
    public String getProviderShortName() {
    	if (StringUtils.isNotBlank(getProviderName()) && getProviderName().length() > 5) {
    		return getProviderName().substring(0, 5) + "...";
		}
        return getProviderName();
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName == null ? null : providerName.trim();
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType == null ? null : providerType.trim();
    }

    public String getProviderNature() {
        return providerNature;
    }

    public void setProviderNature(String providerNature) {
        this.providerNature = providerNature == null ? null : providerNature.trim();
    }

    public String getProviderTag() {
        return providerTag;
    }

    public void setProviderTag(String providerTag) {
        this.providerTag = providerTag == null ? null : providerTag.trim();
    }

    public String getProviderLogo() {
        return providerLogo;
    }

    public void setProviderLogo(String providerLogo) {
        this.providerLogo = providerLogo == null ? null : providerLogo.trim();
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
    
    public String getAddr() {
    	if (StringUtils.isNotBlank(getAddress()) && getAddress().length() > 5) {
    		return getAddress().substring(0, 5) + "...";
		}
        return getAddress();
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

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo == null ? null : licenseNo.trim();
    }

    public Double getProviderScore() {
        return providerScore;
    }

    public void setProviderScore(Double providerScore) {
        this.providerScore = providerScore;
    }

    public String getProviderIntro() {
        return providerIntro;
    }

    public void setProviderIntro(String providerIntro) {
        this.providerIntro = providerIntro == null ? null : providerIntro.trim();
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
}