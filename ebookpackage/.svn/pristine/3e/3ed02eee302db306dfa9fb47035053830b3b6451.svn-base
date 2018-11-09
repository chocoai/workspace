package com.whty.ebp.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.FlatModel;

public interface FlatModelDao extends IBaseDao<FlatModel>{

	void saveAppFlatModel(Map<String, Object> param);

	List<Map<String,Object>> queryFlatModelByAppId(Map<String, Object> param);

	void deleteByEbpAppId(String id);

	void deleteEbpAppFlatModelByFlatModelId(String id);

	void deleteAppWhiteListFlatModeId(String id);

	void saveDerivativeAppFlatModel(Map<String, Object> aa);

	void deleteByDerivativeEbpAppId(String id);

}
