package com.whty.assis.demo.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.whty.assis.api.respvo.CheckNewSilentSoft;
import com.whty.assis.api.respvo.CheckNewSoft;
import com.whty.assis.api.respvo.ClientFile;
import com.whty.assis.api.respvo.SoftAreaUpgrade;
import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.Soft;
import com.whty.assis.demo.model.SoftFile;
import com.whty.assis.demo.model.Ta_user;
import com.whty.assis.demo.model.WidgetLog;

public interface SoftDao extends IBaseDao<Soft> {

	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> getFilePackage(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Soft> queryNewSoft(Map paramMap);

	public void saveSoft(Soft soft);

	public void updateSoft(Soft soft);

	public void updateSoftIsleveup(String isleveup);

	public void saveSoftFileBatch(List<SoftFile> list);

	@SuppressWarnings("rawtypes")
	public List<Soft> querySoft(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<SoftFile> querySoftFile(Map paramMap);

	public List<SoftFile> querySoftUpgradeFile(@SuppressWarnings("rawtypes") Map paramMap);

	@SuppressWarnings("rawtypes")
	public void saveSoftFileUpgradeBatch(List<Map> list);

	@SuppressWarnings("rawtypes")
	public void deleteSoft(List list);

	@SuppressWarnings("rawtypes")
	public void deleteSoftFile(List list);

	@SuppressWarnings("rawtypes")
	public void deleteSoftUpgradeFile(List list);

	// 查询用户指定用户升级
	@SuppressWarnings("rawtypes")
	public List<Ta_user> querySetUserUpgrade(Map map);

	@SuppressWarnings("rawtypes")
	public void deleteSoftUserUpgrade(Map<String, Object> paramMap);

	@SuppressWarnings("rawtypes")
	public void setUserUpgrade(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map> querySoftUserUpgrade(Map paramMap);

	public void updateNoDownloadExe(Soft soft);

	public void updateSoftUpgradeFileOldPath(String id);

	public void updateSoftUpgradeFileTmpfs(String id);

	@SuppressWarnings("rawtypes")
	public Soft querySoftByMap(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Soft> queryUpgradeSoft(Map paramMap);

	@SuppressWarnings("rawtypes")
	public void insertUpgradeSoftRel(Map paramMap);

	@SuppressWarnings("rawtypes")
	public void deleteUpgradeSoftRel(Map paramMap);

	public List<String> queryUpgradeSoftId(String softId);

	@SuppressWarnings("rawtypes")
	public void deleteSoftUpgrade(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Soft> queryVersionCode(Map paramMap);

	// 根据用户id，所属平台编码，用户版本号查询新版本
	@SuppressWarnings("rawtypes")
	public List<CheckNewSoft> newSoftList(Map map);

	@SuppressWarnings("rawtypes")
	public List<Map> newSoftList_1(Map map);

	// 根据用户版本号,参数tag查询新版本列表
	@SuppressWarnings("rawtypes")
	public List<CheckNewSilentSoft> newSilentSoftList(Map map);

	@SuppressWarnings("rawtypes")
	public List<Map> newSilentSoftList_1(Map map);

	// 根据用户版本号,最新版本id查询新版本文件列表
	@SuppressWarnings("rawtypes")
	public List<ClientFile> clientFileList(Map map);

	@SuppressWarnings("rawtypes")
	public void saveSoftFilePackage(Map map);

	@SuppressWarnings("rawtypes")
	public void deleteVersionRelConflict(Map map);

	@SuppressWarnings("rawtypes")
	public void deleteSoftUpgradeConflict(Map map);

	// 根据用户id，用户版本号查询最新版本列表
	@SuppressWarnings("rawtypes")
	public List<Soft> queryNewSoftList(Map paramMap);

	@SuppressWarnings("rawtypes")
	public List<Map> queryUpgradeFileList(String softId);

	@SuppressWarnings("rawtypes")
	public void updateUpgradePackage(List list);

	@SuppressWarnings("rawtypes")
	public void updateUpgradePackageNull(List list);

	@SuppressWarnings("rawtypes")
	public List<String> queryUpgradeSoftRel(Map param);

	@SuppressWarnings("rawtypes")
	public List<String> queryVersionRelConflict(Map param);

	@SuppressWarnings("rawtypes")
	public Map queryUpgradePackage(Map param);

	@SuppressWarnings("rawtypes")
	public List<Map> queryWidgetPage(Map param);

	@SuppressWarnings("rawtypes")
	public List<SoftAreaUpgrade> queryAreaSoft(Map<String, Object> paramMap);

	public void saveSoftAreaUpgrade(Map<String, Object> softAreaMap);

	public void deleteSoftAreaUpgrade(Map<String, Object> param);

	public List<Map<String, Object>> loadFilePackage(Map<String, Object> map);

	/**
	 * @param soft
	 */
	public void updateBaiduBosStatus(Soft soft);

	/**
	 * @param param
	 */
	public void updateSoftFile(SoftFile softFile);

	public List<String> queryVersions(String str);

	public void addClientVersion(Map<String, Object> param);
}
