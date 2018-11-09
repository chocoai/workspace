package com.yhcrt.weihu.cms.manager.assist;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.entity.assist.CmsTT;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsUser;

public interface CmsTTMng {
	public Pagination getPage(Integer siteId, Integer typeId,Integer isAdmin,Integer demand, Integer userId,Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo, int pageSize);
	
	public Pagination getAllPage(Integer siteId, Integer typeId[], Integer isAdmin,Integer demand,Integer userId, Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo, int pageSize);
	
	@Transactional(readOnly = true)
	public List<CmsTT> getList(Integer siteId, Integer typeId,Integer isAdmin,Integer demand, Boolean recommend,
			Boolean checked, boolean desc,boolean cacheable, int first, int max);

	public CmsTT findById(Integer id);

	public CmsTT saveTT(CmsTT bean);
	public CmsTT save(CmsTT bean,String[] picPaths, String[] picDescs);
	
	public CmsTT update(CmsTT bean);
	public CmsTT update(CmsTT bean,String[] picPaths, String[] picDescs);
	
	public CmsTT deleteById(Integer id);

	public CmsTT[] deleteByIds(Integer[] ids);
	
	public CmsTT[] checkByIds(Integer[] ids,CmsUser checkUser,Boolean checkStatus);
}