package com.whty.assis.sysrole.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.sysrole.model.SysModular;
import com.whty.assis.sysrole.model.SysRole;
import com.whty.page.util.HandlerResult;

public interface SysroleService {

	List<SysModular> queryUserSysModularList(Map<String,Object> map);

	HandlerResult querySysRole(Map<String,Object> map);

	public void addSysRole(SysRole sysRole);

	public void updateSysRole(SysRole sysRole);

	public void updateSysRoleStatus(Map<String,Object> paramap);

	public void deleteSysRole(Map<String,Object> paramap);

	public List<Map<String,Object>> queryModularByRoleId(String role_id);

	public List<Map<String,Object>> queryButtonByRoleId(Map<String,Object> paramap);

	public void saveRolePermission(String role_id, List roleModularList, List roleButtonList);

	public List<Map<String,Object>> querySysRoleByUserId(String user_id);

	public void grantRole(List<String> userIdList, List<String> roleIdList);

	public List<Map<String,Object>> queryButtonByCurrUser(Map<String,Object> paramap);

	public List<String> queryButtonNamesByUserId(Map<String, Object> paramap);
	
	public List<SysRole> selectByRoleId(String role_id);
}
