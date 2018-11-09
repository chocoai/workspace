package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.HdBloodPressure;
import com.yhcrt.iHealthCloud.entity.HdBloodPressureExample;
import com.yhcrt.iHealthCloud.pojo.BloodPressureDTO;
import com.yhcrt.iHealthCloud.pojo.DataItem;

import java.util.List;
import java.util.Map;

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
    
    /**
     * 根据会员ID查询某会员的血压的最新测量数据
     * @param memberId
     * @return
     */
    BloodPressureDTO getLatestBloodPressure(String memberId);
    
    /**
     * 查询某个会员在所选时间段内的血压数据
     * @param memberId
     * @param startTime
     * @param endTime
     * @return
     */
	List<BloodPressureDTO> listBloodPressureByTime(@Param("memberId") String memberId,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

    // 向数据库中请求近7天的所有数据
	List<HdBloodPressure> selectHdBloodPressure(Map<String, Object> map);
	
	List<BloodPressureDTO> getBloodPressureDataForWeek(String memberId);
	
	DataItem getMaxMinAvgBloodPressureDataForWeek(String memberId);
	
}