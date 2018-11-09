package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.PersonFenpei;
import com.smart.model.ProjectProcessState;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.PersonFenpeiDao;
import com.smart.dao.ProjectProcessStateDao;

/**
 * AccountService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class PersonFenpeiService {

	@Autowired
	private PersonFenpeiDao personfenpeiDao;

	@Autowired
	private ProjectProcessStateDao projectProcessStateDao;

	public PersonFenpei get(int id) {
		return personfenpeiDao.get(id);
	}

	public void save(PersonFenpei entity) {
		personfenpeiDao.save(entity);
	}

	public void update(PersonFenpei entity) {
		personfenpeiDao.update(entity);
	}

	public void delete(int id) {
		personfenpeiDao.delete(id);
	}

	public List<PersonFenpei> getAll() {
		return personfenpeiDao.getAll();
	}

	public PersonFenpei getOneByProjectId(int projectid) {
		String hql = "from PersonFenpei o where o.project.id = ? ";
		return personfenpeiDao.getUnique(hql, projectid);
	}

	public Page<ProjectProcessState> getPage(int pageNo, int pageSize,
			String projectNo, String projectName) {
		StringBuilder hql = new StringBuilder(
				"from ProjectProcessState b where ");
		hql.append(
				"b.project.id in (select o.project.id from PersonFenpei o ) ");
		hql.append(" and b.type = '0' ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(projectNo)) {
			hql.append(" and b.project.no like ? ");
			paramList.add("%" + projectNo + "%");
		}
		if (!StringUtil.isBlank(projectName)) {
			hql.append(" and b.project.name like ? ");
			paramList.add("%" + projectName + "%");
		}
		hql.append("order by b.id desc");
		return projectProcessStateDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}
}
