package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.DerivativeApp;
import com.whty.ebp.manage.model.DerivativeAppApi;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface DerivativeAppService {

	HandlerResult listDerivativeAppByPage(Map<String, Object> paraMap, PageContext page);

	void save(DerivativeApp derivativeApp) throws Exception;

	void openUpdate(Map map);

	public void delete(String id);

	DerivativeApp queryById(String id);
	
	public void update(DerivativeApp bean) throws Exception;

	List<DerivativeAppApi> getAPPLineList(Map map);
	
	List<EbpApp> getAPPLineList2(Map map);
}
