package com.yhcrt.entity.system;

import com.yhcrt.utils.Constants;

public class SysUser implements java.io.Serializable {
    private Integer cid;

    private String userCode;

    private String password;

    private Integer state;

    private String lastLoginTime;

    private String lastLoginIp;

    private Integer orderNum;

    private String backup;
    
    public SysUser(){
    	this.state = Constants.Middle;
    	this.orderNum = Constants.Middle;
    }

    public SysUser(String userCode) {
    	this.userCode = userCode;
    }
	public SysUser(String userCode,String password) {
		this.userCode = userCode;
		this.password = password;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }
}