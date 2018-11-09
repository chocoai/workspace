package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
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
import com.yhcrt.iHealthCloud.entity.BloodPressureSetting;
import com.yhcrt.iHealthCloud.entity.HdBloodPressure;
import com.yhcrt.iHealthCloud.entity.HdBloodPressureExample;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.entity.MemberRelationship;
import com.yhcrt.iHealthCloud.mapper.AlarmDefineMapper;
import com.yhcrt.iHealthCloud.mapper.AlarmMsgDetailMapper;
import com.yhcrt.iHealthCloud.mapper.AlarmMsgMapper;
import com.yhcrt.iHealthCloud.mapper.BloodPressureSettingMapper;
import com.yhcrt.iHealthCloud.mapper.HdBloodPressureMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.MemberRelationshipMapper;
import com.yhcrt.iHealthCloud.pojo.BloodPressureDTO;
import com.yhcrt.iHealthCloud.service.HealthDataBloodPressureService;
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
public class HealthDataBloodPressureServiceImpl extends BaseService implements HealthDataBloodPressureService {

	// debug mode
	protected boolean isDebug = true;

	@Autowired
	private HdBloodPressureMapper pressureMapper;
	@Autowired
	private BloodPressureSettingMapper bloodPressureSettingMapper;
	@Autowired
	private MemberDeviceMapper memberDeviceMapper;
	@Autowired
	private AlarmDefineMapper alarmDefineMapper;
	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private AlarmMsgMapper alarmMsgMapper;
	@Autowired
	private AlarmMsgDetailMapper alarmMsgDetailMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberRelationshipMapper memberRelationshipMapper;

