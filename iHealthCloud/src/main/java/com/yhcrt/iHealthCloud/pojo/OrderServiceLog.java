/**
 * @Title:   OrderServiceLog.java 
 * @Package: com.yhcrt.iHealthCloud.pojo  
 * @Description: 
 * @author: rpf
 * @date: 2018年2月2日 
 * @version V1.0 
 * Copyright © 2018 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.pojo;

import java.util.List;

import com.yhcrt.iHealthCloud.entity.YwImage;

/**
 * @ClassName: OrderServiceLog
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2018年2月2日 
 */
public class OrderServiceLog {
	
	private String orderId;
	
	// 服务名称
	private String serviceName;
	
	// 服务标题图
	private String serviceTitleImg;

	// 订购用户
	private String buyer;
	
	// 工单内容
	private String orderContent;
	
	// 下单时间
	private String orderTime;
	
	// 服务对象
	private String serviceObject;
	
	// 服务地址
	private String serviceAddress;
	
	// 联系电话
	private String tel;
	
	// 服务人员
	private String serviceHandler;
	
	// 服务时间
	private String serviceTime;
	
	// 服务描述内容
	private String serviceContent;
	
	// 服务评价
	private String serviceComment;
	
	// 服务评分
	private String serviceScore;
	
	// 服务场景图
	private List<YwImage> servicePic;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceTitleImg() {
		return serviceTitleImg;
	}

	public void setServiceTitleImg(String serviceTitleImg) {
		this.serviceTitleImg = serviceTitleImg;
	}
	
	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public String getServiceObject() {
		return serviceObject;
	}

	public void setServiceObject(String serviceObject) {
		this.serviceObject = serviceObject;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getServiceHandler() {
		return serviceHandler;
	}

	public void setServiceHandler(String serviceHandler) {
		this.serviceHandler = serviceHandler;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getServiceComment() {
		return serviceComment;
	}

	public void setServiceComment(String serviceComment) {
		this.serviceComment = serviceComment;
	}

	public String getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(String serviceScore) {
		this.serviceScore = serviceScore;
	}

	public List<YwImage> getServicePic() {
		return servicePic;
	}

	public void setServicePic(List<YwImage> servicePic) {
		this.servicePic = servicePic;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	
	
}
