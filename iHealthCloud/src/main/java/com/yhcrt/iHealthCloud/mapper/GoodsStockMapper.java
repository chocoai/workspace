package com.yhcrt.iHealthCloud.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.GoodsStock;
import com.yhcrt.iHealthCloud.entity.GoodsStockExample;

public interface GoodsStockMapper {
    long countByExample(GoodsStockExample example);

    int deleteByExample(GoodsStockExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(GoodsStock record);

    int insertSelective(GoodsStock record);

    List<GoodsStock> selectByExample(GoodsStockExample example);

    GoodsStock selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") GoodsStock record, @Param("example") GoodsStockExample example);

    int updateByExample(@Param("record") GoodsStock record, @Param("example") GoodsStockExample example);

    int updateByPrimaryKeySelective(GoodsStock record);

    int updateByPrimaryKey(GoodsStock record);
}