package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.HdBloodGlucose;
import com.yhcrt.iHealthCloud.entity.HdBloodGlucoseExample;
import com.yhcrt.iHealthCloud.pojo.BloodGlucoseDTO;
import com.yhcrt.iHealthCloud.pojo.DataItem;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HdBloodGlucoseMapper {

	long countByExample(HdBloodGlucoseExample example);

	int deleteByExample(HdBloodGlucoseExample example);

	int deleteByPrimaryKey(Integer cid);

	int insert(HdBloodGlucose record);

	int insertSelective(HdBloodGlucose record);

	List<HdBloodGlucose> selectByExample(HdBloodGlucoseExample example);

	HdBloodGlucose selectByPrimaryKey(Integer cid);

	int updateByExampleSelective(@Param("record") HdBloodGlucose record,
			@Param("example") HdBloodGlucoseExample example);

	int updateByExample(@Param("record") HdBloodGlucose record, @Param("example") HdBloodGlucoseExample example);

	int updateByPrimaryKeySelective(HdBloodGlucose record);

	int updateByPrimaryKey(HdBloodGlucose record);

	// 向数据库中请求近7天的所有数据
	List<HdBloodGlucose> selectHdBloodGlucose(Map<String, Object> map);

	/**
	 * 获取最新的血糖测量记录
	 * 
	 * @param memberId
	 * @return
	 */
	BloodGlucoseDTO getLatestBloodGlucose(String memberId);

	/**
	 * 获取某位会员的血糖测量历史记录
	 * 
	 * @param memberId
	 * @return
	 */
	List<BloodGlucoseDTO> listBloodGlucoseByMemberId(String memberId);

	/**
	 * 获取某位会员在所选时间段内的血糖测量数据
	 * 
	 * @param memberId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<BloodGlucoseDTO> listBloodGlucoseByTime(@Param("memberId") String memberId,
			@Param("startTime") String startTime, @Param("endTime") String endTime);
	
	List<BloodGlucoseDTO> getBloodGlucoseDataForWeek(String memberId);
	
	DataItem getMaxMinAvgBloodGlucoseDataForWeek(String memberId);

}