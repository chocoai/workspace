package com.yhcrt.weihu.cms.manager.assist;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsUser;

public interface CmsBaoLiaoMng {
	public Pagination getPage(Integer siteId,Integer userId,Integer state,String title,Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo, int pageSize);
	public Pagination getPageByTitle(Integer siteId,String title, int pageNo, int pageSize);
	@Transactional(readOnly = true)
	public List<CmsBaoLiao> getList(Integer siteId, Integer userId, Integer state,String title,Boolean recommend,
			Boolean checked, boolean desc,boolean cacheable, int first, int max);
	
	public CmsBaoLiao findById(Integer id);

	public CmsBaoLiao saveBaoLiao(CmsBaoLiao bean);
	public CmsBaoLiao save(CmsBaoLiao bean,String[] picPaths, String[] picDescs);

	public CmsBaoLiao update(CmsBaoLiao bean);
	public CmsBaoLiao update(CmsBaoLiao bean,String[] picPaths, String[] picDescs);

	public CmsBaoLiao deleteById(Integer id);
	
	public CmsBaoLiao[] deleteByIds(Integer[] ids);
	
	public CmsBaoLiao[] checkByIds(Integer[] ids,CmsUser checkUser,Boolean checkStatus);
}