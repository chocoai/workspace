package com.yhcrt.weihu.cms.dao.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsComment;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsCommentDao{
	public Pagination getPage(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize, boolean cacheable);
	//报料分页
	public Pagination getBaoLiaoPage(Integer siteId, Integer baoliaoId,
			Integer greaterThen, Boolean checked, Boolean recommend,boolean orderBy,
			int pageNo, int pageSize, boolean cacheable);
	
	public Pagination getChildPage(Integer parentId, boolean cacheable, int pageNo, int pageSize);
	
	public List<CmsComment> getList(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean orderBy, int count, boolean cacheable);
	
	public List<CmsComment> getBaoLiaoList(Integer siteId, Integer baoliaoId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			 int count, boolean cacheable,boolean orderBy);
	public List<CmsComment> getBLList(Integer baoliaoId);
	
	public List<CmsComment> getChildList(Integer parentId, boolean cacheable);
	
	public CmsComment findById(Integer id);

	public int deleteByContentId(Integer contentId);

	public int deleteByBaoliaoId(Integer baoliaoId);
	
	public CmsComment save(CmsComment bean);

	public CmsComment updateByUpdater(Updater<CmsComment> updater);

	public CmsComment deleteById(Integer id);

	public Pagination getPageForMember(Integer siteId, Integer contentId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize,boolean cacheable);
	
	public Pagination getBaoLiaoPageForMember(Integer siteId, Integer baoliaoId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			int pageNo, int pageSize,boolean cacheable);
	
	public List<CmsComment> getListForDel(Integer siteId, Integer userId,
			Integer commentUserId, String ip);
}