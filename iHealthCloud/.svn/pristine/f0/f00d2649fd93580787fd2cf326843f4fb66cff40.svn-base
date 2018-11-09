package com.yhcrt.iHealthCloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.AlarmMsgDetail;

public interface AlarmMsgDetailMapper {

	List<AlarmMsgDetail> selectByMemberId(Integer memberId);

	int deleteAll(@Param("memberId")Integer memberId,@Param("followId")Integer followId);
	
    int deleteByPrimaryKey(String cid);
    
    List<AlarmMsgDetail> getAlarmMsgsByMemberId(@Param("memberId")Integer memberId,@Param("followId")Integer followId);

    int insert(AlarmMsgDetail record);

}