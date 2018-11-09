package com.whty.mxbj;

import java.util.HashMap;
import java.util.Map;

import com.whty.mxbj.common.utils.HttpUtils;
import com.whty.mxbj.common.utils.Tool;

import net.sf.json.JSONObject;
public class Test {
	
	public static void main(String[] args){
		
		String ss = "http://1jwqerwqer";
		
		String s = ss.substring(0, 4);
		
		System.out.println(s);
		
		if(s.equals("http")){
			System.out.println("是网络文件");
		}
		
//		String aa ="http://jxb.huijiaoyun.com:21027/eduopen/message/sendAppMsg";
		
//		{
//		    "publisherId": "ce000694102343d1aba1069ab54aeab9",
//		    "publisherName": "宋文萍",
//		    "sendeeOrgId":"f9ad7ee991144b6a9ad5cce1cd37060f",
//		    "busiId":"AQWER1234",
//		    "sendeeId": "@tea",
//		     "loginPlatformCode":"640100",
//		     "platformCode":"640100",
//		     "msgType": "link",
//		     "deadline": "20181102135304",
//			"link": {
//				"pcUrl": "",
//				"appUrl": "http://www.baidu.com",
//				"picUrl": "",
//				"content": "百度一下",
//				"title": "百度",
//				"busiId":"AQWER1234"
//			}
//		}

//		String publisherId = "ce000694102343d1aba1069ab54aeab9";
//		String publisherName = "微辅导测试消息";
//		String sendeeOrgId = "5c9c307224a3429c8e9f8c1ee7083dae";
//		String busiId = "AQWER1234";
//		String sendeeId = "e9dfc1de06894356ac0328e40853dbb9";
//		String loginPlatformCode = "640000";
//		String platformCode = "640100";
//		String msgType = "link";
//		String deadline = "20181102135305";
//		
//		
//		Map<String,String> linkParam = new HashMap<String, String>();
//		linkParam.put("pcUrl", "");
//		linkParam.put("appUrl", "http://www.baidu.com");
////		linkParam.put("picUrl", "http://css.huijiaoyun.com/tianyu_edu/area/888888/images/logo/logo.png");
//		linkParam.put("content", "您的微辅导消息");
//		linkParam.put("busiId", busiId);
//		
//		Map<String,Object> param = new HashMap<String, Object>();
//		param.put("publisherId", publisherId);
//		param.put("publisherName", publisherName);
//		param.put("sendeeOrgId", sendeeOrgId);
//		param.put("busiId", busiId);
//		param.put("sendeeId", sendeeId);
//		param.put("loginPlatformCode", loginPlatformCode);
//		param.put("platformCode", platformCode);
//		param.put("msgType", msgType);
//		param.put("deadline", deadline);
//		param.put("link", linkParam);
//		
//		String bb = HttpUtils.getInstance().httpPost("http://jxb.huijiaoyun.com:21027/eduopen/message/sendAppMsg", JSONObject.fromObject(param).toString());
//		
//		System.out.println(bb);
		
//		String noteType = "1";// 笔记本类型
//		String userPlatformCode = "888888";
//		String userId = "e2883a5961d245d98c7efef7cd9a1113";
//		String noteId = "3_212_0";
//		String coverImageUrl = "http://whty.bj.bcebos.com/mnote/notesconfig/3_212_2_cover.png";
//		
//		String no = noteType+"#"+userPlatformCode+"#"+userId+"#"+noteId+"#"+coverImageUrl;
//		String q = Base64.encodeBase64String(no.getBytes());
//		System.out.println(q);
//		
//		System.out.println(new String(Base64.decodeBase64(q)));
		
//		EncryptUtils eu = EncryptUtils.getInstance();
//		String pwd = "12345678";
//		System.out.println(eu.encode(pwd, EncryptUtils.UC_KEY));
		
		
//		String s="330000";
		
//		System.out.println(s.substring(0, 2));
		
//		Map<String, Object> param = new HashMap<String, Object>();
//
//		Map<String,Object> paramBody = new HashMap<String,Object>();
////		param.put("phoneNumber", phoneNumber);
////		param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");
//
//		param.put("action", "sendAliSms");
//		String verifyCode="1125";
//		paramBody.put("recNum", "15807172437");
//		paramBody.put("signName", "墨香笔记");
//		paramBody.put("templateCode", "SMS_91880093");
//		paramBody.put("paramString", "{\"actcode\":\""+verifyCode+"\"}");
//		
//		param.put("body", paramBody);
//		
//		System.out.println(JSONObject.fromObject(param).toString());
		
//		List<Note> notelist = new ArrayList<Note>();
//		
//		Note note = new Note();
//		note.setNoteName("笔记本");
//		notelist.add(note);
//		List<ArchiveNote> archiveNotelist = new ArrayList<ArchiveNote>();
//		ArchiveNote archiveNote = new ArchiveNote();
//		archiveNote.setArchiveNoteId("11");
//		archiveNote.setNoteName("归档笔记本");
//		
//		archiveNotelist.add(archiveNote);
//		
//		
//		List<Object> objectlist = new ArrayList<Object>();
//		objectlist.addAll(notelist);
//		objectlist.addAll(archiveNotelist);
//		
//		
//		for(Object object:objectlist){
//			
//			
//			System.out.println();
//		}
//		
	}
	
	public String numberToChinese(int num){//转化一个阿拉伯数字为中文字符串  
        if(num == 0){  
            return "零";  
        }  
        int unitPos = 0;//节权位标识  
        String All = new String();  
        String chineseNum = new String();//中文数字字符串  
        boolean needZero = false;//下一小结是否需要补零  
        String strIns = new String();  
        while(num>0){  
            int section = num%10000;//取最后面的那一个小节  
            if(needZero){//判断上一小节千位是否为零，为零就要加上零  
                All = Tool.chnNumChar[0] + All;  
            }  
            chineseNum = sectionTOChinese(section,chineseNum);//处理当前小节的数字,然后用chineseNum记录当前小节数字  
            if( section!=0 ){//此处用if else 选择语句来执行加节权位  
                strIns = Tool.chnUnitSection[unitPos];//当小节不为0，就加上节权位  
                chineseNum = chineseNum + strIns;  
            }else{  
                strIns = Tool.chnUnitSection[0];//否则不用加  
                chineseNum = strIns + chineseNum;  
            }  
            All = chineseNum+All;  
            chineseNum = "";  
            needZero = (section<1000) && (section>0);  
            num = num/10000;  
            unitPos++;  
        }  
        return All;  
    }  
    public String sectionTOChinese(int section,String chineseNum){  
        String setionChinese = new String();//小节部分用独立函数操作  
        int unitPos = 0;//小节内部的权值计数器  
        boolean zero = true;//小节内部的制零判断，每个小节内只能出现一个零  
        while(section>0){  
            int v = section%10;//取当前最末位的值  
            if(v == 0){  
                if( !zero ){  
                    zero = true;//需要补零的操作，确保对连续多个零只是输出一个  
                    chineseNum = Tool.chnNumChar[0] + chineseNum;  
                }  
            }else{  
                zero = false;//有非零的数字，就把制零开关打开  
                setionChinese = Tool.chnNumChar[v];//对应中文数字位  
                setionChinese = setionChinese + Tool.chnUnitChar[unitPos];//对应中文权位  
                chineseNum = setionChinese + chineseNum;  
            }  
            unitPos++;  
            section = section/10;  
        }  
          
        return chineseNum;  
    }  
}
