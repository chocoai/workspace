package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.AlarmDefine;
import com.yhcrt.iHealthCloud.entity.AlarmMsg;
import com.yhcrt.iHealthCloud.entity.AlarmMsgDetail;
import com.yhcrt.iHealthCloud.entity.HdPulse;
import com.yhcrt.iHealthCloud.entity.HdPulseExample;
import com.yhcrt.iHealthCloud.entity.HeartRateSetting;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.entity.MemberRelationship;
import com.yhcrt.iHealthCloud.mapper.AlarmDefineMapper;
import com.yhcrt.iHealthCloud.mapper.AlarmMsgDetailMapper;
import com.yhcrt.iHealthCloud.mapper.AlarmMsgMapper;
import com.yhcrt.iHealthCloud.mapper.HdPulseMapper;
import com.yhcrt.iHealthCloud.mapper.HeartRateSettingMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.MemberRelationshipMapper;
import com.yhcrt.iHealthCloud.pojo.HdPulseDTO;
import com.yhcrt.iHealthCloud.service.HealthDataPulseService;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;
import com.yhcrt.iHealthCloud.util.DateUtils;

/**
 * 
 * @author huzelin
 *
 */
@Service
public class HealthDataPulseServiceImpl extends BaseService implements HealthDataPulseService {

	// debug mode
	protected boolean isDebug = true;

	@Autowired
	private HdPulseMapper pulseMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private HeartRateSettingMapper heartRateSettingMapper;
	@Autowired
	private AlarmDefineMapper alarmDefineMapper;
	@Autowired
	private AlarmMsgMapper alarmMsgMapper;
	@Autowired
	private AlarmMsgDetailMapper alarmMsgDetailMapper;
	@Autowired
	private SysSequenceService sequenceService;
	@Autowired
	private MemberDeviceMapper memberDeviceMapper;
	@Autowired
	private MemberRelationshipMapper memberRelationshipMapper;

