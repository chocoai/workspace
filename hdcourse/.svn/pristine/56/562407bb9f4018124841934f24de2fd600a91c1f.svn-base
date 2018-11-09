package com.whty.assis.sysrole.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.sysrole.model.SysModular;
import com.whty.assis.sysrole.model.SysRole;
import com.whty.page.util.HandlerResult;

public interface SysroleService {

	List<SysModular> queryUserSysModularList(Map map);

	HandlerResult querySysRole(Map map);

	public void addSysRole(SysRole sysRole);

	public void updateSysRole(SysRole sysRole);

	public void updateSysRoleStatus(Map paramap);

	public void deleteSysRole(Map paramap);

	public List<Map> queryModularByRoleId(String role_id);

	public List<Map> queryButtonByRoleId(Map paramap);

	public void saveRolePermission(String role_id, List roleModularList, List roleButtonList);

	public List<Map> querySysRoleByUserId(String user_id);

	public void grantRole(List<String> userIdList, List<String> roleIdList);

	public List<Map> queryButtonByCurrUser(Map paramap);

	public List<String> queryButtonNamesByUserId(Map<String, Object> paramap);
}
