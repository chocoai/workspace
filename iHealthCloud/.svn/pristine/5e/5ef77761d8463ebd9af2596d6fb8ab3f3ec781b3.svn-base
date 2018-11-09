package com.yhcrt.iHealthCloud.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.HealthRecord;
import com.yhcrt.iHealthCloud.entity.HealthRecordExample;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.SysUser;
import com.yhcrt.iHealthCloud.mapper.HealthRecordMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.SysUserMapper;
import com.yhcrt.iHealthCloud.pojo.HealthRecordAppView;
import com.yhcrt.iHealthCloud.service.PersonalInfoService;
import com.yhcrt.iHealthCloud.util.Const;

@Service
public class PersonalInfoServiceImpl extends BaseService implements PersonalInfoService {

	protected boolean isDebug = true;

	@Autowired
	private MemberMapper memberMappler;
	@Autowired
	private HealthRecordMapper recordMapper;
	@Autowired
	private SysUserMapper userMapper;

	@Override
	public String getHealthRecordByMemberId(JSONObject pdataObj) {

		String memberId = getMemberId(pdataObj);
		if (!judgeAgumentsIsLegal(pdataObj, memberId)) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return pdataObj.toJSONString();
		}

		// 根据memberId查询member
		Member member = memberMappler.selectByPrimaryKey(Integer.parseInt(memberId));
		if (member == null) {
			requestFailed(pdataObj, Constants.ExceptionMsg.MEMBER_NOT_FOUND);
			return pdataObj.toJSONString();
		}

		// 根据memberId查询健康档案
		HealthRecordExample example = new HealthRecordExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		HealthRecord record = recordMapper.selectByExample(example);
		if (record != null) {
			HealthRecordAppView healthRecord = setHealthRecordAppView(member, record);
			pdataObj.put(Const.TAG_BIZ, healthRecord);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		} else {
			requestFailed(pdataObj, "该会员未完善健康档案!");
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	private HealthRecordAppView setHealthRecordAppView(Member member, HealthRecord record) {
		// birthday
		String birthday = "";
		if (StringUtils.isNotBlank(member.getIdentityCard()) && member.getIdentityCard().length() == 18) {
			String s = member.getIdentityCard().substring(6, 14);
			StringBuilder sb = new StringBuilder(s);
			sb.insert(4, "-");
			sb.insert(7, "-");
			birthday = sb.toString();
		}

		HealthRecordAppView healthRecord = new HealthRecordAppView();
		healthRecord.setName(member.getRealName());
		healthRecord.setBirthday(birthday);
		healthRecord.setGender(member.getGenderView());
		healthRecord.setIdNum(member.getIdentityCard());
		healthRecord.setPhoneNumber(member.getPhoneNo());
		healthRecord.setCommunicableDiseasesHistory(record.getCommunicableDiseases());
		healthRecord.setDisabilityHistory(record.getDisability());
		healthRecord.setInheritedDiseases(record.getInheritedDiseases());
		healthRecord.setAllergicHistory(record.getAllergicHistory());
		healthRecord.setOperations(record.getOperations());
		healthRecord.setTransfusions(record.getTransfusions());
		healthRecord.setTraumas(record.getTraumas());
		return healthRecord;
	}

	@Override
	public HealthRecord getHealthRecordByMemberId(String memberId) {
		// 根据memberId查询健康档案
		HealthRecordExample example = new HealthRecordExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		HealthRecord record = recordMapper.selectByExample(example);
		if (record == null) {
			record = new HealthRecord();
		}
		return record;
	}

	@Override
	public String register(JSONObject pdataObj) {
		return null;
	}

	@Override
	public String login(JSONObject pdataObj) {
		return null;
	}

	@Override
	public String retrievePassword(JSONObject pdataObj) {
		return null;
	}

	@Override
	public String resetPassword(JSONObject pdataObj) {

		JSONObject bizObj = getBizObj(pdataObj);
		String userId = bizObj.getString(Const.TAG_USER_ID);
		String passwrod = bizObj.getString(Const.TAG_PASSWORD);

		if (judgeAgumentsIsLegal(pdataObj, userId, passwrod)) {

			try {
				SysUser user = new SysUser();
				user.setUserId(Integer.parseInt(userId));
				user.setPassword(passwrod);

				userMapper.updateByPrimaryKeySelective(user);
				requestSucceed(pdataObj, user, "");
			} catch (Exception e) {
				requestFailed(pdataObj, "illegal user_id");
			}

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return pdataObj.toJSONString();
	}

}
