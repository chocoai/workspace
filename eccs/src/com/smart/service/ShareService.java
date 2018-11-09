package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.Share;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.ShareDao;

/**
 * ShareService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ShareService {

	@Autowired
	private ShareDao shareDao;

	public Page<Share> getAll(int pageNo, int pageSize, String name, int userid,
			String no) {
		StringBuilder hql = new StringBuilder(
				"from Share o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(name)) {
			hql.append(" and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		if (!StringUtil.isBlank(no)) {
			hql.append(" and o.project.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (userid != 0) {
			hql.append(" and o.user.id =? ");
			paramList.add(userid);
		}
		hql.append(" order by o.id desc");
		Page<Share> pageBean = shareDao.getPage(pageNo, pageSize,
				hql.toString(), paramList);
		return pageBean;
	}

	public Boolean getUnique(int projectid, int useid) {// 判断共享列表有无重复
		String hql = "from Share o where o.status = 1 and o.project.id = ? and o.user.id = ?";
		Share li = shareDao.getUnique(hql, projectid, useid);
		if (li != null) {// 有重复
			return true;
		}
		return false;
	}

	// ====================== 基本 C R U D 方法 ===========================
	public Share get(int id) {
		return shareDao.get(id);
	}

	public void save(Share entity) {
		shareDao.save(entity);
	}

	public void update(Share entity) {
		shareDao.update(entity);
	}

	public void delete(int id) {
		shareDao.delete(id);
	}

	public List<Share> getAll() {
		return shareDao.getAll();
	}

}
