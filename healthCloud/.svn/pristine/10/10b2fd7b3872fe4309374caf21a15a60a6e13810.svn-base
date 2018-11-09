package com.yhcrt.healthcloud.health.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yhcrt.healthcloud.health.entity.HdBloodGlucose;
import com.yhcrt.healthcloud.health.entity.HdBloodPressure;
import com.yhcrt.healthcloud.health.entity.HdPulse;
import com.yhcrt.healthcloud.health.entity.HdSleep;
import com.yhcrt.healthcloud.health.entity.HdStep;
import com.yhcrt.healthcloud.health.service.HealthDataService;
import com.yhcrt.healthcloud.health.vo.BloodGlucoseAndType;
import com.yhcrt.healthcloud.system.entity.SysDictionary;
import com.yhcrt.healthcloud.system.entity.SysDictionaryExample;
import com.yhcrt.healthcloud.system.mapper.SysDictionaryMapper;
import com.yhcrt.healthcloud.util.DateUtil;

/**
 * 健康数据Controller
 * 
 * @author huzelin
 *
 */
@Controller
@RequestMapping("/healthService")
public class HealthDataController {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private HealthDataService healthDataService;
	
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;

	@RequestMapping(value = "/data")
	public String healthData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {

		// 初始化时间 运动步数和深度睡眠 查询十五天的 其他查询一周的
		String startTime1 = sdf.format(DateUtil.plusDate(14, new Date()));
		String startTime2 = sdf.format(DateUtil.plusDate(6, new Date()));
		String endTime = sdf.format(DateUtil.plusDate(-1, new Date()));
		
		//获取最近15天的数据
		List<String> date = DateUtil.get15Date(new Date());
		
		// 根据时间查询健康数据
		List<HdBloodGlucose> glucoseList = healthDataService.getGlucoseByTimeAndMemberId(startTime2, endTime, memberId);
		List<HdBloodPressure> pressureList = healthDataService.getPressureByTimeAndMemberId(startTime2, endTime,memberId);
		List<HdPulse> pulseList = healthDataService.getPulseByTimeAndMemberId(startTime2, endTime, memberId);
		List<HdStep> stepList = healthDataService.getStepByTimeAndMemberId(startTime1, endTime, memberId, date);
		List<HdSleep> sleepList = healthDataService.getSleepByTimeAndMemberId(startTime1, endTime, memberId, date);

		// 向叶面传入数据
		modelMap.addAttribute("glucoseList", glucoseList);
		modelMap.addAttribute("pressureList", pressureList);
		modelMap.addAttribute("pulseList", pulseList);
		modelMap.addAttribute("stepList", stepList);
		modelMap.addAttribute("sleepList", sleepList);
		modelMap.addAttribute("memberId", memberId);
		modelMap.addAllAttributes(initData(glucoseList, pressureList, pulseList, stepList, sleepList));

		return "/health/jsp/health_data";
	}

