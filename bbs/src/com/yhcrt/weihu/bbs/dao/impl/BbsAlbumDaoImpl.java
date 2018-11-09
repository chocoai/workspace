package com.yhcrt.weihu.bbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsAlbumDao;
import com.yhcrt.weihu.bbs.entity.BbsAlbum;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Page;

@Repository
public class BbsAlbumDaoImpl extends HibernateBaseDao<BbsAlbum,Integer> implements BbsAlbumDao{

	@Override
	public void update(BbsAlbum album){
		getSession().update(album);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BbsAlbum> getListNotImg(Integer userId) {
		String hql = "from BbsAlbum a where a.status=0 and a.creater.id="+userId
				+" and a.imgUrl is null and a.count>0 ";
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsAlbum> getList(Integer userId) {
		String hql = "from BbsAlbum a where a.status=0 and a.creater.id="+userId;
		return getSession().createQuery(hql).list();
	}

	@Override
	public boolean isMyAlbum(Integer userId, Integer albumId) {
		String hqlCount = "select count(*) from BbsAlbum a where a.status=0 and a.creater.id="+userId+" and a.id="+albumId;
		Object obj = getSession().createQuery(hqlCount).uniqueResult();
		if(obj == null){
			return false;
		}else{
			if(((Number)obj).intValue()==0){
				return false;
			}else{
				return true;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BbsAlbum> getAlbumByUserId(Integer pageSize,Integer pageNo,Integer id) {
		Page<BbsAlbum> page = new Page<BbsAlbum>(pageNo,pageSize);
		List<BbsAlbum> list = new ArrayList<BbsAlbum>();
		if(pageSize == null){
			page.setPageSize(8);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String hql = "from BbsAlbum a where a.status=0 and a.creater.id="+id+" order by a.createTime desc";
		String hqlCount = "select count(*) from BbsAlbum a where a.status=0 and a.creater.id="+id;
		Object obj = getSession().createQuery(hqlCount).uniqueResult();
		if(obj == null){
			page.setList(list);
			page.setTotalCount(0);
			return page;
		}else{
			page.setTotalCount(((Number)obj).intValue());
		}
		list = getSession().createQuery(hql).setFirstResult((page.getPageNo()-1)*page.getPageSize())
				.setMaxResults(page.getPageNo()*page.getPageSize()).setCacheable(false).list();
		page.setList(list);
		return page;
	}

	@Override
	public void deleteById(BbsAlbum album) {
		getSession().update(album);
	}

	@Override
	public BbsAlbum save(BbsAlbum album) {
		getSession().save(album);
		return album;
	}

	@Override
	public BbsAlbum findById(Integer id) {
		BbsAlbum album = get(id);
		return album;
	}

	@Override
	protected Class<BbsAlbum> getEntityClass() {
		return BbsAlbum.class;
	}

}
