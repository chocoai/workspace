package com.yhcrt.iHealthCloud.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class HdBloodOxygen {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hd_blood_oxygen.cid
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
	@JSONField(serialize=false)
    private Integer cid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hd_blood_oxygen.imei
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    private String imei;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hd_blood_oxygen.member_id
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    private Integer memberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hd_blood_oxygen.data_source
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    private String dataSource;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hd_blood_oxygen.data_date
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    private String dataDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hd_blood_oxygen.upload_time
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    private String uploadTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hd_blood_oxygen.bo_value
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    private Double boValue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hd_blood_oxygen.cid
     *
     * @return the value of hd_blood_oxygen.cid
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hd_blood_oxygen.cid
     *
     * @param cid the value for hd_blood_oxygen.cid
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hd_blood_oxygen.imei
     *
     * @return the value of hd_blood_oxygen.imei
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public String getImei() {
        return imei;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hd_blood_oxygen.imei
     *
     * @param imei the value for hd_blood_oxygen.imei
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hd_blood_oxygen.member_id
     *
     * @return the value of hd_blood_oxygen.member_id
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hd_blood_oxygen.member_id
     *
     * @param memberId the value for hd_blood_oxygen.member_id
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hd_blood_oxygen.data_source
     *
     * @return the value of hd_blood_oxygen.data_source
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hd_blood_oxygen.data_source
     *
     * @param dataSource the value for hd_blood_oxygen.data_source
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hd_blood_oxygen.data_date
     *
     * @return the value of hd_blood_oxygen.data_date
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public String getDataDate() {
        return dataDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hd_blood_oxygen.data_date
     *
     * @param dataDate the value for hd_blood_oxygen.data_date
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public void setDataDate(String dataDate) {
        this.dataDate = dataDate == null ? null : dataDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hd_blood_oxygen.upload_time
     *
     * @return the value of hd_blood_oxygen.upload_time
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public String getUploadTime() {
        return uploadTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hd_blood_oxygen.upload_time
     *
     * @param uploadTime the value for hd_blood_oxygen.upload_time
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime == null ? null : uploadTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hd_blood_oxygen.bo_value
     *
     * @return the value of hd_blood_oxygen.bo_value
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public Double getBoValue() {
        return boValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hd_blood_oxygen.bo_value
     *
     * @param boValue the value for hd_blood_oxygen.bo_value
     *
     * @mbg.generated Fri Jan 19 15:44:55 CST 2018
     */
    public void setBoValue(Double boValue) {
        this.boValue = boValue;
    }
}