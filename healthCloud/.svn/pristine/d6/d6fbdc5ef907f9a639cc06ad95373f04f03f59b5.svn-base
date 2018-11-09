package com.yhcrt.healthcloud.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import com.yhcrt.healthcloud.common.Constants;

/**
 * @Description:发送短信
 * @author rpf
 * @date 2016年7月20日 下午4:02:17
 */
public class SendSMSUtil {

	private static final String URL = "http://sapi.253.com/msg/";// 应用地址

	private static final String ACCOUNT = "whyhcx";// 账号

	private static final String PASSWORD = "Tch147269";// 密码

	private static final boolean NEEDSTATUS = true;// 是否需要状态报告，需要true，不需要false

	private static final String EXTNO = null;// 扩展码

	//public static final String TIME_OUT = "5"; // 发送验证码超时时限(分钟)

	public static String batchSend(String url, String account, String pswd, String mobile, String msg,
			boolean needstatus, String extno) throws Exception {
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { new NameValuePair("account", account),
					new NameValuePair("pswd", pswd), new NameValuePair("mobile", mobile),
					new NameValuePair("needstatus", String.valueOf(needstatus)), new NameValuePair("msg", msg),
					new NameValuePair("extno", extno), });
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return URLDecoder.decode(baos.toString(), "UTF-8");
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} finally {
			method.releaseConnection();
		}
	}

	public static void send(String captcha, String phoneNumber) throws Exception {

		String msg = "您好，您的验证码是" + captcha + "，在" + Constants.SMS_TIME_OUT + "分钟内有效。如非本人操作请忽略此短信。";// 短信内容
		String returnString = SendSMSUtil.batchSend(URL, ACCOUNT, PASSWORD, phoneNumber, msg, NEEDSTATUS, EXTNO);
		System.out.println(returnString);
		String regex = ",|，|\\s+";
		String[] result= returnString.split(regex);
		if(!"0".equalsIgnoreCase(result[1])){
			throw new Exception("验证码发送失败");
		}
		
	}

	public static void main(String[] args) {
		try {
			String captcha = PhoneSecurityCode.getSecurityCode();
			SendSMSUtil.send(captcha,"13297930710");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
