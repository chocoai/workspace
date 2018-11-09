package com.whty.assis.demo.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.service.TaUserService;
import com.whty.assis.manage.dao.TaUserDao;
import com.whty.assis.manage.model.Ta_user;
import com.whty.page.util.HandlerResult;

@Service
public class TaUserServiceImpl implements TaUserService {

	private static Logger log = LoggerFactory.getLogger(TaUserServiceImpl.class);
	@Autowired
	private TaUserDao taUserDao;

	@SuppressWarnings("rawtypes")
	@Override
	public Ta_user getUserByParam(Map map) {
		log.info("根据id查询用户service，map：" + map);
		return taUserDao.getUserByParam(map);
	}

	@Override
	public void updateUser(Ta_user ta_user) {
		log.info("修改用户信息service，ta_user：" + ta_user);
		taUserDao.updateUser(ta_user);
	}

	@Override
	public void addUser(Ta_user ta_user) {
		log.info("新增用户service，ta_user：" + ta_user);
		taUserDao.addUser(ta_user);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteUser(Map map) {
		log.info("删除用户service，map：" + map);
		taUserDao.deleteUser(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public HandlerResult queryUser(Map map) {
		log.info("查询用户service，map：" + map);
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(taUserDao.queryUser(map));
		return handlerResult;
	}

	@Override
	public void updateUserStatus(Map<String, Object> map) {
		taUserDao.updateUserStatus(map);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public HandlerResult getUserList(Map map) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(taUserDao.getUserList(map));
		return handlerResult;
	}

}
