package com.yhcrt.iHealthCloud.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

public class WorkOrder {

	private Integer orderId;

	private Integer serviceId;

	private Integer memberId;

	private String orderContent;

	private String serviceObject;

	private String tel;

	private String address;

	private String startTime; //

	private String endTime; //

	@JSONField(serialize=false)
	private String handler; // 操作人

	@JSONField(serialize=false)
	private String toUser; // 指派人

	private String createTime;

	private String handleTime; // 操作时间

	private Integer orderStatus; // -1取消 0待支付 1已支付 2已发货/进行中 3待评价/待审核 4完成 5退款中 6已退款 7拒绝退款

	private Double unitPrice;
	
	// 服务价格信息
	private String servicePrice;

	private Integer amount;

	private Double serviceFee;

	private Double discounts;

	private Double actualPayment;

	private Integer payStatus;

	private String payType;

	private String payAccount;	//微信或支付宝返回的给我们的订单号

	private Double payValue;

	private String payTime;

	@JSONField(serialize=false)
	private String remark;
	
	private String cext1; 	//系统生成订单号

	private String cext2;	//传给支付宝或微信的交易单号

	private String cext3;

	private Integer iext1;
	
	@JSONField(serialize=false)
	private Integer iext2;
	
	@JSONField(serialize=false)
	private Date dext1;
	
	@JSONField(serialize=false)
	private Date dext2;
	
	// 订购服务
	@JSONField(serialize = false)
	private Service service;

	// 订购会员
	@JSONField(serialize = false)
	private Member member;
	
	// 工单处理人
	@JSONField(serialize=false)
	private Employee employee;
	
	// 工单服务人员
	@JSONField(serialize=false)
	private Employee emp;
	
	// 订购会员名称
	private String buyer;
	
	private String orderHandler;
	
	private String serviceHandler;
	
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
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
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	// 服务名称
	public String getServiceName() {
		if(service != null) {
			return service.getServiceName();
		}
		return "";
	}
	
	// 供应商Id
	public String getProviderId() {
		if(service != null) {
			return service.getProviderId().toString();
		}
		return "";
	}
	
	public String getProviderName() {
		if(service != null) {
			return service.getProviderName();
		}
		return "";
	}
	
	public String getServiceTitleImg() {
		if(service != null) {
			return service.getTitleImg();
		}
		return "";
	}
	
	public String getServiceUnit() {
		if(service != null) {
			return service.getCext1();
		}
		return "";
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public String getBuyer() {
		if(member != null) {
			buyer = StringUtils.isBlank(member.getRealName()) ? member.getNickName() : member.getRealName();
		}else {
			buyer=  "";
		}
		return buyer;
	}

	public String getOrderHandler() {
		if (employee != null) {
			orderHandler = StringUtils.isBlank(employee.getRealName()) ? employee.getNickName() : employee.getRealName();
		}else {
			orderHandler = "";
		}
		return orderHandler;
	}

	public String getServiceHandler() {
		if (emp != null) {
			serviceHandler = StringUtils.isBlank(emp.getRealName()) ? emp.getNickName() : emp.getRealName();
		}else {
			serviceHandler = "";
		}
		return serviceHandler;
	}

}