package com.whty.common.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.assis.api.model.BookPageStudent;
import com.whty.assis.api.utils.HttpUtils;
import com.whty.common.cache.data.GetCacheBaseData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpUser {

	// private static Logger logger = Logger.getLogger(HttpUser.class);
	// private static Logger logger = Logger.getLogger(HttpUser.class);
	private static Logger logger = LoggerFactory.getLogger(HttpUser.class);

	/**
	 * 获取班级学生
	 * 
	 * @param classId
	 * @return
	 */
	public static List<BookPageStudent> getClassStudentList(String classId, String platformCode) {
		List<BookPageStudent> studentList = new ArrayList<BookPageStudent>();
		// String requestUrl =
		// SysConfig_ebp.getStrValue(platformCode+"_platform_url")+"queryClassStudentReq";
		String requestUrl = GetCacheBaseData.getPropertyValue("platform_url", platformCode) + "queryClassStudentReq";
		JSONObject params = new JSONObject();
		params.put("classid", classId);
		params.put("start", "0");
		params.put("end", String.valueOf(Integer.MAX_VALUE));

		// logger.info("请求3A地址" + classId + ":" + requestUrl);

		String json = "";
		try {
			// json = HttpUtils.getInstance().httpPost(requestUrl,
			// params.toString());
			json = HttpUtils.getInstance().httpPost(requestUrl, params.toString(), 30000, 30000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// logger.info("请求结果：" + json);
		JSONObject result = JSONObject.fromObject(json);
		if ("000000".equals(result.get("result"))) {
			JSONArray list = JSONArray.fromObject(result.get("userinfo").toString());
			BookPageStudent student = null;
			for (int i = 0; i < list.size(); i++) {
				student = new BookPageStudent();
				JSONObject o = JSONObject.fromObject(list.get(i).toString());
				student.setStudentId(o.get("personid").toString());
				student.setStudentName(o.get("name").toString());
				studentList.add(student);
			}
		}
		return studentList;
	}
}
