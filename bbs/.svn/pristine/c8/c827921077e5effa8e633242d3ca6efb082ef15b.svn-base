package com.yhcrt.weihu.bbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsAlbumImgDao;
import com.yhcrt.weihu.bbs.entity.BbsAlbumImg;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Page;

@Repository
public class BbsAlbumImgDaoImpl extends HibernateBaseDao<BbsAlbumImg,Integer> implements BbsAlbumImgDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsAlbumImg> getList(Integer albumId){
		String hql = "from BbsAlbumImg a where a.status=0 and a.album.id="+albumId
				+" order by a.createTime desc ";
		return getSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BbsAlbumImg> getListOne(Integer albumId) {
		String hql = "from BbsAlbumImg a where a.status=0 and a.album.id="+albumId
				+" order by a.createTime desc limit 0,1 ";
		return getSession().createQuery(hql).list();
	}

	@Override
	public void deleteById(BbsAlbumImg albumImg){
		getSession().update(albumImg);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<BbsAlbumImg> getImgByAlbumId(Integer pageSize,Integer pageNo,Integer albumId) {
		Page<BbsAlbumImg> page = new Page<BbsAlbumImg>(pageNo,pageSize);
		List<BbsAlbumImg> list = new ArrayList<BbsAlbumImg>();
		if(pageSize == null){
			page.setPageSize(15);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String hql = "from BbsAlbumImg a where a.status=0 and a.album.id="+albumId+" order by a.createTime desc";
		String hqlCount = "select count(*) from BbsAlbumImg a where a.status=0 and a.album.id="+albumId;
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
	public BbsAlbumImg save(BbsAlbumImg album) {
		getSession().save(album);
		return album;
	}

	@Override
	public BbsAlbumImg findById(Integer id) {
		BbsAlbumImg album = get(id);
		return album;
	}

	@Override
	protected Class<BbsAlbumImg> getEntityClass() {
		return BbsAlbumImg.class;
	}

}
