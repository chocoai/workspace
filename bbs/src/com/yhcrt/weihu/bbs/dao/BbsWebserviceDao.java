package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsWebservice;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsWebserviceDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<BbsWebservice> getList(String type);

	public BbsWebservice findById(Integer id);

	public BbsWebservice save(BbsWebservice bean);

	public BbsWebservice updateByUpdater(Updater<BbsWebservice> updater);

	public BbsWebservice deleteById(Integer id);
}