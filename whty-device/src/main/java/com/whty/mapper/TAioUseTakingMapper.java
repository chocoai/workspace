package com.whty.mapper;

import com.whty.entity.TAioUseTaking;
import com.whty.entity.TAioUseTakingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAioUseTakingMapper {
    /**
     *
     * @mbggenerated 2018-07-07
     */
    int countByExample(TAioUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int deleteByExample(TAioUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int insert(TAioUseTaking record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int insertSelective(TAioUseTaking record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    List<TAioUseTaking> selectByExample(TAioUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    TAioUseTaking selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByExampleSelective(@Param("record") TAioUseTaking record, @Param("example") TAioUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByExample(@Param("record") TAioUseTaking record, @Param("example") TAioUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByPrimaryKeySelective(TAioUseTaking record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByPrimaryKey(TAioUseTaking record);
}