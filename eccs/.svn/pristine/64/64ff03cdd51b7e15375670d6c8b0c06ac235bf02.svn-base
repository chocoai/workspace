package com.smart.dao.impl;

import org.springframework.stereotype.Repository;

import com.smart.dao.ProjectTransferRecordDao;
import com.smart.model.ProjectTransferRecord;

/**
 * ProjectTransferRecordDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class ProjectTransferRecordDaoImpl extends BaseDaoImpl<ProjectTransferRecord, Integer> implements ProjectTransferRecordDao {

	/* (non-Javadoc)
	 * @see com.smart.dao.ProjectTransferRecordDao#getTransferLast()
	 */
	@Override
	public String getTransferLast() {
		String sql = "SELECT transfer_no FROM project_transfer_record WHERE transfer_no IS  NOT NULL ORDER BY cid DESC LIMIT 1";
		Object obj = getSession().createSQLQuery(sql).uniqueResult();
		if(obj != null){
			return obj.toString();
		}else{
			return "00000000";
		}
	}

}