	@Override
	public String getPressureByTime(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);
		String startTime = getStartTime(pdataObj);
		String endTime = getEndTime(pdataObj);
		endTime = endTime + " 23:59:59";
		// 对数据进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId, startTime, endTime)) {
			// 获取最新的心率数据
			BloodPressureDTO latestBloodPressure = pressureMapper.getLatestBloodPressure(memberId);
			latestBloodPressure = latestBloodPressure == null ? new BloodPressureDTO() : latestBloodPressure;
			// 根据时间条件进行查询
			List<BloodPressureDTO> bloodPressures = pressureMapper.listBloodPressureByTime(memberId, startTime, endTime);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("latestBloodPressure", latestBloodPressure);
			jsonObj.put("bloodPressures", bloodPressures);
			pdataObj.put(Const.TAG_BIZ, jsonObj);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}
	
	/*private List<HdBloodPressure> selectPressureByTime(String memberId, String startTime, String endTime) {
		HdBloodPressureExample example = new HdBloodPressureExample();
		example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId)).andUploadTimeBetween(startTime,
				endTime);
		example.setOrderByClause("cid desc");
		List<HdBloodPressure> list = pressureMapper.selectByExample(example);
		return list;
	}*/

	@Override
	public String getPressureByMemberId(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);

		// 判断参数是否为空
		if (judgeAgumentsIsLegal(pdataObj, memberId)) {

			// 向数据库请求数据并判断是否分页
			List<HdBloodPressure> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {

				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectPressureListByMemberId(memberId);
				PageInfo<HdBloodPressure> p = new PageInfo<>(list);
				setPagingData(pdataObj, p.getPages(), p.getPageNum());

			} else {
				list = selectPressureListByMemberId(memberId);
			}

			requestSucceed(pdataObj, list, "");

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	private List<HdBloodPressure> selectPressureListByMemberId(String memberId) {
		List<HdBloodPressure> list;
		HdBloodPressureExample example = new HdBloodPressureExample();
		example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
		example.setOrderByClause("cid desc");
		list = pressureMapper.selectByExample(example);
		return list;
	}

	@Override
	public String insertPressure(JSONObject pdataObj) {
		// 获取biz json对象取出其中的值
		String imei = getImei(pdataObj);
		String dataDate = getDataDate(pdataObj);
		if(StringUtils.isEmpty(dataDate)){
			dataDate = DateUtil.getDateTime();
		}
		String memberId = getMemberId(pdataObj);
		if (StringUtils.isEmpty(memberId)) {
			MemberDevice memberDevice = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
			memberId = memberDevice != null ? memberDevice.getMemberId().toString() : memberId;
		}
		String dbp = getDbp(pdataObj);
		String sbp = getSbp(pdataObj);
		// 对取出的值进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, dbp, sbp)) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		}
		
		if (StringUtils.isNoneBlank(memberId)) {
			// 查询会员的血压安全范围
			BloodPressureSetting bloodPressureSetting = bloodPressureSettingMapper.selectByMemberId(Integer.parseInt(memberId));
			Member member = memberMapper.selectByPrimaryKey(Integer.parseInt(memberId));
			if (member == null) {
				requestFailed(pdataObj, Constants.ExceptionMsg.MEMBER_NOT_FOUND);
				return toJsonStringWithOutNull(pdataObj);
			}
			if (bloodPressureSetting != null) {
				// 会员的血压是否超出了血压安全范围设置的范围,若超出将产生预警消息
				if (Integer.parseInt(dbp) > bloodPressureSetting.getDbpMax()
						|| Integer.parseInt(dbp) < bloodPressureSetting.getDbpMin()
						|| Integer.parseInt(sbp) > bloodPressureSetting.getSbpMax()
						|| Integer.parseInt(sbp) < bloodPressureSetting.getSbpMin()) {
					AlarmDefine alarmDefine = alarmDefineMapper.selectByAlarmType(Constants.AlarmType.BLOODPRESSURE);
					AlarmMsg alarmMsg = new AlarmMsg();
					Integer alarmMsgId = sysSequenceService.getSequenceValue(Constants.SequenceName.ALARM_MSG);
					alarmMsg.setCid(alarmMsgId);
					alarmMsg.setMemberId(Integer.parseInt(memberId));
					alarmMsg.setAlarmType(Constants.AlarmType.BLOODPRESSURE);
					if (alarmDefine != null) {
						String alarmContent = alarmDefine.getAlarmMsg().replaceFirst("\\{}", member.getRealName())
								.replaceFirst("\\{}", "(" + dbp + "," + sbp + ")");
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
		HdBloodPressure pressure = new HdBloodPressure();
		pressure.setImei(imei);
		if (StringUtils.isNotBlank(memberId)) {
			pressure.setMemberId(Integer.parseInt(memberId));
		}
		pressure.setDataDate(dataDate);
		pressure.setUploadTime(DateUtil.getDateTime());
		pressure.setDbp(Integer.parseInt(dbp));
		pressure.setSbp(Integer.parseInt(sbp));
		int insert = pressureMapper.insert(pressure);

		// 判断数据插入是否成功
		if (insert > 0) {
			requestSucceed(pdataObj, pressure, "");
		} else {
			requestFailed(pdataObj, "insert failed");
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getPressureForAWeekByMemberId(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);

		int totalSize = 0; // 数据总数目

		int sbpTotal = 0; // 收缩压数据总和
		int sbpMax = 0; // 收缩压(最大值)
		int sbpMin = 0; // 收缩压(最小值)
		int sbpAve = 0; // 收缩压(平均值)

		int dbpTotal = 0; // 舒张压数据总和
		int dbpMax = 0; // 舒张压(最大值)
		int dbpMin = 0; // 舒张压(最小值)
		int dbpAve = 0; // 舒张压(平均值)

		if (judgeAgumentsIsLegal(pdataObj, memberId)) {

			// get time
			List<String> dateList = getDateString4week();
			// 向数据库中请求近7天的所有数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", Integer.parseInt(memberId));
			map.put("startTime", dateList.get(dateList.size() - 1));
			map.put("endTime", dateList.get(0));
			List<HdBloodPressure> dataList = pressureMapper.selectHdBloodPressure(map);
			if(!dataList.isEmpty()){
				totalSize = dataList.size();
			}

			// 筛选7天中 每天的最近一条数据
			Map<String, HdBloodPressure> dataMap4Week = new HashMap<>();
			for (HdBloodPressure hdBloodPressure : dataList) {
				String time = hdBloodPressure.getUploadTime();
				int sbp = hdBloodPressure.getSbp();
				int dbp = hdBloodPressure.getDbp();

				// 获取总值、最大值、最小值
				sbpTotal += sbp;
				if (sbpMin == 0)
					sbpMin = sbp;
				if (sbp > sbpMax)
					sbpMax = sbp;
				if (sbp < sbpMin)
					sbpMin = sbp;
				dbpTotal += dbp;
				if (dbpMin == 0)
					dbpMin = dbp;
				if (dbp > dbpMax)
					dbpMax = dbp;
				if (dbp < dbpMin)
					dbpMin = dbp;

				// 第一天
				if (!dataMap4Week.containsKey("day0") && dateList.get(0).contains(time)) {
					dataMap4Week.put("day0", hdBloodPressure);
				}

				// 第二天
				if (!dataMap4Week.containsKey("day1") && dateList.get(1).contains(time)) {
					dataMap4Week.put("day1", hdBloodPressure);
				}

				// 第三天
				if (!dataMap4Week.containsKey("day2") && dateList.get(2).contains(time)) {
					dataMap4Week.put("day2", hdBloodPressure);
				}

				// 第四天
				if (!dataMap4Week.containsKey("day3") && dateList.get(3).contains(time)) {
					dataMap4Week.put("day3", hdBloodPressure);
				}

				// 第五天
				if (!dataMap4Week.containsKey("day4") && dateList.get(4).contains(time)) {
					dataMap4Week.put("day4", hdBloodPressure);
				}

				// 第六天
				if (!dataMap4Week.containsKey("day5") && dateList.get(5).contains(time)) {
					dataMap4Week.put("day5", hdBloodPressure);
				}

				// 第七天
				if (!dataMap4Week.containsKey("day6") && dateList.get(6).contains(time)) {
					dataMap4Week.put("day6", hdBloodPressure);
				}
			}

			// 封装数据
			List<HdBloodPressure> data4Week = new ArrayList<>();
			for (int i = dateList.size()-1; i >=0 ; i--) {
				HdBloodPressure hdBloodPressure = dataMap4Week.get("day" + i);
				if (hdBloodPressure == null) {
					hdBloodPressure = new HdBloodPressure();
					hdBloodPressure.setUploadTime(dateList.get(i).substring(5));
				}
				data4Week.add(hdBloodPressure);
			}

			// 取平均值
			if (totalSize == 0)
				totalSize = 1;
			sbpAve = sbpTotal / totalSize;
			dbpAve = dbpTotal / totalSize;

			requestSucceed(pdataObj, data4Week, "");
			getBizObj(pdataObj).put("sbpMax", sbpMax);
			getBizObj(pdataObj).put("sbpMin", sbpMin);
			getBizObj(pdataObj).put("sbpAve", sbpAve);
			getBizObj(pdataObj).put("dbpMax", dbpMax);
			getBizObj(pdataObj).put("dbpMin", dbpMin);
			getBizObj(pdataObj).put("dbpAve", dbpAve);

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public List<HdBloodPressure> getPressureForAWeekByMemberId(String memberId) {
		HdBloodPressureExample example = new HdBloodPressureExample();
		example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
		example.setOrderByClause("cid desc");
		return pressureMapper.selectByExample(example);
	}

}