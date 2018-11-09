package com.yhcrt.iHealthCloud.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.YwOrder;
import com.yhcrt.iHealthCloud.entity.YwOrderExample;

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
    List<YwOrder> search(HashMap<String, Object> params);

    //修改交易订单号
	void updateTranNum(YwOrder ywOrder);
	
	YwOrder selectByCext1(String cext1);

}