package com.yhcrt.iHealthCloud.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.Location;
import com.yhcrt.iHealthCloud.entity.LocationExample;
import com.yhcrt.iHealthCloud.pojo.LocationData;

public interface LocationMapper {
	
    long countByExample(LocationExample example);

    int deleteByExample(LocationExample example);

    int deleteByPrimaryKey(Integer locationId);

    int insert(Location record);

    int insertSelective(Location record);

    List<Location> selectByExample(LocationExample example);
    
    List<Location> getLocation();
    
    List<Location> getOrbit(@Param("imei")String imei,@Param("locationTime")String locationTime);
    
    List<Location> selectByPrimaryKey(String imei);

    int updateByExampleSelective(@Param("record") Location record, @Param("example") LocationExample example);

    int updateByExample(@Param("record") Location record, @Param("example") LocationExample example);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);
    
    List<LocationData> ListOnlineDeviceLocation(String orgId);
    
    List<LocationData> searchOnlineDeviceLocation(@Param("orgId") String orgId,@Param("param")String param);
}