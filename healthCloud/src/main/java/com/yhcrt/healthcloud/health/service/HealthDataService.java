package com.yhcrt.healthcloud.health.service;

import java.util.List;

import com.yhcrt.healthcloud.health.entity.HdBloodGlucose;
import com.yhcrt.healthcloud.health.entity.HdBloodPressure;
import com.yhcrt.healthcloud.health.entity.HdPulse;
import com.yhcrt.healthcloud.health.entity.HdSleep;
import com.yhcrt.healthcloud.health.entity.HdStep;

/**
 * 健康数据服务接口
 * @author huzelin
 *
 */
public interface HealthDataService {
	
	List<HdBloodGlucose> selectByMon(Integer memberId);//血糖
	
	List<HdBloodPressure> selectByMonHp(Integer memberId);//血压，最近一个月的数据
	
	List<HdPulse> selectByMonP(Integer memberId);//心率，最近一个月的数据
	
	List<HdSleep> selectByMonSl(Integer memberId);//睡眠，最近一个月的数据
	
	List<HdStep> selectByMonS(Integer memberId);//步数，最近一个月的数据
	
	/*----------------- 血糖 ---------------------*/
	public HdBloodGlucose getGlucoseByCid(int cid);
	public List<HdBloodGlucose> getGlucoseByMemberId(int memberId);
	public List<HdBloodGlucose> getGlucoseByBgType(int bgType);
	public List<HdBloodGlucose> getGlucoseByImei(String imei);
	public List<HdBloodGlucose> getGlucoseByTimeAndMemberId(String startTime, String endTime, int memberId);
	public HdBloodGlucose getGlucoseLatest(int memberId);
	
	/*----------------- 血压 ---------------------*/
	public HdBloodPressure getPressureByCid(int cid);
	public List<HdBloodPressure> getPressureByMemberId(int memberId);
	public List<HdBloodPressure> getPressureByImei(String imei);
	public List<HdBloodPressure> getPressureByTimeAndMemberId(String startTime, String endTime, int memberId);
	public HdBloodPressure getPressureLatest(int memberId);
	
	/*----------------- 心率 ---------------------*/
	public HdPulse getPulseByCid(int cid);
	public List<HdPulse> getPulseByMemberId(int memberId);
	public List<HdPulse> getPulseByImei(String imei);
	public List<HdPulse> getPulseByTimeAndMemberId(String startTime, String endTime, int memberId);
	public HdPulse getPulseLatest(int memberId);
	
	/*----------------- 睡眠 ---------------------*/
	public HdSleep getSleepByCid(int cid);
	public List<HdSleep> getSleepByMemberId(int memberId);
	public List<HdSleep> getSleepByImei(String imei);
	public List<HdSleep> getSleepByTimeAndMemberId(String startTime, String endTime, int memberId, List<String> date);
	public HdSleep getSleepLatest(int memberId);
	
	/*----------------- 计步 ---------------------*/
	public HdStep getStepByCid(int cid);
	public List<HdStep> getStepByMemberId(int memberId);
	public List<HdStep> getStepByImei(String imei);
	public List<HdStep> getStepByTimeAndMemberId(String startTime, String endTime, int memberId, List<String> date);
	public HdStep getStepLatest(int memberId);
}