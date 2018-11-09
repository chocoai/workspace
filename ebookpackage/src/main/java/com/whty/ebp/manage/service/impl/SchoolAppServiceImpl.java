/**
 * 
 */
package com.whty.ebp.manage.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.common.cache.data.CacheDataUtils;
import com.whty.ebp.api.utils.HttpUtils;
import com.whty.ebp.manage.dao.SchoolAppDao;
import com.whty.ebp.manage.model.SchoolApp;
import com.whty.ebp.manage.service.SchoolAppService;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年10月23日
 */
@Service
public class SchoolAppServiceImpl implements SchoolAppService {

	@Autowired
	private SchoolAppDao schoolAppDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.ebp.manage.service.SchoolAppService#querySchoolAppPage(java.util
	 * .Map)
	 */
	@Override
	public HandlerResult querySchoolAppPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(schoolAppDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.ebp.manage.service.SchoolAppService#save(com.whty.ebp.manage.
	 * model.SchoolApp)
	 */
	@Override
	public void save(SchoolApp bean) {
		schoolAppDao.save(bean);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.ebp.manage.service.SchoolAppService#upate(com.whty.ebp.manage.
	 * model.SchoolApp)
	 */
	@Override
	public void update(SchoolApp bean) {
		schoolAppDao.update(bean);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.ebp.manage.service.SchoolAppService#detail(com.whty.ebp.manage.
	 * model.SchoolApp)
	 */
	@Override
	public SchoolApp detail(Integer id) {
		return schoolAppDao.loadById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.ebp.manage.service.SchoolAppService#listSchool(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
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
			e.printStackTrace();
			// ThreadSysErrorLog.add(new
			// SysErrorLog(Thread.currentThread().getStackTrace()[1].getClassName(),
			// Thread.currentThread().getStackTrace()[1].getMethodName(),
			// ExceptionUtils.getExceptionStackTrace(e),
			// new Date(), null, null));
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
			e.printStackTrace();
			// ThreadSysErrorLog.add(new
			// SysErrorLog(Thread.currentThread().getStackTrace()[1].getClassName(),
			// Thread.currentThread().getStackTrace()[1].getMethodName(),
			// ExceptionUtils.getExceptionStackTrace(e),
			// new Date(), null, null));
		}
		return resultArray;
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

	/* (non-Javadoc)
	 * @see com.whty.ebp.manage.service.SchoolAppService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		schoolAppDao.deleteById(id);
		
	}
}
