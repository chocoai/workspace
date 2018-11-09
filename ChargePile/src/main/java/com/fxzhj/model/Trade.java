package com.fxzhj.model;

import java.io.Serializable;
import java.util.Date;

public class Trade implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    //充电记录id
    private Long recordId;

    //用户
    private String userAccount;

    //交易时间
    private Date ctime;

    //订单金额
    private Float orderMoney;

    //支付方式（0：线下 1：线上）
    private Integer payWay;

    //支付类型（0：钱包支付 1：支付宝支付 2：微信）
    private Integer payType;

    //实缴金额（元）
    private Float factMoney;

    //优惠金额
    private Float saveMoney;

    //优惠券号
    private String cardNo;

    //界面传入数据查询条件
    private String comOrareId;	//根据deep判断是节点是区域id或小区id
    private String deep;	//节点层级
    private String qStartTime;	//开始时间
    private String qEndTime;	//结束时间
    
    //界面展示字段
    private String deviceNo;	//设备编号
    private String portNo;	//设备对应端口编号
    private String cTimeStr;	//在sql中将交易时间转换为日期字符串
    
    public String getcTimeStr() {
		return cTimeStr;
	}

	public void setcTimeStr(String cTimeStr) {
		this.cTimeStr = cTimeStr;
	}

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

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Float getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Float orderMoney) {
        this.orderMoney = orderMoney;
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

    public Float getFactMoney() {
        return factMoney;
    }

    public void setFactMoney(Float factMoney) {
        this.factMoney = factMoney;
    }

    public Float getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(Float saveMoney) {
        this.saveMoney = saveMoney;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", recordId=").append(recordId);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", ctime=").append(ctime);
        sb.append(", orderMoney=").append(orderMoney);
        sb.append(", payWay=").append(payWay);
        sb.append(", payType=").append(payType);
        sb.append(", factMoney=").append(factMoney);
        sb.append(", saveMoney=").append(saveMoney);
        sb.append(", cardNo=").append(cardNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}