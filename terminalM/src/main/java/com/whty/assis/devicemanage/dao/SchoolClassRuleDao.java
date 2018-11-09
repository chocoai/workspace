/**
 * 
 */
package com.whty.assis.devicemanage.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.SchoolClassAppWhitelistRule;
import com.whty.assis.basicdata.model.SchoolClassBrowserTagRule;
import com.whty.assis.basicdata.model.SchoolClassBrowserWhitelistRule;
import com.whty.assis.basicdata.model.SchoolClassUrlBlacklistRule;
import com.whty.assis.basicdata.model.SchoolClassUsetimeRule;
import com.whty.assis.devicemanage.model.SchoolClassAppNetBlacklist;
import com.whty.assis.devicemanage.model.SchoolClassRule;

/**
 * @author zhangzheng
 * @date 2018年5月3日
 */
public interface SchoolClassRuleDao extends IBaseDao<SchoolClassRule> {

	/**
	 * @param schoolId
	 * @return
	 */
	List<Map<String, Object>> listGradeTree(Integer schoolId);

	/**
	 * @param schoolClassId
	 * @return
	 */
	SchoolClassRule getBySchoolClassId(Integer schoolClassId);

	/**
	 * @param bean
	 */
	void deleteSchoolClassRule(SchoolClassRule bean);

	/**
	 * @param bean
	 */
	void saveSchoolClassUsetimeRule(SchoolClassUsetimeRule bean);

	/**
	 * @param bean
	 */
	void saveSchoolClassBrowserWhitelistRule(SchoolClassBrowserWhitelistRule bean);

	/**
	 * @param bean
	 */
	void saveSchoolClassAppWhitelistRule(SchoolClassAppWhitelistRule bean);

	/**
	 * @param bean
	 */
	void saveSchoolClassUrlBlacklistRule(SchoolClassUrlBlacklistRule bean);

	/**
	 * @param bean
	 */
	void saveSchoolClassBrowserTagRule(SchoolClassBrowserTagRule bean);
	
	/**
	 * @param param
	 */
	void deleteSchoolClassBrowserWhitelistRuleBySchoolClassAndRuleId(Map<String, Object> param);

	/**
	 * @param param
	 */
	void deleteSchoolClassUrlBlacklistRuleBySchoolClassAndRule(Map<String, Object> param);

	/**
	 * @param param
	 */
	void deleteAppWhitelistRuleBySchoolClassAndRule(Map<String, Object> param);

	/**
	 * @param param
	 */
	void deleteSchoolClassUsetimeRuleBySchoolClassAndRule(Map<String, Object> param);

	/**
	 * @param param
	 */
	void deleteSchoolClassBrowserTagRuleBySchoolClassAndRule(Map<String, Object> param);

	/**
	 * @param bean
	 */
	void saveSchoolClassAppNetBlacklist(SchoolClassAppNetBlacklist bean);

	/**
	 * @param appNetBlacklistParam
	 */
	void deleteSchoolClassAppNetBlacklistBySchoolClassAndRule(Map<String, Object> appNetBlacklistParam);


}
