/**
 * 
 */
package com.whty.assis.basicdata.model;

import java.util.Date;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年4月3日
 */
public class OrderSchoolTerminalDevice extends BaseModel {

	private static final long serialVersionUID = -1324729090604687122L;

	private Integer orderSchoolId;
	private Integer terminalDeviceTypeId;
	private Integer quan;
	private Integer orderId;
	private Date createTime;
	private Date updateTime;
	private String terminalDeviceTypeName;
	private Integer useDeviceTotalQuan;
	private Integer notUseDeviceTotalQuan;// 未绑定的设备数量
	private Integer schoolId;
	private String schoolName;
	private int orderTerminalDeviceTotalQuan;// 订单的设备总数
	private int orderSchoolTerminalDeviceTotalQuan;// 已经分配的设备数量
	private int notOrderSchoolTerminalDeviceTotalQuan;// 未分配的数量

	private Integer bindDeviceNum;// 绑定的设备数量

	public Integer getBindDeviceNum() {
		return bindDeviceNum;
	}

	public void setBindDeviceNum(Integer bindDeviceNum) {
		this.bindDeviceNum = bindDeviceNum;
	}

	public int getNotOrderSchoolTerminalDeviceTotalQuan() {
		return notOrderSchoolTerminalDeviceTotalQuan;
	}

	public void setNotOrderSchoolTerminalDeviceTotalQuan(int notOrderSchoolTerminalDeviceTotalQuan) {
		this.notOrderSchoolTerminalDeviceTotalQuan = notOrderSchoolTerminalDeviceTotalQuan;
	}

	public int getOrderTerminalDeviceTotalQuan() {
		return orderTerminalDeviceTotalQuan;
	}

	public void setOrderTerminalDeviceTotalQuan(int orderTerminalDeviceTotalQuan) {
		this.orderTerminalDeviceTotalQuan = orderTerminalDeviceTotalQuan;
	}

	public int getOrderSchoolTerminalDeviceTotalQuan() {
		return orderSchoolTerminalDeviceTotalQuan;
	}

	public void setOrderSchoolTerminalDeviceTotalQuan(int orderSchoolTerminalDeviceTotalQuan) {
		this.orderSchoolTerminalDeviceTotalQuan = orderSchoolTerminalDeviceTotalQuan;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getUseDeviceTotalQuan() {
		return useDeviceTotalQuan;
	}

	public void setUseDeviceTotalQuan(Integer useDeviceTotalQuan) {
		this.useDeviceTotalQuan = useDeviceTotalQuan;
	}

	public Integer getNotUseDeviceTotalQuan() {
		return notUseDeviceTotalQuan;
	}

	public void setNotUseDeviceTotalQuan(Integer notUseDeviceTotalQuan) {
		this.notUseDeviceTotalQuan = notUseDeviceTotalQuan;
	}

	public String getTerminalDeviceTypeName() {
		return terminalDeviceTypeName;
	}

	public void setTerminalDeviceTypeName(String terminalDeviceTypeName) {
		this.terminalDeviceTypeName = terminalDeviceTypeName;
	}

	public Integer getOrderSchoolId() {
		return orderSchoolId;
	}

	public void setOrderSchoolId(Integer orderSchoolId) {
		this.orderSchoolId = orderSchoolId;
	}

	public Integer getTerminalDeviceTypeId() {
		return terminalDeviceTypeId;
	}

	public void setTerminalDeviceTypeId(Integer terminalDeviceTypeId) {
		this.terminalDeviceTypeId = terminalDeviceTypeId;
	}

	public Integer getQuan() {
		return quan;
	}

	public void setQuan(Integer quan) {
		this.quan = quan;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
