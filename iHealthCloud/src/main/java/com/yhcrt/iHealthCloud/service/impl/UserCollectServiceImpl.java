package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.UserCollect;
import com.yhcrt.iHealthCloud.entity.UserCollectExample;
import com.yhcrt.iHealthCloud.mapper.UserCollectMapper;
import com.yhcrt.iHealthCloud.service.UserCollectService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;

/**
 * @Description:
 * @version 1.0 2017年9月7日
 * @author jimmy
 */
@Service
public class UserCollectServiceImpl extends BaseService implements UserCollectService {

	@Autowired
	private UserCollectMapper userCollectMapper;

	@Override
	public Integer add(UserCollect userCollect) {
		return userCollectMapper.insert(userCollect);
	}

	@Override
	public Integer del(Integer cid) {
		return userCollectMapper.deleteByPrimaryKey(cid);
	}

	@Override
	public Integer upd(UserCollect userCollect) {
		return userCollectMapper.updateByPrimaryKey(userCollect);
	}

	@Override
	public UserCollect get(Integer cid) {
		return userCollectMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<UserCollect> getAll() {
		return userCollectMapper.selectByExample(null);
	}

	@Override
	public String collectService(JSONObject jsonObject) {
		JSONObject bizObj = jsonObject.getJSONObject(Const.TAG_BIZ);
		String serviceId = bizObj.getString("service_id");
		String memberId = bizObj.getString("member_id");
		try {
			UserCollect userCollect = new UserCollect();
			userCollect.setMemberId(Integer.parseInt(memberId));
			userCollect.setRefId(Integer.parseInt(serviceId));
			userCollect.setCollectType(Constants.SERVICE);
			userCollect.setCreateTime(DateUtil.getDateTime());
			add(userCollect);
			requestSucceed(jsonObject, "", "");
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String collectGoods(JSONObject jsonObject) {
		JSONObject bizObj = jsonObject.getJSONObject(Const.TAG_BIZ);
		String goodsId = bizObj.getString("goods_id");
		String memberId = bizObj.getString("member_id");
		try {
			UserCollect userCollect = new UserCollect();
			userCollect.setMemberId(Integer.parseInt(memberId));
			userCollect.setRefId(Integer.parseInt(goodsId));
			userCollect.setCollectType(Constants.GOODS);
			userCollect.setCreateTime(DateUtil.getDateTime());
			add(userCollect);
			requestSucceed(jsonObject, "", "");
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String collectOrg(JSONObject jsonObject) {
		JSONObject bizObj = jsonObject.getJSONObject(Const.TAG_BIZ);
		String orgId = bizObj.getString("org_id");
		String memberId = bizObj.getString("member_id");
		try {
			UserCollect userCollect = new UserCollect();
			userCollect.setMemberId(Integer.parseInt(memberId));
			userCollect.setRefId(Integer.parseInt(orgId));
			userCollect.setCollectType(Constants.ORG);
			userCollect.setCreateTime(DateUtil.getDateTime());
			add(userCollect);
			requestSucceed(jsonObject, "", "");
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String getUserCollect(JSONObject jsonObject) {
		JSONObject bizObj = jsonObject.getJSONObject(Const.TAG_BIZ);
		String memberId = bizObj.getString("member_id");
		String collectType = bizObj.getString("collect_type");
		String currentPage = bizObj.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = bizObj.getString(Const.TAG_PAGE_SIZE);
		try {
			UserCollectExample example = new UserCollectExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId)).andCollectTypeEqualTo(collectType);
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			List<UserCollect> collects = userCollectMapper.selectByExample(example);
			PageInfo<UserCollect> pageInfo = new PageInfo<UserCollect>(collects);
			setPagingData(jsonObject, pageInfo.getPages(), pageInfo.getPageNum());
			requestSucceed(jsonObject, collects, "");
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public List<UserCollect> getUserCollect(Integer memberId, String collType) {
		UserCollectExample example = new UserCollectExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andCollectTypeEqualTo(collType);
		return userCollectMapper.selectByExample(example);
	}

	@Override
	public UserCollect getUserCollect(Integer memberId, String collType,Integer refId) {
		UserCollectExample example = new UserCollectExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andCollectTypeEqualTo(collType).andRefIdEqualTo(refId);
		List<UserCollect> list= userCollectMapper.selectByExample(example);
		return null!=list&&list.size()>0?list.get(0):null;
	}
}
