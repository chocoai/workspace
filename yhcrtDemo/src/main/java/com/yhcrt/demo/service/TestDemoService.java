package com.yhcrt.demo.service;

import com.yhcrt.demo.model.TestDemo;
import com.yhcrt.demo.util.QueryResult;



/**
 * @author fengkun
 * @email 231788364@qq.com
 */
public interface TestDemoService {
	
    int updateByPrimaryKeySelective(TestDemo record);

    int updateByPrimaryKey(TestDemo record);
    
    int insert(TestDemo record);

	QueryResult<TestDemo> doPaginationQuery(TestDemo testDemo);
	
	Long getCount(TestDemo testDemo);
	
	boolean deleteByPK(Long[] ids);

}
