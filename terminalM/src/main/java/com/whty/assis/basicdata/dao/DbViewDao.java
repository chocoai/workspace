/**
 * 
 */
package com.whty.assis.basicdata.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.basicdata.model.DbView;

/**
 * 获取视图
 * 
 * @author zhangzheng
 * @date 2018年3月29日
 */
public interface DbViewDao {

	public List<DbView> listSchoolType();
	
	public List<Map<String, Object>> listScreenType();

	public List<Map<String, Object>> listTerminalUserType();

	public List<Map<String, Object>> listTouchType();

	public List<Map<String, Object>> listLocationAreaAttribute();

	public List<DbView> listUseTimeType();
	
	/**
	 * @return
	 */
	public List<DbView> listTerminalDeviceTypeTree();

	/**
	 * 订单类型
	 * @return
	 */
	public List<DbView> listOrderType();
	
}
