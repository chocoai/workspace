package com.smart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.RoleDao;
import com.smart.dao.UserDao;
import com.smart.dao.UserRoleDao;
import com.smart.model.Role;
import com.smart.model.User;
import com.smart.model.UserRole;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * RoleService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	public Page<Role> getPage(int pageNo, int pageSize, String deptId) {
		Page<Role> pageBean = roleDao.getPage(pageNo, pageSize, deptId);
		return pageBean;
	}

	public List<Role> getList(String deptId) {
		String hql = "from Role o where o.status = 1 and o.dept.id = ? order by o.id asc";
		return roleDao.getList(hql, deptId);
	}

	public void saveUserRole(String deptId, int userId, String roleIdList) {
		// 删除该用户以前所有的角色
		String hql = "from UserRole o where o.user.id = ? ";
		List<UserRole> urList = userRoleDao.getList(hql, userId);
		for (UserRole ur : urList) {
			userRoleDao.delete(ur);
		}

		// 添加现在重新设置的角色
		String[] roleIds = roleIdList.split(",");
		User user = userDao.get(userId);
		for (String roleId : roleIds) {
			if (StringUtil.isBlank(roleId)) {
				continue;
			}
			Role role = roleDao.get(Integer.parseInt(roleId));
			UserRole ur = new UserRole();
			ur.setRole(role);
			ur.setUser(user);
			userRoleDao.save(ur);
		}
	}

	public Role get(int id) {
		return roleDao.get(id);
	}

	public void save(Role entity) {
		roleDao.save(entity);
	}

	public void update(Role entity) {
		roleDao.update(entity);
	}

	public void delete(int id) {
		roleDao.delete(id);
	}

	public List<Role> getAll() {
		return roleDao.getAll();
	}

}
