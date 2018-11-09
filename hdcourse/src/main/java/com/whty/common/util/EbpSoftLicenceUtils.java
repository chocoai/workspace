package com.whty.common.util;

import java.util.Random;

/*
 * 授权码生成工具类
 */
public class EbpSoftLicenceUtils {

	// private static String[] codesArray =
	// {"0","1","2","3","4","5","6","7","8","9"
	// ,"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","s","y","z"
	// ,"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","S","Y","Z"};
	// 去掉"0","1","l","o","x","I","O",
	private static String[] codesArray = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "y", "z", "A", "B", "C", "D", "E",
			"F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	public static String getLicence() {
		StringBuffer codeBuffer = new StringBuffer();
		// 生成[0,54]的随机数
		int max = 54;
		int s = 0;
		for (int i = 0; i < 12; i++) {
			Random random = new Random();
			s = random.nextInt(max);
			codeBuffer.append(codesArray[s]);
			if (i > 0 && i < 11 && i % 4 == 3)
				codeBuffer.append("-");
		}
		return codeBuffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getLicence());
	}

}
