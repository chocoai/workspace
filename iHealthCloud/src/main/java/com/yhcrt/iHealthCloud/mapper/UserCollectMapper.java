package com.yhcrt.iHealthCloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.UserCollect;
import com.yhcrt.iHealthCloud.entity.UserCollectExample;

public interface UserCollectMapper {
    long countByExample(UserCollectExample example);

    int deleteByExample(UserCollectExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(UserCollect record);

    int insertSelective(UserCollect record);

    List<UserCollect> selectByExample(UserCollectExample example);

    UserCollect selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") UserCollect record, @Param("example") UserCollectExample example);

    int updateByExample(@Param("record") UserCollect record, @Param("example") UserCollectExample example);

    int updateByPrimaryKeySelective(UserCollect record);

    int updateByPrimaryKey(UserCollect record);
}