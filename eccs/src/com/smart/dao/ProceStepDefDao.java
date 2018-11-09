/**
 * 
 */
package com.smart.dao;

import java.util.List;

import com.smart.model.ProceStepDef;

/**
 * @Description:
 * @author raopanfeng
 * @date 2017年1月20日 下午4:32:47
 */
public interface ProceStepDefDao extends BaseDao<ProceStepDef, Integer> {

	public ProceStepDef getStepByStepCode(String stepCode);

	public List<ProceStepDef> getProjectByStep(String type);

}
