/**
 * 
 */
package com.whty.assis.devicemanage.dao;

import java.util.List;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.devicemanage.model.BrowserTagRule;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
public interface BrowserTagRuleDao extends IBaseDao<BrowserTagRule> {

	/**
	 * @param paramMap
	 * @return
	 */
	List<BrowserTagRule> listBySchoolClassRule(Integer schoolClassId);

}
