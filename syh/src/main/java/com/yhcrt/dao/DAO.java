package com.yhcrt.dao;
/**
 * 数据库dao接口
 * @author kejunzhong
 * 2017年5月9日
 * 版权所有：武汉炎黄创新服务有限公司
 */
public interface DAO {

	/**
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return 
	 * @throws Exception
	 */
	public Object save(String str, Object obj) throws Exception;

	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object update(String str, Object obj) throws Exception;

	/**
	 * 删除对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object delete(String str, Object obj) throws Exception;

	/**
	 * 查找单个对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForObject(String str, Object obj) throws Exception;

	/**
	 * 查找多个对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForList(String str, Object obj) throws Exception;

	/**
	 * 查找对象封装成Map
	 * @param s
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForMap(String sql, Object obj, String key, String value) throws Exception;

}
