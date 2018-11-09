package com.yhcrt.tcpip.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.tcpip.util.Constans;
import com.yhcrt.tcpip.util.EncryptionUtil;
import com.yhcrt.tcpip.util.HttpClientUtils;
import com.yhcrt.tcpip.util.RuleUtil;

public class SocketHandler implements Runnable {
	private static final Logger logger =Logger.getLogger(SocketHandler.class);  
	private Socket s;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private String currentUUID = null;
	private boolean bConnected = false;
	private Integer errorNUM = null;

	public SocketHandler(Socket s) {
		this.s = s;
		try {
			currentUUID = UUID.randomUUID().toString();
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			bConnected = true;
			errorNUM = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String str) {
		try {
			dos.write(str.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while(bConnected) {
				byte [] head = new byte[8];
				dis.read(head);
				String start = new String(head);
				logger.info("start："+start);
				send(start);
				StringBuffer strBuffer = new StringBuffer();
				if(start.trim().startsWith(Constans.BEGIN_CODE)){
					errorNUM = 0;
					strBuffer.append(start);
				}else if("".equals(start.trim())){
					errorNUM ++;
					if(errorNUM > 30){
						throw new EOFException();
					}else{
						continue;
					}
				}else{
					errorNUM = 0;
					continue;
				}
				byte [] oneByteData = new byte[1];
				while(dis.read(oneByteData)!=-1){
					strBuffer.append(new String(oneByteData));
					if(strBuffer.toString().endsWith(Constans.END_CODE)){
						break;
					}
					if(strBuffer.length()>1024){  //读取了很长没有结束符
						break;
					}
				}
				String str = strBuffer.toString();
				logger.info("strBuffer："+strBuffer);
				if(RuleUtil.isValid(str)){
					str = str.replace(Constans.BEGIN_CODE, "").replace(Constans.END_CODE, "");
					String[] data = str.split(":");
					if(data.length<6){
						continue;
					}
					String funCode = data[5].trim();
					switch (funCode) {
						case Constans.FUN_START:{   //开机通知--登陆
							try{
								String imei = data[4].trim();
								Constans.clientKeyMap.put(currentUUID, imei);
								Constans.clientMap.put(imei, s);
							}catch(Exception e){
								logger.error("获取开机数据出错："+data);
							}finally{
								String returnStr = Constans.BEGIN_CODE+data[0].trim()+":"+data[1].trim()+":"+data[2].trim()+":"+data[3].trim()+":"
										+data[4].trim()+":"+Constans.FUN_START_R+":1,"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+",123.58.2.85,10007"+Constans.END_CODE;
								send(returnStr); //回复
								logger.info("登陆成功："+returnStr);
								int x;
							    Random ne=new Random();
							    x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
								String bloodTest = new String(Constans.BEGIN_CODE+"1:1:0:"+x+":"+data[4].trim()+":S72::0900,1,1200,1,1800,1"+Constans.END_CODE);
								send(bloodTest); //回复
							}
							break;
						}
						case Constans.FUN_HEART:{   //心跳
							try{
								String imei = data[4].trim();
								Constans.clientKeyMap.put(currentUUID, imei);
								Constans.clientMap.put(imei, s);
							}catch(Exception e){
								logger.error("获取心跳数据出错："+data);
							}finally{
								String returnStr = Constans.BEGIN_CODE+data[0].trim()+":"+data[1].trim()+":"+data[2].trim()+":"+data[3].trim()+":"
										+data[4].trim()+":"+Constans.FUN_HEART_R+Constans.END_CODE;
								send(returnStr); //回复
								logger.info("心跳回复："+returnStr);
							}
							break;
						}
						case Constans.FUN_WEATHER:{   //天气
							try{
								String localdata = data[6].trim();  //6|460| 00|25|6212&6212&3FB5&4321,434343
								String[] localarr = localdata.split(",");
								String localvalue ="";
								String[] dataarr = localarr[0].split("\\|");
							    String mcc = dataarr[1];
							    String mnc = dataarr[2];
							    String ci = Integer.parseInt(dataarr[4].split("&")[1].trim(),16)+"";   //基站编号
							    String lac = Integer.parseInt(dataarr[4].split("&")[0].trim(),16)+"";     //小区编号
							    String url = EncryptionUtil.LBS_URL.replace("${MCC}", mcc).replace("${MNC}", mnc)
							    		.replace("${LAC}", lac).replace("${CI}", ci);
							    JSONObject resultjson = HttpClientUtils.httpGet(url);
							    if(resultjson==null || resultjson.getInteger("errcode") != 0){
							    	logger.error("基站接口结果错误："+resultjson.toJSONString());
							    	localvalue = "武汉";
							    }else{  
								    String lat = resultjson.getString("lat");
								    String lgt = resultjson.getString("lon");
								    localvalue = lgt+","+lat;
							    }
		
								
								String result = EncryptionUtil.getWeather(localvalue);
								if(result==null){
									break;
								}
								String returnStr = Constans.BEGIN_CODE+data[0].trim()+":"+data[1].trim()+":"+data[2].trim()+":"+data[3].trim()+":"
										+data[4].trim()+":"+Constans.FUN_WEATHER_R+":"+result+Constans.END_CODE;
								send(returnStr); //回复	
								logger.info("天气数据："+returnStr);
							}catch(Exception e){
								logger.error("获取天气数据出错："+data);
							}
							break;
						}
						case Constans.FUN_BLOOD: {   //血压上传
							try{
								Constans.socketReturnMap.put(Constans.FUN_BLOOD, null);
								String imei = data[4].trim();
								String blooddata = data[6].trim();  // 67,89,64,434344
								String[] blood = blooddata.split(",");
                                String dbp = blood[1];
                                String sbp = blood[0];
                                String pluse = blood[2];
                                String uptime =  new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date((Long.valueOf(blood[3])-8*60*60)*1000L));
								EncryptionUtil.insertBloodAndPluse(imei, uptime, dbp, sbp,pluse);
							}catch(Exception e){
								logger.error("获取血压数据出错："+data);
							}finally{
								String returnStr = Constans.BEGIN_CODE+data[0].trim()+":"+data[1].trim()+":"+data[2].trim()+":"+data[3].trim()+":"
										+data[4].trim()+":"+Constans.FUN_BLOOD_R+Constans.END_CODE;
								send(returnStr);
								logger.info("血压回复："+returnStr);
							}
							break;
						}
						case Constans.FUN_BLOOD_GLUCOSE: {   //血糖上传
							try{
								Constans.socketReturnMap.put(Constans.FUN_BLOOD_GLUCOSE, null);
								String imei = data[4].trim();
								String blooddata = data[6].trim();  // 108,434344
								String[] blood = blooddata.split(",");
                                String bloodGlucose = blood[0];
                                String uptime =  new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date((Long.valueOf(blood[1])-8*60*60)*1000L));
								EncryptionUtil.insertBloodGlucose(imei, uptime, bloodGlucose);
							}catch(Exception e){
								logger.error("获取血糖数据出错："+data);
							}finally{
								String returnStr = Constans.BEGIN_CODE+data[0].trim()+":"+data[1].trim()+":"+data[2].trim()+":"+data[3].trim()+":"
										+data[4].trim()+":"+Constans.FUN_BLOOD_GLUCOSE_R+Constans.END_CODE;
								send(returnStr);
								logger.info("血糖回复："+returnStr);
							}
							break;
						}
						case Constans.FUN_HEART_RATE : {   //心率-脉搏上传
							try{
								Constans.socketReturnMap.put(Constans.FUN_HEART_RATE, null);
								String imei = data[4].trim();
								String heartdata = data[6].trim();  // 1,0|62|0|0|0|0|0|0|0|0|63|64,423434
								String[] heart = heartdata.split(",");
								String type = heart[0];  //1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据
								String heartvalue = heart[1].substring(heart[1].lastIndexOf("|")+1);
								String uptime =  new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date((Long.valueOf(heart[2])-8*60*60)*1000L));
								if(!"4".equals(type)){
									EncryptionUtil.insertHeartRate(imei, uptime, heartvalue);  
								}else{//睡眠
									EncryptionUtil.insertSleep(imei, heartvalue);
								}
							}catch(Exception e){
								logger.error("获取脉搏数据出错："+data);
							}finally{
								String returnStr = Constans.BEGIN_CODE+data[0].trim()+":"+data[1].trim()+":"+data[2].trim()+":"+data[3].trim()+":"
										+data[4].trim()+":"+Constans.FUN_HEART_RATE_R+Constans.END_CODE;
								send(returnStr);
								logger.info("脉搏回复："+returnStr);
							}
							break;
						}
						case Constans.FUN_STEP_NUM : {   //计步上传
							try{
								String imei = data[4].trim();
								String stepdata = data[6].trim();  //1|2|3|4|5|6|7|8|9|10|11|12|0,76|60|0|0|0|0|0|0|0|0|272|580|988,0|2|0|0|0|0|0|0|0|0|3|4|9,57|45|0|0|0|0|0|0|0|0|207|442|6853 , 1|460| 00|25| 6212&6212&3FB5&4321，0|62|0|0|0|0|0|0|0|0|63|64，1397001635
								String[] stepnum = stepdata.split(",");
								String heartvalue = stepnum[1].substring(stepnum[1].lastIndexOf("|")+1); //第2组才是步数数据，其他没用,一个小时内的总步数
								String uptime =  new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date((Long.valueOf(stepnum[6])-8*60*60)*1000L));
								EncryptionUtil.insertStepNum(imei, uptime, heartvalue);
							}catch(Exception e){
								logger.error("获取计步数据出错："+data);
							}finally{
								String returnStr = Constans.BEGIN_CODE+data[0].trim()+":"+data[1].trim()+":"+data[2].trim()+":"+data[3].trim()+":"
										+data[4].trim()+":"+Constans.FUN_STEP_NUM_R+Constans.END_CODE;
								send(returnStr);
								logger.info("计步(综合)回复："+returnStr);
							}
							break;
						}
						case Constans.FUN_LOCAL : {   //位置上传
							try{
								Constans.socketReturnMap.put(Constans.FUN_LOCAL, null);
								String imei = data[4].trim();
								String localdata = data[6].trim();  //1,1|460| 00|25|6212&6212&3FB5&4321,434343
								String[] localarr = localdata.split(",");
								String type = localarr[0].trim();
								String uptime = new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date());;
								String localvalue ="",addr="";
								if("1".equals(type) || "2".equals(type)){ //基站数据  1|460|00|25|6212&6212&3FB5&4321
									uptime =  new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date((Long.valueOf(localarr[2])-8*60*60)*1000L));
									String[] dataarr = localarr[1].split("\\|");
								    String mcc = dataarr[1];
								    String mnc = dataarr[2];
								    String ci = Integer.parseInt(dataarr[4].split("&")[1].trim(),16)+"";   //基站编号
								    String lac = Integer.parseInt(dataarr[4].split("&")[0].trim(),16)+"";     //小区编号
								    String url = EncryptionUtil.LBS_URL.replace("${MCC}", mcc).replace("${MNC}", mnc)
								    		.replace("${LAC}", lac).replace("${CI}", ci);
								    JSONObject result = HttpClientUtils.httpGet(url);
								    if(result==null || result.getInteger("errcode") != 0){
								    	logger.error("基站接口结果错误："+result.toJSONString());
								    	break;
								    }  
								    String lat = result.getString("lat");
								    String lgt = result.getString("lon");
								    addr = result.getString("address");
								    localvalue = lat+";"+lgt;
								}else if("3".equals(type)){ //WIFI数据  22:27:1d:20:08:d5&-55&|5c:63:bf:a4:bf:56&-86&
									data = str.split(",");  //wifi数据中存在冒号  重新解析
									uptime =  new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date((Long.valueOf(data[2])-8*60*60)*1000L));
									String mac = data[1].split("&")[0];
									String url = EncryptionUtil.WIFI_URL.replace("${MAC}", mac);
								    JSONObject result = HttpClientUtils.httpGet(url);
								    if(result==null || result.getInteger("errcode") != 0){
								    	logger.error("WIFI接口结果错误："+result.toJSONString());
								    	break;
								    }  
								    String lat = result.getString("lat");
								    String lgt = result.getString("lon");
								    addr = result.getString("address");
								    localvalue = lat+";"+lgt;
								}else if("4".equals(type) || "5".equals(type)){ //坐标数据
									localvalue=localarr[1].replace("|", ";");
									uptime =  new  SimpleDateFormat("yyyyMMddHHmmss").format(new Date((Long.valueOf(localarr[2])-8*60*60)*1000L));
								}
								EncryptionUtil.insertLocation(imei, uptime, localvalue,addr);
							}catch(Exception e){
								logger.error("获取位置数据出错："+data);
							}
							break;
						}
						default:
							break;
					}
				}else{
					logger.error("验证失败，收到字符串："+str);
				}
			}
		} catch (EOFException e) {
			if(currentUUID!=null){
				String imei = Constans.clientKeyMap.get(currentUUID);
				if(imei!=null){
					Constans.clientMap.remove(imei);
				}
			}
			logger.error("该客户端断开连接");
		} catch (Exception e) {
			if(currentUUID!=null){
				String imei = Constans.clientKeyMap.get(currentUUID);
				if(imei!=null){
					Constans.clientMap.remove(imei);
				}
			}
			logger.error("运行出错，该客户端断开连接：");
		} finally {
			try {
				if (dis != null)
					dis.close();
				if (dos != null)
					dos.close();
				if (s != null) {
					s.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
