package com.whty.assis.api.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class ZipUtil {
	public static String compress(String str) throws IOException {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		return out.toString("ISO-8859-1");
	}

	public static String uncompress(String str) throws IOException {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}

		return out.toString();
	}

	public static void main(String[] args) throws Exception {
		// String str = "张";

		// System.out.println("原长度：" + str.length());

		// String s = zipString(str);
		//
		// System.out.println(s);
		//
		// System.out.println(ungzipString(s));

		// System.out.println("压缩后：" + compress(str).length());
		//
		// System.out.println("解压缩：" + uncompress(compress(str)));

		String url = "http://119.29.229.105:8888/test";

		// String url =
		//
		// "http://test.wuhaneduyun.cn:20014/aamif/rest/user/9e5598420716428f9e688d61f01fc11d";

		// Map<String, Object> body = new HashMap<String, Object>();
		// body.put("account", "zuozhiwei");

		// JSONObject requestBody =
		// JSONObject.parseObject(JSONObject.toJSONString(body));

		// String result = ToolHttp.post(false, url, nu, null);

		String result = HttpUtils.getInstance().httpGet(url);
		// System.out.println("解压缩：" + uncompress(result));

		// ServletInputStream stream = request.getInputStream();

		// String result = ToolHttp.get(false, url);
		System.out.println(result);

		// System.out.println(compress("11"));

		DefaultHttpClient httpClient = new DefaultHttpClient();
		// // dns探测源ip
		//
		HttpPost post = new HttpPost("http://119.29.229.105:8888/test");
		post.addHeader("Referer", "http://webscan.360.cn/tools/dnslookup");
		post.addHeader("X-Requested-With", "XMLHttpRequest");
		post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		post.addHeader("Accept", "*/*");
		post.addHeader("Accept-Encoding", "gzip, deflate");
		post.addHeader("Accept-Language", "zh-cn,en-us;q=0.7,en;q=0.3");
		post.addHeader("Pragma", "no-cache");
		post.addHeader("Cache-Control", "no-cache");
		//
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		// params.add(new BasicNameValuePair("dns_ip", ip));
		// params.add(new BasicNameValuePair("domain_name", domain));
		// params.add(new BasicNameValuePair("dns_type", "A"));
		UrlEncodedFormEntity formEntity = null;
		HttpResponse response = null;
		String responseHtml = null;
		try {
			formEntity = new UrlEncodedFormEntity(params, "UTF-8");
			post.setEntity(formEntity);
			response = httpClient.execute(post);
			System.out.println(response.getEntity().getContentLength());
			InputStream is = response.getEntity().getContent();

			is = new GZIPInputStream(is);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			responseHtml = sb.toString();
			System.out.println(responseHtml);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
