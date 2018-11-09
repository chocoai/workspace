package com.whty.ebp.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.sys.dao.SysButtonDao;
import com.whty.ebp.sys.model.SysButton;
import com.whty.ebp.sys.service.SysButtonService;
import com.whty.page.util.HandlerResult;

@Service
public class SysButtonServiceImpl implements SysButtonService {

	@Autowired
	private SysButtonDao buttonDao;
	
	@Override
	public HandlerResult queryButton(Map paramMap) {
		//logger.info("queryPage:"+paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(buttonDao.queryButton(paramMap));
		return rs;
	}
	
	@Override
	public List<SysButton> queryButtonByModular(Map paramMap) {
		return buttonDao.queryButton(paramMap);
	}

	@Override
	public void addButton(SysButton button) {
		buttonDao.addButton(button);
	}

	@Override
	public void deleteButton(String id) {
		buttonDao.deleteButton(id);
	}

	@Override
	public void updateButton(SysButton button) {
		buttonDao.updateButton(button);
	}

}
