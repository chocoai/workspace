package com.yhcrt.healthcloud.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.mall.entity.ServicePrice;
import com.yhcrt.healthcloud.mall.mapper.ServicePriceMapper;
import com.yhcrt.healthcloud.mall.service.ServicePriceService;

@Service
public class ServicePriceServiceImpl implements ServicePriceService {

	@Autowired
	private ServicePriceMapper servicePriceMapper;

	// 根据cid查询数据
	@Override
	public ServicePrice queryByServicePrice(ServicePrice servicePrice) {
		return servicePriceMapper.queryByServicePrice(servicePrice);
	}

	// 修改数据
	@Override
	public void update(ServicePrice sp) {
		servicePriceMapper.update(sp);
	}

	// 新增数据
	@Override
	public void insert(ServicePrice sp) {
		servicePriceMapper.insert(sp);
	}

	// 根据serviceId查询价格列表
	@Override
	public List<ServicePrice> queryPriceByServiceId(Integer serviceId) {
		return servicePriceMapper.queryPriceByServiceId(serviceId);
	}

	// 逻辑删除
	@Override
	public int updateByCid(ServicePrice servicePrice) {
		return servicePriceMapper.updateByCid(servicePrice);
	}

	// 根据cid查询需要修改的数据
	@Override
	public ServicePrice queryByCid(Integer cid) {
		return servicePriceMapper.queryByCid(cid);
	}

}
