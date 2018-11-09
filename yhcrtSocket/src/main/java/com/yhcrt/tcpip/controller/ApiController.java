package com.yhcrt.tcpip.controller;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.tcpip.util.Constans;
import com.yhcrt.tcpip.util.HttpClientUtils;
import com.yhcrt.tcpip.util.RuleUtil;


@Controller
@RequestMapping(value = "/setting")
public class ApiController {
	
	
	 private static final Logger logger =Logger.getLogger(ApiController.class);  

	 
	//
	@RequestMapping(value = "/api")
	@ResponseBody
	public String testapi(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsonstr =  HttpClientUtils.getJsonString(request);
		JSONObject padata = JSONObject.parseObject(jsonstr);
		
		String sid = padata.getString("sid"); //方法名
		JSONArray biz = padata.getJSONArray("biz");
		
		for(int i=0;i<biz.size();i++){
			JSONObject jsondata = biz.getJSONObject(i);
			switch (sid) {
//			case Constans.FUN_HEART_RATE_LIMIT:{      //心率范围设置
//				setHeartRateLimit(jsondata);
//				break;
//			}
			case Constans.FUN_CONTACTS_SET:{      //联系人设置
				setContacts(jsondata);
				break;
			}
			case Constans.FUN_DATA_RATE:{      //数据频率采集设置//
				setDataRate(jsondata);
				break;
			}
			case Constans.FUN_GET_LOCAL:{      //主动获取位置
				getLocal(jsondata);
				break;
			}
			case Constans.FUN_GET_HEART_RATE:{      //主动测心率
				getHeartRate(jsondata);
				break;
			}
//			case Constans.FUN_GET_BLOOD:{      //主动测血压
//				getBlood(jsondata);
//				break;
//			}
			default:
				break;
			}
		}
		return null;
	}
	
	private void setDataRate(JSONObject jsondata){ //数据采集频率设置
		try {
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				return ;
			}
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			JSONArray dsfsetting = jsondata.getJSONArray("dsfsetting");
			if(dsfsetting.size()==0){
				return;
			}
			for(int i=0;i<dsfsetting.size();i++){                      //  1   2   0
				JSONObject dsfsetjson = dsfsetting.getJSONObject(i);
				String dataType = dsfsetjson.getString("dataType");//45心率频率（0-300分钟）  46计步频率（0-300分钟）  19位置频率（1-300分钟）
				switch (dataType) {
//				case "0":{
//					String sampleFrequency = dsfsetjson.getString("sampleFrequency");	
//					String str = Constans.BEGIN_CODE+","+Constans.PROTO_VERSION+","+dataType+","+sampleFrequency+","+Constans.END_CODE; //数据采集频率指令
//					dos.write(str.getBytes());
//					logger.info("位置频率设置成功："+dsfsetjson);
//					break;
//				}
				case "1":{
					String sampleFrequency = dsfsetjson.getString("sampleFrequency");	
					int x;
				    Random ne=new Random();
				    x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
				    Integer timeValue = Integer.valueOf(sampleFrequency)*60;
					String str = new String(Constans.BEGIN_CODE+"1:1:0:"+x+":"+imei+":S8:8:00-20:00|"+timeValue+Constans.END_CODE);  //数据采集频率指令
					dos.write(str.getBytes());
					logger.info("心率频率设置成功："+dsfsetjson);
					break;
				}
//				case "2":{
//					String sampleFrequency = dsfsetjson.getString("sampleFrequency");	
//					String str = Constans.BEGIN_CODE+","+Constans.PROTO_VERSION+","+dataType+","+sampleFrequency+","+Constans.END_CODE; //数据采集频率指令
//					dos.write(str.getBytes());
//					logger.info("计步频率设置成功："+dsfsetjson);
//					break;
//				}
				default:
					continue ;
				}
			}
		} catch (Exception e) {
			logger.error("频率设置失败："+jsondata);
		}
	}
	
	private String getLocal(JSONObject jsondata){
		JSONObject resultjson = new JSONObject();
		try{
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				resultjson.put("success", false);
				resultjson.put("msg", "设备未连接");
			}
			int x;
		    Random ne=new Random();
		    x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
			String str = new String(Constans.BEGIN_CODE+"1:1:0:"+x+":"+imei+":"+Constans.FUN_GET_LOCAL+Constans.END_CODE); //主动获取位置
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			Constans.socketReturnMap.put(Constans.FUN_LOCAL, null);
			dos.write(str.getBytes());
            JSONObject resultStr = null; 
            while(resultStr==null){		
            	Thread.sleep(200);
            	resultStr = (JSONObject) Constans.socketReturnMap.get(Constans.FUN_LOCAL);
            }
			resultjson.put("success", true);
			resultjson.put("mes", resultStr);
			logger.info("获取位置数据："+resultStr);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("发送位置指令出错："+jsondata);
			resultjson.put("success", false);
			resultjson.put("msg", "主动获取位置失败");
		}
		return resultjson.toString();
	}
	
	private String getHeartRate(JSONObject jsondata){
		JSONObject resultjson = new JSONObject();
		try{
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				resultjson.put("success", false);
				resultjson.put("msg", "设备未连接");
			}
			int x;
		    Random ne=new Random();
		    x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
			String str = new String(Constans.BEGIN_CODE+"1:1:0:"+x+":"+imei+":"+Constans.FUN_GET_HEART_RATE+Constans.END_CODE); //主动获取心率
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			Constans.socketReturnMap.put(Constans.FUN_HEART_RATE, null);
			dos.write(str.getBytes());
            JSONObject resultStr = null; 
            while(resultStr==null){		
            	Thread.sleep(200);
            	resultStr = (JSONObject) Constans.socketReturnMap.get(Constans.FUN_HEART_RATE);
            }
			resultjson.put("success", true);
			resultjson.put("mes", resultStr);
			logger.info("获取心率数据："+resultStr);
		}catch(Exception e){
			logger.info("发送心率检测指令出错："+jsondata);
			resultjson.put("success", false);
			resultjson.put("msg", "心率检测失败");
		}
		return resultjson.toString();
	}
	

	
	private void setContacts(JSONObject jsondata){ //联系人设置  
		try {
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				return ;
			}
			int x;
		    Random ne=new Random();
		    x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
			StringBuffer strContacts = new StringBuffer(Constans.BEGIN_CODE+"1:1:0:"+x+":"+imei+":"+Constans.FUN_CONTACTS_SET+":"  ); //联系人设置指令
			JSONArray contacts = jsondata.getJSONArray("contacts");
			if(contacts.size()==0){
				return;
			}
			for(int i=0;i<contacts.size();i++){
				JSONObject contact = contacts.getJSONObject(i);
				String tel = contact.getString("phoneNum");
				String name = contact.getString("lankman");
				String tempstr = tel + "," + name + ","+(i+1) ;
				strContacts.append(tempstr);
				if(i<contacts.size()-1){
					strContacts.append(",");
				}
			}	
			strContacts.append(Constans.END_CODE);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.write(strContacts.toString().getBytes("UTF-8"));
			logger.info("联系人设置成功："+jsondata);
		} catch (IOException e) {
			logger.error("联系人设置失败："+jsondata);
		}
	}
	
	
