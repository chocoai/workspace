package com.yhcrt.iHealthCloud.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.Device;
import com.yhcrt.iHealthCloud.entity.DeviceExample;

public interface DeviceMapper {
	
    long countByExample(DeviceExample example);

    int deleteByExample(DeviceExample example);

    int deleteByPrimaryKey(Integer deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> selectByExample(DeviceExample example);

    Device selectByPrimaryKey(Integer deviceId);
    
    Device selectByImei(String imei);
    
    int startDevice(Integer deviceId);

    int updateByExampleSelective(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByExample(@Param("record") Device record, @Param("example") DeviceExample example);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}