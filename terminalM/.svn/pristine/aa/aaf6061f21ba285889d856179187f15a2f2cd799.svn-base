/**
 * 
 */
package com.whty.assis.basicdata.model;

import java.util.Date;
import java.util.List;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年6月20日
 */
public class DataGatherCountLog extends BaseModel {

	private static final long serialVersionUID = 7331410625794240407L;

	private String week;
	private Date startTime;
	private Date endTime;
	private String softwareVersion;
	private String softwareName;
	private String corporateName;
	private String userAccount;
	private String userName;
	private String textbookEdition;
	private Integer subjectId;
	private Integer gradeId;
	private Integer deviceId;
	private Long useTaking;
	private String imgPaths;
	private String subjectName;
	private String gradeName;
	private String useTakingStr;

	
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getUseTakingStr() {
		if (useTaking != null) {
			Long hours = useTaking / 3600000;
			Long minutes = (useTaking % 3600000) / 60000;
			Long seconds = (useTaking % 60000) % 1000;
			useTakingStr = hours + "时" + minutes + "分" + seconds + "秒";
		}
		return useTakingStr;
	}

	public void setUseTakingStr(String useTakingStr) {
		this.useTakingStr = useTakingStr;
	}

	public String getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(String imgPaths) {
		this.imgPaths = imgPaths;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTextbookEdition() {
		return textbookEdition;
	}

	public void setTextbookEdition(String textbookEdition) {
		this.textbookEdition = textbookEdition;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Long getUseTaking() {
		return useTaking;
	}

	public void setUseTaking(Long useTaking) {
		this.useTaking = useTaking;
	}

}
