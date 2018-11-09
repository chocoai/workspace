package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.DeptUserDao;
import com.smart.dao.UserDao;
import com.smart.model.Dept;
import com.smart.model.User;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * UserService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private DeptUserDao deptUserDao;

	@Autowired
	private DeptService deptService;
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 用户名查询
	 * 
	 * @param username
	 * @return
	 */
	public User get(String username) {
		String hql = "from User o where o.status = 1 and o.username = ?";
		return userDao.getUnique(hql, username);
	}

	/**
	 * 用户名+密码查询
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User checkLoginByUserName(String username, String password) {
		String hql = "from User o where o.status = 1 and o.username = ? and o.password = ?";
		return userDao.getUnique(hql, username, password);
	}

	/**
	 * 通过用户中文名称和密码验证登录
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public User checkLoginByName(String name, String password) {
		String hql = "from User o where o.status = 1 and o.name = ? and o.password = ?";
		return userDao.getUnique(hql, name, password);
	}

	public Page<User> getPage(int pageNo, int pageSize, Dept company,
			String name) {
		Dept parentDept = deptService.get(company.getPid());
		StringBuilder hql = new StringBuilder(
				"from User o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (parentDept != null) {
			hql.append(" and o.comid = ? ");
			paramList.add(company.getComid());
		} else {
			hql.append(" and o.comid != ? ");
			paramList.add(company.getComid());
		}
		if (StringUtils.isNotBlank(name)) {
			hql.append(" and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		hql.append(" order by o.ctime desc");
		Page<User> pageBean = userDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
		for (User user : pageBean.getList()) {
			user.setDeptList(deptUserDao.getDeptByUser(user.getId()));
		}
		return pageBean;
	}

	/**
	 * 根据创建用户的id获取他可以选择的所有用户
	 * 
	 * @param deptId
	 * @return
	 */
	public List<User> getByDept(String deptId) {
		String hql = "from User o where o.status = 1 and o.dept.id like ?";
		List<User> list = userDao.getList(hql, deptId + "%");
		for (User user : list) {
			user.setDeptList(deptUserDao.getDeptByUser(user.getId()));
		}
		return list;
	}
	
	/**
	 * 根据lastId更改所有用户的lastId为空
	 */
	public void updateByLastId(Integer lastRoleId) {
		String hql = "update User o set o.lastRoleId = ?  where o.lastRoleId = ?";
		Query query  = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,null);
		query.setInteger(1,lastRoleId);
		query.executeUpdate();
	}

	public Page<User> getAll1(int pageNo, int pageSize, String name,
			String comid) {
		StringBuilder hql = new StringBuilder(
				"from User o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(name)) {
			hql.append(" and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		if (!comid.equals("001")) {
			hql.append(" and o.comid =? ");
			paramList.add(comid);
		}
		hql.append(" order by convert(o.name , 'gbk')");
		Page<User> pageBean = userDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
		return pageBean;
	}

	public Page<User> getAll2(int pageNo, int pageSize, String name) {
		StringBuilder hql = new StringBuilder(
				"from User o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(name)) {
			hql.append(" and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		hql.append(" order by o.id desc");
		Page<User> pageBean = userDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
		return pageBean;
	}

	/**
	 * 根据用户Id查询用户名
	 * 
	 * @param userIds
	 * @return
	 */
	public String getUserNamesByUserIds(String userIds) {
		if (StringUtils.isBlank(userIds))
			return "";
		String[] userId = userIds.split(",");
		String userNames = "";
		for (int i = 0; i < userId.length; i++) {
			if (StringUtils.isNumeric(userId[i])) {
				User user = get(Integer.valueOf(userId[i]));
				userNames += user.getName() + ",";
			}
		}
		userNames = StringUtils.removeEnd(userNames, ",");
		return userNames;
	}

	public User get(int id) {
		return userDao.get(id);
	}

	public int save(User entity) {
		return (Integer) userDao.save(entity);
	}

	public void update(User entity) {
		userDao.update(entity);
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public List<User> getAll() {
		return userDao.getAll();
	}

}
