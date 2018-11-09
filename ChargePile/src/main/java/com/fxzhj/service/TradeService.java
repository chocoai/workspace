package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Trade;

public interface TradeService {

	//根据条件查询充电交易流水
	List<Trade> queryTrade(Trade trade);

}
