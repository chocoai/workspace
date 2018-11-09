package com.yhcrt.iHealthCloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.Organization;
import com.yhcrt.iHealthCloud.entity.OrganizationExample;

public interface OrganizationMapper {
    long countByExample(OrganizationExample example);

    int deleteByExample(OrganizationExample example);

    int deleteByPrimaryKey(Integer orgId);

    int insert(Organization record);

    int insertSelective(Organization record);

    List<Organization> selectByExampleWithBLOBs(OrganizationExample example);

    List<Organization> selectByExample(OrganizationExample example);

    Organization selectByPrimaryKey(Integer orgId);

    int updateByExampleSelective(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByExampleWithBLOBs(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByExample(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKeyWithBLOBs(Organization record);

    int updateByPrimaryKey(Organization record);
    
    Organization getOrgRootNode();
    
    List<Organization> getChildOrgByParentId(Integer parentOrgId);
    
    String getLastOrgCodeByParentId(Integer parentOrgId);

    List<Organization> getAllChildOrgByParentCode(String orgCode);
    
    
    List<Organization> getHotOrgByAreaCode(@Param("areaCode") String areaCode);
    
    List<Organization> getOrgByServiceType(@Param("areaCode") String areaCode,@Param("serviceType")String serviceType, @Param("orgName")String orgName);
    
    List<Organization> getOrgListByGps(@Param("lat") Double lat,@Param("lgt")Double lgt,@Param("orgType")String orgType,@Param("serviceType")String serviceType);
    

}