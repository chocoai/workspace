/**
 * 
 */
package com.whty.assis.devicemanage.dao;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.devicemanage.model.BrowserTag;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
public interface BrowserTagDao extends IBaseDao<BrowserTag> {

	/**
	 * @param id
	 */
	void deleteByRuleId(Integer id);

}
