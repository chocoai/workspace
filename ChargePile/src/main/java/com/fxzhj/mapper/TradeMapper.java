package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Trade;

public interface TradeMapper {

	//根据条件查询小区中充电交易流水
	List<Trade> queryByCId(Trade trade);

	//根据条件查询区域中充电交易流水
	List<Trade> queryByAId(Trade trade);
	
}