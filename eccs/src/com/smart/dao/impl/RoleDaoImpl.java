package com.smart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import com.smart.dao.impl.BaseDaoImpl;
import com.smart.dao.RoleDao;
import com.smart.model.Role;
import com.smart.util.Page;

/**
 * RoleDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role, Integer> implements RoleDao {

	/**
	 * 分页查询某个部门下的所有角色
	 */
	public Page<Role> getPage(int pageNo, int pageSize, String deptId) {
		StringBuilder hql = new StringBuilder(
				"from Role o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (StringUtils.isNotBlank(deptId)) {
			hql.append("and o.dept.id = ? ");
			paramList.add(deptId);
		}
		hql.append(" order by o.id desc");
		Page<Role> pageBean = this.getPage(pageNo, pageSize, hql.toString(),
				paramList);
		return pageBean;
	}

}
