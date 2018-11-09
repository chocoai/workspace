package com.yhcrt.iHealthCloud.pojo;

import java.util.List;

import com.yhcrt.iHealthCloud.entity.HrOperation;
import com.yhcrt.iHealthCloud.entity.HrTransfusion;
import com.yhcrt.iHealthCloud.entity.HrTrauma;

public class HealthRecordAppView {

	private String name; // 姓名
	private String gender; // 性别
	private String birthday; // 生日
	private String phoneNumber; // 电话号码
	private String company; // 工作单位
	private String idNum; // 身份证号
	private String allergicHistory; // 药物过敏史
	private String communicableDiseasesHistory; // 传染病史
	private String inheritedDiseases; // 遗传病史
	private String disabilityHistory; // 残疾病史
	private String pastMedicalHistory; // 既往病史
	private String familyMedicalHistory; // 家族病史

	// 健康档案-手术记录
	List<HrOperation> operations;

	// 健康档案-输血记录
	List<HrTransfusion> transfusions;

	// 健康档案-外伤记录
	List<HrTrauma> traumas;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getAllergicHistory() {
		return allergicHistory;
	}

	public void setAllergicHistory(String allergicHistory) {
		this.allergicHistory = allergicHistory;
	}

	public String getCommunicableDiseasesHistory() {
		return communicableDiseasesHistory;
	}

	public void setCommunicableDiseasesHistory(String communicableDiseasesHistory) {
		this.communicableDiseasesHistory = communicableDiseasesHistory;
	}

	public String getDisabilityHistory() {
		return disabilityHistory;
	}

	public void setDisabilityHistory(String disabilityHistory) {
		this.disabilityHistory = disabilityHistory;
	}

	public String getInheritedDiseases() {
		return inheritedDiseases;
	}

	public void setInheritedDiseases(String inheritedDiseases) {
		this.inheritedDiseases = inheritedDiseases;
	}

	public String getPastMedicalHistory() {
		return pastMedicalHistory;
	}

	public void setPastMedicalHistory(String pastMedicalHistory) {
		this.pastMedicalHistory = pastMedicalHistory;
	}

	public String getFamilyMedicalHistory() {
		return familyMedicalHistory;
	}

	public void setFamilyMedicalHistory(String familyMedicalHistory) {
		this.familyMedicalHistory = familyMedicalHistory;
	}

	public List<HrOperation> getOperations() {
		return operations;
	}

	public void setOperations(List<HrOperation> operations) {
		this.operations = operations;
	}

	public List<HrTransfusion> getTransfusions() {
		return transfusions;
	}

	public void setTransfusions(List<HrTransfusion> transfusions) {
		this.transfusions = transfusions;
	}

	public List<HrTrauma> getTraumas() {
		return traumas;
	}

	public void setTraumas(List<HrTrauma> traumas) {
		this.traumas = traumas;
	}

	
}
