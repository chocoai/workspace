package com.yhcrt.healthcloud.mall.entity;

import org.apache.commons.lang3.StringUtils;

public class ServicePrice {
	
    private Integer cid;	//主键id

    private Integer serviceId;	//服务项目id

    private Double price;	//服务价格
    
    private Double oriPrice;	//服务原价

    private String level;	//服务级别

    private String unit;	//价格单位

    private Integer type;	//价格类型（是否长期服务 1表示一次性价格，0表示长期价格）

    private Integer serviceNum;	//长期服务时长

    private Integer serviceUnit;	//长期服务单位(1表示天 2表示周  3表示月 4表示年)

    private Integer status;	//状态(0启用 1不启用)

    private String desct;	//描述
    
    private String dictKey;	//价格单位
    
    public String getPriceDetails(){
    	String priceDetails = "";
    	if(StringUtils.isNotBlank(level)){
    		priceDetails += level;
    	}
    	if(price != null){
    		priceDetails += price;
    	}
    	if(StringUtils.isNotBlank(dictKey)){
    		priceDetails += dictKey;
    	}
    	return priceDetails;
    }
    
    public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(Double oriPrice) {
		this.oriPrice = oriPrice;
	}

	public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(Integer serviceNum) {
        this.serviceNum = serviceNum;
    }

    public Integer getServiceUnit() {
        return serviceUnit;
    }

    public void setServiceUnit(Integer serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesct() {
        return desct;
    }

    public void setDesct(String desct) {
        this.desct = desct == null ? null : desct.trim();
    }
}