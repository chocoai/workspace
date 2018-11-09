package com.whty.assis.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.whty.assis.api.model.EbpClassStudent;
import com.whty.assis.api.service.RealClassService;
import com.whty.assis.demo.dao.ClassGroupDao;
import com.whty.common.mongodb.MongoDBBaseDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class RealClassServiceImpl implements RealClassService {

	@Autowired
	private MemCachedClient memcachedClient;

	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;

	@Autowired
	private ClassGroupDao classGroupDao;

	/**
	 * 真实班级处理逻辑
	 */
	@Override
	public void realClass(JSONObject ebpUserClassContent, String userId, String platformCode, JSONObject classJson) {

		String classId = classJson.optString("classId");

		// 分组信息
		JSONArray groupJsonObjectList = classJson.optJSONArray("groupList");

		Map<String, Object> groupMap = getGroupMap(groupJsonObjectList);

		Query studentQuery = new Query(Criteria.where("classId").is(classId));
		List<EbpClassStudent> studentList = mongoDBBaseDao.getMongoTemplate().find(studentQuery, EbpClassStudent.class);

		Map<String, Object> groupStudent = new HashMap<String, Object>();

		for (EbpClassStudent classStudentBean : studentList) {

			String classStudentContent = classStudentBean.getContent();

			// JSONObject classStudentJsonContent =
			// JSONObject.fromObject(classStudentContent);

			JSONArray classStudentJsonContentList = JSONArray.fromObject(classStudentContent);

			for (int j = 0; j < classStudentJsonContentList.size(); j++) {

				JSONObject classStudentJsonContent = classStudentJsonContentList.optJSONObject(j);

				int studentSource = classStudentJsonContent.optInt("source");

				// 虚拟学生和真实学生分开
				// if()

			}
		}
	}

	private Map<String, Object> getGroupMap(JSONArray groupJsonObjectList) {
		Map<String, Object> param = new HashMap<String, Object>();
		for (int i = 0; i < groupJsonObjectList.size(); i++) {
			JSONObject groupJson = groupJsonObjectList.optJSONObject(i);

			param.put(groupJson.optString("Id"), groupJson.optString("Name"));

		}
		return param;
	}

}
