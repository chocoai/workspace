/**
 * 
 */
package com.whty.assis.sysres.service.impl;

import java.util.List;

import com.whty.assis.sysres.dao.SysRoleMapper;
import com.whty.assis.sysres.model.SysRole;
import com.whty.assis.sysres.model.SysRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.sysres.service.SysRoleService;
import com.whty.common.util.Constants;

/** 
 * @ClassName: SysModularServiceImpl 
 * @author: zjd
 * @date: 2018年6月2日 下午5:28:07  
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{

	@Autowired
	private SysRoleMapper sysRoleMapper;

	/*
	 * @Title: insert
	 * @return 
	 */ 
	@Override
	public int insert(SysRole sysRole) {
		return sysRoleMapper.insert(sysRole);
	}

	/* 
	 * @Title: delete
	 * @return 
	 */ 
	@Override
	public int delete(Integer id) {
		SysRoleExample example = new SysRoleExample();
		example.createCriteria().andIdEqualTo(id);
		return sysRoleMapper.deleteByExample(example);
	}

	/* 
	 * @Title: update
	 * @return 
	 */ 
	@Override
	public int update(SysRole sysRole) {
		return sysRoleMapper.updateByPrimaryKey(sysRole);
	}

	/* 
	 * @Title: selectRoles
	 * @return 
	 */ 
	@Override
	public List<SysRole> selectRoles() {
		SysRoleExample example = new SysRoleExample();
		example.createCriteria().andStatusEqualTo(Constants.SYS_STATUS_NORMAL);
		return sysRoleMapper.selectByExample(example);
	}
	
	

}
