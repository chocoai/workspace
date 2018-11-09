package com.whty.assis.count.service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.whty.assis.count.model.TDataGatherCountLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * \* User: zjd
 * \* Date: 2018/6/20
 * \* Time: 8:57
 * \* Description:
 * \
 */
public interface TDataGatherCountLogService {

    //查询累计使用人数
    List<TDataGatherCountLog> selectByUseCount(HttpServletRequest request,Integer timeType );
    //设备开机数
    List<TDataGatherCountLog> selectByDevice(HttpServletRequest request,Integer timeType );
    //应用统计
    List<TDataGatherCountLog> selectBySoftware(HttpServletRequest request,Integer timeType );

    //教师使用排名
    List<TDataGatherCountLog> selectByTeacherRank(HttpServletRequest request,Integer timeType );
    //时长统计
    JSONArray selectByUseTaking(HttpServletRequest request, Integer timeType );
    //学科统计时长
    List<TDataGatherCountLog> selectBySubject(HttpServletRequest request,Integer timeType );

    //教师使用排名
    PageInfo<TDataGatherCountLog> selectByTeacher(HttpServletRequest request,String startTime,String endTime,Integer page);
    //教师Excel导出
    List<TDataGatherCountLog> selectByTeacherExcel(Map<String, Object> param);
    //设备统计页面
    PageInfo<TDataGatherCountLog> selectByDeviceUse(HttpServletRequest request,String startTime,String endTime,Integer page);


}