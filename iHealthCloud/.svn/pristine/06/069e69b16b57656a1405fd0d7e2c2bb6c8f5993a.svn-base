package com.yhcrt.iHealthCloud.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.HdPulse;
import com.yhcrt.iHealthCloud.entity.HdPulseExample;
import com.yhcrt.iHealthCloud.pojo.DataItem;
import com.yhcrt.iHealthCloud.pojo.HdPulseDTO;

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
    
    List<Object[]> selectForSleep(@Param("startTime")String startTime,@Param("endTime")String endTime);

    // 向数据库中请求近7天的所有数据
	List<HdPulse> selectHdPulse(Map<String, Object> map);
	
	List<HdPulseDTO> getHdPulseByTime(@Param("memberId") String memberId, @Param("startTime") String startTime,
			@Param("endTime") String endTime);
	
	/**
	 * 查询最近一周的心率
	 * @param memberId
	 * @return
	 */
	List<HdPulseDTO> getPulseDataForWeek(String memberId);
	
	/**
	 * 查询最近一周的心率数据的最大值、最小值、平均值
	 * @param memberId
	 * @return
	 */
	DataItem getMaxMinAvgPulseDataForWeek(String memberId);
}