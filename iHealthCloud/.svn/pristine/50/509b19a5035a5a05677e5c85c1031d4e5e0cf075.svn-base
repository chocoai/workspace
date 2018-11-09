package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.BloodPressureSetting;
import com.yhcrt.iHealthCloud.entity.BloodPressureSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BloodPressureSettingMapper {
	
    long countByExample(BloodPressureSettingExample example);

    int deleteByExample(BloodPressureSettingExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(BloodPressureSetting record);

    int insertSelective(BloodPressureSetting record);

    List<BloodPressureSetting> selectByExample(BloodPressureSettingExample example);

    BloodPressureSetting selectByPrimaryKey(Integer cid);
    
    BloodPressureSetting selectByMemberId(Integer memberId);

    int updateByExampleSelective(@Param("record") BloodPressureSetting record, @Param("example") BloodPressureSettingExample example);

    int updateByExample(@Param("record") BloodPressureSetting record, @Param("example") BloodPressureSettingExample example);

    int updateByPrimaryKeySelective(BloodPressureSetting record);

    int updateByPrimaryKey(BloodPressureSetting record);
}