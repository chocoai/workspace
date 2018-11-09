package com.yhcrt.healthcloud.organization.service;

import java.util.HashMap;
import java.util.List;

import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.entity.OrganizationExample;

/**
 * @Description: 机构SERVICE层接口类
 * @author gongjun
 * 2017年5月17日
 * 版权所有：武汉炎黄创新科技服务有限公司
 */
public interface OrganizationService {
	
	/**
	 * 新增机构
	 * @param organization
	 * @return
	 */
    int insert(Organization organization);
    
    /**
     * 根据条件删除机构
     * @param example
     * @return
     */
    int deleteByExample(OrganizationExample example);

    /**
     * 根据机构ID删除机构
     * @param orgId
     * @return
     */
    int deleteByOrgId(Integer orgId);
    
    /**
     * 更新机构
     * @param record
     * @return
     */
    int update(Organization organization);
	
    /**
     * 根据机构ID查询机构
     * @param orgId
     * @return
     */
    Organization selectByPrimaryKey(Integer orgId);
    
    /**
     * 获取机构根节点
     * @return
     */
    Organization getOrgRootNode();
    
    /**
     * 根据机构ID获取此机构的子节点机构中最大orgCode
     * @param parentOrgId
     * @return
     */
    String getLastOrgCodeByParentId(Integer parentOrgId);
    
    /**
     * 根据机构编码orgCode获取该机构下的所有子机构
     * @param orgCode
     * @return
     */
    List<Organization> getAllChildOrgByParentCode(String orgCode);
    
    List<Organization> listOrgsByArgs(HashMap<String, Object> args);
    
    boolean isExistOrg(String orgId, String parentOrgId, String orgName);

    /**
     * 机构树查询
     * @param orgCode
     * @return
     */
	List<Organization> getChildOrgByParentCode(String orgCode);

}
