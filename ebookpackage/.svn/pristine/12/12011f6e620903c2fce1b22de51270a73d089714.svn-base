package com.whty.ebp.api.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.common.util.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * httpcliet 工具类
 * 
 * @author chenjp
 * @date 2015年6月18日
 */
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	private static HttpUtils instance;

	public HttpUtils() {

	}

	public static HttpUtils getInstance() {
		if (instance == null) {
			instance = new HttpUtils();
		}
		return instance;
	}

	/**
	 * 区域平台请求分发
	 * 
	 * @param httpUrl
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public String httpPost(String httpUrl, String json) throws Exception {
		logger.info("Post请求httpUrl:" + httpUrl+"--------参数:"+json);
		long time0 = System.currentTimeMillis();
		String responseResult = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			// 设置请求和传输超时
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
			StringEntity entity = new StringEntity(json, ContentType.create("application/json", Consts.UTF_8));

			// 采用post方式提交
			HttpPost httpPost = new HttpPost(httpUrl);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			responseResult = EntityUtils.toString(resEntity, "utf-8");
			httpPost.releaseConnection();
		} catch (Throwable e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if(httpClient != null){
				httpClient.close();
			}
		}
		long time1 = System.currentTimeMillis();
		logger.info("Post请求httpUrl:" + httpUrl+"--------参数:"+json);
		logger.info("Post请求返回:" + responseResult);
		logger.info("Post请求耗时（ms）:" + (time1-time0));
		return responseResult;
	}

	/**
	 * 使用http get 方法请求，结果返回字符串
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String httpGet(String url) throws Exception {
		logger.info("Get请求httpUrl:" + url);
		long time0 = System.currentTimeMillis();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String responseResult = null;
		try {
			// get请求
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();

			// 发送请求
			httpGet.setConfig(requestConfig);
			HttpResponse httpresponse = httpClient.execute(httpGet);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			responseResult = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if(httpClient != null){
				httpClient.close();
			}
		}
		long time1 = System.currentTimeMillis();
		logger.info("Get请求httpUrl:" + url);
		logger.info("Get请求返回:" + responseResult);
		logger.info("Get请求耗时（ms）:" + (time1-time0));
		return responseResult;
	}
	
	/**
	 * 区域平台请求分发
	 * 
	 * @param httpUrl
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public String httpPost(String httpUrl, String json,Integer SocketTimeout,Integer ConnectTimeout) throws Exception {
		logger.info("Post请求httpUrl:" + httpUrl+"--------参数:"+json);
		long time0 = System.currentTimeMillis();
		String responseResult = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			// 设置请求和传输超时
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SocketTimeout<5000?5000:SocketTimeout).setConnectTimeout(ConnectTimeout<5000?5000:ConnectTimeout).build();
			StringEntity entity = new StringEntity(json, ContentType.create("application/json", Consts.UTF_8));

			// 采用post方式提交
			HttpPost httpPost = new HttpPost(httpUrl);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			responseResult = EntityUtils.toString(resEntity, "utf-8");
			httpPost.releaseConnection();
		} catch (Throwable e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if(httpClient != null){
				httpClient.close();
			}
		}
		long time1 = System.currentTimeMillis();
		logger.info("Post请求httpUrl:" + httpUrl+"--------参数:"+json);
		logger.info("Post请求返回:" + responseResult);
		logger.info("Post请求耗时（ms）:" + (time1-time0));
		return responseResult;
	}

	/**
	 * 使用http get 方法请求，结果返回字符串
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String httpGet(String url,Integer SocketTimeout,Integer ConnectTimeout) throws Exception {
		logger.info("Get请求httpUrl:" + url);
		long time0 = System.currentTimeMillis();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String responseResult = null;
		try {
			// get请求
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SocketTimeout<5000?5000:SocketTimeout).setConnectTimeout(ConnectTimeout<5000?5000:ConnectTimeout).build();

			// 发送请求
			httpGet.setConfig(requestConfig);
			HttpResponse httpresponse = httpClient.execute(httpGet);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			responseResult = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if(httpClient != null){
				httpClient.close();
			}
		}
		long time1 = System.currentTimeMillis();
		logger.info("Get请求httpUrl:" + url);
		logger.info("Get请求返回:" + responseResult);
		logger.info("Get请求耗时（ms）:" + (time1-time0));
		return responseResult;
	}

	public static void main(String[] args) throws Exception{

//		String url = "http://test.wuhaneduyun.cn:13022/aamty/allAccount/queryPlatformInfo";
////		HttpUtils httpClientUtils = HttpUtils.getInstance();
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("platformCode", "420100");
//		try {
//			String result = HttpUtils.getInstance().httpPost(url, JsonUtils.objTojson(map));
////			ResultMessage msg = (ResultMessage) JsonUtils.jsonToObj(result, ResultMessage.class);
//			System.out.println(result);
////			UserAccount userAccount = (UserAccount) JsonUtils.jsonToObj(JsonUtils.ojbTojson(msg.getData()), UserAccount.class);
////			System.out.println(userAccount.getSyncTime());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		String url = "http://116.211.105.37:10013/aamif/rest//user/1887d34571734dbd803311d745066a8d";
		String result = HttpUtils.getInstance().httpGet(url);
		System.out.println(result);
		
//		String url = "http://112.53.84.149:20014/aamif/rest/ogra/get";
		
//		String url = "http://116.211.105.37:10013/aamif/rest/";
//		
//		String json = "{\"orgaids\":[\"1cee3883f2834892ac5237799fea1ca3\"]}";
//		String result = HttpUtils.getInstance().httpPost(url, json);
//		
//		if (result != null && !"".equals(result)) {
//			JSONObject OgraRespJson = JSONObject.fromObject(result);
//			if ("000000".equals(OgraRespJson.getString("result"))) {
//				JSONArray orgList = OgraRespJson.getJSONArray("orgalist");
//
//				JSONObject orgObject = (JSONObject) orgList.get(0);
//				
//				System.out.println(orgObject.get("areacode").toString());
//				
//				
//			}
//		}
		
	}

}
