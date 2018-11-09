package com.yhcrt.iHealthCloud.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.MedicalExaminationReport;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberExample;
import com.yhcrt.iHealthCloud.entity.MemberExample.Criteria;
import com.yhcrt.iHealthCloud.entity.MerBloodGlucose;
import com.yhcrt.iHealthCloud.entity.MerBloodOxygen;
import com.yhcrt.iHealthCloud.entity.MerBloodPressure;
import com.yhcrt.iHealthCloud.entity.MerBmi;
import com.yhcrt.iHealthCloud.entity.MerBodyFat;
import com.yhcrt.iHealthCloud.entity.MerCholesterol;
import com.yhcrt.iHealthCloud.entity.MerElectrocardiogram;
import com.yhcrt.iHealthCloud.entity.MerTemperature;
import com.yhcrt.iHealthCloud.entity.MerUricAcid;
import com.yhcrt.iHealthCloud.entity.MerWaistHipRatio;
import com.yhcrt.iHealthCloud.entity.SysUser;
import com.yhcrt.iHealthCloud.mapper.MedicalExaminationReportMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.MerBloodGlucoseMapper;
import com.yhcrt.iHealthCloud.mapper.MerBloodOxygenMapper;
import com.yhcrt.iHealthCloud.mapper.MerBloodPressureMapper;
import com.yhcrt.iHealthCloud.mapper.MerBmiMapper;
import com.yhcrt.iHealthCloud.mapper.MerBodyFatMapper;
import com.yhcrt.iHealthCloud.mapper.MerCholesterolMapper;
import com.yhcrt.iHealthCloud.mapper.MerElectrocardiogramMapper;
import com.yhcrt.iHealthCloud.mapper.MerTemperatureMapper;
import com.yhcrt.iHealthCloud.mapper.MerUricAcidMapper;
import com.yhcrt.iHealthCloud.mapper.MerWaistHipRatioMapper;
import com.yhcrt.iHealthCloud.mapper.SysUserMapper;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.Md5PwdEncoder;
import com.yhcrt.iHealthCloud.util.PropertiesUtil;
import com.yhcrt.iHealthCloud.util.RequestUtils;

@Controller
public class MerController {
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private MerBmiMapper merBmiMapper;	
	@Autowired
	private MerBodyFatMapper merBodyFatMapper ;
	@Autowired
	private MerBloodPressureMapper merBloodPressureMapper;
	@Autowired
	private MerBloodGlucoseMapper merBloodGlucoseMapper;
	@Autowired
	private MerUricAcidMapper merUricAcidMapper;
	@Autowired
	private MerCholesterolMapper merCholesterolMapper;
	@Autowired
	private MerBloodOxygenMapper merBloodOxygenMapper;
	@Autowired
	private MerTemperatureMapper merTemperatureMapper;
	@Autowired
	private MerElectrocardiogramMapper merElectrocardiogramMapper;
	@Autowired
	private MerWaistHipRatioMapper merWaistHipRatioMapper;
	@Autowired
	private MedicalExaminationReportMapper medicalExaminationReportMapper;

	
	
