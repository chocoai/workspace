/**
 * 
 */
package com.whty.assis.basicdata.model;

import java.util.Date;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年5月21日
 */
public class SchoolUser extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String aamPersonId;
	
	private String account;
	
	private String name;
	
	private String mobNum;
	
	private Date birthday;
	
	private Integer gender;
	
	private Integer schoolId;
	
	private Integer userType;
	
	private String grade;
	
	private String classes;
	
	private Integer stNumber;
	
	private Date regiTime;
	
	private String comeFrom;
	
	private String idCardNo;
	
	private String userLogoUrl;
	
	private String provinceCode;
	
	private String cityCode;
	
	private String areaCode;
	
	private String orgaIdentity;
	

	public String getAamPersonId() {
		return aamPersonId;
	}

	public void setAamPersonId(String aamPersonId) {
		this.aamPersonId = aamPersonId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobNum() {
		return mobNum;
	}

	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public Integer getStNumber() {
		return stNumber;
	}

	public void setStNumber(Integer stNumber) {
		this.stNumber = stNumber;
	}

	public Date getRegiTime() {
		return regiTime;
	}

	public void setRegiTime(Date regiTime) {
		this.regiTime = regiTime;
	}

	public String getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getUserLogoUrl() {
		return userLogoUrl;
	}

	public void setUserLogoUrl(String userLogoUrl) {
		this.userLogoUrl = userLogoUrl;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getOrgaIdentity() {
		return orgaIdentity;
	}

	public void setOrgaIdentity(String orgaIdentity) {
		this.orgaIdentity = orgaIdentity;
	}

}
