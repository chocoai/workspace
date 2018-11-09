package com.whty.ebp.sys.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.sys.model.SysButton;
import com.whty.page.util.HandlerResult;

public interface SysButtonService {

	//查询所有按钮
	public HandlerResult queryButton(Map paramap);
	
	//查询模块下所有按钮
	public List<SysButton> queryButtonByModular(Map paramMap);
	
	//新增按钮
	public void addButton(SysButton sysButton);
	
	//删除按钮
	public void deleteButton(String id);
	
	//更新按钮
	public void updateButton(SysButton sysButton);
}
