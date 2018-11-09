package com.yhcrt.healthcloud.system.mapper;

import com.yhcrt.healthcloud.system.entity.SysDictionary;
import com.yhcrt.healthcloud.system.entity.SysDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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

    //根据dict_value查询dict_key
	String selectByDictValue(String dict_value);

	//根据dict_en_name查询
	List<SysDictionary> queryByDname(String dict_en_name);
}