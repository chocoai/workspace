package com.yhcrt.healthcloud.device.mapper;

import com.yhcrt.healthcloud.device.entity.DSFSetting;
import com.yhcrt.healthcloud.device.entity.DSFSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DSFSettingMapper {
    long countByExample(DSFSettingExample example);

    int deleteByExample(DSFSettingExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(DSFSetting record);

    int insertSelective(DSFSetting record);

    List<DSFSetting> selectByExample(DSFSettingExample example);

    DSFSetting selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") DSFSetting record, @Param("example") DSFSettingExample example);

    int updateByExample(@Param("record") DSFSetting record, @Param("example") DSFSettingExample example);

    int updateByPrimaryKeySelective(DSFSetting record);

    int updateByPrimaryKey(DSFSetting record);
}