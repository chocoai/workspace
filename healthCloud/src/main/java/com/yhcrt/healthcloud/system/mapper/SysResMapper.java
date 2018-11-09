package com.yhcrt.healthcloud.system.mapper;

import com.yhcrt.healthcloud.system.entity.SysRes;
import com.yhcrt.healthcloud.system.entity.SysResExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysResMapper {
    long countByExample(SysResExample example);

    int deleteByExample(SysResExample example);

    int deleteByPrimaryKey(Integer resId);

    int insert(SysRes record);

    int insertSelective(SysRes record);

    List<SysRes> selectByExample(SysResExample example);
    
    List<SysRes> getChildResByParentId(String parentId);
    
    String getLastResCodeByParentId(String parentId);

    SysRes selectByPrimaryKey(Integer resId);
    
    SysRes getSysResRootNode();
    
    List<String> findPermissionsByUserId(String userId);

    int updateByExampleSelective(@Param("record") SysRes record, @Param("example") SysResExample example);

    int updateByExample(@Param("record") SysRes record, @Param("example") SysResExample example);

    int updateByPrimaryKeySelective(SysRes record);

    int updateByPrimaryKey(SysRes record);
}