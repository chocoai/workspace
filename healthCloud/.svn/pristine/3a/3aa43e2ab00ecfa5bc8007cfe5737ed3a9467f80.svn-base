package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.HdPulse;
import com.yhcrt.healthcloud.health.entity.HdPulseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HdPulseMapper {
    long countByExample(HdPulseExample example);

    int deleteByExample(HdPulseExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(HdPulse record);

    int insertSelective(HdPulse record);

    List<HdPulse> selectByExample(HdPulseExample example);

    HdPulse selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") HdPulse record, @Param("example") HdPulseExample example);

    int updateByExample(@Param("record") HdPulse record, @Param("example") HdPulseExample example);

    int updateByPrimaryKeySelective(HdPulse record);

    int updateByPrimaryKey(HdPulse record);
    
    HdPulse selectLatestByMemberId(int memberId);
    
    List<HdPulse> selectByMon(Integer memberId);//最近一个月的数据
}