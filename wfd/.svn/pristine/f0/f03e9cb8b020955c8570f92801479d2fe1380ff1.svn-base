package com.whty.wfd.base.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {

	/**
	 * 保存数据
	 * 
	 * @param t
	 * @return
	 */
	public int save(T t);

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(String id);

	/**
	 * 修改数据
	 * 
	 * @param t
	 * @return
	 */
	public int update(T t);

	/**
	 * 根据条件更新
	 * 
	 * @param paramMap
	 * @return
	 */
	public int updateByCondition(Map<String, Object> paramMap);

	/**
	 * 根据主键查询 字符串主键
	 * 
	 * @param Id
	 * @return
	 */
	public T loadByStrId(String Id);

	/**
	 * 根据主键查询 数字主键
	 * 
	 * @param Id
	 * @return
	 */
	public T loadByIntId(int Id);

	/**
	 * 根据条件查询
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<T> listByCondition(Map<String, Object> paramMap);
}
