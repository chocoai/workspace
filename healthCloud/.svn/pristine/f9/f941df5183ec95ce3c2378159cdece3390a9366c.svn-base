package com.yhcrt.healthcloud.mall.mapper;

import java.util.List;

import com.yhcrt.healthcloud.mall.entity.ServicePrice;
import com.yhcrt.healthcloud.mall.entity.ServicePriceExample;

public interface ServicePriceMapper {

	List<ServicePrice> selectByExample(ServicePriceExample example);

	// 根据cid查询数据
	ServicePrice queryByServicePrice(ServicePrice servicePrice);

	// 修改数据
	void update(ServicePrice sp);

	// 新增数据
	void insert(ServicePrice sp);

	// 根据serviceId查询价格列表
	List<ServicePrice> queryPriceByServiceId(Integer serviceId);

	// 逻辑删除
	int updateByCid(ServicePrice servicePrice);

	// 根据cid查询需要修改的数据
	ServicePrice queryByCid(Integer cid);

}