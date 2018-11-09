package com.whty.assis.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.api.respvo.SoftAreaUpgrade;
import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.demo.model.Soft;
import com.whty.assis.demo.service.SoftComponentService;
import com.whty.assis.demo.service.SoftService;
import com.whty.common.cache.data.GetCacheBaseData;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.SysConfig;

import net.sf.json.JSONObject;

/**
 * 接口-客户端版本管理接口
 * 
 * @author zhujg
 * @date 2016-3-2 14:20:00
 */
@Controller
@RequestMapping("/api/clientVersion_1")
public class ClientVersionController_1 extends BaseController {

	public static final String FAIL_USER_CODE = "000001";// 操作失败
	public static final String FAIL_USER_MSG = "数据问题-指定用户可升级的最新版本超过1个";

	public static final String FAIL_ALL_USER_CODE = "000002";// 操作失败
	public static final String FAIL_ALL_USER_MSG = "数据问题-全部用户可升级的最新版本超过1个";

	public static final String DATA_FAIL_CODE = "001001";// 操作失败
	public static final String DATA_FAIL_MSG = "数据问题-可升级的最新版本超过1个";

	@Autowired
	private SoftService softService;
	@Autowired
	private SoftComponentService softComponentService;

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	@RequestMapping(value = "packageUrl", method = RequestMethod.GET)
	public void packageUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("packageId");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		Map<String, Object> resultObject = softService.loadFilePackage(param);

		HttpStatus statusCode = HttpStatus.OK;

		Map<String, Object> softParam = new HashMap<String, Object>();

		softParam.put("id", resultObject.get("NEW_SOFT_ID"));

		List<Soft> softList = softService.querySoft(softParam);

		Soft soft = softList.get(0);

		if (resultObject.get("BOS_ADDRESS") != null) {
			String bosAddress = resultObject.get("BOS_ADDRESS").toString();
			response.sendRedirect(bosAddress);
		} else {
			response.sendRedirect(soft.getBosUrl());
		}

