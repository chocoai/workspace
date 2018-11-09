package com.whty.entity;

import java.util.Date;

public class TDeviceInfo {
    /**
     * 
     */
    private Integer id;

    /**
     * 三元组 阿里设备认证
     */
    private String aliDeviceSecret;

    /**
     * 三元组 阿里设备名称
     */
    private String aliDeviceName;

    /**
     * 三元组 阿里产品名称
     */
    private String aliProductKey;

    /**
     * 阿里iotid
     */
    private String aliIotId;

    /**
     * 品牌
     */
    private Integer brandId;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 省份
     */
    private String provinceCode;

    /**
     * 城市
     */
    private String cityCode;

    /**
     * 区域
     */
    private String areaCode;

    /**
     * 学校
     */
    private Integer schoolId;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 激活时间
     */
    private Date registerTime;

    /**
     * 设备类型
     */
    private Integer terminalDeviceTypeId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 冻结状态 是否冻结
     */
    private String freezeStatus;

    /**
     * 客户端软件版本号
     */
    private String clientSoftVersion;

    /**
     * 设备状态 1.未激活 2.已激活
     */
    private Byte deviceStatus;

    /**
     * 操作系统
     */
    private String systemVersion;

    /**
     * 分辨率
     */
    private String resolution;

    /**
     * 注册次数
     */
    private Short registerCount;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 设备唯一标识符
     */
    private String hash;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 内存
     */
    private String storage;

    /**
     * 硬盘
     */
    private String diskSize;

    /**
     * mac
     */
    private String mac;

    /**
     * 设备编号
     */
    private String deviceCode;

    /**
     * 是否在线(0:在线，1：离线)
     */
    private Integer online;

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
     * 三元组 阿里设备认证
     * @return ali_device_secret 三元组 阿里设备认证
     */
    public String getAliDeviceSecret() {
        return aliDeviceSecret;
    }

    /**
     * 三元组 阿里设备认证
     * @param aliDeviceSecret 三元组 阿里设备认证
     */
    public void setAliDeviceSecret(String aliDeviceSecret) {
        this.aliDeviceSecret = aliDeviceSecret == null ? null : aliDeviceSecret.trim();
    }

    /**
     * 三元组 阿里设备名称
     * @return ali_device_name 三元组 阿里设备名称
     */
    public String getAliDeviceName() {
        return aliDeviceName;
    }

    /**
     * 三元组 阿里设备名称
     * @param aliDeviceName 三元组 阿里设备名称
     */
    public void setAliDeviceName(String aliDeviceName) {
        this.aliDeviceName = aliDeviceName == null ? null : aliDeviceName.trim();
    }

    /**
     * 三元组 阿里产品名称
     * @return ali_product_key 三元组 阿里产品名称
     */
    public String getAliProductKey() {
        return aliProductKey;
    }

    /**
     * 三元组 阿里产品名称
     * @param aliProductKey 三元组 阿里产品名称
     */
    public void setAliProductKey(String aliProductKey) {
        this.aliProductKey = aliProductKey == null ? null : aliProductKey.trim();
    }

    /**
     * 阿里iotid
     * @return ali_iot_id 阿里iotid
     */
    public String getAliIotId() {
        return aliIotId;
    }

    /**
     * 阿里iotid
     * @param aliIotId 阿里iotid
     */
    public void setAliIotId(String aliIotId) {
        this.aliIotId = aliIotId == null ? null : aliIotId.trim();
    }

    /**
     * 品牌
     * @return brand_id 品牌
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * 品牌
     * @param brandId 品牌
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * 设备名称
     * @return name 设备名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设备名称
     * @param name 设备名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 省份
     * @return province_code 省份
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 省份
     * @param provinceCode 省份
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * 城市
     * @return city_code 城市
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 城市
     * @param cityCode 城市
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 区域
     * @return area_code 区域
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 区域
     * @param areaCode 区域
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 学校
     * @return school_id 学校
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * 学校
     * @param schoolId 学校
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 最后登录时间
     * @return last_login_time 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 最后登录时间
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 激活时间
     * @return register_time 激活时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 激活时间
     * @param registerTime 激活时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 设备类型
     * @return terminal_device_type_id 设备类型
     */
    public Integer getTerminalDeviceTypeId() {
        return terminalDeviceTypeId;
    }

