package com.yhcrt.healthcloud.util;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author
 *
 */
public class HttpUtil {
	private static final Logger logger = LogManager.getLogger();

	public static final String SOCKET_URL = "http://api.ejyhealth.com:9995/yhcrtSocket/setting/api";

	public static final String httpClientPost(String url) {
		String result = "";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		try {
			client.executeMethod(getMethod);
			result = getMethod.getResponseBodyAsString();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			getMethod.releaseConnection();
		}
		return result;
	}

	public static final String httpClientPost(String url, ArrayList<NameValuePair> list) {
		String result = "";
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		try {
			NameValuePair[] params = new NameValuePair[list.size()];
			for (int i = 0; i < list.size(); i++) {
				params[i] = list.get(i);
			}
			postMethod.addParameters(params);
			client.executeMethod(postMethod);
			result = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}

	@Async
	public void httpPostWithJson(JSONObject jsonObj, String url) {
		HttpPost post = null;
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
			// 设置超时时间
			// httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
			// 2000);
			// httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
			// 2000);
			post = new HttpPost(url);
			// 构造消息头
			post.setHeader("Content-type", "application/json; charset=utf-8");
			post.setHeader("Connection", "Close");
			// String sessionId = getSessionId();
			// post.setHeader("SessionId", sessionId);
			// 构建消息实体
			StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
			entity.setContentEncoding("UTF-8");
			// 发送Json格式的数据请求
			entity.setContentType("application/json");
			post.setEntity(entity);
			HttpResponse response = httpClient.execute(post);
			// 检验返回码
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("httpPostWithJson statusCode error");
			} else {
				logger.error("httpPostWithJson success");
			}
		} catch (Exception e) {
			logger.error("httpPostWithJson error");
		} finally {
			if (post != null) {
				try {
					post.releaseConnection();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String httpPostJson(JSONObject jsonObj, String url) {
		HttpPost post = null;
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			post = new HttpPost(url);
			// 构造消息头
			post.setHeader("Content-type", "application/json; charset=utf-8");
			post.setHeader("Connection", "Close");
			// 构建消息实体
			StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
			entity.setContentEncoding("UTF-8");
			// 发送Json格式的数据请求
			entity.setContentType("application/json");
			post.setEntity(entity);
			HttpResponse response = httpClient.execute(post);
			// 检验返回码
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				return EntityUtils.toString(resEntity).toString();
			}
		} catch (Exception e) {
			logger.error("httpPostJson error");
		} finally {
			if (post != null) {
				try {
					post.releaseConnection();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

}
