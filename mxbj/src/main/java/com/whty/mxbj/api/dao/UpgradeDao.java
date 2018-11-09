package com.whty.mxbj.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whty.mxbj.api.model.Upgrade;
import com.whty.mxbj.base.dao.IBaseDao;

public interface UpgradeDao extends IBaseDao<Upgrade>{

	List<Upgrade> queryUpgrade(Map<String, Object> paramMap);

	Upgrade getUpgrade();

	List<Upgrade> queryPadUpgrade(HashMap<String, Object> hashMap);

	String getpadUpgrade();

}
