package com.yhcrt.weihu.cms.manager.assist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.dao.assist.CmsTTypeDao;
import com.yhcrt.weihu.cms.entity.assist.CmsTType;
import com.yhcrt.weihu.cms.manager.assist.CmsTTypeMng;
import com.yhcrt.weihu.common.hibernate3.Updater;

@Service
@Transactional
public class CmsTTypeMngImpl implements CmsTTypeMng {
	@Transactional(readOnly = true)
	public List<CmsTType> getList(Integer siteId) {
		return dao.getList(siteId);
	}

	@Transactional(readOnly = true)
	public CmsTType findById(Integer id) {
		CmsTType entity = dao.findById(id);
		return entity;
	}

	public CmsTType save(CmsTType bean) {
		dao.save(bean);
		return bean;
	}

	public CmsTType update(CmsTType bean) {
		Updater<CmsTType> updater = new Updater<CmsTType>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsTType deleteById(Integer id) {
		CmsTType bean = dao.deleteById(id);
		return bean;
	}

	public CmsTType[] deleteByIds(Integer[] ids) {
		CmsTType[] beans = new CmsTType[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private CmsTTypeDao dao;

	@Autowired
	public void setDao(CmsTTypeDao dao) {
		this.dao = dao;
	}
}