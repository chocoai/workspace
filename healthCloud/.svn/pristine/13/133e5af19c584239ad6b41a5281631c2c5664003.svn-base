package com.yhcrt.healthcloud.system.mapper;

import com.yhcrt.healthcloud.system.entity.ResRole;
import com.yhcrt.healthcloud.system.entity.ResRoleExample;
import com.yhcrt.healthcloud.system.entity.ResRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResRoleMapper {
    long countByExample(ResRoleExample example);

    int deleteByExample(ResRoleExample example);

    int deleteByPrimaryKey(ResRoleKey key);

    int insert(ResRole record);

    int insertSelective(ResRole record);

    List<ResRole> selectByExample(ResRoleExample example);

    ResRole selectByPrimaryKey(ResRoleKey key);

    int updateByExampleSelective(@Param("record") ResRole record, @Param("example") ResRoleExample example);

    int updateByExample(@Param("record") ResRole record, @Param("example") ResRoleExample example);

    int updateByPrimaryKeySelective(ResRole record);

    int updateByPrimaryKey(ResRole record);
}