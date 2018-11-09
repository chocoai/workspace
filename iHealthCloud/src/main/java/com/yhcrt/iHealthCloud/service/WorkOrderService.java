package com.yhcrt.iHealthCloud.service;	
	
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.WorkOrder;
	
	
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
    
    String sendServiceOrder(JSONObject pdata);
    
    String payWorkorder(JSONObject pdata);
    
    WorkOrder getByOrderNo(String orderNo);	
    
    String getWorkOrderList(JSONObject pdataObj);
    
    String setWorkOrderStatus(JSONObject pdataObj);
    
    String getWorkOrderDetail(JSONObject pdataObj);
    
	/** 获取待办工单列表 */
	String getToDoWorkOrders(JSONObject pdataObj);

	/** 获取全部工单列表 */
	String workOrdersAll(JSONObject pdataObj);

	/** 获取工单详情 */
	String orderDetail(JSONObject pdataObj);

	/** 分配服务人员 */
	String distribute(JSONObject pdataObj);

	/** 分配给我的工单 */
	String sendToMe(JSONObject pdataObj); 
    
	/**
	 * 
	 * @Description: 更新服务订单状态及退款原因
	 * @param cid
	 * @param orderStatus
	 * @return void
	 */
	void updateStatus(Integer cid,Integer orderStatus,String cext3);
	/**
	 * 
	 * @Description: 更新服务订单地址
	 * @param cid
	 * @param serviceObject
	 * @param tel
	 * @param address
	 * @return void
	 */
	void updateAddress(Integer cid,String serviceObject,String tel,String address);

    List<WorkOrder> selectYwOrderList(Integer orderstatus);
    
    List<WorkOrder> selectYwOrderList(Integer memberId,Integer orderstatus);
    
    WorkOrder getOrderInfo(Integer cid);
    
    WorkOrder getByCext1(String cext1);
    
    // 更新服务订单状态及退款原因
	void updateRefund(Integer orderid, Integer backorder, String refundtype,
			String content);
	
	/**
	 * 服务人员提交服务日志信息
	 * @param pdataObj
	 * @return
	 */
	String uploadServiceLog(JSONObject jsonObject);
	
	/**
	 * 获取工单的服务日志详细信息
	 * @param jsonObject
	 * @return
	 */
	String getServiceLogDetail(JSONObject jsonObject);
	
	/**
	 * 确认工单完成接口
	 * @param jsonObject
	 * @return
	 */
	String confirmCompleted(JSONObject jsonObject);
	
	String getWorkOrderTraceDetail(JSONObject jsonObject);
	
	String getOrgServicers(JSONObject jsonObject);
}	
