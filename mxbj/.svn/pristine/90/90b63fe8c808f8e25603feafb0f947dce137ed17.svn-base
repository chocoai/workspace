package com.whty.mxbj.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.mxbj.api.dao.PageDao;
import com.whty.mxbj.api.model.Page;
import com.whty.mxbj.api.service.PageService;

@Component("pageService")
public class PageServiceImpl implements PageService {

	@Autowired
	private PageDao pageDao;

	@Override
	public List<Page> listByCondition(Map<String, Object> param) {
		return pageDao.listByCondition(param);
	}

}
