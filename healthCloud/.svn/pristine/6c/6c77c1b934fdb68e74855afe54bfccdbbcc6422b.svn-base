package com.yhcrt.healthcloud.health.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yhcrt.healthcloud.health.entity.MedicalExaminationReport;
import com.yhcrt.healthcloud.health.entity.MerBloodGlucose;
import com.yhcrt.healthcloud.health.entity.MerBloodOxygen;
import com.yhcrt.healthcloud.health.entity.MerBloodPressure;
import com.yhcrt.healthcloud.health.entity.MerBmi;
import com.yhcrt.healthcloud.health.entity.MerBodyFat;
import com.yhcrt.healthcloud.health.entity.MerCholesterol;
import com.yhcrt.healthcloud.health.entity.MerElectrocardiogram;
import com.yhcrt.healthcloud.health.entity.MerTemperature;
import com.yhcrt.healthcloud.health.entity.MerUricAcid;
import com.yhcrt.healthcloud.health.entity.MerWaistHipRatio;

/**
 * 体检报告服务接口
 * 
 * @author huzelin
 *
 */
public interface MerService {

	/*--------------------体检报告--------------------*/

	/**
	 * 根据memberId获取体检报告集合
	 * 
	 * @param memberId
	 *            会员编号
	 * @return 体检报告集合
	 */
	public List<MedicalExaminationReport> selectByMemberId(Integer memberId);

	/**
	 * 根据体检报告编号获取体检报告
	 * 
	 * @param merId
	 *            体检报告编号
	 * @return 体检报告对象
	 */
	public MedicalExaminationReport selectByPrimaryKey(Integer merId);

	public MedicalExaminationReport getMerByMerId(int merId);

	public List<MedicalExaminationReport> getMerByMemberId(int memberId);

	public List<MedicalExaminationReport> getMerByTime(Date startTime, Date endTime);

	public List<MedicalExaminationReport> getMersByArgs(HashMap<String, Object> args);

	/*--------------------血糖--------------------*/

	public MerBloodGlucose getOneGlucoseByCid(int cid);

	public MerBloodGlucose getGlucoseByMerId(String merId);

	public List<MerBloodGlucose> getGlucoseByBgType(int bgType);

	public List<MerBloodGlucose> getGlucoseByUploadTime(Date startTime, Date endTime);

	public int updateMerBG(MerBloodGlucose bloodGlucose);

	public MerBloodGlucose insertMerBG(MerBloodGlucose bloodGlucose, String memberId);

	/*--------------------血压--------------------*/

	public MerBloodPressure getOnePressureByCid(int cid);

	public MerBloodPressure getPressureByMerId(String merId);

	public List<MerBloodPressure> getPressureByUploadTime(Date startTime, Date endTime);

	public int updateMerBP(MerBloodPressure bloodPressure);

	public MerBloodPressure insertMerBP(MerBloodPressure bloodPressure, String memberId);

	/*--------------------血氧--------------------*/

	public MerBloodOxygen getOneOxygenByCid(int cid);

	public MerBloodOxygen getOxygenByMerId(String merId);

	public List<MerBloodOxygen> getOxygenByUploadTime(Date startTime, Date endTime);

	public int updateMerBO(MerBloodOxygen bloodOxygen);

	public MerBloodOxygen insertMerBO(MerBloodOxygen bloodOxygen, String memberId);

	/*--------------------bmi--------------------*/

	public MerBmi getOneBmiByCid(int cid);

	public MerBmi getBmiByMerId(String merId);

	public List<MerBmi> getBmiByUploadTime(Date startTime, Date endTime);

	public int updateMerBmi(MerBmi merBmi);

	public MerBmi insertMerBmi(MerBmi merBmi, String memberId);

	/*--------------------体脂比--------------------*/

	public MerBodyFat getOneBodyFatByCid(int cid);

	public MerBodyFat getBodyFatByMerId(String merId);

	public List<MerBodyFat> getBodyFatByUploadTime(Date startTime, Date endTime);

	public int updateMerBodyFat(MerBodyFat merBodyFat);

	public MerBodyFat insertMerBodyFat(MerBodyFat merBodyFat, String memberId);

	/*--------------------胆固醇--------------------*/

	public MerCholesterol getOneCholesterolByCid(int cid);

	public MerCholesterol getCholesterolByMerId(String merId);

	public List<MerCholesterol> getCholesterolByUploadTime(Date startTime, Date endTime);

	public int updateMerCholesterol(MerCholesterol cholesterol);

	public MerCholesterol insertMerCholesterol(MerCholesterol cholesterol, String memberId);

	/*--------------------心电图--------------------*/

	public MerElectrocardiogram getOneElectrocardiogramByCid(int cid);

	public MerElectrocardiogram getElectrocardiogramByMerId(String merId);

	public List<MerElectrocardiogram> getElectrocardiogramByUploadTime(Date startTime, Date endTime);
	
	public int updateMerECG(MerElectrocardiogram electrocardiogram);

	public MerElectrocardiogram insertMerECG(MerElectrocardiogram electrocardiogram, String memberId);

	/*--------------------体温--------------------*/

	public MerTemperature getOneTemperatureByCid(int cid);

	public MerTemperature getTemperatureByMerId(String merId);

	public List<MerTemperature> getTemperaturesByUploadTime(Date startTime, Date endTime);
	
	public int updateMerTemperature(MerTemperature temperature);

	public MerTemperature insertMerTemperature(MerTemperature temperature, String memberId);

	/*--------------------尿酸--------------------*/

	public MerUricAcid getOneUricAcidByCid(int cid);

	public MerUricAcid getUricAcidByMerId(String merId);

	public List<MerUricAcid> getUricAcidByUploadTime(Date startTime, Date endTime);

	public int updateMerUricAcid(MerUricAcid merUricAcid);

	public MerUricAcid insertMerUricAcid(MerUricAcid merUricAcid, String memberId);

	/*--------------------腰臀比--------------------*/

	public MerWaistHipRatio getOneWaistHipRatioByCid(int cid);

	public MerWaistHipRatio getWaistHipRatioByMerId(String merId);

	public List<MerWaistHipRatio> getWaistHipRatioByUploadTime(Date startTime, Date endTime);
	
	public int updateMerWHR(MerWaistHipRatio waistHipRatio);

	public MerWaistHipRatio insertMerWHR(MerWaistHipRatio waistHipRatio, String memberId);


}
