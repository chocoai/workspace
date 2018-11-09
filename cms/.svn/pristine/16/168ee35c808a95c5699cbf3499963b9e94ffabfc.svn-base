package com.yhcrt.weihu.cms.manager.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.cms.entity.assist.CmsComment;
import com.yhcrt.weihu.cms.entity.assist.CmsCommentExt;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsUser;

public interface CmsCommentMng {
	public Pagination getPage(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize);
	public Pagination getBaoliaoPage(Integer siteId, Integer baoliaoId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean orderBy, int pageNo, int pageSize);
	public Pagination getPageForTag(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize);
	
	public Pagination getChildPage(Integer parentId, boolean cacheable, int pageNo, int pageSize);
	public List<CmsComment> getChildList(Integer parentId, boolean cacheable);
	
	/**
	 * 
	 * @param siteId
	 * @param contentId
	 * @param toUserId 写评论的用户
	 * @param fromUserId 投稿的信息接收到的相关评论
	 * @param greaterThen
	 * @param checked
	 * @param recommend
	 * @param desc
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPageForMember(Integer siteId, Integer contentId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize);
	public Pagination getBaoliaoPageForMember(Integer siteId, Integer baoliaoId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize);
	/**
	 * 
	 * @param siteId
	 * @param userId 发表信息用户id
	 * @param commentUserId 评论用户id
	 * @param ip  评论来访ip
	 * @return
	 */
	
	public List<CmsComment> getListForDel(Integer siteId, Integer userId,Integer commentUserId,String ip);

	public List<CmsComment> getListForTag(Integer siteId, Integer contentId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int count);
	public List<CmsComment> getBaoliaoList(Integer siteId, Integer baoliaoId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int count);
	public List<CmsComment> getBList(Integer baoliaoId);
	public CmsComment findById(Integer id);

	public CmsComment comment(Integer pid,Integer score,String text, String ip, Integer contentId,
			Integer siteId, Integer userId, boolean checked, boolean recommend);
	public CmsComment commentByBaoliao(Integer pid,Integer score,String text, String ip, CmsBaoLiao baoliao,
			Integer siteId, Integer userId, boolean checked, boolean recommend);
	public CmsComment update(CmsComment bean, CmsCommentExt ext);

	public int deleteByContentId(Integer contentId);
	
	public int deleteByBaoliaoId(Integer baoliaoId);

	public CmsComment deleteById(Integer id);

	public CmsComment[] deleteByIds(Integer[] ids);

	public CmsComment ups(Integer id);

	public CmsComment downs(Integer id);

	public CmsComment[] checkByIds(Integer[] ids, CmsUser user, boolean checked);
}