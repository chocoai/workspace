package com.yhcrt.weihu.cms.dao.assist;

import com.yhcrt.weihu.cms.entity.assist.CmsVoteItem;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsVoteItemDao {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsVoteItem findById(Integer id);

	public CmsVoteItem save(CmsVoteItem bean);

	public CmsVoteItem updateByUpdater(Updater<CmsVoteItem> updater);

	public CmsVoteItem deleteById(Integer id);
}