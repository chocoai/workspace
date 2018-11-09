package com.fxzhj.mapper;

import com.fxzhj.model.RecordDetail;

public interface RecordDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RecordDetail record);

    int insertSelective(RecordDetail record);

    RecordDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RecordDetail record);

    int updateByPrimaryKey(RecordDetail record);
}