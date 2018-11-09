package com.yhcrt.demo.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.demo.dao.SysUserDao;
import com.yhcrt.demo.model.Authority;
import com.yhcrt.demo.model.SysUser;
import com.yhcrt.demo.service.SysUserService;
import com.yhcrt.demo.util.QueryResult;


/**
 * @author fengkun
 * @email 231788364@qq.com
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public List<SysUser> getSysUserList(List<SysUser> resultList) {
		List<SysUser> sysUserList = new ArrayList<SysUser>();
		for (SysUser entity : resultList) {
			SysUser sysUser = new SysUser();
			sysUser.setId(entity.getId());
			sysUser.setUserName(entity.getUserName());
			sysUser.setPassword(entity.getPassword());
			sysUser.setRealName(entity.getRealName());
			sysUser.setTel(entity.getTel());
			sysUser.setEmail(entity.getEmail());
			sysUser.setLastLoginTime(entity.getLastLoginTime());
			sysUser.setRole(entity.getRole());
	//		sysUser.setRoleName(SystemCache.DICTIONARY.get("SYSUSER_ROLE").getItems().get(String.valueOf(entity.getRole())).getValue());
			sysUserList.add(sysUser);
		}
		return sysUserList;
	}
	
	public SysUser getByUserName(String userName){
		return sysUserDao.getByUserName(userName);
	}
	
	public void update(SysUser sysUser){
		sysUserDao.update(sysUser);
	}
	
	public void updateBySelected(SysUser sysUser){
		sysUserDao.updateBySelected(sysUser);
	}
	
	public SysUser getByUserNameAndPassword(String userName,String password){
		return sysUserDao.getByUserNameAndPassword(userName,password);
	}
	
	public void insert(SysUser sysUser){
		sysUserDao.insert(sysUser);
	}
	
	public QueryResult<SysUser> doPaginationQuery(SysUser sysUser){
		List<SysUser> list = sysUserDao.doPaginationQuery(sysUser);
		QueryResult<SysUser> result = new QueryResult<SysUser>();
		result.setResultList(list);
		result.setTotalCount(list.size()+0l);
		return result;
	}
	
	public boolean deleteByPK(Long[] ids){
		 sysUserDao.deleteByPK(ids);
		 return true;
	}

}
