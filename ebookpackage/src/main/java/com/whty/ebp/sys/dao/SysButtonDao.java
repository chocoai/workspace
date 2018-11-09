package com.whty.ebp.sys.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.sys.model.SysButton;


public interface SysButtonDao extends IBaseDao<SysButton>{
	//查询按钮
	public List<SysButton> queryButton(Map paramap);
	
	//新增按钮
	public void addButton(SysButton button);
	
	//删除按钮
	public void deleteButton(String id);
	
	//更新按钮
	public void updateButton(SysButton button);
	
	public List<String> queryButtonNamesByUserId(Map<String,Object> map);
}
