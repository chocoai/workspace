package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.StepSetting;
import com.yhcrt.iHealthCloud.entity.StepSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StepSettingMapper {
    long countByExample(StepSettingExample example);

    int deleteByExample(StepSettingExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(StepSetting record);

    int insertSelective(StepSetting record);

    List<StepSetting> selectByExample(StepSettingExample example);

    StepSetting selectByPrimaryKey(Integer cid);
    
    StepSetting selectByMemberId(Integer memberId);

    int updateByExampleSelective(@Param("record") StepSetting record, @Param("example") StepSettingExample example);

    int updateByExample(@Param("record") StepSetting record, @Param("example") StepSettingExample example);

    int updateByPrimaryKeySelective(StepSetting record);

    int updateByPrimaryKey(StepSetting record);
}