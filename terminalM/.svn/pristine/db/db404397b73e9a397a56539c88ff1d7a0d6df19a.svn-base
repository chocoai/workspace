package com.whty.assis.manage.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * httpcliet 工具类
 * 
 * @author chenjp
 * @date 2015年6月18日
 */
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	// private static Logger logger = Logger.getLogger(HttpUtils.class);
	// private Logger logger = Logger.getLogger(this.getClass());
	private static HttpUtils instance;

	public HttpUtils() {

	}

	/**
	 * 获取当前网络ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
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
//		logger.info("Post请求httpUrl:" + httpUrl + "--------参数:" + json);
		// long time0 = System.currentTimeMillis();
		String responseResult = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			// 设置请求和传输超时
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
					.build();
			StringEntity entity = new StringEntity(json, ContentType.create("application/json", Consts.UTF_8));

			// 采用post方式提交
			HttpPost httpPost = new HttpPost(httpUrl);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(entity);
			response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			responseResult = EntityUtils.toString(resEntity, "utf-8");
			httpPost.releaseConnection();
		} catch (Throwable e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}

		}
		// long time1 = System.currentTimeMillis();
		// logger.info("Post请求httpUrl:" + httpUrl + "--------参数:" + json);
		// logger.info("Post请求返回:" + responseResult);
		// logger.info("Post请求耗时（ms）:" + (time1 - time0));
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
		// long time0 = System.currentTimeMillis();
		CloseableHttpClient httpClient = HttpClients.createDefault();
	
		String responseResult = null;
		CloseableHttpResponse response = null;
		try {
			// get请求
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
					.build();

			// 发送请求
			httpGet.setConfig(requestConfig);
			response = httpClient.execute(httpGet);
			// 获取返回数据
			HttpEntity entity = response.getEntity();
			responseResult = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if (response != null) {
				response.close();
			}

			if (httpClient != null) {
				httpClient.close();
			}
		}
		// long time1 = System.currentTimeMillis();
		// logger.info("Get请求httpUrl:" + url);
		// logger.info("Get请求返回:" + responseResult);
		// logger.info("Get请求耗时（ms）:" + (time1 - time0));
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
	public String httpPost(String httpUrl, String json, Integer SocketTimeout, Integer ConnectTimeout)
			throws Exception {
		logger.info("Post请求httpUrl:" + httpUrl + "--------参数:" + json);
		// long time0 = System.currentTimeMillis();
		String responseResult = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			// 设置请求和传输超时
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(SocketTimeout < 5000 ? 5000 : SocketTimeout)
					.setConnectTimeout(ConnectTimeout < 5000 ? 5000 : ConnectTimeout).build();
			StringEntity entity = new StringEntity(json, ContentType.create("application/json", Consts.UTF_8));

			// 采用post方式提交
			HttpPost httpPost = new HttpPost(httpUrl);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(entity);
			response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			responseResult = EntityUtils.toString(resEntity, "utf-8");
			httpPost.releaseConnection();
		} catch (Throwable e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if (response != null) {
				response.close();
			}

			if (httpClient != null) {
				httpClient.close();
			}
		}
		// long time1 = System.currentTimeMillis();
		// logger.info("Post请求httpUrl:" + httpUrl + "--------参数:" + json);
		// logger.info("Post请求返回:" + responseResult);
		// logger.info("Post请求耗时（ms）:" + (time1 - time0));
		return responseResult;
	}

	/**
	 * 使用http get 方法请求，结果返回字符串
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String httpGet(String url, Integer SocketTimeout, Integer ConnectTimeout) throws Exception {
		logger.info("Get请求httpUrl:" + url);
		// long time0 = System.currentTimeMillis();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String responseResult = null;
		CloseableHttpResponse response = null;
		try {
			// get请求
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(SocketTimeout < 5000 ? 5000 : SocketTimeout)
					.setConnectTimeout(ConnectTimeout < 5000 ? 5000 : ConnectTimeout).build();

			// 发送请求
			httpGet.setConfig(requestConfig);
			response = httpClient.execute(httpGet);
			// 获取返回数据
			HttpEntity entity = response.getEntity();
			responseResult = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if (response != null) {
				response.close();
			}

			if (httpClient != null) {
				httpClient.close();
			}
		}
		// long time1 = System.currentTimeMillis();
		// logger.info("Get请求httpUrl:" + url);
		// logger.info("Get请求返回:" + responseResult);
		// logger.info("Get请求耗时（ms）:" + (time1 - time0));
		return responseResult;
	}

	public static void main(String[] args) throws Exception {

		// String url =
		// "http://116.211.105.37:10013/aamif/rest/user/F259F9A498DA7692E04010AC73D40970";

		// Map<String,String> map = new HashMap<String,String>();
		// map.put("groupid", "F6C68AAFA3F7437CE040007F01001D3D");
		// map.put("type", "1");
		// map.put("start", "0");
		// map.put("end", "10");
		// String result =
		// HttpUtils.getInstance().httpPost(url,JsonUtils.objTojson(map));
		// String result = HttpUtils.getInstance().httpGet(url);
		// System.out.println(result);
		// String url =
		// "http://test.wuhaneduyun.cn:13022/aamty/allAccount/queryPlatformInfo";
		//// HttpUtils httpClientUtils = HttpUtils.getInstance();
		// Map<String,String> map = new HashMap<String,String>();
		// map.put("platformCode", "420100");
		// try {
		// String result = HttpUtils.getInstance().httpPost(url,
		// JsonUtils.objTojson(map));
		//// ResultMessage msg = (ResultMessage) JsonUtils.jsonToObj(result,
		// ResultMessage.class);
		// System.out.println(result);
		//// UserAccount userAccount = (UserAccount)
		// JsonUtils.jsonToObj(JsonUtils.ojbTojson(msg.getData()),
		// UserAccount.class);
		//// System.out.println(userAccount.getSyncTime());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// String url =
		// "http://kcapi.d.huijiaoyun.com/coursecenter_api/exam/myQuesList";
		//
		// Map<String, String> map = new HashMap<String, String>();
		//
		// String json =
		// "{\"loginPlatformCode\",\"430000\",\"platformCode\":\"430000\",
		// \"userSessionId\":\"fhdshfdjfhdkhfksdjfds\",\"chapterId\":\"14C055430274B90FE050A8C033C84BC1\",
		// \"userId\":\"6c9f2d3dee9840cca9b58305d6f31f5e\",
		// \"pageInfo\":{\"currPage\":1, \"pageSize\":10}}";
		// try {
		// String result = HttpUtils.getInstance().httpPost(url, json);
		// System.out.println(result);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// String url =
		// "http://test.wuhaneduyun.cn:20014/aamif/rest/user/1887d34571734dbd803311d745066a8d";

		// String url =
		// "http://test.wuhaneduyun.cn:20014/aamif/rest/querySchoolInfoRep";

		// String url = "http://116.211.105.37:10013/aamif/rest/user/all/";
		// String url =
		// "http://test.wuhaneduyun.cn:20014/aamif/rest/account/getuserinfo";
		// http://61.161.209.183:20014/aamif/rest/
		// Map<String, String> map = new HashMap<String, String>();

		// int start = 0;
		// int end=10;
		// String json =
		// "{\"type\":\"1\",\"provincecode\":\"420000\",\"areacode\":\"420100\",\"start\":\"0\",\"end\":\"10\"}";

		// String json =
		// "{\"type\":\"1\",\"provincecode\":\"330000\",\"start\":\"0\",\"end\":\"10\"}";

		// String json = "{\"accountlist\":
		// [\"1887d34571734dbd803311d745066a8d\"]}";
		//
		// try {
		//
		// for (int i = 0; i < 9000; i = i + 10) {
		// int start = i;
		// int end = i + 10;

		// Map<String, Object> param = new HashMap<String, Object>();
		// param.put("type", 1);
		// param.put("provincecode", "420000");
		// param.put("areacode", "420100");
		// param.put("start", start);
		// param.put("end", end);
		// List<String> s = new ArrayList<>();
		// s.add("F6C68AAFA9B1437CE040007F01001D3D");
		// param.put("orgaids", s);

		// param.put("orgid", "F6C68AAFA6A5437CE040007F01001D3D");

		// String result = HttpUtils.getInstance().httpPost(url,
		// JSONObject.fromObject(param).toString());

		// if (result != null && !"".equals(result)) {
		// JSONObject resultJson = JSONObject.fromObject(result);
		//// System.err.println(resultJson.toString());
		// if ("000000".equals(resultJson.getString("result"))) {
		// JSONArray orgList = resultJson.getJSONArray("organ");
		//
		// for (int j = 0; j < orgList.size(); j++) {
		// JSONObject orgObject = (JSONObject) orgList.get(0);
		// if (orgObject.optString("organame").contains("黄陂")) {
		// System.out.println(orgObject.optString("orgaid"));
		// break;
		// }
		//
		// }
		//
		// // JSONObject count = resultJson.getJSONObject("count");
		//
		// // System.out.println(resultJson.optString("count"));
		//
		// // JSONObject orgObject = (JSONObject) orgList.get(0);
		//
		// }
		// }
		// System.out.println(result);

		// }

		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// System.out.println(Integer.valueOf(null));

		// String ograAddressUrl = "http://116.211.105.37:10013/aamif/rest/" +
		// "ogra/get";
		// List<String> ograList = new ArrayList<String>();
		// ograList.add("f10a740e9cf84d8e80bd6ca064a538ee");
		//
		// Map<String, Object> map = new HashMap<String, Object>();
		//
		// map.put("orgaids", ograList);
		//
		// String ograResp = HttpUtils.getInstance().httpPost(ograAddressUrl,
		// JsonUtils.objTojson(map));
		//
		// if (ograResp != null && !"".equals(ograResp)) {
		// JSONObject OgraRespJson = JSONObject.fromObject(ograResp);
		// if ("000000".equals(OgraRespJson.getString("result"))) {
		// JSONArray orgList = OgraRespJson.getJSONArray("orgalist");
		//
		// JSONObject orgObject = (JSONObject) orgList.get(0);
		// System.out.println(orgObject);
		//// loginHistory.setAreaCode(orgObject.get("areacode") == null ? ""
		//// : orgObject.get("areacode").toString());
		//
		// }
		// }

		// String aamAddress = GetCacheBaseData.getPropertyValue("platform_url",
		// platformCode);
		//
		// String url = aamAddress + "school/statistics/" + orgId;

		// String url =
		// "http://116.211.105.43:22007/aamif/rest/school/statistics/F6C68AAFA3F7437CE040007F01001D3D";
		// int teacherNum = 0;
		// String resp;
		// try {
		// resp = HttpUtils.getInstance().httpGet(url, 30000, 30000);
		//
		// if (logger.isDebugEnabled()) {
		// logger.debug("信息返回:" + resp);
		// }
		//
		// if (resp != null && !"".equals(resp)) {
		// JSONObject respJson = JSONObject.fromObject(resp);
		// if ("000000".equals(respJson.getString("result"))) {
		// // JSONObject teachercount = (JSONObject)
		//// respJson.respJson("teachercount");
		//
		// String teachercount = respJson.getString("teachercount");
		//
		// if (teachercount != null) {
		// teacherNum = Integer.valueOf(teachercount.toString());
		// }
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// System.out.println(teacherNum);
		//
	}

}
