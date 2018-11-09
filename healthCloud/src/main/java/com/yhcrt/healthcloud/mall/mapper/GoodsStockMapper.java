package com.yhcrt.healthcloud.mall.mapper;

import com.yhcrt.healthcloud.mall.entity.GoodsStock;
import com.yhcrt.healthcloud.mall.entity.GoodsStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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