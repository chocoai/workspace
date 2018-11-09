package com.fxzhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxzhj.mapper.NodeMapper;
import com.fxzhj.mapper.PortMapper;
import com.fxzhj.model.Port;
import com.fxzhj.service.PortService;

import tk.mybatis.mapper.util.StringUtil;

@Service
public class PortServiceImpl implements PortService {

	@Autowired
	private PortMapper PMapper;
	
	@Autowired
	private NodeMapper NMapper;

	//根据设备id查询端口信息
	@Override
	public List<Port> queryPort(String deviceId) {
		if(StringUtil.isNotEmpty(deviceId)){
			return PMapper.queryPort(Long.parseLong(deviceId));
		}
		return null;
	}

	//根据id修改状态
	@Override
	@Transactional
	public void updatePort(Long id, Integer status) {
		 PMapper.updatePort(id,status);
		//根据端口id修改节点信息
		NMapper.updateByPid(id,status);
	}

}
