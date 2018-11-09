package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.HdSleep;
import com.yhcrt.iHealthCloud.entity.HdSleepExample;
import com.yhcrt.iHealthCloud.pojo.DataItem;
import com.yhcrt.iHealthCloud.pojo.SleepDTO;

import java.util.List;
import java.util.Map;

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

    //获取最近七天的数据
	List<HdSleep> selectHdSleep(Map<String, Object> map);
	
	/**
	 * 获取最新的睡眠数据
	 * @param memberId
	 * @return
	 */
	SleepDTO getLatestSleepData(String memberId);
	
	/**
	 * 获取历史睡眠数据
	 * @param memberId
	 * @return
	 */
	List<SleepDTO> listSleepDataByMemberId(String memberId);
	
	/**
	 * 获取某个时间段内的睡眠数据
	 * @param memberId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<SleepDTO> listSleepDataByTime(@Param("memberId") String memberId,
			@Param("startTime") String startTime, @Param("endTime") String endTime);
	
	/**
	 * 查询最近一周的睡眠数据
	 * @param memberId
	 * @return
	 */
	List<SleepDTO> getSleepDataForWeek(String memberId);
	
	/**
	 * 查询最近一周的睡眠数据的最大值、最小值、平均值
	 * @param memberId
	 * @return
	 */
	DataItem getMaxMinAvgSleepDataForWeek(String memberId);
}