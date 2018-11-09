package com.yhcrt.iHealthCloud.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.yhcrt.iHealthCloud.util.StringUtil;



public class Service {
    private Integer serviceId;

    private String serviceName;

    private String serviceType;

    private String serviceCategory;

    private Integer providerId;

    private String titleImg;

    private String serviceIntro;

    private Double originalPrice;

    private Double unitPrice;

    private String unit;

    private Integer score;

    private String serviceStatus;

    private String serviceStime;

    private String serviceEtime;

    private String createTime;

    private String createUser;

    private String updateTime;

    @JSONField(serialize=false)
    private String remark;

    private Integer status;//0为正常，1为禁用,根据常量STATUS_XXX来定

    private String cext1;

    @JSONField(serialize=false)
    private String cext2;

    @JSONField(serialize=false)
    private String cext3;

    @JSONField(serialize=false)
    private Integer iext1;

    private Integer iext2;

    @JSONField(serialize=false)
    private Date dext1;

    @JSONField(serialize=false)
    private Date dext2;

    @JSONField(serialize=false)
    private String content;
    
    //关联对象-服务供应商
    @JSONField(serialize=false)
    private ServiceProvider provider;


    public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public ServiceProvider getProvider() {
		return provider;
	}
	
	public String getProviderName() {
		if (getProvider() != null) {
			return getProvider().getProviderName();
		}
		return "";
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

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory == null ? null : serviceCategory.trim();
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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
    
	public String getServiceIntroSub() {
    	return StringUtil.overSubstr(getServiceIntro(), 15);
    }
}