package com.yhcrt.iHealthCloud.service;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.YwOrder;

/**
 * @Description:
 * @version 1.0 2017年9月7日
 * @author jimmy
 */
public interface YwOrderService {

	Integer add(YwOrder ywOrder);

	Integer del(Integer cid);

	Integer upd(YwOrder ywOrder);

	YwOrder get(Integer cid);

	List<YwOrder> getAll(HashMap<String, Object> params);

	String getYwOrderList(JSONObject pdataObj);
	
	String refund(JSONObject pdataObj);

	String setYwOrderStatus(JSONObject pdataObj);

	String getYwOrderDetail(JSONObject pdataObj);

	String sendGoodsOrder(JSONObject pdata);

	String payYworder(JSONObject pdata);

	String sendCartOrder(JSONObject pdata);

	YwOrder getByOrderNo(String orderNo);

	List<YwOrder> selectYwOrderList(Integer memberId, Integer orderstatus);

	List<YwOrder> selectYwOrderList(Integer orderstatus);

	/**
	 * 
	 * @Description: 更新商品订单
	 * @param cid
	 * @param orderStatus
	 * @return void
	 */
	void updateStatus(Integer cid, Integer orderStatus, String cetx3);

	void updateAddress(Integer cid, Integer addressId);

	YwOrder getByCext1(String cext1);

	// 退款修改订单状态
	void updateRefund(Integer orderid, Integer cid, Integer status,
			String refundType, String reson);
	
	String updateRefund(JSONObject pdataObj);

}
