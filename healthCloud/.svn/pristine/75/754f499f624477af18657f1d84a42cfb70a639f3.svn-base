package com.yhcrt.healthcloud.device.mapper;

import com.yhcrt.healthcloud.device.entity.Device;
import com.yhcrt.healthcloud.device.entity.DeviceExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {
	
    long countByExample(DeviceExample example);

    int deleteByExample(DeviceExample example);

    int deleteByPrimaryKey(Integer deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(Integer deviceId);
    
    List<Device> selectByImei(String imei);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    //新增修改时排除重复
	int countByMap(Map<String, Object> map);
}