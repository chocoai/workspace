package com.whty.assis.api.service.impl;

import com.whty.assis.api.dao.TUserBackgroundDao;
import com.whty.assis.api.dao.UserBackgroundPicDao;
import com.whty.assis.api.model.TUserBackground;
import com.whty.assis.api.model.UserBackgroundPic;
import com.whty.assis.api.service.TUserBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class TUserBackgroundServiceImpl implements TUserBackgroundService {

	@Autowired
	private TUserBackgroundDao tUserBackgroundDao;
	@Autowired
	private UserBackgroundPicDao userBackgroundPicDao;

	@Override
	public TUserBackground getListByTUserBackground(TUserBackground entity) {
		return tUserBackgroundDao.getListByTUserBackground(entity);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void insert(TUserBackground entity, UserBackgroundPic userBackgroundPic) {
		try {
			tUserBackgroundDao.insert(entity);
			userBackgroundPicDao.insert(userBackgroundPic);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String backgroundCode) {
		tUserBackgroundDao.delete(backgroundCode);
	}

	@Override
	public void insertObj(TUserBackground entity) {
		tUserBackgroundDao.insert(entity);
	}
}
