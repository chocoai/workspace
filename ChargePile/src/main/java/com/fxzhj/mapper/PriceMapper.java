package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Price;

public interface PriceMapper {
    //批量新增
	void batchAdd(List<Price> list);

	//根据节点查询
	List<Price> queryPrice(Price price);

	//根据节点删除
	void deletePrice(Price pri);

	//修改价格表中的状态
	void updateStatus(Price bean);
	
}