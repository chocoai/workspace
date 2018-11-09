package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsUserExt;
import com.yhcrt.weihu.common.hibernate3.Updater;

public interface BbsUserExtDao {
	public BbsUserExt update(BbsUserExt ext);
	
	public BbsUserExt findById(Integer id);

	public BbsUserExt save(BbsUserExt bean);

	public BbsUserExt updateByUpdater(Updater<BbsUserExt> updater);
}