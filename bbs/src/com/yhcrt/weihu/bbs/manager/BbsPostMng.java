package com.yhcrt.weihu.bbs.manager;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yhcrt.weihu.bbs.entity.BbsPost;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsPostMng {
	
	
	
	/**
	 * 获取用户回复的帖子
	 * @param userId
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page<BbsPost> getPage(Integer userId,Integer pageSize,Integer pageNo);
	
	public BbsPost post(Integer userId, Integer siteId, Integer topicId,Integer postTypeId,
			String title, String content, String ip, List<MultipartFile> file,
			List<String> code,Integer upPostId);
	
	public BbsPost post(Integer userId, Integer siteId, Integer topicId,Integer postTypeId,
			String title, String content, String ip);

	public BbsPost updatePost(Integer id, String title, String content,
			BbsUser editor, String ip, List<MultipartFile> file,
			List<String> code);
	public BbsPost updatePost(Integer id, String title, String content,
			BbsUser editor, String ip);

	public BbsPost shield(Integer id, String reason, BbsUser operator,Short status);

	public BbsPost reply(Integer userId, Integer siteId, Integer topicId,Integer postTypeId,
			String title, String content, String ip, List<MultipartFile> file,
			List<String> code,Integer upPostId);

	public List<BbsPost> getPostByTopic(Integer topicId);

	public Pagination getForTag(Integer siteId, Integer topicId,
			Integer userId, int pageNo, int pageSize);

	public Pagination getMemberReply(Integer webId, Integer memberId,
			int pageNo, int pageSize);

	public int getMemberReplyCount(Integer webId, Integer memberId);

	public BbsPost getLastPost(Integer forumId, Integer topicId);

	public int getIndexCount(Integer topicId);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsPost findById(Integer id);

	public BbsPost save(BbsPost bean);

	public BbsPost update(BbsPost bean);

	public BbsPost deleteById(Integer id);

	public BbsPost[] deleteByIds(Integer[] ids);

	public List<BbsPost> getList(int count, int orderby);
	
	public List<BbsPost> getPostByTopic(Integer topicId,Integer userId,Integer first,int count);
	
	public List<BbsPost> getByTopicList(Integer topicId);
}