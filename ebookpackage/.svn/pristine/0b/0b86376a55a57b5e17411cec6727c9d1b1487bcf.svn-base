package com.whty.ebp.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.DerivativeApp;
import com.whty.ebp.manage.model.DerivativeAppApi;

public interface DerivativeAppDao extends IBaseDao<DerivativeApp>{

	List<DerivativeAppApi> queryNewProductAppListByModelCodeAndPlatformCodeMap(Map map);

	List<DerivativeAppApi> getNewProductAppList(Map map);

	List<DerivativeAppApi> getNewProductAppModelCode(Map map);

	List<DerivativeAppApi> getNewProductAppListPlatformCode(Map map);

	/**
	 * @param bean
	 */
	void updateMd5(DerivativeApp bean);

}
