package com.whty.entity;

import java.util.Date;

public class TEbookUseTaking {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备ID
     */
    private Integer deviceId;

    /**
     * 使用时长
     */
    private Long useTaking;

    /**
     * 当天时间
     */
    private Date dayTime;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 设备ID
     * @return device_id 设备ID
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * 设备ID
     * @param deviceId 设备ID
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
        this.useTaking = useTaking;
    }

    /**
     * 当天时间
     * @return day_time 当天时间
     */
    public Date getDayTime() {
        return dayTime;
    }

    /**
     * 当天时间
     * @param dayTime 当天时间
     */
    public void setDayTime(Date dayTime) {
        this.dayTime = dayTime;
    }

    /**
     * 创建时间
     * @return creat_time 创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 创建时间
     * @param creatTime 创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}