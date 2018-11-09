package com.yhcrt.iHealthCloud.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.entity.ServicePrice;
import com.yhcrt.iHealthCloud.entity.SysDictionary;
import com.yhcrt.iHealthCloud.mapper.ServicePriceMapper;
import com.yhcrt.iHealthCloud.mapper.SysDictionaryMapper;
import com.yhcrt.iHealthCloud.service.ServicePriceService;

/**
 * @author 陈伟
 * 2018年1月18日 下午2:19:09
 */
@org.springframework.stereotype.Service
public class ServicePriceServiceImpl extends BaseService implements ServicePriceService {
	
	@Autowired
	private SysDictionaryMapper sysDictMappper;
	@Autowired
	private ServicePriceMapper servicePriceMapper;
	
	@Override
	public ServicePrice findById(Integer cid) {
		ServicePrice price = servicePriceMapper.selectByPrimaryKey(cid);
		if(!StringUtils.isBlank(price.getUnit())){
			SysDictionary  dict = sysDictMappper.getByDictValue(price.getUnit());
			if(dict!=null){
				price.setUnit(dict.getDictKey());
			}
		}
		return price;
	}

}