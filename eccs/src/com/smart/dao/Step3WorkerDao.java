package com.smart.dao;

import com.smart.dao.BaseDao;
import com.smart.model.Step3Worker;

/**
 * Step3WorkerDao. @author Auto Tools by 充满智慧的威哥
 */
public interface Step3WorkerDao extends BaseDao<Step3Worker, Integer> {

	public String getStep5(Integer projectid, String workType, Integer type);
}
