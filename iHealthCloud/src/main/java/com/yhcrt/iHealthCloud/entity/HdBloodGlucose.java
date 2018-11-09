package com.yhcrt.iHealthCloud.entity;

public class HdBloodGlucose {
    private Integer cid;

    private String imei;

    private Integer memberId;

    private String dataDate;
    
    private String dataSource;
    
    private String uploadTime;

    private Integer bgType;

    private Float bgValue;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate == null ? null : dataDate.trim();
    }
    
    public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime == null ? null : uploadTime.trim();
    }

    public Integer getBgType() {
        return bgType;
    }

    public void setBgType(Integer bgType) {
        this.bgType = bgType;
    }

    public Float getBgValue() {
        return bgValue;
    }

    public void setBgValue(Float bgValue) {
        this.bgValue = bgValue;
    }

	@Override
	public String toString() {
		return "HdBloodGlucose [cid=" + cid + ", imei=" + imei + ", memberId=" + memberId + ", dataDate=" + dataDate
				+ ", dataSource=" + dataSource + ", uploadTime=" + uploadTime + ", bgType=" + bgType + ", bgValue="
				+ bgValue + "]";
	}

}