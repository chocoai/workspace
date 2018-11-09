package com.yhcrt.weihu.cms.manager.assist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.dao.assist.CmsCommentDao;
import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.cms.entity.assist.CmsComment;
import com.yhcrt.weihu.cms.entity.assist.CmsCommentExt;
import com.yhcrt.weihu.cms.manager.assist.CmsBaoLiaoMng;
import com.yhcrt.weihu.cms.manager.assist.CmsCommentExtMng;
import com.yhcrt.weihu.cms.manager.assist.CmsCommentMng;
import com.yhcrt.weihu.cms.manager.assist.CmsSensitivityMng;
import com.yhcrt.weihu.cms.manager.main.ContentCountMng;
import com.yhcrt.weihu.cms.manager.main.ContentMng;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.manager.CmsSiteMng;
import com.yhcrt.weihu.core.manager.CmsUserMng;

@Service
@Transactional
public class CmsCommentMngImpl implements CmsCommentMng {
	@Transactional(readOnly = true)
	public Pagination getPage(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize) {
		Pagination page = dao.getPage(siteId, contentId,greaterThen, checked,
				recommend, desc, pageNo, pageSize, false);
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getBaoliaoPage(Integer siteId, Integer baoliaoId,
			Integer greaterThen, Boolean checked, Boolean recommend,boolean orderBy,
			int pageNo, int pageSize) {
		Pagination page = dao.getBaoLiaoPage(siteId,baoliaoId, greaterThen, checked,
				recommend, orderBy, pageNo, pageSize, true);
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getPageForTag(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize) {
		Pagination page = dao.getPage(siteId, contentId, greaterThen, checked,
				recommend, desc, pageNo, pageSize, true);
		return page;
	}
	@Transactional(readOnly = true)
	public Pagination getChildPage(Integer parentId, boolean cacheable, int pageNo, int pageSize) {
		Pagination page = dao.getChildPage(parentId, true,pageNo, pageSize);
		return page;
	}
	@Transactional(readOnly = true)
	public List<CmsComment> getChildList(Integer parentId, boolean cacheable) {
		return dao.getChildList(parentId, true);
	}
	public Pagination getPageForMember(Integer siteId, Integer contentId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize){
		Pagination page = dao.getPageForMember(siteId, contentId,toUserId,fromUserId, greaterThen, checked,
				recommend, desc, pageNo, pageSize, false);
		return page;
	}
	public Pagination getBaoliaoPageForMember(Integer siteId, Integer baoliaoId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize){
		Pagination page = dao.getBaoLiaoPageForMember(siteId, baoliaoId,toUserId,fromUserId,greaterThen, checked,
				recommend, pageNo, pageSize, false);
		return page;
	}
	@Transactional(readOnly = true)
	public List<CmsComment> getListForDel(Integer siteId, Integer userId,Integer commentUserId,String ip){
		return dao.getListForDel(siteId,userId,commentUserId,ip);
	}

	@Transactional(readOnly = true)
	public List<CmsComment> getListForTag(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int count) {
		return dao.getList(siteId, contentId,greaterThen, checked, recommend,
				desc, count, true);
	}
	@Transactional(readOnly = true)
	public List<CmsComment> getBaoliaoList(Integer siteId, Integer baoliaoId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean orderBy, int count) {
		return dao.getBaoLiaoList(siteId, baoliaoId, greaterThen, checked, recommend, count, true, orderBy);
	}
	public List<CmsComment> getBList(Integer baoliaoId) {
		
		return dao.getBLList(baoliaoId);
	}
	@Transactional(readOnly = true)
	public CmsComment findById(Integer id) {
		CmsComment entity = dao.findById(id);
		return entity;
	}

	public CmsComment comment(Integer pid,Integer score,String text, String ip, Integer contentId,
			Integer siteId, Integer userId, boolean checked, boolean recommend) {
		CmsComment comment = new CmsComment();
		comment.setContent(contentMng.findById(contentId));
		comment.setSite(cmsSiteMng.findById(siteId));
		if (userId != null) {
			comment.setCommentUser(cmsUserMng.findById(userId));
		}
		CmsComment p = new CmsComment();
		if(pid != null){
			p = findById(pid);
			comment.setParent(p);
		} 
		
		comment.setChecked(checked);
		comment.setRecommend(recommend);
		comment.setScore(score);
		comment.init();
		dao.save(comment);
		
		text = cmsSensitivityMng.replaceSensitivity(text);
		cmsCommentExtMng.save(ip, text, comment);
		contentCountMng.commentCount(contentId);
		return comment;
	}
	public CmsComment commentByBaoliao(Integer pid,Integer score,String text, String ip, CmsBaoLiao baoliao,
			Integer siteId, Integer userId, boolean checked, boolean recommend) {
		CmsComment comment = new CmsComment();
		comment.setBaoliao(baoliao);
		comment.setSite(cmsSiteMng.findById(siteId));
		if (userId != null) {
			comment.setCommentUser(cmsUserMng.findById(userId));
		}
		CmsComment p = new CmsComment();
		if(pid != null){
			p = findById(pid);
			comment.setParent(p);
		} 
		comment.setChecked(checked);
		comment.setRecommend(recommend);
		comment.setScore(score);
		comment.init();
		dao.save(comment);
		text = cmsSensitivityMng.replaceSensitivity(text);
		cmsCommentExtMng.save(ip, text, comment);
		return comment;
	}
	public CmsComment update(CmsComment bean, CmsCommentExt ext) {
		Updater<CmsComment> updater = new Updater<CmsComment>(bean);
		bean = dao.updateByUpdater(updater);
		cmsCommentExtMng.update(ext);
		return bean;
	}

	public int deleteByContentId(Integer contentId) {
		cmsCommentExtMng.deleteByContentId(contentId);
		return dao.deleteByContentId(contentId);
	}
	public int deleteByBaoliaoId(Integer baoliaoId) {
		cmsCommentExtMng.deleteByContentId(baoliaoId);
		return dao.deleteByBaoliaoId(baoliaoId);
	}
	public CmsComment deleteById(Integer id) {
		CmsComment bean = dao.deleteById(id);
		return bean;
	}

	public CmsComment[] deleteByIds(Integer[] ids) {
		CmsComment[] beans = new CmsComment[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public CmsComment[] checkByIds(Integer[] ids, CmsUser user, boolean checked) {
		CmsComment[] beans = new CmsComment[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = checkById(ids[i],user,checked);
		}
		return beans;
	}
	
	
	private CmsComment checkById(Integer id,CmsUser checkUser,boolean checked){
		CmsComment bean=findById(id);
		Updater<CmsComment> updater = new Updater<CmsComment>(bean);
		bean = dao.updateByUpdater(updater);
		bean.setChecked(checked);
		return bean;
	}
	
	public CmsComment ups(Integer id) {
		CmsComment bean = findById(id);
		Updater<CmsComment> updater = new Updater<CmsComment>(bean);
		bean = dao.updateByUpdater(updater);
		bean.setUps(bean.getUps() + 1);
		return bean;
	}

	public CmsComment downs(Integer id) {
		CmsComment bean = findById(id);
		Updater<CmsComment> updater = new Updater<CmsComment>(bean);
		bean = dao.updateByUpdater(updater);
		bean.setDowns(bean.getDowns() + 1);
		return bean;
	}

	private CmsSensitivityMng cmsSensitivityMng;
	private CmsUserMng cmsUserMng;
	private CmsSiteMng cmsSiteMng;
	private ContentMng contentMng;
	private CmsBaoLiaoMng baoliaoMng;
	private ContentCountMng contentCountMng;
	private CmsCommentExtMng cmsCommentExtMng;
	private CmsCommentDao dao;

	@Autowired
	public void setCmsSensitivityMng(CmsSensitivityMng cmsSensitivityMng) {
		this.cmsSensitivityMng = cmsSensitivityMng;
	}

	@Autowired
	public void setCmsUserMng(CmsUserMng cmsUserMng) {
		this.cmsUserMng = cmsUserMng;
	}

	@Autowired
	public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
		this.cmsSiteMng = cmsSiteMng;
	}

	@Autowired
	public void setContentMng(ContentMng contentMng) {
		this.contentMng = contentMng;
	}

	@Autowired
	public void setCmsCommentExtMng(CmsCommentExtMng cmsCommentExtMng) {
		this.cmsCommentExtMng = cmsCommentExtMng;
	}

	@Autowired
	public void setContentCountMng(ContentCountMng contentCountMng) {
		this.contentCountMng = contentCountMng;
	}

	@Autowired
	public void setDao(CmsCommentDao dao) {
		this.dao = dao;
	}






}