package com.yhcrt.entity.system;

public class SysUserRole  implements java.io.Serializable{
    private Integer userId;

    private Integer roleCid;

    public SysUserRole(Integer userId,Integer roleCid){
    	this.userId = userId;
    	this.roleCid = roleCid;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleCid() {
        return roleCid;
    }

    public void setRoleCid(Integer roleCid) {
        this.roleCid = roleCid;
    }
}