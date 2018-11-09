package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.OrderRefund;
import com.yhcrt.iHealthCloud.entity.OrderRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderRefundMapper {
	
    long countByExample(OrderRefundExample example);

    int deleteByExample(OrderRefundExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderRefund record);

    int insertSelective(OrderRefund record);

    List<OrderRefund> selectByExample(OrderRefundExample example);

    OrderRefund selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderRefund record, @Param("example") OrderRefundExample example);

    int updateByExample(@Param("record") OrderRefund record, @Param("example") OrderRefundExample example);

    int updateByPrimaryKeySelective(OrderRefund record);

    int updateByPrimaryKey(OrderRefund record);
}