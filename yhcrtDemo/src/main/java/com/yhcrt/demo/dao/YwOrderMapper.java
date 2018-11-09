package com.yhcrt.demo.dao;

import com.yhcrt.demo.model.YwOrder;
import com.yhcrt.demo.model.YwOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YwOrderMapper {
    /**
     *
     * @mbg.generated 2018-03-22
     */
    long countByExample(YwOrderExample example);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    int deleteByExample(YwOrderExample example);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    int deleteByPrimaryKey(Integer orderId);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    int insert(YwOrder record);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    int insertSelective(YwOrder record);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    List<YwOrder> selectByExample(YwOrderExample example);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    YwOrder selectByPrimaryKey(Integer orderId);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    int updateByExampleSelective(@Param("record") YwOrder record, @Param("example") YwOrderExample example);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    int updateByExample(@Param("record") YwOrder record, @Param("example") YwOrderExample example);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    int updateByPrimaryKeySelective(YwOrder record);

    /**
     *
     * @mbg.generated 2018-03-22
     */
    int updateByPrimaryKey(YwOrder record);
}