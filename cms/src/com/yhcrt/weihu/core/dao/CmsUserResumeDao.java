package com.yhcrt.weihu.core.dao;

import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.core.entity.CmsUserResume;

public interface CmsUserResumeDao {
	public CmsUserResume findById(Integer id);

	public CmsUserResume save(CmsUserResume bean);

	public CmsUserResume updateByUpdater(Updater<CmsUserResume> updater);
}