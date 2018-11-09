package com.whty.assis.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.manage.model.Button;
import com.whty.page.util.HandlerResult;

public interface ButtonService {

	// 查询所有按钮
	public HandlerResult queryButton(Map paramap);

	// 查询模块下所有按钮
	public List<Button> queryButtonByModular(Map paramMap);

	// 新增按钮
	public void addButton(Button button);

	// 删除按钮
	public void deleteButton(String id);

	// 更新按钮
	public void updateButton(Button button);
}
