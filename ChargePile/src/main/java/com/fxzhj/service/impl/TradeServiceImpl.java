package com.fxzhj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxzhj.mapper.TradeMapper;
import com.fxzhj.model.Trade;
import com.fxzhj.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeMapper TMapper;

	//根据条件查询充电交易流水
	@Override
	public List<Trade> queryTrade(Trade trade) {
		List<Trade> list = new ArrayList<Trade>();
		String deep = trade.getDeep();
		if("9".equals(deep)){
			//表示具体小区id
			list = TMapper.queryByCId(trade);
		}else if("1".equals(deep) || "2".equals(deep) ||"3".equals(deep)){
			//表示区域id
			list = TMapper.queryByAId(trade);
		}
		return list;
	}


}
