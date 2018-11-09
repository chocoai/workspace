package com.whty.ebp.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.base.service.BaseService;
import com.whty.ebp.sys.dao.SysUserDao;
import com.whty.ebp.sys.model.SysUser;
import com.whty.ebp.sys.service.SysUserService;
import com.whty.page.util.HandlerResult;
/*
 * 后台管理员用户管理
 */
@Service
public class SysUserServiceImpl extends BaseService implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public SysUser querySysUserByAccount(String account) {
		return sysUserDao.loadByAccount(account);
	}

	@Override
	public void updateSysUser(SysUser mUser) {
		sysUserDao.updateSysUser(mUser);
	}
	
	@Override
	public void addSysUser(SysUser mUser) {
		sysUserDao.addSysUser(mUser);
	}
	
	@Override
	public HandlerResult querySysUser(Map paramap) {
		logger.info("queryPage:"+paramap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(sysUserDao.querySysUser(paramap));
		return rs;
	}
	
	@Override
	public void updateSysUserStatus(Map paramap){
		sysUserDao.updateSysUserStatus(paramap);
	}
	
	@Override
	public void deleteSysUser(Map paramap){
		sysUserDao.deleteSysUser(paramap);
	}

	@Override
	public List<SysUser> queryallUsers(Map paramap) {
		// TODO Auto-generated method stub
		List<SysUser> list=sysUserDao.querySysUser(paramap);
		return list;
	}

	@Override
	public void addSysUserList(List list) {
		for(int i=0; i < list.size();i++){
			SysUser info = (SysUser) list.get(i);
			sysUserDao.addSysUser(info);
		}
		
	}


}
