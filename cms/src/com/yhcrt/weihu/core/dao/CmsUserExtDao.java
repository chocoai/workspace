package com.yhcrt.weihu.core.dao;

import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.core.entity.CmsUserExt;

public interface CmsUserExtDao {
	public CmsUserExt findById(Integer id);

	public CmsUserExt save(CmsUserExt bean);

	public CmsUserExt updateByUpdater(Updater<CmsUserExt> updater);
}