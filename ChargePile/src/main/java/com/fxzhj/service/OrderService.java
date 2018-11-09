package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Order;

public interface OrderService {

	//根据条件查询充电订单
	List<Order> queryOrder(Order order);

}
