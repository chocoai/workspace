package com.yhcrt.healthcloud.mall.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yhcrt.healthcloud.memberBack.entity.MemberBack;

public class WorkOrder {
	
    private Integer orderId;

    private Integer serviceId;

    private Integer memberId;

    private String orderContent;

    private String serviceObject;

    private String tel;

    private String address;

    private String startTime;

    private String endTime;

    private String handler;

    private String toUser;

    private String createTime;

    private String handleTime;

    private Integer orderStatus;	//0待支付，1已支付，2进行中，3完成待审核,4已完成，5退款中，6已退款，7拒绝退款 8交易完成 -1取消订单

    private Double unitPrice;

    private Integer amount;

    private Double serviceFee;

    private Double discounts;

    private Double actualPayment;

    private Integer payStatus;//1线上付款  0线下付款

    private String payType;	//small-小程序  wechat-微信app支付  alipay-支付宝app支付  alipay_pc-支付宝网页  wechat_h5-微信H5支付  alipay_h5-支付宝H5支付

    private String payAccount;	//支付宝或微信返回的订单号

    private Double payValue;

    private String payTime;

    private String remark;

    private String cext1;	//订单编号

    private String cext2;	//与支付宝,微信交易号

    private String cext3;	//退款原因

    private Integer iext1;	//服务价格id

    private Integer iext2;	

    private Date dext1;

    private Date dext2;
    
    //临时字段
    private String serviceName;
    
    private String memberName;
    
    private MemberBack member;
    
    private Service service;
    
    private String toUserName;	//被指派人
    private String handlerName;	//工单处理人
    private String providerName;
    
	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public MemberBack getMember() {
        return member;
    }

    public void setMember(MemberBack member) {
        this.member = member;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent == null ? null : orderContent.trim();
    }

    public String getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(String serviceObject) {
        this.serviceObject = serviceObject == null ? null : serviceObject.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler == null ? null : handler.trim();
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser == null ? null : toUser.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime == null ? null : handleTime.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    //订单状态
    public String getOrderStatusView() {
    	String orderStatusView = "";
    	if(orderStatus != null){
    		if(orderStatus == -1){
    			orderStatusView = "取消订单";
    		}else if(orderStatus == 0){
    			orderStatusView = "待支付";
    		}else if(orderStatus == 1){
    			orderStatusView = "已支付";
    		}else if(orderStatus == 2){
    			orderStatusView = "进行中";
    		}else if(orderStatus == 3){
    			orderStatusView = "完成待审核";
    		}else if(orderStatus == 4){
    			orderStatusView = "完成待评价";
    		}else if(orderStatus == 5){
    			orderStatusView = "退款中";
    		}else if(orderStatus == 6){
    			orderStatusView = "已退款";
    		}else if(orderStatus == 7){
    			orderStatusView = "拒绝退款";
    		}else if(orderStatus == 8){
    			orderStatusView = "交易完成";
    		}
    	}
        return orderStatusView;
    }
    
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
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

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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