package com.yhcrt.iHealthCloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.ServiceProvider;
import com.yhcrt.iHealthCloud.entity.ServiceProviderExample;
import com.yhcrt.iHealthCloud.entity.ServiceProviderWithBLOBs;

public interface ServiceProviderMapper {
    long countByExample(ServiceProviderExample example);

    int deleteByExample(ServiceProviderExample example);

    int deleteByPrimaryKey(Integer providerId);

    int insert(ServiceProviderWithBLOBs record);

    int insertSelective(ServiceProviderWithBLOBs record);

    List<ServiceProviderWithBLOBs> selectByExampleWithBLOBs(ServiceProviderExample example);

    List<ServiceProvider> selectByExample(ServiceProviderExample example);
    
    List<ServiceProvider> getProvidersByGps(@Param("lng") Double lng, @Param("lat") Double lat);

    List<ServiceProvider> getRecommendProviders(@Param("serviceType") String serviceType, @Param("providerName") String providerName, @Param("area") String area);

    ServiceProviderWithBLOBs selectByPrimaryKey(Integer providerId);

    int updateByExampleSelective(@Param("record") ServiceProviderWithBLOBs record, @Param("example") ServiceProviderExample example);

    int updateByExampleWithBLOBs(@Param("record") ServiceProviderWithBLOBs record, @Param("example") ServiceProviderExample example);

    int updateByExample(@Param("record") ServiceProvider record, @Param("example") ServiceProviderExample example);

    int updateByPrimaryKeySelective(ServiceProviderWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ServiceProviderWithBLOBs record);

    int updateByPrimaryKey(ServiceProvider record);
}