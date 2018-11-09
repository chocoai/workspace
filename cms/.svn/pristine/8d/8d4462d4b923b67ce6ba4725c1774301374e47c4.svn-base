package com.yhcrt.weihu.cms.dao.assist.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.cms.dao.assist.CmsCommentDao;
import com.yhcrt.weihu.cms.entity.assist.CmsComment;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class CmsCommentDaoImpl extends HibernateBaseDao<CmsComment, Integer>
		implements CmsCommentDao {
	public Pagination getPage(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize, boolean cacheable) {
		Finder f = getFinder(siteId, contentId, null,null,greaterThen, checked,
				recommend, desc, cacheable);
		return find(f, pageNo, pageSize);
	}
	public Pagination getBaoLiaoPage(Integer siteId, Integer baoliaoId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean orderBy, int pageNo, int pageSize, boolean cacheable) {
		Finder f = Finder.create("from CmsComment bean where 1=1 and bean.parent.id is null and bean.content.id is null");
		if (baoliaoId != null) {
			f.append(" and bean.baoliao.id=:baoliaoId"); 
			f.setParam("baoliaoId", baoliaoId);
		} else if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if (greaterThen != null) {
			f.append(" and bean.ups>=:greatTo");
			f.setParam("greatTo", greaterThen);
		}
		if (checked != null) {
			f.append(" and bean.checked=:checked");
			f.setParam("checked", checked);
		}
		if(recommend!=null){
			f.append(" and bean.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		//orderBy:1,回复数多、时间降序；0，默认时间降序
		if(orderBy){
			f.append(" order by bean.commts desc,bean.createTime desc");
		}else{
			f.append(" order by bean.createTime desc");
		}
		f.setCacheable(cacheable);
		return find(f, pageNo, pageSize);
	}
	public Pagination getChildPage(Integer parentId, boolean cacheable, int pageNo, int pageSize){
		Finder f = Finder.create("from CmsComment bean where 1=1");
		if (parentId != null) {
			f.append(" and bean.parent.id=:parentId");
			f.setParam("parentId", parentId);
		}
		f.append(" order by bean.createTime desc");
		f.setCacheable(cacheable);
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List<CmsComment> getChildList(Integer parentId, boolean cacheable) {
		Finder f = Finder.create("from CmsComment bean where 1=1");
		if (parentId != null) {
			f.append(" and bean.parent.id=:parentId");
			f.setParam("parentId", parentId);
		}
		f.append(" order by bean.createTime desc");
		f.setCacheable(cacheable);
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<CmsComment> getList(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int count, boolean cacheable) {
		Finder f = getFinder(siteId, contentId,null,null,greaterThen, checked,
				recommend, desc, cacheable);
		f.setMaxResults(count);
		return find(f);
	}
	@SuppressWarnings("unchecked")
	public List<CmsComment> getBaoLiaoList(Integer siteId, Integer baoliaoId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			int count, boolean cacheable,boolean orderBy) {
		Finder f = Finder.create("from CmsComment bean where 1=1 and bean.parent.id is null and bean.content.id is null");
		if (baoliaoId != null) {
			f.append(" and bean.baoliao.id=:baoliaoId");
			f.setParam("baoliaoId", baoliaoId);
		} else if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if (greaterThen != null) {
			f.append(" and bean.ups>=:greatTo");
			f.setParam("greatTo", greaterThen);
		}
		if (checked != null) {
			f.append(" and bean.checked=:checked");
			f.setParam("checked", checked);
		}
		if(recommend!=null){
			f.append(" and bean.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		//orderBy:1,回复数多、时间降序；0，默认时间降序
		if (orderBy) {
			f.append(" order by bean.commts desc,bean.createTime desc");
		} else {
			f.append(" order by bean.createTime desc");
		}
		f.setCacheable(cacheable);
		f.setMaxResults(count);
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmsComment> getBLList(Integer baoliaoId) {
		Finder f = Finder.create("from CmsComment bean where 1=1 and bean.content.id is null");
		if (baoliaoId != null) {
			f.append(" and bean.baoliao.id=:baoliaoId");
			f.setParam("baoliaoId", baoliaoId);
		}
		return find(f);
	}
	public Pagination getPageForMember(Integer siteId, Integer contentId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize,boolean cacheable){
		Finder f = Finder.create("from CmsComment bean where 1=1 and bean.baoliao.id is null");
		if (contentId != null) {
			f.append(" and bean.content.id=:contentId");
			f.setParam("contentId", contentId);
		} else if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		
		if(toUserId!=null){
			f.append(" and bean.commentUser.id=:commentUserId");
			f.setParam("commentUserId", toUserId);
		}else if(fromUserId!=null){
			f.append(" and bean.content.user.id=:fromUserId");
			f.setParam("fromUserId", fromUserId);
		}
		if (greaterThen != null) {
			f.append(" and bean.ups>=:greatTo");
			f.setParam("greatTo", greaterThen);
		}
		if (checked != null) {
			f.append(" and bean.checked=:checked");
			f.setParam("checked", checked);
		}
		if(recommend!=null){
			f.append(" and bean.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		if (desc) {
			f.append(" order by bean.id desc");
		} else {
			f.append(" order by bean.id asc");
		}
		f.setCacheable(cacheable);
		
		return find(f, pageNo, pageSize);
	}
	public Pagination getBaoLiaoPageForMember(Integer siteId, Integer baoliaoId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			 int pageNo, int pageSize,boolean cacheable){
		Finder f = Finder.create("from CmsComment bean where 1=1 and bean.parent.id is null and bean.content.id is null");
		if (baoliaoId != null) {
			f.append(" and bean.baoliao.id=:baoliaoId");
			f.setParam("baoliaoId", baoliaoId);
		} else if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if(toUserId!=null){
			f.append(" and bean.commentUser.id=:commentUserId");
			f.setParam("commentUserId", toUserId);
		}else if(fromUserId!=null){
			f.append(" and bean.baoliao.user.id=:fromUserId");
			f.setParam("fromUserId", fromUserId);
		}
		if (greaterThen != null) {
			f.append(" and bean.ups>=:greatTo");
			f.setParam("greatTo", greaterThen);
		}
		if (checked != null) {
			f.append(" and bean.checked=:checked");
			f.setParam("checked", checked);
		}
		if(recommend!=null){
			f.append(" and bean.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		f.append(" order by bean.createTime desc");
		f.setCacheable(cacheable);
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List<CmsComment> getListForDel(Integer siteId, Integer userId,
			Integer commentUserId, String ip){
		Finder f = Finder.create("from CmsComment bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if(commentUserId!=null){
			f.append(" and bean.commentUser.id=:commentUserId");
			f.setParam("commentUserId", commentUserId);
		}
		if(userId!=null){
			f.append(" and bean.content.user.id=:fromUserId");
			f.setParam("fromUserId", userId);
		}
		f.setCacheable(false);
		return find(f);
	}

	private Finder getFinder(Integer siteId, Integer contentId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, boolean cacheable) {
		Finder f = Finder.create("from CmsComment bean where 1=1 and bean.parent.id is null and bean.baoliao.id is null");
		if (contentId != null) {
			f.append(" and bean.content.id=:contentId");
			f.setParam("contentId", contentId);
		} else if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		
		if(toUserId!=null){
			f.append(" and bean.commentUser.id=:commentUserId");
			f.setParam("commentUserId", toUserId);
		}else if(fromUserId!=null){
			f.append(" and bean.content.user.id=:fromUserId");
			f.setParam("fromUserId", fromUserId);
		}
		if (greaterThen != null) {
			f.append(" and bean.ups>=:greatTo");
			f.setParam("greatTo", greaterThen);
		}
		if (checked != null) {
			f.append(" and bean.checked=:checked");
			f.setParam("checked", checked);
		}
		if(recommend!=null){
			f.append(" and bean.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		if (desc) {
			f.append(" order by bean.id desc");
		} else {
			f.append(" order by bean.id asc");
		}
		f.setCacheable(cacheable);
		return f;
	}

	public CmsComment findById(Integer id) {
		CmsComment entity = get(id);
		return entity;
	}

	public CmsComment save(CmsComment bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsComment deleteById(Integer id) {
		CmsComment entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	public int deleteByContentId(Integer contentId) {
		String hql = "delete from CmsComment bean where bean.content.id=:contentId";
		return getSession().createQuery(hql).setParameter("contentId",
				contentId).executeUpdate();
	}
	public int deleteByBaoliaoId(Integer baoliaoId) {
		String hql = "delete from CmsComment bean where bean.baoliao.id=:baoliaoId";
		return getSession().createQuery(hql).setParameter("baoliaoId",
				baoliaoId).executeUpdate();
	}
	
	@Override
	protected Class<CmsComment> getEntityClass() {
		return CmsComment.class;
	}




}