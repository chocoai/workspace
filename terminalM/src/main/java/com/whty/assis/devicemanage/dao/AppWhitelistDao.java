/**
 * 
 */
package com.whty.assis.devicemanage.dao;

import java.util.List;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.devicemanage.model.AppWhitelist;
import com.whty.assis.devicemanage.model.AppWhitelistRule;

/**
 * @author zhangzheng
 * @date   2018年5月9日
 */
public interface AppWhitelistDao extends IBaseDao<AppWhitelist>{

	/**
	 * @param id
	 */
	void deleteByRuleId(Integer id);


}
