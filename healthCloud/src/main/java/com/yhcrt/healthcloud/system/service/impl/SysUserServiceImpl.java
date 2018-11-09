package com.yhcrt.healthcloud.system.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.mapper.SysUserMapper;
import com.yhcrt.healthcloud.system.service.SysUserService;

/**
 * 
 * @author gongjun
 * 2017年5月15日
 * 版权所有：武汉炎黄创新科技服务有限公司
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
	
    @Autowired
    private SysUserMapper sysUserMapper;
    
	@Override
	public SysUser selectByUserCode(String userCode) {
	    SysUser user = sysUserMapper.selectByUserCode(userCode);
		return user;
	}
	
	@Override
	public int insert(SysUser record) {
		return sysUserMapper.insert(record);
	}

	@Override
	public int updateByUserCode(SysUser record) {
		return sysUserMapper.updateByUserCode(record); 
	}

	/* 
	 * 更新登录信息
     */
    @Override
    public Integer update(SysUser user) {
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /* 
     * 查询登录信息
     */
    @Override
    public SysUser selUser(Integer userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

	
}
