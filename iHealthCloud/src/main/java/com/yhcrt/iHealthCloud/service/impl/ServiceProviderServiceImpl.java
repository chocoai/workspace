package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Service;
import com.yhcrt.iHealthCloud.entity.ServiceProvider;
import com.yhcrt.iHealthCloud.entity.ServiceProviderExample;
import com.yhcrt.iHealthCloud.entity.ServiceProviderExample.Criteria;
import com.yhcrt.iHealthCloud.entity.UserComment;
import com.yhcrt.iHealthCloud.entity.YwImage;
import com.yhcrt.iHealthCloud.mapper.ServiceProviderMapper;
import com.yhcrt.iHealthCloud.service.ServiceProviderService;
import com.yhcrt.iHealthCloud.service.ServiceService;
import com.yhcrt.iHealthCloud.service.UserCommentService;
import com.yhcrt.iHealthCloud.service.YwImageService;
import com.yhcrt.iHealthCloud.util.Const;

/**
 * 
 * @author
 *
 */
@org.springframework.stereotype.Service
public class ServiceProviderServiceImpl extends BaseService implements ServiceProviderService {
	
	@Autowired
	private YwImageService ywImageService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private ServiceProviderMapper serviceProviderMapper;
	@Autowired
	private UserCommentService userCommentService;

	@Override
	public ServiceProvider getProviderById(Integer id) {
		return serviceProviderMapper.selectByPrimaryKey(id);
	}

	@Override
	public String getServiceProviderDetail(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String providerId = bizObj.getString("provider_id");
		if (StringUtils.isBlank(providerId)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		int id = Integer.parseInt(providerId);
		ServiceProvider provider = serviceProviderMapper.selectByPrimaryKey(id);
		List<Service> services = serviceService.selectByProviderId(id);
		List<YwImage> images =  ywImageService.getByRefId(id,Constants.PROVIDER); 
		List<UserComment> comments = userCommentService.getCommentsListByRefIdAndCommentType(id, Constants.PROVIDER);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("provider", provider);
		jsonObj.put("services", services);
		jsonObj.put("images", images);
		jsonObj.put("comments", comments);
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, jsonObj);
		return toJsonStringWithOutNull(jsonObject);
	}


	@Override
	public String getServiceProviders(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String providerType = bizObj.getString("provider_type");
		String providerName = bizObj.getString("provider_name");
		String area = bizObj.getString("area");
		String currentPage = bizObj.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = bizObj.getString(Const.TAG_PAGE_SIZE);
		
		ServiceProviderExample example = new ServiceProviderExample();
		Criteria criteria = example.createCriteria();
		criteria.andProviderTypeEqualTo(providerType).andAreaLike("%"+area+"%");
		if (StringUtils.isNotBlank(providerName)) {
			criteria.andProviderNameLike("%"+providerName+"%");
		}
		example.setOrderByClause(" provider_score desc ");
		
		List<ServiceProvider> providers = new ArrayList<ServiceProvider>();
		if (StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(currentPage)) {
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			providers = serviceProviderMapper.selectByExample(example);
			PageInfo<ServiceProvider> pageInfo = new PageInfo<>(providers);
			setPagingData(jsonObject, pageInfo.getPages(),pageInfo.getPageNum());
		}else {
			providers = serviceProviderMapper.selectByExample(example);
		}
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, providers);
		return toJsonStringWithOutNull(jsonObject);	
	}


	@Override
	public String getProvidersByGps(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String longitude = bizObj.getString("lng");
		String latitude = bizObj.getString("lat");
		if (StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		try {
			Double lng = Double.parseDouble(longitude);
			Double lat = Double.parseDouble(latitude);
			List<ServiceProvider> providers = serviceProviderMapper.getProvidersByGps(lng, lat);
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObject.put(Const.TAG_BIZ, providers);
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);	
	}

	@Override
	public String getRecommendProviders(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String serviceType = bizObj.getString("service_type");
		String providerName = bizObj.getString("provider_name");
		String area = bizObj.getString("area");
		String currentPage = bizObj.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = bizObj.getString(Const.TAG_PAGE_SIZE);
		
		if (StringUtils.isBlank(serviceType) || StringUtils.isBlank(area)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		
		List<ServiceProvider> providers = new ArrayList<ServiceProvider>();
		if (StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(currentPage)) {
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			providers = serviceProviderMapper.getRecommendProviders(serviceType, providerName, area);
			PageInfo<ServiceProvider> pageInfo = new PageInfo<>(providers);
			setPagingData(jsonObject, pageInfo.getPages(),pageInfo.getPageNum());
		}else {
			providers = serviceProviderMapper.getRecommendProviders(serviceType, providerName, area);
		}
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, providers);
		return toJsonStringWithOutNull(jsonObject);	
	}

}
