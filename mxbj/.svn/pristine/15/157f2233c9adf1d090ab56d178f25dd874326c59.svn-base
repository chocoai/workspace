package com.whty.mxbj.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PadPwdUtils {
	
	public static void main(String[] args){
		System.out.println(creatPwd(10));
	}
	
	/**
	 * 创建密码
	 * 
	 * @param min
	 *            有效分钟
	 * @return
	 */
	public static String creatPwd(int min) {
		String pwd = "";
		try {
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("ddHHmm");
			String time = sdf.format(date);
			pwd = time + min; // 日期小时分钟 +有效分钟
//			 pwd=toHexStr(pwd); //16进制密码
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pwd;
	}

	/**
	 * 十进制字符串转十六进制字符串
	 * 
	 * @param str
	 *            日期、小时、分钟、生效时间 131224120 13号 12点 24分 120分钟
	 * @return
	 */
	public static String toHexStr(String str) {
		String result = "";
		try {
			for (int i = 0; i < str.length(); i += 2) {
			
				String temp = "";
				if (str.substring(i).length() % 2 == 1 && str.substring(i).length() / 2 == 1) {
					temp = Integer.toHexString(Integer.parseInt(str.substring(i)));
					i = str.length();
				} else {
					temp = Integer.toHexString(Integer.parseInt(str.substring(i, i + 2)));
				}

				if (temp.length() < 2) {
					temp = "0" + temp;
				}
				result += temp;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	/**
	 * 十六进制字符串转十进制字符串
	 * 
	 * @param str
	 *            16进制密码 0d0c1878
	 * @return
	 */
	public static String HextoStr(String str) {
		String result = "";
		if (str.length() != 8) {
			return result;
		}
		try {
			for (int i = 0; i < str.length(); i += 2) {
				String temp = Integer.parseInt(str.substring(i, i + 2), 16) + "";
				if (temp.length() < 2) {
					temp = "0" + temp;
				}
				result += temp;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	/**
	 * 验证密码
	 * 
	 * @param 日期、小时、分钟、生效时间
	 *            131224120
	 * @return
	 */
	public static boolean check(String str) {
		// str=HextoStr(str); //16进制密码转让10进制
		boolean isPass = false;

		long mtime = System.currentTimeMillis();
		Date date = new Date(mtime);
		SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
		SimpleDateFormat mdf = new SimpleDateFormat("MM");
		String year = ydf.format(date);
		String month = mdf.format(date);
		String time = year + month + str;

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		String currentdateStr = df.format(date);

		Date currentdate = null, pwddate = null;
		try {
			currentdate = df.parse(currentdateStr);
			pwddate = df.parse(time.substring(0, 12));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (Math.abs((currentdate.getTime() - pwddate.getTime())) < Integer.parseInt(time.substring(12)) * 60 * 1000) {
			isPass = true;
		} else {
			isPass = false;
		}

		return isPass;
	}
}
