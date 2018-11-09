package com.whty.ebp.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.Browser;
import com.whty.ebp.manage.model.WhiteList;

public interface BrowserDao extends IBaseDao<Browser>{

	List<Browser> queryWhiteListByModelCode(Map<String, Object> map);
	List<Browser> queryWhiteListNotBy();
	Browser queryWhiteVersionByModelCode();
	
	List<Browser> whiteListByCondition(Map<String, Object> paramMap);
	
	List<Browser> queryLabelByModelCode(Map<String, Object> map);
	List<Browser> queryLabelNotBy();
	Browser queryLabelVersionByModelCode();
	
	List<Browser> labelByCondition(Map<String, Object> paramMap);
	
	void updateWhiteVersion(Map<String, Object> aa);
	void updateLabelVersion(Map<String, Object> aa);
	void saveBrowserFlatModel(Map<String, Object> aa);
	Browser queryById(String id);
	
	void saveWhite(Browser browser);
	void updateWhite(Browser browser);
	int queryCountById(String id);
	void deleteBrowserById(String id);
	void deleteByMap(Map<String, String> map);
	void deleteBrowserWhiteById(String id);
	void deleteBrowserLabelById(String id);
	
	void saveLabel(Browser browser);
}
