package com.whty.assis.basicdata.dao;

import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.SchoolLocation;
import com.whty.assis.basicdata.model.SchoolLocationLayer;

public interface SchoolLocationDao extends IBaseDao<SchoolLocation>{

	void saveSchoolLocation(SchoolLocation schoolLocation);

	void updateSchoolLocation(SchoolLocation schoolLocation);

	void saveSchoolLocationLayer(SchoolLocationLayer bean);

	/**
	 * @return
	 */
	String getAllCode();

	/**
	 * @param valueOf
	 */
	void deleteSchoolLocationLayer(Map<String,Object> valueOf);

	/**
	 * @param layerParamMap
	 * @return
	 */
	SchoolLocationLayer listLayer(Map<String, Object> layerParamMap);
	
}
