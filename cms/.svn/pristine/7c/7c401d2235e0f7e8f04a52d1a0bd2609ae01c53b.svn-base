package com.yhcrt.weihu.cms.manager.assist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.dao.assist.CmsChangeDao;
import com.yhcrt.weihu.cms.entity.assist.CmsChange;
import com.yhcrt.weihu.cms.manager.assist.CmsChangeMng;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

@Service
@Transactional
public class CmsChangeMngImpl implements CmsChangeMng {
	@Transactional(readOnly = true)
	public Pagination getPage(Integer userId,Integer contentId,Integer siteId,boolean cacheable,int pageNo, int pageSize) {
		Pagination page = dao.getPage(userId,contentId,siteId,cacheable,pageNo, pageSize);
		return page;
	}
	@Override
	public List<CmsChange> getList(Integer siteId, Integer userId, Integer contentId, boolean cacheable) {
		List<CmsChange> list = dao.getList(siteId, userId, contentId, cacheable);
		return list;
	}
	@Transactional(readOnly = true)
	public CmsChange findById(Integer id) {
		CmsChange entity = dao.findById(id);
		return entity;
	}

	public CmsChange save(CmsChange bean) {
		bean.init();
		dao.save(bean);
		return bean;
	}

	public CmsChange update(CmsChange bean) {
		Updater<CmsChange> updater = new Updater<CmsChange>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsChange deleteById(Integer id) {
		CmsChange bean = dao.deleteById(id);
		return bean;
	}
	
	public CmsChange[] deleteByIds(Integer[] ids) {
		CmsChange[] beans = new CmsChange[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private CmsChangeDao dao;

	@Autowired
	public void setDao(CmsChangeDao dao) {
		this.dao = dao;
	}


}