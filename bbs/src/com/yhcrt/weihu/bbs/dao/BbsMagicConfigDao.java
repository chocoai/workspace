package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsMagicConfig;
import com.yhcrt.weihu.common.hibernate3.Updater;

public interface BbsMagicConfigDao {
	
	public BbsMagicConfig findById(Integer id);

	public BbsMagicConfig save(BbsMagicConfig bean);

	public BbsMagicConfig updateByUpdater(Updater<BbsMagicConfig> updater);

	public BbsMagicConfig deleteById(Integer id);
}