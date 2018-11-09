package com.whty.assis.api.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.whty.assis.api.model.BookPageCotent;
import com.whty.assis.api.model.BookPageTeach;
import com.whty.assis.api.model.EbpClassStudent;
import com.whty.assis.api.model.EbpUserClass;
import com.whty.assis.api.service.RealClassService;
import com.whty.assis.api.service.UserClassService;
import com.whty.assis.api.service.VirtulalClassService;
import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.api.virtualclassmodel.ClassModel;
import com.whty.assis.api.virtualclassmodel.Group;
import com.whty.assis.api.virtualclassmodel.GroupType;
import com.whty.assis.api.virtualclassmodel.Student;
import com.whty.assis.api.virtualclassmodel.Teacher;
import com.whty.assis.demo.dao.ClassGroupDao;
import com.whty.common.cache.data.CacheDataUtils;
import com.whty.common.cache.tools.XMemCached;
import com.whty.common.excel.FileVo;
import com.whty.common.excel.PoiUtil;
import com.whty.common.mongodb.MongoDBBaseDao;
import com.whty.common.util.GUIDGenerator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class UserClassServiceImpl implements UserClassService {

	public static String EBP_USER_CLASS = "ebpUserClass";

	@Autowired
	private MemCachedClient memcachedClient;

	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;

	@Autowired
	private ClassGroupDao classGroupDao;

	@Autowired
	private RealClassService realClassService;

	@Autowired
	private VirtulalClassService virtulalClassService;

	@Override
	public void student3() {
		// 处理真实班级
		Query query = new Query(Criteria.where("platformCode").is("420100"));

		List<EbpUserClass> list = mongoDBBaseDao.getMongoTemplate().find(query, EbpUserClass.class);

		for (EbpUserClass ebpUserClass : list) {
			String content = ebpUserClass.getContent();
			String userId = ebpUserClass.getUserId();
			String platformCode = ebpUserClass.getPlatformCode();

			JSONObject ebpUserClassContent = JSONObject.fromObject(content);

			JSONArray classList = ebpUserClassContent.optJSONArray("classlist");

			for (int i = 0; i < classList.size(); i++) {
				JSONObject classJson = classList.optJSONObject(i);

				int classSource = classJson.optInt("source");// 0 真实班级 1.虚拟班级

				if (classSource == 0) {// 真实班级处理逻辑
					realClassService.realClass(ebpUserClassContent, userId, platformCode, classJson);
				} else if (classSource == 1) {// 虚拟班级处理逻辑
					virtulalClassService.virtulalClass(ebpUserClassContent, userId, platformCode, classJson);
				}
			}
		}
	}

	public boolean isJson(String text) {
		try {
			JSONObject.fromObject(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isJsonArray(String text) {
		try {
			JSONArray.fromObject(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void studnet2() {
		Query query = new Query();

		List<EbpUserClass> list = mongoDBBaseDao.getMongoTemplate().find(query, EbpUserClass.class);

		// Map<String,Object> aaa = new HashMap<String,Object>();
		// 先提取2层结构
		for (EbpUserClass bean : list) {
			Map<String, Object> teacherMap = new HashMap<String, Object>();
			String content = bean.getContent();
			String userId = bean.getUserId();
			String platformCode = bean.getPlatformCode();

			teacherMap.put("userId", userId);
			teacherMap.put("platformCode", platformCode);

			if (!isJson(content))
				continue;

			JSONObject contentObject = JSONObject.fromObject(content);

			String name = contentObject.optString("name");
			String type = contentObject.optString("type");
			String schoolId = contentObject.optString("schoolId");
			String schoolName = contentObject.optString("schoolName");
			boolean cacheLoaded = contentObject.optBoolean("cacheLoaded");
			boolean cloudLoaded = contentObject.optBoolean("cloudLoaded");

			// teacherMap.put("name", name);
			// teacherMap.put("type", type);
			// teacherMap.put("schoolId", schoolId);
			// teacherMap.put("schoolName", schoolName);
			// teacherMap.put("cacheLoaded", cacheLoaded);
			// teacherMap.put("cloudLoaded", cloudLoaded);

			JSONArray classList = contentObject.optJSONArray("classlist");
			// teacherMap.put("classlist", classList);

			Teacher teacher = new Teacher();

			teacher.setCacheLoaded(cacheLoaded);
			teacher.setId(bean.getID());
			teacher.setSchoolId(schoolId);
			teacher.setSchoolName(schoolName);
			teacher.setCloudLoaded(cloudLoaded);
			teacher.setName(name);
			teacher.setType(type);
			teacher.setPlatformCode(platformCode);
			teacher.setUserId(userId);
			teacher.setCreateTime(new Date());

			classGroupDao.saveTeacher(teacher);

			List<Map<String, Object>> classListMap = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < classList.size(); i++) {// 班级列表
				ClassModel classModel = new ClassModel();

				JSONObject classData = classList.optJSONObject(i);
				classModel.setClassId(classData.optString("classId"));
				classModel.setClassName(classData.optString("className"));
				classModel.setTeacherCardNumber(classData.optInt("teacherCardNumber"));
				classModel.setSource(classData.optInt("source"));
				classModel.setCardNumberStart(classData.optInt("cardNumberStart"));
				classModel.setCacheLoaded(classData.optBoolean("cacheLoaded"));
				classModel.setCloudLoaded(classData.optBoolean("cloudLoaded"));
				classModel.setClassGroupTeacherId(bean.getID());
				String classId = GUIDGenerator.getUUID32();// 班级类的主键
				classModel.setId(classId);
				classModel.setCreateTime(new Date());
				classGroupDao.saveGroupClass(classModel);

				GroupType groupType = new GroupType(); // 分类

				groupType.setClassGroupClassId(classId);
				String groupTypeId = GUIDGenerator.getUUID32();

				groupType.setSortNum(1);
				groupType.setTypeId(groupTypeId);
				groupType.setTypeName("互动课堂分组");
				groupType.setCreateTime(new Date());
				classGroupDao.saveGroupType(groupType);

				JSONArray groupJsonObjectList = classData.optJSONArray("groupList");// 分组

				for (int j = 0; j < groupJsonObjectList.size(); j++) {
					JSONObject jsonObject = groupJsonObjectList.optJSONObject(j);

					Group group = new Group();
					group.setClassGroupTypeId(groupTypeId);
					group.setGroupId(jsonObject.optString("Id"));
					group.setGroupName(jsonObject.optString("Name"));
					group.setSortNum(j + 1);
					classGroupDao.saveGroup(group);// 保存分组
				}
			}
		}

		// 导入学生
		Query studentQuery = new Query();
		List<EbpClassStudent> classStudentList = mongoDBBaseDao.getMongoTemplate().find(studentQuery,
				EbpClassStudent.class);

		for (EbpClassStudent bean : classStudentList) {
			String content = bean.getContent();

			if (!isJsonArray(content))
				continue;

			JSONArray jsonArray = JSONArray.fromObject(content);

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject studentObject = jsonArray.getJSONObject(i);

				Student student = new Student();

				student.setId(GUIDGenerator.getUUID32());
				student.setCardNumber(studentObject.optString("cardNumber"));
				student.setClassId(bean.getClassId());
				student.setGroupId(studentObject.optString("groupId"));
				student.setPlatformCode(bean.getPlatformCode());
				student.setStudentId(studentObject.optString("studentId"));
				student.setStudentName(studentObject.optString("studentName"));
				student.setPenNumber(studentObject.optString("penNumber"));
				student.setSexType(studentObject.optInt("sexType"));
				student.setSource(studentObject.optInt("source"));

				classGroupDao.saveStudent(student);
			}
		}
	}

	@Override
	public void student() {
		Query query = new Query(Criteria.where("platformCode").is("888888"));

		List<EbpUserClass> list = mongoDBBaseDao.getMongoTemplate().find(query, EbpUserClass.class);

		for (EbpUserClass bean : list) {

			String content = bean.getContent();
			String userId = bean.getUserId();

			JSONObject contentObject = JSONObject.fromObject(content);

			JSONArray classList = contentObject.optJSONArray("classlist");

			for (int i = 0; i < classList.size(); i++) {
				JSONObject classData = classList.optJSONObject(i);
				int source = classData.optInt("source");

				// 处理虚拟班级
				if (source == 0)
					continue;

				String classId = classData.optString("classId");
				JSONArray groupJsonObjectList = classData.optJSONArray("groupList");

				Map<String, Object> groupMap = getGroupMap(groupJsonObjectList);

				Query studentQuery = new Query(Criteria.where("classId").is(classId));
				List<EbpClassStudent> studentList = mongoDBBaseDao.getMongoTemplate().find(studentQuery,
						EbpClassStudent.class);//

				Map<String, Object> groupStudent = new HashMap<String, Object>();

				for (EbpClassStudent classStudentBean : studentList) {

					String classStudentContent = classStudentBean.getContent();

					// JSONObject classStudentJsonContent =
					// JSONObject.fromObject(classStudentContent);

					JSONArray classStudentJsonContentList = JSONArray.fromObject(classStudentContent);

					for (int j = 0; j < classStudentJsonContentList.size(); j++) {

						JSONObject classStudentJsonContent = classStudentJsonContentList.optJSONObject(j);

						int studentSource = classStudentJsonContent.optInt("source");

						// 只处理虚拟学生
						if (studentSource == 0)
							continue;

						String groupId = classStudentJsonContent.optString("groupId");
						List<JSONObject> groupStudentList = (List<JSONObject>) groupStudent.get(groupId);
						if (groupStudentList == null) {
							groupStudentList = new ArrayList<JSONObject>();
							JSONObject userJson = new JSONObject();
							userJson.put("personid", classStudentJsonContent.optString("studentId"));
							userJson.put("memberType", "2");
							groupStudentList.add(userJson);
							groupStudent.put(groupId, groupStudentList);
						} else {
							JSONObject userList = new JSONObject();
							userList.put("personid", classStudentJsonContent.optString("studentId"));
							userList.put("memberType", "2");
							groupStudentList.add(userList);
							groupStudent.put(groupId, groupStudentList);
						}
					}
				}
				List<Map<String, Object>> groupList = new ArrayList<Map<String, Object>>();
				int sortNum = 1;
				for (Map.Entry<String, Object> entry : groupStudent.entrySet()) {
					String groupId = entry.getKey();
					List<JSONObject> groupStudentList = (List<JSONObject>) entry.getValue();

					if (groupMap.get(groupId) != null) {
						String groupName = groupMap.get(groupId).toString();
						Map<String, Object> aamifgroupMap = new HashMap<String, Object>();
						aamifgroupMap.put("groupId", groupId);
						aamifgroupMap.put("groupName", groupName);
						aamifgroupMap.put("userList", groupStudentList);
						aamifgroupMap.put("sortNum", sortNum);
						sortNum = sortNum + 1;
						groupList.add(aamifgroupMap);
					}
				}

				Map<String, Object> classGroupType = new HashMap<String, Object>();
				classGroupType.put("typeId", GUIDGenerator.getUUID32());
				classGroupType.put("typeName", "互动课堂分组");
				classGroupType.put("groupList", groupList);
				classGroupType.put("ownerType", "1");
				classGroupType.put("sortNum", 1);

				Map<String, Object> zz = new HashMap<String, Object>();
				zz.put("classId", classId);
				zz.put("userId", userId);
				zz.put("classGroupType", classGroupType);
				System.out.println(JSONObject.fromObject(zz));

				String hueihuaEditUrl = "http://116.211.105.45:30007/aamif/rest/classGroup/edit";

				// String hueihuaEditResult =
				// HttpUtils.getInstance().httpPost(hueihuaEditUrl,
				// JSONObject.fromObject(zz).toString());
				// System.out.println(hueihuaEditResult);
			}
		}

	}

	@Override
	public void ExportToAAM() throws Exception {
		Query query = new Query(Criteria.where("platformCode").is("888888"));

		List<EbpUserClass> list = mongoDBBaseDao.getMongoTemplate().find(query, EbpUserClass.class);

		for (EbpUserClass bean : list) {

			String content = bean.getContent();
			String userId = bean.getUserId();

			JSONObject contentObject = JSONObject.fromObject(content);

			JSONArray classList = contentObject.optJSONArray("classlist");

			for (int i = 0; i < classList.size(); i++) {
				JSONObject classData = classList.optJSONObject(i);
				int source = classData.optInt("source");

				if (source != 0)
					continue;

				String classId = classData.optString("classId");
				JSONArray groupJsonObjectList = classData.optJSONArray("groupList");

				Map<String, Object> groupMap = getGroupMap(groupJsonObjectList);

				Query studentQuery = new Query(Criteria.where("classId").is(classId));
				List<EbpClassStudent> studentList = mongoDBBaseDao.getMongoTemplate().find(studentQuery,
						EbpClassStudent.class);//

				Map<String, Object> groupStudent = new HashMap<String, Object>();

				for (EbpClassStudent classStudentBean : studentList) {

					String classStudentContent = classStudentBean.getContent();

					// JSONObject classStudentJsonContent =
					// JSONObject.fromObject(classStudentContent);

					JSONArray classStudentJsonContentList = JSONArray.fromObject(classStudentContent);

					for (int j = 0; j < classStudentJsonContentList.size(); j++) {

						JSONObject classStudentJsonContent = classStudentJsonContentList.optJSONObject(j);

						int studentSource = classStudentJsonContent.optInt("source");

						// 不是真实学生，不处理
						if (studentSource != 0)
							continue;

						String groupId = classStudentJsonContent.optString("groupId");
						List<JSONObject> groupStudentList = (List<JSONObject>) groupStudent.get(groupId);
						if (groupStudentList == null) {
							groupStudentList = new ArrayList<JSONObject>();
							JSONObject userJson = new JSONObject();
							userJson.put("personid", classStudentJsonContent.optString("studentId"));
							userJson.put("memberType", "2");
							groupStudentList.add(userJson);
							groupStudent.put(groupId, groupStudentList);
						} else {
							JSONObject userList = new JSONObject();
							userList.put("personid", classStudentJsonContent.optString("studentId"));
							userList.put("memberType", "2");
							groupStudentList.add(userList);
							groupStudent.put(groupId, groupStudentList);
						}
					}
				}
				List<Map<String, Object>> groupList = new ArrayList<Map<String, Object>>();
				int sortNum = 1;
				for (Map.Entry<String, Object> entry : groupStudent.entrySet()) {
					String groupId = entry.getKey();
					List<JSONObject> groupStudentList = (List<JSONObject>) entry.getValue();

					if (groupMap.get(groupId) != null) {
						String groupName = groupMap.get(groupId).toString();
						Map<String, Object> aamifgroupMap = new HashMap<String, Object>();
						aamifgroupMap.put("groupId", groupId);
						aamifgroupMap.put("groupName", groupName);
						aamifgroupMap.put("userList", groupStudentList);
						aamifgroupMap.put("sortNum", sortNum);
						sortNum = sortNum + 1;
						groupList.add(aamifgroupMap);
					}
				}

				Map<String, Object> classGroupType = new HashMap<String, Object>();
				classGroupType.put("typeName", "互动课堂分组");
				classGroupType.put("groupList", groupList);
				classGroupType.put("ownerType", "1");
				classGroupType.put("type", "");
				classGroupType.put("sortNum", 1);

				Map<String, Object> zz = new HashMap<String, Object>();
				zz.put("classId", classId);
				zz.put("userId", userId);
				zz.put("classGroupType", classGroupType);
				System.out.println(JSONObject.fromObject(zz));

				String hueihuaEditUrl = "http://116.211.105.45:30007/aamif/rest/classGroup/edit";

				String hueihuaEditResult = HttpUtils.getInstance().httpPost(hueihuaEditUrl,
						JSONObject.fromObject(zz).toString());
				System.out.println(hueihuaEditResult);
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

	@Override
	public EbpUserClass findUserClass(Map map) {
		EbpUserClass userClass = new EbpUserClass();
		userClass.setUserId((String) map.get("userId"));
		userClass.setPlatformCode((String) map.get("platformCode"));

		// 1.从教育云获取的用户班级列表信息
		List<BookPageTeach> teachContentList = this.getTeachContentList((JSONArray) map.get("teachInfo"));

		// 2.从教师助手后台获取的用户在客户端新建的班级列表信息
		/*
		 * 优先从缓存中获取班级信息
		 */
		// EbpUserClass tempUserClass = this.getUserClassCache(userClass);

		// String value = (String)
		// CacheContainer.whalinMemcachedCache(EBP_USER_CLASS)
		// .get(EBP_USER_CLASS + "|" + userClass.getPlatformCode() +
		// userClass.getUserId());

		EbpUserClass tempUserClass = null;

		String content = this.getUserClassContent(userClass);

		if (content == null) {
			// tempUserClass = userClassDao.queryUserClass(userClass);

			Query query = new Query(Criteria.where("userId").is(userClass.getUserId()).and("platformCode")
					.is(userClass.getPlatformCode()));

			tempUserClass = mongoDBBaseDao.getMongoTemplate().findOne(query, EbpUserClass.class);

			if (tempUserClass != null) {
				// this.addUserClassCache(tempUserClass);

				this.addOrUpdateUserClassContentCache(tempUserClass);
				// CacheContainer.whalinMemcachedCache(EBP_USER_CLASS).add(
				// EBP_USER_CLASS + "|" + userClass.getPlatformCode() +
				// userClass.getUserId(),
				// tempUserClass.getContent());
			}
		}

		BookPageCotent contentPage = new BookPageCotent();
		contentPage.setName((String) map.get("name"));
		contentPage.setSchoolId((String) map.get("orgaid"));
		contentPage.setSchoolName((String) map.get("organame"));
		contentPage.setType((String) map.get("usertype"));
		contentPage.setUserid((String) map.get("userId"));

		contentPage.setClasslist(teachContentList);

		userClass.setContentReal(JSONObject.fromObject(contentPage).toString());

		if (content != null) {
			userClass.setContent(content);
		}

		return userClass;
	}

	private List<BookPageTeach> getTeachContentList(JSONArray jsonArray) {
		List<BookPageTeach> teachContentList = new ArrayList<BookPageTeach>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jo = (JSONObject) jsonArray.get(i);
			BookPageTeach teachContent = new BookPageTeach();

			teachContent.setClassId(jo.optString("classid"));
			teachContent.setClassName(jo.optString("classname"));

			teachContentList.add(teachContent);
		}
		return teachContentList;
	}

	@SuppressWarnings("rawtypes")
	public void updateUserClass(Map para) {
		String userId = (String) para.get("userId");
		String updateContent = JSONObject.fromObject(para.get("content")).toString();
		String platformCode = (String) para.get("platformCode");

		EbpUserClass userClass = new EbpUserClass();
		userClass.setUserId(userId);
		userClass.setPlatformCode(platformCode);

		/*
		 * 优先从缓存中获取班级信息
		 */
		// EbpUserClass tempUserClass = this.getUserClassCache(userClass);

		// EbpUserClass tempUserClass = null;

		String content = this.getUserClassContent(userClass);
		//
		// if (content == null) {

		// Query query = new
		// Query(Criteria.where("userId").is(userClass.getUserId()).and("platformCode")
		// .is(userClass.getPlatformCode()));

		// tempUserClass = mongoDBBaseDao.getMongoTemplate().findOne(query,
		// EbpUserClass.class);
		// tempUserClass = userClassDao.queryUserClass(userClass);
		// }

		userClass.setContent(updateContent);

		if (content == null) {
			userClass.setID(GUIDGenerator.getUUID32());
			// userClassDao.save(userClass);
			mongoDBBaseDao.add(userClass);
		} else {
			Query query = new Query();
			Criteria criteria = Criteria.where("userId").is(userClass.getUserId()).and("platformCode")
					.is(userClass.getPlatformCode());
			query.addCriteria(criteria);

			Update update = new Update();
			update.set("content", userClass.getContent());

			mongoDBBaseDao.getMongoTemplate().updateFirst(query, update, EbpUserClass.class);

			// userClassDao.update(userClass);
		}

		this.addOrUpdateUserClassContentCache(userClass);
		// this.addUserClassCache(userClass);
	}

	/**
	 * 互动课堂班级信息放入缓存
	 */
	public void addUserClassCache(EbpUserClass userClass) {
		try {
			CacheDataUtils.setObjectData(UserClassServiceImpl.EBP_USER_CLASS,
					userClass.getPlatformCode() + "_" + userClass.getUserId(), userClass, 24 * 60 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addOrUpdateUserClassContentCache(EbpUserClass userClass) {
		try {
			// String content = (String)
			// CacheContainer.whalinMemcachedCache(EBP_USER_CLASS).get(EBP_USER_CLASS
			// + XMemCached.KEY_SEPERATER2 + userClass.getPlatformCode() + "_" +
			// userClass.getUserId());

			String content = (String) memcachedClient.get(EBP_USER_CLASS + XMemCached.KEY_SEPERATER2
					+ userClass.getPlatformCode() + "_" + userClass.getUserId());
			if (content == null) {
				memcachedClient.add(EBP_USER_CLASS + XMemCached.KEY_SEPERATER2 + userClass.getPlatformCode() + "_"
						+ userClass.getUserId(), userClass.getContent());
			} else {
				memcachedClient.replace(EBP_USER_CLASS + XMemCached.KEY_SEPERATER2 + userClass.getPlatformCode() + "_"
						+ userClass.getUserId(), userClass.getContent());
				// CacheContainer.whalinMemcachedCache(EBP_USER_CLASS).replace(EBP_USER_CLASS
				// + XMemCached.KEY_SEPERATER2
				// + userClass.getPlatformCode() + "_" + userClass.getUserId(),
				// userClass.getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUserClassContent(EbpUserClass userClass) {
		String content = null;
		try {
			// System.out.println(EBP_USER_CLASS
			// + XMemCached.KEY_SEPERATER2 + userClass.getPlatformCode() + "_" +
			// userClass.getUserId());

			content = (String) memcachedClient.get(EBP_USER_CLASS + XMemCached.KEY_SEPERATER2
					+ userClass.getPlatformCode() + "_" + userClass.getUserId());

			// content = (String)
			// CacheContainer.whalinMemcachedCache(EBP_USER_CLASS).get(EBP_USER_CLASS
			// + XMemCached.KEY_SEPERATER2 + userClass.getPlatformCode() + "_" +
			// userClass.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 从缓存中获取互动课堂班级信息
	 */
	public EbpUserClass getUserClassCache(EbpUserClass userClass) {
		EbpUserClass resultUserClass = null;
		try {
			resultUserClass = (EbpUserClass) CacheDataUtils.getObjectData(UserClassServiceImpl.EBP_USER_CLASS,
					userClass.getPlatformCode() + "_" + userClass.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUserClass;
	}

	/**
	 * 查询用户虚拟班级
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public EbpUserClass findUserVirtualClass(Map map) {
		EbpUserClass virtualClass = new EbpUserClass();
		virtualClass.setUserId((String) map.get("userId"));
		virtualClass.setPlatformCode((String) map.get("userPlatformCode"));

		// 1.从教师助手后台获取的用户在客户端新建的班级列表信息
		/*
		 * 优先从缓存中获取班级信息
		 */
		// EbpUserClass tempVirtualClass = this.getUserClassCache(virtualClass);

		EbpUserClass tempVirtualClass = null;
		String content = this.getUserClassContent(virtualClass);

		if (content == null) {
			// tempVirtualClass = userClassDao.queryUserClass(virtualClass);

			Query query = new Query(Criteria.where("userId").is(virtualClass.getUserId()).and("platformCode")
					.is(virtualClass.getPlatformCode()));

			tempVirtualClass = mongoDBBaseDao.getMongoTemplate().findOne(query, EbpUserClass.class);

			if (tempVirtualClass != null) {
				// this.addUserClassCache(tempVirtualClass);
				content = tempVirtualClass.getContent();
				this.addOrUpdateUserClassContentCache(tempVirtualClass);
			}
		}

		if (content != null) {
			virtualClass.setContent(content);
		}

		return virtualClass;
	}

	@Override
	public Map<String, Object> getAllCardNumberData() throws IOException {
		Map<String, Object> param = new HashMap<String, Object>();

		List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
		File file = new File("D:\\kezhong");

		if (file.exists()) {
			File[] files = file.listFiles();

			for (File file2 : files) {
				String name = file2.getName().substring(0, file2.getName().lastIndexOf("."));// 获取除后缀1位的名

				// if (!name.contains("三年级(1)班"))
				// continue;
				System.out.println(name);
				FileVo fileVo = new FileVo();
				fileVo.setFile(file2);
				fileVo.setFileFileName(file2.getName());
				List<Object> datalst = PoiUtil.readerExcelSheet(fileVo, 1);

				param.put(name, datalst);
			}
		}
		return param;

	}

	@Override
	public void getAllClassInfo() throws Exception {
		Map<String, Object> allCardNumberDataMap = getAllCardNumberData();

		JSONArray result = new JSONArray();
		String url = "http://122.229.30.170:20014/aamif/rest/classinfo/query";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgaid", "3433005813");// 柯桥中学
		// param.put("orgaid", "3133005930");// 鲁外

		param.put("start", "0");
		param.put("end", "50");
		String answer = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(param).toString());
		System.out.println(answer);
		JSONObject answerJson = JSONObject.fromObject(answer);

		JSONArray classinfolist = answerJson.optJSONArray("classinfolist");

		for (int i = 0; i < classinfolist.size(); i++) {
			JSONObject classInfo = classinfolist.optJSONObject(i);

			String className = classInfo.optString("classname");

			// if (!className.contains("三年级(1)班"))
			// continue;
			System.out.println(className);
			Map<String, String> studentCardNumberInfoMap = getStudentCardNumberInfoMap(
					(List<Object>) allCardNumberDataMap.get(className));

			JSONArray classmembers = classInfo.optJSONArray("classmembers");

			String groupId = GUIDGenerator.getUUID32();// 组编号
			JSONArray contentArray = new JSONArray();

			Map<String, String> param2 = new HashMap<String, String>();

			for (int j = 0; j < classmembers.size(); j++) {

				JSONObject classmember = classmembers.optJSONObject(j);
				String studentName = classmember.optString("name");// 学生名称
				String studentId = classmember.optString("personid");// 学生id

				if (param2.get(studentName) != null) {
					continue;
				}

				param2.put(studentName, studentId);

				String penNumber = "";
				String sexType = "0";
				String source = "1";
				String cardNumber = studentCardNumberInfoMap.get(studentName);// 答题卡

				JSONObject student = new JSONObject();

				student.put("studentId", studentId);
				student.put("studentName", studentName);
				student.put("cardNumber", cardNumber);
				student.put("penNumber", penNumber);
				student.put("sexType", sexType);
				student.put("source", source);
				student.put("groupId", groupId);

				contentArray.add(student);
			}

			String id = GUIDGenerator.getUUID32();
			String content = null;
			String type = "";
			content = contentArray.toString();
			String classId = classInfo.optString("classid");
			String platformCode = "330621";

			EbpClassStudent classStudent = new EbpClassStudent();
			classStudent.setClassId(classId);
			classStudent.setPlatformCode(platformCode);

			Query query = new Query();
			Criteria criteria = Criteria.where("classId").is(classStudent.getClassId()).and("platformCode")
					.is(classStudent.getPlatformCode());
			query.addCriteria(criteria);

			EbpClassStudent tempClassStudent = mongoDBBaseDao.getMongoTemplate().findOne(query, EbpClassStudent.class);

			if (tempClassStudent == null) {
				tempClassStudent = new EbpClassStudent();
				tempClassStudent.setClassId(classId);
				tempClassStudent.setContent(content);
				tempClassStudent.setType(type);
				tempClassStudent.setID(id);
				tempClassStudent.setPlatformCode(platformCode);

				mongoDBBaseDao.add(tempClassStudent);
				// 新增
			} else {
				// 先删除在添加
				Query query2 = new Query();
				Criteria criteria2 = Criteria.where("classId").is(classStudent.getClassId()).and("platformCode")
						.is(classStudent.getPlatformCode());
				query2.addCriteria(criteria2);
				mongoDBBaseDao.getMongoTemplate().remove(query2, EbpClassStudent.class);

				tempClassStudent = new EbpClassStudent();
				tempClassStudent.setClassId(classId);
				tempClassStudent.setContent(content);
				tempClassStudent.setType(type);
				tempClassStudent.setID(id);
				tempClassStudent.setPlatformCode(platformCode);
				mongoDBBaseDao.add(tempClassStudent);
				// 更新
			}

		}

		System.out.println("跑完了");
		// return result;
	}

	public Map<String, String> getStudentCardNumberInfoMap(List<Object> ss) {
		Map<String, String> sss = new HashMap<String, String>();
		if (ss != null) {
			for (Object bean : ss) {
				String[] ssStr = (String[]) bean;
				sss.put(ssStr[0], ssStr[1]);
			}
		}
		return sss;
	}

	@Override
	public void importCardNumber() throws Exception {
		// String allTeacher =
		// "http://122.229.30.170:20014/aamif/rest/member/query";
		// Map<String, Object> param = new HashMap<String, Object>();
		//
		// param.put("groupid", "2133006477");
		// String[] typeArr = { "1" };
		// param.put("type", typeArr);
		// param.put("start", 0);
		// param.put("end", 80);
		//
		// String answer = HttpUtils.getInstance().httpPost(allTeacher,
		// JSONObject.fromObject(param).toString());
		//
		// JSONObject answerJson = JSONObject.fromObject(answer);
		//
		// if ("000000".equals(answerJson.optString("result"))) {
		// JSONArray userinfolist = answerJson.optJSONArray("userinfolist");
		//
		// for (int i = 0; i < userinfolist.size(); i++) {
		// JSONObject userInfo = userinfolist.optJSONObject(i);
		//
		// String orgaId = userInfo.optString("orgaid");
		// String personid = userInfo.optString("personid");
		// String platformCode = userInfo.optString("platformCode");
		//
		// JSONArray teachesubjectlist =
		// userInfo.optJSONArray("teachesubjectlist");
		//
		// for (int j = 0; j < teachesubjectlist.size(); j++) {
		// JSONObject teachersubject = teachesubjectlist.optJSONObject(j);
		//
		// String classId = teachersubject.optString("classid");
		//
		// if(classId==null||"".equals(classId))
		// continue;
		//
		//
		//
		// }
		//
		// }
		//
		// }

		// getAllClassInfo();
		// getAllCardNumberData();
	}

}