	@Override
	public String getPulseByTime(JSONObject pdataObj) {

		// 获取查询时间条件
		String memberId = getMemberId(pdataObj);
		String startTime = getStartTime(pdataObj);
		String endTime = getEndTime(pdataObj);
		endTime = endTime + " 23:59:59";
		// 对数据进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId, startTime, endTime)) {
			// 获取最新的心率数据
			HdPulseDTO latestPulse = getLatestPulse(memberId);
			// 根据时间条件进行查询
			List<HdPulseDTO> list = pulseMapper.getHdPulseByTime(memberId, startTime, endTime);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("latestPulse", latestPulse);
			jsonObj.put("pulses", list);
			pdataObj.put(Const.TAG_BIZ, jsonObj);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	public HdPulseDTO getLatestPulse(String memberId) {
		HdPulseExample example = new HdPulseExample();
		example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
		example.setOrderByClause("data_date desc");
		PageHelper.startPage(1, 1);
		List<HdPulse> list = pulseMapper.selectByExample(example);
		HdPulse hdPulse = list.size()>0 ? list.get(0) : new HdPulse();
		HdPulseDTO hdPulseDTO = new HdPulseDTO();
		BeanUtils.copyProperties(hdPulse, hdPulseDTO);
		return hdPulseDTO;
	}

	@Override
	public String getPulseByMemberId(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);

		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId)) {

			List<HdPulse> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {

				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectPulseListByMemberId(memberId);
				PageInfo<HdPulse> p = new PageInfo<>(list);
				setPagingData(pdataObj, p.getPages(), p.getPageNum());

			} else {
				list = selectPulseListByMemberId(memberId);
			}

			requestSucceed(pdataObj, list, "");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getLatestPulse(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		try {
			HdPulseExample example = new HdPulseExample();
			example.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId));
			example.setOrderByClause("upload_time desc");
			List<HdPulse> pulses = pulseMapper.selectByExample(example);
			String pulse = pulses.isEmpty() ? "" : pulses.get(0).getPulse().toString();
			requestSucceed(pdataObj, pulse, "");
		} catch (Exception e) {
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String insertHeartRate(JSONObject pdataObj) {

		// 获取参数
		String imei = getImei(pdataObj);
		String dataDate = getDataDate(pdataObj);
		if(StringUtils.isEmpty(dataDate)){
			dataDate = DateUtil.getDateTime();
		}
		String memberId = getMemberId(pdataObj);
		if (StringUtils.isEmpty(memberId)) {
			MemberDevice memberDevice = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
			if (memberDevice != null) {
				memberId = memberDevice.getMemberId() + "";
			}
		}
		String pluse = getPluse(pdataObj);

		// 对取出的值进行非空判断
		if (StringUtils.isBlank(pluse) ) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		}

		if (StringUtils.isNotBlank(memberId)) {
			// 查询会员的心率安全范围
			HeartRateSetting heartRateSetting = heartRateSettingMapper.selectByImei(imei);
			Member member = memberMapper.selectByPrimaryKey(Integer.parseInt(memberId));
			if (member == null) {
				requestFailed(pdataObj, Constants.ExceptionMsg.MEMBER_NOT_FOUND);
				return toJsonStringWithOutNull(pdataObj);
			}
			if (heartRateSetting != null) {
				// 会员的血压是否超出了血压安全范围设置的范围,若超出将产生预警消息
				if (Integer.parseInt(pluse) > heartRateSetting.getHighHeartRate()
						|| Integer.parseInt(pluse) < heartRateSetting.getLowHeartRate()) {
					AlarmDefine alarmDefine = alarmDefineMapper.selectByAlarmType(Constants.AlarmType.HEARTRATE);
					AlarmMsg alarmMsg = new AlarmMsg();
					Integer alarmMsgId = sequenceService.getSequenceValue(Constants.SequenceName.ALARM_MSG);
					alarmMsg.setCid(alarmMsgId);
					alarmMsg.setMemberId(Integer.parseInt(memberId));
					alarmMsg.setAlarmType(Constants.AlarmType.HEARTRATE);
					if (alarmDefine != null) {
						String alarmContent = alarmDefine.getAlarmMsg().replaceFirst("\\{}", member.getRealName())
								.replaceFirst("\\{}", pluse);
						alarmMsg.setAlarmContent(alarmContent);
					}
					alarmMsg.setStatus(Constants.STATUS_NORMAL);
					alarmMsg.setImei(imei);
					alarmMsg.setIsRead(Constants.UNREAD);
					alarmMsg.setCreateTime(DateUtils.getCurrentTime());
					alarmMsgMapper.insert(alarmMsg);
					List<MemberRelationship> memberRelationshipList = memberRelationshipMapper.selectMemRelaF(Integer.parseInt(memberId));
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
					detail.setMemberId(member.getMemberId());
					alarmMsgDetailMapper.insert(detail);
				}
			}
		}
		// 向数据库中插入数据
		HdPulse pulse = new HdPulse();
		String uploadTime = DateUtil.getDateTime();
		pulse.setImei(imei);
		if (StringUtils.isNotBlank(memberId)) {
			pulse.setMemberId(Integer.parseInt(memberId));
		}
		pulse.setUploadTime(uploadTime);
		pulse.setDataDate(dataDate);
		pulse.setPulse(Integer.valueOf(pluse));

		// 判断数据插入是否成功
		int insert = pulseMapper.insert(pulse);
		if (insert > 0) {
			requestSucceed(pdataObj, pulse, "");
		} else {
			requestFailed(pdataObj, "insert failed");
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	private List<HdPulse> selectPulseListByMemberId(String memberId) {
		List<HdPulse> list;
		HdPulseExample example = new HdPulseExample();
		example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
		example.setOrderByClause("cid desc");
		list = pulseMapper.selectByExample(example);
		return list;
	}

	@Override
	public String getPulseForAWeekByMemberId(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);

		int totalSize = 0; // 数据总数目
		int valueTotal = 0; // 数据总和
		int valueMax = 0; // 数据最大值
		int valueMin = 0; // 数据最小值
		int valueAve = 0; // 数据平均值

		if (judgeAgumentsIsLegal(pdataObj, memberId)) {
			// get time
			List<String> dateList = getDateString4week();

			// 向数据库中请求近7天的所有数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", Integer.parseInt(memberId));
			map.put("startTime", dateList.get(dateList.size() - 1));
			map.put("endTime", dateList.get(0));
			List<HdPulse> dataList = pulseMapper.selectHdPulse(map);
			if(!dataList.isEmpty()){
				totalSize = dataList.size();
			}

			// 筛选7天中 每天的最近一条数据
			Map<String, HdPulse> dataMap4Week = new HashMap<>();
			for (HdPulse hdPulse : dataList) {
				String time = hdPulse.getUploadTime();
				int value = hdPulse.getPulse();

				// 获取总值、最大值、最小值
				valueTotal += value;
				if (valueMin == 0)
					valueMin = value;
				if (value > valueMax)
					valueMax = value;
				if (value < valueMin)
					valueMin = value;

				// 第一天
				if (!dataMap4Week.containsKey("day0") && dateList.get(0).contains(time)) {
					dataMap4Week.put("day0", hdPulse);
				}

				// 第二天
				if (!dataMap4Week.containsKey("day1") && dateList.get(1).contains(time)) {
					dataMap4Week.put("day1", hdPulse);
				}

				// 第三天
				if (!dataMap4Week.containsKey("day2") && dateList.get(2).contains(time)) {
					dataMap4Week.put("day2", hdPulse);
				}

				// 第四天
				if (!dataMap4Week.containsKey("day3") && dateList.get(3).contains(time)) {
					dataMap4Week.put("day3", hdPulse);
				}

				// 第五天
				if (!dataMap4Week.containsKey("day4") && dateList.get(4).contains(time)) {
					dataMap4Week.put("day4", hdPulse);
				}

				// 第六天
				if (!dataMap4Week.containsKey("day5") && dateList.get(5).contains(time)) {
					dataMap4Week.put("day5", hdPulse);
				}

				// 第七天
				if (!dataMap4Week.containsKey("day6") && dateList.get(6).contains(time)) {
					dataMap4Week.put("day6", hdPulse);
				}
			}

			// 封装数据
			List<HdPulse> data4Week = new ArrayList<>();
			for (int i = dateList.size()-1; i >=0 ; i--) {
				HdPulse hdPulse = dataMap4Week.get("day" + i);
				if (hdPulse == null) {
					hdPulse = new HdPulse();
					hdPulse.setUploadTime(dateList.get(i).substring(5));
				}
				data4Week.add(hdPulse);
			}

			// 取平均值
			if (totalSize == 0)
				totalSize = 1;
			valueAve = valueTotal / totalSize;

			requestSucceed(pdataObj, data4Week, "");
			putMaxMinAveValueToBizObj(pdataObj, valueMax, valueMin, valueAve);

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public List<HdPulse> getPulseForAWeekByMemberId(String memberId) {
		HdPulseExample example = new HdPulseExample();
		example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
		example.setOrderByClause("cid desc");
		return pulseMapper.selectByExample(example);
	}
}
