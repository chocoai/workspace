package com.yhcrt.weihu.cms.dao.main;

import com.yhcrt.weihu.cms.entity.main.ContentTxt;
import com.yhcrt.weihu.common.hibernate3.Updater;

public interface ContentTxtDao {
	public ContentTxt findById(Integer id);

	public ContentTxt save(ContentTxt bean);

	public ContentTxt updateByUpdater(Updater<ContentTxt> updater);
}