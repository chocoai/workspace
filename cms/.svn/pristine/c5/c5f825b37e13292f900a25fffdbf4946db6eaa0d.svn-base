package com.yhcrt.weihu.cms.dao.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsTT;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsTTDao {
	public List<CmsTT> getList(Integer siteId, Integer typeId, Integer isAdmin,Integer demand,Boolean recommend,
			Boolean checked,  boolean desc, boolean cacheable, int first, int max);

	public CmsTT findById(Integer id);

	public CmsTT save(CmsTT bean);

	public CmsTT updateByUpdater(Updater<CmsTT> updater);

	public CmsTT deleteById(Integer id);
	
	public Pagination getPage(Integer siteId,Integer typeId, Integer isAdmin,Integer demand,Integer userId,Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo, int pageSize);
	
	public Pagination getAllPage(Integer siteId, Integer typeId[],Integer isAdmin,Integer demand, Integer userId, Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo, int pageSize);

}