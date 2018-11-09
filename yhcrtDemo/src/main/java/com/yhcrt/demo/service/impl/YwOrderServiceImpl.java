/**
 * 
 */
package com.yhcrt.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.demo.dao.YwOrderMapper;
import com.yhcrt.demo.model.YwOrder;
import com.yhcrt.demo.model.YwOrderExample;
import com.yhcrt.demo.service.YwOrderService;

/** 
 * @ClassName: YwOrderServiceImpl 
 * @author: zjd
 * @date: 2018年3月22日 上午11:01:26  
 */
@Service
@Transactional
public class YwOrderServiceImpl implements YwOrderService{

	@Autowired
	private YwOrderMapper orderMapper;
	/* 
	 * @Title: updateByPrimaryKey
	 * @param record
	 * @return 
	 */ 
	@Override
	public int updateByPrimaryKey(YwOrder record) {
		return orderMapper.updateByPrimaryKey(record);
	}

	/* 
	 * @Title: selectByPrimaryKey
	 * @param orderId
	 * @return 
	 */ 
	@Override
	public YwOrder selectByPrimaryKey(Integer orderId) {
		return orderMapper.selectByPrimaryKey(orderId);
	}

	/* 
	 * @Title: selectByExample
	 * @param example
	 * @return 
	 */ 
	@Override
	public List<YwOrder> selectByExample(YwOrderExample example) {
		return orderMapper.selectByExample(example);
	}

	/* 
	 * @Title: insert
	 * @param record
	 * @return 
	 */ 
	@Override
	public int insert(YwOrder record) {
		return orderMapper.insert(record);
	}

	/* 
	 * @Title: deleteByPrimaryKey
	 * @param orderId
	 * @return 
	 */ 
	@Override
	public int deleteByPrimaryKey(Integer orderId) {
		return orderMapper.deleteByPrimaryKey(orderId);
	}

}
