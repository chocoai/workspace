package com.smart.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smart.dao.BaseDao;
import com.smart.util.Page;

/**
 * BaseDaoImpl
 * 
 * @param <T>
 * @param <ID>
 * @author 充满智慧的威哥
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T, ID extends Serializable>
		implements BaseDao<T, ID> {

	private Class<T> entityClass;

	@Autowired
	private SessionFactory sessionFactory;

	// private HibernateTemplate hibernateTemplate;
	// //hibernate4之后，spring3.1把HibernateDaoSupport去除了
	private JdbcTemplate jdbcTemplate;

	public BaseDaoImpl() {
		Type[] actualTypeArguments = ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments();
		this.entityClass = (Class<T>) actualTypeArguments[0];
	}

	public void reload(T entity) {
		getSession().buildLockRequest(LockOptions.NONE).lock(entity);
		// getSession().lock(entity, LockMode.NONE); //已过时
	}

	public T get(ID id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	public T getUnique(final String hql, final Object... args) {
		List<T> list = getList(1, hql, args);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	
	public Serializable save(T entity) {
		// getSession().saveOrUpdate(entity);
		return getSession().save(entity);
	}

	public void save(final Collection<T> collection) {
		if (collection == null || collection.size() == 0) {
			return;
		}
		for (T t : collection) {
			getSession().save(t);
		}
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void update(final Collection<T> collection) {
		if (collection == null || collection.size() == 0) {
			return;
		}
		for (T t : collection) {
			getSession().update(t);
		}
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void delete(ID id) {
		delete(get(id));
	}

	public void delete(final String hql, final Object... args) {
		Query query = createQuery(hql, args);
		query.executeUpdate();
	}

	public List<T> getList(String hql, Object... args) {
		return getList(0, hql, args);
	}

	public List<T> getList(Integer size, String hql, Object... args) {
		return getList(0, size, hql, args);
	}

	public List<T> getList(Integer fromIndex, Integer size, String hql,
			Object... args) {
		if (fromIndex == null) {
			fromIndex = 0;
		}

		Query query = createQuery(hql, args);
		if (fromIndex > 0) {
			query.setFirstResult(fromIndex);
		}
		if (size > 0) {
			query.setMaxResults(size);
		}
		return query.list();
	}

	public Page<T> getPage(Integer pageNo, Integer pageSize, String hql,
			Object... args) {
		Page<T> pager = new Page<T>(pageNo, pageSize);
		pager.setTotalCount(count(hql, args));
		Query query = createQuery(hql, args);
		query.setFirstResult(pager.getFirstIndex());
		query.setMaxResults(pager.getPageSize());
		List<T> list = query.list();
		pager.setList(list);
		return pager;
	}

	public int count(String hql, Object... args) {
		String fromClause = hql.substring(hql.toLowerCase().indexOf("from"));
		String countHql = "select count(*) " + fromClause;

		// 去掉order by
		int orderIndex = countHql.toLowerCase().indexOf("order");
		if (orderIndex != -1) {
			countHql = countHql.substring(0, orderIndex);
		}
		System.out.println("sql语句：" + countHql);
		Query query = createQuery(countHql, args);

		/** 不分表的情况下只会返回一个结果 **/
		return ((Long) query.uniqueResult()).intValue();

		/** 分表时，因为会从多个库中查询数量，所以结果不唯一 **/
		// List list = query.list();
		// int count = 0;
		// if (list.size() == 0) {
		// return 0;
		// } else if (list.size() == 1) {
		// count = ((Long)list.get(0)).intValue();
		// } else {
		// for (Object o : list) {
		// count += ((Long)o).intValue();
		// }
		// }
		// return count;
	}

	public List<T> getAll() {
		Criteria crit = getSession().createCriteria(getEntityClass());
		return crit.list();
	}

	/**
	 * 组装query查询条件
	 * 
	 * @param queryString
	 * @param args
	 * @return
	 */
	private Query createQuery(String queryString, Object... args) {
		Query query = getSession().createQuery(queryString).setCacheable(true);
		if (args == null || args.length == 0) { // 空参数
			return query;
		} else if (args.length == 1 && args[0] instanceof List) { // 一个参数并且是集合形式
			List<?> list = (List<?>) args[0];
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		} else { // 可变参数形式
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query;
	}

	// ================ getter and setter ========================
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		// this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		// Session session = sessionFactory.openSession();
		return session;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	// public HibernateTemplate getHibernateTemplate() {
	// return hibernateTemplate;
	// }
	//
	// public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	// this.hibernateTemplate = hibernateTemplate;
	// }

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
