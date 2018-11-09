package com.yhcrt.iHealthCloud.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;

public class YwOrder {

	private Integer orderId;

	private Integer memberId;

	private Double orderAmount;

	private Double freight;

	private Double discounts;

	private Double actualPayment;

	private String recipient;// 收货人

	private String phoneNum;// 联系电话

	private String address;// 收货地址

	@JSONField(serialize = false)
	private Integer addressId;

	private Integer orderStatus;// -1取消 0待支付 1已支付 2已发货/进行中 3待评价/待审核 4完成 

	private String createTime;

	private String payType;

	private String payAccount;	//微信或支付宝返回的给我们的订单号

	private Double payValue;

	private String payTime;

	private String logistics;

	private String waybill;

	private String cext1;	//订单编号

	private String cext2;	//传给支付宝或微信的交易单号

	@JSONField(serialize = false)
	private String cext3;
	
	@JSONField(serialize = false)
	private Integer iext1;
	
	@JSONField(serialize = false)
	private Integer iext2;
	
	@JSONField(serialize = false)
	private Date dext1;
	
	@JSONField(serialize = false)
	private Date dext2;

	@JSONField(serialize = false)
	private Member member;

	private List<OrderDetail> orderDetails;

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

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public String getMemberName() {
		String memberName = getMember().getRealName();
		if (member != null) {
			return StringUtils.isBlank(memberName) ? getMember().getNickName() : memberName;
		}
		return "";
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}