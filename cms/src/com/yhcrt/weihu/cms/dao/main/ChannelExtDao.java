package com.yhcrt.weihu.cms.dao.main;

import com.yhcrt.weihu.cms.entity.main.ChannelExt;
import com.yhcrt.weihu.common.hibernate3.Updater;

public interface ChannelExtDao {
	public ChannelExt save(ChannelExt bean);

	public ChannelExt updateByUpdater(Updater<ChannelExt> updater);
}