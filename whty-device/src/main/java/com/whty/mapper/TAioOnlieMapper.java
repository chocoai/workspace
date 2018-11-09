package com.whty.mapper;

import com.whty.entity.TAioOnlie;
import com.whty.entity.TAioOnlieExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAioOnlieMapper {
    /**
     *
     * @mbggenerated 2018-07-07
     */
    int countByExample(TAioOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int deleteByExample(TAioOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int insert(TAioOnlie record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int insertSelective(TAioOnlie record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    List<TAioOnlie> selectByExample(TAioOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    TAioOnlie selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByExampleSelective(@Param("record") TAioOnlie record, @Param("example") TAioOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByExample(@Param("record") TAioOnlie record, @Param("example") TAioOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByPrimaryKeySelective(TAioOnlie record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByPrimaryKey(TAioOnlie record);
}