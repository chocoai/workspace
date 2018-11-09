package com.whty.assis.sysres.dao;

import com.whty.assis.sysres.model.SysUserRole;
import com.whty.assis.sysres.model.SysUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
    /**
     *
     * @mbg.generated 2018-06-02
     */
    long countByExample(SysUserRoleExample example);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int deleteByExample(SysUserRoleExample example);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int insert(SysUserRole record);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int insertSelective(SysUserRole record);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    List<SysUserRole> selectByExample(SysUserRoleExample example);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);
    
    void updateByUserId(@Param("roleId")String roleId,@Param("userId")String userId);
}