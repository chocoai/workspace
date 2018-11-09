/**
 * 
 */
package com.whty.assis.basicdata.dao;

import java.util.Map;

/**
 * @author zhangzheng
 * @date 2018年3月29日
 */
public interface IdDao {

	/**
	 * 获取指定表的自增主键
	 * 
	 * @param dataBaseName
	 * @param tableName
	 * @return
	 */

	public int getId(Map<String, Object> param);

}
