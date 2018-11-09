package com.whty.mapper;

import com.whty.entity.TEbookOnlie;
import com.whty.entity.TEbookOnlieExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TEbookOnlieMapper {
    /**
     *
     * @mbggenerated 2018-07-07
     */
    int countByExample(TEbookOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int deleteByExample(TEbookOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int insert(TEbookOnlie record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int insertSelective(TEbookOnlie record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    List<TEbookOnlie> selectByExample(TEbookOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    TEbookOnlie selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByExampleSelective(@Param("record") TEbookOnlie record, @Param("example") TEbookOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByExample(@Param("record") TEbookOnlie record, @Param("example") TEbookOnlieExample example);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByPrimaryKeySelective(TEbookOnlie record);

    /**
     *
     * @mbggenerated 2018-07-07
     */
    int updateByPrimaryKey(TEbookOnlie record);
}