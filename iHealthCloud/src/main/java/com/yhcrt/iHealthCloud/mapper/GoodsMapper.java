package com.yhcrt.iHealthCloud.mapper;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.Goods;
import com.yhcrt.iHealthCloud.entity.GoodsExample;

public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    /**@Title: search
     * @Description: 根据条件查询出商品
     * @param params
     * @return    
    */
    List<Goods> search(HashMap<String, Object> params);
}