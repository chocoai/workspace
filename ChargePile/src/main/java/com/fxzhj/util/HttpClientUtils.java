package com.fxzhj.util;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.gson.Gson;

/**
 * 类功能说明：httpclient工具类,基于httpclient 4.x
 * Title: HttpClientUtils.java
 */
public class HttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * post请求
     * @param url
     * @param formParams
     * @return
     */
    public static String doPost(String url, Map<String, String> formParams) {
        if (MapUtils.isEmpty(formParams)) {
            return doPost(url);
        }

        try {
        	HttpHeaders headers = new HttpHeaders();
        	MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        	headers.setContentType(type);
        	headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        	headers.set("Accept-Charset", "utf-8");  
        	headers.set("Content-type", "application/json; charset=utf-8");  //header的规定
        	HttpEntity<String> formEntity = new HttpEntity<String>(new Gson().toJson(formParams), headers);
            return RestClient.getClient().postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }

        return null;
    }
    
    public static String doPostForValues(String url, Map<String, String> formParams) {
        if (MapUtils.isEmpty(formParams)) {
            return doPost(url);
        }
        try {
		    MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
		    for (Map.Entry<String, String> entry : formParams.entrySet()) {    
		    	 requestEntity.add(entry.getKey(), entry.getValue());
		    }  
            return RestClient.getClient().postForObject(url, requestEntity, String.class);
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }
        return null;
    }

    /**
     * post请求
     * @param url
     * @return
     */
    public static String doPost(String url) {
        try {
        	HttpHeaders headers = new HttpHeaders();
        	MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        	headers.setContentType(type);
        	headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        	headers.set("Accept-Charset", "utf-8");  
        	headers.set("Content-type", "application/json; charset=utf-8");  //header的规定
        	HttpEntity<String> formEntity = new HttpEntity<String>(null, headers);
            return RestClient.getClient().postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }

        return null;
    }

    /**
     * get请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        try {
            return RestClient.getClient().getForObject(url, String.class);
        } catch (Exception e) {
            LOGGER.error("GET请求出错：{}", url, e);
        }

        return null;
    }

}