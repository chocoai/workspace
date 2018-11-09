package com.yhcrt.iHealthCloud.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;

public abstract class BaseService {
	
	protected boolean isDebug = true;
	
	/**
	 * 获取biz对象
	 * @param pdataObj
	 * @return biz对象
	 */
	protected JSONObject getBizObj(JSONObject pdataObj){
		return pdataObj.getJSONObject(Const.TAG_BIZ);
	}
	
	protected String getPageSize(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_PAGE_SIZE);
	}
	
	protected String getCurrentPage(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_CURRENT_PAGE);
	}
	
	protected String getCid(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_CID);
	}
	
	protected String getMemberId(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_MEMBER_ID);
	}
	
	protected String getDataDate(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_DATA_DATE);
	}
	
	protected String getOrgId(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_ORG_ID);
	}
	
	protected String getDeviceType(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_DEVICE_TYPE);
	}
	
	protected String getDeviceSim(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_DEVICE_SIM);
	}
	
	protected String getDeviceName(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_DEVICE_NAME);
	}
	
	protected String getDeviceIsUsed(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_DEVCIE_ISUSED);
	}
	
	protected String getStartTime(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_START_TIME);
	}
	
	protected String getEndTime(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_END_TIME);
	}
	
	protected String getImei(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_IMEI);
	}
	
	protected String getSbp(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_SBP);
	}
	
	protected String getDbp(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_DBP);
	}
	
	protected String getStepCount(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_STEP_COUNT);
	}
	
	protected String getPluse(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_PLUSE);
	}
	
	protected String getSleepQuality(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_SLEEP);
	}
	
	protected String getMerId(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_MER_ID);
	}
	
	protected String getBgType(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_BG_TYPE);
	}
	
	protected String getBgValue(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_BG_VALUE);
	}
	
	protected String getDeviceId(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_DEVICE_ID);
	}
	
	protected String getRealName(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_REAL_NAME);
	}
	
	protected String getIdentityCard(JSONObject pdataObj) {
		return getBizObj(pdataObj).getString(Const.TAG_IDENTITY_CARD);
	}
	
	/**
	 * 判断参数是否为空
	 * @param argms
	 * @return boolean
	 */
	protected boolean judgeAgumentsIsLegal(JSONObject pdataObj, String...argms) {
		
		boolean result = true;
		
		for(int i = 0 ; i < argms.length ; i++ ){
			
			if(Constants.IS_DEUBG && isDebug){
				System.out.println("argm" + ( i + 1 ) +" : " + argms[i]);
			}
			
			if(argms[i] == null || "".equals(argms[i])){
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	protected String requestSucceed(JSONObject pdataObj, Object result, String rmk) {
		
		JSONObject bizObj = getBizObj(pdataObj);
		bizObj.clear();
		bizObj.put(Const.TAG_RESULT, result);
		
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_RMK, rmk);
		
		return pdataObj.toJSONString();
	}
	
	protected String requestFailed(JSONObject pdataObj, String rmk) {
		
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
		pdataObj.put(Const.TAG_RMK, rmk);
		
		return pdataObj.toJSONString();
	}
	
	protected void putCaseIntoBizObj(JSONObject pdataObj, List<?> list) {
		if(list != null && list.size() > 0){
			getBizObj(pdataObj).put(Const.TAG_CASE, list.get(0));
		}
	}
	
	protected void putCaseIntoBizObj(JSONObject pdataObj, Object caze) {
		if(caze != null){
			getBizObj(pdataObj).put(Const.TAG_CASE, caze);
		}
	}
	
	protected void putMaxMinAveValueToBizObj(JSONObject pdataObj, Object max, Object min, Object ave){
		getBizObj(pdataObj).put(Const.TAG_MAX, max);
		getBizObj(pdataObj).put(Const.TAG_MIN, min);
		getBizObj(pdataObj).put(Const.TAG_AVE, ave);
	}
	
	/**
	 * 生成最近一个星期的日期
	 * @return 字符串集合
	 */
	@SuppressWarnings("deprecation")
	protected List<String> getDateString4week() {
		Date date = new Date();
		date.setSeconds(0);
		date.setMinutes(0);
		date.setHours(0);
		List<String> list = new ArrayList<>();
		list.add(DateUtil.format(date, DateUtil.DATE_PATTERN.YYYY_MM_DD));
		for(int i = 0; i < 6; i++){
			date.setDate(date.getDate()-1);
			list.add(DateUtil.format(date, DateUtil.DATE_PATTERN.YYYY_MM_DD));
		}
		return list;
	}
	
	/**
	 * 判断分页信息是否合法
	 * @param totalPage
	 * @param currentPage
	 * @return
	 */
	protected boolean judgePageInfoIsLegal(String currentPage, String pageSize) {
		boolean result;
		if(currentPage == null || pageSize == null || "".equals(currentPage) || "".equals(pageSize)){
			result = false;
		}else{
			try {
				Integer.parseInt(currentPage);
				Integer.parseInt(pageSize);
				result = true;
			} catch (Exception e) {
				result = false;
			}
		}
		return result;
	}
	
	/**
	 * 设置返回的分页信息
	 * @param pdataObj
	 * @param totalpage
	 * @param currentpage
	 */
	protected void setPagingData(JSONObject pdataObj, Integer totalPage, Integer currentPage) {
		
		if(Const.IS_DEBUG && isDebug){
			System.out.println("total page : " + totalPage);
			System.out.println("current page : " + currentPage);
		}
		
		pdataObj.put(Const.TAG_TOTAL_PAGE, totalPage);
		pdataObj.put(Const.TAG_CURRENT_PAGE, currentPage);
	}
	
	/**
	 * JSONObject转化为json字符串
	 * @param pdataObj
	 * @return json string
	 */
	protected String toJsonStringWithOutNull(JSONObject pdataObj) {
		/**
		 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
		 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为"",而非null 
		 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
		 * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
		 */
		return JSONObject.toJSONString(pdataObj, 
				SerializerFeature.WriteNullStringAsEmpty, 
				SerializerFeature.WriteNullListAsEmpty, 
				SerializerFeature.WriteNullNumberAsZero, 
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
}
