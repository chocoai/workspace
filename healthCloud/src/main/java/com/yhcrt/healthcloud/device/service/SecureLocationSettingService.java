package com.yhcrt.healthcloud.device.service;

import java.util.HashMap;
import java.util.List;

import com.yhcrt.healthcloud.device.entity.SecureLocationSetting;

public interface SecureLocationSettingService {
	
	int insert(SecureLocationSetting locationSetting);
	
	int deleteById(Integer Id);
	
	int update(SecureLocationSetting locationSetting);
	
	List<SecureLocationSetting> selectByParams(HashMap<String, Object> params);
	
	SecureLocationSetting getOneById(Integer Id);
	

}
