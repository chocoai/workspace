package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.AlarmDefine;
import com.yhcrt.iHealthCloud.entity.AlarmDefineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlarmDefineMapper {
    long countByExample(AlarmDefineExample example);

    int deleteByExample(AlarmDefineExample example);

    int deleteByPrimaryKey(String cid);

    int insert(AlarmDefine record);

    int insertSelective(AlarmDefine record);

    List<AlarmDefine> selectByExample(AlarmDefineExample example);

    AlarmDefine selectByPrimaryKey(String cid);
    
    AlarmDefine selectByAlarmType(String alarmType);

    int updateByExampleSelective(@Param("record") AlarmDefine record, @Param("example") AlarmDefineExample example);

    int updateByExample(@Param("record") AlarmDefine record, @Param("example") AlarmDefineExample example);

    int updateByPrimaryKeySelective(AlarmDefine record);

    int updateByPrimaryKey(AlarmDefine record);
}