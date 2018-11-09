package com.whty.ebp.sys.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.sys.model.SysModular;

public interface SysModularService {

	//查询所有菜单
	public List<SysModular> queryAllModular();
	
	//新增菜单
	public void addModular(SysModular sysModular);
	
	//删除菜单
	public void deleteModular(List list);
	
	//更新菜单
	public void updateModular(Map paramap);
}
