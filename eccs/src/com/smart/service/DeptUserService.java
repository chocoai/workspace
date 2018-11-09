package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.DeptUserDao;
import com.smart.dao.UserDao;
import com.smart.dao.UserRoleDao;
import com.smart.model.Dept;
import com.smart.model.DeptUser;
import com.smart.model.Role;
import com.smart.model.User;
import com.smart.util.Constants;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.StringUtil;

/**
 * DeptUserService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class DeptUserService {

	@Autowired
	private DeptUserDao deptUserDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	/**
	 * 查询用户所在的所有部门
	 * 
	 * @param userId
	 * @return
	 */
	public List<Dept> getDeptByUser(int userId) {
		return deptUserDao.getDeptByUser(userId);
	}

	/**
	 * 获取部门里的用户
	 * 
	 * @param deptId
	 * @return
	 */
	public List<DeptUser> getList(String deptId) {
		StringBuilder hql = new StringBuilder(
				"from DeptUser o where o.status = 1 and o.dept.id = ? order by o.id asc");
		List<DeptUser> duList = deptUserDao.getList(hql.toString(), deptId);

		for (DeptUser du : duList) {
			int userId = du.getUser().getId();
			List<Role> roleList = userRoleDao.getMyRole(null, userId);
			du.setRoleList(roleList);
		}
		return duList;
	}

	public List<User> getList1(String deptId, String comid) { // 查询部门层下的用户
		StringBuilder hql = new StringBuilder(
				"from DeptUser o where o.status = 1 and o.dept.id like '"
						+ deptId + "%' order by o.id desc");
		List<DeptUser> duList = deptUserDao.getList(hql.toString());
		List<User> us = new ArrayList<User>();
		for (int i = 0; i < duList.size(); i++) {
			int userid = duList.get(i).getUser().getId();
			User user = userDao.get(userid);
			if (!comid.equals("001")) {// 001是特殊公司 总公司
				if (comid.equals(user.getComid())) { // 分公司
					us.add(user);
				}
			} else {
				us.add(user);
			}
		}
		return us;
	}

	public Page<User> getList2(int pageNo, int pageSize, String name,
			String deptId, String comid) { // 查询部门层下的用户
		String str = getBydeptId(deptId);
		if (StringUtil.isBlank(str)) {
			return new Page<User>(pageNo, pageSize);
		}
		StringBuilder hql = new StringBuilder(
				"from User o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();

		if (!StringUtil.isBlank(name)) {
			hql.append(" and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		if (!comid.equals("001")) {
			hql.append(" and o.comid =? ");
			paramList.add(comid);
		}
		if (!StringUtil.isBlank(str)) {
			hql.append(" and  CONCAT(o.id,'') IN " + str + " ");
		}
		hql.append(" order by o.id desc");
		return userDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	public String getBydeptId(String deptId) {
		StringBuilder hql = new StringBuilder(
				"from DeptUser o where o.status = 1 and o.dept.id = ? order by o.id asc");
		List<DeptUser> list = deptUserDao.getList(hql.toString(), deptId);
		if (list.size() < 1) {
			return null;
		}
		StringBuffer str = new StringBuffer("(");
		for (int i = 0; i < list.size(); i++) {
			str.append(list.get(i).getUser().getId());
			if (i < list.size() - 1) {
				str.append(" , ");
			}
		}
		str.append(")");
		return str.toString();
	}

	public List<User> getList2(String projectid) { // 根据某工程下项目经理分配了任务的人员
		StringBuilder hql = new StringBuilder(
				"select o.workUser  from Step3Worker o where o.status = 1 and o.project.id = "
						+ projectid + " order by o.id desc");
		List<User> duList = userDao.getList(hql.toString());
		return duList;
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @param deptId
	 * @return
	 */
	public Page<DeptUser> getPage(int pageNo, int pageSize, String name,
			String deptId) {
		StringBuilder hql = new StringBuilder(
				"from DeptUser o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.user.name like ?");
			paramList.add("%" + name + "%");
		}
		if (!StringUtil.isBlank(deptId)) {
			hql.append("and o.dept.id = ?");
			paramList.add(deptId);
		}
		hql.append("order by o.id desc");
		return deptUserDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	/**
	 * 根据用户和部门查询
	 * 
	 * @param deptId
	 * @param userId
	 * @return
	 */
	public DeptUser get(String deptId, Integer userId) {
		String hql = "from DeptUser o where o.status = 1 and o.dept.id = ? and o.user.id = ?";
		return deptUserDao.getUnique(hql, deptId, userId);
	}

	/**
	 * 保存用户部门关系
	 * 
	 * @param userId
	 *            判断是更新还是新增
	 * @param user
	 * @param dept
	 */
	public void save(Integer userId, User user, Dept dept) {
		User newUser = null;

		/** user **/
		if (userId == null) {
			userDao.save(user);
			newUser = user;
		} else {
			User oldUser = userDao.get(userId);
			// 复制bean，最后一个参数是不复制的属性
			ReflectionUtil.bean2Bean(oldUser, user, "id, sex, ctime, status");
			userDao.update(oldUser);
			newUser = oldUser;
		}

		/** dept_user **/
		if (dept == null) {
			return;
		}

		// 是否更新以前部门关系
		DeptUser du = get(newUser.getId(), dept.getId());
		if (du != null) {
			return;
		}

		// 删除以前所在部门关系
		List<DeptUser> list = getList(newUser.getId());
		if (list != null) {
			delete(list);
		}

		// 保存当前所在部门关系
		du = new DeptUser();
		du.setDept(dept);
		du.setUser(newUser);
		save(du);
	}

	/**
	 * 查询用户部门关系
	 * 
	 * @param userId
	 * @param deptId
	 * @return
	 */
	public DeptUser get(Integer userId, String deptId) {
		String hql = "from DeptUser o where o.status = 1 and o.user.id = ? and o.dept.id = ?";
		return deptUserDao.getUnique(hql, userId, deptId);
	}

	/**
	 * 查询用户部门关系
	 * 
	 * @param userId
	 * @param deptId
	 * @return
	 */
	public List<DeptUser> getList(Integer userId) {
		String hql = "from DeptUser o where o.status = 1 and o.user.id = ?";
		return deptUserDao.getList(hql, userId);
	}

	/**
	 * 查询多个部门下的用户
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param deptIds
	 * @return
	 */
	public Page<DeptUser> getUserByDeptIds(int pageNo, int pageSize,
			String companyId, String deptIds, String name) {
		StringBuilder hql = new StringBuilder(
				"from DeptUser o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(deptIds)) {
			hql.append("and o.dept.id in ? ");
			paramList.add("(" + deptIds + ")");
		}
		if (!companyId.equals(Constants.DEPT_ROOT_NODE_ID)) {
			hql.append(" and o.user.comid = ? ");
			paramList.add(companyId);
		}
		if (StringUtils.isNotBlank(name)) {
			hql.append(" and o.user.name like ? ");
			paramList.add("%" + name + "%");
		}
		hql.append("order by o.id desc");
		Page<DeptUser> pageBean = deptUserDao.getPage(pageNo, pageSize,
				hql.toString(), paramList);
		return pageBean;
	}

	public DeptUser get(int id) {
		return deptUserDao.get(id);
	}

	public void save(DeptUser entity) {
		deptUserDao.save(entity);
	}

	public void update(DeptUser entity) {
		deptUserDao.update(entity);
	}

	public void delete(int id) {
		deptUserDao.delete(id);
	}

	public void delete(List<DeptUser> list) {
		if (list == null) {
			return;
		}
		for (DeptUser du : list) {
			deptUserDao.delete(du);
		}
	}

	public List<DeptUser> getAll() {
		return deptUserDao.getAll();
	}

}
