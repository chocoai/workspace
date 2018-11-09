package com.yhcrt.weihu.bbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.Constants;
import com.yhcrt.weihu.bbs.dao.BbsPointDetailDao;
import com.yhcrt.weihu.bbs.entity.BbsPointDetail;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Page;

@Repository
public class BbsPointDetailDaoImpl extends HibernateBaseDao<BbsPointDetail,Integer> 
	implements BbsPointDetailDao{

	@Override
	public Integer getPointsAll(Integer userId) {
		String hql = "select sum(p.points) from BbsPointDetail p where p.creater.id="+userId+" and p.points>0";
		Object obj = getSession().createQuery(hql).uniqueResult();
		if(obj == null){
			return 0;
		}
		return ((Number)obj).intValue();
	}

	@Override
	public boolean isTodayComplete(Integer userId, String type) {
		String hql = "select count(*) from BbsPointDetail p where p.creater.id="+userId
				+" and DATE(p.createTime)=CURDATE() and p.type='"+type+"'";
		Object obj = getSession().createQuery(hql).uniqueResult();
		if(obj == null){
			return false;
		}else{
			if(((Number)obj).intValue() == 0){
				return false;
			}
			else{
				return true;
			}
		}
	}

	@Override
	public boolean isUploadImg(Integer userId) {
		String hql = "select count(*) from BbsPointDetail p where p.creater.id="+userId
				+" and p.type='"+Constants.POINT_TYPE_IMG+"'";
		Object obj = getSession().createQuery(hql).uniqueResult();
		if(obj == null){
			return false;
		}else{
			if(((Number)obj).intValue() == 0){
				return false;
			}
			else{
				return true;
			}
		}
	}

	@Override
	public boolean isSign(Integer userId) {
		String hql = "select count(*) from BbsPointDetail p where p.creater.id="+userId
				+" and DATE(p.createTime)=CURDATE() and p.type='"+Constants.POINT_TYPE_SIGN+"'";
		Object obj = getSession().createQuery(hql).uniqueResult();
		if(obj == null){
			return false;
		}else{
			if(((Number)obj).intValue() == 0){
				return false;
			}
			else{
				return true;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BbsPointDetail> getPage(Integer userId, Integer pageSize, Integer pageNo) {
		Page<BbsPointDetail> page = new Page<BbsPointDetail>(pageNo,pageSize);
		List<BbsPointDetail> list = new ArrayList<BbsPointDetail>();
		if(pageSize == null){
			page.setPageSize(10);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String hql = "from BbsPointDetail p where p.creater.id="+userId+" order by p.createTime desc";
		String hqlCount = "select count(*) from BbsPointDetail p where p.creater.id="+userId;
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
	public void save(BbsPointDetail bean) {
		getSession().save(bean);
	}
	
	@Override
	protected Class<BbsPointDetail> getEntityClass() {
		return BbsPointDetail.class;
	}

}
