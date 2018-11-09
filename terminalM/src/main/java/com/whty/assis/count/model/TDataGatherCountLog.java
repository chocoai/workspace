package com.whty.assis.count.model;

import java.util.Date;

public class TDataGatherCountLog {
    /**
     * 
     */
    private Integer id;

    /**
     * 星期
     */
    private String week;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    private String createTimeStr;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 软件版本
     */
    private String softwareVersion;

    /**
     * 软件名称
     */
    private String softwareName;

    /**
     * 厂商
     */
    private String corporateName;

    /**
     * 用户帐号
     */
    private String userAccount;

    /**
     * 应用姓名
     */
    private String userName;

    /**
     * 教材
     */
    private String textbookEdition;

    /**
     * 教材
     */
    private Integer subjectId;

    /**
     * 年级
     */
    private Integer gradeId;

    /**
     * 设备id
     */
    private Integer deviceId;

    /**
     * 使用时长
     */
    private Long useTaking;

    /**
     * 排名
     */
    private Integer rank;

    /**
     * 使用次数
     */
    private Integer userCount;

    private String percent;

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 星期
     * @return week 星期
     */
    public String getWeek() {
        return week;
    }

    /**
     * 星期
     * @param week 星期
     */
    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    /**
     * 开始时间
     * @return start_time 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     * @return end_time 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * 软件版本
     * @return software_version 软件版本
     */
    public String getSoftwareVersion() {
        return softwareVersion;
    }

    /**
     * 软件版本
     * @param softwareVersion 软件版本
     */
    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion == null ? null : softwareVersion.trim();
    }

    /**
     * 软件名称
     * @return software_name 软件名称
     */
    public String getSoftwareName() {
        return softwareName;
    }

    /**
     * 软件名称
     * @param softwareName 软件名称
     */
    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName == null ? null : softwareName.trim();
    }

    /**
     * 厂商
     * @return corporate_name 厂商
     */
    public String getCorporateName() {
        return corporateName;
    }

    /**
     * 厂商
     * @param corporateName 厂商
     */
    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName == null ? null : corporateName.trim();
    }

    /**
     * 用户帐号
     * @return user_account 用户帐号
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 用户帐号
     * @param userAccount 用户帐号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    /**
     * 应用姓名
     * @return user_name 应用姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 应用姓名
     * @param userName 应用姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 教材
     * @return textbook_edition 教材
     */
    public String getTextbookEdition() {
        return textbookEdition;
    }

    /**
     * 教材
     * @param textbookEdition 教材
     */
    public void setTextbookEdition(String textbookEdition) {
        this.textbookEdition = textbookEdition == null ? null : textbookEdition.trim();
    }

    /**
     * 教材
     * @return subject_id 教材
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * 教材
     * @param subjectId 教材
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 年级
     * @return grade_id 年级
     */
    public Integer getGradeId() {
        return gradeId;
    }

    /**
     * 年级
     * @param gradeId 年级
     */
    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * 设备id
     * @return device_id 设备id
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * 设备id
     * @param deviceId 设备id
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 使用时长
     * @return use_taking 使用时长
     */
    public Long getUseTaking() {
        return useTaking;
    }

    /**
     * 使用时长
     * @param useTaking 使用时长
     */
    public void setUseTaking(Long useTaking) {
        this.useTaking = useTaking ;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}