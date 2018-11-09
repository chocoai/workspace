/**
 * 
 */
package com.whty.assis.devicemanage.dao;

import java.util.List;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.devicemanage.model.BrowserWhitelist;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
public interface BrowserWhitelistDao extends IBaseDao<BrowserWhitelist> {

	/**
	 * @param id
	 */
	void deleteByRuleId(Integer id);

	/**
	 * @param schoolId
	 * @return
	 */
	List<BrowserWhitelist> getwhiteListBySchoolClassId(Integer schoolId);

}
