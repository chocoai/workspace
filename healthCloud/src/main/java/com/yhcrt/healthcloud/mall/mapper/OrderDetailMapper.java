package com.yhcrt.healthcloud.mall.mapper;

import java.util.List;

import com.yhcrt.healthcloud.mall.entity.OrderDetail;
import com.yhcrt.healthcloud.mall.entity.OrderDetailExample;

public interface OrderDetailMapper {
	
    long countByExample(OrderDetailExample example);

    int deleteByExample(OrderDetailExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExample(OrderDetailExample example);

    OrderDetail selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

	void updateByCid(OrderDetail od);

	// 根据已知的明细订单查询是否还有其他进行中的订单
	int countByBean(OrderDetail od);

	// 修改订单和明细订单状态
	void updateByOrderId(OrderDetail orderDetail);
	
}