    /**
     * 设备类型
     * @param terminalDeviceTypeId 设备类型
     */
    public void setTerminalDeviceTypeId(Integer terminalDeviceTypeId) {
        this.terminalDeviceTypeId = terminalDeviceTypeId;
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

    /**
     * 冻结状态 是否冻结
     * @return freeze_status 冻结状态 是否冻结
     */
    public String getFreezeStatus() {
        return freezeStatus;
    }

    /**
     * 冻结状态 是否冻结
     * @param freezeStatus 冻结状态 是否冻结
     */
    public void setFreezeStatus(String freezeStatus) {
        this.freezeStatus = freezeStatus == null ? null : freezeStatus.trim();
    }

    /**
     * 客户端软件版本号
     * @return client_soft_version 客户端软件版本号
     */
    public String getClientSoftVersion() {
        return clientSoftVersion;
    }

    /**
     * 客户端软件版本号
     * @param clientSoftVersion 客户端软件版本号
     */
    public void setClientSoftVersion(String clientSoftVersion) {
        this.clientSoftVersion = clientSoftVersion == null ? null : clientSoftVersion.trim();
    }

    /**
     * 设备状态 1.未激活 2.已激活
     * @return device_status 设备状态 1.未激活 2.已激活
     */
    public Byte getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * 设备状态 1.未激活 2.已激活
     * @param deviceStatus 设备状态 1.未激活 2.已激活
     */
    public void setDeviceStatus(Byte deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    /**
     * 操作系统
     * @return system_version 操作系统
     */
    public String getSystemVersion() {
        return systemVersion;
    }

    /**
     * 操作系统
     * @param systemVersion 操作系统
     */
    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion == null ? null : systemVersion.trim();
    }

    /**
     * 分辨率
     * @return resolution 分辨率
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * 分辨率
     * @param resolution 分辨率
     */
    public void setResolution(String resolution) {
        this.resolution = resolution == null ? null : resolution.trim();
    }

    /**
     * 注册次数
     * @return register_count 注册次数
     */
    public Short getRegisterCount() {
        return registerCount;
    }

    /**
     * 注册次数
     * @param registerCount 注册次数
     */
    public void setRegisterCount(Short registerCount) {
        this.registerCount = registerCount;
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * 设备唯一标识符
     * @return hash 设备唯一标识符
     */
    public String getHash() {
        return hash;
    }

    /**
     * 设备唯一标识符
     * @param hash 设备唯一标识符
     */
    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    /**
     * 订单id
     * @return order_id 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 订单id
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 内存
     * @return storage 内存
     */
    public String getStorage() {
        return storage;
    }

    /**
     * 内存
     * @param storage 内存
     */
    public void setStorage(String storage) {
        this.storage = storage == null ? null : storage.trim();
    }

    /**
     * 硬盘
     * @return disk_size 硬盘
     */
    public String getDiskSize() {
        return diskSize;
    }

    /**
     * 硬盘
     * @param diskSize 硬盘
     */
    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize == null ? null : diskSize.trim();
    }

    /**
     * mac
     * @return mac mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * mac
     * @param mac mac
     */
    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    /**
     * 设备编号
     * @return device_code 设备编号
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * 设备编号
     * @param deviceCode 设备编号
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    /**
     * 是否在线(0:在线，1：离线)
     * @return online 是否在线(0:在线，1：离线)
     */
    public Integer getOnline() {
        return online;
    }

    /**
     * 是否在线(0:在线，1：离线)
     * @param online 是否在线(0:在线，1：离线)
     */
    public void setOnline(Integer online) {
        this.online = online;
    }
}