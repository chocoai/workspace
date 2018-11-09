package com.yhcrt.healthcloud.mall.mapper;

import java.util.HashMap;
import java.util.List;

import com.yhcrt.healthcloud.mall.entity.WorkOrder;
import com.yhcrt.healthcloud.mall.entity.WorkOrderExample;

public interface WorkOrderMapper {
	
	long countByExample(WorkOrderExample example);

	int deleteByExample(WorkOrderExample example);

	int deleteByPrimaryKey(Integer orderId);

	int insert(WorkOrder record);

	int insertSelective(WorkOrder record);

	List<WorkOrder> selectByExample(WorkOrderExample example);

	WorkOrder selectByPrimaryKey(Integer orderId);

	int updateByPrimaryKeySelective(WorkOrder record);

	int updateByPrimaryKey(WorkOrder record);

	/**
	 * @Title: search
	 * @Description: 根据条件查询出工单
	 * @param params
	 * @return
	 */
	List<WorkOrder> search(HashMap<String, Object> params);

	public Integer getToDoWorkNum(HashMap<String, Object> params);

	public Integer getToDoGoodsNum();

	// 修改服务工单
	void updateById(WorkOrder wo);

	// 根据订单id查询信息
	WorkOrder queryByOrderId(Integer orderId);

}