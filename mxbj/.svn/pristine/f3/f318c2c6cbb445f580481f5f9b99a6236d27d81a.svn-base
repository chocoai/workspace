package com.whty.mxbj.api.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.whty.mxbj.api.model.User;
import com.whty.mxbj.base.exception.BusinessException;

import net.rubyeye.xmemcached.exception.MemcachedException;

public interface UserService {

	Map<String, Object> login(Map<String, String> params);

	// Map<String, String> register(Map<String, String> params);

	Map<String, Object> modifyPwd(Map<String, String> params);

	Map<String, Object> modifyEmail(Map<String, String> params);

	Map<String, Object> userInfo(Map<String, String> params);

	Map<String, Object> getVerifyCode(Map<String, String> params)
			throws TimeoutException, InterruptedException, MemcachedException;

	Map<String, Object> register(Map<String, String> params) throws Exception;

	Map<String, Object> setUserConfigCheckParam(String body) throws IOException, BusinessException;

	void setUserConfig(Map<String, Object> params);

	Map<String, Object> getUserConfigCheckParam(String Object) throws IOException, BusinessException;

	Map<String, Object> getUserConfig(Map<String, Object> params);

	Map<String, Object> rePwd(Map<String, String> params);

	Map<String, Object> searchPhoneNumberCheckParam(String body) throws IOException, BusinessException;

	User searchPhoneNumber(Map<String, Object> params);

	Map<String, Object> getVerifyCode2(Map<String, String> params)
			throws TimeoutException, InterruptedException, MemcachedException;

	Map<String, Object> rePwd2(Map<String, String> params);

	Map<String, Object> login2(Map<String, String> params);

}
