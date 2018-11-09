package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Order;

public interface OrderMapper {

	//根据条件查询具体小区的充电订单
	List<Order> queryByCId(Order order);

	//根据条件查询具体区域的充电订单
	List<Order> queryByAId(Order order);
}