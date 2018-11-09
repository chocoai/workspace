package com.yhcrt.healthcloud.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.healthcloud.system.entity.ResRole;
import com.yhcrt.healthcloud.system.entity.ResRoleExample;
import com.yhcrt.healthcloud.system.mapper.ResRoleMapper;
import com.yhcrt.healthcloud.system.service.ResRoleService;
import com.yhcrt.healthcloud.util.DateUtil;

@Service
@Transactional
public class ResRoleServiceImpl implements ResRoleService {
	@Autowired
	private ResRoleMapper resRoleMapper;

	@Override
	public void insert(String roleId, String[] resId) {
		this.deleteByRoleId(Integer.valueOf(roleId));
		for (int i = 0; i < resId.length; i++) {
			ResRole resRole = new ResRole();
			resRole.setRoleId(Integer.valueOf(roleId));
			resRole.setResId(Integer.valueOf(resId[i]));
			resRole.setCreateTime(DateUtil.getDateTime());
			resRoleMapper.insert(resRole);
		}
	}

	@Override
	public int deleteByRoleId(Integer roleId) {
		ResRoleExample example = new ResRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return resRoleMapper.deleteByExample(example);
	}

	@Override
	public List<ResRole> ListByRoleId(Integer roleId) {
		ResRoleExample example = new ResRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return resRoleMapper.selectByExample(example);
	}

}
