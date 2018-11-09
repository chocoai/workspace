package com.yhcrt.weihu.cms.dao.assist;

import com.yhcrt.weihu.cms.entity.assist.CmsGuestbookExt;
import com.yhcrt.weihu.common.hibernate3.Updater;

public interface CmsGuestbookExtDao {
	public CmsGuestbookExt findById(Integer id);

	public CmsGuestbookExt save(CmsGuestbookExt bean);

	public CmsGuestbookExt updateByUpdater(Updater<CmsGuestbookExt> updater);

	public CmsGuestbookExt deleteById(Integer id);
}