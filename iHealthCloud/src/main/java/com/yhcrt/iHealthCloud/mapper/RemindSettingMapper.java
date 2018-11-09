package com.yhcrt.iHealthCloud.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.RemindSetting;
import com.yhcrt.iHealthCloud.entity.RemindSettingExample;

public interface RemindSettingMapper {
    long countByExample(RemindSettingExample example);

    int deleteByExample(RemindSettingExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(RemindSetting record);

    int insertSelective(RemindSetting record);

    List<RemindSetting> selectByExample(RemindSettingExample example);

    RemindSetting selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") RemindSetting record, @Param("example") RemindSettingExample example);

    int updateByExample(@Param("record") RemindSetting record, @Param("example") RemindSettingExample example);

    int updateByPrimaryKeySelective(RemindSetting record);

    int updateByPrimaryKey(RemindSetting record);
}