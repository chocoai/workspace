package com.yhcrt.healthcloud.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.system.entity.SysRole;
import com.yhcrt.healthcloud.system.entity.SysRoleExample;

public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    //查询其角色查询其能分配的角色
	List<SysRole> queryByList(List<Integer> list);

}