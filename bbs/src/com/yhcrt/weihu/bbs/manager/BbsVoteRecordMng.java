package com.yhcrt.weihu.bbs.manager;


import com.yhcrt.weihu.bbs.entity.BbsVoteRecord;


public interface BbsVoteRecordMng {
	public BbsVoteRecord findRecord(Integer userId, Integer topicId);
	
	public BbsVoteRecord save(BbsVoteRecord bean);
}
