package com.yhcrt.healthcloud.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.system.entity.SysVersion;
import com.yhcrt.healthcloud.system.mapper.SysVersionMapper;
import com.yhcrt.healthcloud.system.service.SysVersionService;


@Service
public class SysVersionServiceImpl implements SysVersionService {

	@Autowired
	private SysVersionMapper sysVersionMapper;

	//根据条件查询所有的版本信息
	@Override
	public List<SysVersion> queryAll(HashMap<String, Object> params) {
		return sysVersionMapper.queryAll(params);
	}

	//根据kid查询版本信息
	@Override
	public SysVersion queryById(String kid) {
		return sysVersionMapper.queryById(kid);
	}

	//修改版本信息
	@Override
	public Integer update(SysVersion version) {
		Integer count = sysVersionMapper.countBySysVersion(version);
		if(count > 0){
			return -1;
		}
		return sysVersionMapper.update(version);
	}

	//新增版本信息
	@Override
	public Integer insert(SysVersion version) {
		//新增判断重复
		Integer count = sysVersionMapper.countBySysVersion(version);
		if(count > 0){
			return -1;
		}
		return sysVersionMapper.insert(version);
	}

	//查询版本存放路径
	@Override
	public String queryUrl(String kid) {
		return sysVersionMapper.queryUrl(kid);
	}

	//批量删除版本信息
	@Override
	public void batchDel(List<String> list) {
		sysVersionMapper.batchDel(list);
	}

}
