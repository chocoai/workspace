/**
 * 
 */
package com.whty.wfd.page.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.wfd.common.utils.HttpUtils;
import com.whty.wfd.page.dao.IdDao;
import com.whty.wfd.page.dao.TSchoolMapper;
import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.model.TSchool;
import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.service.BasePropertyService;
import com.whty.wfd.page.service.SynUserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年8月16日
 */
@Service
public class SynUserServiceImpl implements SynUserService {

	@Autowired
	private TUserMapper tUserMapper;

	@Autowired
	private TSchoolMapper tSchoolMapper;

	@Autowired
	private IdDao idDao;

	@Autowired
	private BasePropertyService basePropertyService;

	public void synUser(String platformCode, String orgaId, String aamUrl, String schoolName) {

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", "wfd");
		idParam.put("tableName", "t_school");
		Integer schoolId = idDao.getId(idParam);

		TSchool tschool = new TSchool();
		tschool.setName(schoolName);
		tschool.setCreateTime(new Date());
		tschool.setUpdateTime(new Date());
		tschool.setOrgaId(orgaId);
		tschool.setPlatformCode(platformCode);
		tSchoolMapper.insert(tschool);

		aamUrl = aamUrl + "/member/query";

		int start = 0;
		int end = 100;

		int pageSize = 100;
		int totalPageNo = 0;// 当且页面

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("groupid", orgaId);
		param.put("type", "[\"0\",\"1\"]");

		String answerStr = querySchoolUser(param, aamUrl, start, end);

		String result = JSONObject.fromObject(answerStr).optString("result");

		if ("000000".equals(result)) {
			JSONArray userInfolist = JSONObject.fromObject(answerStr).optJSONArray("userinfolist");
			// 计算共多少页
			int totalCount = JSONObject.fromObject(answerStr).optInt("count");

			totalPageNo = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

			for (int i = 0; i < totalPageNo; i++) {
				String subAnswerUrl = querySchoolUser(param, aamUrl, start, end);

				JSONArray subUserInfolist = JSONObject.fromObject(subAnswerUrl).optJSONArray("userinfolist");

				for (int j = 0; j < subUserInfolist.size(); j++) {
					JSONObject userInfo = subUserInfolist.optJSONObject(j);
					TUser bean = new TUser();
					bean.setAccount(userInfo.optString("account"));
					bean.setPersonId(userInfo.optString("personid"));
					bean.setUserType(userInfo.optString("usertype"));
					bean.setCreateTime(new Date());
					bean.setUpdateTime(new Date());
					bean.setName(userInfo.optString("name"));
					bean.setPlatformCode(userInfo.optString("platformCode"));
					bean.setSchoolId(schoolId);

					JSONArray userlogolist = userInfo.getJSONArray("userlogolist");

					String userLogo = "http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/img_narmal.png";

					if (userlogolist.size() > 0) {
						for (int k = 0; k < userlogolist.size(); k++) {
							JSONObject userlogoJsonObject = userlogolist.optJSONObject(k);
							if ("2".equals(userlogoJsonObject.optString("logotype"))) {
								userLogo = userlogoJsonObject.optString("logourl");
								break;
							}
						}
					}
					bean.setLogoUrl(userLogo);
					bean.setOrgaId(orgaId);

					tUserMapper.insert(bean);

				}
				start = start + pageSize;
				end = end + pageSize;
			}
		}
	}

	public JSONArray querySchoolTeacher(Map<String, Object> param, String url, String start, String end) {
		JSONArray resultJson = new JSONArray();
		param.put("start", start);
		param.put("end", end);

		try {
			String answer = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(param).toString());
			String result = JSONObject.fromObject(answer).optString("result");

			if ("000000".equals(result)) {
				resultJson = JSONObject.fromObject(answer).optJSONArray("userinfolist");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
	}

	public String querySchoolUser(Map<String, Object> param, String url, int start, int end) {
		param.put("start", start);
		param.put("end", end);
		String answer = null;
		try {
			answer = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(param).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}
}
