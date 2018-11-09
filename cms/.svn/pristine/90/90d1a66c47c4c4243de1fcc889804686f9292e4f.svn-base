package com.yhcrt.weihu.cms.manager.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsSearchWords;
import com.yhcrt.weihu.common.page.Pagination;

import net.sf.ehcache.Ehcache;

public interface CmsSearchWordsMng {
	public Pagination getPage(int pageNo, int pageSize);

	public List<CmsSearchWords> getList(String name,Integer orderBy,boolean cacheable);
	
	public CmsSearchWords findById(Integer id);

	public CmsSearchWords save(CmsSearchWords bean);

	public CmsSearchWords update(CmsSearchWords bean);

	public CmsSearchWords deleteById(Integer id);
	
	public CmsSearchWords[] deleteByIds(Integer[] ids);
	
	public int freshCacheToDB(Ehcache cache);

}