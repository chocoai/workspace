/**
 * 
 */
package com.whty.assis.api.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.api.model.AppTools;
import com.whty.assis.base.dao.IBaseDao;

/**
 * @author zhangzheng
 * @date 2018年8月7日
 */
public interface AppToolsDao extends IBaseDao<AppTools> {

	/**
	 * @param paramMap
	 * @return
	 */
	List<AppTools> listByConditionByCode(Map<String, Object> paramMap);

}