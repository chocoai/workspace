package com.yhcrt.healthcloud.health.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yhcrt.healthcloud.health.entity.MedicalExaminationReportExample.Criteria;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.health.entity.MedicalExaminationReport;
import com.yhcrt.healthcloud.health.entity.MedicalExaminationReportExample;
import com.yhcrt.healthcloud.health.entity.MerBloodGlucose;
import com.yhcrt.healthcloud.health.entity.MerBloodGlucoseExample;
import com.yhcrt.healthcloud.health.entity.MerBloodOxygen;
import com.yhcrt.healthcloud.health.entity.MerBloodOxygenExample;
import com.yhcrt.healthcloud.health.entity.MerBloodPressure;
import com.yhcrt.healthcloud.health.entity.MerBloodPressureExample;
import com.yhcrt.healthcloud.health.entity.MerBmi;
import com.yhcrt.healthcloud.health.entity.MerBmiExample;
import com.yhcrt.healthcloud.health.entity.MerBodyFat;
import com.yhcrt.healthcloud.health.entity.MerBodyFatExample;
import com.yhcrt.healthcloud.health.entity.MerCholesterol;
import com.yhcrt.healthcloud.health.entity.MerCholesterolExample;
import com.yhcrt.healthcloud.health.entity.MerElectrocardiogram;
import com.yhcrt.healthcloud.health.entity.MerElectrocardiogramExample;
import com.yhcrt.healthcloud.health.entity.MerTemperature;
import com.yhcrt.healthcloud.health.entity.MerTemperatureExample;
import com.yhcrt.healthcloud.health.entity.MerUricAcid;
import com.yhcrt.healthcloud.health.entity.MerUricAcidExample;
import com.yhcrt.healthcloud.health.entity.MerWaistHipRatio;
import com.yhcrt.healthcloud.health.entity.MerWaistHipRatioExample;
import com.yhcrt.healthcloud.health.mapper.MedicalExaminationReportMapper;
import com.yhcrt.healthcloud.health.mapper.MerBloodGlucoseMapper;
import com.yhcrt.healthcloud.health.mapper.MerBloodOxygenMapper;
import com.yhcrt.healthcloud.health.mapper.MerBloodPressureMapper;
import com.yhcrt.healthcloud.health.mapper.MerBmiMapper;
import com.yhcrt.healthcloud.health.mapper.MerBodyFatMapper;
import com.yhcrt.healthcloud.health.mapper.MerCholesterolMapper;
import com.yhcrt.healthcloud.health.mapper.MerElectrocardiogramMapper;
import com.yhcrt.healthcloud.health.mapper.MerTemperatureMapper;
import com.yhcrt.healthcloud.health.mapper.MerUricAcidMapper;
import com.yhcrt.healthcloud.health.mapper.MerWaistHipRatioMapper;
import com.yhcrt.healthcloud.health.service.MerService;
import com.yhcrt.healthcloud.system.service.SysSequenceService;
import com.yhcrt.healthcloud.util.DateUtil;

@Service
public class MerServiceImpl implements MerService {

	@Autowired
	private MedicalExaminationReportMapper merMapper;
	@Autowired
	private MerBloodGlucoseMapper bloodGlucoseMapper;
	@Autowired
	private MerBloodPressureMapper bloodPressureMapper;
	@Autowired
	private MerBloodOxygenMapper bloodOxygenMapper;
	@Autowired
	private MerBmiMapper bmiMapper;
	@Autowired
	private MerBodyFatMapper bodyFatMapper;
	@Autowired
	private MerCholesterolMapper cholesterolMapper;
	@Autowired
	private MerElectrocardiogramMapper electrocardiogramMapper;
	@Autowired
	private MerTemperatureMapper temperatureMapper;
	@Autowired
	private MerUricAcidMapper uricAcidMapper;
	@Autowired
	private MerWaistHipRatioMapper waistHipRatioMapper;

	@Autowired
	private SysSequenceService sysSequenceService;

