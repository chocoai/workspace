package com.fxzhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxzhj.mapper.PriceMapper;
import com.fxzhj.model.Price;
import com.fxzhj.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceMapper PMapper;
	
	//批量新增
	@Override
	public void batchAdd(List<Price> list) {
		PMapper.batchAdd(list);
	}

	//根据节点查询
	@Override
	public List<Price> queryPrice(Price price) {
		return PMapper.queryPrice(price);
	}

	//根据节点删除
	@Override
	public void deletePrice(Price pri) {
		PMapper.deletePrice(pri);
	}

	//修改价格表中的状态
	@Override
	public void updateStatus(Price bean) {
		PMapper.updateStatus(bean);
	}


}
