package com.yhcrt.entity.system;

import com.yhcrt.utils.Constants;

public class SysRes implements java.io.Serializable{
	private Integer cid;

	private Integer pareId;

	private String resName;
	
	private String resUrl;

	private Integer state;

	private Integer orderNum;

	private String backup;

	private Integer exp1;

	private Integer exp2;

	private String exp3;

	private String exp4;

	private String exp5;

	private String exp6;
	
	private SysRes pareSysRes;
	
	public SysRes(){
	 	this.state = Constants.Middle;
    	this.orderNum = Constants.Middle;
	}

	@Override
	public String toString() {
		return "SysRes [cid=" + cid + ", pareId=" + pareId + ", resName=" + resName + ", resUrl=" + resUrl + ", state="
				+ state + ", orderNum=" + orderNum + ", backup=" + backup + ", exp1=" + exp1 + ", exp2=" + exp2
				+ ", exp3=" + exp3 + ", exp4=" + exp4 + ", exp5=" + exp5 + ", exp6=" + exp6 + "]";
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPareId() {
		return pareId;
	}

	public void setPareId(Integer pareId) {
		this.pareId = pareId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Integer getExp1() {
		return exp1;
	}

	public void setExp1(Integer exp1) {
		this.exp1 = exp1;
	}

	public Integer getExp2() {
		return exp2;
	}

	public void setExp2(Integer exp2) {
		this.exp2 = exp2;
	}

	public String getExp3() {
		return exp3;
	}

	public void setExp3(String exp3) {
		this.exp3 = exp3;
	}

	public String getExp4() {
		return exp4;
	}

	public void setExp4(String exp4) {
		this.exp4 = exp4;
	}

	public String getExp5() {
		return exp5;
	}

	public void setExp5(String exp5) {
		this.exp5 = exp5;
	}

	public String getExp6() {
		return exp6;
	}

	public void setExp6(String exp6) {
		this.exp6 = exp6;
	}

	public SysRes getPareSysRes() {
		return pareSysRes;
	}

	public void setPareSysRes(SysRes pareSysRes) {
		this.pareSysRes = pareSysRes;
	}
	
}