/**
 * 
 */
package com.whty.assis.sysres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.whty.assis.sysres.dao.TaManageUserInfoMapper;
import com.whty.assis.sysres.model.SysUserRole;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.assis.sysres.model.TaManageUserInfoExample;
import com.whty.assis.sysres.service.SysUserService;
import com.whty.common.util.GUIDGenerator;
import com.whty.assis.sysres.dao.SysUserRoleMapper;

/** 
 * @ClassName: SysModularServiceImpl 
 * @author: zjd
 * @date: 2018年6月2日 下午5:28:07  
 */
@Service
public class SysUserServiceImpl implements SysUserService{
	

	@Autowired
	private TaManageUserInfoMapper manageUserDao;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	/* 
	 * @Title: insert
	 * @return 
	 */ 
	@Override
	public int insert(TaManageUserInfo user) {
		int i = 0;
		manageUserDao.insert(user);
		TaManageUserInfoExample example = new TaManageUserInfoExample();
		example.createCriteria().andAccountEqualTo(user.getAccount());
		TaManageUserInfo user2 = manageUserDao.selectByExample(example).get(0);
		SysUserRole record = new SysUserRole();
		record.setId(GUIDGenerator.getGUID());
		record.setRoleId(user2.getRoleId());
		record.setUserId(user2.getId().toString());
		i=sysUserRoleMapper.insert(record);
		return i;
	}

	/* 
	 * @Title: delete
	 * @return 
	 */ 
	@Override
	public int delete(TaManageUserInfo user) {
		return manageUserDao.deleteByPrimaryKey(user.getId());
	}

	/* 
	 * @Title: update
	 * @return 
	 */ 
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public int update(TaManageUserInfo user) {
		int i = 0;
		try{
			TaManageUserInfo oldUser= manageUserDao.selectByPrimaryKey(user.getId());
			if(!oldUser.getRoleId().equals(user.getRoleId())){
				sysUserRoleMapper.updateByUserId(user.getRoleId(), user.getId().toString());
			}
			i=manageUserDao.updateByPrimaryKey(user);
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("出错了，要回滚");
			e.printStackTrace();
			i=0;
		}
		
		return i;
	}

	/* 
	 * @Title: selectUsers
	 * @return 
	 */ 
	@Override
	public List<TaManageUserInfo> selectUsers() {
		return manageUserDao.selectAlluser();
	}

	/* 
	 * @Title: selectByAccount
	 * @param account
	 * @return 
	 */ 
	@Override
	public TaManageUserInfo selectByAccount(String account) {
		return manageUserDao.selectByAccount(account);
	}

	/* 
	 * @Title: selectById
	 * @param account
	 * @return 
	 */ 
	@Override
	public TaManageUserInfo selectById(Integer id) {
		return manageUserDao.selectByPrimaryKey(id);
	}
}
