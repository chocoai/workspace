package com.yhcrt.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * string操作类
 * 
 * @author 
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{
	
	/**
	 * hql 转 sql
	 * @param map
	 * @param hql
	 * @return
	 */
	public static String hql2Sql(Map<String, String> map, String hql) {
		if (map != null && map.size() > 0) {
			for (Entry<String, String> entry : map.entrySet()) {
				hql = hql.replaceAll(entry.getKey(), entry.getValue());
			}
		}
		hql = bean2Name(hql);
		return hql;
	}
	
	/**
	 * 数组转字符串
	 * @param array 数组
	 * @param split 分隔符
	 * @return 1,2,3,4,5
	 */
	public static String array2Str(Object [] array, String split) {
		StringBuilder sb = new StringBuilder();
		for (Object o : array) {
			sb.append(split).append(o.toString());
		}
		return sb.substring(1);
	}
	/**
	 * 把数组转换成set
	 * @param array
	 * @return
	 */
	public static Set<?> array2Set(Object[] array) {
		Set<Object> set = new TreeSet<Object>();
		for (Object id : array) {
			if(null != id){
				set.add(id);
			}
		}
		return set;
	}
	/**
	 * 把list转成数组
	 * @param list
	 * @return
	 */
	public static String[] list2array(List<String> list) {
		String [] arr = new String[list.size()];
		for (int i = 0; i <list.size(); i++) {
			arr[i] = list.get(i);
		}		
		return arr;
	}
	/**
	 * 将list转化为Set 
	 * @param map
	 * @return
	 */
	public static Set<String> List2Set(List<String> list) {
		return new HashSet<String>(list);
	}
	/**
	 * 将Map 的值转化为Set 
	 * @param map
	 * @return
	 */
	public static Set<String> Map2Set(Map<String, String> map) {
		Set<String> mapValuesSet = new HashSet<String>(map.values()); 
		return mapValuesSet;
	}
	
	
	/**
	 * 集合转字符串
	 * @param array 数组
	 * @param split 分隔符
	 * @return 1,2,3,4,5
	 */
	@SuppressWarnings("unchecked")
	public static String list2Str(List list, String split) {
		StringBuilder sb = new StringBuilder();
		for (Object o : list) {
			sb.append(split).append(o.toString());
		}
		return sb.substring(1);
	}
	
	/**
	 * 字符串转数组
	 * @param str 
	 * @param split 分隔符
	 * @return
	 */
	public static String[] str2Array(String str, String split) {
		if (str.startsWith(split)) {
			str = str.substring(split.length());
		}
		if (str.endsWith(split)) {
			str = str.substring(0, str.length() - split.length());
		}
		String [] strArr = str.split(split);
		return strArr;
	}
	/**
	 * 字符串转list
	 * @param str
	 * @param split
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List str2List(String str, String split) {
		String [] arr = str2Array(str, split);
		List list = new ArrayList();
		for (String s : arr) {
			list.add(s);
		}
		return list;
	}
	
	/**
	 * 去字符串中所有的空格
	 * @param str
	 * @return
	 */
	public static String trimAllWhitespace(String str) {
		if (isBlank(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		for (int index = 0; sb.length() > index;) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			} else {
				index++;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 统计一个字符串出现的次数
	 * @param source
	 * @param regexNew
	 * @return
	 */
	public static int finder(String source, String regexNew) {
		String regex = "[a-zA-Z]+";
		if (regexNew != null && !regexNew.equals("")) {
			regex = regexNew;
		}
		Pattern expression = Pattern.compile(regex);
		Matcher matcher = expression.matcher(source);
		int n = 0;
		while (matcher.find()) {
			n++;
		}
		return n;
	}
	
	/**
	 * 截取字符串
	 * @param s 原始字符串
	 * @param maxLength 截取长度
	 * @param ext 后缀
	 * @return
	 */
	public static String interceptStr(String s, int maxLength, String ext) {
		if (isBlank(s)) {
			return "";
		}
		return s.length() > maxLength ? s.substring(0, maxLength - 1) + ext : s;
	}
	
	/**
	 * 汉字转数字
	 * @param s
	 * @return
	 */
	public static String conNum(String s) {
		return s.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
			.replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
			.replaceAll("七", "7").replaceAll("八", "8").replaceAll("九", "9")
			.replaceAll("零", "0").replaceAll("两", "2");
	}
	
	/**
	 * 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isBlank(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return false;
	}

    public static String lowerCase(String str)
    {
        if(str == null)
            return null;
        else
            return str.toLowerCase();
    }
	/**
	 * 首字母小写
	 * @param s String
	 * @return String
	 */
	public static String firstCharLowerCase(String s) {
		if (s == null || "".equals(s)) {
			return ("");
		}
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	/**
	 * 首字母大写
	 * @param s String
	 * @return String
	 */
	public static String firstCharUpperCase(String s) {
		if (s == null || "".equals(s)) {
			return ("");
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * aBbbCcc => a_bbb_ccc
	 * @param property
	 * @return String
	 */
	public static String bean2Name(String bean) {
		StringBuffer sb = new StringBuffer();
		//遍历property如果有大写字母则将大写字母转换为_加小写
		for (int i = 0; i < bean.length(); i++) {
			char cur = bean.charAt(i);
			if (Character.isUpperCase(cur)) {
				if (!Character.isWhitespace(bean.charAt(i - 1))) {
					sb.append("_");
				}
				sb.append(Character.toLowerCase(cur));
			} else {
				sb.append(cur);
			}
		}
		return sb.toString();
	}
	
	/**
	 * a_bbb_ccc => aBbbCcc
	 * @param property
	 * @return String
	 */
	public static String name2Bean(String name) {
		if (isBlank(name) || name.indexOf("_") == -1) {
			return name;
		}
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		//遍历property如果有大写字母则将大写字母转换为_加小写
		for (int i = 0; i < name.length(); i++) {
			char cur = name.charAt(i);
			if ('_' == cur) {
				flag = true;
				continue;
			} else {
				sb.append(flag ? Character.toUpperCase(cur) : cur);
				flag = false;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 计算GBK编码的字符串的字节数
	 * 
	 * @param s
	 * @return
	 */
	public static int countCn(String s) {
		if (s == null) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		return count;
	}

	/**
	 * 文本转html
	 * @param txt
	 * @return
	 */
	public static String txt2htm(String txt) {
		if (isBlank(txt)) {
			return txt;
		}
		StringBuilder bld = new StringBuilder();
		char c;
		for (int i = 0; i < txt.length(); i++) {
			c = txt.charAt(i);
			switch (c) {
			case '&':
				bld.append("&amp;");
				break;
			case '<':
				bld.append("&lt;");
				break;
			case '>':
				bld.append("&gt;");
				break;
			case '"':
				bld.append("&quot;");
				break;
			case ' ':
				bld.append("&nbsp;");
				break;
			case '\n':
				bld.append("<br/>");
				break;
			default:
				bld.append(c);
				break;
			}
		}
		return bld.toString();
	}

	/**
	 * html转文本
	 * @param htm
	 * @return
	 */
	public static String htm2txt(String htm) {
		if (htm == null) {
			return htm;
		}
		htm = htm.replace("&amp;", "&");
		htm = htm.replace("&lt;", "<");
		htm = htm.replace("&gt;", ">");
		htm = htm.replace("&quot;", "\"");
		htm = htm.replace("&nbsp;", " ");
		htm = htm.replace("<br/>", "\n");
		return htm;
	}

	/**
	 * 全角-->半角
	 * @param qjStr
	 * @return
	 */
	public String Q2B(String qjStr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < qjStr.length(); i++) {
			try {
				Tstr = qjStr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}
		return outStr;
	}

}
