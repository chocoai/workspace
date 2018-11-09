package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.HdBloodGlucose;
import com.yhcrt.healthcloud.health.entity.HdBloodGlucoseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HdBloodGlucoseMapper {
    long countByExample(HdBloodGlucoseExample example);

    int deleteByExample(HdBloodGlucoseExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(HdBloodGlucose record);

    int insertSelective(HdBloodGlucose record);

    List<HdBloodGlucose> selectByExample(HdBloodGlucoseExample example);

    HdBloodGlucose selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") HdBloodGlucose record, @Param("example") HdBloodGlucoseExample example);

    int updateByExample(@Param("record") HdBloodGlucose record, @Param("example") HdBloodGlucoseExample example);

    int updateByPrimaryKeySelective(HdBloodGlucose record);

    int updateByPrimaryKey(HdBloodGlucose record);
    
    HdBloodGlucose selectLatestByMemberId(int memberId);
    
    List<HdBloodGlucose> selectByMon(Integer memberId);//最近一个月的数据
}