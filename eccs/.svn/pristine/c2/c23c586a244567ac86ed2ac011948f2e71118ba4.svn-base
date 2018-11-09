package com.smart.dao.impl;

import org.springframework.stereotype.Repository;
import com.smart.dao.impl.BaseDaoImpl;
import com.smart.model.BidBondAssess;
import com.smart.dao.BidBondAssessDao;

/**
 * BidBondAssessDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class BidBondAssessDaoImpl extends BaseDaoImpl<BidBondAssess, Integer> implements BidBondAssessDao {

	/* (non-Javadoc)
	 * @see com.smart.dao.BidBondAssessDao#getBondLast()
	 */
	@Override
	public String getBondLast() {
		String sql = "SELECT apply_no FROM bid_bond_assess WHERE apply_no IS  NOT NULL ORDER BY cid DESC LIMIT 1";
		Object obj = getSession().createSQLQuery(sql).uniqueResult();
		if(obj != null){
			return obj.toString();
		}else{
			return "00000000";
		}
	}

}

