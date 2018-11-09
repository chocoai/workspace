package com.yhcrt.healthcloud.mall.service;

import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.mall.entity.YwOrder;

public interface YwOrderService {

	Integer add(YwOrder ywOrder);

	Integer del(Integer cid);

	Integer update(YwOrder ywOrder);

	YwOrder get(Integer cid);

	List<YwOrder> getAll(Map<String, Object> params);

	// 根据orderId修改订单状态
	void updateByStatus(YwOrder order);

	// 退款修改订单信息 及调用退款接口
	Map<String, String> updateByDetailCid(String cid, String status);

}
