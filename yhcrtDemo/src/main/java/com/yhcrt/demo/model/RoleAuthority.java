package com.yhcrt.demo.model;


/**
 * @author fengkun
 * @email 231788364@qq.com
 * @角色权限实体类
 */
public class RoleAuthority {
	private Long id; // ID
	
	private Short role; // 角色（被禁用：0，超级管理员：1，普通管理员：2，普通用户：3）
	
	private String authorityId; // 权限菜单ID

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getRole() {
		return role;
	}

	public void setRole(Short role) {
		this.role = role;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
}
