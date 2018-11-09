package com.yhcrt.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.yhcrt.demo.util.ExtJSBaseParameter;

/**
 * @author fengkun
 * @email 231788364@qq.com
 * @管理员信息实体类
 */
public class SysUser extends ExtJSBaseParameter  {

	private Long id; // ID

	private String userName; // 用户名

	private String password; // 密码

	private String realName; // 姓名

	private String tel; // 手机号

	private String email; // 邮箱

	private Date lastLoginTime; // 最后登录时间

	private Short role; // 角色（被禁用：0，超级管理员：1，普通管理员：2，普通用户：3）

	//显示
	private String roleName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Short getRole() {
		return role;
	}

	public void setRole(Short role) {
		this.role = role;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public String getLastLoginTimeStr() {
		if(lastLoginTime == null){
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastLoginTime);
	}

	public String getRoleName() {
		if(this.role==0){
			return "禁用";
		}else if(this.role==1){
			return "超级管理员";
		}else if(this.role==2){
			return "普通管理员";
		}else if(this.role==3){
			return "普通用户";
		}
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}