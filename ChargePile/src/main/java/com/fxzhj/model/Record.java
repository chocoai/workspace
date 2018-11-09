package com.fxzhj.model;

import java.io.Serializable;

public class Record implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    //设备id
    private Long deviceId;

    //端口号id
    private Long portId;

    //用户
    private String userAccount;

    //充电开始时间
    private String startTime;

    //预计充电结束时间
    private String endTime;

    //实际结束时间
    private String fEndTime;

    //预充时长（分钟）
    private Integer expectTime;

    //金额（元）
    private Float money;

    //实充时长（分钟）
    private Integer factTime;

    //支付方式（0：线下 1：线上）
    private Integer payWay;

    //支付类型（0：钱包支付 1：支付宝支付 2：其它）
    private Integer payType;

    //状态（0：正在充电 1：充电完成）
    private Integer status;
    
    //界面传入数据查询条件
    private String comOrareId;	//根据deep判断是节点是区域id或小区id
    private String deep;	//节点层级
    private String qStartTime;	//开始时间
    private String qEndTime;	//结束时间
    
    //界面展示字段
    private String siteName;	//投放地址
    private String deviceNo;	//设备编号
    private String cardNo;	//卡号
    private String portNo;	//设备对应端口编号
    
    public String getqStartTime() {
		return qStartTime;
	}

	public void setqStartTime(String qStartTime) {
		this.qStartTime = qStartTime;
	}

	public String getqEndTime() {
		return qEndTime;
	}

	public void setqEndTime(String qEndTime) {
		this.qEndTime = qEndTime;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getComOrareId() {
		return comOrareId;
	}

	public void setComOrareId(String comOrareId) {
		this.comOrareId = comOrareId;
	}

	public String getDeep() {
		return deep;
	}

	public void setDeep(String deep) {
		this.deep = deep;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getfEndTime() {
        return fEndTime;
    }

    public void setfEndTime(String fEndTime) {
        this.fEndTime = fEndTime;
    }

    public Integer getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Integer expectTime) {
        this.expectTime = expectTime;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Integer getFactTime() {
        return factTime;
    }

    public void setFactTime(Integer factTime) {
        this.factTime = factTime;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}