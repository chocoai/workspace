package com.yhcrt.weihu.bbs.entity;

import com.yhcrt.weihu.bbs.entity.BbsTopic;

/**
 * 
 * 投票贴
 * 
 */
public class BbsVoteTopic extends BbsTopic {
	/**
	 * 总票数
	 */
	private Integer totalCount;

	public void init() {
		super.init();
		if (totalCount == null) {
			totalCount = 0;
		}
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public short getCategory() {
		return TOPIC_VOTE;
	}
}
