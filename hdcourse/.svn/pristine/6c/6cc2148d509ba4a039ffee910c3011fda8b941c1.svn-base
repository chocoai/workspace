package com.whty.assis.api.service.impl;

import com.whty.assis.api.dao.UserBackgroundPicDao;
import com.whty.assis.api.model.UserBackgroundPic;
import com.whty.assis.api.service.UserBackgroundPicService;
import com.whty.common.util.BaiduBosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Transactional
public class UserBackgroundPicServiceImpl implements UserBackgroundPicService {

	@Autowired
	private UserBackgroundPicDao userBackgroundPicDao;

	@Override
	public void insert(UserBackgroundPic userBackgroundPic) {
		userBackgroundPicDao.insert(userBackgroundPic);
	}

	@Override
	public UserBackgroundPic selectByPrimaryKey(String resourceId) {
		return userBackgroundPicDao.selectByPrimaryKey(resourceId);
	}

	@Override
	public List<UserBackgroundPic> selectByUserBackgroundCode(String userBackgroundCode) {
		return userBackgroundPicDao.selectByUserBackgroundCode(userBackgroundCode);
	}

	@Override
	public void delete(String resourceId) {
		UserBackgroundPic userBackgroundPic = userBackgroundPicDao.selectByPrimaryKey(resourceId);
		userBackgroundPicDao.delete(resourceId);

		String reg = "http://whty.bj.bcebos.com";
		final String downUrl=userBackgroundPic.getDownUrl().replace(reg,"");
		final String thumbnail=userBackgroundPic.getThumbnail().replace(reg,"");
		new Thread(new Runnable() {
			@Override
			public void run() {
				BaiduBosUtils.deleteFile(downUrl);
				BaiduBosUtils.deleteFile(thumbnail);
			}
		}).start();
	}
}
