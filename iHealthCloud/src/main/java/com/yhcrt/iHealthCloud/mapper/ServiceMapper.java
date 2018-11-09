package com.yhcrt.iHealthCloud.mapper;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.Service;
import com.yhcrt.iHealthCloud.entity.ServiceExample;

public interface ServiceMapper {
    long countByExample(ServiceExample example);

    int deleteByExample(ServiceExample example);

    int deleteByPrimaryKey(Integer serviceId);

    int insert(Service record);

    int insertSelective(Service record);

    List<Service> selectByExampleWithBLOBs(ServiceExample example);

    List<Service> selectByExample(ServiceExample example);

    Service selectByPrimaryKey(Integer serviceId);

    int updateByExampleSelective(@Param("record") Service record, @Param("example") ServiceExample example);

    int updateByExampleWithBLOBs(@Param("record") Service record, @Param("example") ServiceExample example);

    int updateByExample(@Param("record") Service record, @Param("example") ServiceExample example);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKeyWithBLOBs(Service record);

    int updateByPrimaryKey(Service record);

    /**@Title: search
     * @Description: 根据条件查询
     * @param params
     * @return    
    */
    List<Service> search(HashMap<String, Object> params);
    
	List<Service> getServices(@Param("providerType") String providerType, @Param("area") String area,
			@Param("serviceType") String serviceType, @Param("orderByClause") String orderByClause);
}