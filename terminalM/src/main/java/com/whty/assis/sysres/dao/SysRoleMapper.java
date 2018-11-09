package com.whty.assis.sysres.dao;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.sysres.model.SysRole;
import com.whty.assis.sysres.model.SysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper extends IBaseDao<SysRole>{
    /**
     *
     * @mbg.generated 2018-06-22
     */
    long countByExample(SysRoleExample example);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    int deleteByExample(SysRoleExample example);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    int insert(SysRole record);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    int insertSelective(SysRole record);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    List<SysRole> selectByExample(SysRoleExample example);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    SysRole selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     *
     * @mbg.generated 2018-06-22
     */
    int updateByPrimaryKey(SysRole record);
}