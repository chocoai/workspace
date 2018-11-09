package com.yhcrt.tcpip.util;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Constans {
	// key:imei
	public static Map<String, Socket> clientMap = new HashMap<String, Socket>();
	public static Map<String, String> clientKeyMap = new HashMap<String, String>();
	
	public static Map<String, Object> socketReturnMap = new HashMap<String, Object>();
	
	/****服务端错误码*******/
	public final static Integer SUCCESS = 0;     //成功
	public final static Integer DISCONNECT_ERROR = 1;  //断开连接
	public final static Integer INNER_ERROR = 500;  //断开连接
	
	

	// 起始符  CHAR[4]
	public final static String BEGIN_CODE = "{";
	
	//协议版本号 CHAR[3]
	public final static String PROTO_VERSION = "1";
	
	//类别代码（接口编号）  
	public final static String FUN_HEART = "T2";  //客户端心跳报文
	public final static String FUN_HEART_R = "S2";  //客户端心跳报文回复
	
	public final static String FUN_START = "T1";  //开机通知--登陆
	public final static String FUN_START_R = "S1";  //开机通知--登陆
	
	public final static String FUN_WEATHER = "T47";  //终端请求获取天气
	public final static String FUN_WEATHER_R = "S47";  //回复
	
	public final static String FUN_BLOOD = "T45";  //血压上传
	public final static String FUN_BLOOD_R = "S45";  //回复
	
	public final static String FUN_HEART_RATE = "T28";  //心率-脉搏上传
	public final static String FUN_HEART_RATE_R = "S28";  //
	
	public final static String FUN_BLOOD_GLUCOSE = "T59";  //血糖上传
	public final static String FUN_BLOOD_GLUCOSE_R = "S59";  //

	public final static String FUN_STEP_NUM = "T50";  //步数上传
	public final static String FUN_STEP_NUM_R = "S50";  //
	
	public final static String FUN_LOCAL = "T29";  //位置上传
	public final static String FUN_LOCAL_R = "S29";  //

	public final static String FUN_CONTACTS_SET = "S57";  //联系人设置
	
	public final static String FUN_GET_LOCAL = "S13";  //主动定位
	public final static String FUN_GET_HEART_RATE = "S5";  //主动测心率
	
	
	
	public final static String FUN_GET_BLOOD = "115";  //主动测血压
	public final static String FUN_HEART_RATE_LIMIT = "47";  //心率阀值设置
	public final static String FUN_DATA_RATE = "datarate";  //数据频率采集设置


	
	// 结束符  CHAR[4]
	public final static String END_CODE = "}";

	


}
