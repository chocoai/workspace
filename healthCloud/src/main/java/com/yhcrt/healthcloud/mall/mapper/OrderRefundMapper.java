package com.yhcrt.healthcloud.mall.mapper;

import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.mall.entity.OrderRefund;

public interface OrderRefundMapper {

	// 查询服务退款信息
	OrderRefund queryByMap(Map<String, Object> map);

	// 查询商品退款信息
	List<OrderRefund> queryGoodsList(Map<String, Object> map);

	// 修改退款数据状态
	void updateById(OrderRefund refund);

	// 根据cid查询退款表中数据
	OrderRefund queryByRefId(int refId);
	
}