	/**
	 * 计算健康数据中最大值、最小值、平均值
	 * 
	 * @param glucoseList
	 * @param pressureList
	 * @param pulseList
	 * @param stepList
	 * @param sleepList
	 * @return 各个集合中的最大值、最小值、平均值的map集合
	 */
	private Map<String, Object> initData(List<HdBloodGlucose> glucoseList, List<HdBloodPressure> pressureList,
			List<HdPulse> pulseList, List<HdStep> stepList, List<HdSleep> sleepList) {

		Map<String, Object> map = new HashMap<>();

		// glucose
		float glucoseMax = 0;
		float glucoseMin = 0;
		float glucoseAve = 0;
		float glucoseTotal = 0;
		for (int i = 0; i < glucoseList.size(); i++) {
			float bgValue = glucoseList.get(i).getBgValue();
			glucoseTotal += bgValue;
			if (i == 0) {
				glucoseMax = bgValue;
				glucoseMin = bgValue;
			} else {
				if (bgValue > glucoseMax) {
					glucoseMax = bgValue;
				}
				if (bgValue < glucoseMin) {
					glucoseMin = bgValue;
				}
			}
		}
		if (glucoseList.size() > 0) {
			glucoseAve = glucoseTotal / glucoseList.size();
			glucoseAve = Math.round(glucoseAve * 100) / 100;
		}
		map.put("glucoseMax", glucoseMax);
		map.put("glucoseMin", glucoseMin);
		map.put("glucoseAve", glucoseAve);

		// pressure
		int sbpMax = 0;
		int sbpMin = 0;
		int sbpAve = 0;
		int dbpMax = 0;
		int dbpMin = 0;
		int dbpAve = 0;
		int sbpTotal = 0;
		int dbpTotal = 0;
		for (int i = 0; i < pressureList.size(); i++) {
			int sbp = pressureList.get(i).getSbp();
			sbpTotal += sbp;
			int dbp = pressureList.get(i).getDbp();
			dbpTotal += dbp;
			if (i == 0) {
				sbpMax = sbp;
				dbpMax = dbp;
				sbpMin = sbp;
				dbpMin = dbp;
			} else {
				if (sbp > sbpMax) {
					sbpMax = sbp;
				}
				if (sbp < sbpMin) {
					sbpMin = sbp;
				}
				if (dbp > dbpMax) {
					dbpMax = dbp;
				}
				if (dbp < dbpMin) {
					dbpMin = dbp;
				}
			}
		}
		if (pressureList.size() > 0) {
			sbpAve = sbpTotal / pressureList.size();
		}

		if (pressureList.size() > 0) {
			dbpAve = dbpTotal / pressureList.size();
		}

		map.put("sbpMax", sbpMax);
		map.put("sbpMin", sbpMin);
		map.put("sbpAve", sbpAve);
		map.put("dbpMax", dbpMax);
		map.put("dbpMin", dbpMin);
		map.put("dbpAve", dbpAve);

		// pulse
		int pulseMax = 0;
		int pulseMin = 0;
		int pulseAve = 0;
		int pulseTotal = 0;
		for (int i = 0; i < pulseList.size(); i++) {
			int pulse = pulseList.get(i).getPulse();
			pulseTotal += pulse;
			if (i == 0) {
				pulseMax = pulse;
				pulseMin = pulse;
			} else {
				if (pulse > pulseMax) {
					pulseMax = pulse;
				}

				if (pulse < pulseMin) {
					pulseMin = pulse;
				}

			}
		}
		if (pulseList.size() > 0) {
			pulseAve = pulseTotal / pulseList.size();
		}

		map.put("pulseMax", pulseMax);
		map.put("pulseMin", pulseMin);
		map.put("pulseAve", pulseAve);

		// step
		int stepMax = 0;
		int stepMin = 0;
		int stepAve = 0;
		int stepTotal = 0;
		for (int i = 0; i < stepList.size(); i++) {
			int stepCount = stepList.get(i).getStepCount();
			stepTotal += stepCount;
			if (i == 0) {
				stepMax = stepCount;
				stepMin = stepCount;
			} else {
				if (stepCount > stepMax) {
					stepMax = stepCount;
				}
				if (stepCount < stepMin) {
					stepMin = stepCount;
				}
			}
		}
		if (stepList.size() > 0) {
			stepAve = stepTotal / stepList.size();
		}
		map.put("stepMax", stepMax);
		map.put("stepMin", stepMin);
		map.put("stepAve", stepAve);

		// sleep
		double sleepMax = 0;
		double sleepMin = 0;
		double sleepAve = 0;
		double sleepTotal = 0;
		for (int i = 0; i < sleepList.size(); i++) {
			double sleep = sleepList.get(i).getDeepSleepDuration();
			sleepTotal += sleep;
			if (i == 0) {
				sleepMax = sleep;
				sleepMin = sleep;
			} else {
				if (sleep > sleepMax) {
					sleepMax = sleep;
				}
				if (sleep < sleepMin) {
					sleepMin = sleep;
				}

			}
		}
		if (sleepList.size() > 0) {
			sleepAve = sleepTotal / sleepList.size();
		}
		map.put("sleepMax", Math.round(sleepMax * 10) / 10);
		map.put("sleepMin", Math.round(sleepMin * 10) / 10);
		map.put("sleepAve", Math.round(sleepAve * 100) / 100);

		return map;
	}

	@RequestMapping(value = "/bloodGlucose")
	public String bloodGlucose(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		List<HdBloodGlucose> glucoseList = healthDataService.getGlucoseByMemberId(memberId);
		modelMap.addAttribute("items", toVo(glucoseList));
		modelMap.addAttribute("memberId", memberId);
		return "/health/jsp/blood_glucose";
	}

	private List<BloodGlucoseAndType> toVo(List<HdBloodGlucose> glucoseList) {
		List<BloodGlucoseAndType> items = new ArrayList<>();
		for (HdBloodGlucose glucose : glucoseList) {
			BloodGlucoseAndType item = new BloodGlucoseAndType();
			item.setGlucose(glucose);
			Integer type = glucose.getBgType();
			if (type != null) {
			//	String typeValue = glucoseTypeService.getTypeValue(type);
				SysDictionaryExample example = new SysDictionaryExample();
				example.createCriteria().andDictValueEqualTo(type+"").andDictEnNameEqualTo("bg_type");
				List<SysDictionary> list = sysDictionaryMapper.selectByExample(example);
				if(list.size()>0){
					item.setType(list.get(0).getDictKey());
				}
			}
			items.add(item);
		}
		return items;
	}

}
