package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.entity.SysVersion;
import com.yhcrt.iHealthCloud.mapper.SysVersionMapper;
import com.yhcrt.iHealthCloud.service.SysVersionService;
import com.yhcrt.iHealthCloud.util.Const;

@Service
public class SysVersionServiceImpl extends BaseService implements
		SysVersionService {

	@Autowired
	private SysVersionMapper sysVersionMapper;

	// @Autowired
	// private SysDictionaryMapper sysDictionaryMapper;

	// 查询最新版本
	@Override
	public String queryNewVersion(JSONObject pdataObj) {
		// 获取biz中的参数
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String version_type = bizObj.getString("version_type");
		if (StringUtils.isBlank(version_type)) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "应用名称不能为空!");
		} else {
			try {
				SysVersion version = sysVersionMapper.queryNewVersion(version_type);
				if (version != null && StringUtils.isNotBlank(version.getcVersionDesc())) {
					pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
					pdataObj.put(Const.TAG_RMK, "获取成功");
					pdataObj.put(Const.TAG_BIZ, version);
				} else {
					pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
					pdataObj.put(Const.TAG_RMK, "未查询到新版本!");
				}
			} catch (Exception e) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, e.getMessage());
				e.printStackTrace();
			}
		}
		return pdataObj.toJSONString();
	}
	
	// 查询每个版本list
	@Override
	public String getVersionList(JSONObject pdataObj) {  
		// 获取biz中的参数
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String app_code = bizObj.getString("app_code");
		if (StringUtils.isBlank(app_code)) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "app_code不能为空!");
		} else {
			try {
				List<SysVersion> versionList = sysVersionMapper.getDescList(app_code);
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "获取成功");
				pdataObj.put(Const.TAG_BIZ, versionList);
			} catch (Exception e) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, e.getMessage());
				e.printStackTrace();
			}
		}
		return pdataObj.toJSONString();
	}
	
	// 查询版本说明
	@Override
	public String getVersionDesc(JSONObject pdataObj) {
		// 获取biz中的参数
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String version_id = bizObj.getString("version_id");
		if (StringUtils.isBlank(version_id)) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "version_id不能为空!");
		} else {
			try {
				// 获取字典中应用名称的key
				// version_type = sysDictionaryMapper.getDictValue(version_type);
				SysVersion version = sysVersionMapper.selectByPrimarykey(version_id);
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "获取成功");
				pdataObj.put(Const.TAG_BIZ, version);
			} catch (Exception e) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, e.getMessage());
				e.printStackTrace();
			}
		}
		return pdataObj.toJSONString();
	}

	// 统计下载版本次数
	@Override
	public String totalVersionNum(JSONObject pdataObj) {
		// 获取biz中的参数
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String kid = bizObj.getString("kid");
		if (StringUtils.isBlank(kid)) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "应用名称不能为空!");
		} else {
			try {
				SysVersion version = sysVersionMapper.queryByKid(kid);	//查询下载包路径所在数据
				if (version != null) {
					if(StringUtils.isNotBlank(version.getcExt01())){
						version.setcExt01(String.valueOf(Integer.parseInt(version.getcExt01())+1));
					}else{
						version.setcExt01("1");
					}
					sysVersionMapper.update(version);	//修改统计数量
				}
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "获取成功");
				pdataObj.put(Const.TAG_BIZ, version);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pdataObj.toJSONString();
	}

}
