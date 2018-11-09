package com.yhcrt.demo.dao;

import com.yhcrt.demo.model.TestDemo;
import com.yhcrt.demo.model.TestDemoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TestDemoMapper {
    long countByExample(TestDemoExample example);

    int deleteByExample(TestDemoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TestDemo record);

    int insertSelective(TestDemo record);

    List<TestDemo> selectByExample(TestDemoExample example);

    TestDemo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TestDemo record, @Param("example") TestDemoExample example);

    int updateByExample(@Param("record") TestDemo record, @Param("example") TestDemoExample example);

    int updateByPrimaryKeySelective(TestDemo record);

    int updateByPrimaryKey(TestDemo record);
    
    //user add
	public List<TestDemo> doPaginationQuery(TestDemo record);
	
	public Long getCount(TestDemo record);
	
	public void deleteByPK(@Param("ids") Long[] ids);
}