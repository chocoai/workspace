package com.whty.mapper;

import com.whty.entity.TEbookUseTaking;
import com.whty.entity.TEbookUseTakingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TEbookUseTakingMapper {
    /**
     *
     * @mbggenerated 2018-07-07
     */
    int countByExample(TEbookUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int deleteByExample(TEbookUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int insert(TEbookUseTaking record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int insertSelective(TEbookUseTaking record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    List<TEbookUseTaking> selectByExample(TEbookUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    TEbookUseTaking selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByExampleSelective(@Param("record") TEbookUseTaking record, @Param("example") TEbookUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByExample(@Param("record") TEbookUseTaking record, @Param("example") TEbookUseTakingExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByPrimaryKeySelective(TEbookUseTaking record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByPrimaryKey(TEbookUseTaking record);
}