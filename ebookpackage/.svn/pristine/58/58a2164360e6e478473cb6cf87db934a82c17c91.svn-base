package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.Browser;
import com.whty.ebp.manage.model.WhiteList;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface BrowserService {

	List<Browser> queryWhiteList(Map<String, Object> map);
	String queryWhiteVersion();
	
	HandlerResult queryWhiteListPage(Map<String, Object> paraMap, PageContext page);
	
	void saveWhite(Browser browser);
	void deleteWhite(Map<String, String> map);
	void updateWhite(Browser browser);
	
	void saveLabel(Browser browser);
	void deleteLabel(Map<String, String> map);
	void updateLabel(Browser browser);
	
	
	List<Browser> queryLabel(Map<String, Object> map);
	String queryLabelVersion();
	
	HandlerResult queryLabelPage(Map<String, Object> paraMap, PageContext page);
	Object queryById(String id);
}
