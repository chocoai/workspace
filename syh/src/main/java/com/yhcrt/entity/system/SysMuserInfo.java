package com.yhcrt.entity.system;

import com.yhcrt.utils.Constants;

public class SysMuserInfo implements java.io.Serializable{
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer cid;

    private Integer userId;
	
	private String userSname;

    private String userEname;

    private String petName;

    private Integer userSex;

    private String birthday;

    private String lastUpdateTime;

    private String lastUpdateIp;

    private Integer orderNum;
    
    private Integer state;

    private String backup;

    private Integer exp1;

    private Integer exp2;

    private String exp3;

    private String exp4;

    private String exp5;

    private String exp6;
    
    private SysUser sysUser;
    
    private SysRole sysRole;
    @Override
	public String toString() {
		return "SysMuserInfo [cid=" + cid + ", userId=" + userId + ", userSname=" + userSname + ", userEname="
				+ userEname + ", petName=" + petName + ", userSex=" + userSex + ", birthday=" + birthday
				+ ", lastUpdateTime=" + lastUpdateTime + ", lastUpdateIp=" + lastUpdateIp + ", orderNum=" + orderNum
				+ ", state=" + state + ", backup=" + backup + ", exp1=" + exp1 + ", exp2=" + exp2 + ", exp3=" + exp3
				+ ", exp4=" + exp4 + ", exp5=" + exp5 + ", exp6=" + exp6 + "]";
	}

	public SysMuserInfo(){
    	this.orderNum = Constants.Middle;
    	this.state = Constants.Middle;
    	
    }
    
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserSname() {
        return userSname;
    }

    public void setUserSname(String userSname) {
        this.userSname = userSname;
    }

    public String getUserEname() {
        return userEname;
    }

    public void setUserEname(String userEname) {
        this.userEname = userEname;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getUserSex() {
        return userSex;
    }
    public String getUserSexStr() {
    	if(getUserSex()==Constants.Middle){
    		return "男";
    	}
    	return "女";
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateIp() {
        return lastUpdateIp;
    }

    public void setLastUpdateIp(String lastUpdateIp) {
        this.lastUpdateIp = lastUpdateIp;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

	public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
    	this.backup = backup == null ? null : backup.trim();
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

	public SysUser getSysUser() {
		return sysUser = sysUser == null? new SysUser():sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public SysRole getSysRole() {
		
		return sysRole = sysRole==null?new SysRole():sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
    
}