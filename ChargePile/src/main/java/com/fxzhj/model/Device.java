package com.fxzhj.model;

import java.io.Serializable;
import java.util.Date;

public class Device implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    //设备名称
    private String name;

    //区域表id
    private Integer areaId;
    
    //小区表id
    private Integer communityId;

    //设备编号
    private String deviceNo;

    //卡号
    private String cardNo;

    //生产厂家
    private String madeIn;

    //出厂日期
    private Date outDate;

    //入库日期
    private Date inDate;

    //端口数量
    private Integer portNum;

    //设备状态（0：停用 1:启用 ）
    private Integer status;

    //通信号
    private String deviceIp;
    
    //节点id
    private String nodeId;
    
    //修改时间
    private Date updateTime;
    
    //界面显示字段
    private String siteName;	//投放点地址
    private String telPhone;	//物业管理联系方式
    private String lng;	//经度
    private String lat;	//纬度
    private String startTime;
    private String endTime;
    private String endLoadTime;	//最后一次使用时间
    private String count;	//统计使用次数
    private String outDateStr;
    private String inDateStr;

    //界面传入字段
    private Integer comOrareId;	//区域或小区id
    private String deep;	//判断comOrareId
    private String date;	//创建时间
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMadeIn() {
		return madeIn;
	}
	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Integer getPortNum() {
		return portNum;
	}
	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEndLoadTime() {
		return endLoadTime;
	}
	public void setEndLoadTime(String endLoadTime) {
		this.endLoadTime = endLoadTime;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getOutDateStr() {
		return outDateStr;
	}
	public void setOutDateStr(String outDateStr) {
		this.outDateStr = outDateStr;
	}
	public String getInDateStr() {
		return inDateStr;
	}
	public void setInDateStr(String inDateStr) {
		this.inDateStr = inDateStr;
	}
	public Integer getComOrareId() {
		return comOrareId;
	}
	public void setComOrareId(Integer comOrareId) {
		this.comOrareId = comOrareId;
	}
	public String getDeep() {
		return deep;
	}
	public void setDeep(String deep) {
		this.deep = deep;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}