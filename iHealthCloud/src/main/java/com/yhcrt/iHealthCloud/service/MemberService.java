package com.yhcrt.iHealthCloud.service;

import com.alibaba.fastjson.JSONObject;

public interface MemberService {
	
	/**
	 * 获取APP首页模块的数据
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getIndexData(JSONObject pdataObj);
	
	/**
	 * 我的关注(查询APP关注模块数据)
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getMyAttention(JSONObject pdataObj);

	/**
	 * 查询我关注的人
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getMyFollowing(JSONObject pdataObj);
	
	public String getMyFollowingForSmall(JSONObject pdataObj);
	
	//小程序健康数据  血糖 血压 心率 步数 睡眠 五项值
	public String getHealthData(JSONObject pdataObj);

	/**
	 * 查询关注我的人
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getMyFollowers(JSONObject pdataObj);
	
	//获取积分 点赞 关注 ---手表屏幕显示
	public String getFollowAndScore(JSONObject pdataObj);

	/**
	 * 查询我的机构服务人员
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getMyOrgServicer(JSONObject pdataObj);

	/**
	 * 添加关注人接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String toFollow(JSONObject pdataObj);

	/**
	 * 取消关注接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String unFollow(JSONObject pdataObj);

	/**
	 * 关注人员详情接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String followerDetail(JSONObject pdataObj);

	/**
	 * 绑定终端设备
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String bindDevice(JSONObject pdataObj);
	
	/**
	 * 启用终端设备
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String startDevice(JSONObject pdataObj);

	/**
	 * 解绑终端设备
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String unBindDevice(JSONObject pdataObj);
	
	/**
	 * 设置默认终端设备
	 * @param jsonObj
	 * @return
	 */
	public String setDefaultDevice(JSONObject jsonObj);

	/**
	 * 我的终端设备列表接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getMyDevices(JSONObject pdataObj);

	/**
	 * 获取我的终端设备详情接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getDeviceDetail(JSONObject pdataObj);

	/**
	 * 获取我的终端设备详情接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String setLinkman(JSONObject pdataObj);
	
	//联系人修改
	public String modifyLinkman(JSONObject pdataObj);
	
	//联系人删除
	public String delLinkman(JSONObject pdataObj);
	
	/**
	 * 终端设置查询联系人接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getLinkman(JSONObject pdataObj);

	/**
	 * 获取我的终端设备详情接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String setRemind(JSONObject pdataObj);

	/**
	 * 终端设置查询提醒设置接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getRemindSet(JSONObject pdataObj);
	
	/**
	 * 终端设置心率安全范围设置接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String setHeart(JSONObject pdataObj);
	
	/**
	 * 终端设置查询心率安全范围设置接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getHeartSet(JSONObject pdataObj);

	/**
	 * 终端设置安全定位设置接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String setSecureLocation(JSONObject pdataObj);
	
	/**
	 * 终端设置查询安全定位设置接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getSecureLocation(JSONObject pdataObj);

	/**
	 * 获取我的终端设备详情接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getLoction(JSONObject pdataObj);

	/**
	 * 获取我的终端设备详情接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getLoctionHis(JSONObject pdataObj);

	/**
	 * 获取终端用户列表接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getDeviceMembers(JSONObject pdataObj);

	public String getAppMembers(JSONObject pdataObj);

	public String getMemberProfile(JSONObject pdataObj);

	public String getMemberImeis(JSONObject pdataObj);

	public String searchDeviceMembers(JSONObject pdataObj);

	/**
	 * 获会员端 查询运动目标设置
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getStepSetting(JSONObject pdataObj);

	/**
	 * 会员端 设置运动目标
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String updateStepSetting(JSONObject pdataObj);

	/**
	 * 会员端 查询血压安全范围设置
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getBloodPressureSetting(JSONObject pdataObj);

	/**
	 * 会员端 设置血压安全范围
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String updateBloodPressureSetting(JSONObject pdataObj);
	
	/**
	 * 终端设备定位数据上传接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String uploadLocation(JSONObject pdataObj);
	
	/**
	 * 获取预警消息列表接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String getAlarmMsgs(JSONObject pdataObj);
	
	/**
	 * 清除预警消息接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String clearAlarmMsg(JSONObject pdataObj);
	
	/**
	 * 清除所有预警消息接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String clearAllAlarmMsg(JSONObject pdataObj);
	
	
	String getInfoByTel(JSONObject pdataObj);
	
	/**
	 * 小程序添加关注人接口
	 * 
	 * @param pdataObj
	 * @return jsonString
	 */
	public String toFollowByTel(JSONObject pdataObj);
	
	/**
	 * 获取用户收货地址信息列表接口
	 * @param pdataObj
	 * @return
	 */
	public String getMemberAddress(JSONObject pdataObj);
	
	/**
	 * 小程序获取收货地址id
	 * @param pdataObj
	 * @return
	 */
	public String getAddressId(JSONObject pdataObj);
	
	/**
	 * 新增收货地址接口
	 * @param pdataObj
	 * @return
	 */
	public String addMemberAddress(JSONObject pdataObj);
	
	/**
	 * 编辑收货地址接口
	 * @param pdataObj
	 * @return
	 */
	public String updateMemberAddress(JSONObject pdataObj);
	
	/**
	 * 编辑收货地址接口
	 * @param pdataObj
	 * @return
	 */
	public String deleteMemberAddress(JSONObject pdataObj);
	
	/**
	 * 设置默认收货地址接口
	 * @param pdataObj
	 * @return
	 */
	public String setDefaultAddress(JSONObject pdataObj);

	/**
	 * 根据用户id查询默认收货地址接口
	 * @param pdataObj
	 * @return
	 */
	public String getMemberDefaultAddress(JSONObject pdataObj);
	
}