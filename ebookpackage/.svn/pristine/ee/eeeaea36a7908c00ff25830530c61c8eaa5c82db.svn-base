package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.model.EbpAppFile;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface AppService {

	public EbpApp getNewyidongjiangtaiEbpApp();

	HandlerResult listProductByPage(Map<String, Object> param, PageContext page);

	/*
	 * 开放升级
	 */
	@SuppressWarnings("rawtypes")
	void openUpdate(Map map);

	/*
	 * 开放升级
	 */
	void openUpdate(String id, String productId, String canUpdate);

	void save(EbpApp ebpApp) throws Exception;

	void update(EbpApp ebpApp);

	/*
	 * 查询各个产品最新的版本应用，查询时间为 2016-7-1 日前
	 */
	@SuppressWarnings("rawtypes")
	List<EbpApp> getNewProductAppList(Map map);

	/*
	 * 根据id查询应用
	 */
	@SuppressWarnings("rawtypes")
	EbpApp loadProductNewApp(Map map);

	EbpApp queryById(String id);

	public boolean unzipApp(String appId);

	public void saveAppFile(List<EbpAppFile> list);

	@SuppressWarnings("rawtypes")
	public List<EbpAppFile> queryAppFile(Map map);

	@SuppressWarnings("rawtypes")
	public List<EbpAppFile> queryAppUpgradeFile(Map map);

	public void createAppUpgradeFile(String appId, String productId);

	public String getbaiduDownUrl(String id);

	public void updateMd5(EbpApp bean);

}
