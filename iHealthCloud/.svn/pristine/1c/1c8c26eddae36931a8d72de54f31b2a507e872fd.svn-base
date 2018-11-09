package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.HrOperation;
import com.yhcrt.iHealthCloud.entity.HrOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrOperationMapper {
    long countByExample(HrOperationExample example);

    int deleteByExample(HrOperationExample example);

    int deleteByPrimaryKey(Integer cId);

    int insert(HrOperation record);

    int insertSelective(HrOperation record);
    
    List<HrOperation> listByRecordId(Integer recordId);

    List<HrOperation> selectByExample(HrOperationExample example);

    HrOperation selectByPrimaryKey(Integer cId);

    int updateByExampleSelective(@Param("record") HrOperation record, @Param("example") HrOperationExample example);

    int updateByExample(@Param("record") HrOperation record, @Param("example") HrOperationExample example);

    int updateByPrimaryKeySelective(HrOperation record);

    int updateByPrimaryKey(HrOperation record);
}