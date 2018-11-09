package com.whty.assis.sysrole.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.sysrole.model.SysRole;

public interface SysRoleDao extends IBaseDao<SysRole> {

	public List<SysRole> querySysRole(Map paramap);

	public void addSysRole(SysRole sysRole);

	public void updateSysRole(SysRole sysRole);

	public void updateSysRoleStatus(Map paramap);

	public void deleteSysRole(Map paramap);

	public List<Map> queryModularByRoleId(String role_id);

	public List<Map> queryButtonByRoleId(Map paramap);

	public void deleteSysRoleModular(String role_id);

	public void deleteSysRoleButton(String role_id);

	public void saveSysRoleModularBatch(List list);

	public void saveSysRoleButtonBatch(List list);

	public List<Map> querySysRoleByUserId(String user_id);

	public List<Map> queryAllSysRole();

	public void deleteSysUserRole(List list);

	public void saveSysUserRoleBatch(List list);

	public List<Map> queryButtonByCurrUser(Map paramap);
}
