package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsTopicMsg;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsTopicMsgDao {
	public Pagination getPage(int pageNo, int pageSize);

	public BbsTopicMsg findById(Integer id);

	public BbsTopicMsg save(BbsTopicMsg bean);

	public BbsTopicMsg updateByUpdater(Updater<BbsTopicMsg> updater);

	public BbsTopicMsg deleteById(Integer id);
	
	public List<BbsTopicMsg> getTopicList(Integer userId,Integer count) ;
}