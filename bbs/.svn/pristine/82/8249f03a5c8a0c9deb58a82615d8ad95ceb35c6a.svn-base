package com.yhcrt.weihu.bbs.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsWebserviceCallRecordDao;
import com.yhcrt.weihu.bbs.entity.BbsWebserviceCallRecord;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class BbsWebserviceCallRecordDaoImpl extends HibernateBaseDao<BbsWebserviceCallRecord, Integer> implements BbsWebserviceCallRecordDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public BbsWebserviceCallRecord findById(Integer id) {
		BbsWebserviceCallRecord entity = get(id);
		return entity;
	}

	public BbsWebserviceCallRecord save(BbsWebserviceCallRecord bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsWebserviceCallRecord deleteById(Integer id) {
		BbsWebserviceCallRecord entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<BbsWebserviceCallRecord> getEntityClass() {
		return BbsWebserviceCallRecord.class;
	}
}