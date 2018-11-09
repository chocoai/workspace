package com.yhcrt.healthcloud.device.entity;

import org.apache.commons.lang3.StringUtils;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.organization.entity.Organization;

public class Device {
	
    private Integer deviceId;

    private String deviceName;

    private String imei;

    private Integer orgId;

    private String deviceCategory;

    private String deviceType;

    private String sim;

    private Integer isUse;	//是否绑定（0：未绑定，为默认值；1：已绑定；）
    
    private String firstStartTime;	//第一次启用设备时间
    
    private Integer startStatus;	//启用状态(0未启用 1启用 )

    private Integer orderNum;

    private Integer createUser;

    private String createTime;

    private String updateTime;

    private Integer status;

    private String remark;
    
    private Organization org;
    
	public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory == null ? null : deviceCategory.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getSim() {
        return sim;
    }
    
	public String getSimView() {
		if (StringUtils.isNotBlank(getSim())) {
			return getSim().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		}
		return "";
	}

    public void setSim(String sim) {
        this.sim = sim == null ? null : sim.trim();
    }

    public Integer getIsUse() {
        return isUse;
    }
    
    public String getIsUseView() {
    	if (Constants.Device.USED == getIsUse()) {
			return "已绑定";
		}
        return "未绑定";
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }
    

    public String getFirstStartTime() {
		return firstStartTime;
	}

	public void setFirstStartTime(String firstStartTime) {
		this.firstStartTime = firstStartTime == null? null :firstStartTime.trim();
	}

	public Integer getStartStatus() {
		return startStatus;
	}
	
	public String getStartStatusView() {
    	if (startStatus!=null && startStatus == 1) {
			return "已启用";
		}
        return "未启用";
    }
	
	public void setStartStatus(Integer startStatus) {
		this.startStatus = startStatus;
	}

	public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
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
    
    public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

}