/*	private void setHeartRateLimit(JSONObject jsondata){ //心率范围设置
		try {
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				return ;
			}
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			JSONArray heartRateSetting = jsondata.getJSONArray("heartRateSetting");
			if(heartRateSetting.size()==0){
				return;
			}
			JSONObject heartRaterange = heartRateSetting.getJSONObject(0);
			String high = heartRaterange.getString("highHeartRate");
			String low = heartRaterange.getString("lowHeartRate");

			String str = Constans.BEGIN_CODE+","+Constans.PROTO_VERSION+","+Constans.FUN_HEART_RATE_LIMIT+","+low+","+high+","+Constans.END_CODE; //心率指令
			dos.write(str.getBytes());
			logger.info("心率设置成功："+jsondata);
		} catch (IOException e) {
			logger.error("心率设置失败："+jsondata);
		}
	}*/
	
/*	private void setContacts(JSONObject jsondata){ //联系人设置  
		try {
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				return ;
			}
			StringBuffer strContacts = new StringBuffer(Constans.BEGIN_CODE+","+Constans.PROTO_VERSION+","+Constans.FUN_CONTACTS_SET +","+"1" ); //联系人设置指令
			JSONArray contacts = jsondata.getJSONArray("contacts");
			if(contacts.size()==0){
				return;
			}
			Integer sosnum = contacts.size()>3?3:contacts.size();
			StringBuffer strSOS = new StringBuffer(Constans.BEGIN_CODE+","+Constans.PROTO_VERSION+","+Constans.FUN_SOS_SET +","+sosnum+"," ); //SOS联系人设置指令
			strContacts.append(","+contacts.size()+",");
			for(int i=0;i<contacts.size();i++){
				JSONObject contact = contacts.getJSONObject(i);
				String tel = contact.getString("phoneNum");
				String name = contact.getString("lankman");
				String tempstr = tel + ";" + RuleUtil.string2Unicode(name) + ";"+"1" ;
				strSOS.append(tel);
				if(i < contacts.size()-1){
					tempstr += "|";
				}
				if(i < sosnum-1){
					strSOS.append("|");
				}
				strContacts.append(tempstr);
			}	
			strSOS.append(","+Constans.END_CODE);
			strContacts.append(","+Constans.END_CODE);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.write(strContacts.toString().getBytes());
			logger.info("联系人设置成功："+jsondata);
			dos.write(strSOS.toString().getBytes());
			logger.info("SOS联系人设置成功："+jsondata);
		} catch (IOException e) {
			logger.error("联系人设置失败："+jsondata);
		}
	}*/
	
