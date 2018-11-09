package com.yhcrt.weihu.bbs.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhcrt.weihu.bbs.entity.BbsSession;
import com.yhcrt.weihu.common.page.Pagination;

import net.sf.ehcache.Ehcache;

public interface BbsSessionMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<BbsSession> getList(Integer count);
	
	public void recordUserSession(HttpServletRequest request,HttpServletResponse response);
	
	public Integer total(boolean member);
	
	public void freshCacheToDB(Ehcache cache);

	public BbsSession findById(Long id);
	
	public BbsSession findBySessionId(String sessionId);

	public BbsSession save(BbsSession bean);

	public BbsSession update(BbsSession bean);

	public BbsSession deleteById(Long id);
	
	public BbsSession[] deleteByIds(Long[] ids);
}