/**
 * 
 */
package com.whty.wfd.page.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.wfd.common.utils.GUIDGenerator;
import com.whty.wfd.common.utils.HttpUtils;
import com.whty.wfd.page.dao.IdDao;
import com.whty.wfd.page.dao.TClassMapper;
import com.whty.wfd.page.dao.TClassPlateMapper;
import com.whty.wfd.page.dao.TClassUserMapper;
import com.whty.wfd.page.dao.TSchoolMapper;
import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.model.TClass;
import com.whty.wfd.page.model.TClassPlate;
import com.whty.wfd.page.model.TClassPlateExample;
import com.whty.wfd.page.model.TClassUser;
import com.whty.wfd.page.model.TClassUserExample;
import com.whty.wfd.page.model.TPlateUserExample;
import com.whty.wfd.page.model.TSchool;
import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.model.TUserExample;
import com.whty.wfd.page.service.BasePropertyService;
import com.whty.wfd.page.service.ManagerService;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年8月20日
 */
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private MemcachedClient memcachedClient;

	@Autowired
	private TUserMapper tuserMapper;

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private TUserMapper tUserMapper;

	@Autowired
	private TClassPlateMapper tClassPlateMapper;

	@Autowired
	private TClassUserMapper tClassUserMapper;

	@Autowired
	private IdDao idDao;

	@Autowired
	private TClassMapper tClassMapper;

	@Autowired
	private TSchoolMapper tSchoolMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.wfd.page.service.ManagerService#getClassByUserId(java.lang.
	 * Integer)
	 */
	@Override
	public JSONArray getClassByUserId(Integer userId) {
		JSONArray resultJson = new JSONArray();

		TUser user = tuserMapper.selectByPrimaryKey(userId);

		if(StringUtils.isEmpty(user.getOrgaId())){
			return resultJson;
		}
		
		TSchool bean = tSchoolMapper.selectByPrimaryKey(user.getSchoolId());

		String aamUrl = basePropertyService.getPropertyValue("aamUrl", bean.getPlatformCode()) + "/classinfo/query";

		int start = 0;
		int end = 100;
		int pageSize = 100;
		int totalPageNo = 0;// 当且页面

		Map<String, Object> schoolParam = new HashMap<String, Object>();
		schoolParam.put("orgaid", user.getOrgaId());
		String answerStr = getClassByUserId(schoolParam, aamUrl, start, end);

		String result = JSONObject.fromObject(answerStr).optString("result");

		// JSONObject resultJsonObject = JSONObject.fromObject(result);
		if (result.equals("000000")) {
			// 计算共多少页
			int totalCount = JSONObject.fromObject(answerStr).optInt("count");
			totalPageNo = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

			for (int i = 0; i < totalPageNo; i++) {
				String subAnswerUrl = getClassByUserId(schoolParam, aamUrl, start, end);

				JSONArray classinfolist = JSONObject.fromObject(subAnswerUrl).optJSONArray("classinfolist");

				for (int j = 0; j < classinfolist.size(); j++) {
					JSONObject classJsonObject = classinfolist.getJSONObject(j);

					String classId = classJsonObject.optString("classid");
					String className = classJsonObject.optString("classname");
					Map<String, Object> classParam = new HashMap<String, Object>();
					classParam.put("classId", classId);
					classParam.put("className", className);
					resultJson.add(classParam);
				}
				start = start + pageSize;
				end = end + pageSize;
			}

		}

		// TODO
		return resultJson;
	}

	/**
	 * @param schoolParam
	 * @param aamUrl
	 * @param start
	 * @param end
	 * @return
	 */
	private String getClassByUserId(Map<String, Object> param, String aamUrl, int start, int end) {
		param.put("start", start);
		param.put("end", end);
		String answer = null;
		try {
			answer = HttpUtils.getInstance().httpPost(aamUrl, JSONObject.fromObject(param).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.wfd.page.service.ManagerService#getStudentByClassId(java.lang.
	 * String, java.lang.Integer)
	 */
	@Override
	public JSONArray getStudentByClassId(String aamClassId, Integer userId) {
		JSONArray resultJson = new JSONArray();
		TUser user = tuserMapper.selectByPrimaryKey(userId);

		TSchool school = tSchoolMapper.selectByPrimaryKey(user.getSchoolId());

		String aamUrl = basePropertyService.getPropertyValue("aamUrl", school.getPlatformCode())
				+ "/class/queryMemberOrderByLoginTimes";
		Map<String, Object> schoolParam = new HashMap<String, Object>();
		// schoolParam.put("orgaid", user.getOrgaId());

		schoolParam.put("classId", aamClassId);
		schoolParam.put("start", 0);
		schoolParam.put("end", 100);

		String result = HttpUtils.getInstance().httpPost(aamUrl, JSONObject.fromObject(schoolParam).toString());

		JSONObject resultJsonObject = JSONObject.fromObject(result);

		if (resultJsonObject.optString("result").equals("000000")) {
			JSONArray userinfolist = resultJsonObject.optJSONArray("userinfolist");
			if (userinfolist != null) {
				Map<String, Object> studentParam = null;
				for (int i = 0; i < userinfolist.size(); i++) {
					JSONObject userJsonObject = userinfolist.getJSONObject(i);

					studentParam = new HashMap<String, Object>(3);

					studentParam.put("studnetId", userJsonObject.optString("personid"));
					studentParam.put("studentName", userJsonObject.optString("name"));

					JSONArray userlogolist = userJsonObject.optJSONArray("userlogolist");

					String userLogo = "http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_narmal.png";
					if (userlogolist.size() > 0) {
						for (int j = 0; j < userlogolist.size(); j++) {
							JSONObject userlogoJsonObject = userlogolist.optJSONObject(j);
							if ("2".equals(userlogoJsonObject.optString("logotype"))) {
								userLogo = userlogoJsonObject.optString("logourl");
								break;
							}
						}
					}

					studentParam.put("platformCode", user.getPlatformCode());
					studentParam.put("studentLogo", userLogo);
					resultJson.add(studentParam);
				}
			}
		}
		return resultJson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.wfd.page.service.ManagerService#getStudentByStudentName(java.
	 * lang.String, java.lang.Integer)
	 */
	@Override
	public List<TUser> getStudentByStudentName(String studentName, Integer userId) {
		TUser user = tuserMapper.selectByPrimaryKey(userId);

		List<TUser> list = new ArrayList<TUser>();

		JSONArray studentList = new JSONArray();
		try {
			String key = user.getSchoolId() + "_" + user.getPlatformCode() + "_studentList";
			studentList = memcachedClient.get(key);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}

		if (studentList != null) {
			for (int i = 0; i < studentList.size(); i++) {
				JSONObject jsonObject = studentList.optJSONObject(i);
				TUser bean = new TUser();

				String name = jsonObject.optString("studentName");

				if (name.contains(studentName)) {
					String logoUrl = jsonObject.optString("studentLogo");
					String platformCode = jsonObject.optString("platformCode");

					bean.setPersonId(jsonObject.optString("studnetId"));
					bean.setName(name);
					bean.setLogoUrl(logoUrl);
					bean.setPlatformCode(platformCode);
					list.add(bean);
				}

			}
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.wfd.page.service.ManagerService#saveClass(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public void saveClass(HttpServletRequest request) throws Exception {
		String studentStr = request.getParameter("studentList");
		String[] plateStr = request.getParameterValues("plateList");
		String className = request.getParameter("className");
		String userId = request.getParameter("userId");

		String classId = request.getParameter("classId");

		TClass classBean = tClassMapper.selectByPrimaryKey(classId);

		TUser user = tUserMapper.selectByPrimaryKey(Integer.valueOf(userId));

		if (classBean == null) {// 创建
			classBean = new TClass();
			classBean.setId(classId);
			classBean.setCreateTime(new Date());
			classBean.setUpdateTime(new Date());
			classBean.setSchoolId(user.getSchoolId());
			classBean.setCreator(user.getId());
			classBean.setName(className);
			tClassMapper.insert(classBean);

			if (plateStr != null) {
				for (int i = 0; i < plateStr.length; i++) {
					String plateId = plateStr[i];

					TClassPlate classPlate = new TClassPlate();
					classPlate.setClassId(classId);
					classPlate.setPlateId(plateId);
					classPlate.setCreateTime(new Date());
					classPlate.setUpdateTime(new Date());

					tClassPlateMapper.insert(classPlate);
				}
			}

			if (StringUtils.isNotEmpty(studentStr)) {
				JSONArray studentList = JSONArray.fromObject(studentStr);
				saveStudent(studentList, user, classId);
			}
		} else {// 更新
			classBean.setUpdateTime(new Date());
			classBean.setName(className);
			tClassMapper.updateByPrimaryKey(classBean);

			// 删除原来的
			TClassUserExample classUserExample = new TClassUserExample();
			classUserExample.createCriteria().andClassIdEqualTo(classId);
			tClassUserMapper.deleteByExample(classUserExample);

			TClassPlateExample classPlateExample = new TClassPlateExample();
			classPlateExample.createCriteria().andClassIdEqualTo(classId);
			tClassPlateMapper.deleteByExample(classPlateExample);

			if (plateStr != null) {
				for (int i = 0; i < plateStr.length; i++) {
					String plateId = plateStr[i];

					TClassPlate classPlate = new TClassPlate();
					classPlate.setClassId(classId);
					classPlate.setPlateId(plateId);
					classPlate.setCreateTime(new Date());
					classPlate.setUpdateTime(new Date());

					tClassPlateMapper.insert(classPlate);
				}
			}

			if (StringUtils.isNotEmpty(studentStr)) {
				JSONArray studentList = JSONArray.fromObject(studentStr);
				saveStudent(studentList, user, classId);
			}
		}
	}

	public void saveStudent(JSONArray studentList, TUser user, String classId) {
		for (int i = 0; i < studentList.size(); i++) {
			JSONObject student = studentList.optJSONObject(i);
			String personid = student.optString("id");

			TUserExample userExample = new TUserExample();
			userExample.createCriteria().andPersonIdEqualTo(personid).andPlatformCodeEqualTo(user.getPlatformCode());

			List<TUser> users = tuserMapper.selectByExample(userExample);
			Integer studentId = null;
			if (users.size() == 0) {

				String aamUrl = basePropertyService.getPropertyValue("aamUrl", user.getPlatformCode()) + "/user/"
						+ personid;

				String result;
				try {
					result = HttpUtils.getInstance().httpGet(aamUrl);
					JSONObject resultJsonObject = JSONObject.fromObject(result);

					if (resultJsonObject.optString("result").equals("000000")) {
						JSONObject userinfo = resultJsonObject.optJSONObject("userinfo");

						TUser tUser = new TUser();
						tUser.setName(userinfo.optString("name"));
						tUser.setUserType(userinfo.optString("usertype"));
						tUser.setPlatformCode(userinfo.optString("platformCode"));
						tUser.setSchoolId(user.getSchoolId());
						tUser.setAccount(userinfo.optString("account"));
						tUser.setCreateTime(new Date());
						tUser.setUpdateTime(new Date());
						tUser.setOrgaId(userinfo.optString("orgaid"));
						tUser.setPersonId(userinfo.optString("personid"));
						tUser.setLoginPlatformCode(user.getLoginPlatformCode());
						JSONArray userlogolist = userinfo.optJSONArray("userlogolist");
						String userLogo = "http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_narmal.png";
						if (userlogolist.size() > 0) {
							for (int j = 0; j < userlogolist.size(); j++) {
								JSONObject userlogoJsonObject = userlogolist.optJSONObject(j);
								if ("2".equals(userlogoJsonObject.optString("logotype"))) {
									userLogo = userlogoJsonObject.optString("logourl");
									break;
								}
							}
						}
						tUser.setLogoUrl(userLogo);

						Map<String, Object> idParam = new HashMap<String, Object>(2);
						idParam.put("databaseName", "wfd");
						idParam.put("tableName", "t_user");
						studentId = idDao.getId(idParam);

						tuserMapper.insert(tUser);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				studentId = users.get(0).getId();
			}

			TClassUser classUser = new TClassUser();
			classUser.setClassId(classId);
			classUser.setUserId(studentId);
			classUser.setCreateTime(new Date());
			classUser.setUpdateTime(new Date());
			tClassUserMapper.insert(classUser);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.wfd.page.service.ManagerService#deleteClass(java.lang.String)
	 */
	@Override
	public void deleteClass(String classId) {
		tClassMapper.deleteByPrimaryKey(classId);

		TClassPlateExample classPlateExample = new TClassPlateExample();
		classPlateExample.createCriteria().andClassIdEqualTo(classId);
		tClassPlateMapper.deleteByExample(classPlateExample);

		TClassUserExample classUserExample = new TClassUserExample();
		classUserExample.createCriteria().andClassIdEqualTo(classId);
		tClassUserMapper.deleteByExample(classUserExample);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.wfd.page.service.ManagerService#addStudentToClass(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void addStudentToClass(String classId, String studentListStr, TUser user) {
		// 先删除这个班的所有学生

		TClassUserExample bean = new TClassUserExample();
		bean.createCriteria().andClassIdEqualTo(classId);

		tClassUserMapper.deleteByExample(bean);

		JSONArray studentList = JSONArray.fromObject(studentListStr);

		for (int i = 0; i < studentList.size(); i++) {
			JSONObject student = studentList.optJSONObject(i);
			String personid = student.optString("id");

			TUserExample userExample = new TUserExample();
			userExample.createCriteria().andPersonIdEqualTo(personid);

			List<TUser> users = tuserMapper.selectByExample(userExample);
			Integer studentId = null;
			if (users.size() == 0) {
				TSchool school = tSchoolMapper.selectByPrimaryKey(Integer.valueOf(user.getSchoolId()));

				String aamUrl = basePropertyService.getPropertyValue("aamUrl", school.getPlatformCode()) + "/user/"
						+ personid;

				String result;
				try {
					result = HttpUtils.getInstance().httpGet(aamUrl);
					JSONObject resultJsonObject = JSONObject.fromObject(result);

					if (resultJsonObject.optString("result").equals("000000")) {
						JSONObject userinfo = resultJsonObject.optJSONObject("userinfo");

						TUser tUser = new TUser();
						tUser.setName(userinfo.optString("name"));
						tUser.setUserType(userinfo.optString("usertype"));
						tUser.setPlatformCode(userinfo.optString("platformCode"));
						tUser.setSchoolId(user.getSchoolId());
						tUser.setAccount(userinfo.optString("account"));
						tUser.setCreateTime(new Date());
						tUser.setUpdateTime(new Date());
						tUser.setOrgaId(userinfo.optString("orgaid"));
						tUser.setLoginPlatformCode(user.getLoginPlatformCode());
						JSONArray userlogolist = userinfo.optJSONArray("userlogolist");
						String userLogo = null;
						if (userlogolist.size() > 0) {
							for (int j = 0; j < userlogolist.size(); j++) {
								JSONObject userlogoJsonObject = userlogolist.optJSONObject(j);
								if ("1".equals(userlogoJsonObject.optString("logotype"))) {
									userLogo = userlogoJsonObject.optString("logourl");
									break;
								}
							}
						}
						tUser.setLogoUrl(userLogo);

						Map<String, Object> idParam = new HashMap<String, Object>(2);
						idParam.put("databaseName", "wfd");
						idParam.put("tableName", "t_user");
						studentId = idDao.getId(idParam);

						tuserMapper.insert(tUser);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				studentId = users.get(0).getId();
			}

			TClassUser classUser = new TClassUser();
			classUser.setClassId(classId);
			classUser.setUserId(studentId);
			classUser.setCreateTime(new Date());
			classUser.setUpdateTime(new Date());
			tClassUserMapper.insert(classUser);
		}

	}

}