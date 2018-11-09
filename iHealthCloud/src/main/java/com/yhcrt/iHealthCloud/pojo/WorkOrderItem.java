/**
 * @Title:   WorkOrder.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2018年2月10日 
 * @version V1.0 
 * Copyright © 2018 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

/**
 * @ClassName: WorkOrder
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2018年2月10日
 */
public class WorkOrderItem {

	private String orderId;

	private String orderNo;

	private String buyer;

	private String serviceName;

	private String serviceImg;

	private String orderContent;

	private String orderStatus;

	private String serviceObject;

	private String orderTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceImg() {
		return serviceImg;
	}

	public void setServiceImg(String serviceImg) {
		this.serviceImg = serviceImg;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getServiceObject() {
		return serviceObject;
	}

	public void setServiceObject(String serviceObject) {
		this.serviceObject = serviceObject;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * 
	 */
	public WorkOrderItem() {
	}

}
