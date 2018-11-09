package com.yhcrt.weihu.cms.dao.main;

import com.yhcrt.weihu.cms.entity.main.ContentCheck;
import com.yhcrt.weihu.common.hibernate3.Updater;

public interface ContentCheckDao {
	public ContentCheck findById(Long id);

	public ContentCheck save(ContentCheck bean);

	public ContentCheck updateByUpdater(Updater<ContentCheck> updater);
}