package com.yhcrt.weihu.bbs.dao;


import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsMessageReply;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsMessageReplyDao {
	public BbsMessageReply findById(Integer id);

	public BbsMessageReply save(BbsMessageReply bean);

	public BbsMessageReply updateByUpdater(Updater<BbsMessageReply> updater);

	public BbsMessageReply deleteById(Integer id);

	public Pagination getPageByMsgId(Integer msgId, Integer pageNo,
			Integer pageSize);
	
	public Pagination getPage(Integer pageNo, Integer pageSize);
	
	public List<BbsMessageReply> getList(Integer msgId,int count);
	
	public int countByReceiver(String username);
	
	public List<BbsMessageReply> getList(String username);
}