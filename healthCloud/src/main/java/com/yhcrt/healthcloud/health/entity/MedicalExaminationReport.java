package com.yhcrt.healthcloud.health.entity;

import com.yhcrt.healthcloud.memberBack.entity.MemberBack;

public class MedicalExaminationReport {
    private Integer merId;

    private Integer memberId;

    private String merTime;
    
    private MemberBack member;

	private MerBloodGlucose merBloodGlucose;
    
    private MerBloodOxygen bloodOxygen;
    
    private MerBloodPressure bloodPressure;
    
    private MerBmi bmi;
    
    private MerBodyFat bodyFat;
    
    private MerCholesterol cholesterol;
    
    private MerElectrocardiogram electrocardiogram;
    
    private MerTemperature merTemperature;
    
    private MerUricAcid acid;
    
    private MerWaistHipRatio hipRatio;
    
    public MerBloodGlucose getMerBloodGlucose() {
		return merBloodGlucose;
	}

	public void setMerBloodGlucose(MerBloodGlucose merBloodGlucose) {
		this.merBloodGlucose = merBloodGlucose;
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

	public MerTemperature getMerTemperature() {
		return merTemperature;
	}

	public void setMerTemperature(MerTemperature merTemperature) {
		this.merTemperature = merTemperature;
	}

	public MerUricAcid getAcid() {
		return acid;
	}

	public void setAcid(MerUricAcid acid) {
		this.acid = acid;
	}

	public MerWaistHipRatio getHipRatio() {
		return hipRatio;
	}

	public void setHipRatio(MerWaistHipRatio hipRatio) {
		this.hipRatio = hipRatio;
	}

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

	public MemberBack getMember() {
		return member;
	}

	public void setMember(MemberBack member) {
		this.member = member;
	}
    
}