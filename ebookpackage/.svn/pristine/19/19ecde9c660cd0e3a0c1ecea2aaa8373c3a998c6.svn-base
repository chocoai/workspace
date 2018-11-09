package com.whty.ebp.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.WhiteList;

public interface WhiteListDao extends IBaseDao<WhiteList> {

	WhiteList queryById(String id);

	void saveWhiteListFlatModel(Map<String, Object> aa);

	void deleteMapByAppWhiteListId(String id);

	void saveAppWhiteListFlatModel(Map<String, Object> aa);

	List<WhiteList> queryWhiteList(Map<String, Object> map);

	List<WhiteList> queryWhiteListByModelCode(Map<String, Object> map);

	List<WhiteList> queryWhiteListByPlatformCode(Map<String, Object> map);

}
