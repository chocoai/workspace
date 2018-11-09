package com.yhcrt.iHealthCloud.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Tom
 */
public class DateUtils {
	private StringBuffer buffer = new StringBuffer();

	private static String ZERO = "0";

	private static DateUtils date;

	public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	public static SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

	public String getNowString() {
		Calendar calendar = getCalendar();
		buffer.delete(0, buffer.capacity());
		buffer.append(getYear(calendar));

		if (getMonth(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMonth(calendar));

		if (getDate(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getDate(calendar));
		if (getHour(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getHour(calendar));
		if (getMinute(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMinute(calendar));
		if (getSecond(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getSecond(calendar));
		return buffer.toString();
	}

	private static int getDateField(Date date, int field) {
		Calendar c = getCalendar();
		c.setTime(date);
		return c.get(field);
	}

	public static int getYearsBetweenDate(Date begin, Date end) {
		int bYear = getDateField(begin, Calendar.YEAR);
		int eYear = getDateField(end, Calendar.YEAR);
		return eYear - bYear;
	}

	public static int getMonthsBetweenDate(Date begin, Date end) {
		int bMonth = getDateField(begin, Calendar.MONTH);
		int eMonth = getDateField(end, Calendar.MONTH);
		return eMonth - bMonth;
	}

	public static int getWeeksBetweenDate(Date begin, Date end) {
		int bWeek = getDateField(begin, Calendar.WEEK_OF_YEAR);
		int eWeek = getDateField(end, Calendar.WEEK_OF_YEAR);
		return eWeek - bWeek;
	}

	public static int getDaysBetweenDate(Date begin, Date end) {
		return (int) ((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24));
	}

	public static void main(String args[]) {
		System.out.println(getSpecficDateStart(new Date(), 288));
		System.out.println(dateToString19(new Timestamp(System.currentTimeMillis())));
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date date = sdf.parse("10/23/1989");
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取date年后的amount年的第一天的开始时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficYearStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, amount);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date年后的amount年的最后一天的终止时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficYearEnd(Date date, int amount) {
		Date temp = getStartDate(getSpecficYearStart(date, amount + 1));
		Calendar cal = Calendar.getInstance();
		cal.setTime(temp);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 获取date月后的amount月的第一天的开始时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficMonthStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, amount);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取当前自然月后的amount月的最后一天的终止时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficMonthEnd(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getSpecficMonthStart(date, amount + 1));
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 获取date周后的第amount周的开始时间（这里星期一为一周的开始）
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficWeekStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		cal.add(Calendar.WEEK_OF_MONTH, amount);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date周后的第amount周的最后时间（这里星期日为一周的最后一天）
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficWeekEnd(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		cal.add(Calendar.WEEK_OF_MONTH, amount);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return getFinallyDate(cal.getTime());
	}

	public static Date getSpecficDateStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, amount);
		return getStartDate(cal.getTime());
	}

	public static Date getSpecficDateEnd(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, amount);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 得到指定日期的一天的的最后时刻23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFinallyDate(Date date) {
		String temp = format.format(date);
		temp += " 23:59:59";

		try {
			return format1.parse(temp);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 得到指定日期的一天的开始时刻00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {
		String temp = format.format(date);
		temp += " 00:00:00";

		try {
			return format1.parse(temp);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 *            指定比较日期
	 * @param compareDate
	 * @return
	 */
	public static boolean isInDate(Date date, Date compareDate) {
		if (compareDate.after(getStartDate(date)) && compareDate.before(getFinallyDate(date))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 获取两个时间的差值秒
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Integer getSecondBetweenDate(Date d1, Date d2) {
		Long second = (d2.getTime() - d1.getTime()) / 1000;
		return second.intValue();
	}

	private int getYear(Calendar calendar) {
		return calendar.get(Calendar.YEAR);
	}

	private int getMonth(Calendar calendar) {
		return calendar.get(Calendar.MONDAY) + 1;
	}

	private int getDate(Calendar calendar) {
		return calendar.get(Calendar.DATE);
	}

	private int getHour(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	private int getMinute(Calendar calendar) {
		return calendar.get(Calendar.MINUTE);
	}

	private int getSecond(Calendar calendar) {
		return calendar.get(Calendar.SECOND);
	}

	private static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public static DateUtils getDateInstance() {
		if (date == null) {
			date = new DateUtils();
		}
		return date;
	}

	/**
	 * 是否是今天。根据System.currentTimeMillis() / 1000 / 60 / 60 / 24计算。
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		long day = date.getTime() / 1000 / 60 / 60 / 24;
		long currentDay = System.currentTimeMillis() / 1000 / 60 / 60 / 24;
		return day == currentDay;
	}

	/**
	 * 
	 * @param date
	 *            判断是否是本周，取出日期，依据日期取出该日所在周所有日期，在依据这些日期是否和本日相等
	 * @return
	 */
	public static boolean isThisWeek(Date date) {
		List<Date> dates = dateToWeek(date);
		Boolean flag = false;
		for (Date d : dates) {
			if (isToday(d)) {
				flag = true;
				break;
			} else {
				continue;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param date
	 *            判断是否是本月的日期
	 * @return
	 */
	public static boolean isThisMonth(Date date) {
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.setTime(date);
		int year = myCalendar.get(Calendar.YEAR) - 1900;
		int month = myCalendar.get(Calendar.MONTH);
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) - 1900 == year && calendar.get(Calendar.MONTH) == month;
	}

	/**
	 * 
	 * @param date
	 *            判断是否是本年的日期
	 * @return
	 */
	public static boolean isThisYear(Date date) {
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.setTime(date);
		int year = myCalendar.get(Calendar.YEAR) - 1900;
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) - 1900 == year;
	}

	/**
	 * 
	 * @param mdate
	 *            取出该日的一周所有日期
	 * @return
	 */
	public static List<Date> dateToWeek(Date mdate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mdate);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		Date fdate;
		List<Date> list = new ArrayList<Date>();
		Long fTime = mdate.getTime() - day * 24 * 3600000;
		for (int i = 0; i < 7; i++) {
			fdate = new Date();
			fdate.setTime(fTime + (i * 24 * 3600000));
			list.add(i, fdate);
		}
		return list;
	}

	public static Date afterDate(Date date, Integer after) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + after);
		return calendar.getTime();
	}

	/**
	 * 字符串转日期(yyyyMMddHHmmss)
	 */
	public static Date stringToDate14(String str) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转日期(yyyyMMdd)
	 */
	public static Date stringToDate8(String str) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转日期(yyyy-MM-dd HH:mm:ss)
	 */
	public static Date stringToDate19(String str) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转日期
	 */
	public static Date stringToDateByPattern(String str, String pattern) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期转字符串(yyyyMMddHHmmss)
	 */
	public static String dateToString14(Date date) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = "";
		if (date != null) {
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 日期转字符串(yyyy-MM-dd HH:mm:ss)
	 */
	public static String dateToString19(Date date) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = "";
		if (date != null) {
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 获取当前时间字符串(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Timestamp(System.currentTimeMillis()));
		return dateStr;
	}

	/**
	 * 获取当天日期字符串(yyyy-MM-dd)
	 * 
	 * @return
	 */
	public static String getCurrentDayString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Timestamp(System.currentTimeMillis()));
		return dateStr;
	}

	/**
	 * 日期转字符串(yyyyMMdd)
	 */
	public static String dateToString8(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyyMMdd");
	}

	/**
	 * 日期转字符串
	 */
	public static String dateToStringPattern(Date date, String pattern) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateStr = "";
		if (date != null) {
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 格式化时间(yyyy-MM-dd HH:mm)
	 */
	public static String getTime(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 格式化时间(yyyy-MM-dd HH:mm:ss)
	 */
	public static String getTimeToSS(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化时间(MM/dd HH:mm)
	 */
	public static String getTime2(Date date) throws RuntimeException {
		return getStringFromDate(date, "MM/dd HH:mm");
	}

	/**
	 * 格式化时间(yyyyMMddHHmm)
	 */
	public static String getTime3(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyyMMddHHmm");
	}

	/**
	 * 格式化日期(yyyy-MM-dd)
	 */
	public static String getDate(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyy-MM-dd");
	}

	/**
	 * 日期转字符串
	 */
	public static String getStringFromDate(Date date, String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String result = "";
		if (date != null) {
			result = dateFormat.format(date);
		}
		return result;
	}

	/**
	 * 获取距离当前时间
	 */
	public static String getPassTime(Date date) throws RuntimeException {
		String str = "";

		long now = new Date().getTime();
		long difference = now - date.getTime();
		long second = 1000l;
		long minute = 60000l;
		long hour = 3600000l;
		long day = 86400000l;
		long month = 2592000000l;
		long year = 31536000000l;

		if (difference < minute) {
			str = difference / second + "秒前";
		} else if (difference < hour) {
			str = difference / minute + "分" + difference % minute / second + "秒前";
		} else if (difference < day) {
			str = difference / hour + "小时" + difference % hour / minute + "分前";
		} else if (difference < month) {
			str = difference / day + "天" + difference % day / hour + "小时前";
		} else if (difference < year) {
			str = difference / month + "月" + difference % month / day + "天前";
		} else {
			str = difference / year + "年" + difference % year / month + "月前";
		}
		return str;
	}

	/**
	 * 分钟转时间字符串
	 */
	public static String minute2Time(long num) {
		String str = "";
		long difference = num;
		long hour = 60l;
		long day = 1440l;
		long month = 43200l;
		long year = 525600l;

		if (difference < hour) {
			str = num + "分钟";
		} else if (difference < day) {

			String min_val = difference % hour != 0 ? (difference % hour + "分钟") : "";
			str = (difference / hour + "小时") + min_val;

		} else if (difference < month) {

			String min_val = difference % hour != 0 ? (difference % hour + "分钟") : "";
			String hour_val = (difference % day / hour) != 0 ? (difference % day / hour) + "小时" : "";
			str = (difference / day + "天") + hour_val + min_val;

		} else if (difference < year) {

			String hour_val = (difference % day / hour) != 0 ? (difference % day / hour) + "小时" : "";
			String day_val = (difference % month / day) != 0 ? (difference % month / day) + "天" : "";
			str = (difference / month + "月") + day_val + hour_val;

		} else {

			String day_val = (difference % month / day) != 0 ? (difference % month / day) + "天" : "";
			String month_val = (difference % year / month) != 0 ? (difference % year / month) + "月" : "";
			str = difference / year + "年" + month_val + day_val;

		}
		return str;
	}

	/**
	 * 收费时段
	 */
	public static String getFeeShortTime(String time) throws RuntimeException {
		String result = "";
		if (StringUtils.isNotBlank(time) && time.length() == 6) {
			result = time.substring(0, 2) + ":" + time.substring(2, 4);
		}
		return result;
	}

	/**
	 * 字符串转Calendar(yyyyMMddHHmmss)
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Calendar getCalendar(String str) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringToDate14(str));
		return cal;
	}

	/**
	 * 计算两个日期字符串相隔天数(yyyyMMdd)
	 */
	public static int daysBetween(String sDate, String eDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(sDate));
		long time1 = cal.getTimeInMillis();

		cal.setTime(sdf.parse(eDate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期字符串相隔天数(yyyyMMddHHmm)
	 */
	public static int daysBetween2(String sDate, String eDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(sDate));
		long time1 = cal.getTimeInMillis();

		cal.setTime(sdf.parse(eDate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获取去年年份
	 */
	public static List<Integer> getLatestYear(int num) {
		List<Integer> years = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < num; i++) {
			years.add(calendar.get(Calendar.YEAR));
			calendar.add(Calendar.YEAR, -1);
		}
		return years;
	}

	/**
	 * 获取年份列表
	 */
	public static List<Integer> getYearList() {
		List<Integer> years = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 50; i++) {
			years.add(calendar.get(Calendar.YEAR));
			calendar.add(Calendar.YEAR, -1);
		}
		return years;
	}

	/**
	 * 获取月份列表
	 */
	public static List<DateUtilHelper> getMonthListClass() {
		List<DateUtilHelper> helpers = new ArrayList<DateUtilHelper>();
		for (int i = 1; i <= 12; i++) {
			DateUtilHelper helper = new DateUtilHelper();
			helper.setKey(i / 10 == 0 ? ("0" + i) : (i + ""));
			helper.setValue(i + "月");
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			if (month == i) {
				helper.setSelected(true);
			}
			helpers.add(helper);
		}
		return helpers;
	}

	/**
	 * 获取月份列表
	 */
	public static List<String> getMonthList() {
		List<String> months = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			months.add(i / 10 == 0 ? ("0" + i) : String.valueOf(i));
		}
		return months;
	}

	/**
	 * 获取日期列表
	 */
	public static List<String> getDayList() {
		List<String> days = new ArrayList<String>();
		for (int i = 1; i <= 31; i++) {
			days.add(i / 10 == 0 ? ("0" + i) : String.valueOf(i));
		}
		return days;
	}

	/**
	 * 获取日期列表
	 */
	public static List<String> getDayList(int day) {
		List<String> days = new ArrayList<String>();
		for (int i = 1; i <= day; i++) {
			days.add(i / 10 == 0 ? ("0" + i) : String.valueOf(i));
		}
		return days;
	}

	/**
	 * 获取昨天日期
	 */
	public static String getYesterday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);

		return sdf.format(cal.getTime());
	}

	/**
	 * 获取一月中的第几天
	 */
	public static int getDayByCalenday(Calendar cal) {
		int month = -1;
		if (cal != null) {
			month = cal.get(Calendar.DAY_OF_MONTH);
		}
		return month;
	}

	/**
	 * 获取明天的日期
	 * 
	 * @param cal
	 * @return
	 */
	public static String getTomorrowDate() {
		String tomorrow = "";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date = calendar.getTime();
		tomorrow = dateToString8(date);
		return tomorrow;
	}

	/**
	 * 当前时间加减分钟
	 * 
	 * @param min
	 * @return
	 */
	public static String getLastMinDate(int min) {
		String lastMinDate = "";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, min);
		Date date = calendar.getTime();
		lastMinDate = dateToString14(date);
		return lastMinDate;
	}

	/**
	 * 时间错：1442304019000 转化成时间：2015-09-15 16:00:15
	 * 
	 * @param stamp
	 * @return
	 */
	public static String stamp2Date(Long stamp) {
		Date nowTime = new Date(stamp);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}

	public static Date StringToDate(String dateStr, String pattern) {
		try {
			SimpleDateFormat sdFormatter = new SimpleDateFormat(pattern);
			return sdFormatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static class DateUtilHelper {
		private Boolean selected = false;

		private String key;

		private String value;

		public Boolean getSelected() {
			return selected;
		}

		public void setSelected(Boolean selected) {
			this.selected = selected;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

}
