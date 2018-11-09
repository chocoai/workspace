package com.whty.assis.basicdata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.OrderDao;
import com.whty.assis.basicdata.dao.OrderSchoolDao;
import com.whty.assis.basicdata.dao.SchoolClassDao;
import com.whty.assis.basicdata.dao.SchoolDao;
import com.whty.assis.basicdata.dao.SchoolUserClassDao;
import com.whty.assis.basicdata.dao.SchoolUserDao;
import com.whty.assis.basicdata.model.OrderSchool;
import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.model.SchoolAreaTreeNode;
import com.whty.assis.basicdata.model.SchoolClass;
import com.whty.assis.basicdata.service.SchoolService;
import com.whty.assis.manage.model.SysErrorLog;
import com.whty.assis.manage.utils.ExceptionUtils;
import com.whty.assis.manage.utils.HttpUtils;
import com.whty.common.aaa.ThreadSysErrorLog;
import com.whty.common.cache.data.CacheDataUtils;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolDao schoolDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderSchoolDao orderSchoolDao;

	@Autowired
	private SchoolUserDao schoolUserDao;

	@Autowired
	private SchoolClassDao schoolClassDao;

	@Autowired
	private SchoolUserClassDao schoolUserClassDao;

	@Override
	public void save(School school) {
		schoolDao.save(school);
	}

	@Override
	public List<School> listByCondition(Map<String, Object> paramMap) {
		return schoolDao.listByCondition(paramMap);
	}

	@Override
	public HandlerResult querySchoolPage(Map<String, Object> paramMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(schoolDao.listByCondition(paramMap));
		return rs;
	}

	@Override
	public void update(School school) {
		schoolDao.update(school);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.SchoolService#listByConditionByAreaCode(
	 * java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> listByConditionByAreaCode(Map<String, Object> paraMap) {
		return schoolDao.listByConditionByAreaCode(paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.SchoolService#listSchooleMap(java.util.
	 * Map)
	 */
	@Override
	public List<Map<String, Object>> listSchooleMap(Map<String, Object> param) {
		return schoolDao.listSchoolMap(param);
	}

	private SchoolAreaTreeNode getProvince(School school) {
		SchoolAreaTreeNode bean = null;
		if (school.getProvinceCode() != null) {
			bean = new SchoolAreaTreeNode();
			bean.setId(school.getProvinceCode());
			bean.setName(school.getProvinceName());

			if (school.getCityCode() != null) {
				SchoolAreaTreeNode subNode = new SchoolAreaTreeNode();
				subNode.setId(school.getCityCode());
				subNode.setName(school.getCityName());

				List<SchoolAreaTreeNode> subList = new ArrayList<SchoolAreaTreeNode>();
				subList.add(subNode);
				bean.setSubNodeList(subList);
			}
		}
		return bean;
	}

	private SchoolAreaTreeNode getCity(School school) {
		SchoolAreaTreeNode bean = null;
		if (school.getCityCode() != null) {
			bean = new SchoolAreaTreeNode();
			bean.setId(school.getCityCode());
			bean.setName(school.getCityName());

			if (school.getAreaCode() != null) {
				SchoolAreaTreeNode subNode = new SchoolAreaTreeNode();
				subNode.setId(school.getCityCode());
				subNode.setName(school.getCityName());

				List<SchoolAreaTreeNode> subList = new ArrayList<SchoolAreaTreeNode>();
				subList.add(subNode);
				bean.setSubNodeList(subList);
			}
		}
		return bean;
	}

	private SchoolAreaTreeNode getArea(School school) {
		SchoolAreaTreeNode bean = null;
		if (school.getCityCode() != null) {
			bean = new SchoolAreaTreeNode();
			bean.setId(school.getAreaCode());
			bean.setName(school.getAreaName());

			SchoolAreaTreeNode subNode = new SchoolAreaTreeNode();
			subNode.setId(Integer.toString(school.getId()));
			subNode.setName(school.getName());

			List<SchoolAreaTreeNode> subList = new ArrayList<SchoolAreaTreeNode>();
			subList.add(subNode);
			bean.setSubNodeList(subList);
		}
		return bean;
	}

	private SchoolAreaTreeNode getSchoolParam(School school) {
		SchoolAreaTreeNode bean = new SchoolAreaTreeNode();
		bean.setId(Integer.toString(school.getId()));
		bean.setName(school.getName());
		return bean;
	}

	@Override
	public String listOgra(Map<String, Object> map, String start, String end, String url) {
		map.put("start", start);
		map.put("end", end);

		String answer = null;
		try {
			answer = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public JSONArray listSchool(String url, String platformCode, String province, String city, String orgName) {
		JSONArray resultArray = new JSONArray();
		String schoolStr;
		try {
			schoolStr = CacheDataUtils.getData("schoolList", platformCode + "_" + province + "_" + city);
			if (schoolStr != null && schoolStr.length() > 0) {
				JSONArray qq = JSONArray.fromObject(schoolStr);
				for (int i = 0; i < qq.size(); i++) {
					JSONObject school = (JSONObject) qq.get(i);

					String schoolOrgCode = school.optString("orgacode");
					String schoolOrgName = school.optString("organame");

					if (schoolOrgName.contains(orgName)) {
						JSONObject bb = new JSONObject();
						bb.put("id", schoolOrgCode);
						bb.put("text", schoolOrgName);
						resultArray.add(bb);
					}
				}
				return resultArray;
			}
		} catch (Exception e) {
			ThreadSysErrorLog.add(new SysErrorLog(Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ExceptionUtils.getExceptionStackTrace(e),
					new Date(), null, null));
		}

		JSONArray schoolJson = new JSONArray();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", "1");
		param.put("provincecode", province);
		param.put("areacode", city);

		int start = 0;
		int end = 40;

		String answer = listOgra(param, Integer.toString(start), Integer.toString(end), url);

		String result = JSONObject.fromObject(answer).optString("result");
		if ("000000".equals(result)) {
			int count = JSONObject.fromObject(answer).optInt("count");
			String answerStr = listOgra(param, Integer.toString(start), Integer.toString(count), url);

			JSONArray dataJsonArray = JSONObject.fromObject(answerStr).optJSONArray("organ");

			if (dataJsonArray.size() > 0) {
				for (int i = 0; i < dataJsonArray.size(); i++) {
					JSONObject data = dataJsonArray.optJSONObject(i);
					Map<String, Object> schoolMap = new HashMap<String, Object>();
					schoolMap.put("orgacode", data.optString("orgaid"));
					schoolMap.put("organame", data.optString("organame"));
					schoolJson.add(schoolMap);
				}
			}
		}
		try {

			CacheDataUtils.setObjectData("schoolList", platformCode + "_" + province + "_" + city,
					schoolJson.toString());

			for (int i = 0; i < schoolJson.size(); i++) {
				JSONObject school = (JSONObject) schoolJson.get(i);
				String schoolOrgName = school.optString("organame");
				String schoolOrgCode = school.optString("orgacode");

				if (schoolOrgName.contains(orgName)) {
					JSONObject bb = new JSONObject();
					bb.put("id", schoolOrgCode);
					bb.put("text", schoolOrgName);
					resultArray.add(bb);
				}
			}
		} catch (Exception e) {
			ThreadSysErrorLog.add(new SysErrorLog(Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ExceptionUtils.getExceptionStackTrace(e),
					new Date(), null, null));
		}
		return resultArray;
	}

	/*
	 * @Title: getSchools
	 * 
	 * @return
	 */
	@Override
	public List<School> getSchools(Integer provinceId, Integer cityId, Integer areaId) {
		return schoolDao.getSchools(provinceId, cityId, areaId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.SchoolService#loadById(java.lang.
	 * Integer)
	 */
	@Override
	public School loadById(Integer id) {
		return schoolDao.loadById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.SchoolService#update(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public String update(HttpServletRequest request) {
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String provinceCode = request.getParameter("provinceCode");
		String cityCode = request.getParameter("cityCode");
		String areaCode = request.getParameter("areaCode");
		String contacts = request.getParameter("contacts");
		String id = request.getParameter("id");
		String webUrl = request.getParameter("webUrl");
		School school = schoolDao.loadById(Integer.valueOf(id));

		if (StringUtils.isNotEmpty(webUrl)) {
			school.setWebUrl(webUrl);
		}

		if (StringUtils.isNotEmpty(address)) {
			school.setAddress(address);
		}

		if (StringUtils.isNotEmpty(mobile)) {
			school.setMobile(mobile);
		}

		if (StringUtils.isNotEmpty(email)) {
			school.setEmail(email);
		}

		if (StringUtils.isNotEmpty(provinceCode)) {
			school.setProvinceCode(provinceCode);
		}

		if (StringUtils.isNotEmpty(cityCode)) {
			school.setCityCode(cityCode);
		}

		if (StringUtils.isNotEmpty(areaCode)) {
			school.setAreaCode(areaCode);
		}

		if (StringUtils.isNotEmpty(contacts)) {
			school.setContacts(contacts);
		}

		schoolDao.update(school);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.SchoolService#delete(java.lang.Integer)
	 */
	@Override
	public String delete(Integer schoolId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("schoolId", schoolId);

		List<OrderSchool> orderSchoolList = orderSchoolDao.listByCondition(paramMap);

		if (orderSchoolList != null && orderSchoolList.size() > 0) {
			return "学校下有订单，不能删除";
		}

		schoolUserDao.deleteBySchoolId(schoolId);// 删除学校用户

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", schoolId);
		List<SchoolClass> schoolClassList = schoolClassDao.listByCondition(paramMap);

		for (SchoolClass schoolClass : schoolClassList) {
			schoolUserClassDao.deleteBySchoolClassId(schoolClass.getId());// 删除班级用户表
			schoolClassDao.deleteById(schoolClass.getId());// 删除学校班级表
		}

		schoolDao.deleteById(schoolId);// 删除学校表

		return SysConstants.SUCCESS;
	}

	/* 
	 * @Title: 查询所有学校
	 * @return 
	 */ 
	@Override
	public List<School> getAllSchools() {
		return schoolDao.getAllSchools();
	}

}
