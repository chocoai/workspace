package com.whty.mxbj.common.utils;

import java.io.IOException;
import java.net.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
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

	// public static void main(String[] args){
	// // 调用创建文件夹接口
	//
	//
	// }

	public String cmsHttpPost(String prefix, String url, String json) {
		logger.info("Post请求httpUrl:" + prefix + url + "--------参数:" + json);
		String responseResult = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		String cmsAppId = SysConfigUtils.getStrValue("cms.appId");
		String cmsAppSecret = SysConfigUtils.getStrValue("cms.appSecret");
		String timestamp = String.valueOf(System.currentTimeMillis());
		String key = cmsAppId + "&POST&" + url + "&" + timestamp;
		// 数字签名
		CryptoServer cryptoServer = CryptoServer.getInstance(cmsAppSecret);
		String signature = cryptoServer.signature(key);
		try {
			httpClient = HttpClients.createDefault();
			// 设置请求和传输超时
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
					.build();
			StringEntity entity = new StringEntity(json, ContentType.create("application/json", Consts.UTF_8));

			// 采用post方式提交
			HttpPost httpPost = new HttpPost(prefix + url);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(entity);

			httpPost.setHeader("AppId", cmsAppId);
			httpPost.setHeader("Timestamp", timestamp);
			httpPost.setHeader("Signature", signature);

			response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			responseResult = EntityUtils.toString(resEntity, "utf-8");
			httpPost.releaseConnection();
		} catch (Throwable e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// long time1 = System.currentTimeMillis();
		// logger.info("Post请求httpUrl:" + httpUrl + "--------参数:" + json);
		// logger.info("Post请求返回:" + responseResult);
		// logger.info("Post请求耗时（ms）:" + (time1 - time0));
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
	public String httpPost(String httpUrl, String json) {
		logger.info("Post请求httpUrl:" + httpUrl + "--------参数:" + json);
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
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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

	public String yNoteHttpPost(String httpUrl, List<NameValuePair> nvps, String token, String json, String title,
			String content, String notebook) throws Exception {
		logger.info("Post请求httpUrl:" + httpUrl + ":" + token);
		String responseResult = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			// 设置请求和传输超时
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
					.build();
			// StringEntity entity = new StringEntity(json,
			// ContentType.create("application/json", Consts.UTF_8));
			StringBody titleSB = new StringBody(title, ContentType.TEXT_PLAIN);
			StringBody notebookSB = new StringBody(notebook, ContentType.TEXT_PLAIN);
			StringBody contentSB = new StringBody(content, ContentType.TEXT_PLAIN);
			// 采用post方式提交
			HttpPost httpPost = new HttpPost(httpUrl);
			httpPost.setConfig(requestConfig);

			if (nvps != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			}

			String oauth = "OAuth oauth_token=\"" + token + "\"";
			httpPost.setHeader("Authorization", oauth);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("notebook", notebookSB)
					.addPart("title", titleSB).addPart("content", contentSB).build();

			httpPost.setEntity(reqEntity);

			response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();

			responseResult = EntityUtils.toString(resEntity, "utf-8");
			httpPost.releaseConnection();
		} catch (Throwable e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseResult;
	}

	public String yNoteHttpPost(String httpUrl, List<NameValuePair> nvps, String token) throws Exception {
		logger.info("Post请求httpUrl:" + httpUrl + ":" + token);
		String responseResult = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			// 设置请求和传输超时
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
					.build();
					// StringEntity entity = new StringEntity(json,
					// ContentType.create("application/json", Consts.UTF_8));

			// 采用post方式提交
			HttpPost httpPost = new HttpPost(httpUrl);
			httpPost.setConfig(requestConfig);

			if (nvps != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			}
			// httpPost.setEntity(entity);

			String oauth = "OAuth oauth_token=\"" + token + "\"";
			httpPost.setHeader("Authorization", oauth);

			response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			responseResult = EntityUtils.toString(resEntity, "utf-8");
			httpPost.releaseConnection();
		} catch (Throwable e) {
			logger.error("调用接口出错：" + e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// long time1 = System.currentTimeMillis();
		// logger.info("Post请求httpUrl:" + httpUrl + "--------参数:" + json);
		// logger.info("Post请求返回:" + responseResult);
		// logger.info("Post请求耗时（ms）:" + (time1 - time0));
		return responseResult;
	}

	public String yNoteHttpGet(String url, String token) throws Exception {
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
			String oauth = "OAuth oauth_token=\"" + token + "\"";
			httpGet.setHeader("Authorization", oauth);

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
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			responseResult = EntityUtils.toString(resEntity, "utf-8");
			httpPost.releaseConnection();
		} catch (Throwable e) {
			logger.error("调用接口出错：" + e);
		} finally {
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
		try {
			// get请求
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(SocketTimeout < 5000 ? 5000 : SocketTimeout)
					.setConnectTimeout(ConnectTimeout < 5000 ? 5000 : ConnectTimeout).build();

			// 发送请求
			httpGet.setConfig(requestConfig);
			HttpResponse httpresponse = httpClient.execute(httpGet);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			responseResult = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			logger.error("调用接口出错：" + e);
		} finally {
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

		/*String url = "http://whty.bj.bcebos.com/mnote/b5d03e8e7ec242baaffc94fa1103b1e2888888/notebooks/3_212_5/1.png";
		//
		// System.out.println(getInstance().httpGet(url));
		// String url
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

		con.setRequestMethod("HEAD");

		// 从 HTTP 响应消息获取状态码

		System.out.println(con.getResponseCode());*/
		// return (con.getResponseCode() == HttpURLConnection.HTTP_OK);

		// String prefix = "http://res.t.huijiaoyun.com:30005/cms-gateway";
		// String url = "/createResource";
		// Map<String, Object> s = new HashMap<String, Object>();

		// s.put("dirName", "新建文件夹2");
		// s.put("parentId", "0");
		// s.put("ownerType", "0");
		// s.put("userId", "d5f980cff8374643b7f6a43dde8bcf07");

		// s.put("fid", "588737131917082624");
		// s.put("title", "11122");
		// s.put("userId", "d5f980cff8374643b7f6a43dde8bcf07");
		// s.put("resForm", "1");
		// s.put("ownerType", "0");
		// s.put("resSource", "0");
		// s.put("parentId", "0");

		// System.out.println(HttpUtils.getInstance().cmsHttpPost(prefix, url,
		// JSONObject.fromObject(s).toString()));

		// // 这里是平台为应用创建的AppId
		// String appId = "c13f0fd8fef541c5816034d2ef2214ba";
		//
		// // 这里是应用对应的密钥
		// String appSecret =
		// "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMDtm1ClAfpUHGKyyP/izajzQ3mKLWQgMctdxo17koypu+2X13mBBHDoM5TtBUv507EgGj5gIjYFa5ugDj2PDBEgHbiJV+Gu3hmp+ZobzwM64kHW0sJizAAyUrHoF8/zoajpRvsNXn91DNbkDL/rEEVJywPOmU0qs1MiVMSFQpG9AgMBAAECgYAls4khdk1vpNYzN2ruDl1QQdGSw7zhDyWL71Rou5hE4rp10LFr7hl3EvlZhgP99+E1gOd//vtQgTipHdsKjpS1wSqilW4cpNKxgvAsBm4329JLVOzUwVtKhFVspyB+9CmzWlaBNcYzTixBvOm+CSRMkMvANGXz/af1xyo3yNN5wQJBAOC3Q36FfVfmeDg06TGgDNzNJJmZTtk3roB/wAv0cG4NwsN8lzTtrcvs9oiDk+6vxMBVzEt84cpUJmF0qPKgz80CQQDbyXKzxol18ZE4fGlNSM1t2c2338RKHPeIDEsuFyNhqg58GzHjIGvebJOEJN4heFCfCizlHUBOzWfmCQGgR3mxAkEA0dvUeJAJ4tElbLhdHy8tU/dxOtDlxg4uNWsY2xFI/j5zqUTtoUxmho+iHRNkUABuaoC17bmO0/mKbnEYZp6v8QJAGfsHLw8iAqoqk3XJcNRRTFH5ymmOJ4CjFhb18B3WFd5nVe5VCFc1yRYmnfFJvMUfKp2mTvMVPftz87JO2eZ5EQJAUwUrMQ0PyZ/+w261/EHw7PvuyBhVafA/HZSvtea5eLFE5uLvEPFtZ9Ax3yaS94QrHnyTGS5g8RvfUlK7uYHq+Q==";
		//
		// // 调用创建文件夹接口
		// String prefix = "http://res.t.huijiaoyun.com:30005/cms-gateway";
		// String url = "/createFolder";
		//
		// // 时间戳
		// String timestamp = String.valueOf(System.currentTimeMillis());
		// // 认证字符串
		// String key = appId + "&POST&" + url + "&" + timestamp;
		// // 数字签名
		// CryptoServer cryptoServer = CryptoServer.getInstance(appSecret);
		// String signature = cryptoServer.signature(key);
		//
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		// HttpPost httpPost = new HttpPost(prefix + url);
		// // 设置认证头信息，这是关键
		// httpPost.setHeader("AppId", appId);
		// httpPost.setHeader("Timestamp", timestamp);
		// httpPost.setHeader("Signature", signature);
		//
		// // 设置http body
		// Map<String, Object> body = new HashMap<>();
		// body.put("dirName", "新建文件夹");
		// body.put("parentId", "0");
		// body.put("ownerType", "0");
		// body.put("userId", "d5f980cff8374643b7f6a43dde8bcf07");
		// StringEntity entity = new
		// StringEntity(JSONObject.fromObject(body).toString(), "utf-8");
		// entity.setContentEncoding("UTF-8");
		// entity.setContentType("application/json");
		// httpPost.setEntity(entity);
		//
		// CloseableHttpResponse response = null;
		//
		// try {
		// response = httpClient.execute(httpPost);
		// HttpEntity responseEntity = response.getEntity();
		// String ret = null;
		// if (responseEntity != null) {
		// ret = EntityUtils.toString(responseEntity, "UTF-8");
		// }
		// EntityUtils.consume(responseEntity);
		// System.out.println(ret);
		// } catch (ClientProtocolException ex) {
		// throw new RuntimeException("协议异常", ex);
		// } catch (IOException ex) {
		// throw new RuntimeException("执行请求时IO异常", ex);
		// } finally {
		// try {
		// if (response != null)
		// response.close();
		// if (httpClient != null)
		// httpClient.close();
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// }
		// }

	}

}