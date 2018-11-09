package com.whty.assis.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.common.cache.data.GetCacheBaseData;
import com.whty.common.util.CommonFunction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户登录平台处理
 * 
 * @ClassName: LoginUtil
 * @author zhujg
 */
public class LoginUtil {
	private static Logger logger = LoggerFactory.getLogger(LoginUtil.class);
	// private static Logger logger = Logger.getLogger(LoginUtil.class);

	/**
	 * 成功状态
	 */
	private static final String SUCCESS_CODE = "000000";

	@SuppressWarnings("unchecked")
	public static Map get(String usessionId, String platformCode) throws Exception {
		Map map = new HashMap();
		if (logger.isDebugEnabled()) {
			logger.debug("请求URL：" + GetCacheBaseData.getPropertyValue("platform_url", platformCode)
					+ "user/getuserinfo/" + usessionId);
		}
		// String
		// resp=HttpUtils.doGet(SysConfig_ebp.getStrValue(platformCode+"_platform_url")+"user/getuserinfo/"+usessionId);
		// String
		// resp=HttpUtils.doGet(GetCacheBaseData.getPropertyValue("platform_url",
		// platformCode)+"user/getuserinfo/"+usessionId);
		String url = GetCacheBaseData.getPropertyValue("platform_url", platformCode) + "user/getuserinfo/" + usessionId;
		String resp = HttpUtils.getInstance().httpGet(url, 30000, 30000);
		if (logger.isDebugEnabled()) {
			logger.debug("信息返回:" + resp);
		}
		if (resp != null && !"".equals(resp)) {
			JSONObject respJson = JSONObject.fromObject(resp);
			if ("000000".equals(respJson.getString("result"))) {
				map.put("success", true);
				JSONObject userObject = (JSONObject) respJson.get("userinfo");
				map.put("userId", userObject.getString("personid"));
				// map.put("mobnum",
				// userObject.get("mobnum")==null?"":userObject.get("mobnum"));
				map.put("email", userObject.get("email") == null ? "" : userObject.get("email"));
				map.put("name", userObject.get("name") == null ? "" : userObject.get("name"));
				map.put("areacode",
						userObject.get("areacode") == null ? "" : userObject.get("areacode").toString().trim());
				// map.put("gender",
				// userObject.get("gender")==null?"":userObject.get("gender"));
				// map.put("birthday",
				// userObject.get("birthday")==null?"":userObject.get("birthday"));
				// 用户类型 0 学生; 1 老师; 2 家长 ; 3 机构; 4 学校; 5 学校工作人员 6 机构工作人员
				map.put("usertype", userObject.get("usertype"));
				// map.put("address",
				// userObject.get("address")==null?"":userObject.get("address"));
				// 当为学生时获取班级信息
				map.put("classid", userObject.get("classid") == null ? "" : userObject.get("classid"));
				map.put("classname", userObject.get("classname") == null ? "" : userObject.get("classname"));
				// JSONArray logoObject =
				// (JSONArray)userObject.get("userlogolist");
				// if(logoObject!=null&&logoObject.size()>0){
				// for(int i=0,total=logoObject.size();i<total;i++){
				// JSONObject logo = logoObject.getJSONObject(i);
				// if("1".equals(logo.get("logotype"))){
				// map.put("logourl", logo.get("logourl"));
				// }
				// }
				// }
				// map.put("idcardno",
				// userObject.get("idcardno")==null?"":userObject.get("idcardno"));

				JSONArray teachArr = (JSONArray) userObject.get("teachesubjectlist");
				JSONArray teachInfo = new JSONArray();
				JSONObject jo = new JSONObject();
				if (teachArr != null && teachArr.size() > 0) {
					for (int i = 0; i < teachArr.size(); i++) {
						JSONObject teachObj = (JSONObject) teachArr.get(i);
						if (CommonFunction.isNotNull(teachObj.get("subjectid"))
								|| CommonFunction.isNotNull(teachObj.get("classid"))) {
							jo.put("classid", teachObj.get("classid"));
							jo.put("classname", teachObj.get("classname"));
							jo.put("subjectid", teachObj.get("subjectid"));
							jo.put("subjectname", teachObj.get("subjectname"));
							teachInfo.add(jo);
						}
					}
				}
				map.put("teachInfo", teachInfo);
				map.put("orgaid", userObject.get("orgaid") == null ? "" : userObject.get("orgaid"));
				map.put("organame", userObject.get("organame") == null ? "" : userObject.get("organame"));
				map.put("account", userObject.get("account") == null ? "" : userObject.get("account"));
				// TODO 获取platformCode
				map.put("platformCode", platformCode);
			} else {
				map.put("success", false);
				map.put("message", respJson.get("desc"));
				return map;
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static Map getUserClassByUserId(String userId, String platformCode) throws Exception {
		Map map = new HashMap();
		if (logger.isDebugEnabled()) {
			logger.debug("请求URL：" + GetCacheBaseData.getPropertyValue("platform_url", platformCode) + "user/" + userId);
		}
		// String
		// resp=HttpUtils.doGet(SysConfig_ebp.getStrValue(platformCode+"_platform_url")+"user/getuserinfo/"+usessionId);
		// String
		// resp=HttpUtils.doGet(GetCacheBaseData.getPropertyValue("platform_url",
		// platformCode)+"user/getuserinfo/"+usessionId);
		String url = GetCacheBaseData.getPropertyValue("platform_url", platformCode) + "user/" + userId;
		String resp = HttpUtils.getInstance().httpGet(url, 30000, 30000);
		if (logger.isDebugEnabled()) {
			logger.debug("信息返回:" + resp);
		}
		if (resp != null && !"".equals(resp)) {
			JSONObject respJson = JSONObject.fromObject(resp);
			if ("000000".equals(respJson.getString("result"))) {
				map.put("success", true);
				JSONObject userObject = (JSONObject) respJson.get("userinfo");
				map.put("userId", userObject.getString("personid"));
				// map.put("mobnum",
				// userObject.get("mobnum")==null?"":userObject.get("mobnum"));
				map.put("email", userObject.get("email") == null ? "" : userObject.get("email"));
				map.put("name", userObject.get("name") == null ? "" : userObject.get("name"));
				map.put("areacode",
						userObject.get("areacode") == null ? "" : userObject.get("areacode").toString().trim());
				// map.put("gender",
				// userObject.get("gender")==null?"":userObject.get("gender"));
				// map.put("birthday",
				// userObject.get("birthday")==null?"":userObject.get("birthday"));
				// 用户类型 0 学生; 1 老师; 2 家长 ; 3 机构; 4 学校; 5 学校工作人员 6 机构工作人员
				map.put("usertype", userObject.get("usertype"));
				// map.put("address",
				// userObject.get("address")==null?"":userObject.get("address"));
				// 当为学生时获取班级信息
				map.put("classid", userObject.get("classid") == null ? "" : userObject.get("classid"));
				map.put("classname", userObject.get("classname") == null ? "" : userObject.get("classname"));
				// JSONArray logoObject =
				// (JSONArray)userObject.get("userlogolist");
				// if(logoObject!=null&&logoObject.size()>0){
				// for(int i=0,total=logoObject.size();i<total;i++){
				// JSONObject logo = logoObject.getJSONObject(i);
				// if("1".equals(logo.get("logotype"))){
				// map.put("logourl", logo.get("logourl"));
				// }
				// }
				// }
				// map.put("idcardno",
				// userObject.get("idcardno")==null?"":userObject.get("idcardno"));

				JSONArray teachArr = (JSONArray) userObject.get("teachesubjectlist");
				JSONArray teachInfo = new JSONArray();
				JSONObject jo = new JSONObject();
				if (teachArr != null && teachArr.size() > 0) {
					for (int i = 0; i < teachArr.size(); i++) {
						JSONObject teachObj = (JSONObject) teachArr.get(i);
						if (CommonFunction.isNotNull(teachObj.get("subjectid"))
								|| CommonFunction.isNotNull(teachObj.get("classid"))) {
							jo.put("classid", teachObj.get("classid"));
							jo.put("classname", teachObj.get("classname"));
							jo.put("subjectid", teachObj.get("subjectid"));
							jo.put("subjectname", teachObj.get("subjectname"));
							teachInfo.add(jo);
						}
					}
				}
				map.put("teachInfo", teachInfo);
				map.put("orgaid", userObject.get("orgaid") == null ? "" : userObject.get("orgaid"));
				map.put("organame", userObject.get("organame") == null ? "" : userObject.get("organame"));
				map.put("account", userObject.get("account") == null ? "" : userObject.get("account"));
				// TODO 获取platformCode
				map.put("platformCode", platformCode);
			} else {
				map.put("success", false);
				map.put("message", respJson.get("desc"));
				return map;
			}
		}
		return map;
	}
}
