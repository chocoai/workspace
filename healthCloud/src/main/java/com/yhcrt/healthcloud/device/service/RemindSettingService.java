package com.yhcrt.healthcloud.device.service;

import java.util.HashMap;
import java.util.List;

import com.yhcrt.healthcloud.device.entity.RemindSetting;

public interface RemindSettingService {

    int insert(RemindSetting record);
    
    int deleteByCid(Integer cid);
    
    int update(RemindSetting record);

    List<RemindSetting> selectByParams(HashMap<String, Object> params);

    RemindSetting selectByCid(Integer cid);

}
