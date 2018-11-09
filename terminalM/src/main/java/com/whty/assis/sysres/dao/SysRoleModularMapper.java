package com.whty.assis.sysres.dao;

import com.whty.assis.sysres.model.SysRoleModular;
import com.whty.assis.sysres.model.SysRoleModularExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleModularMapper {
    /**
     *
     * @mbg.generated 2018-06-02
     */
    long countByExample(SysRoleModularExample example);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int deleteByExample(SysRoleModularExample example);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int insert(SysRoleModular record);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int insertSelective(SysRoleModular record);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    List<SysRoleModular> selectByExample(SysRoleModularExample example);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int updateByExampleSelective(@Param("record") SysRoleModular record, @Param("example") SysRoleModularExample example);

    /**
     *
     * @mbg.generated 2018-06-02
     */
    int updateByExample(@Param("record") SysRoleModular record, @Param("example") SysRoleModularExample example);
    
    void insertRes(@Param("id")String id,@Param("roleId")String roleId,@Param("resIds")String[] resIds);
}