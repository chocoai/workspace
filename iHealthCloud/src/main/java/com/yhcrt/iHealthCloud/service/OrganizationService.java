package com.yhcrt.iHealthCloud.service;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.Organization;
import com.yhcrt.iHealthCloud.entity.OrganizationExample;


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
     * 根据机构ID获取此机构的子节点机构
     * @param parentOrgId
     * @return
     */
    List<Organization> getChildOrgByParentId(Integer parentOrgId);
    
    /**
     * 根据机构ID获取此机构的子节点机构中最大orgCode
     * @param parentOrgId
     * @return
     */
    String getLastOrgCodeByParentId(Integer parentOrgId);
    
    /**
     * 查询所有组织机构
     * @return
     */
    List<Organization> listAllOrg();

    /**
     * 根据机构编码orgCode获取该机构下的所有子机构
     * @param orgCode
     * @return
     */
    List<Organization> getAllChildOrgByParentCode(String orgCode);
    
    List<Organization> listOrgsByArgs(HashMap<String, Object> args);
    
    boolean isExistOrg(String orgId, String parentOrgId, String orgName);
    
	// String getHotOrgByCity(JSONObject pdata);
    
    String getOrgByServiceType(JSONObject pdata);
    
    String getOrgByType(JSONObject pdata);
    
    /**
     * 获取机构详情
     * @param pdata
     * @return
     */
    String getOrgDetail(JSONObject pdata);
    
    String getOrgListByGps(JSONObject pdata);
    
    /**
     * 获取推荐服务组织、养老机构、日间照料中心、居家养老服务组织接口
     * @param pdata
     * @return
     */
    String getRecommendOrg(JSONObject jsonObject);
    
    /**
     * 获取服务机构列表接口,根据机构类型、机构性质、服务区域查询机构
     * @param pdata
     * @return
     */
    String getOrgs(JSONObject jsonObject); 
    
    /**
     * 获取服务机构筛选接口
     * @param jsonObject
     * @return
     */
    String getOrgsFilterItem(JSONObject jsonObject); 
}
