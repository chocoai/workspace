package com.yhcrt.iHealthCloud.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 日期操作辅助类
 * 
 * @author ShenHuaJie
 * @version $Id: DateUtil.java, v 0.1 2014年3月28日 上午8:58:11 ShenHuaJie Exp $
 */
public final class DateUtil {
	private DateUtil() {
	}
	/** 日期格式 **/
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    }

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String format(Object date) {
		return format(date, DATE_PATTERN.YYYY_MM_DD);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String format(Object date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null) {
			return format(date);
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 获取日期
	 * 
	 * @return
	 */
	public static final String getDate() {
		return format(new Date());
	}

	/**
	 * 获取日期时间
	 * 
	 * @return
	 */
	public static final String getDateTime() {
		return format(new Date(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取日期
	 * 
	 * @param pattern
	 * @return
	 */
	public static final String getDateTime(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static final Date addDate(Date date, int field, int amount) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 字符串转换为日期:不支持yyM[M]d[d]格式
	 * 
	 * @param date
	 * @return
	 */
	public static final Date stringToDate(String date) {
		if (date == null) {
			return null;
		}
		String separator = String.valueOf(date.charAt(4));
		String pattern = "yyyyMMdd";
		if (!separator.matches("\\d*")) {
			pattern = "yyyy" + separator + "MM" + separator + "dd";
			if (date.length() < 10) {
				pattern = "yyyy" + separator + "M" + separator + "d";
			}
		} else if (date.length() < 8) {
			pattern = "yyyyMd";
		}
		pattern += " HH:mm:ss.SSS";
		pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 间隔天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getDayBetween(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);

		long n = end.getTimeInMillis() - start.getTimeInMillis();
		return (int) (n / (60 * 60 * 24 * 1000l));
	}

	/**
	 * 间隔月
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		return n;
	}

	/**
	 * 间隔月，多一天就多算一个月
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		int day1 = start.get(Calendar.DAY_OF_MONTH);
		int day2 = end.get(Calendar.DAY_OF_MONTH);
		if (day1 <= day2) {
			n++;
		}
		return n;
	}
	
	/**
	 * 生成第三方订单号
	 */
	public static final String getOrderNo() {
		 int x;//定义两变量 
	     Random ne=new Random();//实例化一个random的对象ne 
	     x=ne.nextInt(99999-10000+1)+10000;//为变量赋随机值10000-99999 
		 return "jkyl"+DateUtils.dateToString14(new Date())+x;
	}
	
	/**
	 * 传入微信的交易订单号
	 * @return
	 */
	public static final String getTranNum() {
		 int x;//定义两变量 
	     Random ne=new Random();//实例化一个random的对象ne 
	     x=ne.nextInt(99999-10000+1)+10000;//为变量赋随机值10000-99999 
		 return "wxzf"+DateUtils.dateToString14(new Date())+x;
	}
	
	public static String date2StrLong(Date date) {
		return date2Str(date, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
	}
	
	/** 日期转字符 **/
	public static String date2Str(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		String str = df.format(date);
		return str;
	}
	/** 字符转日期**/
	public static Date str2Date(String dataStr, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = df.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return date;
	}
	/**两个时间相差秒数**/
	public static  long calLastedTime(String dataStr) {
		  Date startDate = str2Date(dataStr, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		  long a = new Date().getTime();
		  long b = startDate.getTime();
		  long c = ((a - b) / 1000);
		  return c;
	}
	/**订单剩余有效时间(秒)**/
	public static  long validTime(String dataStr) {
		long c = calLastedTime(dataStr);
		return Const.VALID-c;
	}
	public static void main(String[] args) {
		System.out.println(calLastedTime("2018-01-23 11:30:43"));
	}
}
