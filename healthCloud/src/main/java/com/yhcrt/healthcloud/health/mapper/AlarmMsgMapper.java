package com.yhcrt.healthcloud.health.mapper;

import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.health.entity.AlarmMsg;

public interface AlarmMsgMapper {

	//查询健康预警数据
	List<AlarmMsg> getAlarmMsgsByMap(Map<String, Object> map);
}