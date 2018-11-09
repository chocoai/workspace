package com.yhcrt.iHealthCloud.util.wxpay;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

/**
 * 微信签名sign
 * @author PC
 *
 */
public class WXSignUtils {

	/**
	 * 微信支付
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters, String key){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v) 
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		System.out.println("签名："+sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}
	
	
	/**
	 * 微信小程序支付
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String creSmallSign(String characterEncoding,SortedMap<Object,Object> parameters, String key){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v) 
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		System.out.println("签名："+sb.toString());
		String sign = MD5Util.getMD5(sb.toString()).toUpperCase();
		return sign;
	}

	public static String getRequestXml(SortedMap<Object,Object> parameters){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Entry<Object, Object>>  es = parameters.entrySet();
		Iterator<Entry<Object, Object>> it = es.iterator();
        while(it.hasNext()) {
        	Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if("sign".equalsIgnoreCase(k)){

            }else if("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)) {
                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
            }else {
                sb.append("<"+k+">"+v+"</"+k+">");
            }
        }
        sb.append("<"+"sign"+">"+"<![CDATA["+parameters.get("sign")+"]]></"+"sign"+">");
        sb.append("</xml>");
        return sb.toString();
    }

}
