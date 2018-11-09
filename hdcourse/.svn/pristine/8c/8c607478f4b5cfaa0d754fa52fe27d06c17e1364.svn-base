package com.whty.assis.manage.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Test {

	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
														// DES,DESede,Blowfish

	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	private static byte[] change(byte[] buff) {
		for (int i = 0; i < buff.length; i++) {
			int b = 0;
			for (int j = 0; j < 8; j++) {
				int bit = (buff[i] >> j & 1) == 0 ? 1 : 0;
				b += (1 << j) * bit;
			}
			buff[i] = (byte) b;
		}
		return buff;
	}

	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	public static byte[] initKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(Algorithm);
		kg.init(8);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Integer a = 3/8;
		// System.out.println(a);
		// Integer b = 10/8;
		// System.out.println(b);
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(CommonFunction.getSampleDate("2015-11-27"));
		// System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		// System.out.println(CommonFunction.getWeek(CommonFunction.getSampleDate("2015-11-27")));

		// File file = new
		// File("E:\\天喻通讯\\资料\\应用\\教师助手\\数字教材\\20151202\\8-12月3日入库图片");
		// String[] tempList = file.list();
		// System.out.println(tempList.length);

		// String path = "F:\\tmpfs\\soft";
		// DelFile.delFolderAllFile(path);

		// Vector<String> s = new Vector<String>();
		// s.add("1");
		// System.out.println(s.size());

		// byte[] b = new byte[8];
		//
		// Random random = new Random(new Date().getTime());
		//
		// random.nextBytes(b);
		//
		// System.out.println(new String(b));
		//
		// final byte[] GK = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40,
		// 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB,
		// (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40,
		// 0x36, (byte) 0xE2 }; // 24字节密钥
		//
		// byte[] left = encryptMode(GK, b);
		//
		// System.out.println(new String(left));
		//
		// byte[] right = change(left);
		//
		// byte[] vk = byteMerger(left, right);
		//
		// byte[] identifycode = encryptMode(GK, "noIs-HFMs-Bp5z".getBytes());
		//
		// StringBuffer hash = new StringBuffer();
		//
		// for (int i = 0; i < GK.length; i++) {
		// hash.append(Integer.toHexString(GK[i]));
		// }

		// String canUseDayCount = null;
		//
		// long days =
		// TimeUnit.MILLISECONDS.toDays(TimeUtil.string2Date("2017-07-31").getTime()
		// - TimeUtil.string2Date("2017-07-01").getTime());
		// canUseDayCount = days > 9999 ? "9999" : days + "";
		// System.out.println(canUseDayCount);
		// 添加新安全算法,如果用JCE就要把它添加进去
		// Security.addProvider(new com.sun.crypto.provider.SunJCE());

		// final byte[] keyBytes = {0x11, 0x22, 0x4F, 0x58, (byte)0x88, 0x10,
		// 0x40, 0x38
		// , 0x28, 0x25, 0x79, 0x51, (byte)0xCB, (byte)0xDD, 0x55, 0x66
		// , 0x77, 0x29, 0x74, (byte)0x98, 0x30, 0x40, 0x36, (byte)0xE2};
		// //24字节的密钥
		// String szSrc = "noIs-HFMs-Bp5z";
		//
		// System.out.println("加密前的字符串:" + szSrc);
		//
		// byte[] encoded = encryptMode(random.nextInt(), szSrc.getBytes());
		// System.out.println("加密后的字符串:" + new String(encoded));
		//
		// byte[] srcBytes = decryptMode(keyBytes, encoded);
		// System.out.println("解密后的字符串:" + (new String(srcBytes)));
		// Calendar cal1 = Calendar.getInstance();
		// cal1.add(Calendar.DAY_OF_MONTH, -1);// 昨天
		// Calendar cal2 = Calendar.getInstance();
		// cal2.add(Calendar.DAY_OF_MONTH, 0);// 今日
		//
		// Map<String, Object> param = new HashMap<String, Object>();
		//
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String startTime = sdf.format(cal1.getTime());
		// String endTime = sdf.format(cal2.getTime());
		//
		// System.out.println(startTime + endTime);

		// Calendar cal1 = Calendar.getInstance(); // 当前日期
		// cal1.add(Calendar.MONTH, -1);
		// cal1.set(Calendar.DAY_OF_MONTH, 1); // 上月第一天
		// Calendar cal2 = Calendar.getInstance();
		// cal2.add(Calendar.MONTH, 0);
		// cal2.set(Calendar.DAY_OF_MONTH, 1); // 当月第一天
		//
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String startTime = sdf.format(cal1.getTime());
		// String endTime = sdf.format(cal2.getTime());
		//
		// System.out.println(startTime + endTime);
		//
		//
		// String aa = null;
		// Map<String,Object> p = new HashMap<String,Object>();
		// p.put("a", aa);
		// System.out.println(p.get("a"));
		//
		// String s="2018_02_26_10_54_58";
		// System.out.println(s.concat("2018"));
		//
		// String str1 = "abcdefg";
		// int result1 = str1.indexOf("a");
		// if(result1 != -1){
		// System.out.println("字符串str中包含子串“a”"+result1);
		// }else{
		// System.out.println("字符串str中不包含子串“a”"+result1);
		// }

		// Calendar startCal = Calendar.getInstance();
		//
		// startCal.set(Calendar.DATE, startCal.get(Calendar.DATE) - 7);
		// startCal.set(Calendar.HOUR_OF_DAY, 0);
		// startCal.set(Calendar.MINUTE, 0);
		// startCal.set(Calendar.SECOND, 0);
		// startCal.set(Calendar.MILLISECOND, 0);
		//
		// System.out.println(TimeUtils.date2String(startCal.getTime(),TimeUtils.TIME_FORMAT));

		// String ss =
		// "[{\"url\":\"1\",\"name\":\"1\",\"logo\":\"1\",\"groupNum\":\"1\",\"groupName\":\"1\"}]";
		//
		// JSONArray sss = JSONArray.fromObject(ss);
		// System.out.println(sss.size());

		// String url =
		// "http://data.zjer.cn/bigdata/interfaces/bigscreen/postAppUsageCount";
		//
		// String dd = "{\"doneTime\":\"2018-05-10
		// 11:05:00\",\"typeCode\":\"2\",\"staticTime\":\"2018051010\",\"countNum\":\"22607\"}";
		//
		// HttpUtils.doPost(url, dd);

		// Calendar cal = Calendar.getInstance();
		// int currentHour = cal.get(Calendar.HOUR_OF_DAY);
		// if (currentHour >= 6 && currentHour <= 17) {
		// System.out.println("积分");
		// }

		// System.out.println();

		int seconds = 1000000;// 60*60*24*2+3600*4+180+58;
		int day = seconds / (60 * 60 * 24);// 换成天
		int hour = (seconds - (60 * 60 * 24 * day)) / 3600;// 总秒数-换算成天的秒数=剩余的秒数
															// 剩余的秒数换算为小时
		int minute = (seconds - 60 * 60 * 24 * day - 3600 * hour) / 60;// 总秒数-换算成天的秒数-换算成小时的秒数=剩余的秒数
																		// 剩余的秒数换算为分
		int second = seconds - 60 * 60 * 24 * day - 3600 * hour - 60 * minute;// 总秒数-换算成天的秒数-换算成小时的秒数-换算为分的秒数=剩余的秒数
		System.out.println(day + "天" + hour + "时" + minute + "分" + second + "秒");
	}

}
