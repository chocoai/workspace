package com.yhcrt.weihu.cms.manager.main;

import com.yhcrt.weihu.cms.entity.main.Channel;
import com.yhcrt.weihu.cms.entity.main.ChannelExt;

public interface ChannelExtMng {
	public ChannelExt save(ChannelExt ext, Channel channel);

	public ChannelExt update(ChannelExt ext);
}