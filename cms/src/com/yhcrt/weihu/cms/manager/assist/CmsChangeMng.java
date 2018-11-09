package com.yhcrt.weihu.cms.manager.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsChange;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsChangeMng {
	public Pagination getPage(Integer userId,Integer contentId,Integer siteId,boolean cacheable,int pageNo, int pageSize);
    
    public List<CmsChange> getList(Integer siteId,Integer userId, Integer contentId, boolean cacheable);
	
	public CmsChange findById(Integer id);

	public CmsChange save(CmsChange bean);

	public CmsChange update(CmsChange bean);

	public CmsChange deleteById(Integer id);
	
	public CmsChange[] deleteByIds(Integer[] ids);
}