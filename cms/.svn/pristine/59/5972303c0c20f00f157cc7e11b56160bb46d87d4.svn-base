package com.yhcrt.weihu.cms.manager.assist.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.dao.assist.CmsBaoLiaoDao;
import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.cms.entity.main.Content;
import com.yhcrt.weihu.cms.manager.assist.CmsBaoLiaoMng;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsUser;

@Service
@Transactional
public class CmsBaoLiaoMngImpl implements CmsBaoLiaoMng {
	@Transactional(readOnly = true)
	public Pagination getPage(Integer siteId,Integer userId,Integer state,String title,Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo, int pageSize) {
		Pagination page = dao.getPage(siteId,userId,state,title,recommend,checked,desc,cacheable,pageNo, pageSize);
		return page;
	}
	public Pagination getPageByTitle(Integer siteId, String title, int pageNo, int pageSize) {
		Pagination page = dao.getPageByTitle(siteId, title, pageNo, pageSize);
		return page;
	}
	@Transactional(readOnly = true)
	public List<CmsBaoLiao> getList(Integer siteId, Integer userId, Integer state,String title,Boolean recommend,
			Boolean checked, boolean desc,boolean cacheable, int first, int max) {
		return dao.getList(siteId, userId, state,title,recommend, checked, desc, cacheable,
				first, max);
	}

	@Transactional(readOnly = true)
	public CmsBaoLiao findById(Integer id) {
		CmsBaoLiao entity = dao.findById(id);
		return entity;
	}
	public CmsBaoLiao save(CmsBaoLiao bean,String[] picPaths, String[] picDescs) {
		saveBaoLiao(bean);
		// 保存图片集
				if (picPaths != null && picPaths.length > 0) {
					for (int i = 0, len = picPaths.length; i < len; i++) {
						if (!StringUtils.isBlank(picPaths[i])) {
							bean.addToPictures(picPaths[i], picDescs[i]);
						}
					}
				}
		return bean;
	}
	public CmsBaoLiao saveBaoLiao(CmsBaoLiao bean) {
		bean.init();
		dao.save(bean);
		return bean;
	}
	public CmsBaoLiao update(CmsBaoLiao bean,String[] picPaths, String[] picDescs) {
		Updater<CmsBaoLiao> updater = new Updater<CmsBaoLiao>(bean);
		bean = dao.updateByUpdater(updater);
		// 更新图片集
				bean.getPictures().clear();
				if (picPaths != null && picPaths.length > 0) {
					for (int i = 0, len = picPaths.length; i < len; i++) {
						if (!StringUtils.isBlank(picPaths[i])) {
							bean.addToPictures(picPaths[i], picDescs[i]);
						}
					}
				}
		return bean;
	}
	public CmsBaoLiao update(CmsBaoLiao bean) {
		Updater<CmsBaoLiao> updater = new Updater<CmsBaoLiao>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsBaoLiao deleteById(Integer id) {
		CmsBaoLiao bean = dao.deleteById(id);
		return bean;
	}
	
	public CmsBaoLiao[] deleteByIds(Integer[] ids) {
		CmsBaoLiao[] beans = new CmsBaoLiao[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private CmsBaoLiaoDao dao;

	@Autowired
	public void setDao(CmsBaoLiaoDao dao) {
		this.dao = dao;
	}
	@Override
	public CmsBaoLiao[] checkByIds(Integer[] ids, CmsUser checkUser, Boolean checkStatus) {
		CmsBaoLiao[] beans = new CmsBaoLiao[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = checkById(ids[i],checkUser,checkStatus);
		}
		return beans;
	}
	private CmsBaoLiao checkById(Integer id,CmsUser checkUser,Boolean checkStatus){
		CmsBaoLiao bean=findById(id);
		Updater<CmsBaoLiao> updater = new Updater<CmsBaoLiao>(bean);
		bean = dao.updateByUpdater(updater);
		if(checkStatus){
			bean.setAdmin(checkUser);
		}
		bean.setChecked(checkStatus);
		return bean;
	}
	
	
}