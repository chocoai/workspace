package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.yhcrt.iHealthCloud.entity.OrderDetail;

/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/
public interface OrderDetailService {

	Integer add(OrderDetail orderDetail);

	Integer del(Integer cid);

	Integer upd(OrderDetail orderDetail);

	OrderDetail get(Integer cid);

	List<OrderDetail> getAll();

	/**
	 * 根据订单ID查找出对应的详细信息
	 * 
	 * @param orderId
	 * @return
	 */
	List<OrderDetail> getDetailsByOrderId(Integer orderId);

	void setDetailStatus(Integer orderId, Integer orderStatus);

}
