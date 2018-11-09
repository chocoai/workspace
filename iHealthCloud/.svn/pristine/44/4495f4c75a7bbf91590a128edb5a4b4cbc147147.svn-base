package com.yhcrt.iHealthCloud.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.SysDictionary;
import com.yhcrt.iHealthCloud.entity.SysDictionaryExample;

public interface SysDictionaryMapper {
    long countByExample(SysDictionaryExample example);

    int deleteByExample(SysDictionaryExample example);

    int deleteByPrimaryKey(Integer dictId);

    int insert(SysDictionary record);

    int insertSelective(SysDictionary record);

    List<SysDictionary> selectByExample(SysDictionaryExample example);

    SysDictionary selectByPrimaryKey(Integer dictId);

    int updateByExampleSelective(@Param("record") SysDictionary record, @Param("example") SysDictionaryExample example);

    int updateByExample(@Param("record") SysDictionary record, @Param("example") SysDictionaryExample example);

    int updateByPrimaryKeySelective(SysDictionary record);

    int updateByPrimaryKey(SysDictionary record);
    
    List<SysDictionary> getItems();

    //查询所有"org_type"的服务类型
	List<SysDictionary> getAllItems(String string);

	//获取字典中应用名称的key
	String getDictValue(String version_type);
	
	SysDictionary getByDictValue(String dictValue);
}