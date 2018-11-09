package com.whty.assis.demo.service_ht.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.base.service.BaseService;
import com.whty.assis.demo.service_ht.ManageUserService;
import com.whty.assis.manage.dao.ManageUserDao;
import com.whty.assis.manage.model.ManageUserInfo;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/*
 * 后台管理员用户管理
 */
@Service
public class ManageUserServiceImpl extends BaseService implements ManageUserService {

	private static Logger logger = LoggerFactory.getLogger(ManageUserServiceImpl.class);

	@Autowired
	private ManageUserDao manageUserDao;

	@Override
	public ManageUserInfo queryManageUserInfoByAccount(String account) {
		return manageUserDao.loadByAccount(account);
	}

	@Override
	public void updateManageUser(ManageUserInfo mUser) {
		manageUserDao.updateManageUser(mUser);
	}

	@Override
	public void addManageUser(ManageUserInfo mUser) {
		manageUserDao.addManageUser(mUser);
	}

	@Override
	public HandlerResult queryManageUser(Map<String,Object> paramap) {
		logger.info("queryPage:" + paramap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(manageUserDao.queryManageUser(paramap));
		return rs;
	}
	
	@Override
	public HandlerResult queryManageUserPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(manageUserDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	@Override
	public void updateManageUserStatus(Map<String,Object> paramap) {
		manageUserDao.updateManageUserStatus(paramap);
	}

	@Override
	public void deleteManageUser(Map<String,Object> paramap) {
		manageUserDao.deleteManageUser(paramap);
	}

	@Override
	public List<ManageUserInfo> queryallUsers(Map<String,Object> paramap) {
		// TODO Auto-generated method stub
		List<ManageUserInfo> list = manageUserDao.queryManageUser(paramap);
		return list;
	}

	@Override
	public void addManageUserList(List list) {
		for (int i = 0; i < list.size(); i++) {
			ManageUserInfo info = (ManageUserInfo) list.get(i);
			manageUserDao.addManageUser(info);
		}

	}

	/* 
	 * @Title: 得到所有用户
	 * @return 
	 */ 
	@Override
	public List<ManageUserInfo> getUsers() {
		return manageUserDao.getUsers();
	}

}
