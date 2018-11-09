package com.yhcrt.iHealthCloud.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.HdBloodGlucose;
import com.yhcrt.iHealthCloud.entity.HdBloodPressure;
import com.yhcrt.iHealthCloud.entity.HdPulse;
import com.yhcrt.iHealthCloud.entity.HdSleep;
import com.yhcrt.iHealthCloud.entity.HdStep;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.mapper.HdBloodGlucoseMapper;
import com.yhcrt.iHealthCloud.mapper.HdBloodPressureMapper;
import com.yhcrt.iHealthCloud.mapper.HdPulseMapper;
import com.yhcrt.iHealthCloud.mapper.HdSleepMapper;
import com.yhcrt.iHealthCloud.mapper.HdStepMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtils;

@Controller
public class HealthDataController {
	
	@Autowired
	private MemberDeviceMapper memberDeviceMapper;
	@Autowired
	private HdStepMapper hdStepMapper;
	@Autowired
	private HdSleepMapper hdSleepMapper;
	@Autowired
	private HdPulseMapper hdPulseMapper;
	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private HdBloodPressureMapper hdBloodPressureMapper;
	@Autowired
	private HdBloodGlucoseMapper hdBloodGlucoseMapper;
	
	/**
	 * 运动量(计步)数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/step/upload", method = RequestMethod.POST)
	public void uploadStepData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		String imei = request.getParameter("imei");
		String stepCount = request.getParameter("step_count");
		JSONObject jsonObj = new JSONObject();
		// 检查IMEI是否是有效的imei,即已被会员绑定使用
		MemberDevice md = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
		if (null != md) {
			HdStep hdStep =  new HdStep();
			hdStep.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.HD_STEP));
			hdStep.setImei(imei);
			hdStep.setMemberId(md.getMemberId());
			hdStep.setDataDate(DateUtils.getCurrentDayString());
			hdStep.setUploadTime(DateUtils.getCurrentTime());
			hdStep.setStepCount(Integer.parseInt(stepCount));
			hdStepMapper.insert(hdStep);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}else{
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC,  Constants.ExceptionMsg.INVALID_IMEI);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonObj.toString());
		
	}
	
	/**
	 * 睡眠数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/sleep/upload", method = RequestMethod.POST)
	public void uploadSleepData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		String imei = request.getParameter("imei");
		String deepSleepDuration = request.getParameter("deep_sleep_duration");
		JSONObject jsonObj = new JSONObject();
		// 检查IMEI是否是有效的imei,即已被会员绑定使用
		MemberDevice md = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
		if (null != md) {
			HdSleep hdSleep = new HdSleep();
			hdSleep.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.HD_STEP));
			hdSleep.setImei(imei);
			hdSleep.setMemberId(md.getMemberId());
			hdSleep.setDataDate(DateUtils.getCurrentDayString());
			hdSleep.setUploadTime(DateUtils.getCurrentTime());
			hdSleep.setDeepSleepDuration(Double.parseDouble(deepSleepDuration));
			hdSleepMapper.insert(hdSleep);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}else{
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC,  Constants.ExceptionMsg.INVALID_IMEI);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonObj.toString());
		
	}
	
	/**
	 * 心率数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/pulse/upload", method = RequestMethod.POST)
	public void uploadPulseData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		String imei = request.getParameter("imei");
		String pulse = request.getParameter("pulse");
		JSONObject jsonObj = new JSONObject();
		// 检查IMEI是否是有效的imei,即已被会员绑定使用
		MemberDevice md = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
		if (null != md) {
			HdPulse hdPulse = new HdPulse();
			hdPulse.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.HD_STEP));
			hdPulse.setImei(imei);
			hdPulse.setMemberId(md.getMemberId());
			hdPulse.setDataDate(DateUtils.getCurrentDayString());
			hdPulse.setUploadTime(DateUtils.getCurrentTime());
			hdPulse.setPulse(Integer.parseInt(pulse));
			hdPulseMapper.insert(hdPulse);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}else{
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC,  Constants.ExceptionMsg.INVALID_IMEI);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonObj.toString());
	}
	
	/**
	 * 血压数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/bloodpressure/upload", method = RequestMethod.POST)
	public void uploadBloodPressureData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		String imei = request.getParameter("imei");
		String sbp = request.getParameter("sbp");
		String dbp = request.getParameter("dbp");
		JSONObject jsonObj = new JSONObject();
		// 检查IMEI是否是有效的imei,即已被会员绑定使用
		MemberDevice md = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
		if (null != md) {
			HdBloodPressure hdBloodPressure = new HdBloodPressure();
			hdBloodPressure.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.HD_STEP));
			hdBloodPressure.setImei(imei);
			hdBloodPressure.setMemberId(md.getMemberId());
			hdBloodPressure.setDataDate(DateUtils.getCurrentDayString());
			hdBloodPressure.setUploadTime(DateUtils.getCurrentTime());
			hdBloodPressure.setSbp(Integer.parseInt(sbp));
			hdBloodPressure.setDbp(Integer.parseInt(dbp));
			hdBloodPressureMapper.insert(hdBloodPressure);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}else{
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC,  Constants.ExceptionMsg.INVALID_IMEI);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonObj.toString());
	}
	
	/**
	 * 血糖数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/bloodglucose/upload", method = RequestMethod.POST)
	public void uploadBloodGlucoseData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		String imei = request.getParameter("imei");
		String bgType = request.getParameter("bg_type");
		String bgValue = request.getParameter("bg_value");
		JSONObject jsonObj = new JSONObject();
		// 检查IMEI是否是有效的imei,即已被会员绑定使用
		MemberDevice md = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
		if (null != md) {
			HdBloodGlucose hdBloodGlucose = new HdBloodGlucose();
			hdBloodGlucose.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.HD_STEP));
			hdBloodGlucose.setImei(imei);
			hdBloodGlucose.setMemberId(md.getMemberId());
			hdBloodGlucose.setDataDate(DateUtils.getCurrentDayString());
			hdBloodGlucose.setUploadTime(DateUtils.getCurrentTime());
			hdBloodGlucose.setBgType(Integer.parseInt(bgType));
			hdBloodGlucose.setBgValue(Float.parseFloat(bgValue));
			hdBloodGlucoseMapper.insert(hdBloodGlucose);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}else{
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC,  Constants.ExceptionMsg.INVALID_IMEI);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonObj.toString());
	}
}
