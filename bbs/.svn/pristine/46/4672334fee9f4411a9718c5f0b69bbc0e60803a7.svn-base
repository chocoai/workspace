package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsSession;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

import net.sf.ehcache.Ehcache;

public interface BbsSessionDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<BbsSession> getList(Integer count);
	
	public Integer total(boolean member);
	
	public void freshCacheToDB(Ehcache cache);

	public BbsSession findById(Long id);
	
	public BbsSession findBySessionId(String sessionId);

	public BbsSession save(BbsSession bean);

	public BbsSession updateByUpdater(Updater<BbsSession> updater);

	public BbsSession deleteById(Long id);
}