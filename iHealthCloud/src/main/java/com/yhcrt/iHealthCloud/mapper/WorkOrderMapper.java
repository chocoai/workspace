package com.yhcrt.iHealthCloud.mapper;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.WorkOrder;
import com.yhcrt.iHealthCloud.entity.WorkOrderExample;
import com.yhcrt.iHealthCloud.pojo.OrderServiceLog;
import com.yhcrt.iHealthCloud.pojo.WorkOrderTrace;

public interface WorkOrderMapper {
    long countByExample(WorkOrderExample example);

    int deleteByExample(WorkOrderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(WorkOrder record);

    int insertSelective(WorkOrder record);

    List<WorkOrder> selectByExample(WorkOrderExample example);
    
    List<com.yhcrt.iHealthCloud.pojo.WorkOrderItem> getToDoWorkOrders(@Param("orgCode")String orgCode);
    
    List<com.yhcrt.iHealthCloud.pojo.WorkOrderItem> workOrdersAll(@Param("orgCode")String orgCode,@Param("status")String status);
    
    List<com.yhcrt.iHealthCloud.pojo.WorkOrderItem> sendToMe(@Param("empId")String empId,@Param("status")String status);
    
    WorkOrder orderDetail(@Param("orderId")Integer orderId);

    WorkOrder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") WorkOrder record, @Param("example") WorkOrderExample example);

    int updateByExample(@Param("record") WorkOrder record, @Param("example") WorkOrderExample example);

    int updateByPrimaryKeySelective(WorkOrder record);

    int updateByPrimaryKey(WorkOrder record);

    /**@Title: search
     * @Description: 根据条件查询出工单
     * @param params
     * @return    
    */
    List<WorkOrder> search(HashMap<String, Object> params);

    //修改交易单号
	void updateTranNum(WorkOrder workOrder);
	
	OrderServiceLog getWorkOrderServiceLogDetail(Integer orderId);
	
	WorkOrderTrace getWorkOrderTraceDetail(Integer orderId);
}