package com.smart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.dao.DeptUserDao;
import com.smart.model.Dept;
import com.smart.model.DeptUser;

/**
 * DeptUserDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class DeptUserDaoImpl extends BaseDaoImpl<DeptUser, Integer>
		implements DeptUserDao {

	/**
	 * 查询用户所在的所有部门
	 */
	public List<Dept> getDeptByUser(int userId) {
		String hql = "from DeptUser o where o.status = 1 and o.user.id = ?";
		List<DeptUser> list = getList(hql, userId);
		List<Dept> deptList = new ArrayList<Dept>();
		for (DeptUser du : list) {
			deptList.add(du.getDept());
		}
		return deptList;
	}

}