/*	private void setDataRate(JSONObject jsondata){ //数据采集频率设置
		try {
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				return ;
			}
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			JSONArray dsfsetting = jsondata.getJSONArray("dsfsetting");
			if(dsfsetting.size()==0){
				return;
			}
			for(int i=0;i<dsfsetting.size();i++){                      //      1              2                     0
				JSONObject dsfsetjson = dsfsetting.getJSONObject(i);
				String dataType = dsfsetjson.getString("dataType");//45心率频率（0-300分钟）  46计步频率（0-300分钟）  19位置频率（1-300分钟）
				switch (dataType) {
				case "0":
					dataType = "19";
					break;
				case "1":
					dataType = "45";
					break;
				case "2":
					dataType = "46";
					break;
				default:
					continue ;
				}
				String sampleFrequency = dsfsetjson.getString("sampleFrequency");	
				String str = Constans.BEGIN_CODE+","+Constans.PROTO_VERSION+","+dataType+","+sampleFrequency+","+Constans.END_CODE; //数据采集频率指令
				dos.write(str.getBytes());
				logger.info("频率设置成功："+dsfsetjson);
			}
		} catch (IOException e) {
			logger.error("频率设置失败："+jsondata);
		}
	}*/
	
/*	private String getLocal(JSONObject jsondata){
		JSONObject resultjson = new JSONObject();
		try{
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				resultjson.put("success", false);
				resultjson.put("msg", "设备未连接");
			}
			String str = Constans.BEGIN_CODE+","+Constans.PROTO_VERSION+","+Constans.FUN_GET_LOCAL+","+Constans.END_CODE; //主动定位指令
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			Constans.socketReturnMap.put(Constans.FUN_LOCAL, null);
			dos.write(str.getBytes());
            JSONObject resultStr = null; 
            while(resultStr==null){		
            	Thread.sleep(200);
            	resultStr = (JSONObject) Constans.socketReturnMap.get(Constans.FUN_LOCAL);
            }
			resultjson.put("success", true);
			resultjson.put("mes", resultStr);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("发送定位指令出错："+jsondata);
			resultjson.put("success", false);
			resultjson.put("msg", "主动定位失败");
		}
		return resultjson.toString();
	}*/
	
/*	private String getHeartRate(JSONObject jsondata){
		JSONObject resultjson = new JSONObject();
		try{
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				resultjson.put("success", false);
				resultjson.put("msg", "设备未连接");
			}
			String str = Constans.BEGIN_CODE+","+Constans.PROTO_VERSION+","+Constans.FUN_GET_HEART_RATE+","+Constans.END_CODE; //主动测心率
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			Constans.socketReturnMap.put(Constans.FUN_HEART_RATE, null);
			dos.write(str.getBytes());
            JSONObject resultStr = null; 
            while(resultStr==null){		
            	Thread.sleep(200);
            	resultStr = (JSONObject) Constans.socketReturnMap.get(Constans.FUN_HEART_RATE);
            }
			resultjson.put("success", true);
			resultjson.put("mes", resultStr);
		}catch(Exception e){
			logger.info("发送心率检测指令出错："+jsondata);
			resultjson.put("success", false);
			resultjson.put("msg", "心率检测失败");
		}
		return resultjson.toString();
	}*/
	
/*	private String getBlood(JSONObject jsondata){
		JSONObject resultjson = new JSONObject();
		try{
			String imei = jsondata.getString("imei");
			Socket socket = Constans.clientMap.get(imei);
			if(socket == null){
				logger.info("设备未连接："+jsondata);
				resultjson.put("success", false);
				resultjson.put("msg", "设备未连接");
			}
			String str = Constans.BEGIN_CODE+","+Constans.PROTO_VERSION+","+Constans.FUN_GET_LOCAL+","+Constans.END_CODE; //主动测血压
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			Constans.socketReturnMap.put(Constans.FUN_BLOOD, null);
			dos.write(str.getBytes());
            JSONObject resultStr = null; 
            while(resultStr==null){		
            	Thread.sleep(200);
            	resultStr = (JSONObject) Constans.socketReturnMap.get(Constans.FUN_BLOOD);
            }
			resultjson.put("success", true);
			resultjson.put("mes", resultStr);
		}catch(Exception e){
			logger.info("发送血压检测指令出错："+jsondata);
			resultjson.put("success", false);
			resultjson.put("msg", "血压检测失败");
		}
		return resultjson.toString();
	}*/
	 


}
