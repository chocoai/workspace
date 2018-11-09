package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.HdSleep;
import com.yhcrt.healthcloud.health.entity.HdSleepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HdSleepMapper {
    long countByExample(HdSleepExample example);

    int deleteByExample(HdSleepExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(HdSleep record);

    int insertSelective(HdSleep record);

    List<HdSleep> selectByExample(HdSleepExample example);

    HdSleep selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") HdSleep record, @Param("example") HdSleepExample example);

    int updateByExample(@Param("record") HdSleep record, @Param("example") HdSleepExample example);

    int updateByPrimaryKeySelective(HdSleep record);

    int updateByPrimaryKey(HdSleep record);
    
    HdSleep selectLatestByMemberId(int memberId);
    
    List<HdSleep> selectByMon(Integer memberId);//最近一个月的数据

	List<HdSleep> selectHdSleep(HdSleepExample example);
}