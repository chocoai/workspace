package com.whty.assis.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.manage.model.Modular;

public interface ModularDao extends IBaseDao<Modular> {
	// 查询所有菜单
	public List<Modular> queryAllModular();

	// 新增菜单
	public void addModular(Modular Modular);

	// 删除菜单
	public void deleteModular(List list);

	// 更新菜单
	public void updateModular(Map paramap);
}
