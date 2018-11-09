package com.whty.assis.sysres.dao;

import com.whty.assis.sysres.model.SysModular;
import com.whty.assis.sysres.model.SysModularExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysModularMapper {
    /**
     *
     * @mbg.generated 2018-06-04
     */
    long countByExample(SysModularExample example);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    int deleteByExample(SysModularExample example);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    int insert(SysModular record);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    int insertSelective(SysModular record);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    List<SysModular> selectByExample(SysModularExample example);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    SysModular selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    int updateByExampleSelective(@Param("record") SysModular record, @Param("example") SysModularExample example);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    int updateByExample(@Param("record") SysModular record, @Param("example") SysModularExample example);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    int updateByPrimaryKeySelective(SysModular record);

    /**
     *
     * @mbg.generated 2018-06-04
     */
    int updateByPrimaryKey(SysModular record);
    
    List<SysModular> getChildrenModular(SysModular record);
    
    List<SysModular> listAllSysModular(Integer userId);
}