package com.whty.assis.base.dao;

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

	public int deleteById(Integer id);

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
	 * 根据主键查询
	 * 
	 * @param Id
	 * @return
	 */
	public T loadById(String Id);

	public T loadById(Integer Id);

	/**
	 * 根据条件查询
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<T> listByCondition(Map<String, Object> paramMap);
}
