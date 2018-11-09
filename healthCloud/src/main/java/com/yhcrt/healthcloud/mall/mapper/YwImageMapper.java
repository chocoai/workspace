package com.yhcrt.healthcloud.mall.mapper;

import com.yhcrt.healthcloud.mall.entity.YwImage;
import com.yhcrt.healthcloud.mall.entity.YwImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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