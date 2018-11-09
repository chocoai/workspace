package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.UserCollect;

/**
 * @Description: 
 * @version 1.0 2017年9月7日
 * @author jimmy
 */
public interface UserCollectService {

	Integer add(UserCollect userCollect);

	Integer del(Integer cid);

	Integer upd(UserCollect userCollect);

	UserCollect get(Integer cid);

	List<UserCollect> getAll();
	
	/**
	 * 收藏服务
	 * @param jsonObject
	 * @return
	 */
	public String collectService(JSONObject jsonObject);
	
	/**
	 * 收藏商品接口
	 * @param jsonObject
	 * @return
	 */
	String collectGoods(JSONObject jsonObject);
	
	/**
	 * 收藏机构
	 * @param jsonObject
	 * @return
	 */
	String collectOrg(JSONObject jsonObject);
	
	/**
	 * 获取用户收藏列表
	 * @param jsonObject
	 * @return
	 */
	String getUserCollect(JSONObject jsonObject);
	
	
	List<UserCollect> getUserCollect(Integer memberId, String collType);

	UserCollect getUserCollect(Integer memberId, String collType,Integer refId);
}
