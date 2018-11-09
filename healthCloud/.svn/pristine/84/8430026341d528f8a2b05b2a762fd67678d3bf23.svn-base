package com.yhcrt.healthcloud.health.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.health.entity.HdBloodGlucose;
import com.yhcrt.healthcloud.health.entity.HdBloodGlucoseExample;
import com.yhcrt.healthcloud.health.entity.HdBloodPressure;
import com.yhcrt.healthcloud.health.entity.HdBloodPressureExample;
import com.yhcrt.healthcloud.health.entity.HdPulse;
import com.yhcrt.healthcloud.health.entity.HdPulseExample;
import com.yhcrt.healthcloud.health.entity.HdSleep;
import com.yhcrt.healthcloud.health.entity.HdSleepExample;
import com.yhcrt.healthcloud.health.entity.HdStep;
import com.yhcrt.healthcloud.health.entity.HdStepExample;
import com.yhcrt.healthcloud.health.mapper.HdBloodGlucoseMapper;
import com.yhcrt.healthcloud.health.mapper.HdBloodPressureMapper;
import com.yhcrt.healthcloud.health.mapper.HdPulseMapper;
import com.yhcrt.healthcloud.health.mapper.HdSleepMapper;
import com.yhcrt.healthcloud.health.mapper.HdStepMapper;
import com.yhcrt.healthcloud.health.service.HealthDataService;

@Service
public class HealthDataServiceImpl implements HealthDataService {

	// mapper
	@Autowired
	private HdStepMapper hdStepMapper;
	@Autowired
	private HdPulseMapper hdPulseMapper;
	@Autowired
	private HdBloodPressureMapper hdPressureMapper;
	@Autowired
	private HdBloodGlucoseMapper hdGlucoseMapper;
	@Autowired
	private HdSleepMapper hdSleepMapper;

