package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.AlarmMsg;
import com.yhcrt.iHealthCloud.entity.AlarmMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlarmMsgMapper {
    long countByExample(AlarmMsgExample example);

    int deleteByExample(AlarmMsgExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(AlarmMsg record);

    int insertSelective(AlarmMsg record);

    List<AlarmMsg> selectByExample(AlarmMsgExample example);
    
    List<AlarmMsg> getAlarmMsgsByMemberId(Integer memberId);

    AlarmMsg selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") AlarmMsg record, @Param("example") AlarmMsgExample example);

    int updateByExample(@Param("record") AlarmMsg record, @Param("example") AlarmMsgExample example);

    int updateByPrimaryKeySelective(AlarmMsg record);

    int updateByPrimaryKey(AlarmMsg record);
}