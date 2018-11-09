package com.yhcrt.healthcloud.device.entity;

public class MemberDeviceVideo {
	
    private Integer cid;

    private Integer orgId;	//所属机构

    private String deviceId;	//设备id

    private String deviceName;	//设备名称

    private String deviceModel;	//设备型号

    private String deviceVendor;	//设备厂商

    private String address;	//安装地址
    
    private Integer status;	//设备状态(1启用  2禁用 3损坏)

    private String createTime;
    
    private String orgName;	//机构名称
    
    public String getStatusView() {
    	String statusView = "";
    	if(status != null){
    		if(status == 1){
    			statusView = "启用";
    		}else if(status == 2){
    			statusView = "禁用";
    		}else if(status == 3){
    			statusView = "损坏";
    		}
    	}
		return statusView;
	}
    
    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    public String getDeviceVendor() {
        return deviceVendor;
    }

    public void setDeviceVendor(String deviceVendor) {
        this.deviceVendor = deviceVendor == null ? null : deviceVendor.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}