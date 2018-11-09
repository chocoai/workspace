package com.whty.mxbj.api.service.impl;

import java.security.DigestException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.mxbj.api.dao.SubjectDao;
import com.whty.mxbj.api.model.Subject;
import com.whty.mxbj.api.service.SubjectService;
import com.whty.mxbj.common.constants.ResultConstants;
import com.whty.mxbj.common.utils.HttpUtils;
import com.whty.mxbj.common.utils.Sha1;
import com.whty.mxbj.common.utils.SysConfigUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component("subjectService")
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;

	@Override
	public Map<String, Object> getSubjectInfos() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", subjectDao.listAll());
		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

	@Override
	public void synTask() {

		try {
//			List<Subject> subjectList = subjectDao.listAll();

			String rop_app_key = SysConfigUtils.getStrValue("rop.app.key");
			String rop_app_value = SysConfigUtils.getStrValue("rop.app.value");
			String rop_app_version = SysConfigUtils.getStrValue("rop.app.subjec.version");

			Map<String, Object> aa = new HashMap<String, Object>();
			aa.put("appKey", rop_app_key);
			aa.put("messageFormat", "json");
			aa.put("method", "cms.queryPeriodSubject");
			aa.put("v", rop_app_version);

			String key = new StringBuffer().append(rop_app_value).append(Sha1.getOrderByLexicographic(aa))
					.append(rop_app_value).toString();
			String sign = Sha1.SHA1(key);

			StringBuffer sb = new StringBuffer();
			sb.append("appKey=").append(rop_app_key).append("&messageFormat=json&method=cms.queryPeriodSubject&v=")
					.append(rop_app_version).append("&sign=").append(sign);
			String rop_url = SysConfigUtils.getStrValue("rop.url") + "?" + sb.toString();
			String result = HttpUtils.getInstance().httpGet(rop_url);
			System.out.println(result);

			JSONArray json = JSONObject.fromObject(result).optJSONArray("list");

			if (json != null && json.size() > 0) {
				for (int i = 0; i < json.size(); i++) {
					Subject subject = new Subject();
					subject.setSubjectId(json.optJSONObject(i).optString("subject_id"));
					subject.setSubjectName(json.optJSONObject(i).optString("subject_name"));
					saveOrUpdate(subject);
				}
			}
		} catch (DigestException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveOrUpdate(Subject subject) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectId", subject.getSubjectId());

		Subject bean = subjectDao.getSubject(map);

		if (bean == null) {
			subjectDao.save(subject);
		} else {
			subjectDao.update(subject);
		}
	}

}
