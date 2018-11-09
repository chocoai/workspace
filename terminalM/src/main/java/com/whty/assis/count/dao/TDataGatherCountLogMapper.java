package com.whty.assis.count.dao;

import com.whty.assis.count.model.TDataGatherCountLog;
import com.whty.assis.count.model.TDataGatherCountLogExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TDataGatherCountLogMapper {
    /**
     *
     * @mbg.generated 2018-06-20
     */
    long countByExample(TDataGatherCountLogExample example);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    int deleteByExample(TDataGatherCountLogExample example);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    int insert(TDataGatherCountLog record);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    int insertSelective(TDataGatherCountLog record);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    List<TDataGatherCountLog> selectByExample(TDataGatherCountLogExample example);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    TDataGatherCountLog selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    int updateByExampleSelective(@Param("record") TDataGatherCountLog record, @Param("example") TDataGatherCountLogExample example);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    int updateByExample(@Param("record") TDataGatherCountLog record, @Param("example") TDataGatherCountLogExample example);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    int updateByPrimaryKeySelective(TDataGatherCountLog record);

    /**
     *
     * @mbg.generated 2018-06-20
     */
    int updateByPrimaryKey(TDataGatherCountLog record);

    //查询累计使用人数
    List<TDataGatherCountLog> selectByUseCount(Map<String, Object> param);

    //设备开机数
    List<TDataGatherCountLog> selectByDevice(Map<String, Object> param);

    //应用统计
    List<TDataGatherCountLog> selectBySoftware(Map<String, Object> param);

    //教师使用排名
    List<TDataGatherCountLog> selectByTeacher(Map<String, Object> param);

    //时长统计
    List<TDataGatherCountLog> selectByUseTaking(Map<String, Object> param);

    //学科统计时长
    List<TDataGatherCountLog> selectBySubject(Map<String, Object> param);

    //设备使用统计
    List<TDataGatherCountLog> selectByDeviceUse(Map<String, Object> param);

}