package com.smart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.ResDao;
import com.smart.dao.RoleDao;
import com.smart.dao.RoleResDao;
import com.smart.model.Res;
import com.smart.model.Role;
import com.smart.model.RoleRes;
import com.smart.util.SpringUtil;
import com.smart.util.StringUtil;

/**
 * ResService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ResService {

	@Autowired
	private ResDao resDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RoleResDao roleResDao;

	/**
	 * 获取多个角色的权限
	 * 
	 * @param myRoleList
	 * @return
	 */
	public Map<String, Res> getMyRes(List<Role> myRoleList) {

		// 是否开启权限，在config里配置true | false
		String flag = SpringUtil.getConfig("security");

		Map<String, Res> map = new HashMap<String, Res>();
		if ("true".equals(flag)) {
			for (Role role : myRoleList) {
				List<Res> resList = getList(role.getId());
				for (Res res : resList) {
					res.getName(); // 手动调用一下其中的方法把数据加载出来，不然hibernate会延迟加载
					map.put(res.getId(), res);
				}
			}
			List<Res> resGlobalList = getGlobalList();
			for (Res res : resGlobalList) { // 通用权限
				map.put(res.getId(), res);
			}
		} else {
			List<Res> resList = getAll();
			for (Res res : resList) {
				res.getName();
				map.put(res.getId(), res);
			}
		}

		return map;
	}

	/**
	 * 获取多个角色的权限
	 * 
	 * @param myRoleList
	 * @return
	 */
	public Map<String, Res> getMyRes(String lastRoleId) {

		// 是否开启权限，在config里配置true | false
		String flag = SpringUtil.getConfig("security");

		Map<String, Res> map = new HashMap<String, Res>();
		if ("true".equals(flag)) {
			// 根据角色ID获取该角色所拥有的权限资源
			List<Res> resList = getList(Integer.valueOf(lastRoleId));
			for (Res res : resList) {
				map.put(res.getId(), res);
			}
			// 获取全局权限资源，即所有用户都有权限访问
			List<Res> resGlobalList = getGlobalList();
			for (Res res : resGlobalList) {
				map.put(res.getId(), res);
			}
		} else {
			List<Res> resList = getAll();
			for (Res res : resList) {
				map.put(res.getId(), res);
			}
		}

		return map;
	}

	/**
	 * 获取不需要配置的资源,这些资源状态为2 默认所有用户都可以访问
	 * 
	 * @return
	 */
	public List<Res> getGlobalList() {
		String hql = "from Res o where o.status = 2  ";
		List<Res> list = resDao.getList(hql);
		return list;
	}

	public List<RoleRes> getList1(int roleId) {
		String hql = "from RoleRes o where o.status = 1 and o.role.id = ? ";
		List<RoleRes> list = roleResDao.getList(hql, roleId);
		return list;
	}

	/**
	 * 根据角色ID获取该角色所拥有的权限资源
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Res> getList(int roleId) {
		String hql = "from RoleRes o where o.status = 1 and o.role.id = ? ";
		List<RoleRes> list = roleResDao.getList(hql, roleId);
		List<Res> resList = new ArrayList<Res>();
		for (RoleRes rr : list) {
			resList.add(rr.getRes());
		}

		return resList;
	}

	public List<Res> getList() {
		String hql = "from Res o where o.status = 1 order by o.id asc";
		return resDao.getList(hql);
	}

	public void save(int roleId, String resIdList) {
		List<RoleRes> rrList = roleResDao.getByRole(roleId);
		for (RoleRes rr : rrList) {
			roleResDao.delete(rr);
		}

		Role role = roleDao.get(roleId);
		String[] ids = resIdList.split(",");
		for (String id : ids) {
			if (StringUtil.isBlank(id)) {
				continue;
			}
			Res res = get(id);
			RoleRes rr = new RoleRes();
			rr.setRole(role);
			rr.setRes(res);
			roleResDao.save(rr);
		}
	}

	// ====================== 基本 C R U D 方法 ===========================
	public Res get(String id) {
		return resDao.get(id);
	}

	public void save(Res entity) {
		resDao.save(entity);
	}

	public void update(Res entity) {
		resDao.update(entity);
	}

	public void delete(String id) {
		resDao.delete(id);
	}

	public List<Res> getAll() {
		return resDao.getAll();
	}

}
