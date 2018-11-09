package com.yhcrt.iHealthCloud.service;

import com.alibaba.fastjson.JSONObject;

public interface UserService {

	/**
	 * 注册用户获取验证码
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String getCaptcha(JSONObject pdataObj);
	
	public String getTelCode(JSONObject pdataObj);  //小程序获取验证码
	
	public String bindingTel(JSONObject pdataObj);  //小程序绑定手机

	/**
	 * 注册用户
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String register(JSONObject pdataObj);
	
	public String registerSmall(JSONObject pdataObj);

	/**
	 * 用户登录
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String login(JSONObject pdataObj);
	
	/**
	 * 获取验证码重置密码
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String getCaptchaRetrievePassword(JSONObject pdataObj);
	
	/**
	 * 校验验证码重置密码
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String checkCaptchaRetrievePassword(JSONObject pdataObj);
	
	/**
	 * 忘记密码重置密码
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String resetPassword(JSONObject pdataObj);
	
	/**
	 * 修改个人资料
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String updateProfile(JSONObject pdataObj);

	/**
	 * 修改密码
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String updatePassword(JSONObject pdataObj);
	
	/**
	 * 获取机构下员工或医师的个人资料
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String getUserProfile(JSONObject pdataObj);
	
	/**
	 * 更新机构下员工或医师的个人资料
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String updateUserProfile(JSONObject pdataObj);
	
	/**
	 *  第三方登录接口
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String thirdLogin(JSONObject pdataObj);
	
	/**
	 * 第三方登录获取验证码接口
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String thirdLoginCaptcha(JSONObject pdataObj);
	
	/**
	 * 第三方登录绑定手机号码接口
	 * 
	 * @param pdataObject
	 * @return json
	 */
	public String thirdLoginBindPhone(JSONObject pdataObj);

}
