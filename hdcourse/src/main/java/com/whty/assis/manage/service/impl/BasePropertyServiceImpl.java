package com.whty.assis.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.manage.dao.BasePropertyDao;
import com.whty.assis.manage.model.BaseProperty;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.cache.data.CacheDataUtils;
import com.whty.common.util.CommonFunction;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class BasePropertyServiceImpl implements BasePropertyService {

	@Autowired
	private BasePropertyDao basePropertyDao;

	@Override
	public List<BaseProperty> find(Map<String, Object> params) {
		return basePropertyDao.listByCondition(params);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public HandlerResult queryBaseProperty(Map para) {
		HandlerResult rs = new HandlerResult();
		List<BaseProperty> resultList = basePropertyDao.queryBaseProperty(para);
		rs.setResultList(resultList);
		return rs;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<BaseProperty> queryAllBaseProperty(Map para) {
		List<BaseProperty> list = basePropertyDao.queryBaseProperty(para);
		return list;
	}

	@Override
	public void addBaseProperty(BaseProperty baseProperty) {
		basePropertyDao.addBaseProperty(baseProperty);
	}

	@Override
	public void updateBaseProperty(BaseProperty baseProperty) {
		basePropertyDao.updateBaseProperty(baseProperty);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteBaseProperty(Map para) {
		basePropertyDao.deleteBaseProperty(para);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public BaseProperty getPlatformProperty(Map map) throws Exception {
		BaseProperty bp = null;
		bp = (BaseProperty) CacheDataUtils.getObjectData("BaseProperty",
				"getPlatformProperty" + CommonFunction.mapValueToStr(map));
		if (null == bp) {
			List<BaseProperty> list = basePropertyDao.getPlatformProperty(map);
			if (list != null && list.size() > 0) {
				bp = list.get(0);
				CacheDataUtils.setObjectData("BaseProperty", "getPlatformProperty" + CommonFunction.mapValueToStr(map),
						bp);
			}
		}
		return bp;
	}

	/*
	 * 根据属性key和平台编码获取属性对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseProperty getBasePropertyByKeyAndPlatformCode(String propertyKey, String platformCode) throws Exception {
		BaseProperty bp = null;
		Map map = new HashMap();
		map.put("property_key", propertyKey);
		map.put("platform_code", platformCode);
		bp = (BaseProperty) CacheDataUtils.getObjectData("BaseProperty",
				"getBasePropertyByKeyAndPlatformCode" + CommonFunction.mapValueToStr(map));
		if (null == bp) {
			List<BaseProperty> list = basePropertyDao.getBasePropertyByKeyAndPlatformCode(map);
			if (list != null && list.size() > 0) {
				bp = list.get(0);
			}
		}
		return bp;
	}

	/*
	 * 根据属性key和平台编码获取属性值
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getPropertyValue(String propertyKey, String platformCode) {
		String propertyValue = null;
		try {
			propertyValue = CacheDataUtils.getData("BaseProperty", propertyKey + "_" + platformCode);
			if (propertyValue == null || !propertyValue.trim().equals("")) {
				// 查询数据库
				Map map = new HashMap();
				map.put("property_key", propertyKey);
				map.put("platform_code", platformCode);
				propertyValue = basePropertyDao.getPropertyValue(map);
				if (null != propertyValue) {
					CacheDataUtils.setData("BaseProperty", propertyKey + "_" + platformCode, propertyValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}

	/*
	 * 查询所有的配置属性对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BaseProperty> queryBasePropertyList(Map paramMap) {
		return basePropertyDao.listByCondition(paramMap);
	}

	@Override
	public BaseProperty loadBaseProById(String id) {
		return basePropertyDao.loadById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseProperty> getPlatformList() {
		return basePropertyDao.getPlatformList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.manage.service.BasePropertyService#getUserName(java.lang.
	 * String)
	 */
	@Override
	public Map<String, Object> getUserName(String getUserkeyInfoStr) {
		Map<String, Object> param = new HashMap<String, Object>();
		String userId = "";
		String userName = "";
		String platformCode = "";
		String loginPlatformCode = "";
		String loginAccount = "";
		JSONObject useKeyInfoResultJson = JSONObject.fromObject(getUserkeyInfoStr);
		System.out.println(useKeyInfoResultJson);
		if (useKeyInfoResultJson.optString("retCode").equals("000000")) {
			System.out.println(useKeyInfoResultJson.optString("retCode"));
			JSONObject useKeyInfoResultJsonData = new JSONObject();
			JSONArray useKeyInfoResultArrayJsonData = useKeyInfoResultJson.optJSONArray("data");

			if (useKeyInfoResultArrayJsonData.size() > 0) {
				useKeyInfoResultJsonData = useKeyInfoResultArrayJsonData.optJSONObject(0);
			}

			String useKeyInfoResultJsonDataPlatformCode = useKeyInfoResultJsonData.optString("sysCode");
			loginAccount = useKeyInfoResultJsonData.optString("loginAccount");
			param.put("loginAccount", loginAccount);

			platformCode = useKeyInfoResultJsonDataPlatformCode; // 用户平台编码
			userId = useKeyInfoResultJsonData.optString("platUserId");

			param.put("userId", userId);
			param.put("loginPlatformCode", platformCode);

			String useKeyInfoResultJsonDataPlatformCodeLoginAccount = useKeyInfoResultJsonData
					.optString("loginAccount");

			// 查询到登录平台编码
			String ss = "http://116.211.105.38:13022/aamty/allAccount/queryAccountPlatformInfo";

			Map<String, Object> ssmap = new HashMap<String, Object>();
			// ssmap.put("account", "tyjiaoshi02");
			ssmap.put("account", useKeyInfoResultJsonDataPlatformCodeLoginAccount);
			String result2;
			try {
				result2 = HttpUtils.getInstance().httpPost(ss, JSONObject.fromObject(ssmap).toString());

				JSONObject logUserInfo = JSONObject.fromObject(result2);

				if (logUserInfo.optString("result").equals("000000")) {
					JSONObject logUserInfoData = logUserInfo.optJSONObject("data");
					loginPlatformCode = logUserInfoData.optString("platformCode");// 登录平台编码
					param.put("loginPlatformCode", loginPlatformCode);

					String platformUrl = logUserInfoData.optString("platformUrl");

					String userInfoUrlResult = HttpUtils.getInstance().httpGet(platformUrl + "/user/" + userId);
					String userInfoName = JSONObject.fromObject(userInfoUrlResult).optJSONObject("userinfo")
							.optString("name");
					if (StringUtils.isNotEmpty(userInfoName)) {
						userName = userInfoName;
						param.put("userName", userName);
					}

				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// String userInfoUrl = getPropertyValue("platform_url",
			// useKeyInfoResultJsonDataPlatformCode);
			//
			// if (userInfoUrl != null) {
			// String userInfoUrlResult;
			// try {
			// userInfoUrlResult = HttpUtils.getInstance().httpGet(userInfoUrl +
			// "user/" + userId);
			// String userInfoName =
			// JSONObject.fromObject(userInfoUrlResult).optJSONObject("userinfo")
			// .optString("name");
			// if (StringUtils.isNotEmpty(userInfoName)) {
			// userName = userInfoName;
			// param.put("userName", userName);
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			//
			// }

		}
		return param;
	}
}
