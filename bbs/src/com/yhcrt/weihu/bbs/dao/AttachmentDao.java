package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.Attachment;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface AttachmentDao {
	public Pagination getPage(int pageNo, int pageSize);

	public Attachment findById(Integer id);

	public Attachment save(Attachment bean);

	public Attachment updateByUpdater(Updater<Attachment> updater);

	public Attachment deleteById(Integer id);
}