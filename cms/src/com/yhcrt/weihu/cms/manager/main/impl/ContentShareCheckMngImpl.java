package com.yhcrt.weihu.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.dao.main.ContentShareCheckDao;
import com.yhcrt.weihu.cms.entity.main.Channel;
import com.yhcrt.weihu.cms.entity.main.Content;
import com.yhcrt.weihu.cms.entity.main.ContentShareCheck;
import com.yhcrt.weihu.cms.manager.main.ContentShareCheckMng;
import com.yhcrt.weihu.common.page.Pagination;

@Service
@Transactional
public class ContentShareCheckMngImpl implements ContentShareCheckMng {

	public ContentShareCheck findById(Integer id) {
		return dao.findById(id);
	}

	public List<ContentShareCheck> getList(Integer contentId, Integer channelId) {
		return dao.getList(contentId, channelId);
	}

	public Pagination getPageForShared(String title, Byte status,
			Integer siteId, Integer targetSiteId, Integer requestSiteId, int pageNo, int pageSize) {
		return dao.getPageForShared(title, status, siteId, targetSiteId,requestSiteId,
				pageNo, pageSize);
	}

	public ContentShareCheck save(ContentShareCheck check) {
		dao.save(check);
		return check;
	}

	public ContentShareCheck save(ContentShareCheck bean, Content content,
			Channel channel) {
		bean.init();
		bean.setContent(content);
		bean.setChannel(channel);
		dao.save(bean);
		content.setContentShareCheck(bean);
		channel.setContentShareCheck(bean);
		return bean;
	}

	public ContentShareCheck update(ContentShareCheck bean) {
		dao.update(bean);
		return bean;
	}

	public ContentShareCheck deleteById(Integer id) {
		return dao.findById(id);
	}

	public ContentShareCheck[] deleteByIds(Integer[] ids) {
		return dao.deleteByIds(ids);
	}

	private ContentShareCheckDao dao;

	@Autowired
	public void setDao(ContentShareCheckDao dao) {
		this.dao = dao;
	}

}