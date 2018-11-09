package com.yhcrt.healthcloud.organization.mapper;

import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.entity.OrganizationExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    
    String getLastOrgCodeByParentId(Integer parentOrgId);

    List<Organization> getAllChildOrgByParentCode(String orgCode);

    //机构树查询
	List<Organization> getChildOrgByParentCode(String orgCode);

}