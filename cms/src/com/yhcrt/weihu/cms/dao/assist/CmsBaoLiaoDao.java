package com.yhcrt.weihu.cms.dao.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsBaoLiaoDao {
	public Pagination getPage(Integer siteId,Integer userId,Integer state,String title,Boolean checked, 
			Boolean recommend,boolean desc,boolean cacheable, int pageNo, int pageSize);
	public Pagination getPageByTitle(Integer siteId,String title, int pageNo, int pageSize);
	public List<CmsBaoLiao> getList(Integer siteId, Integer userId,Integer state,String title,
			Boolean checked, Boolean recommend, boolean desc,boolean cacheable,int first, int max);
	
	public CmsBaoLiao findById(Integer id);

	public CmsBaoLiao save(CmsBaoLiao bean);

	public CmsBaoLiao updateByUpdater(Updater<CmsBaoLiao> updater);

	public CmsBaoLiao deleteById(Integer id);
}