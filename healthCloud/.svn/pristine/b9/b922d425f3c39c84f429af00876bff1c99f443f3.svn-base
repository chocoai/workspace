package com.yhcrt.healthcloud.device.mapper;

import com.yhcrt.healthcloud.device.entity.HeartRateSetting;
import com.yhcrt.healthcloud.device.entity.HeartRateSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}