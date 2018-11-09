package com.smart.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.dao.impl.BaseDaoImpl;
import com.smart.dao.RoleResDao;
import com.smart.model.RoleRes;

/**
 * RoleResDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class RoleResDaoImpl extends BaseDaoImpl<RoleRes, Integer>
		implements RoleResDao {

	public List<RoleRes> getByRole(int roleId) {
		String hql = "from RoleRes o where o.role.id = ?";
		return getList(hql, roleId);
	}

}
