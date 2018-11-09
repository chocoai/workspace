import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.whty.assis.api.utils.HttpUtils;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.EncryptionUtils;
import com.whty.common.util.SysConfig;
import com.whty.common.util.TimeUtils;

import net.sf.json.JSONObject;

/**
 * Created by zhangzheng on 2016/9/6.
 */
public class Test {

	public static void test() throws IOException {

		Calendar cal = Calendar.getInstance();
		int currentHour = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println(currentHour);

		// String urltt = SysConfig.getStrValue("jifen.shouke");
		// Map<String, Object> paramtt = new HashMap<String, Object>();
		// paramtt.put("usercode", "636230");
		// paramtt.put("typecode",
		// SysConfig.getStrValue("zj.jifen.shouke.action.num"));
		//// paramtt.put("typecode","JF000356");
		// paramtt.put("comeFrom", "1");
		// paramtt.put("relatedId", SysConfig.getStrValue("zj.app.code"));
		//// paramtt.put("checkTypeDesc", "移动授课");
		// String resulttt;
		// try {
		// resulttt = HttpUtils.getInstance().httpPost(urltt,
		// JSONObject.fromObject(paramtt).toString());
		// System.out.println(resulttt);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Calendar cal = Calendar.getInstance();
		// String startTime = CommonFunction.getSampleString(cal.getTime());
		// cal.set(Calendar.DAY_OF_YEAR,
		// cal.get(Calendar.DAY_OF_YEAR) + Integer.valueOf("10"));
		//
		// String endTime = CommonFunction.getSampleString(cal.getTime());
		// System.out.println(startTime+endTime);

		// String json =
		// "key={\"list\":[{\"platformCode\":\"420100\",\"classId\":\"d4750b1df85c474cbd2e4983496dc2ed\"},{\"classId\":\"98337728964841528d236e74e83c0bca\",\"platformCode\":\"420100\"}]}";
		// String httpUrl =
		// "http://zhktapi.t.huijiaoyun.com:30176/hdcourse/classStudent/getContent";
		//

		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// GZIPOutputStream gout = new GZIPOutputStream(out);
		// gout.write(json.toString().getBytes("UTF-8"));
		// gout.close();
		// result = out.toByteArray();
		//
		// String responseResult = null;
		// CloseableHttpClient httpClient = null;
		// CloseableHttpResponse response = null;
		// try {
		// httpClient = HttpClients.createDefault();
		// // 设置请求和传输超时
		// RequestConfig requestConfig =
		// RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
		// .build();
		//// StringEntity entity = new StringEntity(json,
		// ContentType.create("application/json", Consts.UTF_8));
		//
		// response.setHeader("Content-Encoding","gzip");
		//
		// // 采用post方式提交
		// HttpPost httpPost = new HttpPost(httpUrl);
		// httpPost.setConfig(requestConfig);
		// httpPost.
		//
		// response = httpClient.execute(httpPost);
		// HttpEntity resEntity = response.getEntity();
		// responseResult = EntityUtils.toString(resEntity, "utf-8");
		// httpPost.releaseConnection();
		// } catch (Throwable e) {
		//// logger.error("调用接口出错：" + e);
		// } finally {
		// if (response != null) {
		// response.close();
		// }
		// if (httpClient != null) {
		// httpClient.close();
		// }
		//
		// }

		// CloseableHttpClient httpClient = HttpClients.createDefault();

		// String postStr =
		// "key={\"list\":[{\"platformCode\":\"420100\",\"classId\":\"d4750b1df85c474cbd2e4983496dc2ed\"},{\"classId\":\"98337728964841528d236e74e83c0bca\",\"platformCode\":\"420100\"}]}";
		// String url =
		// "http://zhktapi.t.huijiaoyun.com:30176/hdcourse/classStudent/getContent";
		//
		// PostMethod postMethod = new PostMethod(url);
		// postMethod.setContentChunked(true);
		// postMethod.addRequestHeader("Accept", "text/plain");
		// postMethod.setRequestHeader("Content-Encoding", "gzip");
		// postMethod.setRequestHeader("Transfer-Encoding", "chunked");
		//
		//
		// ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
		// originalContent
		// .write(postStr.getBytes(Charset.forName("UTF-8")));
		//
		// ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
		// originalContent.writeTo(gzipOut);
		// gzipOut.finish();
		//
		//
		// HttpClient httpClient = new HttpClient();
		//
		// int status = httpClient.executeMethod(postMethod);
		//
		// System.out.println(status);
		//
		// String sss = postMethod.getResponseBodyAsString();
		// System.out.println(sss);

		// String content = "test";
		// String password = "12345678";
		// // 加密
		// System.out.println("加密前：" + content);
		// String s = encrypt(content, password);
		// System.out.println("加密后：" + s);
		// // 解密
		//
		// String s1 = decrypt(s, password);
		// System.out.println("解密后：" + s1);

		// String str = "鲸";
		// char[] ch = str.toCharArray();
		// byte[] ss = str.getBytes();
		// System.out.println(bytesToHexFun1(str.getBytes()));
		// System.out.println(bytesToHexFun1(str.getBytes("iso-8859-1")));
		// String s2 = new String(str.getBytes("iso-8859-1"), "utf-8");
		//
		// System.out.println(bytesToHexFun1(s2.getBytes()));
		//
		// System.out.println(s2);
		//
		// String s3 = new String(s2.getBytes("utf-8"), "iso-8859-1");
		// System.out.println(s3);

		// Double s = 2.00000;
		//
		// System.out.println(Double.valueOf(s).toString());

		// Map<String, Object> param = new HashMap<String, Object>();
		//
		// String appId = "goDy89cfmgmUZdNwwgt9CR67zd9aSDPK";
		// String appKey = "KGjZc9Xxb0d9yEst51A0JmK4K2XqRCuf";
		//
		// String timeStamp =
		// Long.valueOf(System.currentTimeMillis()).toString();
		//
		// String keyInfoStr = appId + appKey + timeStamp;
		//
		// byte[] hmacSHA = EncryptionUtils.getHmacSHA1(keyInfoStr, appKey);
		// String digest = EncryptionUtils.bytesToHexString(hmacSHA);
		//
		// param.put("appId", appId);
		// param.put("timeStamp", timeStamp);
		// param.put("keyInfo", digest);
		// param.put("sysCode", "420100");
		// //
		// http://gateway.system.eduyun.cn:40015/userSession/validaTicket?accessToken=778bfe2ea5b0440390872a6f8fdfb91a
		// String ss =
		// "http://gateway.system.eduyun.cn:40015/apigateway/getAccessToken";
		// try {
		// System.out.println(HttpUtils.getInstance().httpPost(ss,
		// JSONObject.fromObject(param).toString()));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	public static String bytesToHexFun1(byte[] bytes) {
		// 一个byte为8位，可用两个十六进制位标识
		char[] buf = new char[bytes.length * 2];
		int a = 0;
		int index = 0;
		for (byte b : bytes) { // 使用除与取余进行转换
			if (b < 0) {
				a = 256 + b;
			} else {
				a = b;
			}

			buf[index++] = HEX_CHAR[a / 16];
			buf[index++] = HEX_CHAR[a % 16];
		}

		return new String(buf);
	}

