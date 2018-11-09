package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.ServiceContent;
import com.smart.dao.ServiceContentDao;

/**
 * ServiceContentService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ServiceContentService {

	@Autowired
	private ServiceContentDao serviceContentDao;

	// ====================== 基本 C R U D 方法 ===========================
	public ServiceContent get(int id) {
		return serviceContentDao.get(id);
	}

	public void save(ServiceContent entity) {
		serviceContentDao.save(entity);
	}

	public void update(ServiceContent entity) {
		serviceContentDao.update(entity);
	}

	public void delete(int id) {
		serviceContentDao.delete(id);
	}

	public List<ServiceContent> getAll() {
		return serviceContentDao.getAll();
	}

}
