package com.whty.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.whty.common.exception.BusinessException;

public class CommonFunction {

	public static String clobToString(Clob clob) throws Exception {
		Reader is = clob.getCharacterStream();// 得到流
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
			sb.append(s).append("\n");
			s = br.readLine();
		}
		return sb.toString();
	}
	
	/**
	 * 判断对象是否为空
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isNotNull(Object o) {
		if (o == null) {
			return false;
		} else if (o instanceof String && "".equals(((String) o).trim())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 从页面的pageMap中取数据
	 * 
	 * @param o
	 * @return
	 */
	public static String getStringValueFromMap(Map map, String key) {
		Object o = map.get(key);
		if (o instanceof String[]) {
			return ((String[]) map.get(key))[0];
		} else if (o instanceof String) {
			return (String) o;
		} else {
			if (o != null) {
				return o.toString();
			} else {
				return null;
			}
		}
	}

	// *********************************lht*****************************************************
	public static final String DEFAULT_DATE_SAMPLE_FORMAT = "yyyy-MM-dd";

	public static final String DEFAULT_SAMPLE_FORMAT = "yyyy-MM-dd";

	public static final String DEFAULT_TIME_SAMPLE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final java.text.SimpleDateFormat DATE_FORMATTER = new java.text.SimpleDateFormat(
			DEFAULT_DATE_SAMPLE_FORMAT);

	private static final java.text.SimpleDateFormat TIME_FORMATTER = new java.text.SimpleDateFormat(
			DEFAULT_TIME_SAMPLE_FORMAT);

	private static final java.text.SimpleDateFormat DEFAULT_FORMATTER = new java.text.SimpleDateFormat(
			DEFAULT_SAMPLE_FORMAT);

	/**
	 * 获得日期类型的时间戳字符串
	 * 
	 * @param date
	 *            java.util.Date - 日期对象
	 * @return String - 包含指定日期的标准时间戳格式字符串
	 */
	public static String getTimeSampleString(java.util.Date date) {
		String s = null;
		if (date != null)
			s = TIME_FORMATTER.format(date);
		return s;
	}

	/**
	 * 获得日期类型的日期戳字符串
	 * 
	 * @param date
	 *            java.util.Date - 日期对象
	 * @return String - 包含指定日期的标准时间戳格式字符串
	 */
	public static String getDateSampleString(java.util.Date date) {
		if (date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}

	/**
	 * 获得日期类型的日期戳字符串
	 * 
	 * @param date
	 *            java.util.Date - 日期对象
	 * @return String - 包含指定日期的标准时间戳格式字符串
	 */
	public static String getSampleString(java.util.Date date) {
		if (date == null)
			return null;
		return DEFAULT_FORMATTER.format(date);
	}

	public static java.sql.Date getDateToDate(Date date) {
		String temp = null;
		temp = getSampleString(date);
		if (temp == null || temp.trim().length() < 10) {
			return null;
		}
		String year, month, day;
		year = temp.substring(0, 4);
		month = temp.substring(5, 7);
		day = temp.substring(8);
		StringBuffer str = new StringBuffer();
		str.append(year);
		str.append("-");
		str.append(month);
		str.append("-");
		str.append(day);
		java.sql.Date date1 = java.sql.Date.valueOf(str.toString());
		return date1;
	}

	public static Date getSampleDate(String date) {
		Date d = new Date();
		if (date == null)
			return null;
		try {
			d = DEFAULT_FORMATTER.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 获得日期类型的日期戳字符串
	 * 
	 * @param date
	 *            java.util.Date - 日期对象
	 * @param format
	 *            java.lang.String - 指定的日期格式
	 * @return String - 包含指定日期的标准时间戳格式字符串
	 */
	public static String getDateSampleString(java.util.Date date, String format) {
		if (date == null)
			return null;
		if (format == null)
			return getDateSampleString(date);
		java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(
				format);
		return dateFormater.format(date);
	}

	// *********************************lht*****************************************************
	public static java.util.Date setDateTime(java.util.Date date, int h, int m,
			int s) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.set(year, month, date, hourOfDay, minute, second)
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), h, m, s);
		return calendar.getTime();
	}

	public static Date getNextDate(Date edate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(edate);
		gc.add(5, 1);
		// logger.debug(gc.getTime());
		return gc.getTime();
	}

	public static String getWeek(Date edate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(edate);
		String[] arr = { "日", "一", "二", "三", "四", "五", "六" };
		int day = gc.get(Calendar.DAY_OF_WEEK) - 1;
		String week = "星期" + arr[day];
		return week;
	}
	
	/**
	 * 根据条件时间返回上月时间
	 * 
	 * @param time
	 * @return
	 */
	public static Date lastMonth(Date time) {
		String temp = getSampleString(time);
		if (temp == null || temp.trim().length() < 10) {
			return null;
		}
		String year, month;
		year = temp.substring(0, 4);
		month = temp.substring(5, 7);
		if (Integer.valueOf(month) == 1) {
			year = String.valueOf((Integer.valueOf(year) - 1));
			month = "12";
		} else {
			month = String.valueOf(Integer.valueOf(month) - 1);
		}
		StringBuffer str = new StringBuffer();
		str.append(year);
		str.append("-");
		str.append(month);
		str.append("-");
		str.append("01");
		java.sql.Date date = java.sql.Date.valueOf(str.toString());
		return date;
	}
	
	/**
	 * 设置Cookies
	 * @param request
	 * @param name
	 * @param value
	 * @param paramInt
	 * @throws UnsupportedEncodingException
	 */
	public static void setCookie(HttpServletResponse response,
			String name, String value, int maxAge)
			throws UnsupportedEncodingException {
		Cookie localCookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		localCookie.setMaxAge(maxAge);
		localCookie.setPath("/");
		response.addCookie(localCookie);
	}
	
	/**
	 * 从Cookies中获取参数
	 * @param paramHttpServletRequest
	 * @param paramString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getCookie(HttpServletRequest request, String name) throws UnsupportedEncodingException {
		Cookie[] cookies =  request.getCookies();
		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length; ++i){
			if (cookies[i].getName().equals(name)){
				return URLDecoder.decode(cookies[i].getValue(), "utf-8");
			}
		}
		return null;
	}
	
	public static String formatNumber(String pattern, double paramDouble) {
		DecimalFormat localDecimalFormat = new DecimalFormat(pattern);
		return localDecimalFormat.format(paramDouble);
	}

	public static String formatNumber(String pattern, long paramLong) {
		DecimalFormat localDecimalFormat = new DecimalFormat(pattern);
		return localDecimalFormat.format(paramLong);
	}
	//yyyy-MM-dd HH:mm:ss String to date
	public static Date getSampleTimeString(String date) {
		Date d = new Date();
		if (date == null)
			return null;
		try {
			d = TIME_FORMATTER.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 参数校验
	 * @param para
	 * @throws BusinessException
	 */
	public static void checkParam(Map para) throws BusinessException{
		  Set keySet = para.keySet();//返回键的集合 
		  Iterator it = keySet.iterator(); 
		  String key = null;
		  while(it.hasNext()) {     //第一种迭代方式取键值 
			  key = (String) it.next(); 
			  if(!CommonFunction.isNotNull(para.get(key))){
					throw new BusinessException("参数"+key+"不能为空");
			  }
		  } 
	}
	
	/**
	 * 读取请求内容
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String getRequestBody(ServletInputStream inputStream) throws IOException {
		return IOUtils.toString(inputStream, "UTF-8");
	}

	/**
     * 判断字符是否为空
     *
     * @param str 某字符串
     * @return 为null或为空串则返回true，否则返回false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


	public static void writeResp(HttpServletResponse response, String string) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		//处理中文乱码 @zhangchao
		response.setCharacterEncoding("utf-8");
		//response.setContentType("application/json; charset=utf-8");
		PrintWriter wr=response.getWriter();
		wr.write(string);
		wr.flush();
		wr.close();
	}

	public static void main(String[] args){
		
		String s = "2017_09_27_15_49_47";
		
//		Date startTime = CommonFunction.getSampleTimeString(s);
		
		
		String[] endTimeArr = s.split("_");
		String endTimeStr = endTimeArr[0] + "-" + endTimeArr[1] + "-" + endTimeArr[2];
//		System.out.println(endTimeStr);
		Date endTime = CommonFunction.getSampleDate(endTimeStr);
	}
	
	public static String mapValueToStr(Map map) {
		StringBuffer sb = new StringBuffer();
		 Set keySet = map.keySet();//返回键的集合 
		  Iterator it = keySet.iterator(); 
		  String key = null;
		  while(it.hasNext()) {     //第一种迭代方式取键值 
			  key = (String) it.next(); 
			  if(null!=map.get(key)&&!map.get(key).equals("")&&!map.get(key).equals("null")){
				  sb.append(key).append("_").append(map.get(key)).append("_");
			  }
		  } 
		return sb.toString();
	}
}
