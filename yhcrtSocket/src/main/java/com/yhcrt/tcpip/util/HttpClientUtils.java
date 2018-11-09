package com.yhcrt.tcpip.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 类功能说明：httpclient工具类,基于httpclient 4.x
 * Title: HttpClientUtils.java
 */
public class HttpClientUtils {

    private static final Logger LOGGER =Logger.getLogger(HttpClientUtils.class);  

    public static boolean httpPostWithJson(JSONObject jsonObj,String url){
        boolean isSuccess = false;
        HttpPost post = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            // 设置超时时间
//            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
//            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
//            String sessionId = getSessionId();
//            post.setHeader("SessionId", sessionId);              
            // 构建消息实体
            StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);   
            HttpResponse response = httpClient.execute(post);
            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
            	LOGGER.info("请求出错: "+statusCode);
                isSuccess = false;
            }else{
            	isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }finally{
            if(post != null){
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }
    
    
    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.parseObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
            	LOGGER.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
        	LOGGER.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }
    
	/**
	 * 通过request获取post传输过来的json
	 * @param request
	 * @return
	 */
	public static String getJsonString(HttpServletRequest request) {
		BufferedReader br;
		String s;
		try {
			br = request.getReader();
			StringBuffer sb = new StringBuffer();
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    
    

    // 构建唯一会话Id
    public static String getSessionId(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }
    
    public static void main(String[] args) {
    	String POST_URL = "http://api.ejyhealth.com:9995/yhcrtSocket/setting/api";
    	JSONObject jsonImei = new JSONObject();
        jsonImei.put("imei", "888830000025327");
        JSONObject user = new JSONObject(); 
        user.put("sid", "SonlineNum"); 
        user.put("biz", jsonImei);
    	httpPostWithJson(user,POST_URL);
	}
}