package com.fxzhj.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    //订单编号
    private String orderNo;

    //用户
    private String userAccount;

    //设备id
    private Long deviceId;

    //端口号id
    private Long portId;
    
    //电价
    private Float price;
    
    //分钟
    private Integer mins;

    //0后付款  1预付费
    private Integer priceType;
    
    //订单时间
    private Date ctime;

    //订单应缴金额
    private Float orderMoney;

    //订单实缴金额
    private Float payMoney;

    //优惠金额
    private Float saveMoney;

    //订单状态（0：未处理 1：已处理 -1：关闭）
    private Integer status;

    //支付类型（0：钱包支付 1：支付宝支付 2：微信支付  3:其他）
    private Integer payType;

    //支付方式（0：线下 1：线上）
    private Integer payWay;
    
    //界面传入数据查询条件
    private String comOrareId;	//根据deep判断是节点是区域id或小区id
    private String deep;	//节点层级
    private String qStartTime;	//开始时间
    private String qEndTime;	//结束时间
    
    //界面展示字段
    private String deviceNo;	//设备编号
    private String portNo;	//设备对应端口编号
    private String cTimeStr;	//订单时间在sql中转化为字符串
    
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
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
    
    public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getMins() {
		return mins;
	}

	public void setMins(Integer mins) {
		this.mins = mins;
	}

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

    public Float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Float orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Float payMoney) {
        this.payMoney = payMoney;
    }

    public Float getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(Float saveMoney) {
        this.saveMoney = saveMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getcTimeStr() {
		return cTimeStr;
	}

	public void setcTimeStr(String cTimeStr) {
		this.cTimeStr = cTimeStr;
	}
    
}