	/*------------glucose-------------*/
	@Override
	public MerBloodGlucose getOneGlucoseByCid(int cid) {
		return bloodGlucoseMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerBloodGlucose getGlucoseByMerId(String merId) {
		MerBloodGlucoseExample example = new MerBloodGlucoseExample();
		example.createCriteria().andMerIdEqualTo(merId);
		List<MerBloodGlucose> bloodGlucoses = bloodGlucoseMapper.selectByExample(example);
		return bloodGlucoses.size() > 0 ? bloodGlucoses.get(0) : new MerBloodGlucose();
	}

	@Override
	public List<MerBloodGlucose> getGlucoseByBgType(int bgType) {
		MerBloodGlucoseExample example = new MerBloodGlucoseExample();
		example.createCriteria().andBgTypeEqualTo(bgType);
		return bloodGlucoseMapper.selectByExample(example);
	}

	@Override
	public List<MerBloodGlucose> getGlucoseByUploadTime(Date startTime, Date endTime) {
		MerBloodGlucoseExample example = new MerBloodGlucoseExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return bloodGlucoseMapper.selectByExample(example);
	}

	/*------------pressure-------------*/
	@Override
	public MerBloodPressure getOnePressureByCid(int cid) {
		return bloodPressureMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerBloodPressure getPressureByMerId(String merId) {
		if (StringUtils.isNotBlank(merId)) {
			MerBloodPressureExample example = new MerBloodPressureExample();
			example.createCriteria().andMerIdEqualTo(Integer.parseInt(merId));
			List<MerBloodPressure> bloodPressures = bloodPressureMapper.selectByExample(example);
			return bloodPressures.size() > 0 ? bloodPressures.get(0) : null;
		}
		return null;
	}

	@Override
	public List<MerBloodPressure> getPressureByUploadTime(Date startTime, Date endTime) {
		MerBloodPressureExample example = new MerBloodPressureExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return bloodPressureMapper.selectByExample(example);
	}

	/*------------oxygen-------------*/
	@Override
	public MerBloodOxygen getOneOxygenByCid(int cid) {
		return bloodOxygenMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerBloodOxygen getOxygenByMerId(String merId) {
		if (StringUtils.isNotBlank(merId)) {
			MerBloodOxygenExample example = new MerBloodOxygenExample();
			example.createCriteria().andMerIdEqualTo(Integer.parseInt(merId));
			List<MerBloodOxygen> bloodOxygens = bloodOxygenMapper.selectByExample(example);
			return bloodOxygens.size() > 0 ? bloodOxygens.get(0) : null;
		}
		return null;
	}

	@Override
	public List<MerBloodOxygen> getOxygenByUploadTime(Date startTime, Date endTime) {
		MerBloodOxygenExample example = new MerBloodOxygenExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return bloodOxygenMapper.selectByExample(example);
	}

	/*------------bmi-------------*/
	@Override
	public MerBmi getOneBmiByCid(int cid) {
		return bmiMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerBmi getBmiByMerId(String merId) {
		MerBmiExample example = new MerBmiExample();
		example.createCriteria().andMerIdEqualTo(merId);
		List<MerBmi> bmis = bmiMapper.selectByExample(example);
		return bmis.size() > 0 ? bmis.get(0) : null;
	}

	@Override
	public List<MerBmi> getBmiByUploadTime(Date startTime, Date endTime) {
		MerBmiExample example = new MerBmiExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return bmiMapper.selectByExample(example);
	}

	/*------------bodyFat-------------*/
	@Override
	public MerBodyFat getOneBodyFatByCid(int cid) {
		return bodyFatMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerBodyFat getBodyFatByMerId(String merId) {
		MerBodyFatExample example = new MerBodyFatExample();
		example.createCriteria().andMerIdEqualTo(String.valueOf(merId));
		List<MerBodyFat> bodyFats = bodyFatMapper.selectByExample(example);
		return bodyFats.size() > 0 ? bodyFats.get(0) : null;
	}

	@Override
	public List<MerBodyFat> getBodyFatByUploadTime(Date startTime, Date endTime) {
		MerBodyFatExample example = new MerBodyFatExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return bodyFatMapper.selectByExample(example);
	}

	/*------------cholesterol-------------*/
	@Override
	public MerCholesterol getOneCholesterolByCid(int cid) {
		return cholesterolMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerCholesterol getCholesterolByMerId(String merId) {
		MerCholesterolExample example = new MerCholesterolExample();
		example.createCriteria().andMerIdEqualTo(merId);
		List<MerCholesterol> cholesterols = cholesterolMapper.selectByExample(example);
		return cholesterols.size() > 0 ? cholesterols.get(0) : null;
	}

	@Override
	public List<MerCholesterol> getCholesterolByUploadTime(Date startTime, Date endTime) {
		MerCholesterolExample example = new MerCholesterolExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return cholesterolMapper.selectByExample(example);
	}

	/*------------electrocardiogram-------------*/
	@Override
	public MerElectrocardiogram getOneElectrocardiogramByCid(int cid) {
		return electrocardiogramMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerElectrocardiogram getElectrocardiogramByMerId(String merId) {
		MerElectrocardiogramExample example = new MerElectrocardiogramExample();
		example.createCriteria().andMerIdEqualTo(String.valueOf(merId));
		List<MerElectrocardiogram> electrocardiograms = electrocardiogramMapper.selectByExample(example);
		return electrocardiograms.size() > 0 ? electrocardiograms.get(0) : null;
	}

	@Override
	public List<MerElectrocardiogram> getElectrocardiogramByUploadTime(Date startTime, Date endTime) {
		MerElectrocardiogramExample example = new MerElectrocardiogramExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return electrocardiogramMapper.selectByExample(example);
	}

	/*------------temperature-------------*/
	@Override
	public MerTemperature getOneTemperatureByCid(int cid) {
		return temperatureMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerTemperature getTemperatureByMerId(String merId) {
		if (StringUtils.isNotBlank(merId)) {
			MerTemperatureExample example = new MerTemperatureExample();
			example.createCriteria().andMerIdEqualTo(Integer.parseInt(merId));
			List<MerTemperature> temperatures = temperatureMapper.selectByExample(example);
			return temperatures.size() > 0 ? temperatures.get(0) : null;
		}
		return null;
	}

	@Override
	public List<MerTemperature> getTemperaturesByUploadTime(Date startTime, Date endTime) {
		MerTemperatureExample example = new MerTemperatureExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return temperatureMapper.selectByExample(example);
	}

	/*------------uricAcid-------------*/
	@Override
	public MerUricAcid getOneUricAcidByCid(int cid) {
		return uricAcidMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerUricAcid getUricAcidByMerId(String merId) {
		MerUricAcidExample example = new MerUricAcidExample();
		example.createCriteria().andMerIdEqualTo(merId);
		List<MerUricAcid> uricAcids = uricAcidMapper.selectByExample(example);
		return uricAcids.size() > 0 ? uricAcids.get(0) : null;
	}

	@Override
	public List<MerUricAcid> getUricAcidByUploadTime(Date startTime, Date endTime) {
		MerUricAcidExample example = new MerUricAcidExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return uricAcidMapper.selectByExample(example);
	}

	/*------------waistHipRatio-------------*/
	@Override
	public MerWaistHipRatio getOneWaistHipRatioByCid(int cid) {
		return waistHipRatioMapper.selectByPrimaryKey(cid);
	}

	@Override
	public MerWaistHipRatio getWaistHipRatioByMerId(String merId) {
		if (StringUtils.isNotBlank(merId)) {
			MerWaistHipRatioExample example = new MerWaistHipRatioExample();
			example.createCriteria().andMerIdEqualTo(Integer.parseInt(merId));
			List<MerWaistHipRatio> waistHipRatios = waistHipRatioMapper.selectByExample(example);
			return waistHipRatios.size() > 0 ? waistHipRatios.get(0) : null;
		}
		return null;
		
	}

	@Override
	public List<MerWaistHipRatio> getWaistHipRatioByUploadTime(Date startTime, Date endTime) {
		MerWaistHipRatioExample example = new MerWaistHipRatioExample();
		example.createCriteria().andUploadTimeBetween(startTime.toString(), endTime.toString());
		return waistHipRatioMapper.selectByExample(example);
	}

	/*------------mer-------------*/
	@Override
	public MedicalExaminationReport getMerByMerId(int merId) {
		return merMapper.selectByPrimaryKey(merId);
	}

	@Override
	public List<MedicalExaminationReport> getMerByMemberId(int memberId) {
		MedicalExaminationReportExample example = new MedicalExaminationReportExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		example.setOrderByClause("mer_time desc");
		return merMapper.selectByExample(example);
	}

	@Override
	public List<MedicalExaminationReport> getMerByTime(Date startTime, Date endTime) {
		MedicalExaminationReportExample example = new MedicalExaminationReportExample();
		example.createCriteria().andMerTimeBetween(startTime.toString(), endTime.toString());
		example.setOrderByClause("mer_time desc");
		return merMapper.selectByExample(example);
	}

	@Override
	public List<MedicalExaminationReport> selectByMemberId(Integer memberId) {
		return merMapper.selectByMemberId(memberId);
	}

	@Override
	public MedicalExaminationReport selectByPrimaryKey(Integer merId) {
		return merMapper.selectByPrimaryKey(merId);
	}

	@Override
	public List<MedicalExaminationReport> getMersByArgs(HashMap<String, Object> args) {
		MedicalExaminationReportExample example = new MedicalExaminationReportExample();
		Criteria criteria = example.createCriteria();
		if (args.get("memberId") != null) {
			criteria.andMemberIdEqualTo(Integer.parseInt(args.get("memberId").toString()));
		}
		if (args.get("merId") != null) {
			criteria.andMerIdEqualTo(Integer.parseInt(args.get("merId").toString()));
		}
		if (args.get("merTime") != null) {
			criteria.andMerTimeLike((String) args.get("merTime") + "%");
		}
		example.setOrderByClause(" mer_time desc");
		return merMapper.selectByExample(example);
	}

	@Override
	public int updateMerBmi(MerBmi merBmi) {
		return bmiMapper.updateByPrimaryKey(merBmi);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerBmi insertMerBmi(MerBmi merBmi, String memberId) {
		try {
			if (StringUtils.isBlank(merBmi.getMerId())) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				merBmi.setMerId(mer.getMerId().toString());
			}
			merBmi.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_BMI));
			merBmi.setUploadTime(DateUtil.getDateTime());
			bmiMapper.insert(merBmi);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return merBmi;
	}

	@Override
	public int updateMerBodyFat(MerBodyFat merBodyFat) {
		return bodyFatMapper.updateByPrimaryKey(merBodyFat);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerBodyFat insertMerBodyFat(MerBodyFat merBodyFat, String memberId) {
		try {
			if (StringUtils.isBlank(merBodyFat.getMerId())) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				merBodyFat.setMerId(mer.getMerId().toString());
			}
			merBodyFat.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_BODY_FAT));
			merBodyFat.setUploadTime(DateUtil.getDateTime());
			bodyFatMapper.insert(merBodyFat);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return merBodyFat;
	}

	@Override
	public int updateMerBP(MerBloodPressure bloodPressure) {
		return bloodPressureMapper.updateByPrimaryKey(bloodPressure);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerBloodPressure insertMerBP(MerBloodPressure bloodPressure, String memberId) {
		try {
			if (null == bloodPressure.getMerId()) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				bloodPressure.setMerId(mer.getMerId());
			}
			bloodPressure.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_BLOOD_PRESSURE));
			bloodPressure.setUploadTime(DateUtil.getDateTime());
			bloodPressure.setDataDate(new Date());
			bloodPressureMapper.insert(bloodPressure);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return bloodPressure;
	}

	@Override
	public int updateMerBG(MerBloodGlucose bloodGlucose) {
		return bloodGlucoseMapper.updateByPrimaryKey(bloodGlucose);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerBloodGlucose insertMerBG(MerBloodGlucose bloodGlucose, String memberId) {
		try {
			if (StringUtils.isBlank(bloodGlucose.getMerId())) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				bloodGlucose.setMerId(mer.getMerId().toString());
			}
			bloodGlucose.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_BLOOD_GLUCOSE));
			bloodGlucose.setUploadTime(DateUtil.getDateTime());
			bloodGlucose.setDataTime(DateUtil.getDate());
			bloodGlucoseMapper.insert(bloodGlucose);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return bloodGlucose;
	}

	@Override
	public int updateMerUricAcid(MerUricAcid merUricAcid) {
		return uricAcidMapper.updateByPrimaryKey(merUricAcid);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerUricAcid insertMerUricAcid(MerUricAcid merUricAcid, String memberId) {
		try {
			if (StringUtils.isBlank(merUricAcid.getMerId())) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				merUricAcid.setMerId(mer.getMerId().toString());
			}
			merUricAcid.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_URIC_ACID));
			merUricAcid.setUploadTime(DateUtil.getDateTime());
			uricAcidMapper.insert(merUricAcid);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return merUricAcid;
	}

	@Override
	public int updateMerCholesterol(MerCholesterol cholesterol) {
		return cholesterolMapper.updateByPrimaryKey(cholesterol);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerCholesterol insertMerCholesterol(MerCholesterol cholesterol, String memberId) {
		try {
			if (StringUtils.isBlank(cholesterol.getMerId())) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				cholesterol.setMerId(mer.getMerId().toString());
			}
			cholesterol.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_CHOLESTEROL));
			cholesterol.setUploadTime(DateUtil.getDateTime());
			cholesterolMapper.insert(cholesterol);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return cholesterol;
	}

	@Override
	public int updateMerBO(MerBloodOxygen bloodOxygen) {
		return bloodOxygenMapper.updateByPrimaryKey(bloodOxygen);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerBloodOxygen insertMerBO(MerBloodOxygen bloodOxygen, String memberId) {
		try {
			if (null == bloodOxygen.getMerId()) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				bloodOxygen.setMerId(mer.getMerId());
			}
			bloodOxygen.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_BLOOD_OXYGEN));
			bloodOxygen.setUploadTime(DateUtil.getDateTime());
			bloodOxygen.setDataTime(DateUtil.getDate());
			bloodOxygenMapper.insert(bloodOxygen);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return bloodOxygen;
	}

	@Override
	public int updateMerECG(MerElectrocardiogram electrocardiogram) {
		return electrocardiogramMapper.updateByPrimaryKey(electrocardiogram);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerElectrocardiogram insertMerECG(MerElectrocardiogram electrocardiogram, String memberId) {
		try {
			if (StringUtils.isBlank(electrocardiogram.getMerId())) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				electrocardiogram.setMerId(mer.getMerId().toString());
			}
			electrocardiogram.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_ELECTROCARDIOGRAM));
			electrocardiogram.setUploadTime(DateUtil.getDateTime());
			electrocardiogramMapper.insert(electrocardiogram);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return electrocardiogram;
	}

	@Override
	public int updateMerTemperature(MerTemperature temperature) {
		return temperatureMapper.updateByPrimaryKey(temperature);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerTemperature insertMerTemperature(MerTemperature temperature, String memberId) {
		try {
			if (null == temperature.getMerId()) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				temperature.setMerId(mer.getMerId());
			}
			temperature.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_TEMPERATURE));
			temperature.setUploadTime(DateUtil.getDateTime());
			temperatureMapper.insert(temperature);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return temperature;
	}

	@Override
	public int updateMerWHR(MerWaistHipRatio waistHipRatio) {
		return waistHipRatioMapper.updateByPrimaryKey(waistHipRatio);
	}
	
	@Transactional(rollbackFor = { Exception.class })
	@Override
	public MerWaistHipRatio insertMerWHR(MerWaistHipRatio waistHipRatio, String memberId) {
		try {
			if (null == waistHipRatio.getMerId()) {
				MedicalExaminationReport mer = new MedicalExaminationReport();
				mer.setMerId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEDICAL_EXAMINATION_REPORT));
				mer.setMemberId(Integer.parseInt(memberId));
				mer.setMerTime(DateUtil.getDateTime());
				merMapper.insert(mer);
				waistHipRatio.setMerId(mer.getMerId());
			}
			waistHipRatio.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.MER_TEMPERATURE));
			waistHipRatio.setUploadTime(DateUtil.getDateTime());
			waistHipRatioMapper.insert(waistHipRatio);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return waistHipRatio;
	}

}
