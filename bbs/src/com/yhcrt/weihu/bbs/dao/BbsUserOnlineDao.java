package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsUserOnline;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsUserOnlineDao {

	public Pagination getPage(int pageNo, int pageSize);

	public BbsUserOnline findById(Integer id);

	public BbsUserOnline save(BbsUserOnline bean);
	
	public int clearCount(boolean week, boolean month,boolean year);

	public BbsUserOnline updateByUpdater(Updater<BbsUserOnline> bean);

	public BbsUserOnline deleteById(Integer id);

	public List<BbsUserOnline> getList();

}