package com.smart.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import com.smart.util.Page;

/**
 * BaseDao
 * 
 * @param <T>
 * @param <ID>
 * @author 充满智慧的威哥
 */
public interface BaseDao<T, ID extends Serializable> {

	/**
	 * 获得sessionFactory
	 * 
	 * @return
	 */
	public SessionFactory getSessionFactory();

	/**
	 * 获得session
	 * 
	 * @return
	 */
	public Session getSession();

	/**
	 * 获得HibernateTemplate实例
	 * 
	 * @return
	 */
	// hibernate4之后，spring3.1把HibernateDaoSupport去除了
	// public HibernateTemplate getHibernateTemplate();

	/**
	 * 获得JdbcTemplate实例
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();

	/**
	 * 游离状态转换成持久状态
	 * 
	 * @param entity
	 */
	public void reload(T entity);

	/**
	 * 根据id获得实体对象
	 * 
	 * @param id
	 * @return
	 */
	public T get(ID id);

	/**
	 * 查询唯一对象，结果不唯一时无报错提示，返回最近的一条记录
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            参数
	 * @return
	 */
	public T getUnique(final String hql, final Object... values);

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public Serializable save(T entity);

	/**
	 * 保存或修改集合参数中的所有实体对象
	 * 
	 * @param collection
	 */
	public void save(Collection<T> collection);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 修改集合参数中的所有实体对象
	 * 
	 * @param collection
	 */
	public void update(Collection<T> collection);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 根据id删除实体
	 * 
	 * @param id
	 */
	public void delete(ID id);

	/**
	 * 根据条件删除实体
	 * 
	 * @param hql
	 * @param values
	 */
	public void delete(final String hql, final Object... values);

	/**
	 * 列表查询
	 * 
	 * @param size
	 *            需要获取的结果数
	 * @param hql
	 *            hql语句
	 * @param values
	 *            参数值列表
	 * @return
	 */
	public List<T> getList(final Integer size, final String hql,
			final Object... values);

	public List<T> getList(final String hql, final Object... values);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示数
	 * @param hql
	 *            hql语句
	 * @param values
	 *            参数值
	 * @return
	 */
	public Page<T> getPage(final Integer pageNo, final Integer pageSize,
			final String hql, final Object... values);

	/**
	 * 查询所有结果
	 */
	public List<T> getAll();

	/**
	 * count查询
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public int count(final String hql, final Object... values);

}
