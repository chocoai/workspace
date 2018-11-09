/**
 * 
 */
package com.whty.assis.devicemanage.dao;

import java.util.List;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.devicemanage.model.BrowserWhitelistRule;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
public interface BrowserWhitelistRuleDao extends IBaseDao<BrowserWhitelistRule> {

	/**
	 * @param schoolClassId
	 * @return
	 */
	List<BrowserWhitelistRule> listBySchoolClassRule(Integer schoolClassId);

}
