package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.ServiceType;
import com.smart.dao.ServiceTypeDao;

/**
 * ServiceTypeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ServiceTypeService {

	@Autowired
	private ServiceTypeDao serviceTypeDao;

	public ServiceType get(int id) {
		return serviceTypeDao.get(id);
	}

	public void save(ServiceType entity) {
		serviceTypeDao.save(entity);
	}

	public void update(ServiceType entity) {
		serviceTypeDao.update(entity);
	}

	public void delete(int id) {
		serviceTypeDao.delete(id);
	}

	public List<ServiceType> getAll() {
		StringBuilder hql = new StringBuilder(
				" from ServiceType o order by o.sort ");
		List<Object> paramList = new ArrayList<Object>();
		List<ServiceType> list = serviceTypeDao.getList(hql.toString(),
				paramList);
		return list;
	}

}
