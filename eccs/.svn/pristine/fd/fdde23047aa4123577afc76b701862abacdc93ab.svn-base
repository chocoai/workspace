package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.smart.model.Dept;
import com.smart.model.DeptUser;
import com.smart.model.Role;
import com.smart.util.StringUtil;
import com.smart.dao.DeptDao;
import com.smart.dao.DeptUserDao;
import com.smart.dao.RoleDao;
import com.smart.dao.UserRoleDao;

/**
 * DeptService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class DeptService {

	@Autowired
	private DeptDao deptDao;

	@Autowired
	private DeptUserDao deptUserDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserRoleDao userRoleDao;

	/**
	 * 查询所有组织
	 * 
	 * @return
	 */
	public List<Dept> getList() {
		String hql = "from Dept o where o.status = 1 order by ord asc";
		return deptDao.getList(hql);
	}

	/**
	 * 根据公司ID查询该公司下的所有子部门
	 */
	public List<Dept> getList(String comid) {
		List<Dept> list;
		String hql;
		if (!comid.equals("001")) {
			hql = "from Dept o where  o.status = 1 and o.comid =? order by ord asc";
			list = deptDao.getList(hql, comid);
		} else {
			hql = "from Dept o where  o.status = 1 order by ord asc";
			list = deptDao.getList(hql);
		}
		return list;
	}

	public Dept getLast(String pid) {
		if (StringUtil.isBlank(pid)) {
			return null;
		}
		String sql = "from Dept o where o.pid = ? order by id desc";
		List<Dept> list = deptDao.getList(1, sql, pid);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据父节点Id查询子部门
	 * 
	 * @param pid
	 * @return
	 */
	public List<Dept> getChildDeptByPid(String pid) {
		if (StringUtil.isBlank(pid)) {
			return null;
		}
		String sql = "from Dept o where o.status = 1 and o.pid = ? order by ord asc";
		List<Dept> li = deptDao.getList(sql, pid);
		return li;
	}

	public Dept getOrd(int ord, String pid) {
		String hql = "from Dept o where o.status = 1 and o.ord = ? and o.pid = ?";
		return deptDao.getUnique(hql, ord, pid);
	}

	public void delete(String id) {
		// 删除此部门的人员关系
		String hql = "from DeptUser o where o.status = 1 and o.dept.id = ?";
		List<DeptUser> duList = deptUserDao.getList(hql, id);
		for (DeptUser du : duList) {
			du.setStatus(-1);
			deptUserDao.delete(du);
		}
		// 删除此部门下的角色
		hql = "from Role o where o.status = 1 and o.dept.id = ?";
		List<Role> rList = roleDao.getList(hql, id);
		for (Role role : rList) {
			// 删除角色和用户间的对应关系
			userRoleDao.delete(
					"delete from UserRole o where o.status = 1 and o.role.id = ? ",
					role.getId());
			roleDao.delete(role);
		}

		// 删除部门
		Dept dept = get(id);
		// dept.setStatus(-1);以前的更新status现在直接删除
		// update(dept);
		deptDao.delete(dept);
	}

	// ====================== 基本 C R U D 方法 ===========================
	public Dept get(String id) {
		return deptDao.get(id);
	}

	public void save(Dept entity) {
		deptDao.save(entity);
	}

	public void update(Dept entity) {
		deptDao.update(entity);
	}

	public List<Dept> getAll() {
		return deptDao.getAll();
	}

}
