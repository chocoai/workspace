package com.yhcrt.healthcloud.device.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.device.entity.DsSosLinkman;
import com.yhcrt.healthcloud.device.entity.DsSosLinkmanExample;
import com.yhcrt.healthcloud.device.entity.DsSosLinkmanExample.Criteria;
import com.yhcrt.healthcloud.device.mapper.DsSosLinkmanMapper;
import com.yhcrt.healthcloud.device.service.SosLinkmanService;
/**
 * 
 * @author rpf
 *
 */
@Service
public class SosLinkmanServiceImpl implements SosLinkmanService {
	
	@Autowired
	private DsSosLinkmanMapper dsSosLinkmanMapper;

	@Override
	public int insert(DsSosLinkman dsSosLinkman) {
		return dsSosLinkmanMapper.insert(dsSosLinkman);
	}

	@Override
	public int delete(Integer cId) {
		return dsSosLinkmanMapper.deleteByPrimaryKey(cId);
	}

	@Override
	public int update(DsSosLinkman dsSosLinkman) {
		return dsSosLinkmanMapper.updateByPrimaryKey(dsSosLinkman);
	}

	@Override
	public DsSosLinkman getLinkmanByCid(Integer cId) {
		return dsSosLinkmanMapper.selectByPrimaryKey(cId);
	}

	@Override
	public List<DsSosLinkman> listByArgs(String imei) {
		DsSosLinkmanExample example = new DsSosLinkmanExample();
		Criteria criteria = example.createCriteria();
		criteria.andImeiEqualTo(imei);
		return dsSosLinkmanMapper.selectByExample(example);
	}

}
