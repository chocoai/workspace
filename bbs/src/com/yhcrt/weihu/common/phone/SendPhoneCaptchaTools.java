package com.yhcrt.weihu.common.phone;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendPhoneCaptchaTools {
	
	private static final String APP_KEY = "5eb609065c9d450370f8764e2759927e";
	private static final String APP_SECRET = "77cc38a5f2ce";
	private static final String NONCE = "12345";
	private static final String URL = "https://api.netease.im/sms/sendtemplate.action";//发送模板短信
	private static final String TEMPLATE_ID = "3037018";
	
	public static JSONObject sendCaptcha(String captcha,JSONArray json,JSONArray params) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URL);
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE ,curTime);
		// 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("mobiles", json.toString()));
        nvps.add(new BasicNameValuePair("templateid", TEMPLATE_ID));
        nvps.add(new BasicNameValuePair("params", params.toString()));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        // 打印执行结果
        String sss = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(sss);
        return JSONObject.fromObject(sss);
	}
	
    public static void main(String[] args) throws Exception{
    	
    	JSONArray json = new JSONArray();
    	json.add("18963984150");
    	JSONArray params = new JSONArray();
        params.add("1234");
        String captcha = "yzm";
        params.add(captcha);
    	JSONObject o = sendCaptcha("201611", json,params);
    	System.out.println(o.toString());
    	String code = o.optString("code");
    	if(code.equals("200")){
    		System.out.println("发送成功了");
    	}else{
    		System.out.println("code:"+code);
    		System.out.println("msg:"+o.optString("msg"));
    		System.out.println("obj:"+o.optString("obj"));
    	}
    }
}