		// return new ResponseEntity(null, statusCode);
	}

	// @RequestMapping(value = "packageUrl", method = RequestMethod.GET)
	// public void packageUrl(HttpServletRequest request, HttpServletResponse
	// response) throws IOException {
	// Map<String, Object> params = new HashMap<String, Object>();
	// String ip = getIp(request);
	// params.put("ip", ip);
	// String id =
	// "5022d012aded42318775b1d4cf8debbc";//request.getParameter("packageId");
	// List<String> versionList = softService.queryVersions(id);
	// if(versionList.size()>0){
	// String newVersionCode = versionList.get(0);
	// String oldVersionCode = versionList.get(1);
	// params.put("old_version_code", oldVersionCode);
	// params.put("new_version_code", newVersionCode);
	// softService.addClientVersion(params);
	// }
	// Map<String, Object> param = new HashMap<String, Object>();
	// param.put("id", id);
	// Map<String, Object> resultObject = softService.loadFilePackage(param);
	//
	// HttpStatus statusCode = HttpStatus.OK;
	//
	// Map<String, Object> softParam = new HashMap<String, Object>();
	//
	// softParam.put("id", resultObject.get("NEW_SOFT_ID"));
	//
	// List<Soft> softList = softService.querySoft(softParam);
	//
	// Soft soft = softList.get(0);
	//
	// if (resultObject.get("BOS_ADDRESS") != null) {
	// String bosAddress = resultObject.get("BOS_ADDRESS").toString();
	// response.sendRedirect(bosAddress);
	// } else {
	// response.sendRedirect(soft.getBosUrl());
	// }
	//
	// // return new ResponseEntity(null, statusCode);
	// }

	// @RequestMapping(value = "packageUrl", method = RequestMethod.GET)
	// public ResponseEntity<String> packageUrl(HttpServletRequest request,
	// HttpServletResponse response)
	// throws IOException {
	// String id = request.getParameter("packageId");
	//
	// Map<String, Object> param = new HashMap<String, Object>();
	// param.put("id", id);
	// Map<String, Object> resultObject = softService.loadFilePackage(param);
	//
	// // if (resultObject == null)
	// // return;
	//
	// InputStream inputStream = null;
	// ServletOutputStream out = null;
	//
	// // File file = new File(resultObject.get("ABSOLUTE_URL").toString());
	// HttpStatus statusCode = HttpStatus.OK;
	// try {
	// File file = new File(resultObject.get("ABSOLUTE_URL").toString());
	// int fSize = Integer.parseInt(String.valueOf(file.length()));
	//
	// logger.info(resultObject.get("ABSOLUTE_URL") + ":" + fSize);
	// logger.info("Range:" + request.getHeader("Range"));
	//
	// response.setCharacterEncoding("utf-8");
	// response.setContentType("application/x-download");
	// response.setHeader("Accept-Ranges", "bytes");
	// response.setHeader("Content-Length", String.valueOf(fSize));
	// response.setHeader("Content-Disposition", "attachment;fileName="
	// +
	// CommonFunction.getExtensionName(resultObject.get("ABSOLUTE_URL").toString()));
	//
	// inputStream = new
	// FileInputStream(resultObject.get("ABSOLUTE_URL").toString());
	// long pos = 0;
	// if (null != request.getHeader("Range")) {
	// // 断点续传
	// response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
	// try {
	// String[] s = request.getHeader("Range").replaceAll("bytes=",
	// "").split("-");
	//
	// // Long endPos = Long.valueOf(s[1]);
	// Long startPos = Long.valueOf(s[0]);
	//
	// pos = startPos;
	// } catch (NumberFormatException e) {
	// pos = 0;
	// }
	// }
	// out = response.getOutputStream();
	// String contentRange = new StringBuffer("bytes ").append(pos +
	// "").append("-").append((fSize - 1) + "")
	// .append("/").append(fSize + "").toString();
	// response.setHeader("Content-Range", contentRange);
	// inputStream.skip(pos);
	// byte[] buffer = new byte[1024 * 10];
	// int length = 0;
	// while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
	// out.write(buffer, 0, length);
	// // Thread.sleep(100);
	// }
	// } catch (Exception e) {
	// logger.error("id:" + id);
	// logger.error("ODEX软件下载异常：" + e);
	// statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
	// } finally {
	// try {
	// if (null != out)
	// out.flush();
	// if (null != out)
	// out.close();
	// if (null != inputStream)
	// inputStream.close();
	// } catch (IOException e) {
	// }
	// }
	// return new ResponseEntity(null, statusCode);
	// }

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/gzipTest", method = RequestMethod.POST)
	@ResponseBody
	public void gzipTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String str = "[{\"studentId\":\"cb617982401f4e8bb9d3586b16e4208c\",\"studentName\":\"学生00\",\"cardNumber\":1,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"e3862228b50145b7a3cbed40f2389daa\",\"studentName\":\"学生01\",\"cardNumber\":2,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"5a5c77685bbd4aa8a51107b72a66afbd\",\"studentName\":\"学生02\",\"cardNumber\":3111111111,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"ec90ad5c1a674f5cacf25d48cad9e67c\",\"studentName\":\"学生03\",\"cardNumber\":4,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"f0578cbb9c6f4b7898d82813b6e0e7f2\",\"studentName\":\"学生04\",\"cardNumber\":5,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"724128d24c424cc5af7399fe4b49ad0e\",\"studentName\":\"学生05\",\"cardNumber\":6,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"6be8107f83354a75a330325afd721f6c\",\"studentName\":\"学生06\",\"cardNumber\":7,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"3d1a4ba1182c4ef7b3d8da069586fdb6\",\"studentName\":\"学生07\",\"cardNumber\":8,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"8878230cae4f4fdaa9d54f65e770c78b\",\"studentName\":\"学生08\",\"cardNumber\":9,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"c1bd99cc139f42c4a068be40d56e2489\",\"studentName\":\"学生09\",\"cardNumber\":10,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"50c00f193c20451cb8faf9f33be78bc0\",\"studentName\":\"学生10\",\"cardNumber\":11,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"3bf72b30cc4c4959adfaa08d3bc90642\",\"studentName\":\"学生11\",\"cardNumber\":12,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"f7a60a97a29141b7a77248eda3f426ba\",\"studentName\":\"学生12\",\"cardNumber\":13,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"cfdf970463bb4bd4a92284c92c021bd0\",\"studentName\":\"学生13\",\"cardNumber\":14,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"ed11609d3a9549e5962acb8bfbaa078e\",\"studentName\":\"学生14\",\"cardNumber\":15,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"dedbfdd61bfe4839b6d1bddb2f91d11f\",\"studentName\":\"学生15\",\"cardNumber\":16,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"e2836335cf4c4ef692cc78ce033aa891\",\"studentName\":\"学生16\",\"cardNumber\":17,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"bb3eab2c8bae46f8890a5325610145d6\",\"studentName\":\"学生17\",\"cardNumber\":18,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"c06c018ee35249f68e76ad1dc55c6fd8\",\"studentName\":\"学生18\",\"cardNumber\":19,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"b320f0ffb59748c5bc6112481a5c9794\",\"studentName\":\"学生19\",\"cardNumber\":20,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"cd2815d83322431ca144dfcab324edb1\",\"studentName\":\"学生20\",\"cardNumber\":21,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"87b33cb41e3843cba1697c3c6a0f3a19\",\"studentName\":\"学生21\",\"cardNumber\":22,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"2201ef9b7d2e4adb93fd4bf633248216\",\"studentName\":\"学生22\",\"cardNumber\":23,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"6fc1c7e48b884923ac5bc5be03a8cf8d\",\"studentName\":\"学生23\",\"cardNumber\":24,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"fe1d83cd723f4310abab3c82aa27b33c\",\"studentName\":\"学生24\",\"cardNumber\":25,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"ba3a884eeb1d4be29e73202c5b7a2128\",\"studentName\":\"学生25\",\"cardNumber\":26,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"f06be8497dd64223a731710356583622\",\"studentName\":\"学生26\",\"cardNumber\":27,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"84ccaf036bb3476baead8aab5ceceef4\",\"studentName\":\"学生27\",\"cardNumber\":28,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"6bd51c24217d4605a2d72cbe4410ae15\",\"studentName\":\"学生28\",\"cardNumber\":29,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"ea57913594834c1591a37ba20262530c\",\"studentName\":\"学生29\",\"cardNumber\":30,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"176c1bf634fe4d6ba4887c76cd734f31\",\"studentName\":\"学生30\",\"cardNumber\":31,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"9c4fea2d9658489d90dfe1ecce9377f5\",\"studentName\":\"学生31\",\"cardNumber\":32,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"d35f248659c74cb995985d6b009d5634\",\"studentName\":\"学生32\",\"cardNumber\":33,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"38a6d379f69c483eba4345e069962f49\",\"studentName\":\"学生33\",\"cardNumber\":34,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"302417b9191245559bbc84a63291e3fd\",\"studentName\":\"学生34\",\"cardNumber\":35,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"0455026ab39e4c4ab6ed23c17805f9ac\",\"studentName\":\"学生35\",\"cardNumber\":36,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"a4112499fbd8457bb2f1e2050ed82831\",\"studentName\":\"学生36\",\"cardNumber\":37,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"c2891adb7c4a4780872c9c40935884cb\",\"studentName\":\"学生37\",\"cardNumber\":38,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"7cfad6560f60488284ba4b8ab98c7610\",\"studentName\":\"学生38\",\"cardNumber\":39,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"4e1438aa401846ddbc623cc2907192c1\",\"studentName\":\"学生39\",\"cardNumber\":40,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"ea849f92dc3948428499f0095d144dbe\",\"studentName\":\"学生40\",\"cardNumber\":41,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"1d0f289d7edb4af4be960f6815f9d243\",\"studentName\":\"学生41\",\"cardNumber\":42,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"c43d9adfe21c43cd906c0518f92cc7dd\",\"studentName\":\"学生42\",\"cardNumber\":43,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"fc8a94a1cc1d46f49eab9b1af4fb5401\",\"studentName\":\"学生43\",\"cardNumber\":44,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"55dae047243246f8aa52fdb063c67fb6\",\"studentName\":\"学生44\",\"cardNumber\":45,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"275851c869374e1f873d6b1a716de6b7\",\"studentName\":\"学生45\",\"cardNumber\":46,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"81feed3445b849db94e70d437dcf6f11\",\"studentName\":\"学生46\",\"cardNumber\":47,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"d4c2f25405944d74826466e57cd26069\",\"studentName\":\"学生47\",\"cardNumber\":48,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"57098599c04b4c02a03fac085c0200b7\",\"studentName\":\"学生48\",\"cardNumber\":49,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"13eb91fb8c1d46f594498b3240cae6ea\",\"studentName\":\"学生49\",\"cardNumber\":50,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"45a4be4e241d40c489964a7205fbc338\",\"studentName\":\"学生50\",\"cardNumber\":51,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"fd4f969c55334faaa34d694bea17c560\",\"studentName\":\"学生51\",\"cardNumber\":52,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"b2b1f410d51945daaab4b5ca9b5cd203\",\"studentName\":\"学生52\",\"cardNumber\":53,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"7b08a9a3c5704a0ab94909c68d953dd9\",\"studentName\":\"学生53\",\"cardNumber\":54,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"7bd161c552144dc39f36b0535d71f51a\",\"studentName\":\"学生54\",\"cardNumber\":55,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"f1449229cc3342feb3388d53d963140f\",\"studentName\":\"学生55\",\"cardNumber\":56,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"1ee0de2fb7cf4cbf9cfd08396a6db0c6\",\"studentName\":\"学生56\",\"cardNumber\":57,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"79ce615745e34fe3b1243322c32e73b9\",\"studentName\":\"学生57\",\"cardNumber\":58,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"649c86eca52a4f7eb47d7d333959a18c\",\"studentName\":\"学生58\",\"cardNumber\":59,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"fe462604e28941769bb2d045a6706624\",\"studentName\":\"学生59\",\"cardNumber\":60,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"}]";

		// String str2 = new String(Base64.encodeBase64(ZipUtil.compress2(str)),
		// "UTF-8");

		// CommonFunction.writeResp(resp, GZipUtil.gzipString(str));
	}

	/*
	 * 正常增量升级，检测新版本
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/checkNew", method = RequestMethod.POST)
	@ResponseBody
	public void checkNew(HttpServletRequest req, HttpServletResponse resp, @RequestBody String body)
			throws IOException {
		// 默认没有新版本
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", Constants.SUCCESS_CODE);
		resultJson.put("resultDesc", "success");
		resultJson.put("isNew", "0");
		JSONObject dataJson = new JSONObject();
		try {
			// 检查参数
			Map param = new HashMap();
			param = checkParam(body);

			// 根据用户id，所属平台编码，用户版本号查询新版本列表
			List<Map> softList = softService.newSoftList_1(param);

			Map newSoft = null;// 非指定用户的最新版本
			Map newSoftToUser = null;// 指定用户的最新版本
			Map newSoftToArea = null;// 指定区域升级
			// 是否有指定用户升级的版本-是否指定用户更新(0:否，1：是)
			for (Map map : softList) {

				Map<String, Object> softAreaUpgrade = new HashMap<String, Object>();

				softAreaUpgrade.put("softId", map.get("ID"));

				List<SoftAreaUpgrade> softAreaList = softService.querySoftAreaUpgrade(softAreaUpgrade);
				if (softAreaList != null && softAreaList.size() > 0) {
					// String remoteIp = HttpUtils.getIpAddr(req);
					// String remoteIp = "61.183.234.146";
					// IPUtils.load(req.getSession().getServletContext().getRealPath("/")
					// + "17monipdb.dat");

					// String[] addressNameStrArr = IPUtils.find(remoteIp);

					// 根据用户id找到所在学校的地理信息
					String userId = (String) param.get("userId");
					String userPlatformCode = (String) param.get("userPlatformCode");
					if ((userId != null && !"".equals(userId.trim()))
							&& (userPlatformCode != null && !"".equals(userPlatformCode))) {

						try {
							String requestUrl = GetCacheBaseData.getPropertyValue("platform_url", userPlatformCode)
									+ "user/" + userId;
							String json = HttpUtils.getInstance().httpGet(requestUrl);
							JSONObject result = JSONObject.fromObject(json);
							if ("000000".equals(result.get("result"))) {

								JSONObject userInfo = JSONObject.fromObject(result.get("userinfo").toString());

								String proviceCode = userInfo.optString("provicecode");
								String cityCode = userInfo.optString("citycode");

								for (SoftAreaUpgrade bean : softAreaList) {
									if (!StringUtils.isEmpty(proviceCode)) {
										if (bean.getAreaCode().equals(proviceCode)) {
											newSoftToArea = map;
										}
									}

									if (!StringUtils.isEmpty(cityCode)) {
										if (bean.getAreaCode().equals(cityCode)) {
											newSoftToArea = map;
										}
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					if (map.get("USER_UPDATE") != null && "1".equals(map.get("USER_UPDATE").toString())) {
						if (newSoftToUser == null) {
							newSoftToUser = map;
						} else {
							// 数据问题-指定用户可升级的最新版本超过1个
							resultJson.put("result", FAIL_USER_CODE);
							resultJson.put("resultDesc", FAIL_USER_MSG);
						}
					} else if ("1".equals(map.get("ALL_USER_UPGRADE").toString())) {
						if (newSoft == null) {
							newSoft = map;
						} else {
							// 数据问题-用户可升级的最新版本超过1个
							resultJson.put("result", FAIL_ALL_USER_CODE);
							resultJson.put("resultDesc", FAIL_ALL_USER_MSG);
						}
					}
				}

			}

			Map softMap = null;

			if (null != newSoftToUser) {
				// 有指定用户升级
				softMap = newSoftToUser;
			} else if (null != newSoft) {
				// 全部用户升级
				softMap = newSoft;
			} else if (null != newSoftToArea) {
				softMap = newSoftToArea;
			}

			if (softMap != null) {
				resultJson.put("isNew", "1");

				// 新增百度云下载地址
				dataJson.put("md5", softMap.get("MD5"));
				dataJson.put("url", softMap.get("BOS_URL"));

				dataJson.put("id", softMap.get("ID"));
				dataJson.put("softName", softMap.get("SOFT_NAME"));
				dataJson.put("versionCode", softMap.get("VERSION_CODE"));
				if (softMap.get("UPDATE_CONTENT") != null) {
					// dataJson.put("updateContent",
					// CommonFunction.clobToString((Clob)
					// softMap.get("UPDATE_CONTENT")));
					dataJson.put("updateContent", softMap.get("UPDATE_CONTENT"));
				} else {
					dataJson.put("updateContent", null);
				}
				dataJson.put("forceUpdate", softMap.get("FORCE_UPDATE"));
				dataJson.put("userUpdate", softMap.get("USER_UPDATE"));
				dataJson.put("fileUrl", softMap.get("FILE_URL"));
				dataJson.put("allUserUpgrade", softMap.get("ALL_USER_UPGRADE"));

				if (softMap.get("UPGRADE_PACKAGE") != null) {
					String file_path_pre = SysConfig.getStrValue("file_path_pre");
					String file_path_http = SysConfig.getStrValue("file_path_http");
					String upgradePackagePath = softMap.get("UPGRADE_PACKAGE").toString();
					dataJson.put("upgradePackage", file_path_http + upgradePackagePath.replace(file_path_pre, ""));
				} else {
					dataJson.put("upgradePackage", null);
				}
				dataJson.put("fileSize", softMap.get("FILE_SIZE"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			resultJson.put("result", Constants.FAIL_CODE);
			resultJson.put("resultDesc", e.getMessage());
		}

		resultJson.put("data", dataJson);
		CommonFunction.writeResp(resp, resultJson.toString());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map checkParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();

		para.put("versionCode", reqJson.get("versionCode"));
		// 添加软件类型 0.标准版 1.教师版
		para.put("softType", reqJson.get("softType"));

		if ("2.4.0.570000".equals(reqJson.get("versionCode"))) {
			para.put("softType", "4");
		}

		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("userPlatformCode", reqJson.get("userPlatformCode") == null ? "" : reqJson.get("userPlatformCode"));
		para.put("userId", reqJson.get("userId") == null ? "" : reqJson.get("userId"));
		return para;
	}

	/*
	 * 静默升级，检测新版本 参数-tag:0全部升级，1根据参数升级
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/checkNewSilent", method = RequestMethod.POST)
	@ResponseBody
	public void checkNewSilent(HttpServletRequest req, HttpServletResponse resp, @RequestBody String body)
			throws IOException {
		// 默认没有新版本
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", Constants.SUCCESS_CODE);
		resultJson.put("resultDesc", "success");
		resultJson.put("isNew", "0");
		JSONObject dataJson = new JSONObject();
		try {
			// 检查参数
			Map param = new HashMap();
			param = checkNewSilentParam(body);

			// 根据用户版本号,参数tag查询新版本列表
			List<Map> softList = softService.newSilentSoftList_1(param);
			Map softMap = null;

			if (null != softList && softList.size() > 0) {
				if (softList.size() > 1) {
					// 数据问题-可升级的最新版本超过1个
					resultJson.put("result", DATA_FAIL_CODE);
					resultJson.put("resultDesc", DATA_FAIL_MSG);
				} else {
					// 有静默升级新版本
					softMap = softList.get(0);
				}
			}

			if (softMap != null) {
				resultJson.put("isNew", "1");

				dataJson.put("id", softMap.get("ID"));
				dataJson.put("softName", softMap.get("SOFT_NAME"));
				dataJson.put("versionCode", softMap.get("VERSION_CODE"));
				if (softMap.get("UPDATE_CONTENT") != null) {
					// dataJson.put("updateContent",
					// CommonFunction.clobToString((Clob)
					// softMap.get("UPDATE_CONTENT")));
					dataJson.put("updateContent", softMap.get("UPDATE_CONTENT"));
				} else {
					dataJson.put("updateContent", null);
				}
				dataJson.put("fileUrl", softMap.get("FILE_URL"));

				if (softMap.get("UPGRADE_PACKAGE") != null) {
					String file_path_pre = SysConfig.getStrValue("file_path_pre");
					String file_path_http = SysConfig.getStrValue("file_path_http");
					String upgradePackagePath = softMap.get("UPGRADE_PACKAGE").toString();
					dataJson.put("upgradePackage", file_path_http + upgradePackagePath.replace(file_path_pre, ""));
				} else {
					dataJson.put("upgradePackage", null);
				}
				dataJson.put("fileSize", softMap.get("FILE_SIZE"));
			}

		} catch (Exception e) {
			e.printStackTrace();

			resultJson.put("result", Constants.FAIL_CODE);
			resultJson.put("resultDesc", e.getMessage());
		}

		resultJson.put("data", dataJson);
		CommonFunction.writeResp(resp, resultJson.toString());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkNewSilentParam(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("versionCode", reqJson.get("versionCode"));

		para.put("softType", reqJson.get("softType"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		String tag = reqJson.get("tag") == null ? null : reqJson.get("tag") + "";
		para.put("tag", (tag == null || !tag.trim().equals("1")) ? "0" : "1");

		// para.put("userId", reqJson.get("userId"));
		// para.put("userPlatformCode", reqJson.get("userPlatformCode"));

		return para;
	}
}
