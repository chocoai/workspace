package com.yhcrt.iHealthCloud.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.AlarmDefine;
import com.yhcrt.iHealthCloud.entity.AlarmMsg;
import com.yhcrt.iHealthCloud.entity.AlarmMsgDetail;
import com.yhcrt.iHealthCloud.entity.BloodPressureSetting;
import com.yhcrt.iHealthCloud.entity.Device;
import com.yhcrt.iHealthCloud.entity.DsSosLinkman;
import com.yhcrt.iHealthCloud.entity.DsSosLinkmanExample;
import com.yhcrt.iHealthCloud.entity.HdPulse;
import com.yhcrt.iHealthCloud.entity.HdPulseExample;
import com.yhcrt.iHealthCloud.entity.HeartRateSetting;
import com.yhcrt.iHealthCloud.entity.HeartRateSettingExample;
import com.yhcrt.iHealthCloud.entity.Location;
import com.yhcrt.iHealthCloud.entity.LocationExample;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberAddress;
import com.yhcrt.iHealthCloud.entity.MemberAddressExample;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.entity.MemberDeviceExample;
import com.yhcrt.iHealthCloud.entity.MemberExample;
import com.yhcrt.iHealthCloud.entity.MemberRelationship;
import com.yhcrt.iHealthCloud.entity.MemberRelationshipExample;
import com.yhcrt.iHealthCloud.entity.MemberRelationshipExample.Criteria;
import com.yhcrt.iHealthCloud.entity.RemindSetting;
import com.yhcrt.iHealthCloud.entity.RemindSettingExample;
import com.yhcrt.iHealthCloud.entity.SecureLocationSetting;
import com.yhcrt.iHealthCloud.entity.SecureLocationSettingExample;
import com.yhcrt.iHealthCloud.entity.StepSetting;
import com.yhcrt.iHealthCloud.mapper.AlarmDefineMapper;
import com.yhcrt.iHealthCloud.mapper.AlarmMsgDetailMapper;
import com.yhcrt.iHealthCloud.mapper.AlarmMsgMapper;
import com.yhcrt.iHealthCloud.mapper.BloodPressureSettingMapper;
import com.yhcrt.iHealthCloud.mapper.DeviceMapper;
import com.yhcrt.iHealthCloud.mapper.DsSosLinkmanMapper;
import com.yhcrt.iHealthCloud.mapper.HdBloodGlucoseMapper;
import com.yhcrt.iHealthCloud.mapper.HdBloodPressureMapper;
import com.yhcrt.iHealthCloud.mapper.HdPulseMapper;
import com.yhcrt.iHealthCloud.mapper.HdSleepMapper;
import com.yhcrt.iHealthCloud.mapper.HdStepMapper;
import com.yhcrt.iHealthCloud.mapper.HeartRateSettingMapper;
import com.yhcrt.iHealthCloud.mapper.LocationMapper;
import com.yhcrt.iHealthCloud.mapper.MemberAddressMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.MemberRelationshipMapper;
import com.yhcrt.iHealthCloud.mapper.OrganizationMapper;
import com.yhcrt.iHealthCloud.mapper.RemindSettingMapper;
import com.yhcrt.iHealthCloud.mapper.SecureLocationSettingMapper;
import com.yhcrt.iHealthCloud.mapper.StepSettingMapper;
import com.yhcrt.iHealthCloud.pojo.BloodGlucoseDTO;
import com.yhcrt.iHealthCloud.pojo.BloodPressureDTO;
import com.yhcrt.iHealthCloud.pojo.IndexData;
import com.yhcrt.iHealthCloud.pojo.MemberDTO;
import com.yhcrt.iHealthCloud.pojo.MemberRelationshipDTO;
import com.yhcrt.iHealthCloud.pojo.OrgServicer;
import com.yhcrt.iHealthCloud.pojo.SleepDTO;
import com.yhcrt.iHealthCloud.service.MemberService;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.LocationUtil;
import com.yhcrt.iHealthCloud.util.RequestUtils;
/**
 * 
 * @author rpf
 *
 */
@Service
public class MemberServiceImpl extends BaseService implements MemberService {
	
	@Autowired
	private MemberRelationshipMapper memberRelationshipMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberDeviceMapper memberDeviceMapper;
	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private LocationMapper locationMapper;
	@Autowired
	private DsSosLinkmanMapper dsSosLinkmanMapper;
	@Autowired
	private RemindSettingMapper remindSettingMapper;
	@Autowired
	private HeartRateSettingMapper heartRateSettingMapper;
	@Autowired
	private SecureLocationSettingMapper secureLocationSettingMapper;
	@Autowired
	private StepSettingMapper stepSettingMapper;
	@Autowired
	private BloodPressureSettingMapper bloodPressureSettingMapper;
	@Autowired
	private AlarmDefineMapper alarmDefineMapper;
	@Autowired
	private AlarmMsgMapper alarmMsgMapper;
	@Autowired
	private AlarmMsgDetailMapper alarmMsgDetailMapper;
	@Autowired
	private MemberAddressMapper	memberAddressMapper;
	@Autowired
	private HdPulseMapper hdPulseMapper;
	@Autowired
	private HdBloodPressureMapper hdBloodPressureMapper;
	@Autowired
	private HdBloodGlucoseMapper hdBloodGlucoseMapper;
	@Autowired
	private HdStepMapper hdStepMapper;
	@Autowired
	private HdSleepMapper hdSleepMapper;
	@Autowired
	private  OrganizationMapper organizationMapper;
	
