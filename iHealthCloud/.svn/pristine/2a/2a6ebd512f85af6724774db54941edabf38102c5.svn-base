package com.yhcrt.iHealthCloud.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.SecureLocationSetting;
import com.yhcrt.iHealthCloud.entity.SecureLocationSettingExample;

public interface SecureLocationSettingMapper {

	long countByExample(SecureLocationSettingExample example);

	int deleteByExample(SecureLocationSettingExample example);

	int deleteByPrimaryKey(Integer cid);

	int insert(SecureLocationSetting record);

	int insertSelective(SecureLocationSetting record);

	List<SecureLocationSetting> selectByExample(SecureLocationSettingExample example);

	SecureLocationSetting selectByPrimaryKey(Integer cid);
	
	SecureLocationSetting selectByImei(String imei);

	int updateByExampleSelective(@Param("record") SecureLocationSetting record,
			@Param("example") SecureLocationSettingExample example);

	int updateByExample(@Param("record") SecureLocationSetting record,
			@Param("example") SecureLocationSettingExample example);

	int updateByPrimaryKeySelective(SecureLocationSetting record);

	int updateByPrimaryKey(SecureLocationSetting record);
}