package com.whty.entity;

import java.util.Date;

public class TEbookOnlie {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备ID
     */
    private Integer deviceId;

    /**
     * 状态（0：开机，1：关机）
     */
    private Integer status;

    /**
     * 开机时间
     */
    private Date openTime;

    /**
     * 关机时间
     */
    private Date closeTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
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
     * 状态（0：开机，1：关机）
     * @return status 状态（0：开机，1：关机）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0：开机，1：关机）
     * @param status 状态（0：开机，1：关机）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 开机时间
     * @return open_time 开机时间
     */
    public Date getOpenTime() {
        return openTime;
    }

    /**
     * 开机时间
     * @param openTime 开机时间
     */
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    /**
     * 关机时间
     * @return close_time 关机时间
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * 关机时间
     * @param closeTime 关机时间
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
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
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}