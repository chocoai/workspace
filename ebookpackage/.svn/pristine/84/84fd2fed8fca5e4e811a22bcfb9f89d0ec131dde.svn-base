package com.whty.ebp.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PadPwdUtils {

	public static void main(String[] args) {
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
			// SimpleDateFormat sdf = new SimpleDateFormat("ddHHmm");
			// String time = sdf.format(date);
			// pwd = time + min; // 日期小时分钟 +有效分钟
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");
			SimpleDateFormat Hsdf = new SimpleDateFormat("HH");
			SimpleDateFormat msdf = new SimpleDateFormat("mm");
			System.out.println("creatpwd...");
			String day = Integer.toHexString((Integer.parseInt(dsdf.format(date)) + min));
			String hour = Integer.toHexString((Integer.parseInt(Hsdf.format(date)) + min));
			String minute = Integer.toHexString((Integer.parseInt(msdf.format(date)) + min));
			if (day.length() < 2) {
				day = "0" + day;
			}
			if (hour.length() < 2) {
				hour = "0" + hour;
			}
			if (minute.length() < 2) {
				minute = "0" + minute;
			}

			if (min > 9) {
				pwd = day + hour + minute + Integer.toHexString(min);// 日期小时分钟
																		// +有效分钟
			} else {
				pwd = day + hour + minute + "0" + Integer.toHexString(min);// 日期小时分钟
																			// +有效分钟
			}

			System.out.println("creatpwd..." + pwd);
			// pwd=toHexStr(pwd); //16进制密码

		} catch (Exception e) {
			// TODO: handle exception
		}
		return pwd;
	}

	public static byte[] ByteXOR(byte[] parameter1, byte[] parameter2) {
		if (parameter1.length != parameter2.length) {
			System.out.println("不能进行模二加！");
			return null;
		}
		byte[] ByteXOR = new byte[parameter1.length];
		byte temp3;
		for (int i = 0; i < parameter1.length; i++) {
			byte temp1 = parameter1[i];
			byte temp2 = parameter2[i];
			temp3 = (byte) (temp1 ^ temp2);
			ByteXOR[i] = temp3;
		}
		return ByteXOR;
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
