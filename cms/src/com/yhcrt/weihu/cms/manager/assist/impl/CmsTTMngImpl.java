package com.yhcrt.weihu.cms.manager.assist.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.dao.assist.CmsTTDao;
import com.yhcrt.weihu.cms.entity.assist.CmsTT;
import com.yhcrt.weihu.cms.manager.assist.CmsTTMng;
import com.yhcrt.weihu.cms.manager.assist.CmsTTypeMng;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsUser;

@Service
@Transactional
public class CmsTTMngImpl implements CmsTTMng {
	@Transactional(readOnly = true)
	public Pagination getPage(Integer siteId, Integer typeId,Integer isAdmin,Integer demand,Integer userId, Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo,
			int pageSize) {
		return dao.getPage(siteId, typeId,isAdmin,demand,userId, recommend, checked, desc, cacheable,
				pageNo, pageSize);
	}
	
	@Transactional(readOnly = true)
	public Pagination getAllPage(Integer siteId, Integer typeIds[],Integer isAdmin,Integer demand,Integer userId, Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo,
			int pageSize) {
		return dao.getAllPage(siteId, typeIds,isAdmin,demand,userId, recommend, checked, desc, cacheable,
				pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List<CmsTT> getList(Integer siteId, Integer typeId,Integer isAdmin,Integer demand,
			Boolean recommend, Boolean checked, boolean desc,
			boolean cacheable, int first, int max) {
		return dao.getList(siteId, typeId, isAdmin,demand,recommend, checked, desc, cacheable,
				first, max);
	}

	@Override
	public CmsTT findById(Integer id) {
		CmsTT bean = dao.findById(id);
		return bean;
	}
	public CmsTT saveTT(CmsTT bean) {
		bean.init();
		dao.save(bean);
		return bean;
	}

	public CmsTT save(CmsTT bean,String[] picPaths, String[] picDescs) {
		saveTT(bean);
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

	public CmsTT update(CmsTT bean, String[] picPaths, String[] picDescs) {
		Updater<CmsTT> updater = new Updater<CmsTT>(bean);
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
	public CmsTT update(CmsTT bean) {
		Updater<CmsTT> updater = new Updater<CmsTT>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsTT deleteById(Integer id) {
		CmsTT bean = dao.deleteById(id);
		return bean;
	}

	public CmsTT[] deleteByIds(Integer[] ids) {
		CmsTT[] beans = new CmsTT[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	public CmsTT[] checkByIds(Integer[] ids,CmsUser checkUser,Boolean checkStatus) {
		CmsTT[] beans = new CmsTT[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = checkById(ids[i],checkUser,checkStatus);
		}
		return beans;
	}
	private CmsTT checkById(Integer id,CmsUser checkUser,Boolean checkStatus){
		CmsTT bean=findById(id);
		Updater<CmsTT> updater = new Updater<CmsTT>(bean);
		bean = dao.updateByUpdater(updater);
		if(checkStatus){
			bean.setAdmin(checkUser);
		}
		bean.setChecked(checkStatus);
		return bean;
	}
	private CmsTTDao dao;
	
	private CmsTTypeMng cmsTTypeMng;

	@Autowired
	public void setDao(CmsTTDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setCmsTType(CmsTTypeMng cmsTTypeMng) {
		this.cmsTTypeMng = cmsTTypeMng;
	}


}