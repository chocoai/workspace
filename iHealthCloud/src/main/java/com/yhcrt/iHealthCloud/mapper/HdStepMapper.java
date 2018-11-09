package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.HdStep;
import com.yhcrt.iHealthCloud.entity.HdStepExample;
import com.yhcrt.iHealthCloud.pojo.DataItem;
import com.yhcrt.iHealthCloud.pojo.HdStepDTO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HdStepMapper {
	long countByExample(HdStepExample example);

	int deleteByExample(HdStepExample example);

	int deleteByPrimaryKey(Integer cid);

	int insert(HdStep record);

	int insertSelective(HdStep record);

	List<HdStep> selectByExample(HdStepExample example);

	HdStep selectByPrimaryKey(Integer cid);

	int updateByExampleSelective(@Param("record") HdStep record, @Param("example") HdStepExample example);

	int updateByExample(@Param("record") HdStep record, @Param("example") HdStepExample example);

	int updateByPrimaryKeySelective(HdStep record);

	int updateByPrimaryKey(HdStep record);

	int getTodayStep(String memberId);

	// 查询最近七天行走总步数
	List<HdStep> selectHdStep(Map<String, Object> map);

	/**
	 * 根据选定的日期查询该时间段内用户的步数
	 * 
	 * @param memberId
	 *            会员ID
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	List<HdStepDTO> getStepByTime(@Param("memberId") Integer memberId, @Param("startTime") String startTime,
			@Param("endTime") String endTime);
	
	List<HdStepDTO> getStepDataForWeek(String memberId);
	
	DataItem getMaxMinAvgStepDataForWeek(String memberId);
	
}