	/*----------glucose------------*/
	@Override
	public HdBloodGlucose getGlucoseByCid(int cid) {
		return hdGlucoseMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<HdBloodGlucose> getGlucoseByMemberId(int memberId) {
		HdBloodGlucoseExample example = new HdBloodGlucoseExample();
		example.setOrderByClause("cid desc");
		example.createCriteria().andMemberIdEqualTo(memberId);
		return hdGlucoseMapper.selectByExample(example);
	}

	@Override
	public List<HdBloodGlucose> getGlucoseByBgType(int bgType) {
		HdBloodGlucoseExample example = new HdBloodGlucoseExample();
		example.createCriteria().andBgTypeEqualTo(bgType);
		example.setOrderByClause("cid desc");
		return hdGlucoseMapper.selectByExample(example);
	}

	@Override
	public List<HdBloodGlucose> getGlucoseByImei(String imei) {
		HdBloodGlucoseExample example = new HdBloodGlucoseExample();
		example.createCriteria().andImeiEqualTo(imei);
		example.setOrderByClause("cid desc");
		return hdGlucoseMapper.selectByExample(example);
	}

	@Override
	public List<HdBloodGlucose> getGlucoseByTimeAndMemberId(String startTime, String endTime, int memberId) {
		HdBloodGlucoseExample example = new HdBloodGlucoseExample();
		example.createCriteria().andUploadTimeBetween(startTime, endTime).andMemberIdEqualTo(memberId);
		example.setOrderByClause("upload_time asc");
		return hdGlucoseMapper.selectByExample(example);
	}

	@Override
	public HdBloodGlucose getGlucoseLatest(int memberId) {
		return hdGlucoseMapper.selectLatestByMemberId(memberId);
	}

	/*-------------pressure-------------*/
	@Override
	public HdBloodPressure getPressureByCid(int cid) {
		return hdPressureMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<HdBloodPressure> getPressureByMemberId(int memberId) {
		HdBloodPressureExample example = new HdBloodPressureExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		example.setOrderByClause("cid desc");
		return hdPressureMapper.selectByExample(example);
	}

	@Override
	public List<HdBloodPressure> getPressureByImei(String imei) {
		HdBloodPressureExample example = new HdBloodPressureExample();
		example.createCriteria().andImeiEqualTo(imei);
		example.setOrderByClause("cid desc");
		return hdPressureMapper.selectByExample(example);
	}

	@Override
	public List<HdBloodPressure> getPressureByTimeAndMemberId(String startTime, String endTime, int memberId) {
		HdBloodPressureExample example = new HdBloodPressureExample();
		example.createCriteria().andUploadTimeBetween(startTime, endTime).andMemberIdEqualTo(memberId);
		example.setOrderByClause("upload_time asc");
		return hdPressureMapper.selectByExample(example);
	}

	@Override
	public HdBloodPressure getPressureLatest(int memberId) {
		return hdPressureMapper.selectLatestByMemberId(memberId);
	}

	/*---------------pulse----------------*/
	@Override
	public HdPulse getPulseByCid(int cid) {
		return hdPulseMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<HdPulse> getPulseByMemberId(int memberId) {
		HdPulseExample example = new HdPulseExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		example.setOrderByClause("cid desc");
		return hdPulseMapper.selectByExample(example);
	}

	@Override
	public List<HdPulse> getPulseByImei(String imei) {
		HdPulseExample example = new HdPulseExample();
		example.createCriteria().andImeiEqualTo(imei);
		example.setOrderByClause("cid desc");
		return hdPulseMapper.selectByExample(example);
	}

	@Override
	public List<HdPulse> getPulseByTimeAndMemberId(String startTime, String endTime, int memberId) {
		HdPulseExample example = new HdPulseExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andUploadTimeBetween(startTime, endTime);
		example.setOrderByClause("upload_time asc");
		return hdPulseMapper.selectByExample(example);
	}

	@Override
	public HdPulse getPulseLatest(int memberId) {
		return hdPulseMapper.selectLatestByMemberId(memberId);
	}

	/*---------------sleep----------------*/
	@Override
	public HdSleep getSleepByCid(int cid) {
		return hdSleepMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<HdSleep> getSleepByMemberId(int memberId) {
		HdSleepExample example = new HdSleepExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		example.setOrderByClause("cid desc");
		return hdSleepMapper.selectByExample(example);
	}

	@Override
	public List<HdSleep> getSleepByImei(String imei) {
		HdSleepExample example = new HdSleepExample();
		example.createCriteria().andImeiEqualTo(imei);
		example.setOrderByClause("cid desc");
		return hdSleepMapper.selectByExample(example);
	}

	@Override
	public List<HdSleep> getSleepByTimeAndMemberId(String startTime, String endTime, int memberId, List<String> dateList) {
		HdSleepExample example = new HdSleepExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andUploadTimeBetween(startTime, endTime);
		example.setOrderByClause("cid desc");
		List<HdSleep> dataList = hdSleepMapper.selectHdSleep(example);
		Map<String, HdSleep> dataDay = new HashMap<String, HdSleep>();
		for (HdSleep hs : dataList) {
			String time = hs.getUploadTime();
			// 第一天
			if (!dataDay.containsKey("day0") && dateList.get(0).contains(time)) {
				dataDay.put("day0", hs);
			}
			// 第二天
			if (!dataDay.containsKey("day1") && dateList.get(1).contains(time)) {
				dataDay.put("day1", hs);
			}
			// 第三天
			if (!dataDay.containsKey("day2") && dateList.get(2).contains(time)) {
				dataDay.put("day2", hs);
			}
			// 第四天
			if (!dataDay.containsKey("day3") && dateList.get(3).contains(time)) {
				dataDay.put("day3", hs);
			}
			// 第五天
			if (!dataDay.containsKey("day4") && dateList.get(4).contains(time)) {
				dataDay.put("day4", hs);
			}
			// 第六天
			if (!dataDay.containsKey("day5") && dateList.get(5).contains(time)) {
				dataDay.put("day5", hs);
			}
			// 第七天
			if (!dataDay.containsKey("day6") && dateList.get(6).contains(time)) {
				dataDay.put("day6", hs);
			}
			// 第八天
			if (!dataDay.containsKey("day7") && dateList.get(7).contains(time)) {
				dataDay.put("day7", hs);
			}
			// 第九天
			if (!dataDay.containsKey("day8") && dateList.get(8).contains(time)) {
				dataDay.put("day8", hs);
			}
			// 第十天
			if (!dataDay.containsKey("day9") && dateList.get(9).contains(time)) {
				dataDay.put("day9", hs);
			}
			// 第十一天
			if (!dataDay.containsKey("day10") && dateList.get(10).contains(time)) {
				dataDay.put("day10", hs);
			}
			// 第十二天
			if (!dataDay.containsKey("day11") && dateList.get(11).contains(time)) {
				dataDay.put("day11", hs);
			}
			// 第十三天
			if (!dataDay.containsKey("day12") && dateList.get(12).contains(time)) {
				dataDay.put("day12", hs);
			}
			// 第十四天
			if (!dataDay.containsKey("day13") && dateList.get(13).contains(time)) {
				dataDay.put("day13", hs);
			}
			// 第十五天
			if (!dataDay.containsKey("day14") && dateList.get(14).contains(time)) {
				dataDay.put("day14", hs);
			}
			
		}

		// 封装数据
		List<HdSleep> list = new ArrayList<HdSleep>();
		for (int i=dateList.size() -1; i >= 0; i--) {
			HdSleep hdSleep = dataDay.get("day" + i);
			if (hdSleep == null) {
				hdSleep = new HdSleep();
				hdSleep.setUploadTime(dateList.get(i).substring(5));
				hdSleep.setDeepSleepDuration(0.0);
			}
			list.add(hdSleep);
		}
		return list;
	}

	@Override
	public HdSleep getSleepLatest(int memberId) {
		return hdSleepMapper.selectLatestByMemberId(memberId);
	}

	/*---------------step----------------*/
	@Override
	public HdStep getStepByCid(int cid) {
		return hdStepMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<HdStep> getStepByMemberId(int memberId) {
		HdStepExample example = new HdStepExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		example.setOrderByClause("cid desc");
		return hdStepMapper.selectByExample(example);
	}

	@Override
	public List<HdStep> getStepByImei(String imei) {
		HdStepExample example = new HdStepExample();
		example.createCriteria().andImeiEqualTo(imei);
		example.setOrderByClause("cid desc");
		return hdStepMapper.selectByExample(example);
	}

	// 统计最近15天数据
	@Override
	public List<HdStep> getStepByTimeAndMemberId(String startTime, String endTime, int memberId, List<String> dateList) {
		HdStepExample example = new HdStepExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andUploadTimeBetween(startTime, endTime);
		example.setOrderByClause("cid desc");
		List<HdStep> dataList = hdStepMapper.selectCountStep(example);
		
		Map<String, HdStep> dataDay = new HashMap<String, HdStep>();
		for (HdStep hs : dataList) {
			String time = hs.getUploadTime();
			// 第一天
			if (!dataDay.containsKey("day0") && dateList.get(0).contains(time)) {
				dataDay.put("day0", hs);
			}
			// 第二天
			if (!dataDay.containsKey("day1") && dateList.get(1).contains(time)) {
				dataDay.put("day1", hs);
			}
			// 第三天
			if (!dataDay.containsKey("day2") && dateList.get(2).contains(time)) {
				dataDay.put("day2", hs);
			}
			// 第四天
			if (!dataDay.containsKey("day3") && dateList.get(3).contains(time)) {
				dataDay.put("day3", hs);
			}
			// 第五天
			if (!dataDay.containsKey("day4") && dateList.get(4).contains(time)) {
				dataDay.put("day4", hs);
			}
			// 第六天
			if (!dataDay.containsKey("day5") && dateList.get(5).contains(time)) {
				dataDay.put("day5", hs);
			}
			// 第七天
			if (!dataDay.containsKey("day6") && dateList.get(6).contains(time)) {
				dataDay.put("day6", hs);
			}
			// 第八天
			if (!dataDay.containsKey("day7") && dateList.get(7).contains(time)) {
				dataDay.put("day7", hs);
			}
			// 第九天
			if (!dataDay.containsKey("day8") && dateList.get(8).contains(time)) {
				dataDay.put("day8", hs);
			}
			// 第十天
			if (!dataDay.containsKey("day9") && dateList.get(9).contains(time)) {
				dataDay.put("day9", hs);
			}
			// 第十一天
			if (!dataDay.containsKey("day10") && dateList.get(10).contains(time)) {
				dataDay.put("day10", hs);
			}
			// 第十二天
			if (!dataDay.containsKey("day11") && dateList.get(11).contains(time)) {
				dataDay.put("day11", hs);
			}
			// 第十三天
			if (!dataDay.containsKey("day12") && dateList.get(12).contains(time)) {
				dataDay.put("day12", hs);
			}
			// 第十四天
			if (!dataDay.containsKey("day13") && dateList.get(13).contains(time)) {
				dataDay.put("day13", hs);
			}
			// 第十五天
			if (!dataDay.containsKey("day14") && dateList.get(14).contains(time)) {
				dataDay.put("day14", hs);
			}
		}

		// 封装数据
		List<HdStep> list = new ArrayList<HdStep>();
		for (int i=dateList.size() -1; i >= 0; i--) {
			HdStep hdStep = dataDay.get("day" + i);
			if (hdStep == null) {
				hdStep = new HdStep();
				hdStep.setUploadTime(dateList.get(i).substring(5));
				hdStep.setStepCount(0);
			}
			list.add(hdStep);
		}
		return list;
	}

	@Override
	public HdStep getStepLatest(int memberId) {
		return hdStepMapper.selectLatestByMemberId(memberId);
	}

	@Override
	public List<HdBloodGlucose> selectByMon(Integer memberId) {
		return hdGlucoseMapper.selectByMon(memberId);
	}

	@Override
	public List<HdBloodPressure> selectByMonHp(Integer memberId) {
		return hdPressureMapper.selectByMon(memberId);
	}

	@Override
	public List<HdPulse> selectByMonP(Integer memberId) {
		return hdPulseMapper.selectByMon(memberId);
	}

	@Override
	public List<HdSleep> selectByMonSl(Integer memberId) {
		return hdSleepMapper.selectByMon(memberId);
	}

	@Override
	public List<HdStep> selectByMonS(Integer memberId) {
		return hdStepMapper.selectByMon(memberId);
	}
}
