package com.yhcrt.weihu.common.util;

import org.apache.commons.lang.math.RandomUtils;

public class GeneratorStr {

	public static String getString(int length){
		char[] ss = ("0123456799abcdefghijklmnopqrstuvwxyz").toCharArray();
		char[] s = new char[length];
		for(int i=0 ;i<s.length;i++){
			s[i] = ss[RandomUtils.nextInt(35)];
		}
		return new String(s);
	}
	
}
