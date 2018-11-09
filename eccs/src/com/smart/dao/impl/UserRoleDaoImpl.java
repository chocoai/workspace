package com.smart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.dao.impl.BaseDaoImpl;
import com.smart.dao.UserRoleDao;
import com.smart.model.Role;
import com.smart.model.UserRole;
import com.smart.util.StringUtil;

/**
 * UserRoleDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole, Integer>
		implements UserRoleDao {

	public List<Role> getMyRole(String deptId, int userId) {
		StringBuilder hql = new StringBuilder(
				"from UserRole o where o.status = 1 and o.user.id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);

		if (!StringUtil.isBlank(deptId)) {
			hql.append("and o.role.dept.id = ? ");
			paramList.add(deptId);
		}

		hql.append("order by o.id asc");

		List<UserRole> urList = getList(hql.toString(), paramList);
		List<Role> roleList = new ArrayList<Role>();
		for (UserRole ur : urList) {
			roleList.add(ur.getRole());
		}
		return roleList;
	}

	public List<UserRole> getByRole(int roleId) {
		StringBuilder hql = new StringBuilder(
				"from UserRole o where o.status = 1 and o.role.id = ?");
		List<UserRole> urList = getList(hql.toString(), roleId);
		return urList;
	}
}
