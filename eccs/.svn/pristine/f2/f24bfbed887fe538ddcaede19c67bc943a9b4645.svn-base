package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.T_Contact;
import com.smart.util.Page;
import com.smart.dao.T_ContactDao;

/**
 * T_ContactService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_ContactService {

	@Autowired
	private T_ContactDao t_ContactDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_Contact get(int id) {
		return t_ContactDao.get(id);
	}

	public void save(T_Contact entity) {
		t_ContactDao.save(entity);
	}

	public void update(T_Contact entity) {
		t_ContactDao.update(entity);
	}

	public void delete(int id) {
		t_ContactDao.delete(id);
	}

	public List<T_Contact> getAll() {
		return t_ContactDao.getAll();
	}

	public Page<T_Contact> getPage(int pageNo, int pageSize,
			Integer customerId) {
		StringBuilder hql = new StringBuilder(
				"from T_Contact c where c.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (customerId != null) {
			hql.append("and c.t_Customer.id = ?");
			paramList.add(customerId);
		}
		hql.append("order by c.id desc");
		return t_ContactDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	/**
	 * 通过客户Id获取客户单位的默认联系人
	 * 
	 * @param customerId
	 *            客户Id
	 * @return
	 */
	public T_Contact getDefaultContactByCustomerId(Integer customerId) {
		StringBuilder hql = new StringBuilder(
				"from T_Contact c where c.status = 1 and c.defContact = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (null != customerId) {
			hql.append("and c.t_Customer.id = ?");
			paramList.add(customerId);
		}
		return t_ContactDao.getUnique(hql.toString(), paramList);
	}

	public List<T_Contact> getList() {
		String hql = "from T_Contact c where  c.status = 1";
		return t_ContactDao.getList(hql);
	}

	public List<T_Contact> getLists(Integer customer) {
		String hql = "from T_Contact c where  c.status = 1 and c.t_Customer.id="
				+ customer + "";
		return t_ContactDao.getList(hql);
	}
}
