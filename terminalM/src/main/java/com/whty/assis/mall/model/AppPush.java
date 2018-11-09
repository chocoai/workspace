/**
 * 
 */
package com.whty.assis.mall.model;

import java.util.Date;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年4月18日
 */
public class AppPush extends BaseModel {

	private static final long serialVersionUID = 5603131394946625645L;

	private Integer appId;

	private Integer creator;

	private String provinceCode;

	private String cityCode;

	private String areaCode;

	private String creatorName;

	private String appName;

	private Integer pushDeviceNum;// 推送成功数量

	private Integer pushDeviceQuan;// 推送设备数量

	private Integer pushSuccessNum;// 推送成功数

	private Integer pushFailNum;// 推送失败数

	private Date pushTime;// 推送时间

	private Integer status;// 推送状态

	private Integer schoolId;// 推送的学校

	private Integer classId;

	private String schoolName;

	private String className;

	private String provinceName;

	private String cityName;

	private String areaName;

	private String deleteStatus;

	private String appVersion;

	public Integer getPushDeviceQuan() {
		return pushDeviceQuan;
	}

	public void setPushDeviceQuan(Integer pushDeviceQuan) {
		this.pushDeviceQuan = pushDeviceQuan;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Integer getPushDeviceNum() {
		return pushDeviceNum;
	}

	public void setPushDeviceNum(Integer pushDeviceNum) {
		this.pushDeviceNum = pushDeviceNum;
	}

	public Integer getPushSuccessNum() {
		return pushSuccessNum;
	}

	public void setPushSuccessNum(Integer pushSuccessNum) {
		this.pushSuccessNum = pushSuccessNum;
	}

	public Integer getPushFailNum() {
		return pushFailNum;
	}

	public void setPushFailNum(Integer pushFailNum) {
		this.pushFailNum = pushFailNum;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
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

}
