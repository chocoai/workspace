/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.ClassClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.IdDao;
import com.whty.assis.basicdata.dao.SchoolClassDao;
import com.whty.assis.basicdata.dao.SchoolUserClassDao;
import com.whty.assis.basicdata.dao.SchoolUserDao;
import com.whty.assis.basicdata.model.SchoolClass;
import com.whty.assis.basicdata.model.SchoolUser;
import com.whty.assis.basicdata.model.SchoolUserClass;
import com.whty.assis.basicdata.service.SchoolClassService;
import com.whty.assis.manage.dao.BasePropertyDao;
import com.whty.common.util.HttpUtils;
import com.whty.common.util.SysConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年5月11日
 */
@Service
public class SchoolClassServiceImpl implements SchoolClassService {

	@Autowired
	private SchoolClassDao schoolClassDao;

	@Autowired
	private SchoolUserDao schoolUserDao;

	@Autowired
	private IdDao idDao;

	@Autowired
	private SchoolUserClassDao schoolUserClassDao;

	@Autowired
	private BasePropertyDao basePropertyDao;
	
	@Override
	public List<SchoolClass> getSchoolClass(Map<String, Object> param) {
		return schoolClassDao.listByCondition(param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.SchoolClassService#writePerson()
	 */
	@Override
	public void writePerson(String platformCode ,String orgaId,Integer schoolId) throws MalformedURLException, IOException {
//		String url = "http://116.211.105.46:22007/aamif/rest/classinfo/query";// 武汉

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("property_key", "aamUrl");
		map.put("platform_code", platformCode);
		String aamUrl = basePropertyDao.getPropertyValue(map);
		
		aamUrl =aamUrl+"classinfo/query";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgaid", orgaId);

		String result = HttpUtils.doPost(aamUrl, JSONObject.fromObject(param).toString());

		JSONObject resultJson = JSONObject.fromObject(result);

		if (resultJson.optString("result").equals("000000")) {

			JSONArray classinfolist = resultJson.optJSONArray("classinfolist");

			if (classinfolist != null && classinfolist.size() > 0) {

				for (int i = 0; i < classinfolist.size(); i++) {
					JSONObject classinfo = classinfolist.optJSONObject(i);

					String aamClassId = classinfo.optString("classid");
					String classname = classinfo.optString("classname");
					String classType = classinfo.optString("classtype");

					String grade = classinfo.optString("grade");
					Map<String, Object> idParam = new HashMap<String, Object>(2);
					idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
					idParam.put("tableName", "t_school_class");

					Integer schoolClassId = idDao.getId(idParam);

					SchoolClass schoolClass = new SchoolClass();
					schoolClass.setAamClassId(aamClassId);
					schoolClass.setClassName(classname);
					schoolClass.setClassType(classType);
					schoolClass.setFoundTime(classinfo.optString("foundtime"));
					schoolClass.setGradeClass(classinfo.optString("gradeclass"));
					schoolClass.setEdusysType(classinfo.optString("edusystype"));
					schoolClass.setSchoolId(schoolId);
					schoolClass.setCreateTime(new Date());
					schoolClass.setUpdateTime(new Date());
					schoolClass.setGradeId(Integer.valueOf(grade));
					schoolClassDao.save(schoolClass);

					JSONArray classmembers = classinfo.optJSONArray("classmembers");
					
					for (int j = 0; j < classmembers.size(); j++) {
						JSONObject classmember = classmembers.optJSONObject(j);

						String aamPersonId = classmember.optString("personid");
						String name = classmember.optString("name");

						Map<String, Object> schoolUserParam = new HashMap<String, Object>(2);
						schoolUserParam.put("databaseName", SysConfig.getStrValue("databaseName"));
						schoolUserParam.put("tableName", "t_school_user");

						Integer schoolUserId = idDao.getId(schoolUserParam);

						SchoolUser schoolUser = new SchoolUser();
						schoolUser.setUserType(0);
						schoolUser.setAamPersonId(aamPersonId);
						schoolUser.setName(name);
						schoolUser.setCreateTime(new Date());
						schoolUser.setUpdateTime(new Date());
						schoolUser.setSchoolId(schoolId);
						schoolUserDao.save(schoolUser);

						SchoolUserClass schoolUserClass = new SchoolUserClass();
						schoolUserClass.setSchoolClassId(schoolClassId);
						schoolUserClass.setSchoolUserId(schoolUserId);
						schoolUserClass.setAamClassId(aamClassId);
						schoolUserClass.setAamPersonId(aamPersonId);

						schoolUserClassDao.save(schoolUserClass);

					}
				}
			}
		}
	}

}
