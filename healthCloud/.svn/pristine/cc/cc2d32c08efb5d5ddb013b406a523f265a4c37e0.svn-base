package com.yhcrt.healthcloud.health.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.health.entity.AlarmMsg;
import com.yhcrt.healthcloud.health.mapper.AlarmMsgMapper;
import com.yhcrt.healthcloud.health.service.AlarmMsgService;

@Service
public class AlarmMsgServiceImpl implements AlarmMsgService {
	
	@Autowired
	private AlarmMsgMapper alarmMsgMapper;

	//查询健康预警数据
	@Override
	public List<AlarmMsg> getAlarmMsgsByMap(Map<String, Object> map) {
		return alarmMsgMapper.getAlarmMsgsByMap(map);
	}


}
