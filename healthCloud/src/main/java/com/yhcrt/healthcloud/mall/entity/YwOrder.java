package com.yhcrt.healthcloud.mall.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yhcrt.healthcloud.memberBack.entity.MemberAddress;

public class YwOrder {
	
    private Integer orderId;

    private Integer memberId;

    private Double orderAmount;

    private Double freight;

    private Double discounts;

    private Double actualPayment;

    private Integer addressId;

    private Integer orderStatus; //0待支付，1待发货，2待收货，3待评价,4已完成 -1取消订单

    private String createTime;

    private String payType;	//small-小程序  wechat-微信app支付  alipay-支付宝app支付  alipay_pc-支付宝网页  wechat_h5-微信H5支付  alipay_h5-支付宝H5支付

    private String payAccount;

    private Double payValue;

    private String payTime;

    private String logistics;

    private String waybill;

    private String cext1;

    private String cext2;

    private String cext3;

    private Integer iext1;

    private Integer iext2;

    private Date dext1;

    private Date dext2;
    
    private String memberName;
    
    private MemberAddress memberAddress;

	public MemberAddress getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(MemberAddress memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Double discounts) {
        this.discounts = discounts;
    }

    public Double getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(Double actualPayment) {
        this.actualPayment = actualPayment;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    //订单状态
    public String getOrderStatusView() {
    	String orderStatusView="";
    	if(orderStatus != null){
    		if(orderStatus == -1){
    			orderStatusView="取消订单";
        	}else if(orderStatus == 0){
        		orderStatusView="待支付";
        	}else if(orderStatus == 1){
        		orderStatusView="待发货";
        	}else if(orderStatus == 2){
        		orderStatusView="待收货";
        	}else if(orderStatus == 3){
        		orderStatusView="待评价";
        	}else if(orderStatus == 4){
        		orderStatusView="已完成";
        	}
    	}
        return orderStatusView;
    }
    

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }
    
    //支付类型名称
    public String getPayTypeView() {
    	String payTypeView = "";
    	if(StringUtils.isNotBlank(payType)){
    		if("small".equals(payType)){
    			payTypeView = "微信小程序支付";
    		}else if("wechat".equals(payType)){
    			payTypeView = "微信app支付";
    		}else if("alipay".equals(payType)){
    			payTypeView = "支付宝app支付";
    		}else if("alipay_pc".equals(payType)){
    			payTypeView = "支付宝网页支付";
    		}else if("wechat_h5".equals(payType)){
    			payTypeView = "微信H5支付";
    		}else if("alipay_h5".equals(payType)){
    			payTypeView = "支付宝H5支付";
    		}
    	}
        return payTypeView;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    public Double getPayValue() {
        return payValue;
    }

    public void setPayValue(Double payValue) {
        this.payValue = payValue;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? null : logistics.trim();
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill == null ? null : waybill.trim();
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
}