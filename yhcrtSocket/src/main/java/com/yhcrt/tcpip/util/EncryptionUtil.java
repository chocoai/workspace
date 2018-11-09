package com.yhcrt.tcpip.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.tcpip.socket.SocketHandler;


/**
 * @author 
 */
public class EncryptionUtil {
	private static final Logger logger =Logger.getLogger(SocketHandler.class);
	//接口服务器地址
	public final static String WEB_URL = "http://localhost:9998/iHealthCloud/socketServices";
	//基站查询
	public final static String LBS_URL = "http://api.cellocation.com/cell/?mcc=${MCC}&mnc=${MNC}&lac=${LAC}&ci=${CI}&output=json";
	//WIFI查询
	public final static String WIFI_URL = "http://api.cellocation.com/wifi/?mac=${MAC}&output=json";
	//天气接口
	public final static String WEATHER_URL =  "http://api.map.baidu.com/telematics/v3/weather?location=${LOCAL}&output=json&ak=59c4a98346ef8cc66935878fab1a6a4e";
	
	
	public final static Map<String,Object> parmMap ; 
	
	static {
		parmMap = new HashMap<String,Object>();
		parmMap.put("bid", "");
		parmMap.put("uid", "");
		parmMap.put("sts", "0");
		parmMap.put("rmk", "");
		parmMap.put("ver", "1");
		parmMap.put("tok", "0~9");	
	}
	
