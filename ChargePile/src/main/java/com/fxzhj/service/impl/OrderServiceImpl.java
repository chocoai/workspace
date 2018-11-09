package com.fxzhj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxzhj.mapper.OrderMapper;
import com.fxzhj.model.Order;
import com.fxzhj.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper OMapper;

	//根据条件查询充电订单
	@Override
	public List<Order> queryOrder(Order order) {
		List<Order> list = new ArrayList<Order>();
		String deep = order.getDeep();
		if("9".equals(deep)){
			//表示具体小区id
			list = OMapper.queryByCId(order);
		}else if("1".equals(deep) || "2".equals(deep) ||"3".equals(deep)){
			//表示区域id
			list = OMapper.queryByAId(order);
		}
		return list;
	}


}
