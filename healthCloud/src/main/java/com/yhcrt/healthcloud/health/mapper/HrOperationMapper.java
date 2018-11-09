package com.yhcrt.healthcloud.health.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.health.entity.HrOperation;
import com.yhcrt.healthcloud.health.entity.HrOperationExample;

public interface HrOperationMapper {

	long countByExample(HrOperationExample example);

	int deleteByExample(HrOperationExample example);

	int deleteByPrimaryKey(Integer cId);

	int insert(HrOperation record);

	int insertSelective(HrOperation record);

	List<HrOperation> selectByExample(HrOperationExample example);

	List<HrOperation> selectByRecordId(Integer recordId);

	HrOperation selectByPrimaryKey(Integer cId);

	int updateByExampleSelective(@Param("record") HrOperation record, @Param("example") HrOperationExample example);

	int updateByExample(@Param("record") HrOperation record, @Param("example") HrOperationExample example);

	int updateByPrimaryKeySelective(HrOperation record);

	int updateByPrimaryKey(HrOperation record);
}