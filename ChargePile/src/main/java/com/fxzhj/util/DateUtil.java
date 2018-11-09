package com.fxzhj.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class DateUtil {
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** yyyy-MM-dd HH:mm:ss.SSS */
    public static final String nodelong                = "yyyyMMddHHmmssSSS";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
    
    /** 生成转账单前缀*/
    public static final String orderPrefix  		   = "FXZHJ";
    
    /** 生成充电桩节点编号前缀*/
    public static final String nodePrefix  		   	   = "CDZ";
    
    /** 生成电梯节点编号前缀*/
    public static final String elePrefix  		   	   = "DT";
    
    /** 生成商城节点编号前缀*/
    public static final String storePrefix  		   = "SC";
    
    /** 生成二维码编号前缀*/
    public static final String codePrefix  		   	   = "CODE";
	
	
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     * 以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return orderPrefix + df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到秒)，格式：yyyyMMddHHmmss
	 * @return
	 */
	public static String getLongDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	
	/**
	 * 充电桩节点编号
	 * @return
	 */
	public static String CDZstr(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(nodelong);
		return nodePrefix + df.format(date);
	}
	
	/**
	 * 电梯节点编号
	 * @return
	 */
	public static String DTstr(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(nodelong);
		return elePrefix + df.format(date);
	}
	
	/**
	 * 商城节点编号
	 * @return
	 */
	public static String SCstr(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(nodelong);
		return storePrefix + df.format(date);
	}
	
	/**
	 * 二维码生产规则
	 * @return
	 */
	public static String codeNum(int i){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return codePrefix+df.format(date)+String.format("%04d", 1);
	}
}
