package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.ServicePrice;
import com.yhcrt.iHealthCloud.entity.ServicePriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServicePriceMapper {
    long countByExample(ServicePriceExample example);

    int deleteByExample(ServicePriceExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(ServicePrice record);

    int insertSelective(ServicePrice record);

    List<ServicePrice> selectByExampleWithBLOBs(ServicePriceExample example);

    List<ServicePrice> selectByExample(ServicePriceExample example);

    ServicePrice selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") ServicePrice record, @Param("example") ServicePriceExample example);

    int updateByExampleWithBLOBs(@Param("record") ServicePrice record, @Param("example") ServicePriceExample example);

    int updateByExample(@Param("record") ServicePrice record, @Param("example") ServicePriceExample example);

    int updateByPrimaryKeySelective(ServicePrice record);

    int updateByPrimaryKeyWithBLOBs(ServicePrice record);

    int updateByPrimaryKey(ServicePrice record);
}