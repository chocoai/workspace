package com.yhcrt.healthcloud.mall.service;

import java.util.List;

import com.yhcrt.healthcloud.mall.entity.OrderDetail;

public interface OrderDetailService {

	Integer add(OrderDetail orderDetail);

	Integer del(Integer cid);

	Integer upd(OrderDetail orderDetail);

	OrderDetail get(Integer cid);

	List<OrderDetail> getAll();

	// 根据订单ID查找出对应的详细信息
	List<OrderDetail> getDetailsByOrderId(Integer orderId);

}
