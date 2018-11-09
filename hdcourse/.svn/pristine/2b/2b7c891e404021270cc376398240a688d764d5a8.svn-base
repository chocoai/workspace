package com.whty.assis.api.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.whty.assis.api.model.BookPageStudent;
import com.whty.assis.api.model.EbpClassStudent;
import com.whty.assis.api.service.ClassStudentService;
import com.whty.common.cache.data.CacheDataUtils;
import com.whty.common.cache.tools.CacheContainer;
import com.whty.common.cache.tools.XMemCached;
import com.whty.common.mongodb.MongoDBBaseDao;
import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.HttpUser;

import net.sf.json.JSONArray;

@Service
public class ClassStudentServiceImpl implements ClassStudentService {

	public static String EBP_CLASS_STUDENT = "ebpClassStudent";

	@Autowired
	private MemCachedClient memcachedClient;

	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;

	@Override
	public EbpClassStudent findEduClassStudent(Map map) {
		String classId = (String) map.get("classId");
		String platformCode = (String) map.get("platformCode");
		EbpClassStudent classStudent = new EbpClassStudent();
		classStudent.setClassId(classId);
		classStudent.setPlatformCode(platformCode);

		// 1.从教育云获取班级学生信息列表
		List<BookPageStudent> bookContentList = HttpUser.getClassStudentList(classId, platformCode);

		// 2.从教师助手后台获取的用户在客户端新建的学生信息列表
		/*
		 * 优先从缓存中获取学生信息
		 */
		// EbpClassStudent tempClassStudent =
		// this.getClassStudentCache(classStudent);

		EbpClassStudent tempClassStudent = null;

		String content = this.getClassStudentContentCache(classStudent);

		if (content == null) {
			// tempClassStudent =
			// classStudentDao.queryClassStudent(classStudent);

			Query query = new Query(Criteria.where("classId").is(classStudent.getClassId()).and("platformCode")
					.is(classStudent.getPlatformCode()));

			tempClassStudent = mongoDBBaseDao.getMongoTemplate().findOne(query, EbpClassStudent.class);

			// mongoDBBaseDao.

			if (tempClassStudent != null) {
				content = tempClassStudent.getContent();
				this.addOrUpdateClassStudentContentCache(tempClassStudent);
				// this.addClassStudentCache(tempClassStudent);
			}
		}

		if (content != null) {
			classStudent.setContent(content);
		}
		classStudent.setContentReal(JSONArray.fromObject(bookContentList).toString());

		return classStudent;
	}

