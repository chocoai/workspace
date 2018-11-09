package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.smart.model.RoleRes;
import com.smart.dao.RoleResDao;

/**
 * RoleResService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class RoleResService {

	@Autowired
	private RoleResDao roleResDao;

	// ====================== 基本 C R U D 方法 ===========================
	public RoleRes get(int id) {
		return roleResDao.get(id);
	}

	public List<RoleRes> getByRole(int roleId) {
		return roleResDao.getByRole(roleId);
	}

	public void save(RoleRes entity) {
		roleResDao.save(entity);
	}

	public void update(RoleRes entity) {
		roleResDao.update(entity);
	}

	public void delete(int id) {
		roleResDao.delete(id);
	}

	public List<RoleRes> getAll() {
		return roleResDao.getAll();
	}

}
