package com.yhcrt.tcpip.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.druid.util.StringUtils;


/*
 * 通讯接收信息规则校验
 */
public class RuleUtil {


	/**
	 * 判断起始符/结束符是否正确
	 */
	public static boolean isValid(String str){
		boolean isStartValid=false;
		if(StringUtils.isEmpty(str)){
			return isStartValid;
		}else{
			str = str.trim();
		}
		if(str.startsWith(Constans.BEGIN_CODE) && str.endsWith(Constans.END_CODE)){
			isStartValid=true;
		}
		return isStartValid;
	}
	
	/**
	  * 字符串转换unicode
	  */
	 public static String string2Unicode(String string) {
	     StringBuffer unicode = new StringBuffer();
	     for (int i = 0; i < string.length(); i++) {
	         // 取出每一个字符
	        char c = string.charAt(i);
	         // 转换为unicode
	         unicode.append("\\u" + Integer.toHexString(c));
	     }
	     return unicode.toString();
	 }
	 
	 /**
	  * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
	     StringBuffer string = new StringBuffer();
	     String[] hex = unicode.split("\\\\u");
	     for (int i = 1; i < hex.length; i++) {
	         // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	         // 追加成string
	         string.append((char) data);
	     }
	     return string.toString();
	 }
	
	  /**
     * 字符串转换为Ascii
     * @param value
     * @return
     */
    public static String stringToAscii(String value)  {  
        StringBuffer sbu = new StringBuffer();  
        char[] chars = value.toCharArray();   
        for (int i = 0; i < chars.length; i++) {  
            if(i != chars.length - 1){  
                sbu.append((int)chars[i]).append(",");  
            }else {  
                sbu.append((int)chars[i]);  
            }  
        }  
        return sbu.toString();  
    }
    
    /**
     * Ascii转换为字符串
     * @param value
     * @return
     */
    public static String asciiToString(String value){
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }
	
	/**
	 * 编码base64
	 */
	public static String myEncrypt(String originalText) {
		Base64 base64 = new Base64();
		String cipherTextBase64 = "";
		try {
			cipherTextBase64 = base64.encodeToString(originalText.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return cipherTextBase64;
	}

	/**
	 * 解码base64
	 */
	public static String myDecrypt(String cipherTextBase64) {
		Base64 base64 = new Base64();
		byte[] cipherTextArray = base64.decode(cipherTextBase64);
		String decrypt = "";
		try {
			decrypt = new String(cipherTextArray, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decrypt;
	}
	
	public static void main(String[] args) {
		System.out.println(stringToAscii("{}"));
	System.out.println(asciiToString("123,49,58,49,58,48,58,50,51,50,51,50,50,58,32,51,57,50,51,56,50,51,57,51,50,57,58,83,49,58,49,44,32,50,48,49,51,45,48,56,45,49,48,32,49,56,58,52,51,58,53,50,44,32,49,50,49,46,52,48,46,49,56,55,46,49,51,54,32,44,56,56,56,56,125"));
	}


}
