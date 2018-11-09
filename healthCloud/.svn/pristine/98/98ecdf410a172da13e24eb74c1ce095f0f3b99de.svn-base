package com.yhcrt.healthcloud.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.mall.entity.YwOrder;
import com.yhcrt.healthcloud.mall.entity.YwOrderExample;

public interface YwOrderMapper {
    long countByExample(YwOrderExample example);

    int deleteByExample(YwOrderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(YwOrder record);

    int insertSelective(YwOrder record);

    List<YwOrder> selectByExample(YwOrderExample example);

    YwOrder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") YwOrder record, @Param("example") YwOrderExample example);

    int updateByExample(@Param("record") YwOrder record, @Param("example") YwOrderExample example);

    int updateByPrimaryKeySelective(YwOrder record);

    int updateByPrimaryKey(YwOrder record);

    /**@Title: search
     * @Description: 通过条件查询订单
     * @param params
     * @return    
    */
    List<YwOrder> search(Map<String, Object> params);

    //根据orderId修改订单状态
	void updateByStatus(YwOrder order);
}