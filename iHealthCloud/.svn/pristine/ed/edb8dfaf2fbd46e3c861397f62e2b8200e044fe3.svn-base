package com.yhcrt.iHealthCloud.mapper;

import java.util.List;

import com.yhcrt.iHealthCloud.entity.SysVersion;

public interface SysVersionMapper {

	//查询最新版本
	SysVersion queryNewVersion(String versionType);
	
	List<SysVersion> getDescList(String appCode);
	
	SysVersion selectByPrimarykey(String version_id);

	//根据kid查询数据
	SysVersion queryByKid(String kid);

	//根据kid修改数据
	void update(SysVersion version);

}