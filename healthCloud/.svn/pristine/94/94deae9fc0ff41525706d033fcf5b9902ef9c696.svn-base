/**
 * @Title:   SysRoleServiceImpl.java 
 * @Package: com.yhcrt.healthcloud.system.service.impl  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月26日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.entity.SysRole;
import com.yhcrt.healthcloud.system.entity.SysRoleExample;
import com.yhcrt.healthcloud.system.mapper.SysRoleMapper;
import com.yhcrt.healthcloud.system.service.SysRoleService;

/**
 * @ClassName: SysRoleServiceImpl
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2017年5月26日 
 */

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    
	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysRoleService#insert(com.yhcrt.healthcloud.system.entity.SysRole)
	 */
	@Override
	public int insert(SysRole sysRole) {
		return sysRoleMapper.insert(sysRole);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysRoleService#deleteByRoleId(java.lang.Integer)
	 */
	@Override
	public int deleteByRoleId(Integer roleId) {
		return sysRoleMapper.deleteByPrimaryKey(roleId);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysRoleService#updateByRoleId(com.yhcrt.healthcloud.system.entity.SysRole)
	 */
	@Override
	public int updateByRoleId(SysRole sysRole) {
		return sysRoleMapper.updateByPrimaryKey(sysRole);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysRoleService#listAllSysRole()
	 */
	@Override
	public List<SysRole> listAllSysRole() {
		SysRoleExample example = new SysRoleExample();
		example.createCriteria().andStatusEqualTo(Constants.STATUS_NORMAL);
		example.setOrderByClause("order_num asc");
		return sysRoleMapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysRoleService#getSysRoleByRoleId(java.lang.Integer)
	 */
	@Override
	public SysRole getSysRoleByRoleId(Integer roleId) {
		return sysRoleMapper.selectByPrimaryKey(roleId);
	}

	//查询其角色查询其能分配的角色
	@Override
	public List<SysRole> queryByList(List<Integer> list) {
		return sysRoleMapper.queryByList(list);
	}

}
