package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.api.respvo.CheckNewSilentSoft;
import com.whty.ebp.api.respvo.CheckNewSoft;
import com.whty.ebp.api.respvo.ClientFile;
import com.whty.ebp.manage.model.Soft;
import com.whty.ebp.manage.model.SoftFile;
import com.whty.page.util.HandlerResult;
/**
 * 客户端版本信息Service
 * @author zhangguozhu
 */
public interface SoftService {
	
	
	@SuppressWarnings("rawtypes")
	public Soft queryNew(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public List<Soft> querySoft(Map paramMap);
	
	public HandlerResult querySoftPage(Map<String, Object> paramMap);
	
	public void saveSoft(Soft soft);
	
	public boolean unzipSoft(String softId);
	
	public void updateSoft(Soft soft);
	
	public void saveSoftFile(List<SoftFile> list);
	
	public void createSoftUpgradeFile(String softId);
	
	@SuppressWarnings("rawtypes")
	public List<SoftFile> querySoftFile(Map map);
	
	@SuppressWarnings("rawtypes")
	public List<SoftFile> querySoftUpgradeFile(Map map);
	
	@SuppressWarnings("rawtypes")
	public void deleteSoft(List list);
	
//	@SuppressWarnings("rawtypes")
//	public HandlerResult querySetUserUpgrade(Map map);
	
	@SuppressWarnings("rawtypes")
	public void setUserUpgrade(String softId,List allIdList,List idList);
	
	@SuppressWarnings("rawtypes")
	public boolean canUpgrade(Map paramap);

	public void openDownload(Soft soft);

	public Soft getSoft(String id);

	public void updateSoftUpgradeFileOldPath(String softId);

	public void updateSoftUpgradeFileTmpfs(String softId);

	@SuppressWarnings("rawtypes")
	public Soft querySoftByMap(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public HandlerResult queryUpgradeSoft(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Soft queryUpgradeSoftDetail(Map paramMap);
	
	public void createUpgradeSoft(Soft soft,List<String> upgradeSoftIdList);
	
	public void editUpgradeSoft(Soft soft,List<String> upgradeSoftIdList);
	
	public void deleteUpgradeSoft(String softId);
	
	@SuppressWarnings("rawtypes")
	public List<Soft> queryVersionCode(Map paramap);
	
	@SuppressWarnings("rawtypes")
	public List<Soft> queryUpgradeVersionCode(Map paramMap);

	//根据用户id，所属平台编码，用户版本号查询新版本列表
	@SuppressWarnings("rawtypes")
	public List<CheckNewSoft> newSoftList(Map map);
	
	@SuppressWarnings("rawtypes")
	public List<Map> newSoftList_1(Map map);

	//根据用户版本号,参数tag查询新版本列表
	@SuppressWarnings("rawtypes")
	public List<CheckNewSilentSoft> newSilentSoftList(Map map);
	
	@SuppressWarnings("rawtypes")
	public List<Map> newSilentSoftList_1(Map map);

	//根据用户版本号,最新版本id查询新版本文件列表
	@SuppressWarnings("rawtypes")
	public List<ClientFile> clientFileList(Map map);
	
	public void setAllUserUpgrade(Soft soft);

	//根据用户id，用户版本号查询最新版本列表
	@SuppressWarnings("rawtypes")
	public List<Soft> queryNewSoftList(Map paramMap);
	
	public void createUpgradePackage(String softId) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public Map queryUpgradePackage(Map param);

	@SuppressWarnings("rawtypes")
	public HandlerResult queryWidgetPage(Map paramMap);

}