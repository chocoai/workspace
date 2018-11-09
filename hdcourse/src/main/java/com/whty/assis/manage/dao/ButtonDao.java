package com.whty.assis.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.manage.model.Button;

public interface ButtonDao extends IBaseDao<Button> {
	// 查询按钮
	public List<Button> queryButton(Map paramap);

	// 新增按钮
	public void addButton(Button button);

	// 删除按钮
	public void deleteButton(String id);

	// 更新按钮
	public void updateButton(Button button);
}