	@RequestMapping(value = "/member/getMemberId", method = RequestMethod.POST)
	public void getMemberId(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		String realName = request.getParameter("real_name");
		String identityCard = request.getParameter("identity_card");
		String phoneNo = request.getParameter("phoneNo");
		JSONObject jsonObj = new JSONObject();
		MemberExample example = new MemberExample();
		Criteria criteria = example.createCriteria();
		criteria.andRealNameEqualTo(realName);
		criteria.andIdentityCardEqualTo(identityCard);
		criteria.andPhoneNoEqualTo(phoneNo);
		List<Member> members = memberMapper.selectByExample(example);
		if (members.size()>0) {
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put("member_id", members.get(0).getMemberId());
			jsonObj.put(Const.ERROR_DESC, "");
		}else{
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC,  Constants.ExceptionMsg.INVALID_IMEI);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonObj.toString());
	}
	
	/**
	 *   接收双佳一体机上传数据的接口
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/uploadDataFromSJ", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadDataFromSJ(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultjson = new JSONObject();
		String pdata = RequestUtils.getJsonString(request);
		JSONObject jsondata = JSONObject.parseObject(pdata);
		if(jsondata.isEmpty()){
			resultjson.put("success", "false");
			resultjson.put("message", "未获取到数据");
			return resultjson;
		}
		if(jsondata.getJSONObject("Member") == null){
			resultjson.put("success", "false");
			resultjson.put("message", "未获取到用户数据");
			return resultjson;
		}
		String machineId = jsondata.getString("MachineId");
		JSONObject memberjson = jsondata.getJSONObject("Member");
		String mobile = StringUtils.isEmpty(memberjson.getString("Mobile"))?null:memberjson.getString("Mobile");
		String idcard = StringUtils.isEmpty(memberjson.getString("IdCode"))?null:memberjson.getString("IdCode");
		MemberExample example = new MemberExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdentityCardEqualTo(idcard).andIdentityCardIsNotNull();
		List<Member> members = memberMapper.selectByExample(example);
		Member member = new Member();
		if(members.size()==0){  //先匹配身份证，如果匹配不到就匹配手机号
			if(mobile == null ){ //身份证匹配不到，又没有手机号，不给创建，也不上传数据
				resultjson.put("success", "false");
				resultjson.put("message", "用户创建失败，无手机号");
				return resultjson;
			}
			criteria = example.createCriteria();
			criteria.andPhoneNoEqualTo(mobile);
			members = memberMapper.selectByExample(example);
			if(members.size()==0){ //手机号也匹配不到就创建用户信息
				SysUser sysuser = new SysUser();
				Integer userid = sysSequenceService.getSequenceValue(Constants.SequenceName.SYS_USER);
				sysuser.setUserId(userid);
				sysuser.setUserCode(mobile);
				sysuser.setPassword(Md5PwdEncoder.encodePassword(mobile));
				sysuser.setUserType(Constants.UserType.USER_TYPE_MEMBER);
				sysuser.setStatus(Constants.STATUS_NORMAL);
				sysuser.setCreateTime(DateUtils.dateToString19(new Date()));
				sysuser.setRemark("一体机检测创建");
				sysUserMapper.insert(sysuser);
				member = new Member();
				member.setMemberId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEMBER));
				member.setUserId(userid);
				member.setRealName(memberjson.getString("Name"));
				member.setIdentityCard(idcard);
				member.setPhoneNo(mobile);
				member.setGender(memberjson.getInteger("Sex"));
				member.setNickName(memberjson.getString("Name"));
				member.setCreateTime(DateUtils.dateToString19(new Date()));
				member.setMemberType(Constants.MemberType.COMMON);
				member.setStatus(Constants.STATUS_NORMAL);
				String orgid = PropertiesUtil.getProperty("/config.properties", machineId);
				if(!StringUtils.isEmpty(orgid)){
					member.setOrgId(Integer.valueOf(orgid)); //配置的一体机id与机构的关系
				}
				member.setRemark("一体机检测创建");
				memberMapper.insert(member);
			}else{
				member = members.get(0);
			}
		}else {
			member = members.get(0);
		}
		String uploadTime = jsondata.getString("MeasureTime");
		String datadate = DateUtils.dateToStringPattern( DateUtils.stringToDate19(uploadTime),"yyyy-MM-dd")  ;
		MedicalExaminationReport mer = new MedicalExaminationReport();
		Integer merId =  sysSequenceService.getSequenceValue("medical_examination_report");
		mer.setMerId(merId);
		mer.setMemberId(member.getMemberId());
		mer.setMerTime(uploadTime);
		medicalExaminationReportMapper.insert(mer);
		
		//用户信息确认完毕，开始上传数据
		if(jsondata.getJSONObject("Whr") != null){  //腰臀比
			JSONObject Whr = jsondata.getJSONObject("Whr");
			MerWaistHipRatio merWaistHipRatio = new MerWaistHipRatio();
			merWaistHipRatio.setMerId(merId);
			merWaistHipRatio.setUploadTime(uploadTime);
			merWaistHipRatio.setDataDate(datadate);
			merWaistHipRatio.setWaistline(Whr.getDouble("Waistline")); //腰围
			merWaistHipRatio.setHipline(Whr.getDouble("Hipline"));  //臀围
			merWaistHipRatio.setWaistHipRatio(Whr.getDouble("Whr")); //腰臀比
			String result = "";
			switch (Whr.getString("Result")) {
			case "0":
				result = "正常";
				break;
			case "1":
				result = "中心性肥胖";
				break;
			default:
				break;
			}
			merWaistHipRatio.setConclusion(result);  //结论   0:正常，1：中心性肥胖
			merWaistHipRatioMapper.insert(merWaistHipRatio);
		}
		if(jsondata.getJSONObject("Ua") != null){  //尿酸
			JSONObject Ua = jsondata.getJSONObject("Ua");
			MerUricAcid merUricAcid = new MerUricAcid();
			merUricAcid.setMerId(merId+"");
			merUricAcid.setUploadTime(uploadTime);
			merUricAcid.setUaValue(Ua.getDouble("Ua"));
			String result = "";
			switch (Ua.getString("Result")) {   //结论：-1、低，0、正常，1、高
			case "0":
				result = "正常";
				break;
			case "-1":
				result = "低";
				break;
			case "1":
				result = "高";
				break;
			default:
				break;
			}
			merUricAcid.setConclusion(result);
			merUricAcidMapper.insert(merUricAcid);
		}
		if(jsondata.getJSONObject("Temperature") != null){  //体温
			JSONObject Temperature = jsondata.getJSONObject("Temperature");
			MerTemperature merTemperature = new MerTemperature();
			merTemperature.setMerId(merId);
			merTemperature.setUploadTime(uploadTime);
			merTemperature.setTemperature(Temperature.getDouble("Temperature"));;
			String result = "";
			switch (Temperature.getString("Result")) {   //结论：-1、低，0、正常，1、高
			case "0":
				result = "正常";
				break;
			case "-1":
				result = "低";
				break;
			case "1":
				result = "高";
				break;
			default:
				break;
			}
			merTemperature.setConclusion(result);
			merTemperatureMapper.insert(merTemperature);
		}
		if(jsondata.getJSONObject("Ecg") != null){  //心电
			JSONObject Ecg = jsondata.getJSONObject("Ecg");
			MerElectrocardiogram merElectrocardiogram = new MerElectrocardiogram();
			merElectrocardiogram.setMerId(merId+"");
			merElectrocardiogram.setUploadTime(uploadTime);
			merElectrocardiogram.setRateValue(Ecg.getInteger("Hr"));
			merElectrocardiogram.setElectrocardiogram(Ecg.getString("EcgData"));
			String result = "";
			switch (Ecg.getString("Analysis")) {   //结论：-1、低，0、正常，1、高
			case "5025":
				result = "节律无异常";
				break;
			case "5026":
				result = "疑似心跳稍快，请注意休息";
				break;
			case "5027":
				result = "疑似心跳过快，请注意休息";
				break;
			case "5028":
				result = "疑似阵发性心跳过快";
				break;
			case "5029":
				result = "疑似心跳稍缓，请注意休息";
				break;
			case "5030":
				result = "疑似心跳过缓，请注意休息";
				break;
			case "5031":
				result = "疑似心跳间期缩短";
				break;
			case "5032":
				result = "疑似心跳间期不规则";
				break;
			case "5033":
				result = "疑似心跳稍快伴有心跳间期缩短";
				break;
			case "5034":
				result = "疑似心跳稍缓伴有心跳间期缩短";
				break;
			case "5035":
				result = "疑似心跳稍缓伴有心跳间期不规则";
				break;
			case "5036":
				result = "波形有漂移";
				break;
			case "5037":
				result = "疑似心跳过快伴有波形漂移";
				break;
			case "5038":
				result = "疑似心跳过缓伴有波形漂移";
				break;
			case "5039":
				result = "疑似心跳间期缩短伴有波形漂移";
				break;
			case "5040":
				result = "疑似心跳间期不规则伴有波形漂移";
				break;
			case "5041":
				result = "信号较差，请重新测量";
				break;
			default:
				break;
			}
			merElectrocardiogram.setConclusion(result);
			merElectrocardiogramMapper.insert(merElectrocardiogram);
		}
		if(jsondata.getJSONObject("Chol") != null){  //胆固醇
			JSONObject Chol = jsondata.getJSONObject("Chol");
			MerCholesterol merCholesterol = new MerCholesterol();
			merCholesterol.setMerId(merId+"");
			merCholesterol.setUploadTime(uploadTime);
			merCholesterol.setCholesterol(Chol.getDouble("Chol"));
			String result = "";
			switch (Chol.getString("Result")) {   //结论：-1、低，0、正常，1、高
			case "0":
				result = "正常";
				break;
			case "-1":
				result = "低";
				break;
			case "1":
				result = "高";
				break;
			default:
				break;
			}
			merCholesterol.setConclusion(result);
			merCholesterolMapper.insert(merCholesterol);
		}
		if(jsondata.getJSONObject("Fat") != null){  //体脂
			JSONObject Fat = jsondata.getJSONObject("Fat");
			MerBodyFat merBodyFat = new MerBodyFat();
			merBodyFat.setUploadTime(uploadTime);
			merBodyFat.setMerId(merId+"");
			
			merBodyFat.setBfp(Fat.getDouble("FatRate"));
			merBodyFat.setBf(Fat.getDouble("Fat"));
			merBodyFat.setNonFat(Fat.getDouble("Fat"));
			merBodyFat.setBwp(Fat.getDouble("WaterRate"));
			merBodyFat.setBw(Fat.getDouble("Water"));
			merBodyFat.setMineral(Fat.getDouble("Minerals"));
			merBodyFat.setProtein(Fat.getDouble("Protein"));
			merBodyFat.setEcw(Fat.getDouble("Foc"));
			merBodyFat.setIcw(Fat.getDouble("Fic"));
			merBodyFat.setMuscleMass(Fat.getDouble("Muscle"));
			merBodyFat.setFatRegulation(Fat.getDouble("FatAdjust"));
			merBodyFat.setWeightRegulation(Fat.getDouble("WeightAdjust"));
			merBodyFat.setMuscleRegulation(Fat.getDouble("MuscleAdjust"));
			merBodyFat.setBmr(Fat.getDouble("BasicMetabolism"));
			merBodyFat.setVisceralFatLevel(Fat.getDouble("Viscera"));
			merBodyFat.setSkeletalMass(Fat.getDouble("Bmc"));
			merBodyFat.setMusclePercent(Fat.getDouble("MuscleRate"));
			merBodyFat.setTrunkFat(Fat.getDouble("QuganFat"));
			merBodyFat.setTrunkMuscle(Fat.getDouble("QuganMuscle"));
			
			merBodyFat.setLeftArmMuscle(Fat.getDouble("ZuobiMuscle"));
			merBodyFat.setRightArmMuscle(Fat.getDouble("YoubiMuscle"));
			merBodyFat.setLeftLegMuscle(Fat.getDouble("ZuotuiMuscle"));
			merBodyFat.setRightLegMuscle(Fat.getDouble("YoutuiMuscle"));
			
			merBodyFat.setLeftArmFat(Fat.getDouble("ZuobiFat"));
			merBodyFat.setRightArmFat(Fat.getDouble("YoubiFat"));
			merBodyFat.setLeftLegFat(Fat.getDouble("ZuotuiFat"));
			merBodyFat.setRightLegFat(Fat.getDouble("YoutuiFat"));
			String result = "";
			switch (Fat.getString("Result")) {   //结论：-1、稍瘦，0、标准，1、超重，2、肥胖
			case "0":
				result = "标准";
				break;
			case "-1":
				result = "稍瘦";
				break;
			case "1":
				result = "超重";
				break;
			case "2":
				result = "肥胖";
				break;
			default:
				break;
			}
			merBodyFat.setConclusion(result);
			merBodyFatMapper.insert(merBodyFat);
		}
		if(jsondata.getJSONObject("Height") != null){  //体重
			JSONObject Height = jsondata.getJSONObject("Height");
			MerBmi merBmi = new MerBmi();
			merBmi.setMerId(merId+"");
			merBmi.setHeight(Height.getDouble("Height"));
			merBmi.setWeight(Height.getDouble("Weight"));
			merBmi.setBmi(Height.getDouble("BMI"));
			merBmi.setIdealWeight(Height.getDouble("IdealWeight"));
			String result = "";
			switch (Height.getString("Result")) {   //结论：-1、稍瘦，0、标准，1、超重，2、肥胖
			case "0":
				result = "正常";
				break;
			case "-1":
				result = "稍瘦";
				break;
			case "1":
				result = "超重";
				break;
			case "2":
				result = "肥胖";
				break;
			default:
				break;
			}
			merBmi.setConclusion(result);
			merBmi.setUploadTime(uploadTime);
			merBmiMapper.insert(merBmi);
		}
		if(jsondata.getJSONObject("BloodPressure") != null){  //血压
			JSONObject BloodPressure = jsondata.getJSONObject("BloodPressure");
			MerBloodPressure merBloodPressure = new MerBloodPressure();
			merBloodPressure.setMerId(merId);
			merBloodPressure.setUploadTime(uploadTime);
			merBloodPressure.setDataDate(new Date());
			merBloodPressure.setSbp(BloodPressure.getInteger("HighPressure"));
			merBloodPressure.setDbp(BloodPressure.getInteger("LowPressure"));
			merBloodPressure.setSphygmus(BloodPressure.getInteger("Pulse"));
			String result = "";
			switch (BloodPressure.getString("Result")) {   //结论：-1、低压，0、正常，1、正常高压 2、轻度高压 3、中度高压 4、重度高压
			case "0":
				result = "正常";
				break;
			case "-1":
				result = "低压";
				break;
			case "1":
				result = "正常高压 ";
				break;
			case "2":
				result = "轻度高压";
				break;
			case "3":
				result = "中度高压";
				break;
			case "4":
				result = "重度高压";
				break;
			default:
				break;
			}
			merBloodPressure.setConclusion(result);
			merBloodPressureMapper.insert(merBloodPressure);
		}
		if(jsondata.getJSONObject("Bo") != null){  //血氧
			JSONObject Bo = jsondata.getJSONObject("Bo");
			MerBloodOxygen merBloodOxygen = new MerBloodOxygen();
			merBloodOxygen.setMerId(merId);
			merBloodOxygen.setUploadTime(uploadTime);
			merBloodOxygen.setDataTime(datadate);
			merBloodOxygen.setBoValue(Bo.getDouble("Oxygen"));
			String result = "";
			switch (Bo.getString("Result")) {   //结论：-1、低血氧，0、正常血氧
			case "0":
				result = "正常血氧";
				break;
			case "-1":
				result = "低血氧";
				break;
			default:
				break;
			}
			merBloodOxygen.setConclusion(result);
			merBloodOxygenMapper.insert(merBloodOxygen);
		}
		if(jsondata.getJSONObject("BloodSugar") != null){  //血糖
			JSONObject BloodSugar = jsondata.getJSONObject("BloodSugar");
			MerBloodGlucose merBloodGlucose = new MerBloodGlucose();		
			merBloodGlucose.setMerId(merId+"");
			merBloodGlucose.setDataTime(datadate);
			merBloodGlucose.setUploadTime(uploadTime);
			merBloodGlucose.setBgType(BloodSugar.getInteger("BloodsugarType"));  //血糖类型：1、餐前血糖，2、餐后血糖，3、随机血糖
			merBloodGlucose.setGlucoseValue(BloodSugar.getDouble("BloodSugar"));
			String result = "";
			switch (BloodSugar.getString("Result")) {   //结论：-1、低血糖，0、正常，1、偏高 2、高
			case "0":
				result = "正常";
				break;
			case "-1":
				result = "低血糖";
				break;
			case "1":
				result = "偏高";
				break;
			case "2":
				result = "高";
				break;
			default:
				break;
			}
			merBloodGlucose.setConclusion(result);
			merBloodGlucoseMapper.insert(merBloodGlucose);
		}
		resultjson.put("success", "true");
		resultjson.put("message", "上传成功");
		return resultjson;
	}
	
	
	@RequestMapping(value = "/bmi/upload", method = RequestMethod.POST)
	public void uploadBMIData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		String merId = request.getParameter("mer_id");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String bmi = request.getParameter("bmi");
		String idealWeight = request.getParameter("ideal_weight");
		String conclusion = request.getParameter("conclusion");
		String advice = request.getParameter("advice");
		JSONObject jsonObj = new JSONObject();
		try {
			MerBmi merBmi = new MerBmi();
			merBmi.setMerId(merId);
			merBmi.setHeight(Double.parseDouble(height));
			merBmi.setWeight(Double.parseDouble(weight));
			merBmi.setBmi(Double.parseDouble(bmi));
			merBmi.setIdealWeight(Double.parseDouble(idealWeight));
			merBmi.setConclusion(conclusion);
			merBmi.setAdvice(advice);
			merBmi.setUploadTime(DateUtils.getCurrentTime());
			merBmiMapper.insert(merBmi);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	@RequestMapping(value = "/bodyfat/upload", method = RequestMethod.POST)
	public void uploadBodyFatData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObj = new JSONObject();
		try {
			MerBodyFat merBodyFat = getMerBodyFat(request);
			merBodyFatMapper.insert(merBodyFat);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	@RequestMapping(value = "/bp/upload", method = RequestMethod.POST)
	public void uploadBPData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObj = new JSONObject();
		try {
			MerBloodPressure merBloodPressure = new MerBloodPressure();
			merBloodPressure.setMerId(Integer.parseInt(request.getParameter("mer_id")));
			merBloodPressure.setUploadTime(DateUtils.getCurrentTime());
			merBloodPressure.setDataDate(new Date());
			merBloodPressure.setSbp(Integer.parseInt(request.getParameter("sbp")));
			merBloodPressure.setDbp(Integer.parseInt(request.getParameter("dbp")));
			merBloodPressure.setSphygmus(Integer.parseInt(request.getParameter("sphygmus")));
			merBloodPressure.setAdvice(request.getParameter("advice"));
			merBloodPressure.setConclusion(request.getParameter("conclusion"));
			merBloodPressureMapper.insert(merBloodPressure);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	@RequestMapping(value = "/bg/upload", method = RequestMethod.POST)
	public void uploadBGData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObj = new JSONObject();
		try {
			MerBloodGlucose merBloodGlucose = new MerBloodGlucose();		
			merBloodGlucose.setMerId(request.getParameter("mer_id"));
			merBloodGlucose.setDataTime(DateUtils.getCurrentDayString());
			merBloodGlucose.setUploadTime(DateUtils.getCurrentTime());
			merBloodGlucose.setBgType(Integer.parseInt(request.getParameter("bg_type")));
			merBloodGlucose.setGlucoseValue(Double.parseDouble(request.getParameter("bg_value")));
			merBloodGlucose.setAdvice(request.getParameter("advice"));
			merBloodGlucose.setConclusion(request.getParameter("conclusion"));
			merBloodGlucoseMapper.insert(merBloodGlucose);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	@RequestMapping(value = "/uricacid/upload", method = RequestMethod.POST)
	public void uploadUricAcidData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObj = new JSONObject();
		try {
			MerUricAcid merUricAcid = new MerUricAcid();
			merUricAcid.setMerId(request.getParameter("mer_id"));
			merUricAcid.setUploadTime(DateUtils.getCurrentTime());
			merUricAcid.setUaValue(Double.parseDouble(request.getParameter("ua_value")));
			merUricAcid.setAdvice(request.getParameter("advice"));
			merUricAcid.setConclusion(request.getParameter("conclusion"));
			merUricAcidMapper.insert(merUricAcid);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	/**
	 * 胆固醇体检数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/cholesterol/upload", method = RequestMethod.POST)
	public void uploadCholesterolData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObj = new JSONObject();
		try {
			MerCholesterol merCholesterol = new MerCholesterol();
			merCholesterol.setMerId(request.getParameter("mer_id"));
			merCholesterol.setUploadTime(DateUtils.getCurrentTime());
			merCholesterol.setCholesterol(Double.parseDouble(request.getParameter("cholesterol")));
			merCholesterol.setAdvice(request.getParameter("advice"));
			merCholesterol.setConclusion(request.getParameter("conclusion"));
			merCholesterolMapper.insert(merCholesterol);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	/**
	 * 血氧体检数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/bloodoxygen/upload", method = RequestMethod.POST)
	public void uploadBloodOxygenData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObj = new JSONObject();
		try {
			MerBloodOxygen merBloodOxygen = new MerBloodOxygen();
			merBloodOxygen.setMerId(Integer.parseInt(request.getParameter("mer_id")));
			merBloodOxygen.setUploadTime(DateUtils.getCurrentTime());
			merBloodOxygen.setDataTime(DateUtils.getCurrentDayString());
			merBloodOxygen.setBoValue(Double.parseDouble(request.getParameter("bo_value")));
			merBloodOxygen.setAdvice(request.getParameter("advice"));
			merBloodOxygen.setConclusion(request.getParameter("conclusion"));
			merBloodOxygenMapper.insert(merBloodOxygen);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	/**
	 * 体温测量体检数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/temperature/upload", method = RequestMethod.POST)
	public void uploadTemperatureData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObj = new JSONObject();
		try {
			MerTemperature merTemperature = new MerTemperature();
			merTemperature.setMerId(Integer.parseInt(request.getParameter("mer_id")));
			merTemperature.setUploadTime(DateUtils.getCurrentTime());
			merTemperature.setTemperature(Double.parseDouble(request.getParameter("temperature")));;
			merTemperature.setAdvice(request.getParameter("advice"));
			merTemperature.setConclusion(request.getParameter("conclusion"));
			merTemperatureMapper.insert(merTemperature);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	/**
	 * 心电测量体检数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/ecg/upload", method = RequestMethod.POST)
	public void uploadEcgData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObj = new JSONObject();
		try {
			MerElectrocardiogram merElectrocardiogram = new MerElectrocardiogram();
			merElectrocardiogram.setMerId(request.getParameter("mer_id"));
			merElectrocardiogram.setUploadTime(DateUtils.getCurrentTime());
			merElectrocardiogram.setRateValue(Integer.parseInt(request.getParameter("rate_value")));
			merElectrocardiogram.setElectrocardiogram(request.getParameter("electrocardiogram"));
			merElectrocardiogram.setAdvice(request.getParameter("advice"));
			merElectrocardiogram.setConclusion(request.getParameter("conclusion"));
			merElectrocardiogramMapper.insert(merElectrocardiogram);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	/**
	 * 腰臀比测量体检数据上传
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/waisthipratio/upload", method = RequestMethod.POST)
	public void uploadWaistHipRatioData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		JSONObject jsonObj = new JSONObject();
		try {
			MerWaistHipRatio merWaistHipRatio = new MerWaistHipRatio();
			merWaistHipRatio.setMerId(Integer.parseInt(request.getParameter("mer_id")));
			merWaistHipRatio.setUploadTime(DateUtils.getCurrentTime());
			merWaistHipRatio.setDataDate(DateUtils.getCurrentDayString());
			merWaistHipRatio.setWaistline(Double.parseDouble(request.getParameter("waistline")));
			merWaistHipRatio.setHipline(Double.parseDouble(request.getParameter("hipline")));
			merWaistHipRatio.setWaistHipRatio(Double.parseDouble(request.getParameter("waist_hip_ratio")));
			merWaistHipRatio.setAdvice(request.getParameter("advice"));
			merWaistHipRatio.setConclusion(request.getParameter("conclusion"));
			merWaistHipRatioMapper.insert(merWaistHipRatio);
			jsonObj.put(Const.TAG_RESULT, Const.TAG_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		}catch(Exception e){
			e.printStackTrace();
			jsonObj.put(Const.TAG_RESULT, Const.TAG_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	
	public MerBodyFat getMerBodyFat(HttpServletRequest request) throws Exception{
		MerBodyFat merBodyFat = new MerBodyFat();
		try {
			merBodyFat.setUploadTime(DateUtils.getCurrentTime());
			merBodyFat.setMerId(request.getParameter("mer_id"));
			merBodyFat.setBfp(Double.parseDouble(request.getParameter("bfp")));
			merBodyFat.setBf(Double.parseDouble(request.getParameter("bf")));
			merBodyFat.setNonFat(Double.parseDouble(request.getParameter("non_fat")));
			merBodyFat.setBwp(Double.parseDouble(request.getParameter("bwp")));
			merBodyFat.setBw(Double.parseDouble(request.getParameter("bw")));
			merBodyFat.setMineral(Double.parseDouble(request.getParameter("mineral")));
			merBodyFat.setProtein(Double.parseDouble(request.getParameter("protein")));
			merBodyFat.setEcw(Double.parseDouble(request.getParameter("ecw")));
			merBodyFat.setIcw(Double.parseDouble(request.getParameter("icw")));
			merBodyFat.setMuscleMass(Double.parseDouble(request.getParameter("muscle_mass")));
			merBodyFat.setFatRegulation(Double.parseDouble(request.getParameter("fat_regulation")));
			merBodyFat.setWeightRegulation(Double.parseDouble(request.getParameter("weight_regulation")));
			merBodyFat.setMuscleRegulation(Double.parseDouble(request.getParameter("muscle_regulation")));
			merBodyFat.setBmr(Double.parseDouble(request.getParameter("bmr")));
			merBodyFat.setVisceralFatLevel(Double.parseDouble(request.getParameter("visceral_fat_level")));
			merBodyFat.setSkeletalMass(Double.parseDouble(request.getParameter("skeletal_mass")));
			merBodyFat.setMusclePercent(Double.parseDouble(request.getParameter("muscle_percent")));
			merBodyFat.setTrunkFat(Double.parseDouble(request.getParameter("trunk_fat")));
			merBodyFat.setTrunkMuscle(Double.parseDouble(request.getParameter("trunk_muscle")));
			
			merBodyFat.setLeftArmMuscle(Double.parseDouble(request.getParameter("left_arm_muscle")));
			merBodyFat.setRightArmMuscle(Double.parseDouble(request.getParameter("right_arm_muscle")));
			merBodyFat.setLeftLegMuscle(Double.parseDouble(request.getParameter("left_leg_muscle")));
			merBodyFat.setRightLegMuscle(Double.parseDouble(request.getParameter("right_leg_muscle")));
			
			merBodyFat.setLeftArmFat(Double.parseDouble(request.getParameter("left_arm_fat")));
			merBodyFat.setRightArmFat(Double.parseDouble(request.getParameter("right_arm_fat")));
			merBodyFat.setLeftLegFat(Double.parseDouble(request.getParameter("left_leg_fat")));
			merBodyFat.setRightLegFat(Double.parseDouble(request.getParameter("right_leg_fat")));
			
			merBodyFat.setAdvice(request.getParameter("advice"));
			merBodyFat.setConclusion(request.getParameter("conclusion"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e ;
		}
		return merBodyFat; 
	}


}
