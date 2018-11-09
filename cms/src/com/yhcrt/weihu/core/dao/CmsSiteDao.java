package com.yhcrt.weihu.core.dao;

import java.util.List;

import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.core.entity.CmsSite;

/**
 * 站点DAO接口
 */
public interface CmsSiteDao {
	/**
	 * 获得站点数量
	 * 
	 * @param cacheable
	 * @return
	 */
	public int siteCount(boolean cacheable);

	/**
	 * 获得所有站点
	 * 
	 * @param cacheable
	 * @return
	 */
	public List<CmsSite> getList(boolean cacheable);
	
	public List<CmsSite> getListByMaster(Boolean master);
	
	public List<CmsSite> getListByParent(Integer parentId);
	
	public int  getCountByProperty(String property);
	
	public List<CmsSite> getTopList();

	public CmsSite findByDomain(String domain);
	
	public CmsSite findByDomain(String domain, boolean cacheable);
	
	public CmsSite findByAccessPath(String accessPath);

	public CmsSite findById(Integer id);

	public CmsSite save(CmsSite bean);

	public CmsSite updateByUpdater(Updater<CmsSite> updater);

	public CmsSite deleteById(Integer id);

	
}