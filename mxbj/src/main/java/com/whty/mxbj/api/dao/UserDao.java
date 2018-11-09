package com.whty.mxbj.api.dao;

import java.util.List;
import java.util.Map;

import com.whty.mxbj.api.model.User;
import com.whty.mxbj.api.model.UserConfig;
import com.whty.mxbj.api.model.UserPen;
import com.whty.mxbj.base.dao.IBaseDao;

public interface UserDao extends IBaseDao<User> {
	User findUserByPhoneNumber(String phoneNumber);

	UserPen findUserPenByUserIdAndPlatform(Map<String, Object> param);

	User loadUserByUserIdAndPlatform(Map<String, Object> param);

	void saveUserConfig(Map<String, Object> params);

	UserConfig getUserConfig(Map<String, Object> params);

	void updateUserConfig(Map<String, Object> params);

	List<Map<String, Object>> getSynCloudSchool(Map<String, Object> params);

	Integer getVerifyCodeCount(Map<String, Object> verifyCodeParams);

	void saveVerifyCodeLog(Map<String, Object> params);

}
