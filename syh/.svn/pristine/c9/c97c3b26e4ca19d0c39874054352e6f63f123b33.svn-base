package com.yhcrt.entity.system;

import com.yhcrt.utils.Constants;

public class SysDept  implements java.io.Serializable{
    private Integer cid;

    private Integer pareCdoe;

    private String deptName;

    private String deptSname;

    private String deptEname;

    private Integer state;

    private Integer orderNum;

    private String backup;

    private Integer exp1;

    private Integer exp2;

    private String exp3;

    private String exp4;

    private String exp5;

    private String exp6;
    
    private SysDept sysDept;//上级部门
    
    private Integer [] userCids;//部门用户
    
    
	public SysDept(){
	 	this.state = Constants.Middle;
    	this.orderNum = Constants.Middle;
	}

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getPareCdoe() {
        return pareCdoe;
    }

    public void setPareCdoe(Integer pareCdoe) {
        this.pareCdoe = pareCdoe;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptSname() {
        return deptSname;
    }

    public void setDeptSname(String deptSname) {
        this.deptSname = deptSname;
    }

    public String getDeptEname() {
        return deptEname;
    }

    public void setDeptEname(String deptEname) {
        this.deptEname = deptEname;
    }

    public Integer getState() {
        return state;
    }
    public String getStateStr() {
    	if(getState()==Constants.Middle){
    		return "启用中";
    	}else if(getState()==Constants.Middle_01){
    		return "已禁用";
    	}else if(getState()==Constants.Middle_02){
    		return "已锁定";
    	}
    	return "已删除";
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

	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	public Integer[] getUserCids() {
		return userCids;
	}

	public void setUserCids(Integer[] userCids) {
		this.userCids = userCids;
	}
    
}