package com.yhcrt.iHealthCloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.HeartRateSetting;
import com.yhcrt.iHealthCloud.entity.HeartRateSettingExample;

public interface HeartRateSettingMapper {
	
	long countByExample(HeartRateSettingExample example);

	int deleteByExample(HeartRateSettingExample example);

	int deleteByPrimaryKey(Integer cid);

	int insert(HeartRateSetting record);

	int insertSelective(HeartRateSetting record);

	List<HeartRateSetting> selectByExample(HeartRateSettingExample example);

	HeartRateSetting selectByPrimaryKey(Integer cid);

	int updateByExampleSelective(@Param("record") HeartRateSetting record, @Param("example") HeartRateSettingExample example);

	int updateByExample(@Param("record") HeartRateSetting record, @Param("example") HeartRateSettingExample example);

	int updateByPrimaryKeySelective(HeartRateSetting record);

	int updateByPrimaryKey(HeartRateSetting record);
	
	HeartRateSetting selectByImei(String imei);
	
}