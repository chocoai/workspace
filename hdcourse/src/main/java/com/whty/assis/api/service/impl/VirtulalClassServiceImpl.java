package com.whty.assis.api.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.whty.assis.api.service.VirtulalClassService;
import com.whty.assis.demo.dao.ClassGroupDao;
import com.whty.common.mongodb.MongoDBBaseDao;

import net.sf.json.JSONObject;

@Service
public class VirtulalClassServiceImpl implements VirtulalClassService {

	@Autowired
	private MemCachedClient memcachedClient;

	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;

	@Autowired
	private ClassGroupDao classGroupDao;

	/**
	 * 虚拟班级处理逻辑
	 */
	@Override
	public void virtulalClass(JSONObject ebpUserClassContent, String userId, String platformCode,
			JSONObject classJson) {

	}

}
