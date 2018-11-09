package com.whty.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HttpUtils {
	static final String charsetName = "UTF-8";

	public static String doGet(String url) throws MalformedURLException, IOException {
		HttpURLConnection urlconn = null;
		urlconn = (HttpURLConnection) new URL(url).openConnection();
		urlconn.getRequestProperties();
		urlconn.setRequestProperty("content-type", "text/html");
		urlconn.setRequestMethod("GET");
		urlconn.setConnectTimeout(30000);
		urlconn.setReadTimeout(30000);
		urlconn.setDoInput(true);

		BufferedReader rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));

		String temp = null;
		StringBuffer sb = new StringBuffer();
		temp = rd.readLine();
		while (temp != null) {
			sb.append(temp);
			temp = rd.readLine();
		}
		rd.close();
		urlconn.disconnect();

		return sb.toString();
	}

	public static String doPost(String url, String value) throws MalformedURLException, IOException {
		HttpURLConnection urlconn = null;
		urlconn = (HttpURLConnection) new URL(url).openConnection();
		urlconn.getRequestProperties();
		urlconn.setRequestProperty("content-type", "application/json");
		urlconn.setRequestMethod("POST");
		urlconn.setConnectTimeout(30000);
		urlconn.setReadTimeout(30000);
		urlconn.setDoInput(true);
		urlconn.setDoOutput(true);
		urlconn.getOutputStream().write(value.getBytes("utf-8"));
		urlconn.getOutputStream().close();

		BufferedReader rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));

		String temp = null;
		StringBuffer sb = new StringBuffer();
		temp = rd.readLine();
		while (temp != null) {
			sb.append(temp);
			temp = rd.readLine();
		}
		rd.close();
		urlconn.disconnect();

		return sb.toString();
	}

	public static String doPost(String url, String value, Map<String, String> header)
			throws MalformedURLException, IOException {
		HttpURLConnection urlconn = null;
		urlconn = (HttpURLConnection) new URL(url).openConnection();
		urlconn.getRequestProperties();
		urlconn.setRequestProperty("content-type", "application/json");
		urlconn.setRequestMethod("POST");
		urlconn.setConnectTimeout(30000);
		urlconn.setReadTimeout(30000);
		urlconn.setDoInput(true);
		urlconn.setDoOutput(true);

		for (String key : header.keySet()) {
			urlconn.setRequestProperty(key, header.get(key));
		}

		urlconn.getOutputStream().write(value.getBytes("utf-8"));
		urlconn.getOutputStream().close();

		BufferedReader rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));

		String temp = null;
		StringBuffer sb = new StringBuffer();
		temp = rd.readLine();
		while (temp != null) {
			sb.append(temp);
			temp = rd.readLine();
		}
		rd.close();
		urlconn.disconnect();

		return sb.toString();
	}

	public static String doGet(String url, Map<String, String> header) throws MalformedURLException, IOException {
		HttpURLConnection urlconn = null;
		urlconn = (HttpURLConnection) new URL(url).openConnection();
		urlconn.getRequestProperties();
		urlconn.setRequestProperty("content-type", "text/html");
		urlconn.setRequestMethod("GET");
		urlconn.setConnectTimeout(30000);
		urlconn.setReadTimeout(30000);
		urlconn.setDoInput(true);

		for (String key : header.keySet()) {
			// System.out.println("key= " + key + " and value= " +
			// header.get(key));
			urlconn.setRequestProperty(key, header.get(key));
		}

		BufferedReader rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));

		String temp = null;
		StringBuffer sb = new StringBuffer();
		temp = rd.readLine();
		while (temp != null) {
			sb.append(temp);
			temp = rd.readLine();
		}
		rd.close();
		urlconn.disconnect();

		return sb.toString();
	}
}
