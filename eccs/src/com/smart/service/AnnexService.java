package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.Annex;
import com.smart.util.AppUtil;
import com.smart.util.StringUtil;
import com.smart.dao.AnnexDao;

/**
 * AnnexService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class AnnexService {

	@Autowired
	private AnnexDao annexDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Annex get(int id) {
		return annexDao.get(id);
	}

	public void save(Annex entity) {
		annexDao.save(entity);
	}

	public void update(Annex entity) {
		annexDao.update(entity);
	}

	public void delete(int id) {
		annexDao.delete(id);
	}

	public List<Annex> getAll() {
		return annexDao.getAll();
	}

	public List<Annex> getByProjectIdForDGBZ(Integer projectid,
			String datestr) { // 底稿编制查询附件不同
		// 是按时间查询,删除状态不作过滤
		StringBuilder hql = new StringBuilder("from Annex o where 1 = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (projectid == null) {
			return new ArrayList<Annex>();
		} else {
			hql.append("and o.project.id = ?");
			paramList.add(projectid);
		}
		if (!StringUtil.isBlank(datestr)) {
			hql.append("and date_format(o.ctime,'%Y%m%d%H%i') = ?");
			paramList.add(datestr);
		}
		hql.append("and o.stepName = ?");
		paramList.add(AppUtil.DGBZ + "");
		hql.append("order by o.id desc");
		return annexDao.getList(hql.toString(), paramList);
	}
	
	public List<Annex> getAnnexs(Integer projectid) { //根据项目id和步骤编号查询
		StringBuilder hql = new StringBuilder("from Annex o where 1 = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (projectid == null) {
			return new ArrayList<Annex>();
		} else {
			hql.append("and o.project.id = ?");
			paramList.add(projectid);
		}
		hql.append("and o.stepName = ?");
		paramList.add(AppUtil.DGBZ + "");
		hql.append("order by o.id desc");
		return annexDao.getList(hql.toString(), paramList);
	}

	public List<Annex> getByProjectId(Integer projectid, String step) {
		StringBuilder hql = new StringBuilder(
				"from Annex o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (projectid == null) {
			return new ArrayList<Annex>();
		} else {
			hql.append("and o.project.id = ?");
			paramList.add(projectid);
		}
		if (!StringUtil.isBlank(step)) {
			hql.append("and o.stepName = ?");
			paramList.add(step);
		}
		hql.append("order by o.id desc");
		return annexDao.getList(hql.toString(), paramList);
	}

	public void backupDGBZ(Integer projectid, Integer step) { // 将底稿编制的附件保存为历史
		StringBuilder hql = new StringBuilder("from Annex o where 1 = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (projectid == null) {
			return;
		} else {
			hql.append("and o.project.id = ?");
			paramList.add(projectid);
		}
		hql.append("and o.stepName = ?");
		paramList.add(step + "");
		List<Annex> annexList = annexDao.getList(hql.toString(), paramList);
		List<Annex> annexlist = new ArrayList<Annex>();
		for (Annex annex : annexList) {
			annex.setStatus(0);
			annexlist.add(annex);
		}
		annexDao.update(annexlist);
	}

	@SuppressWarnings("unchecked")
	public List<String> getDateForDGBZ(Integer projectid, Integer step) {
		StringBuilder hql = new StringBuilder(
				"select date_format(o.ctime,'%Y%m%d%H%i') from Annex o where 1 = 1 ");
		// List<Object> paramList = new ArrayList<Object>();
		if (projectid == null) {
			return new ArrayList<String>();
		} else {
			hql.append("and o.project.id = '" + projectid + "' ");
		}
		hql.append("and o.stepName = '" + step + "' ");
		hql.append(" group by date_format(o.ctime,'%Y%m%d%H%i') ");
		hql.append("order by o.id desc");

		Session session = annexDao.getSession();
		Query query = session.createQuery(hql.toString());
		return query.list();
	}

	public boolean saveAllFile(Integer projectid, String step) {
		StringBuilder hql = new StringBuilder(
				"form Annex o where o.filedTime is  null ");
		List<Object> paramList = new ArrayList<Object>();
		if (projectid == null) {
			return false;
		} else {
			hql.append("and o.project.id = ?");
			paramList.add(projectid);
		}
		if (!StringUtil.isBlank(step)) {
			hql.append("and o.stepName = ?");
			paramList.add(step);
		}
		return true;
	}
}