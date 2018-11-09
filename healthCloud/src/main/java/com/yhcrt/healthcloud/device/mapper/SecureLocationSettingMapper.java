package com.yhcrt.healthcloud.device.mapper;

import com.yhcrt.healthcloud.device.entity.SecureLocationSetting;
import com.yhcrt.healthcloud.device.entity.SecureLocationSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SecureLocationSettingMapper {
	
    long countByExample(SecureLocationSettingExample example);

    int deleteByExample(SecureLocationSettingExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(SecureLocationSetting record);

    int insertSelective(SecureLocationSetting record);

    List<SecureLocationSetting> selectByExample(SecureLocationSettingExample example);

    SecureLocationSetting selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") SecureLocationSetting record, @Param("example") SecureLocationSettingExample example);

    int updateByExample(@Param("record") SecureLocationSetting record, @Param("example") SecureLocationSettingExample example);

    int updateByPrimaryKeySelective(SecureLocationSetting record);

    int updateByPrimaryKey(SecureLocationSetting record);
}