/**
 * @Title:   ProviderServiceImpl.java 
 * @Package: com.yhcrt.healthcloud.provider.service.impl  
 * @Description: 
 * @author: rpf
 * @date: 2018年1月22日 
 * @version V1.0 
 * Copyright © 2018 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.provider.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.provider.entity.ServiceProvider;
import com.yhcrt.healthcloud.provider.entity.ServiceProviderExample;
import com.yhcrt.healthcloud.provider.entity.ServiceProviderExample.Criteria;
import com.yhcrt.healthcloud.provider.mapper.ServiceProviderMapper;
import com.yhcrt.healthcloud.provider.service.ProviderService;
import com.yhcrt.healthcloud.util.DateUtil;

/**
 * @ClassName: ProviderServiceImpl
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2018年1月22日 
 */
@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private ServiceProviderMapper providerMapper;
	
	@Override
	public List<ServiceProvider> getProvidersByOrgId(String orgId) {
		if (StringUtils.isBlank(orgId)) {
			return null;
		}
		ServiceProviderExample example = new ServiceProviderExample();
		example.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId)).andStatusEqualTo(Constants.STATUS_NORMAL);
		example.setOrderByClause("create_time desc");
		List<ServiceProvider> providers = providerMapper.selectByExample(example);
		return providers;
	}

	@Override
	public boolean isExistProvider(String orgId, String providerName, String providerId) {
		ServiceProviderExample example = new ServiceProviderExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrgIdEqualTo(Integer.valueOf(orgId));
		criteria.andProviderNameEqualTo(providerName);
		criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
		if (StringUtils.isNotBlank(providerId)) {
			criteria.andProviderIdNotEqualTo(Integer.valueOf(providerId));
		}
		List<ServiceProvider> providers = providerMapper.selectByExample(example);
		if (providers.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public int insert(ServiceProvider provider) {
		provider.setStatus(Constants.STATUS_NORMAL);
		provider.setCreateTime(DateUtil.getDateTime());
		return providerMapper.insert(provider);
	}

	@Override
	public int update(ServiceProvider provider) {
		provider.setUpdateTime(DateUtil.getDateTime());
		return providerMapper.updateByPrimaryKeySelective(provider);
	}

	@Override
	public ServiceProvider getProviderById(Integer providerId) {
		return providerMapper.selectByPrimaryKey(providerId);
	}

	//根据机构id集合查询机构服务商
	@Override
	public List<ServiceProvider> queryByOrgList(List<Integer> list) {
		return providerMapper.queryByOrgList(list);
	}

}
