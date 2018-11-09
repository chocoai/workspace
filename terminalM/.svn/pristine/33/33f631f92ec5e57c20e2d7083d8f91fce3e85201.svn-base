package com.whty.assis.sysrole.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.sysrole.model.SysRole;

public interface SysRoleDao extends IBaseDao<SysRole> {

	public List<SysRole> querySysRole(Map<String,Object> paramap);

	public void addSysRole(SysRole sysRole);

	public void updateSysRole(SysRole sysRole);

	public void updateSysRoleStatus(Map<String,Object> paramap);

	public void deleteSysRole(Map<String,Object> paramap);

	public List<Map<String,Object>> queryModularByRoleId(String role_id);

	public List<Map<String,Object>> queryButtonByRoleId(Map paramap);

	public void deleteSysRoleModular(String role_id);

	public void deleteSysRoleButton(String role_id);

	public void saveSysRoleModularBatch(List list);

	public void saveSysRoleButtonBatch(List list);

	public List<Map<String,Object>> querySysRoleByUserId(String user_id);

	public List<Map<String,Object>> queryAllSysRole();

	public void deleteSysUserRole(List list);

	public void saveSysUserRoleBatch(List list);

	public List<Map<String,Object>> queryButtonByCurrUser(Map paramap);
	
	public List<SysRole> selectByRoleId(String roleId);
}
