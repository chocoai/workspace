package com.smart.dao.impl;

import org.springframework.stereotype.Repository;

import com.smart.dao.impl.BaseDaoImpl;
import com.smart.dao.ContractReviewDao;
import com.smart.model.ContractReview;

/**
 * ContractReviewDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class ContractReviewDaoImpl extends BaseDaoImpl<ContractReview, Integer>
		implements ContractReviewDao {

	public ContractReview getByContractId(int contractId) {
		return this.getUnique("from ContractReview cr  where cr.contract.id=?",
				contractId);
	}
	
	//获取最后一条合同编号
		public String getContractLast() {
			String sql = "SELECT no FROM contract WHERE NO IS  NOT NULL  ORDER BY id DESC LIMIT 1";
			String no = getSession().createSQLQuery(sql).
					uniqueResult().toString();
			System.out.println();
			return no;
		}

}
