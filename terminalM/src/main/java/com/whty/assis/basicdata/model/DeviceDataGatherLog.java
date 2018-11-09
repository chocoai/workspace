/**
 * 
 */
package com.whty.assis.basicdata.model;

import java.util.Date;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年6月1日
 */
public class DeviceDataGatherLog extends BaseModel {

	private static final long serialVersionUID = -7365798257493160100L;

	private String week;
	private Date useDate;
	private Integer subjectId;
	private String subjectName;
	private Integer gradeId;
	private String gradeName;
	private String softwareName;
	private String softwareVersion;
	private String corporateName;
	private String useAccount;
	private String textbookEdition;
	private Integer deviceId;
	private Long useTaking;
	private String userName;
	private String useTakingStr;

	private String imgPaths;

	public String getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(String imgPaths) {
		this.imgPaths = imgPaths;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUseTakingStr() {
		if (useTaking != null) {
			Long h = useTaking / 3600;
			Long m = (useTaking % 3600) / 60;
			Long s = (useTaking % 3600) % 60;
			useTakingStr = h + "时" + m + "分" + s + "秒";
		}

		return useTakingStr;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public void setUseTakingStr(String useTakingStr) {
		this.useTakingStr = useTakingStr;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getUseAccount() {
		return useAccount;
	}

	public void setUseAccount(String useAccount) {
		this.useAccount = useAccount;
	}

	public String getTextbookEdition() {
		return textbookEdition;
	}

	public void setTextbookEdition(String textbookEdition) {
		this.textbookEdition = textbookEdition;
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
