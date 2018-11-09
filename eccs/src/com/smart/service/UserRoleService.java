package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Role;
import com.smart.model.UserRole;
import com.smart.util.StringUtil;
import com.smart.dao.UserRoleDao;

/**
 * UserRoleService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;

	public List<Role> getMyRole(String deptId, int userId) {
		return userRoleDao.getMyRole(deptId, userId);
	}

	public List<Role> getMyRole(int userId) {
		return userRoleDao.getMyRole(null, userId);
	}

	public List<UserRole> getByRole(int roleId) {
		return userRoleDao.getByRole(roleId);
	}

	public List<UserRole> getUserRole(int userId, String deptId) {
		StringBuilder hql = new StringBuilder(
				"from UserRole o where o.status = 1  and o.user.id = ?  and o.role.dept.id = ?");
		return userRoleDao.getList(hql.toString(), userId, deptId);
	}

	public List<UserRole> getMyUserRole(String deptId, int userId) {
		StringBuilder hql = new StringBuilder(
				"from UserRole o where o.status = 1 and o.user.id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);
		if (!StringUtil.isBlank(deptId)) {
			hql.append("and o.role.dept.id = ? ");
			paramList.add(deptId);
		}

		hql.append("order by o.id asc");

		return userRoleDao.getList(hql.toString(), paramList);
	}

	// ====================== 基本 C R U D 方法 ===========================
	public UserRole get(int id) {
		return userRoleDao.get(id);
	}

	public void save(UserRole entity) {
		userRoleDao.save(entity);
	}

	public void update(UserRole entity) {
		userRoleDao.update(entity);
	}

	public void delete(int id) {
		userRoleDao.delete(id);
	}

	public List<UserRole> getAll() {
		return userRoleDao.getAll();
	}

}
