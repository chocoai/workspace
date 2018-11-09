package com.yhcrt.healthcloud.organization.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.entity.OrganizationExample;
import com.yhcrt.healthcloud.organization.entity.OrganizationExample.Criteria;
import com.yhcrt.healthcloud.organization.mapper.OrganizationMapper;
import com.yhcrt.healthcloud.organization.service.OrganizationService;
import com.yhcrt.healthcloud.util.DateUtil;
/**
 * @Description: 机构SERVICE层实现类
 * @author gongjun
 * 2017年5月17日
 * 版权所有：武汉炎黄创新科技服务有限公司
 */

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private  OrganizationMapper organizationMapper;
	
	/**
	 * 根据机构ID查询机构信息
	 * @param orgId
	 * @return
	 */
	@Override
	public Organization selectByPrimaryKey(Integer orgId) {
		return organizationMapper.selectByPrimaryKey(orgId);
	}

	@Override
	public Organization getOrgRootNode() {
		return organizationMapper.getOrgRootNode();
	}

	@Override
	public int insert(Organization organization) {
		organization.setStatus(Constants.STATUS_NORMAL);
		organization.setCreateTime(DateUtil.getDateTime());
		String orgCode;
		// 查询当前的资源编码
		Organization parentOrg = organizationMapper.selectByPrimaryKey(organization.getParentOrgId());
		String lastOrgCode = organizationMapper.getLastOrgCodeByParentId(organization.getParentOrgId());
		if (StringUtils.isNotBlank(lastOrgCode)) {
			orgCode = String.valueOf((Long.parseLong(lastOrgCode) + 1));
		} else {
			orgCode = parentOrg.getOrgCode() + organizationMapper.getOrgRootNode().getOrgCode();
		}
		organization.setOrgCode(orgCode);
		return organizationMapper.insert(organization);
	}

	@Override
	public int deleteByExample(OrganizationExample example) {
		return organizationMapper.deleteByExample(example);
	}

	@Override
	public int deleteByOrgId(Integer orgId) {
		return organizationMapper.deleteByPrimaryKey(orgId);
	}

	@Override
	public int update(Organization org) {
		Organization organization = organizationMapper.selectByPrimaryKey(org.getOrgId());
		BeanUtils.copyProperties(org, organization, "createUser", "status", "createTime");
		organization.setUpdateTime(DateUtil.getDateTime());
		return organizationMapper.updateByPrimaryKeySelective(organization);
	}

    @Override
    public List<Organization> getAllChildOrgByParentCode(String orgCode) {
        return organizationMapper.getAllChildOrgByParentCode(orgCode);
    }

	@Override
	public List<Organization> listOrgsByArgs(HashMap<String, Object> args) {
		OrganizationExample example = new OrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
		if (args.get("orgName") != null) {
			criteria.andOrgNameEqualTo((String) args.get("orgName"));
		}
		if (args.get("parentOrgId") != null) {
			criteria.andParentOrgIdEqualTo(Integer.valueOf((String) args.get("parentOrgId")));
		}
		return organizationMapper.selectByExample(example);
	}

	@Override
	public String getLastOrgCodeByParentId(Integer parentOrgId) {
		return organizationMapper.getLastOrgCodeByParentId(parentOrgId);
	}

	@Override
	public boolean isExistOrg(String orgId, String parentOrgId, String orgName) {
		OrganizationExample example = new OrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
		if(StringUtils.isNotBlank(parentOrgId)){
			criteria.andParentOrgIdEqualTo(Integer.parseInt(parentOrgId));
		}else{
			criteria.andParentOrgIdIsNull();
		}
		criteria.andOrgNameEqualTo(orgName);
		if(StringUtils.isNoneBlank(orgId)){
			criteria.andOrgIdNotEqualTo(Integer.parseInt(orgId));
		}
		List<Organization> orgs = organizationMapper.selectByExample(example);
		if (orgs.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	//机构树查询
	@Override
	public List<Organization> getChildOrgByParentCode(String orgCode) {
		return organizationMapper.getChildOrgByParentCode(orgCode);
	}

}
