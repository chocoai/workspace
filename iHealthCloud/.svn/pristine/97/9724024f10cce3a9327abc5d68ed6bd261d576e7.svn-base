package com.yhcrt.iHealthCloud.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.iHealthCloud.entity.SysUser;
import com.yhcrt.iHealthCloud.mapper.SysUserMapper;
import com.yhcrt.iHealthCloud.service.SysUserService;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser selectByUserCode(String userCode) {
		return sysUserMapper.selectByUserCode(userCode);
	}

	@Override
	public int insert(SysUser record) {
		return sysUserMapper.insert(record);
	}

	@Override
	public int updateByUserCode(SysUser record) {
		return sysUserMapper.updateByUserCode(record);
	}

	@Override
	public Integer update(SysUser user) {
		return sysUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public SysUser getUserById(Integer userId) {
		return sysUserMapper.selectByPrimaryKey(userId);
	}

}
