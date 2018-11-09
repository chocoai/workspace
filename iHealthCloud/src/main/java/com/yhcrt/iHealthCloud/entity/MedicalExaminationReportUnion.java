package com.yhcrt.iHealthCloud.entity;

public class MedicalExaminationReportUnion {
	private Integer merId;

	private Integer memberId;

	private String merTime;

	private MerBmi bmi;

	private MerBloodGlucose bloodGlucose;

	private MerBloodPressure bloodPressure;

	private MerBloodOxygen bloodOxygen;

	private MerBodyFat bodyFat;

	private MerCholesterol cholesterol;

	private MerElectrocardiogram electrocardiogram;

	private MerTemperature temperature;

	private MerUricAcid uricAcid;

	private MerWaistHipRatio waistHipRatio;

	public Integer getMerId() {
		return merId;
	}

	public void setMerId(Integer merId) {
		this.merId = merId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMerTime() {
		return merTime;
	}

	public void setMerTime(String merTime) {
		this.merTime = merTime == null ? null : merTime.trim();
	}

	public MerBloodGlucose getBloodGlucose() {
		return bloodGlucose;
	}

	public void setBloodGlucose(MerBloodGlucose bloodGlucose) {
		this.bloodGlucose = bloodGlucose;
	}

	public MerBloodOxygen getBloodOxygen() {
		return bloodOxygen;
	}

	public void setBloodOxygen(MerBloodOxygen bloodOxygen) {
		this.bloodOxygen = bloodOxygen;
	}

	public MerBloodPressure getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(MerBloodPressure bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public MerBmi getBmi() {
		return bmi;
	}

	public void setBmi(MerBmi bmi) {
		this.bmi = bmi;
	}

	public MerBodyFat getBodyFat() {
		return bodyFat;
	}

	public void setBodyFat(MerBodyFat bodyFat) {
		this.bodyFat = bodyFat;
	}

	public MerCholesterol getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(MerCholesterol cholesterol) {
		this.cholesterol = cholesterol;
	}

	public MerElectrocardiogram getElectrocardiogram() {
		return electrocardiogram;
	}

	public void setElectrocardiogram(MerElectrocardiogram electrocardiogram) {
		this.electrocardiogram = electrocardiogram;
	}

	public MerTemperature getTemperature() {
		return temperature;
	}

	public void setTemperature(MerTemperature temperature) {
		this.temperature = temperature;
	}

	public MerUricAcid getUricAcid() {
		return uricAcid;
	}

	public void setUricAcid(MerUricAcid uricAcid) {
		this.uricAcid = uricAcid;
	}

	public MerWaistHipRatio getWaistHipRatio() {
		return waistHipRatio;
	}

	public void setWaistHipRatio(MerWaistHipRatio waistHipRatio) {
		this.waistHipRatio = waistHipRatio;
	}

}