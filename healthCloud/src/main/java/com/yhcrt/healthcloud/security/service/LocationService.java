package com.yhcrt.healthcloud.security.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.security.entity.Location;
import com.yhcrt.healthcloud.security.entity.LocationExample;

public interface LocationService {
    long countByExample(LocationExample example);

    int deleteByExample(LocationExample example);

    int deleteByPrimaryKey(Integer locationId);

    int insert(Location record);

    int insertSelective(Location record);

    List<Location> selectByExample(LocationExample example);
    
    List<Location> getLocation(Map<String, Object> map);
    
    List<Location> getOrbit(String imei,String locationTime);

    List<Location> selectByPrimaryKey(String imei);

    int updateByExampleSelective(@Param("record") Location record, @Param("example") LocationExample example);

    int updateByExample(@Param("record") Location record, @Param("example") LocationExample example);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);	
}
