package com.fxzhj.mapper;

import com.fxzhj.model.Help;

public interface HelpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Help record);

    int insertSelective(Help record);

    Help selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Help record);

    int updateByPrimaryKeyWithBLOBs(Help record);

    int updateByPrimaryKey(Help record);
}