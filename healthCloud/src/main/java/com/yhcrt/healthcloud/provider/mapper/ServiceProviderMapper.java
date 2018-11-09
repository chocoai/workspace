package com.yhcrt.healthcloud.provider.mapper;

import com.yhcrt.healthcloud.provider.entity.ServiceProvider;
import com.yhcrt.healthcloud.provider.entity.ServiceProviderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ServiceProviderMapper {
	
    long countByExample(ServiceProviderExample example);

    int deleteByExample(ServiceProviderExample example);

    int deleteByPrimaryKey(Integer providerId);

    int insert(ServiceProvider record);

    int insertSelective(ServiceProvider record);

    List<ServiceProvider> selectByExample(ServiceProviderExample example);

    ServiceProvider selectByPrimaryKey(Integer providerId);

    int updateByExampleSelective(@Param("record") ServiceProvider record, @Param("example") ServiceProviderExample example);

    int updateByExample(@Param("record") ServiceProvider record, @Param("example") ServiceProviderExample example);

    int updateByPrimaryKeySelective(ServiceProvider record);

    int updateByPrimaryKey(ServiceProvider record);

    //根据机构id集合查询机构服务商
	List<ServiceProvider> queryByOrgList(List<Integer> list);
	
}