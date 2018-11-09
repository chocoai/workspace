package com.yhcrt.healthcloud.mall.entity;

import com.yhcrt.healthcloud.util.Arith;

public class OrderDetail {
    private Integer cid;

    private Integer orderId;

    private Integer goodsId;

    private Double price;

    private Integer amount;

    private Double totalPay;	//合计金额

    private Double actualPayment;	//实际支付金额

    private String createTime;

    private Integer status;	//0待支付，1待发货，2待收货，3待评价,4已完成  5:退款中 6:已退款 7:拒绝退款 -1取消订单
    
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    public Double getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(Double actualPayment) {
        this.actualPayment = actualPayment;
    }
    
    //优惠价 合计金额 - 实际金额
    public Double getDifferMoney(){
    	Double differMoney = 0.0;
    	if(actualPayment != null && totalPay != null){
    		if(totalPay > actualPayment){
    			differMoney = Arith.sub(totalPay, actualPayment);
    		}
    	}
		return differMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    //订单状态
    public String getStatusView() {
    	String statusView = "";
    	if(status != null){
    		if(status == -1){
    			statusView = "取消订单";
    		}else if(status == 0){
    			statusView = "待支付";
    		}else if(status == 1){
    			statusView = "待发货";
    		}else if(status == 2){
    			statusView = "待收货";
    		}else if(status == 3){
    			statusView = "待评价";
    		}else if(status == 4){
    			statusView = "已完成";
    		}else if(status == 5){
    			statusView = "退款中";
    		}else if(status == 6){
    			statusView = "已退款";
    		}else if(status == 7){
    			statusView = "拒绝退款";
    		}
    	}
        return statusView;
    }
    
}