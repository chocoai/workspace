package com.whty.assis.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.base.service.BaseService;
import com.whty.assis.manage.dao.ButtonDao;
import com.whty.assis.manage.model.Button;
import com.whty.assis.manage.service.ButtonService;
import com.whty.page.util.HandlerResult;

@Service
public class ButtonServiceImpl implements ButtonService {

	@Autowired
	private ButtonDao buttonDao;

	@Override
	public HandlerResult queryButton(Map paramMap) {
		// logger.info("queryPage:"+paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(buttonDao.queryButton(paramMap));
		return rs;
	}

	@Override
	public List<Button> queryButtonByModular(Map paramMap) {
		return buttonDao.queryButton(paramMap);
	}

	@Override
	public void addButton(Button button) {
		buttonDao.addButton(button);
	}

	@Override
	public void deleteButton(String id) {
		buttonDao.deleteButton(id);
	}

	@Override
	public void updateButton(Button button) {
		buttonDao.updateButton(button);
	}

}
