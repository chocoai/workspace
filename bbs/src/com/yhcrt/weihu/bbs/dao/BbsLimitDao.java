package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsLimit;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsLimitDao {
	public Pagination getPage(int pageNo, int pageSize);

	public BbsLimit findById(Integer id);
	
	public List<BbsLimit> getList(String ip,Integer userId);

	public BbsLimit save(BbsLimit bean);

	public BbsLimit updateByUpdater(Updater<BbsLimit> updater);

	public BbsLimit deleteById(Integer id);
}