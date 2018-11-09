package com.yhcrt.entity.system;

import com.yhcrt.utils.Constants;

public class SysRole  implements java.io.Serializable{
    private Integer cid;

    private String roleName;

    private String roleCode;

    private Integer state;

    private String backup;
    
    private Integer orderNum;
    
    public SysRole(){
    	this.state = Constants.Middle;
    	this.orderNum = Constants.Middle;
    }
    

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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
        this.backup = backup;
    }


	public Integer getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
    
}