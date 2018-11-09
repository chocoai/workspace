/**
 * @Title:   ProviderService.java 
 * @Package: com.yhcrt.healthcloud.provider.service  
 * @Description: 
 * @author: rpf
 * @date: 2018年1月22日 
 * @version V1.0 
 * Copyright © 2018 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.provider.service;

import java.util.List;

import com.yhcrt.healthcloud.provider.entity.ServiceProvider;

/**
 * @ClassName: ProviderService
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2018年1月22日 
 */
public interface ProviderService {
	
	List<ServiceProvider> getProvidersByOrgId(String orgId);
	
	boolean isExistProvider(String orgId, String providerName, String providerId);
	
	int insert(ServiceProvider provider);
	
	int update(ServiceProvider provider);
	
	ServiceProvider getProviderById(Integer providerId);

	//根据机构id集合查询机构服务商
	List<ServiceProvider> queryByOrgList(List<Integer> list);

}
