/**
 * 
 */
package com.whty.assis.basicdata.dao;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.SchoolUserClass;

/**
 * @author zhangzheng
 * @date   2018年5月21日
 */
public interface SchoolUserClassDao extends IBaseDao<SchoolUserClass>{

	/**
	 * @param id
	 */
	void deleteBySchoolClassId(int id);

}
