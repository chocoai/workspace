package com.whty.mxbj.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtils {
	// private static Logger logger = Logger.getLogger(TimeUtil.class);

	private static Logger logger = LoggerFactory.getLogger(TimeUtils.class);

	public static final String STR_DATETIME_PATTERN = "yyyyMMddHHmmss";

	public static final String STR_DATETIME_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss";

	public static final String DAY_FORMAT_1 = "yyyy-MM-dd";

	public static final String DAY_FORMAT_2 = "yyyyMMdd";

	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DAY_FORMAT_3 = "yyyy-MM-dd HH:mm";

	public static final long MILLS_PER_DAY = 1000 * 60 * 60 * 24;

	public static final long MINUTE = 1000 * 60;

	// 判断两个日期是否在同一周
	boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// Date s = string2Date("20121111101010",STR_DATETIME_PATTERN);
		// String mu = "20121111101010".substring(0, 8);
		// String ss="20121111101010".substring(8);
		// System.out.println(getSum(new Date()));
		// System.out.println(getSta(new Date()));
		//
		/*
		 * Date startDate = getCurrentMonthStartDate();
		 * 
		 * System.out.println(dateTime2String(startDate));
		 */
		//
		// Date endDate = getCurrentMonthEndDate();
		//
		// System.out.println(dateTime2String(endDate));

		// Calendar cal = Calendar.getInstance();
		// int year = cal.get(Calendar.YEAR);
		// int month = cal.get(Calendar.MONTH)+1;
		//
		// System.out.println(year+":"+month);

		// Calendar cal = Calendar.getInstance();
		//
		//// cal.set(Calendar.MONTH, Calendar.MONTH-1);
		// cal.add(Calendar.MONTH, -1);
		//
		// System.out.println(cal.get(Calendar.MONTH)+1+":"+cal.get(Calendar.YEAR));
		//
		// System.out.println(dateTime2String(cal.getTime()));

		// BigDecimal bigDecimal = new BigDecimal(6);
		//
		// BigDecimal bigDecimal2 = new BigDecimal(10000);
		//
		// System.out.println(BigDecimalUtils.getPrettyNumber(bigDecimal.divide(bigDecimal2).multiply(new
		// BigDecimal(100)).toString()));

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);

		System.out.println(dateTime2String(cal.getTime()));

	}

	// 产生周序列
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0 " + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;

	}

	// 获得周一的日期
	public static String getMonday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd ").format(c.getTime());
	}

	public static String dateTime2String(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
		return simpleDateFormat.format(date);
	}

	// 获得周五的日期
	public static String getFriday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		return new SimpleDateFormat("yyyy-MM-dd ").format(c.getTime());

	}

	public static List<String> getMonth() {
		List<String> monthlst = new ArrayList<String>();

		monthlst.add("01");
		monthlst.add("02");
		monthlst.add("03");
		monthlst.add("04");
		monthlst.add("05");
		monthlst.add("06");
		monthlst.add("07");
		monthlst.add("08");
		monthlst.add("09");
		monthlst.add("10");
		monthlst.add("11");
		monthlst.add("12");

		return monthlst;
	}

	/**
	 * 得到当前年开始时间
	 * 
	 * @return
	 */
	public static Date getCurrentYearStartDate() {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.YEAR));
		// int month = cal.get(Calendar.MONTH) + 1;
		// if (month < 10) {
		// sb.append("0").append(month);
		// } else {
		// sb.append(month);
		// }

		sb.append("01");
		sb.append("01");

		sb.append("00").append("00").append("00");

		return new Date(dateTimeStrToMilsec(sb.toString(), null));
	}

	/**
	 * 得到当前年结束时间
	 * 
	 * @return
	 */
	public static Date getCurrentYearEndDate() {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.YEAR));

		sb.append("12");
		sb.append("31");
		sb.append("23").append("59").append("59");

		return new Date(dateTimeStrToMilsec(sb.toString(), null));
	}

	public static Date getMonthStartDate(int month) {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.YEAR));
		if (month < 10) {
			sb.append("0").append(month);
		} else {
			sb.append(month);
		}

		sb.append("01");

		sb.append("00").append("00").append("00");

		return new Date(dateTimeStrToMilsec(sb.toString(), null));
	}

	public static Date getMonthEndDate(int month) {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.YEAR));
		if (month < 10) {
			sb.append("0").append(month);
		} else {
			sb.append(month);
		}

		sb.append(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		sb.append("23").append("59").append("59");

		return new Date(dateTimeStrToMilsec(sb.toString(), null));
	}

	/**
	 * 得到当月开始时间
	 * 
	 * @return
	 */
	public static Date getCurrentMonthStartDate() {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.YEAR));
		int month = cal.get(Calendar.MONTH) + 1;
		if (month < 10) {
			sb.append("0").append(month);
		} else {
			sb.append(month);
		}

		sb.append("01");

		sb.append("00").append("00").append("00");

		return new Date(dateTimeStrToMilsec(sb.toString(), null));
	}

	/**
	 * 得到当月结束时间
	 * 
	 * @return
	 */
	public static Date getCurrentMonthEndDate() {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.YEAR));
		int month = cal.get(Calendar.MONTH) + 1;
		if (month < 10) {
			sb.append("0").append(month);
		} else {
			sb.append(month);
		}

		sb.append(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		sb.append("23").append("59").append("59");

		return new Date(dateTimeStrToMilsec(sb.toString(), null));
	}

	public static String getSta(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return new SimpleDateFormat("yyyyMMdd").format(c.getTime());

	}

	public static String getSum(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		return new SimpleDateFormat("yyyyMMdd").format(c.getTime());

	}

	/**
	 * 将日期时间的字符串转化为毫秒数
	 * 
	 * @param strDateTime
	 *            ,日期时间字符串,格式为pattern的格式
	 * @param pattern
	 *            日期时间字符串的格式,可为空,默认为"yyyyMMddHHmmss"
	 * @return 毫秒数
	 */
	public static long dateTimeStrToMilsec(String strDateTime, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat((pattern == null) ? STR_DATETIME_PATTERN : pattern);

		try {
			return sdf.parse(strDateTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();

			return 0;
		}
	}

	/**
	 * 将日期时间的字符串转化为秒数
	 * 
	 * @param strDateTime
	 *            ,日期时间字符串,格式为pattern的格式
	 * @param pattern
	 *            日期时间字符串的格式,可为空,默认为"yyyyMMddHHmmss"
	 * @return 秒数
	 */
	public static long dateTimeStrToSecond(String strDateTime, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat((pattern == null) ? STR_DATETIME_PATTERN : pattern);

		try {
			return sdf.parse(strDateTime).getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();

			return 0;
		}
	}

	/**
	 * 将毫秒数日期时间转化为字符串
	 * 
	 * @param milsecDateTime
	 *            ,毫秒数日期时间
	 * @param pattern
	 *            日期时间字符串的格式,可为空,默认为"yyyyMMddHHmmss"
	 * @return
	 */
	public static String dateTimeMilsecToStr(long milsecDateTime, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat((pattern == null) ? STR_DATETIME_PATTERN : pattern);
		Date dateTime = new Date(milsecDateTime);
		return (dateTime == null) ? null : sdf.format(dateTime);
	}

	/**
	 * 将日期时间转化为指定Pattern的字符串
	 * 
	 * @param dateTime
	 *            ,日期时间
	 * @param pattern
	 *            ，格式,默认“yyyyMMddHHmmss”
	 * @return
	 */
	public static String dateTimeToStr(Date dateTime, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat((pattern == null) ? STR_DATETIME_PATTERN : pattern);

		return (dateTime == null) ? null : sdf.format(dateTime);
	}

	/**
	 * 将时间字符串从原格式转化为目标格式
	 * 
	 * @param dateTimeStr
	 *            ,时间字符串
	 * @param srcPattern
	 *            ，原格式
	 * @param destPattern
	 *            ，目标格式
	 * @return
	 */
	public static String dateTimeStrChangePattern(String dateTimeStr, String srcPattern, String destPattern) {
		if (dateTimeStr == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(srcPattern);
		Date date;

		try {
			date = sdf.parse(dateTimeStr);
			sdf.applyPattern(destPattern);

			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * Get current date
	 * 
	 * @return java.util.Date
	 */
	public static java.util.Date getCurrentTime() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static java.sql.Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Get current year
	 * 
	 * @return int
	 */
	public static int getCurrentYear() {
		return getYear(getCurrentTime());
	}

	/**
	 * Get current month
	 * 
	 * @return int
	 */
	public static int getCurrentMonth() {

		return getMonth(getCurrentTime());
	}

	public static int getMonth(Date time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		return c.get(Calendar.MONTH);
	}

	/**
	 * Get current minute
	 * 
	 * @return int
	 */
	public static int getCurrentMinute() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MINUTE);
	}

	/**
	 * Get current second
	 * 
	 * @return int
	 */
	public static int getCurrentSecond() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.SECOND);
	}

	/**
	 * Get current second
	 * 
	 * @return int
	 */
	public static int getCurrentMillSecond() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MILLISECOND);
	}

	public static Date getCurrentStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	public static Date getCurrentEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

	/**
	 * Get current day in the month
	 * 
	 * @return int
	 */
	public static int getCurrentDay() {
		return getDay(TimeUtils.getCurrentTime());
	}

	public static int getDay(Date time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Get current hour
	 * 
	 * @return int
	 */
	public static int getCurrentHour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Construct the Date object according to the string and the specifed
	 * format.
	 * 
	 * @param dateValue
	 * @param dateFormat
	 * @return Date
	 */
	public static java.util.Date string2Date(String dateValue, String dateFormat) {
		return string2Date(dateValue, dateFormat, null);
	}

	/**
	 * Parse string to date according to the specified format,if exception
	 * occurs,return the specified default value
	 * 
	 * @param dateValue
	 * @param dateFormat
	 * @param defaultValue
	 * @return date
	 */
	public static java.util.Date string2Date(String dateValue, String dateFormat, Date defaultValue) {
		java.util.Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			date = sdf.parse(dateValue);
		} catch (Exception eDate) {
			return defaultValue;
		}
		return date;
	}

	/**
	 * Parse the given string into date.
	 * 
	 * @param dateValue
	 * @return Date
	 */
	public static Date string2Date(String dateValue) {
		if (dateValue == null)
			return null;
		if (dateValue.length() == DAY_FORMAT_1.length())
			return string2Date(dateValue, DAY_FORMAT_1);
		else if (dateValue.length() == TIME_FORMAT.length())
			return string2Date(dateValue, TIME_FORMAT);
		else
			return null;
	}

	/**
	 * Parse string to date according to the specified format,if exception
	 * occurs,return null
	 * 
	 * @param dateValue
	 * @param defaultValue
	 * @return
	 */
	public static Date string2Date(String dateValue, Date defaultValue) {
		return string2Date(dateValue, DAY_FORMAT_1, defaultValue);
	}

	/**
	 * Adds the specified (signed) amount of time to the date time field.
	 * 
	 * @param date
	 * @param days
	 * @return date
	 */
	public static Date addDate(Date date, int days) {
		if (date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	/**
	 * Adds the specified (signed) amount of time to the hour-of-day field.
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addHour(Date date, int hours) {
		if (date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hours);
		return c.getTime();
	}

	/**
	 * Adds the specified (signed) amount of time to the minutes field.
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinute(Date date, int minutes) {
		if (date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	/**
	 * Adds the specified (signed) amount of time to the seconds field.
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSecond(Date date, int seconds) {
		if (date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, seconds);
		return c.getTime();
	}

	/**
	 * Adds the specified (signed) amount of time to the date time field.
	 * 
	 * @param date
	 * @param days
	 * @return date
	 */
	public static Timestamp addDate(Timestamp date, int days) {
		if (date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return new Timestamp(c.getTime().getTime());
	}

	/**
	 * Adds the specified (signed) amount of time to the hour-of-day field.
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Timestamp addHour(Timestamp date, int hours) {
		if (date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hours);
		return new Timestamp(c.getTime().getTime());
	}

	/**
	 * Adds the specified (signed) amount of time to the minutes field.
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Timestamp addMinute(Timestamp date, int minutes) {
		if (date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return new Timestamp(c.getTime().getTime());
	}

	/**
	 * Adds the specified (signed) amount of time to the seconds field.
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Timestamp addSecond(Timestamp date, int seconds) {
		if (date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, seconds);
		return new Timestamp(c.getTime().getTime());
	}

	/**
	 * Parse the give date time to string format with the default time format
	 * 
	 * @param dateValue
	 * @return string
	 */
	public static String date2String(Date dateValue) {
		return date2String(dateValue, DAY_FORMAT_1);
	}

	/**
	 * Parse the given time to string format
	 * 
	 * @param dateValue
	 * @param dateFormat
	 * @return string
	 */
	public static String date2String(java.util.Date dateValue, String dateFormat) {
		if (dateValue == null)
			return "";
		String sDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sDate = sdf.format(dateValue);
		} catch (Exception ex) {
			// logger.fatal(ex.getMessage());
			logger.error(ex.getMessage());
			ex.printStackTrace();
			throw new IllegalArgumentException(ex.getMessage());
		}
		return sDate;
	}

	/**
	 * Returns the time when is the fist monday from now on. Hours,minutes,and
	 * seconds are all set to be zero.
	 * 
	 * @return time
	 */
	public static Calendar getFirstMonday() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return getFirstMonday(c);
	}

	/**
	 * Returns the time when is the fist monday from now on. Hours,minutes,and
	 * seconds are all set to be zero.
	 * 
	 * @return time
	 */
	public static Calendar getFirstMonday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getFirstMonday(c);
	}

	/**
	 * Returns the time when is the fist monday from the given time.
	 * Hours,minutes,and seconds are all set to be zero.
	 * 
	 * @return time
	 */
	public static Calendar getFirstMonday(Calendar c) {
		for (int dow = c.get(Calendar.DAY_OF_WEEK); dow != Calendar.MONDAY; dow = c.get(Calendar.DAY_OF_WEEK)) {
			c.add(Calendar.DATE, 1);
		}
		return c;
	}

	public static Date getFirstDayOfYear() {
		Calendar c = Calendar.getInstance();
		return getFirstDayOfYear(c.get(Calendar.YEAR));
	}

	private static Date getFirstDayOfYear(int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * Format time by the default time.
	 * 
	 * @param time
	 * @return string
	 */
	public static String format(Date time) {
		return format(time, DAY_FORMAT_1);
	}

	/**
	 * Format time by the specified time.
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String format(Date time, String format) {
		if (time == null)
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(time);
		} catch (Exception eDate) {
			return time.toString();
		}
	}

	/**
	 * Returns year of the specified time.
	 * 
	 * @param time
	 * @return year
	 */
	public static int getYear(Date time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		return c.get(Calendar.YEAR);
	}

	/**
	 * Returns true iff the specified time is <code>Sunday</code>
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isSunday(Date time) {
		if (time == null)
			throw new NullPointerException(" argument is null ");
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	/**
	 * Returns true iff the specified time is <code>Saturday</code>
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isSaturday(Date time) {
		if (time == null)
			throw new NullPointerException(" argument is null ");
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}

	public static Date getMonday4Sunday2Saturday(Date date) {
		if (date == null)
			throw new NullPointerException(" specified date is null");

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			c.add(Calendar.DATE, 1);
		else
			while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
				c.add(Calendar.DATE, -1);

		return c.getTime();
	}

	/**
	 * Returns the time when is the monday of the current week.
	 * 
	 * @return Date
	 */
	public static Date getMondayOfWeek() {
		return getMondayOfWeek(getCurrentTime());
	}

	/**
	 * Returns the time when is the monday of the current week.
	 * 
	 * @param thisWeek
	 * @return Date
	 */
	public static Date getMondayOfWeek(Date thisWeek) {
		Calendar c = Calendar.getInstance();
		c.setTime(thisWeek);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
			c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	public static int differentDays(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		c1.set(Calendar.HOUR, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		c2.set(Calendar.HOUR, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		long l1 = c1.getTimeInMillis();
		long l2 = c2.getTimeInMillis();

		return (int) ((l1 - l2) / MILLS_PER_DAY);
	}

	public static int differentMinutes(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		long l1 = c1.getTimeInMillis();
		long l2 = c2.getTimeInMillis();

		return (int) ((l1 - l2) / MINUTE);
	}

}