	/**
	 * 从缓存中获取互动课堂学生信息
	 */
	public EbpClassStudent getClassStudentCache(EbpClassStudent classStudent) {
		EbpClassStudent resultClassStudent = null;
		try {
			resultClassStudent = (EbpClassStudent) CacheDataUtils.getObjectData(
					ClassStudentServiceImpl.EBP_CLASS_STUDENT,
					classStudent.getPlatformCode() + "_" + classStudent.getClassId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultClassStudent;
	}

	/**
	 * 互动课堂学生信息放入缓存
	 */
	public void addClassStudentCache(EbpClassStudent classStudent) {
		try {
			CacheDataUtils.setObjectData(ClassStudentServiceImpl.EBP_CLASS_STUDENT,
					classStudent.getPlatformCode() + "_" + classStudent.getClassId(), classStudent, 24 * 60 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public EbpClassStudent findSelfClassStudent(Map map) {
		String classId = (String) map.get("classId");
		String platformCode = (String) map.get("platformCode");
		EbpClassStudent classStudent = new EbpClassStudent();
		classStudent.setClassId(classId);
		classStudent.setPlatformCode(platformCode);

		/*
		 * 优先从缓存中获取学生信息
		 */
		// EbpClassStudent tempClassStudent =
		// this.getClassStudentCache(classStudent);

		EbpClassStudent tempClassStudent = null;

		String content = this.getClassStudentContentCache(classStudent);

		if (content == null) {

			Query query = new Query();
			Criteria criteria = Criteria.where("classId").is(classStudent.getClassId()).and("platformCode")
					.is(classStudent.getPlatformCode());
			query.addCriteria(criteria);

			tempClassStudent = mongoDBBaseDao.getMongoTemplate().findOne(query, EbpClassStudent.class);

			if (tempClassStudent != null) {
				content = tempClassStudent.getContent();
				this.addOrUpdateClassStudentContentCache(tempClassStudent);
				// this.addClassStudentCache(tempClassStudent);
			}
		}

		if (null != content)
			// classStudent = tempClassStudent;
			classStudent.setContent(content);
		// 从教师助手后台获取的用户在客户端新建的学生信息列表
		return classStudent;
	}

	@SuppressWarnings("rawtypes")
	public void updateClassStudent(Map para) {
		String classId = (String) para.get("classId");
		String updateContent = JSONArray.fromObject(para.get("content")).toString();
		String platformCode = (String) para.get("platformCode");

		EbpClassStudent classStudent = new EbpClassStudent();
		classStudent.setClassId(classId);
		classStudent.setPlatformCode(platformCode);

		/*
		 * 优先从缓存中获取学生信息
		 */
		// EbpClassStudent tempClassStudent =
		// this.getClassStudentCache(classStudent);

		EbpClassStudent tempClassStudent = null;

		String content = this.getClassStudentContentCache(classStudent);

		if (content == null) {

			// tempClassStudent =
			// classStudentDao.queryClassStudent(classStudent);

			Query query = new Query();
			Criteria criteria = Criteria.where("classId").is(classStudent.getClassId()).and("platformCode")
					.is(classStudent.getPlatformCode());
			query.addCriteria(criteria);

			tempClassStudent = mongoDBBaseDao.getMongoTemplate().findOne(query, EbpClassStudent.class);

		}

		classStudent.setContent(updateContent);

		if (content == null) {
			classStudent.setID(GUIDGenerator.getUUID32());

			mongoDBBaseDao.add(classStudent);

			// classStudentDao.save(classStudent);
		} else {
			Query query = new Query();
			Criteria criteria = Criteria.where("classId").is(classStudent.getClassId()).and("platformCode")
					.is(classStudent.getPlatformCode());
			query.addCriteria(criteria);

			Update update = new Update();
			update.set("content", classStudent.getContent());

			mongoDBBaseDao.getMongoTemplate().updateFirst(query, update, EbpClassStudent.class);
			// classStudentDao.update(classStudent);
		}

		this.addOrUpdateClassStudentContentCache(classStudent);
	}

	private void addOrUpdateClassStudentContentCache(EbpClassStudent classStudent) {
		try {
			// String content = (String)
			// CacheContainer.whalinMemcachedCache(EBP_CLASS_STUDENT).get(EBP_CLASS_STUDENT
			// + XMemCached.KEY_SEPERATER2 + classStudent.getPlatformCode() +
			// "_" + classStudent.getClassId());

			String content = (String) memcachedClient.get(EBP_CLASS_STUDENT + XMemCached.KEY_SEPERATER2
					+ classStudent.getPlatformCode() + "_" + classStudent.getClassId());

			if (content == null) {

				memcachedClient.add(EBP_CLASS_STUDENT + XMemCached.KEY_SEPERATER2 + classStudent.getPlatformCode() + "_"
						+ classStudent.getClassId(), classStudent.getContent());

				// CacheContainer.whalinMemcachedCache(EBP_CLASS_STUDENT).add(EBP_CLASS_STUDENT
				// + XMemCached.KEY_SEPERATER2
				// + classStudent.getPlatformCode() + "_" +
				// classStudent.getClassId(), classStudent.getContent());
			} else {
				memcachedClient.replace(EBP_CLASS_STUDENT + XMemCached.KEY_SEPERATER2 + classStudent.getPlatformCode()
						+ "_" + classStudent.getClassId(), classStudent.getContent());

				// CacheContainer.whalinMemcachedCache(EBP_CLASS_STUDENT).replace(EBP_CLASS_STUDENT
				// + XMemCached.KEY_SEPERATER2 + classStudent.getPlatformCode()
				// + "_" + classStudent.getClassId(),
				// classStudent.getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getClassStudentContentCache(EbpClassStudent classStudent) {
		String content = null;
		try {
			content = (String) memcachedClient.get(EBP_CLASS_STUDENT + XMemCached.KEY_SEPERATER2
					+ classStudent.getPlatformCode() + "_" + classStudent.getClassId());

			// content = (String)
			// CacheContainer.whalinMemcachedCache(EBP_CLASS_STUDENT).get(EBP_CLASS_STUDENT
			// + XMemCached.KEY_SEPERATER2 + classStudent.getPlatformCode() +
			// "_" + classStudent.getClassId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