	@Override
	public String getMyFollowing(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		if(StringUtils.isNotBlank(memberId)){
			// 我关注的人
			List<MemberDTO> myFollowing = memberMapper.listMyFollowing(memberId);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("following", myFollowing);
			pdataObj.put(Const.TAG_BIZ, jsonObj);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		}else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getMyFollowers(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		if(StringUtils.isNotBlank(memberId)){
			// 我关注的人
			List<MemberDTO> myFollowers = memberMapper.listMyFollowers(memberId);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("followers", myFollowers);
			pdataObj.put(Const.TAG_BIZ, jsonObj);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		}else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getFollowAndScore(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String imei = bizObj.getString("imei");
	
		
		MemberRelationshipExample example = new MemberRelationshipExample();
		if(StringUtils.isNotBlank(imei)){
			MemberDevice device = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
			if(device==null){
				requestFailed(pdataObj, "未查询到此imei");
				return toJsonStringWithOutNull(pdataObj);
			}
			example.createCriteria().andMemberIdEqualTo(device.getMemberId());
			List<MemberRelationship> mrs = memberRelationshipMapper.selectByExample(example);
			JSONObject biz = new JSONObject();
			biz.put("follow", mrs.size());
			biz.put("score", 0);
			biz.put("praise", 0);
			pdataObj.put(Const.TAG_BIZ, biz);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		}else{
			requestFailed(pdataObj, "缺少imei");
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getMyFollowingForSmall(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String memberId = bizObj.getString("member_id");
		if (StringUtils.isBlank(memberId)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		List<MemberRelationshipDTO> mrs = memberRelationshipMapper.getMyFollowingForSmall(memberId);
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, mrs);
		return toJsonStringWithOutNull(jsonObject);
	}
	
	@Override
	public String getHealthData(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String memberId = bizObj.getString("member_id");
		if(StringUtils.isNotBlank(memberId)){
			BloodGlucoseDTO  bloodGlucoseDTO = hdBloodGlucoseMapper.getLatestBloodGlucose(memberId); //血糖
			BloodPressureDTO bloodPressureDTO = hdBloodPressureMapper.getLatestBloodPressure(memberId); //血压
			HdPulseExample example = new HdPulseExample();
			example.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId));
			example.setOrderByClause("upload_time desc");
			List<HdPulse> pulses = hdPulseMapper.selectByExample(example);
			JSONObject hdPulseDTO = new JSONObject();
            if(pulses.size()>0){
            	HdPulse pulse = pulses.get(0);
            	hdPulseDTO.put("dataDate", pulse.getUploadTime());
            	hdPulseDTO.put("pulse", pulse.getPulse());
            }
			int step = hdStepMapper.getTodayStep(memberId); //步数
			SleepDTO sleepDTO = hdSleepMapper.getLatestSleepData(memberId);
			JSONObject biz = new JSONObject();
			biz.put("bloodGlucoseDTO", bloodGlucoseDTO);
			biz.put("bloodPressureDTO", bloodPressureDTO);
			biz.put("hdPulseDTO", hdPulseDTO);
			biz.put("step", step);
			biz.put("sleepDTO", sleepDTO);
			pdataObj.put(Const.TAG_BIZ, biz);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		}else{
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "缺少member_id");
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getMyOrgServicer(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String memberId = bizObj.getString("member_id");
		if(StringUtils.isNotBlank(memberId)){
			List<OrgServicer> list = memberMapper.listOrgServicer(memberId);
			requestSucceed(pdataObj, list, "");
		}
		return toJsonStringWithOutNull(pdataObj);
		
	}
	
	@Override
	public String getIndexData(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		if(StringUtils.isNotBlank(memberId)){
			List<IndexData> indexDatas = memberMapper.listIndexData(memberId);
			pdataObj.put(Const.TAG_BIZ, JSON.toJSON(indexDatas));
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getMyAttention(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		if(StringUtils.isNotBlank(memberId)){
			// 我关注的人
			List<com.yhcrt.iHealthCloud.pojo.MemberDTO> myFollowing = memberMapper.listMyFollowing(memberId);
			// 关注我的人
			List<com.yhcrt.iHealthCloud.pojo.MemberDTO> myFollowers = memberMapper.listMyFollowers(memberId);
			// 机构服务人员
			List<OrgServicer> orgServicers = memberMapper.listOrgServicer(memberId);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("following", myFollowing);
			jsonObj.put("followers", myFollowers);
			jsonObj.put("orgServicers", orgServicers);
			requestSucceed(pdataObj, jsonObj, "");
		}else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	

	@Override
	public String toFollow(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String memberId = bizObj.getString("member_id");
		String imei = bizObj.getString("imei");
		String relationDesc = bizObj.getString("relation_desc");
		try {
			MemberDevice md = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
			if (md == null) {
				throw new Exception("会员未找到，请检查输入的IMEI号码是否正确");
			}
			if (memberId.equals(md.getMemberId().toString())) {
				throw new Exception("请不要关注自己哦");
			}
			// 检查是否已经关注过此会员
			MemberRelationshipExample example = new MemberRelationshipExample();
			Criteria criteria = example.createCriteria();
			criteria.andMemberIdEqualTo(md.getMemberId());
			criteria.andFollowerIdEqualTo(Integer.parseInt(memberId));
			criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
			List<MemberRelationship> mrs = memberRelationshipMapper.selectByExample(example);
			if (mrs.isEmpty()) {
				MemberRelationship mr = new MemberRelationship();
				mr.setMemberId(md.getMemberId());
				mr.setFollowerId(Integer.parseInt(memberId));
				mr.setRelationDesc(relationDesc);
				mr.setCreateTime(DateUtils.getCurrentTime());
				mr.setStatus(Constants.STATUS_NORMAL);
				memberRelationshipMapper.insert(mr);
			}else{
				throw new Exception("已关注过此会员，请勿重复关注");
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
			
			example = new MemberRelationshipExample();
			criteria = example.createCriteria();
			criteria.andMemberIdEqualTo(md.getMemberId());
			criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
			mrs = memberRelationshipMapper.selectByExample(example);
			JSONObject pdata = new JSONObject();
			JSONObject biz = new JSONObject();
			pdata.put("sid", "S61");
			biz.put("imei", imei);
			biz.put("type", "1");
			biz.put("followNum", mrs.size());
			pdata.put("biz", biz);
			RequestUtils.httpPostWithJson(pdata, RequestUtils.SOCKET_URL);  //手表指令设置关注
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String toFollowByTel(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String memberId = bizObj.getString("member_id");
		String phone = bizObj.getString("phone_no");
		String relationDesc = bizObj.getString("relation_desc");
		try {
			Member member = selectByPrimaryTel(phone);
			if (member == null) {
				throw new Exception("会员未找到，请检查输入的手机号码是否正确");
			}
			// 检查是否已经关注过此会员
			MemberRelationshipExample example = new MemberRelationshipExample();
			Criteria criteria = example.createCriteria();
			criteria.andMemberIdEqualTo(member.getMemberId());
			criteria.andFollowerIdEqualTo(Integer.parseInt(memberId));
			criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
			List<MemberRelationship> mrs = memberRelationshipMapper.selectByExample(example);
			if (mrs.isEmpty()) {
				MemberRelationship mr = new MemberRelationship();
				mr.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MEMBER_RELATIONSHIP));
				mr.setMemberId(member.getMemberId());
				mr.setFollowerId(Integer.parseInt(memberId));
				mr.setRelationDesc(relationDesc);
				mr.setCreateTime(DateUtils.getCurrentTime());
				mr.setStatus(Constants.STATUS_NORMAL);
				memberRelationshipMapper.insert(mr);
			}else{
				throw new Exception("已关注过此会员，请勿重复关注");
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String unFollow(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String memberId = bizObj.getString("member_id");
		String followerId = bizObj.getString("follower_id");
		try {
			MemberRelationshipExample example = new MemberRelationshipExample();
			Criteria criteria = example.createCriteria();
			if (StringUtils.isNotBlank(memberId) && StringUtils.isNotBlank(followerId)) {
				criteria.andMemberIdEqualTo(Integer.parseInt(memberId));
				criteria.andFollowerIdEqualTo(Integer.parseInt(followerId));
			}
			memberRelationshipMapper.deleteByExample(example);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String followerDetail(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String memberId = bizObj.getString("member_id");
		String followerId = bizObj.getString("follower_id");
		try {
			MemberRelationshipExample example = new MemberRelationshipExample();
			Criteria criteria = example.createCriteria();
			if (StringUtils.isNotBlank(memberId) && StringUtils.isNotBlank(followerId)) {
				criteria.andMemberIdEqualTo(Integer.parseInt(memberId));
				criteria.andFollowerIdEqualTo(Integer.parseInt(followerId));
			}
			List<MemberRelationship> mrs = memberRelationshipMapper.selectByExample(example);
			if (!mrs.isEmpty()) {
				JSONObject memberObj = new JSONObject();
				memberObj.put("nickName", mrs.get(0).getMember().getNickName());
				memberObj.put("gender", mrs.get(0).getMember().getGenderView());
				memberObj.put("orgName",mrs.get(0).getMember().getOrg()!=null?mrs.get(0).getMember().getOrg().getOrgName():"");
				JSONObject followerObj = new JSONObject();
				followerObj.put("nickName", mrs.get(0).getFollower().getNickName());
				followerObj.put("gender", mrs.get(0).getFollower().getGenderView());
				followerObj.put("orgName",mrs.get(0).getFollower().getOrg()!=null? mrs.get(0).getFollower().getOrg().getOrgName():"");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("memberId", mrs.get(0).getMemberId());
				jsonObject.put("followerId", mrs.get(0).getFollowerId());
				jsonObject.put("relationDesc", mrs.get(0).getRelationDesc());
				jsonObject.put("createTime", mrs.get(0).getCreateTime());
				jsonObject.put("member", memberObj);
				jsonObject.put("follower", followerObj);
				pdataObj.put(Const.TAG_BIZ, jsonObject);
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "");
			}else {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			}
			
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public String bindDevice(JSONObject pdataObj) {
		JSONObject bizObj = pdataObj.getJSONObject(Const.TAG_BIZ);
		String memberId = bizObj.getString("member_id");
		String imei = bizObj.getString("imei");
		String sim = bizObj.getString("sim");
		if (judgeAgumentsIsLegal(pdataObj, memberId,imei,sim)) {
			Device device = deviceMapper.selectByImei(imei);
			if (device == null) {
				requestFailed(pdataObj, Constants.ExceptionMsg.DEVICE_NOT_FOUND);
				return toJsonStringWithOutNull(pdataObj);
			}
			MemberDeviceExample example = new MemberDeviceExample();
			example.createCriteria().andImeiEqualTo(imei);
			List<MemberDevice> mds = memberDeviceMapper.selectByExample(example);
			if (!mds.isEmpty()) {
				requestFailed(pdataObj, Constants.ExceptionMsg.DEVICE_HAS_BEEN_BINDING);
				return toJsonStringWithOutNull(pdataObj);
			}
			example = new MemberDeviceExample();
			example.createCriteria().andSimEqualTo(sim);
			mds = memberDeviceMapper.selectByExample(example);
			if (!mds.isEmpty()) {
				requestFailed(pdataObj, Constants.ExceptionMsg.SIM_HAS_BEEN_BINDING);
				return toJsonStringWithOutNull(pdataObj);
			}
			Integer isDefault = 1 ;   //是否默认设备
			example = new MemberDeviceExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			mds = memberDeviceMapper.selectByExample(example);
			if(mds.size()>0){
				isDefault = 0;
			}
			try {
				Member member = memberMapper.selectByPrimaryKey(Integer.parseInt(memberId));
				MemberDevice md  = new MemberDevice();
				md.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MEMBER_DEVICE));
				md.setMemberId(Integer.parseInt(memberId));
				md.setImei(imei);
				md.setSim(sim);
				md.setBindTime(DateUtils.getCurrentTime());
				md.setStatus(Constants.STATUS_NORMAL);
				md.setOrgId(device.getOrgId());
				md.setIsDefault(isDefault);
				md.setDeviceType(device.getDeviceType());
				memberDeviceMapper.insert(md);
				// 更新设备状态
				device.setIsUse(Constants.Device.USED);
				device.setSim(sim);
				deviceMapper.updateByPrimaryKey(device);
				// 更新会员等级、所属部门等信息
				member.setMemberType(Constants.MemberType.VIP);
				member.setOrgId(device.getOrgId());
				memberMapper.updateByPrimaryKey(member);
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "");
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
				e.printStackTrace();
			}
		}else{
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public String startDevice(JSONObject pdataObj) {
		JSONObject bizObj = pdataObj.getJSONObject(Const.TAG_BIZ);
		String imei = bizObj.getString("imei");
		if (judgeAgumentsIsLegal(pdataObj,imei)) {
			Device device = deviceMapper.selectByImei(imei);
			if (device == null) {
				requestFailed(pdataObj, Constants.ExceptionMsg.DEVICE_NOT_FOUND);
				return toJsonStringWithOutNull(pdataObj);
			}
			try {
				if(device.getStatus()==null || device.getStatus()==0){
					deviceMapper.startDevice(device.getDeviceId());	
				}
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "");
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
				e.printStackTrace();
			}
		}else{
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public String unBindDevice(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		String imei = bizObj.getString("imei");
		if (StringUtils.isBlank(memberId) || StringUtils.isBlank(imei)) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		}
		
		Device device = deviceMapper.selectByImei(imei);
		if (device == null) {
			requestFailed(pdataObj, Constants.ExceptionMsg.DEVICE_NOT_FOUND);
			return toJsonStringWithOutNull(pdataObj);
		}
		
		try {
			MemberDeviceExample example = new MemberDeviceExample();
			com.yhcrt.iHealthCloud.entity.MemberDeviceExample.Criteria criteria = example.createCriteria();
			criteria.andMemberIdEqualTo(Integer.parseInt(memberId));
			criteria.andImeiEqualTo(imei);
			memberDeviceMapper.deleteByExample(example);
			// 更新设备状态
			device.setIsUse(Constants.Device.UNUSED);
			device.setSim("");
			deviceMapper.updateByPrimaryKey(device);
			// 检查会员是否还绑定有其他设备，若无则更新会员等级为普通会员
			example = new MemberDeviceExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId)).andStatusEqualTo(Constants.STATUS_NORMAL);
			List<MemberDevice> mds = memberDeviceMapper.selectByExample(example);
			if (mds.isEmpty()) {
				Member member = memberMapper.selectByPrimaryKey(Integer.parseInt(memberId));
				member.setMemberType(Constants.MemberType.COMMON);
				member.setOrgId(organizationMapper.getOrgRootNode().getOrgId());
				memberMapper.updateByPrimaryKey(member);
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String setDefaultDevice(JSONObject jsonObj) {
		JSONObject bizObj = getBizObj(jsonObj);
		String memberId = bizObj.getString("member_id");
		String imei = bizObj.getString("imei");
		if (StringUtils.isBlank(memberId) || StringUtils.isBlank(imei)) {
			requestFailed(jsonObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObj);
		}
		Device device = deviceMapper.selectByImei(imei);
		if (device == null) {
			requestFailed(jsonObj, Constants.ExceptionMsg.DEVICE_NOT_FOUND);
			return toJsonStringWithOutNull(jsonObj);
		}
		
		try {
			MemberDevice defaultDevice = memberDeviceMapper.getDefaultDevice(memberId);
			// 更新设备状态
			defaultDevice.setIsDefault(Constants.NO);
			memberDeviceMapper.updateByPrimaryKeySelective(defaultDevice);
			
			MemberDevice md = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
			// 更新设备状态
			md.setIsDefault(Constants.YES);
			md.setOrgId(device.getOrgId());
			memberDeviceMapper.updateByPrimaryKeySelective(md);
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			requestFailed(jsonObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObj);
	}
	

	@Override
	public String getMyDevices(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		try {
			MemberDeviceExample deviceExample = new MemberDeviceExample();
			com.yhcrt.iHealthCloud.entity.MemberDeviceExample.Criteria criteria = deviceExample.createCriteria();
			criteria.andMemberIdEqualTo(Integer.parseInt(memberId));
			criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
			List<MemberDevice> mds = memberDeviceMapper.selectByExample(deviceExample);
			System.out.println(mds.toString());
			pdataObj.put(Const.TAG_BIZ, mds);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			System.out.println(pdataObj.toJSONString());
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getDeviceDetail(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String imei = bizObj.getString("imei");
		Device device = deviceMapper.selectByImei(imei); 
		if(device == null){
			requestFailed(pdataObj, "no such device");
		}else{
			requestSucceed(pdataObj, device, "");
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String setLinkman(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String imei = bizObj.getString("imei");
		String lankman = bizObj.getString("lankman");
		String phoneNum = bizObj.getString("phone_num");
		if(judgeAgumentsIsLegal(pdataObj, imei,lankman,phoneNum)){
			DsSosLinkmanExample example1 = new DsSosLinkmanExample();		
			example1.createCriteria().andImeiEqualTo(imei).andPhoneNumEqualTo(phoneNum);
			List<DsSosLinkman> linkmanlist1 = dsSosLinkmanMapper.selectByExample(example1);
			if(linkmanlist1.size()>0){
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK,"该号码已经存在");
				return toJsonStringWithOutNull(pdataObj);
			}
			DsSosLinkman sosLinkman = new DsSosLinkman();
			Integer cid = sysSequenceService.getSequenceValue(Constants.SequenceName.SOS_LINKMAN);
			sosLinkman.setCid(cid);
			sosLinkman.setImei(imei);
			sosLinkman.setLankman(lankman);
			sosLinkman.setPhoneNum(phoneNum);
			dsSosLinkmanMapper.insert(sosLinkman);
			DsSosLinkmanExample example = new DsSosLinkmanExample();		
			example.createCriteria().andImeiEqualTo(imei);
			example.setOrderByClause("cid asc");
			List<DsSosLinkman> linkmanlist = dsSosLinkmanMapper.selectByExample(example);
			if(linkmanlist.size()>8){
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK,"联系人已经超过8人,不能继续添加");
				dsSosLinkmanMapper.deleteByPrimaryKey(cid);
				return toJsonStringWithOutNull(pdataObj);
			}
			JSONObject pdata = new JSONObject();
			JSONObject contact = new JSONObject();
			pdata.put("sid", "S57");
			contact.put("imei", imei);
			contact.put("contacts", linkmanlist);
			pdata.put("biz", contact);
			String str = RequestUtils.httpPostWithJson(pdata, RequestUtils.SOCKET_URL);  //手表指令添加通讯录
			JSONObject strjson = JSONObject.parseObject(str);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, strjson.getString("rmk"));
//			if(!"1".equals(strjson.getString("sts"))){  //不成功则删除表记录
//				dsSosLinkmanMapper.deleteByPrimaryKey(cid);
//			}
		}else{
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String modifyLinkman(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String sosid = bizObj.getString("sos_id");
		String lankman = bizObj.getString("lankman");
		String phoneNum = bizObj.getString("phone_num");
		if(judgeAgumentsIsLegal(pdataObj, sosid,lankman,phoneNum)){
			DsSosLinkman sosLinkman = dsSosLinkmanMapper.selectByPrimaryKey(Integer.valueOf(sosid));
			if(sosLinkman==null){
				requestFailed(pdataObj,"未找到此sos_id");
				return toJsonStringWithOutNull(pdataObj);
			}
			DsSosLinkmanExample example1 = new DsSosLinkmanExample();		
			example1.createCriteria().andImeiEqualTo(sosLinkman.getImei()).andPhoneNumEqualTo(phoneNum).andCidNotEqualTo(Integer.valueOf(sosid));
			List<DsSosLinkman> linkmanlist1 = dsSosLinkmanMapper.selectByExample(example1);
			if(linkmanlist1.size()>0){
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK,"该号码已经存在");
				return toJsonStringWithOutNull(pdataObj);
			}
			sosLinkman.setLankman(lankman);
			sosLinkman.setPhoneNum(phoneNum);
			dsSosLinkmanMapper.updateByPrimaryKeySelective(sosLinkman);
			DsSosLinkmanExample example = new DsSosLinkmanExample();		
			example.createCriteria().andImeiEqualTo(sosLinkman.getImei());
			example.setOrderByClause("cid asc");
			List<DsSosLinkman> linkmanlist = dsSosLinkmanMapper.selectByExample(example);
			JSONObject pdata = new JSONObject();
			JSONObject contact = new JSONObject();
			pdata.put("sid", "S57");
			contact.put("imei", sosLinkman.getImei());
			contact.put("contacts", linkmanlist);
			pdata.put("biz", contact);
			String str = RequestUtils.httpPostWithJson(pdata, RequestUtils.SOCKET_URL);  //手表指令添加通讯录
			JSONObject strjson = JSONObject.parseObject(str);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, strjson.getString("rmk"));
		}else{
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String delLinkman(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String sosidstr = bizObj.getString("sos_id");
		if(judgeAgumentsIsLegal(pdataObj, sosidstr)){
			String[] sisids = sosidstr.split(",");
			String imei = "";
			for(String sosid : sisids){
				if("".equals(sosid.trim())){
					continue;
				}
				DsSosLinkman sosLinkman = dsSosLinkmanMapper.selectByPrimaryKey(Integer.valueOf(sosid));
				if(sosLinkman!=null){
					imei = sosLinkman.getImei();
					dsSosLinkmanMapper.deleteByPrimaryKey(Integer.valueOf(sosid));
				}
			}
			if(StringUtils.isBlank(imei)){
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, "该联系人不存在");
				return toJsonStringWithOutNull(pdataObj);
			}
			DsSosLinkmanExample example = new DsSosLinkmanExample();		
			example.createCriteria().andImeiEqualTo(imei);
			example.setOrderByClause("cid asc");
			List<DsSosLinkman> linkmanlist = dsSosLinkmanMapper.selectByExample(example);
			JSONObject pdata = new JSONObject();
			JSONObject contact = new JSONObject();
			pdata.put("sid", "S57");
			contact.put("imei", imei);
			contact.put("contacts", linkmanlist);
			pdata.put("biz", contact);
			String str = RequestUtils.httpPostWithJson(pdata, RequestUtils.SOCKET_URL);  //手表指令添加通讯录
			JSONObject strjson = JSONObject.parseObject(str);
			pdataObj.put(Const.TAG_STS,  Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, strjson.getString("rmk"));
		}else{
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getLinkman(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String imei = bizObj.getString("imei");
		try {
			DsSosLinkmanExample example = new DsSosLinkmanExample();
			example.createCriteria().andImeiEqualTo(imei);
			List<DsSosLinkman> linkmans = dsSosLinkmanMapper.selectByExample(example);
			pdataObj.put(Const.TAG_BIZ, JSON.toJSON(linkmans));
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String setRemind(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String imei = bizObj.getString("imei");
		String remindType = bizObj.getString("remind_type");
		String remindTime = bizObj.getString("remind_time");
		String remindSwitch = bizObj.getString("remind_switch");
		try {
			// 对参数进行非空判断
			if(judgeAgumentsIsLegal(pdataObj, imei,remindType,remindSwitch)){
				RemindSettingExample example = new RemindSettingExample();
				com.yhcrt.iHealthCloud.entity.RemindSettingExample.Criteria criteria = example.createCriteria();
				criteria.andImeiEqualTo(imei);
				criteria.andRemindTypeEqualTo(Integer.parseInt(remindType));
				List<RemindSetting> rss = remindSettingMapper.selectByExample(example);
				if (rss.isEmpty()) {
					RemindSetting rs = new RemindSetting();
					rs.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.REMIND_SET));
					rs.setImei(imei);
					rs.setRemindType(Integer.parseInt(remindType));
					rs.setRemindTime(remindTime);
					rs.setRemindSwitch(Integer.parseInt(remindSwitch));
					remindSettingMapper.insert(rs);
				}else{
					RemindSetting rs = rss.get(0);
					rs.setRemindType(Integer.parseInt(remindType));
					rs.setRemindTime(remindTime);
					rs.setRemindSwitch(Integer.parseInt(remindSwitch));
					remindSettingMapper.updateByPrimaryKey(rs);
				}
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "");
			}else{
				requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			}
			
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getRemindSet(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String imei = bizObj.getString("imei");
		try {
			RemindSettingExample example = new RemindSettingExample();
			example.createCriteria().andImeiEqualTo(imei);
			List<RemindSetting> remindSettings = remindSettingMapper.selectByExample(example);
			pdataObj.put(Const.TAG_BIZ, JSON.toJSON(remindSettings));
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String setHeart(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String imei = bizObj.getString("imei");
		String highHeartRate = bizObj.getString("high_heart_rate");
		String lowHeartRate = bizObj.getString("low_heart_rate");
		String alarmSwitch = bizObj.getString("alarm_switch");
		try {
			HeartRateSettingExample example = new HeartRateSettingExample();
			com.yhcrt.iHealthCloud.entity.HeartRateSettingExample.Criteria criteria = example.createCriteria();
			criteria.andImeiEqualTo(imei);
			List<HeartRateSetting> hrss = heartRateSettingMapper.selectByExample(example);
			if (hrss.isEmpty()) {
				HeartRateSetting hrs = new HeartRateSetting();
				hrs.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.HEART_SECURITY_SETTING));
				hrs.setImei(imei);
				hrs.setHighHeartRate(Integer.parseInt(highHeartRate));
				hrs.setLowHeartRate(Integer.parseInt(lowHeartRate));
				hrs.setAlarmSwitch(Integer.parseInt(alarmSwitch));
				heartRateSettingMapper.insert(hrs);
			}else{
				HeartRateSetting hrs = hrss.get(0);
				hrs.setHighHeartRate(Integer.parseInt(highHeartRate));
				hrs.setLowHeartRate(Integer.parseInt(lowHeartRate));
				hrs.setAlarmSwitch(Integer.parseInt(alarmSwitch));
				heartRateSettingMapper.updateByPrimaryKey(hrs);
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getHeartSet(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String imei = bizObj.getString("imei");
		try {
			HeartRateSettingExample example = new HeartRateSettingExample();
			example.createCriteria().andImeiEqualTo(imei);
			List<HeartRateSetting> heartRateSettings = heartRateSettingMapper.selectByExample(example);
			pdataObj.put(Const.TAG_BIZ, JSON.toJSON(heartRateSettings));
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String setSecureLocation(JSONObject pdataObj) {

		JSONObject bizObj = getBizObj(pdataObj);
		String imei = bizObj.getString("imei");
		String longitude = bizObj.getString("longitude");
		String latitude = bizObj.getString("latitude");
		String radius = bizObj.getString("radius");
		String alarmSwitch = bizObj.getString("alarm_switch");
		try {
			// 对参数进行非空判断
			if(judgeAgumentsIsLegal(pdataObj, longitude,latitude,radius,alarmSwitch)){
				SecureLocationSettingExample example = new SecureLocationSettingExample();
				com.yhcrt.iHealthCloud.entity.SecureLocationSettingExample.Criteria criteria = example.createCriteria();
				criteria.andImeiEqualTo(imei);
				List<SecureLocationSetting> slss = secureLocationSettingMapper.selectByExample(example);
				if (slss.isEmpty()) {
					SecureLocationSetting sls = new SecureLocationSetting();
					sls.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.SECURE_LOCATION));
					sls.setImei(imei);
					sls.setLongitude(Double.valueOf(longitude));
					sls.setLatitude(Double.valueOf(latitude));
					sls.setRadius(Integer.parseInt(radius));
					sls.setAlarmSwitch(Integer.parseInt(alarmSwitch));
					secureLocationSettingMapper.insert(sls);
				} else {
					SecureLocationSetting sls = slss.get(0);
					sls.setLongitude(Double.valueOf(longitude));
					sls.setLatitude(Double.valueOf(latitude));
					sls.setRadius(Integer.parseInt(radius));
					sls.setAlarmSwitch(Integer.parseInt(alarmSwitch));
					secureLocationSettingMapper.updateByPrimaryKey(sls);
				}
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "");
				
			}else{
				requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			}
			
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getSecureLocation(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String imei = bizObj.getString("imei");
		try {
			SecureLocationSettingExample example = new SecureLocationSettingExample();
			example.createCriteria().andImeiEqualTo(imei);
			List<SecureLocationSetting> secureLocationSettings = secureLocationSettingMapper.selectByExample(example);
			pdataObj.put(Const.TAG_BIZ, JSON.toJSON(secureLocationSettings));
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getLoction(JSONObject pdataObj) {
		
		String memberId = getMemberId(pdataObj);
		if (StringUtils.isBlank(memberId)) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		}

		// 查询会员的默认主设备
		MemberDevice defaultDevice = memberDeviceMapper.getDefaultDevice(memberId);
		String imei = "";
		if (defaultDevice != null) {
			imei = defaultDevice.getImei();
		} else {
			// 查询memberDevice
			MemberDeviceExample memberDeviceExample = new MemberDeviceExample();
			memberDeviceExample.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			memberDeviceExample.setOrderByClause(" bind_time desc ");
			List<MemberDevice> memberDeviceList = memberDeviceMapper.selectByExample(memberDeviceExample);
			imei = memberDeviceList.isEmpty() ? "" : memberDeviceList.get(0).getImei();
		}
		
		// 根据会员的设备IMEI查询会员的最新定位位置
		LocationExample locationExample = new LocationExample();
		locationExample.createCriteria().andImeiEqualTo(imei);
		locationExample.setOrderByClause("location_time desc");
		PageHelper.startPage(1, 1);
		List<Location> locations = locationMapper.selectByExample(locationExample);
		Location location = locations.isEmpty() ? new Location() : locations.get(0);
		JSONObject locationJsonObj = (JSONObject) JSON.toJSON(location);
		locationJsonObj.remove("locationId");
		
		// 查询会员的电子围栏设置
		SecureLocationSettingExample secureLocationExample = new SecureLocationSettingExample();
		secureLocationExample.createCriteria().andImeiEqualTo(imei);
		List<SecureLocationSetting> secureLocationSettings = secureLocationSettingMapper.selectByExample(secureLocationExample);
		SecureLocationSetting secureLocationSetting = secureLocationSettings.isEmpty() ? new SecureLocationSetting() : secureLocationSettings.get(0);
		JSONObject secureLocationSettingJsonObj = (JSONObject) JSON.toJSON(secureLocationSetting);
		secureLocationSettingJsonObj.remove("cid");
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("location",locationJsonObj);
		jsonObj.put("secureLocationSetting", secureLocationSettingJsonObj);
		pdataObj.put(Const.TAG_BIZ, jsonObj);
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_RMK, "");

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getLoctionHis(JSONObject pdataObj) {

		JSONObject bizObj = getBizObj(pdataObj);
		String imei = bizObj.getString("imei");
		imei = StringUtils.isBlank(imei) ? "" : imei;
		String dataDate = bizObj.getString("data_date");
		if (StringUtils.isBlank(dataDate)) {
			dataDate = DateUtils.getDate(new Date());
		}
		LocationExample locationExample = new LocationExample();
		locationExample.createCriteria().andImeiEqualTo(imei).andLocationTimeLike(dataDate + "%");
		locationExample.setOrderByClause("location_time desc");
		List<Location> locations = locationMapper.selectByExample(locationExample);
		requestSucceed(pdataObj, locations, "");
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getDeviceMembers(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String orgId = bizObj.getString("org_id");
		try {
			MemberExample example = new MemberExample();
			example.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId))
					.andMemberTypeEqualTo(Constants.MemberType.VIP)
					.andStatusEqualTo(Constants.STATUS_NORMAL);
			List<MemberDTO> members = memberMapper.listDeviceMemberByExample(example);
			if (!members.isEmpty()) {
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(members));
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getAppMembers(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String orgId = bizObj.getString("org_id");
		try {
			MemberExample example = new MemberExample();
			example.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId))
					.andMemberTypeEqualTo(Constants.MemberType.COMMON).andStatusEqualTo(Constants.STATUS_NORMAL);
			List<Member> members = memberMapper.selectByExample(example);
			if (!members.isEmpty()) {
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(members));
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getMemberProfile(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		
		try {
			Member member = memberMapper.selectByPrimaryKey(Integer.parseInt(memberId));
			if (member != null) {
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(member));
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
		
	}

	@Override
	public String getMemberImeis(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		try {
			MemberDeviceExample example = new MemberDeviceExample();
			example.createCriteria().andStatusEqualTo(Constants.STATUS_NORMAL).andMemberIdEqualTo(Integer.parseInt(memberId));
			List<MemberDevice> mds = memberDeviceMapper.selectByExample(example);
			if (mds.isEmpty()) {
				throw new Exception("会员未找到，请检查输入的会员ID是否正确");
			}
			pdataObj.put(Const.TAG_BIZ, JSON.toJSON(mds));
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String searchDeviceMembers(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String imei = bizObj.getString("imei");
		try {
			MemberDeviceExample example = new MemberDeviceExample();
			example.createCriteria().andImeiLike(imei).andStatusEqualTo(Constants.STATUS_NORMAL);
			List<MemberDevice> mds = memberDeviceMapper.selectByExample(example);
			if (mds.isEmpty()) {
				throw new Exception("会员未找到，请检查输入的IMEI号码是否正确");
			}
			pdataObj.put(Const.TAG_BIZ, JSON.toJSON(mds));
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getStepSetting(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		try {
			StepSetting stepSetting = stepSettingMapper.selectByMemberId(Integer.parseInt(memberId));
			requestSucceed(pdataObj, stepSetting, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String updateStepSetting(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		String targetStep = bizObj.getString("target_step");
		try {
			StepSetting stepSetting = stepSettingMapper.selectByMemberId(Integer.parseInt(memberId));
			if (stepSetting == null) {
				stepSetting = new StepSetting();
				stepSetting.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.STEP_SETTING));
				stepSetting.setMemberId(Integer.parseInt(memberId));
				stepSetting.setTargetStep(Integer.parseInt(targetStep));
				stepSetting.setUpdateTime(DateUtil.getDateTime());
				stepSettingMapper.insert(stepSetting);
			}else{
				stepSetting.setTargetStep(Integer.parseInt(targetStep));
				stepSetting.setUpdateTime(DateUtil.getDateTime());
				stepSettingMapper.updateByPrimaryKey(stepSetting);
			}
			stepSetting = stepSettingMapper.selectByMemberId(Integer.parseInt(memberId));
			requestSucceed(pdataObj, stepSetting, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getBloodPressureSetting(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		try {
			BloodPressureSetting bloodPressureSetting = bloodPressureSettingMapper.selectByMemberId(Integer.parseInt(memberId));
			requestSucceed(pdataObj, bloodPressureSetting, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String updateBloodPressureSetting(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		String dbpMin = bizObj.getString("dbp_min");
		String dbpMax = bizObj.getString("dbp_max");
		String sbpMin = bizObj.getString("sbp_min");
		String sbpMax = bizObj.getString("sbp_max");
		String alarmSwitch = bizObj.getString("alarm_switch");
		try {
			BloodPressureSetting bloodPressureSetting = bloodPressureSettingMapper.selectByMemberId(Integer.parseInt(memberId));
			if (bloodPressureSetting == null) {
				bloodPressureSetting = new BloodPressureSetting();
				bloodPressureSetting.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.BLOOD_PRESSURE_SETTING));
				bloodPressureSetting.setDbpMin(Integer.parseInt(dbpMin));
				bloodPressureSetting.setDbpMax(Integer.parseInt(dbpMax));
				bloodPressureSetting.setSbpMin(Integer.parseInt(sbpMin));
				bloodPressureSetting.setSbpMax(Integer.parseInt(sbpMax));
				bloodPressureSetting.setMemberId(Integer.parseInt(memberId));
				bloodPressureSetting.setAlarmSwitch(Integer.parseInt(alarmSwitch));
				bloodPressureSettingMapper.insert(bloodPressureSetting);
			}else{
				bloodPressureSetting.setDbpMin(Integer.parseInt(dbpMin));
				bloodPressureSetting.setDbpMax(Integer.parseInt(dbpMax));
				bloodPressureSetting.setSbpMin(Integer.parseInt(sbpMin));
				bloodPressureSetting.setSbpMax(Integer.parseInt(sbpMax));
				bloodPressureSetting.setAlarmSwitch(Integer.parseInt(alarmSwitch));
				bloodPressureSettingMapper.updateByPrimaryKey(bloodPressureSetting);
			}
			bloodPressureSetting = bloodPressureSettingMapper.selectByMemberId(Integer.parseInt(memberId));
			requestSucceed(pdataObj, bloodPressureSetting, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String uploadLocation(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String imei = bizObj.getString("imei");
		String datadate = bizObj.getString("data_date");
		String longitude = bizObj.getString("longitude");
		String latitude = bizObj.getString("latitude");
		Boolean sos= bizObj.getBoolean("sos");
		String addr = bizObj.getString("addr")==null?"":bizObj.getString("addr");
		if (judgeAgumentsIsLegal(pdataObj, imei, longitude, latitude)) {
			SecureLocationSetting locationSetting = secureLocationSettingMapper.selectByImei(imei);
			MemberDevice md = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
			if(sos!=null && sos.booleanValue()==true){ //是否手机sos呼叫产生的定位信息
				AlarmDefine alarmDefine = alarmDefineMapper.selectByAlarmType(Constants.AlarmType.SOS);
				AlarmMsg alarmMsg = new AlarmMsg();
				Integer alarmMsgId = sysSequenceService.getSequenceValue(Constants.SequenceName.ALARM_MSG);
				alarmMsg.setCid(alarmMsgId);
				if (md != null) {
					alarmMsg.setMemberId(md.getMemberId());
				}
				alarmMsg.setAlarmType(Constants.AlarmType.SOS);
				if (alarmDefine != null){
					String alarmContent = alarmDefine.getAlarmMsg().replaceFirst("\\{}",md.getMember().getRealName()).replaceFirst("\\{}", addr);
					alarmMsg.setAlarmContent(alarmContent);
				}
				alarmMsg.setStatus(Constants.STATUS_NORMAL);
				alarmMsg.setIsRead(Constants.UNREAD);
				alarmMsg.setImei(imei);
				alarmMsg.setCreateTime(DateUtils.getCurrentTime());
				alarmMsgMapper.insert(alarmMsg);
				List<MemberRelationship> memberRelationshipList = memberRelationshipMapper.selectMemRelaF(md.getMemberId());
				for(MemberRelationship ship : memberRelationshipList){
					Integer followid = ship.getFollowerId();
					String uuid = UUID.randomUUID().toString().replace("-", "");  
					AlarmMsgDetail detail = new AlarmMsgDetail();
					detail.setCid(uuid);
					detail.setAlarmMsgId(alarmMsgId);
					detail.setIsRead(Constants.UNREAD);
					detail.setMemberId(followid);
					alarmMsgDetailMapper.insert(detail);
				}
				String uuid = UUID.randomUUID().toString().replace("-", "");  
				AlarmMsgDetail detail = new AlarmMsgDetail();
				detail.setCid(uuid);
				detail.setAlarmMsgId(alarmMsgId);
				detail.setIsRead(Constants.UNREAD);
				detail.setMemberId(md.getMemberId());
				alarmMsgDetailMapper.insert(detail);
			}else if (isOutOfBounds(Double.valueOf(latitude), Double.valueOf(longitude), locationSetting)) {// 会员的位置是否超出了电子围栏设置的范围,若超出将产生预警消息
				AlarmDefine alarmDefine = alarmDefineMapper.selectByAlarmType(Constants.AlarmType.LOCATION);
				AlarmMsg alarmMsg = new AlarmMsg();
				Integer alarmMsgId = sysSequenceService.getSequenceValue(Constants.SequenceName.ALARM_MSG);
				alarmMsg.setCid(alarmMsgId);
				if (md != null) {
					alarmMsg.setMemberId(md.getMemberId());
				}
				alarmMsg.setAlarmType(Constants.AlarmType.LOCATION);
				if (alarmDefine != null){
					String alarmContent = alarmDefine.getAlarmMsg().replaceFirst("\\{}",md.getMember().getRealName()).replaceFirst("\\{}", addr);
					alarmMsg.setAlarmContent(alarmContent);
				}
				alarmMsg.setStatus(Constants.STATUS_NORMAL);
				alarmMsg.setImei(imei);
				alarmMsg.setIsRead(Constants.UNREAD);
				alarmMsg.setCreateTime(DateUtils.getCurrentTime());
				alarmMsgMapper.insert(alarmMsg);
				List<MemberRelationship> memberRelationshipList = memberRelationshipMapper.selectMemRelaF(md.getMemberId());
				for(MemberRelationship ship : memberRelationshipList){
					Integer followid = ship.getFollowerId();
					String uuid = UUID.randomUUID().toString().replace("-", "");  
					AlarmMsgDetail detail = new AlarmMsgDetail();
					detail.setCid(uuid);
					detail.setAlarmMsgId(alarmMsgId);
					detail.setIsRead(Constants.UNREAD);
					detail.setMemberId(followid);
					alarmMsgDetailMapper.insert(detail);
				}
				String uuid = UUID.randomUUID().toString().replace("-", "");  
				AlarmMsgDetail detail = new AlarmMsgDetail();
				detail.setCid(uuid);
				detail.setAlarmMsgId(alarmMsgId);
				detail.setIsRead(Constants.UNREAD);
				detail.setMemberId(md.getMemberId());
				alarmMsgDetailMapper.insert(detail);
			}
			Location location = new Location();
			location.setLocationId(sysSequenceService.getSequenceValue(Constants.SequenceName.SECURE_LOCATION));
			location.setImei(imei);
			location.setAddr(addr);
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			if(StringUtils.isNotEmpty(datadate)){
				location.setLocationTime(datadate);
			}else{
				location.setLocationTime(DateUtils.getCurrentTime());
			}
			locationMapper.insert(location);
			requestSucceed(pdataObj, null, "");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	/**
	 * 会员的位置是否超出了电子围栏设置的范围
	 * 
	 * @param latitude
	 * @param longitude
	 * @param locationSetting
	 * @return
	 */
	public boolean isOutOfBounds(Double latitude, Double longitude, SecureLocationSetting locationSetting) {
		if (null == locationSetting) {
			return false;
		}
		Double distance = LocationUtil.GetDistance(latitude, longitude, locationSetting.getLatitude(),
				locationSetting.getLongitude());
		if (distance > locationSetting.getRadius()) {
			return true;
		}
		return false;
	}

	@Override
	public String getAlarmMsgs(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String member_id = bizObj.getString("member_id");
		String follow_id = bizObj.getString("follow_id");
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);
		if(StringUtils.isEmpty(member_id) || StringUtils.isEmpty(follow_id)){
			requestFailed(pdataObj, "缺少参数");
			return toJsonStringWithOutNull(pdataObj);
		}
		try {
			List<AlarmMsgDetail> alarmMsgDetails;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				alarmMsgDetails = alarmMsgDetailMapper.getAlarmMsgsByMemberId(Integer.parseInt(member_id),Integer.parseInt(follow_id));
				PageInfo<AlarmMsgDetail> pageInfo = new PageInfo<>(alarmMsgDetails);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			}else{
				alarmMsgDetails = alarmMsgDetailMapper.getAlarmMsgsByMemberId(Integer.parseInt(member_id),Integer.parseInt(follow_id));
			}
			JSONArray jsonAlarmMsgDetailList = new JSONArray();
			for(AlarmMsgDetail alarmMsgDetail: alarmMsgDetails){
				JSONObject jsonAlarmMsgDetail = new JSONObject();
				jsonAlarmMsgDetail.put("cid", alarmMsgDetail.getCid());
				AlarmMsg alarmMsg = alarmMsgDetail.getAlarmMsg()==null?new AlarmMsg():alarmMsgDetail.getAlarmMsg();
				jsonAlarmMsgDetail.put("alrmType", alarmMsg.getAlarmType());
				jsonAlarmMsgDetail.put("alarmContent", alarmMsg.getAlarmContent());
				jsonAlarmMsgDetail.put("createTime", alarmMsg.getCreateTime());
				jsonAlarmMsgDetail.put("memberId", alarmMsg.getMemberId());
				jsonAlarmMsgDetailList.add(jsonAlarmMsgDetail);
			}
			requestSucceed(pdataObj, jsonAlarmMsgDetailList, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String clearAlarmMsg(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String cid = bizObj.getString("cid");
		if(StringUtils.isEmpty(cid)){
			requestFailed(pdataObj, "缺少参数cid");
			return toJsonStringWithOutNull(pdataObj);
		}
		try {
			alarmMsgDetailMapper.deleteByPrimaryKey(cid);
			requestSucceed(pdataObj, new JSONArray(), "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String clearAllAlarmMsg(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String member_id = bizObj.getString("member_id");
		String follow_id = bizObj.getString("follow_id");
		if(StringUtils.isEmpty(member_id) || StringUtils.isEmpty(follow_id)){
			requestFailed(pdataObj, "缺少参数");
			return toJsonStringWithOutNull(pdataObj);
		}
		try {
			alarmMsgDetailMapper.deleteAll(Integer.valueOf(member_id),Integer.valueOf(follow_id));
			requestSucceed(pdataObj,  new JSONArray(), "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getInfoByTel(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String phoneNo = bizObj.getString("phone_no");
		try {
			Member member = selectByPrimaryTel(phoneNo);
			if (member != null) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "获取成功");
				pdataObj.put(Const.TAG_BIZ, member);
			}else{
				requestFailed(pdataObj,"该会员不存在");
			}
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	private Member selectByPrimaryTel(String phoneNo){
		MemberExample memberExample = new MemberExample();
		memberExample.createCriteria().andPhoneNoEqualTo(phoneNo);
		List<Member> memberList = memberMapper.selectByExample(memberExample);
		if(memberList.size()>0){
			return memberList.get(0);
		}
		return null;
	}

	@Override
	public String getMemberAddress(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		try {
			MemberAddressExample example = new MemberAddressExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			List<MemberAddress> addresses = memberAddressMapper.selectByExample(example);
			requestSucceed(pdataObj, addresses, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getAddressId(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String address = bizObj.getString("address");
		String area = bizObj.getString("area");
		String phoneNum = bizObj.getString("phone_num");
		String memberId = bizObj.getString("member_id");
		String recipient = bizObj.getString("recipient");
		try {
			if(judgeAgumentsIsLegal(pdataObj, address,phoneNum,recipient,memberId)){
				MemberAddressExample example = new MemberAddressExample();
				example.createCriteria().andAddressEqualTo(address).andTelEqualTo(phoneNum).andRecipientEqualTo(recipient).andMemberIdEqualTo(Integer.parseInt(memberId));
				List<MemberAddress> addresses = memberAddressMapper.selectByExample(example);
				String addressId="";
				if(addresses.size()>0){
					addressId = addresses.get(0).getCid()+"";
					pdataObj.put(Const.TAG_BIZ, addressId);
					pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
					pdataObj.put(Const.TAG_RMK, "");
				}else{
					MemberAddress  memberaddress = new MemberAddress();
					//memberaddress.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MEMBER_ADDRESS));
					memberaddress.setAddress(address);
					memberaddress.setArea(area);
					memberaddress.setTel(phoneNum);
					memberaddress.setRecipient(recipient);
					memberaddress.setMemberId(Integer.valueOf(memberId));
					memberaddress.setStatus(0);
					memberAddressMapper.insert(memberaddress);
					pdataObj.put(Const.TAG_BIZ, memberaddress.getCid());
					pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
					pdataObj.put(Const.TAG_RMK, "");
				}
			}else{
				requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			}
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String addMemberAddress(JSONObject pdataObj) {
		String bizStr = pdataObj.getString(Const.TAG_BIZ);
		try {
			MemberAddress address = JSON.parseObject(bizStr, MemberAddress.class);
			address.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MEMBER_ADDRESS));
			address.setTel(address.getPhoneNum());
			memberAddressMapper.insert(address);
			MemberAddressExample example = new MemberAddressExample();
			example.createCriteria().andMemberIdEqualTo(address.getMemberId());
			List<MemberAddress> addresses = memberAddressMapper.selectByExample(example);
			requestSucceed(pdataObj, addresses, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String updateMemberAddress(JSONObject pdataObj) {
		String bizStr = pdataObj.getString(Const.TAG_BIZ);
		try {
			MemberAddress address = JSON.parseObject(bizStr, MemberAddress.class);
			memberAddressMapper.updateByPrimaryKeySelective(address);
			MemberAddressExample example = new MemberAddressExample();
			example.createCriteria().andMemberIdEqualTo(address.getMemberId());
			List<MemberAddress> addresses = memberAddressMapper.selectByExample(example);
			requestSucceed(pdataObj, addresses, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String deleteMemberAddress(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String addressId = bizObj.getString("address_id");
		String memberId = bizObj.getString("member_id");
		try {
			memberAddressMapper.deleteByPrimaryKey(Integer.parseInt(addressId));
			MemberAddressExample example = new MemberAddressExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			List<MemberAddress> addresses = memberAddressMapper.selectByExample(example);
			requestSucceed(pdataObj, addresses, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public String setDefaultAddress(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String addressId = bizObj.getString("address_id");
		String memberId = bizObj.getString("member_id");
		try {
			MemberAddress address = memberAddressMapper.selectByPrimaryKey(Integer.parseInt(addressId));
			MemberAddress defaultAddress = memberAddressMapper.getDefaultAddress(Integer.parseInt(memberId));
			address.setIsDefault(Constants.YES);
			defaultAddress.setIsDefault(Constants.NO);
			// 设置新的默认地址，并取消原默认地址
			memberAddressMapper.updateByPrimaryKeySelective(address);
			memberAddressMapper.updateByPrimaryKeySelective(defaultAddress);
			
			MemberAddressExample example = new MemberAddressExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			List<MemberAddress> addresses = memberAddressMapper.selectByExample(example);
			requestSucceed(pdataObj, addresses, "");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getMemberDefaultAddress(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		try {
			if(judgeAgumentsIsLegal(pdataObj, memberId)){
				MemberAddressExample example = new MemberAddressExample();
				example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId)).andIsDefaultEqualTo(1).andStatusEqualTo(0);
				List<MemberAddress> addresses = memberAddressMapper.selectByExample(example);
				if(null!=addresses&&addresses.size()>0){
					requestSucceed(pdataObj, addresses.get(0), "");
				}
			}else{
				requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			}
			
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
}
