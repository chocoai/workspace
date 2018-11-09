package com.yhcrt.iHealthCloud.entity;

public class OrderRefund {
	
    private Integer id;

    private Integer refId;	//关联id  order_id

    private String type;	//类型 service 或 goods

    private String applyTime;	//申请退款时间

    private Double refundMoney;	//申请退款金额

    private String refundTime;	
    
    private String refundType;	//退款理由类型

    private String refundReson;	//退款原因

    private Integer status;	//退款状态 5退款中 6 退款成功

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime == null ? null : applyTime.trim();
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime == null ? null : refundTime.trim();
    }

    public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType == null ? null : refundType.trim();
	}

	public String getRefundReson() {
        return refundReson;
    }

    public void setRefundReson(String refundReson) {
        this.refundReson = refundReson == null ? null : refundReson.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}