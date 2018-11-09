package com.yhcrt.healthcloud.mall.entity;

import java.util.Date;

import com.yhcrt.healthcloud.provider.entity.ServiceProvider;
import com.yhcrt.healthcloud.util.DictUtil;

public class Service {
	
    private Integer serviceId;

    private String serviceName;

    private String serviceType;

    private String serviceCategory;

    private Integer providerId;

    private String titleImg;

    private String serviceIntro;

    private Double originalPrice;

    private String unitPrice;

    private String unit;

    private Integer score;

    private String serviceStatus;

    private String serviceStime;

    private String serviceEtime;

    private String createTime;

    private String createUser;

    private String updateTime;

    private String remark;

    private Integer status;//0为正常，1为禁用,根据常量STATUS_XXX来定

    private String cext1;

    private String cext2;

    private String cext3;

    private Integer iext1;

    private Integer iext2;

    private Date dext1;

    private Date dext2;

    private String content;
    
    //关联服务供应商对象
    private ServiceProvider provider;
    
	public ServiceProvider getProvider() {
		return provider;
	}

	public void setProvider(ServiceProvider provider) {
		this.provider = provider;
	}

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getServiceType() {
        return serviceType;
    }
    
    public String getTypeName(){
    	return DictUtil.viewByCode(DictUtil.SERVICE_TYPE, getServiceType());
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getServiceCategory() {
        return serviceCategory;
    }
    
    public String getCategoryName(){
    	return DictUtil.viewByCode(DictUtil.SERVICE_CATEGORY, getServiceCategory());
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory == null ? null : serviceCategory.trim();
    }

    public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg == null ? null : titleImg.trim();
    }

    public String getServiceIntro() {
        return serviceIntro;
    }

    public void setServiceIntro(String serviceIntro) {
        this.serviceIntro = serviceIntro == null ? null : serviceIntro.trim();
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice == null ? null : unitPrice.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus == null ? null : serviceStatus.trim();
    }

    public String getServiceStime() {
        return serviceStime;
    }

    public void setServiceStime(String serviceStime) {
        this.serviceStime = serviceStime == null ? null : serviceStime.trim();
    }

    public String getServiceEtime() {
        return serviceEtime;
    }

    public void setServiceEtime(String serviceEtime) {
        this.serviceEtime = serviceEtime == null ? null : serviceEtime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}