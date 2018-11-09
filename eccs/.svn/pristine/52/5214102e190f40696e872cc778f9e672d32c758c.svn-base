package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.EditorialType;
import com.smart.dao.EditorialTypeDao;

/**
 * EditorialTypeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class EditorialTypeService {

	@Autowired
	private EditorialTypeDao editorialTypeDao;

	// ====================== 基本 C R U D 方法 ===========================
	public EditorialType get(int id) {
		return editorialTypeDao.get(id);
	}

	public List<EditorialType> getPid(int id) {
		String hql = "from EditorialType o where o.status = 1 and o.pid = ? order by o.id asc";
		return editorialTypeDao.getList(hql, id);
	}

	public void save(EditorialType entity) {
		editorialTypeDao.save(entity);
	}

	public void update(EditorialType entity) {
		editorialTypeDao.update(entity);
	}

	public void delete(int id) {
		editorialTypeDao.delete(id);
	}

	public List<EditorialType> getAll() {
		return editorialTypeDao.getAll();
	}

}
