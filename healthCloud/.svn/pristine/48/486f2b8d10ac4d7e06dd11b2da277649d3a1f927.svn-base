package com.yhcrt.healthcloud.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.mall.entity.OrderRefund;
import com.yhcrt.healthcloud.mall.entity.WorkOrder;

/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
 */
public interface WorkOrderService {

	Integer add(WorkOrder workOrder);

	Integer del(Integer cid);

	Integer upd(WorkOrder workOrder);

	WorkOrder get(Integer cid);

	List<WorkOrder> getAll(HashMap<String, Object> params);

	Integer toDoWorkNum(HashMap<String, Object> params);

	Integer toDoGoodsNum();

	// 退款修改数据
	Map<String, String> update(OrderRefund refund);

	// 根据订单id查询信息
	WorkOrder queryByOrderId(Integer orderId);

}
