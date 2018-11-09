package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.HdBloodPressure;
import com.yhcrt.healthcloud.health.entity.HdBloodPressureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HdBloodPressureMapper {
    long countByExample(HdBloodPressureExample example);

    int deleteByExample(HdBloodPressureExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(HdBloodPressure record);

    int insertSelective(HdBloodPressure record);

    List<HdBloodPressure> selectByExample(HdBloodPressureExample example);

    HdBloodPressure selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") HdBloodPressure record, @Param("example") HdBloodPressureExample example);

    int updateByExample(@Param("record") HdBloodPressure record, @Param("example") HdBloodPressureExample example);

    int updateByPrimaryKeySelective(HdBloodPressure record);

    int updateByPrimaryKey(HdBloodPressure record);
    
    HdBloodPressure selectLatestByMemberId(int memberId);
    
    List<HdBloodPressure> selectByMon(Integer memberId);//最近一个月的数据
}