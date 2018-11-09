package com.yhcrt.iHealthCloud.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.YwImage;
import com.yhcrt.iHealthCloud.entity.YwImageExample;

public interface YwImageMapper {
    long countByExample(YwImageExample example);

    int deleteByExample(YwImageExample example);

    int deleteByPrimaryKey(Integer imgId);

    int insert(YwImage record);

    int insertSelective(YwImage record);

    List<YwImage> selectByExample(YwImageExample example);

    YwImage selectByPrimaryKey(Integer imgId);

    int updateByExampleSelective(@Param("record") YwImage record, @Param("example") YwImageExample example);

    int updateByExample(@Param("record") YwImage record, @Param("example") YwImageExample example);

    int updateByPrimaryKeySelective(YwImage record);

    int updateByPrimaryKey(YwImage record);
}