	/**
	 * 跌倒报警信息插入
	 */
	public static void insertFallDown(String imei,String uptime,String lbs,String wifi) {
		JSONObject biz = new JSONObject();
		try{
			biz.put("imei", imei);
			String datstr = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new  SimpleDateFormat("yyyyMMddHHmmss").parse(uptime));
			biz.put("data_date", datstr);
			biz.put("lbs", lbs);
			biz.put("wifi", wifi);
			parmMap.put("sid", "healthCloud.fallDown.insert");
			parmMap.put("biz",biz);
			boolean result = HttpClientUtils.httpPostWithJson((JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
			logger.info("插入跌倒报警数据：" + result);
		}catch(Exception e){
			logger.error("插入跌倒报警数据出错：" + biz.toJSONString());
		}
	}
	
	/**
	 * 手表血压数据插入
	 */
	public static void insertBloodAndPluse(String imei,String uptime,String dbp,String sbp,String pluse) {
		JSONObject bizBlood = new JSONObject();
		try{
			bizBlood.put("imei", imei);
			String datstr = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new  SimpleDateFormat("yyyyMMddHHmmss").parse(uptime));
			bizBlood.put("data_date", datstr);
			bizBlood.put("dbp", dbp);
			bizBlood.put("sbp", sbp);
			parmMap.put("sid", "healthCloud.app.hdBloodPressure.insert");
			parmMap.put("biz",bizBlood);
			Constans.socketReturnMap.put(Constans.FUN_BLOOD, bizBlood);
			boolean result = HttpClientUtils.httpPostWithJson((JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
			logger.info("插入血压数据：" + result);
		}catch(Exception e){
			logger.error("插入血压数据出错：" + bizBlood.toJSONString());
		}
		JSONObject bizPluse = new JSONObject();
		try{
			bizPluse.put("imei", imei);
			String datstr = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new  SimpleDateFormat("yyyyMMddHHmmss").parse(uptime));
			bizPluse.put("data_date", datstr);
			bizPluse.put("pluse", pluse);
			parmMap.put("sid", "healthCloud.app.hdPulse.upload");
			parmMap.put("biz",bizPluse);
			Constans.socketReturnMap.put(Constans.FUN_HEART_RATE, bizPluse);
			boolean result = HttpClientUtils.httpPostWithJson((JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
			logger.info("插入心率数据：" + result);
		}catch(Exception e){
			logger.error("插入心率数据出错：" + bizPluse.toJSONString());
		}
	}
	
	/**
	 * 手表血糖数据插入
	 */
	public static void insertBloodGlucose(String imei,String uptime,String bloodGlucose) {
		JSONObject biz = new JSONObject();
		try{
			biz.put("imei", imei);
			String datstr = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new  SimpleDateFormat("yyyyMMddHHmmss").parse(uptime));
			biz.put("data_date", datstr);
			biz.put("bg_type", 0);
			biz.put("bg_value", bloodGlucose);
			parmMap.put("sid", "healthCloud.app.hdBloodGlucose.insert");
			parmMap.put("biz",biz);
			Constans.socketReturnMap.put(Constans.FUN_BLOOD, biz);
			boolean result = HttpClientUtils.httpPostWithJson((JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
			logger.info("插入血糖数据：" + result);
		}catch(Exception e){
			logger.error("插入血糖数据出错：" + biz.toJSONString());
		}
	}
	
	/**
	 * 手表心率数据插入
	 */
	public static void insertHeartRate(String imei,String uptime,String heartrate) {
		JSONObject biz = new JSONObject();
		try{
			biz.put("imei", imei);
			String datstr = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new  SimpleDateFormat("yyyyMMddHHmmss").parse(uptime));
			biz.put("data_date", datstr);
			biz.put("pluse", heartrate);
			parmMap.put("sid", "healthCloud.app.hdPulse.upload");
			parmMap.put("biz",biz);
			Constans.socketReturnMap.put(Constans.FUN_HEART_RATE, biz);
			boolean result = HttpClientUtils.httpPostWithJson((JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
			logger.info("插入心率数据：" + result);
		}catch(Exception e){
			logger.error("插入心率数据出错：" + biz.toJSONString());
		}
	}
	
	/**
	 * 步数上传
	 */
	public static void insertStepNum(String imei,String uptime,String stepnum) {
		JSONObject biz = new JSONObject();
		try{
			biz.put("imei", imei);
			String datstr = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new  SimpleDateFormat("yyyyMMddHHmmss").parse(uptime));
			biz.put("data_date", datstr);
			biz.put("step_count", stepnum);
			parmMap.put("sid", "healthCloud.app.hdStep.insert");
			parmMap.put("biz",biz);
			boolean result = HttpClientUtils.httpPostWithJson((JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
			logger.info("插入运动数据：" + result);
		}catch(Exception e){
			logger.error("插入运动数据出错：" + biz.toJSONString());
		}
	}
	
	/**
	 * 位置上传
	 */
	public static void insertLocation(String imei,String uptime,String local,String addr) {
		JSONObject biz = new JSONObject();
		try{
			biz.put("imei", imei);
			String datstr = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new  SimpleDateFormat("yyyyMMddHHmmss").parse(uptime));
			biz.put("data_date", datstr);
			String lat = local.split(";")[0];
			String lgt = local.split(";")[1];
			biz.put("latitude", lat);
			biz.put("longitude", lgt);
			biz.put("addr", addr);
			parmMap.put("sid", "healthCloud.app.location.upload");
			parmMap.put("biz",biz);
			Constans.socketReturnMap.put(Constans.FUN_LOCAL, biz);
			boolean result = HttpClientUtils.httpPostWithJson((JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
			logger.info("插入位置数据：" + result);
		}catch(Exception e){
			logger.error("插入位置数据出错：" + biz.toJSONString());
		}
	}
	
	/**
	 * 睡眠上传
	 */
	public static void insertSleep(String imei,String sleepvalue) {
		JSONObject biz = new JSONObject();
		try{
			biz.put("imei", imei);
			biz.put("sleep", sleepvalue);
			parmMap.put("sid", "healthCloud.app.hdSleep.insert");
			parmMap.put("biz",biz);
			boolean result = HttpClientUtils.httpPostWithJson((JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
			logger.info("插入睡眠数据：" + result);
		}catch(Exception e){
			logger.error("插入睡眠数据出错：" + biz.toJSONString());
		}
	}
	
	/**
	 *获取天气数据
	 */
	public static String getWeather(String location) {
		try{
			String str = WEATHER_URL.replace("${LOCAL}", location);
			JSONObject json = HttpClientUtils.httpGet(str);
			Integer error = json.getInteger("error");
			if(error!=0){
				return null;
			}
			JSONObject wetherdata = json.getJSONArray("results").getJSONObject(0).getJSONArray("weather_data").getJSONObject(0);
			String temperature = wetherdata.getString("temperature");
			String lowValue = temperature.split("~")[1].trim().replace("℃", "");
			String highValue = temperature.split("~")[0].trim().replace("℃", "");
			String weather = wetherdata.getString("weather");
//			String logo1 = wetherdata.getString("dayPictureUrl");
//			String logo2 = wetherdata.getString("nightPictureUrl");
			Integer logo = getLogo(weather);
//			if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>17 || Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<6){
//				logo = logo2;
//			}
			String pm25 =  json.getJSONArray("results").getJSONObject(0).getString("pm25");
			String currentCity = json.getJSONArray("results").getJSONObject(0).getString("currentCity");
			String reslutstr = lowValue+","+highValue+","+weather+","+logo+","+pm25+","+currentCity;
			System.out.println(reslutstr);
			return reslutstr;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("获取天气数据出错！" );
		}
		return null;
	}
	
	public static Integer getLogo(String weather){
		Integer logo = 0;
		if(weather.contains("多云")){
			logo = 1;
		}else if(weather.contains("阴")){
			logo = 2;
		}else if(weather.contains("阵雨")){
			logo = 3;
		}else if(weather.contains("雨")&&weather.contains("晴")){
			logo = 3;
		}else if(weather.contains("雷")&&weather.contains("雨")){
			logo = 4;
		}else if(weather.contains("雪")&&weather.contains("雨")){
			logo = 6;
		}else if(weather.contains("小雨")){
			logo = 7;
		}else if(weather.contains("中雨")){
			logo = 8;
		}else if(weather.contains("大雨")){
			logo = 9;
		}else if(weather.contains("暴雨")){
			logo = 11;
		}else if(weather.contains("小雪")){
			logo = 14;
		}else if(weather.contains("中雪")){
			logo = 15;
		}else if(weather.contains("大雪")){
			logo = 16;
		}else if(weather.contains("暴雪")){
			logo = 17;
		}else if(weather.contains("雪")&&weather.contains("晴")){
			logo = 13;
		}else if(weather.contains("沙尘暴")){
			logo = 20;
		}else if(weather.contains("雾")){
			logo = 18;
		}else if(weather.contains("雨")){
			logo = 8;
		}
		return logo;
	}
	



   public static void main(String[] args) {
//	   String str = "{'bid':'2013082719093303452','uid':'18971690300','sid':'healthCloud.app.hdPulse.upload','sts':'0','rmk':'','ver':'1','tok':'0~9','biz':{'imei':'7788414','memberId':1006,'pluse':'90'}}";
//	   HttpClientUtils.httpGet("http://localhost:8080/iHealthCloud/services?pdata="+str);
//	   getWeather("武汉");
	   System.out.println("6|460|0|2|7010&f591&34&34$7010&c2e5&28&28$7010&f590&24&24$7010&f16e&23&23$7010&9cfa&19&19$7010&f16d&18&18".split("\\|")[1]);

   }
   
   public static void test(){
	    JSONArray bizs = new JSONArray();
	    JSONObject data = new JSONObject();
	    data.put("imei", "142701199501194");
	    data.put("uptime", "201709110101");
	    data.put("dbp", "90");
	    data.put("sbp", "120");
	    bizs.add(data);
		parmMap.put("sid", "47");
		parmMap.put("biz",bizs);
        System.out.println(parmMap);
		HttpClientUtils.httpPostWithJson( (JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
   }
   
   public static  void test1(String str){
	    JSONArray bizs = new JSONArray();
	    JSONObject data = new JSONObject();
	    data.put("imei", str);
	    data.put("high_heart_rate", "150");
	    data.put("low_heart_rate", "60");
	    bizs.add(data);
		parmMap.put("sid", "47");
		parmMap.put("biz",bizs);
        System.out.println(parmMap);
		HttpClientUtils.httpPostWithJson( (JSONObject)JSONObject.toJSON(parmMap),WEB_URL);
   }

	

}