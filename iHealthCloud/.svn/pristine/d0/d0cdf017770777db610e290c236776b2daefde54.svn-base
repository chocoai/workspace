package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.entity.MedicalExaminationReport;
import com.yhcrt.iHealthCloud.entity.MedicalExaminationReportExample;
import com.yhcrt.iHealthCloud.entity.MedicalExaminationReportUnion;
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
import com.yhcrt.iHealthCloud.mapper.MedicalExaminationReportMapper;
import com.yhcrt.iHealthCloud.service.MerService;
import com.yhcrt.iHealthCloud.util.Const;

/**
 * 
 * @author huzelin
 *
 */
@Service
public class MerServiceImpl extends BaseService implements MerService {

	// debug mode
	// protected boolean isDebug = true;
	@Autowired
	private MedicalExaminationReportMapper merMapper;

	@Override
	public String getMerByMerId(JSONObject pdataObj) {
		// 获取参数
		String merId = getMerId(pdataObj);
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, merId)) {
			// 根据merId查询体检报告
			MedicalExaminationReport mer = merMapper.selectByPrimaryKey(Integer.parseInt(merId));
			// 判断是否有报告
			if (mer != null) {
				JSONObject result = setResultData(mer.getMerId());
				pdataObj.put(Const.TAG_BIZ, result);
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			} else {
				requestFailed(pdataObj, "no such record");
			}
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getMerListByMemberId(JSONObject pdataObj) {
		// 拿到memberId的值
		String memberId = getMemberId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageLength = getPageSize(pdataObj);
		Integer pageNum = StringUtils.isNotBlank(currentPage) ? Integer.parseInt(currentPage) : 1;
		Integer pageSize = StringUtils.isNotBlank(pageLength) ? Integer.parseInt(pageLength) : 10;
		
		// 对条件参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId)) {
			// 根据memberId查询数据
			MedicalExaminationReportExample example = new MedicalExaminationReportExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			example.setOrderByClause("mer_time desc");
			PageHelper.startPage(pageNum, pageSize);
			List<MedicalExaminationReport> mers = merMapper.selectByExample(example);
			PageInfo<MedicalExaminationReport> p = new PageInfo<MedicalExaminationReport>(mers);
			setPagingData(pdataObj, p.getPages(), p.getPageNum());
			requestSucceed(pdataObj, mers, "");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public List<MedicalExaminationReport> getMerListByMemberId(String memberId) {
		MedicalExaminationReportExample example = new MedicalExaminationReportExample();
		example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
		example.setOrderByClause("mer_time desc");
		return merMapper.selectByExample(example);
	}

	@Override
	public String getLatestMerByMemberId(JSONObject pdataObj) {
		// 获取参数
		String memberId = getMemberId(pdataObj);
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId)) {
			// 向数据库请求数据
			MedicalExaminationReportExample merExample = new MedicalExaminationReportExample();
			merExample.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			merExample.setOrderByClause("mer_time desc");
			List<MedicalExaminationReport> merList = merMapper.selectByExample(merExample);
			// 判断是否有数据
			if (merList.size() > 0) {
				MedicalExaminationReport mer = merList.get(0);
				JSONObject result = setResultData(mer.getMerId());
				pdataObj.put(Const.TAG_BIZ, result);
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			} else {
				requestFailed(pdataObj, Const.NO_MER_RECORD);
			}
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	private JSONObject setResultData(Integer merId) {
		// 获取体检报告中的merId，用于查询具体的数据
		JSONObject result = new JSONObject();
		MedicalExaminationReportUnion merUnion = merMapper.getMedicalExaminationReportUnionById(merId);
		// body and fat 体脂检测
		MerBodyFat bodyFat = merUnion.getBodyFat();
		result.put("bodyFat", bodyFat == null ? new MerBodyFat() : bodyFat);
		// blood glucose 血糖
		MerBloodGlucose bloodGlucose = merUnion.getBloodGlucose();
		result.put("bloodGlucose", bloodGlucose == null ? new MerBloodGlucose() : bloodGlucose);
		// blood oxygen 血氧
		MerBloodOxygen bloodOxygen = merUnion.getBloodOxygen();
		result.put("bloodOxygen", bloodOxygen == null ? new MerBloodOxygen() : bloodOxygen);
		// blood pressure 血压
		MerBloodPressure bloodPressure = merUnion.getBloodPressure();
		result.put("bloodPressure", bloodPressure == null ? new MerBloodPressure() : bloodPressure);
		// bmi 身高，体重
		MerBmi bmi = merUnion.getBmi();
		result.put("bmi", bmi == null ? new MerBmi() : bmi);
		// cholesterol 胆固醇测量
		MerCholesterol cholesterol = merUnion.getCholesterol();
		result.put("cholesterol", cholesterol == null ? new MerCholesterol() : cholesterol);
		// electrocardiogram 心电测量
		MerElectrocardiogram electrocardiogram = merUnion.getElectrocardiogram();
		result.put("electrocardiogram", electrocardiogram == null ? new MerElectrocardiogram() : electrocardiogram);
		// electrocardiogram 体表温度
		MerTemperature temperature = merUnion.getTemperature();
		result.put("temperature", temperature == null ? new MerTemperature() : temperature);
		// uricAcid 尿酸测量
		MerUricAcid uricAcid = merUnion.getUricAcid();
		result.put("uricAcid", uricAcid == null ? new MerUricAcid() : uricAcid);
		// waistHipRatio 腰臀比测量
		MerWaistHipRatio waistHipRatio = merUnion.getWaistHipRatio();
		result.put("waistHipRatio", waistHipRatio == null ? new MerWaistHipRatio() : waistHipRatio);
		return result;
	}

}