	public static String encrypt(String bef_aes, String password) {
		byte[] byteContent = null;
		try {
			byteContent = bef_aes.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encrypt(byteContent, password);
	}

	public static String encrypt(byte[] content, String password) {
		try {
			SecretKey secretKey = getKey(password);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			String aft_aes = parseByte2HexStr(result);
			return aft_aes; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String aft_aes, String password) {
		try {
			byte[] content = parseHexStr2Byte(aft_aes);
			SecretKey secretKey = getKey(password);
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			String bef_aes = new String(result);
			return bef_aes; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int value = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16);
			result[i] = (byte) value;
		}
		return result;
	}

	public static SecretKey getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			_generator.init(128, secureRandom);
			return _generator.generateKey();
		} catch (Exception e) {
			throw new RuntimeException("初始化密钥出现异常");
		}
	}

	public static void main(String[] args) throws Exception {

		// String js = "<script type=\"text/javascript\">var
		// a=10;alert(a);</script>";
		// System.out.println(org.springframework.web.util.JavaScriptUtils.javaScriptEscape(js));
		// System.out.println(org.apache.commons.lang.StringEscapeUtils.escapeJavaScript(js));
		//
		// StringBuffer s = new StringBuffer();
		//
		// for(int i=0;i<js.length();i++){
		// char c = js.charAt(i);
		// if("\"".equals(c)){
		// s.append("\\\"");
		// }else{
		// s.append(c);
		// }
		// }
		// System.out.println(s.toString());

		// Calendar cal = Calendar.getInstance();
		// int currentHour = cal.get(Calendar.HOUR_OF_DAY);
		// System.out.println(currentHour);
		Long time = 1540197071160L;
		Date date = new Date(time);

		System.out.println(TimeUtils.date2String(date, TimeUtils.STR_DATETIME_PATTERN_LONG));

		// Map<String, Object> param = new HashMap<String, Object>();
		//
		// String appId = "goDy89cfmgmUZdNwwgt9CR67zd9aSDPK";
		// String appKey = "KGjZc9Xxb0d9yEst51A0JmK4K2XqRCuf";
		//
		// String timeStamp =
		// Long.valueOf(System.currentTimeMillis()).toString();
		//
		// String keyInfoStr = appId + appKey + timeStamp;
		//
		// byte[] hmacSHA = EncryptionUtils.getHmacSHA1(keyInfoStr, appKey);
		// String digest = EncryptionUtils.bytesToHexString(hmacSHA);
		//
		// param.put("appId", appId);
		// param.put("timeStamp", timeStamp);
		// param.put("keyInfo", digest);
		// param.put("sysCode", "420100");
		// //
		// http://gateway.system.eduyun.cn:40015/userSession/validaTicket?accessToken=778bfe2ea5b0440390872a6f8fdfb91a
		// String ss =
		// "http://gateway.system.eduyun.cn:40015/apigateway/getAccessToken";
		// try {
		// // System.out.println();
		// String result = HttpUtils.getInstance().httpPost(ss,
		// JSONObject.fromObject(param).toString());
		//
		// //
		// {"retCode":"000000","retDesc":"成功","data":{"accessToken":"b932bf5fd7e84390af1ec59e16d8125a","appId":"goDy89cfmgmUZdNwwgt9CR67zd9aSDPK","appLvl":"0","appName":"互动课堂","appType":"0","sysCode":"420100","userId":"DQtHV9El5PyIRaAAcO09zFe4nPU1Qn6b","validTime":"1532516156785"}}
		//
		// JSONObject jsonObject = JSONObject.fromObject(result);
		//
		// if ("000000".equals(jsonObject.optString("retCode"))) {
		//
		// JSONObject data = jsonObject.optJSONObject("data");
		//
		// String accessToken = data.optString("accessToken");
		//
		// String url2 =
		// "http://gateway.system.eduyun.cn:40015/userSession/validaTicket?accessToken="
		// + accessToken;
		//
		// Map<String, Object> ticket = new HashMap<String, Object>();
		//
		// ticket.put("ticket",
		// "b2c2MzZlOTc4MTEtMjU5Zi00NDI4LWE5NzgtMTEyNTlmZjQyODgzMTUzMjU2OTAxNjE3MA==");
		//
		// String result2 = HttpUtils.getInstance().httpPost(url2,
		// JSONObject.fromObject(ticket).toString());
		//
		// //
		// {"retCode":"000000","retDesc":"成功","data":{"userId":"546a4e5ed8914c02aa4e5ed8915c0240","name":"测试用例","gender":"2","dafaultIdentity":"1","orgRelList":[{"orgId":"15484566546045169e36f8d9e2a2491e","orgCode":"3132001314","orgName":"太仓市沙溪第一中学","orgIdentity":"1"},{"orgId":"0021752645f14063884ad7418b10914d","orgCode":"2144002195","orgName":"广州市增城区新塘镇南安小学","orgIdentity":"1"}]}}
		//
		// System.out.println(result2);
		//
		// JSONObject userInfo = JSONObject.fromObject(result2);
		//
		// if ("000000".equals(userInfo.optString("retCode"))) {
		//
		// JSONObject userInfoData = userInfo.optJSONObject("data");
		//
		// String userName = userInfoData.optString("name");
		//
		// System.out.println(userName);
		//
		// }
		//
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// test();

		// Integer doublea = 20;
		//
		// Double aa = Double.valueOf(doublea.toString());
		//
		// System.out.println(aa);

		// test();

		// Double a = 1.3;
		// Double b = 2.6;
		// System.out.println(a.intValue());
		// System.out.println(b.intValue());
		// int rateNum = a.intValue() - b.intValue();
		// System.out.println(rateNum);
		//
		// if (a instanceof Double) {
		// System.out.println(1);
		// }

		// Integer abb = -791882625;
		//
		// System.out.println(abb);

		// Map<String, Object> s = new HashMap<String, Object>();

		// s.put("update", "S2N");
		// s.put("platformCode", "420000");
		// s.put("update", "1");
		// for (int i = 0; i < 500; i++) {
		// String result = HttpUtils.getInstance().httpPost(
		// "http://ebookpackage.t.huijiaoyun.com:20175/ebookpackage/api/newProductInfo/newProductList",
		// JSONObject.fromObject(s).toString());
		//// System.out.println(result);
		// // Thread.sleep(900);
		// JSONObject jsonObject = JSONObject.fromObject(result);
		//
		// JSONArray array = jsonObject.optJSONArray("data");
		//// System.out.println(array.size());
		//
		// if (array.size() == 0) {
		// System.out.println(jsonObject);
		// }
		//
		// }
		// Double a = new Double(29.0);
		//
		// Integer b = Integer.valueOf(a.toString());
		//
		// System.out.println(b);

		// Calendar cal = Calendar.getInstance();
		// StringBuffer sb = new StringBuffer();
		// sb.append(cal.get(Calendar.YEAR));
		// int month = cal.get(Calendar.MONTH) + 1;
		// if (month < 10) {
		// sb.append("0").append(month);
		// } else {
		// sb.append(month);
		// }
		//
		// int date = cal.get(Calendar.DAY_OF_MONTH);
		//
		// if(date<10){
		// sb.append("0").append(date);
		// }else{
		// sb.append(date);
		// }
		//
		//
		//
		// sb.append("00").append("00").append("00");
		//
		// System.out.println(sb.toString());

		// List<String> sssss = new ArrayList<String>();
		//
		// sssss.add("11");
		// System.out.println(sssss.get(0)+":"+sssss.size());
		//
		// JSONObject jsonObject = new JSONObject();
		// jsonObject.put("lastModified", "1496797178000");
		//
		// System.out.println(TimeUtil.date2String(new
		// Date(Long.valueOf(jsonObject.get("lastModified").toString()))));
		//
		// Date date =
		// TimeUtil.string2Date("20170413000000",TimeUtil.STR_DATETIME_PATTERN);
		//
		// System.out.println(date.getTime());
		// String sss = "111111";
		//
		// if(!sss.contains(":")){
		// System.out.println(1);
		// }
		//
		// String ss = "2017_04_20_16_29_37";
		//
		//// String[] startTimeStr = ss.split("_");
		//
		// String[] endTimeArr = ss.split("_");
		// String endTimeStr = endTimeArr[0] + "-" + endTimeArr[1] + "-" +
		// endTimeArr[2];
		// Date endTime = CommonFunction.getSampleDate(endTimeStr);
		//
		// System.err.println(endTime.getTime());
		//
		// JSONObject s = new JSONObject();
		//
		// s.put("11", "22");
		// s.put("11", "22");
		//
		// System.out.println(s);

		// Boolean flag = Boolean.parseBoolean("1");
		// System.out.println(flag);
		// String s = null;
		//
		// s="hello world!";
		//
		// s =s+"11";
		//
		// System.out.println("111");

		// int a=1;
		// System.out.println(String.valueOf(a));

		// System.out.println(getTimeInterval(new Date()));

		// System.out.println(getLastTimeInterval());

		// int a = 39 - 568;
		// int b = 599 - 568;
		//
		// System.out.println(a);
		// BigDecimal bigDecimal = new BigDecimal(0);
		//
		// BigDecimal bigDecimal2 = new BigDecimal(568);
		//
		// String rate = BigDecimalUtils
		// .getPrettyNumber(bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
		// BigDecimal(100)).toString());
		//
		// System.out.println(rate);

		// 查询 学校下的老师人数 接口 aamif/rest/school/statistics
		// http://116.211.105.43:22007/aamif/rest/school/statistics/4c6b9222a2644dde9eff0819325e9ae5

		// 查询区域下的所有学校接口
		// BigDecimal b1 = new BigDecimal(0);
		// BigDecimal b2 = new BigDecimal(300);
		// String rate = BigDecimalUtils
		// .getPrettyNumber(b1.divide(b2, 2,
		// BigDecimal.ROUND_HALF_EVEN).multiply(new
		// BigDecimal(100)).toString());
		//
		// System.out.println(rate);

		// List<AreaMonthUsageCount> list = new
		// ArrayList<AreaMonthUsageCount>();
		// AreaMonthUsageCount bean = new AreaMonthUsageCount();
		// bean.setUseCount(1);
		// list.add(bean);
		//
		// bean = new AreaMonthUsageCount();
		// bean.setUseCount(2);
		// list.add(bean);
		//
		// bean = new AreaMonthUsageCount();
		// bean.setUseCount(3);
		// list.add(bean);
		//
		//
		// Collections.sort(list, new Comparator<AreaMonthUsageCount>() {
		// @Override
		// public int compare(AreaMonthUsageCount bean1, AreaMonthUsageCount
		// bean2) {
		// return bean2.getUseCount().compareTo(bean1.getUseCount());
		// }
		// });
		//
		// for(int i=0;i<list.size();i++){
		// AreaMonthUsageCount eg = (AreaMonthUsageCount)list.get(i);
		//
		// eg.setOrgRanking(i+1);
		// System.out.println(eg.getUseCount()+":"+eg.getOrgRanking());
		// }
		// Map<String,Object> param = new HashMap<String,Object>();
		//
		// param.put("test", 1);
		// System.out.println(JSONObject.fromObject(param).toString());

		// WidgetLog bean1 = new WidgetLog();
		// bean1.setClassId("1");
		// bean1.setClassType("2");
		// bean1.setCpuInfo("2");
		// WidgetHistory bean2 = new WidgetHistory();
		//
		// try {
		// BeanUtilsBean.getInstance().copyProperties(bean2, bean1);
		// System.out.println("1");
		// } catch (IllegalAccessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// BigDecimal current = new BigDecimal(8078);
		// BigDecimal last = new BigDecimal(6777);
		//
		// System.out.println(current.subtract(last).divide(last, 2,
		// BigDecimal.ROUND_HALF_EVEN)
		// .multiply(new BigDecimal(100)).toString());

		//
		// List<String> ss = new ArrayList<String>();
		// ss.add("111");
		//
		// if(ss.size()<=10){
		//
		// }else{
		//
		// }
		//
		// for(int i=0;i<=10;i++){
		// System.out.println(ss.get(i));
		// }

		// Map<String,Object> s = new HashMap<String,Object>();
		// Object ss = s.get("bb");
		// System.out.println(ss);

		// Set<String> s = new HashSet<String>();
		//
		// s.add("1");
		//
		// if(s.contains("1"))
		// System.out.println(11);

		// {"start":"0","provincecode":"440000","areacode":"441400","type":"1","end":"10"}

		// String url =
		// "http://116.211.105.43:22007/aamif/rest/area/querySchoolInfoRep";
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("type", "1");// 1.学校 2.机构
		// map.put("provincecode", 440000);
		// map.put("areacode", 441400);
		// map.put("start", "0");
		// map.put("end", "10");
		//
		// String resultResp = HttpUtils.getInstance().httpPost(url,
		// JsonUtils.objTojson(map));
		//
		// System.out.println(resultResp);

		// Date startTime = TimeUtil.getCurrentMonthStartDate();
		// System.out.println(CommonFunction.getDateSampleString(startTime));

		// Calendar cal = Calendar.getInstance();
		// int year = cal.get(Calendar.YEAR);
		// int month = cal.get(Calendar.MONTH);
		// cal.add(Calendar.MONTH, -1);
		//
		// System.out.println(month);
		//
		// month = cal.get(Calendar.MONTH);
		// System.out.println(month);

		// Date startTime = TimeUtil.getCurrentMonthStartDate();
		// Date endTime = TimeUtil.getCurrentMonthEndDate();
		// System.out.println(CommonFunction.getDateSampleString(startTime));

		// parm.put("startTime", CommonFunction.getDateSampleString(startTime));
		// parm.put("endTime", CommonFunction.getDateSampleString(endTime));
		// Integer aa = null;

		// System.out.println(Integer.valueOf(aa));

		// Map<String,Integer> ss = new HashMap<String,Integer>();
		//
		// ss.put("11", 11);
		// ss.put("22", 22);
		//
		// Set s = ss.entrySet();
		//
		// System.out.println(s.size());
		//
		// List<Map<String,Integer>> b = new ArrayList<Map<String,Integer>>();
		// b.addAll(s);
		// for(int i=0;i<b.size();i++){
		// Map.Entry<String, Integer> entry1 = (Map.Entry<String,
		// Integer>)b.get(i);
		//
		// System.out.println(entry1.getKey()+""+entry1.getValue());
		//
		// }
		// Date startTime = new Date();
		// String s =
		// HttpUtils.getInstance().httpGet("http://hdkt.p.huijiaoyun.com:40175/hdcourse/heartBeat/check");
		// int complateCount=0;
		// int errorCount=0;
		// while(true){
		// try{
		// complateCount=complateCount+1;
		// HttpUtils.getInstance().httpGet("http://hdkt.p.huijiaoyun.com:40175/hdcourse/heartBeat/check");
		// System.out.println("正确次数:"+complateCount+" 错误次数:"+errorCount);
		// Thread.sleep(4000);
		// }catch(Exception e){
		// errorCount = errorCount+1;
		// }
		// }
		// Date endTime = new Date();

		// Boolean b = Boolean.valueOf("1");
		// Boolean c = Boolean.valueOf("2");
		// System.out.println(b +""+ c);

		// String content = "你好";
		// // String content = messageDomain.getCode();
		// // 鉴权code
		// String sp_spcode = "APPCODE3";
		// // 鉴权password
		// String sp_password = "12345678";
		// // 发短信地址
		// String sms_url = "http://10.8.10.156:18092/apimgt/smsJsonService";
		// String product_Id = "testproductId";
		//
		// String timeOut = "2000";
		//// if (StringUtil.isEmptyString (timeOut)) {
		//// timeOut = "3000";
		//// }
		// int time = Integer.valueOf (timeOut);
		// SMS.resetSystemParamHttpTimeOut (time);
		// String result = SMS.sendSms (product_Id, sp_spcode, sp_password,
		// sms_url, content, messageDomain.getAccount (), senderName);
		//

		// Integer sa = null;
		// System.out.println(sa);

		// System.out.println(sa.intValue());

		// System.out.println(Integer.parseInt(null));

	}

	public static byte[] gzip(String foo) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzos = null;

		try {
			gzos = new GZIPOutputStream(baos);
			gzos.write(foo.getBytes("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (gzos != null)
				try {
					gzos.close();
				} catch (IOException ignore) {
				}
			;
		}

		return baos.toByteArray();
	}

	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	static SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);

	/**
	 * 根据当前日期获得所在周的日期区间（周一和周日日期）
	 * 
	 * @return
	 * @author zhaoxuepu
	 * @throws ParseException
	 */
	static public String getTimeInterval(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		String imptimeBegin = sdf.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		return imptimeBegin + "," + imptimeEnd;
	}

	/**
	 * 根据当前日期获得上周的日期区间（上周周一和周日日期）
	 * 
	 * @return
	 * @author zhaoxuepu
	 */
	static public String getLastTimeInterval() {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		int offset1 = 1 - dayOfWeek;
		int offset2 = 7 - dayOfWeek;
		calendar1.add(Calendar.DATE, offset1 - 7);
		calendar2.add(Calendar.DATE, offset2 - 7);
		// System.out.println(sdf.format(calendar1.getTime()));// last Monday
		String lastBeginDate = sdf.format(calendar1.getTime());
		// System.out.println(sdf.format(calendar2.getTime()));// last Sunday
		String lastEndDate = sdf.format(calendar2.getTime());
		return lastBeginDate + "," + lastEndDate;
	}
}
