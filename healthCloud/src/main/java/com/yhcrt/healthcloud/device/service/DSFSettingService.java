package com.yhcrt.healthcloud.device.service;

import java.util.HashMap;
import java.util.List;

import com.yhcrt.healthcloud.device.entity.DSFSetting;

public interface DSFSettingService {
	
	int insert(DSFSetting dsfSetting);
	
	int delete(Integer cid);
	
	int update(DSFSetting dsfSetting);
	
	List<DSFSetting> getListByParams(HashMap<String, Object> params);
	
	DSFSetting getDSFSettingByCid(Integer cid);
	
	

}
