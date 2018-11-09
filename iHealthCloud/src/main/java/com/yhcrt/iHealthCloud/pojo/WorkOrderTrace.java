/**
 * @Title:   OrderTrace.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2018年2月2日 
 * @version V1.0 
 * Copyright © 2018 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

/**
 * @ClassName: OrderTrace
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2018年2月2日
 */
public class WorkOrderTrace {

	// 服务名称
	private String serviceName;

	// 订购用户
	private String buyer;

	// 下单时间
	private String orderTime;

	// 支付时间
	private String payTime;

	// 服务人员
	private String serviceHandler;

	// 分配时间
	private String allocateTime;

	// 完成时间
	private String completionTime;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getServiceHandler() {
		return serviceHandler;
	}

	public void setServiceHandler(String serviceHandler) {
		this.serviceHandler = serviceHandler;
	}

	public String getAllocateTime() {
		return allocateTime;
	}

	public void setAllocateTime(String allocateTime) {
		this.allocateTime = allocateTime;
	}

	public String getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}

	/**
	 * 
	 */
	public WorkOrderTrace() {

	}

}
