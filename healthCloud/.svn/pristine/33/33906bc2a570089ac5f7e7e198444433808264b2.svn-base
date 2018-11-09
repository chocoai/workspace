package com.yhcrt.healthcloud.mall.mapper;

import com.yhcrt.healthcloud.mall.entity.ServiceLog;
import com.yhcrt.healthcloud.mall.entity.ServiceLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceLogMapper {
    long countByExample(ServiceLogExample example);

    int deleteByExample(ServiceLogExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(ServiceLog record);

    int insertSelective(ServiceLog record);

    List<ServiceLog> selectByExample(ServiceLogExample example);

    ServiceLog selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") ServiceLog record, @Param("example") ServiceLogExample example);

    int updateByExample(@Param("record") ServiceLog record, @Param("example") ServiceLogExample example);

    int updateByPrimaryKeySelective(ServiceLog record);

    int updateByPrimaryKey(ServiceLog record);
}