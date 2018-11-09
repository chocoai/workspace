package com.yhcrt.demo.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.demo.dao.TestDemoMapper;
import com.yhcrt.demo.model.TestDemo;
import com.yhcrt.demo.service.TestDemoService;
import com.yhcrt.demo.util.QueryResult;


/**
 * @author fengkun
 * @email 231788364@qq.com
 */
@Service
public class TestDemoServiceImpl implements TestDemoService {

	@Autowired
	private TestDemoMapper testDemoDao;
	
	

	@Override
	public int updateByPrimaryKeySelective(TestDemo record) {
		// TODO Auto-generated method stub
		return testDemoDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TestDemo record) {
		// TODO Auto-generated method stub
		return testDemoDao.updateByPrimaryKey(record);
	}

	@Override
	public int insert(TestDemo record) {
		// TODO Auto-generated method stub
		return testDemoDao.insert(record);
	}

	public QueryResult<TestDemo> doPaginationQuery(TestDemo testDemo){
		List<TestDemo> list = testDemoDao.doPaginationQuery(testDemo);
		QueryResult<TestDemo> result = new QueryResult<TestDemo>();
		result.setResultList(list);
		result.setTotalCount(list.size()+0l);
		return result;
	}

	@Override
	public Long getCount(TestDemo testDemo) {
		return testDemoDao.getCount(testDemo);
	}
	
	public boolean deleteByPK(Long[] ids){
		testDemoDao.deleteByPK(ids);
		return true;
	}
	
	
	

}
