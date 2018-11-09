package com.yhcrt.iHealthCloud.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.OrderDetail;
import com.yhcrt.iHealthCloud.entity.OrderDetailExample;

public interface OrderDetailMapper {
	
    long countByExample(OrderDetailExample example);

    int deleteByExample(OrderDetailExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExample(OrderDetailExample example);

    OrderDetail selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
    
    void setDetailStatus(@Param("orderId")Integer orderId,@Param("orderStatus")Integer orderStatus);  //订单支付成功 子订单修改状态
    
    List<OrderDetail> getOrderDetailsByOrderId(Integer orderId);
}