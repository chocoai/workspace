/**
 * 
 */
package com.smart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.model.ProceStepDef;

/**
 * @Description:
 * @author raopanfeng
 * @date 2017年1月20日 下午4:36:12
 */
@Repository
public class ProceStepDefDaoImpl extends BaseDaoImpl<ProceStepDef, Integer>
		implements com.smart.dao.ProceStepDefDao {

	@Override
	public ProceStepDef getStepByStepCode(String stepCode) {
		StringBuilder hql = new StringBuilder(
				"from ProceStepDef bean where bean.stepCode = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(stepCode);
		ProceStepDef step = this.getUnique(hql.toString(), paramList);
		return step;
	}

	@Override
	public List<ProceStepDef> getProjectByStep(String type) {
		StringBuilder hqlBuilder = new StringBuilder(
				"from ProceStepDef bean where 1=1");
		hqlBuilder.append(" and bean.businessType = ").append(type);
		hqlBuilder.append(" order by bean.sort");
		return this.getList(hqlBuilder.toString());
	}

}
