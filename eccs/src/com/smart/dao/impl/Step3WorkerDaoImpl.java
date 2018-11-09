package com.smart.dao.impl;

import org.springframework.stereotype.Repository;
import com.smart.dao.impl.BaseDaoImpl;
import com.smart.dao.Step3WorkerDao;
import com.smart.model.Step3Worker;
import com.smart.util.StringUtil;

/**
 * Step3WorkerDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class Step3WorkerDaoImpl extends BaseDaoImpl<Step3Worker, Integer>
		implements Step3WorkerDao {

	@Override
	public String getStep5(Integer projectid, String workType, Integer type) {
		StringBuffer sqlBuff = new StringBuffer(
				"SELECT GROUP_CONCAT(y.work_user_id) FROM `step3_worker` y WHERE 1=1");
		if (projectid != null) {
			sqlBuff.append(" and y.project_id=").append(projectid);
		}
		if (!StringUtil.isBlank(workType)) {
			sqlBuff.append(" and y.work_type=").append(workType);
		}
		if (type != null) {
			sqlBuff.append(" and y.type<=").append(type);
		}
		return (String) getSession().createSQLQuery(sqlBuff.toString())
				.uniqueResult();
	}

}
