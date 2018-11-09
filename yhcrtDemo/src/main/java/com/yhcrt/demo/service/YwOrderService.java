/**
 * 
 */
package com.yhcrt.demo.service;

import java.util.List;

import com.yhcrt.demo.model.YwOrder;
import com.yhcrt.demo.model.YwOrderExample;

/** 
 * @ClassName: YwOrderService 
 * @author: zjd
 * @date: 2018年3月22日 上午10:59:42  
 */
public interface YwOrderService {
	
	int updateByPrimaryKey(YwOrder record);
	
	YwOrder selectByPrimaryKey(Integer orderId);
	
	List<YwOrder> selectByExample(YwOrderExample example);
	
	int insert(YwOrder record);
	
	int deleteByPrimaryKey(Integer orderId);
}
