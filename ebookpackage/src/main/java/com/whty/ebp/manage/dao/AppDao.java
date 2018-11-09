package com.whty.ebp.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.model.EbpAppFile;

public interface AppDao extends IBaseDao<EbpApp> {

	@SuppressWarnings("rawtypes")
	void updateNoUpdate(Map map);

	@SuppressWarnings("rawtypes")
	List<EbpApp> getNewProductAppList(Map map);
	
	
	@SuppressWarnings("rawtypes")
	public List<EbpApp> getNewProductAppModelCode(Map map);
	
	@SuppressWarnings("rawtypes")
	public List<EbpApp> getNewProductAppListPlatformCode(Map map);
	
	EbpApp queryById(String id);

	@SuppressWarnings("rawtypes")
	EbpApp loadProductNewApp(Map map);
	
	public void saveAppFileBatch(List<EbpAppFile> list);
	
	@SuppressWarnings("rawtypes")
	public void saveAppFileUpgradeBatch(List<Map> list);
	
	@SuppressWarnings("rawtypes")
	public void deleteAppFile(List list);
	
	@SuppressWarnings("rawtypes")
	public void deleteAppUpgradeFile(Map map);
	
	@SuppressWarnings("rawtypes")
	public List<EbpAppFile> queryAppFile(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<EbpAppFile> queryAppUpgradeFile(Map paramMap);

	@SuppressWarnings("rawtypes")
	EbpApp getNewyidongjiangtaiEbpApp();

	List<EbpApp> queryNewProductAppListByModelCodeAndPlatformCodeMap(Map map);

	EbpApp loadApp(Map map);

	String getbaiduDownUrl(String id);

	void updateMd5(EbpApp bean);

}
