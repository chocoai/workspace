package com.whty.assis.api.controller;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.whty.assis.api.model.TUserBackground;
import com.whty.assis.api.model.UserBackgroundPic;
import com.whty.assis.api.service.TUserBackgroundService;
import com.whty.assis.api.service.UserBackgroundPicService;
import com.whty.assis.api.utils.ImageUtil;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.common.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/api/tuserBackGround")
public class TUserBackgroundController extends BaseController {

	@Autowired
	private TUserBackgroundService tUserBackgroundService;
	@Autowired
	private UserBackgroundPicService userBackgroundPicService;

	final SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 查询用户背景图片列表，互动课堂根据用户的登录信息，在工具-背景页面加载用户自定义的背景图片列表。
	 * 
	 * @param request
	 * @param response
	 * @param body（platformCode，loginPlatformCode，userId）
	 */
	@RequestMapping(value = "getUserBackGround", method = RequestMethod.POST)
	@ResponseBody
	public void getUserBackGround(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map<String, Object> result = new HashMap<>();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			JSONObject reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
			// 参数检验
			if (!CommonFunction.isNotNull(reqJson)) {
				throw new BusinessException("参数不存在");
			}
			paramMap.put("userId", reqJson.get("userId"));
			paramMap.put("platformCode", reqJson.get("platformCode"));
			paramMap.put("loginPlatformCode", reqJson.get("loginPlatformCode"));
			// 必填字段检查
			CommonFunction.checkParam(paramMap);

			String userId = paramMap.get("userId").toString();
			String platformCode = paramMap.get("platformCode").toString();
			String loginPlatformCode = paramMap.get("loginPlatformCode").toString();

			TUserBackground entity = new TUserBackground();
			entity.setPlatformCode(platformCode);
			entity.setLoginPlatformCode(loginPlatformCode);
			entity.setUserId(userId);

			entity = tUserBackgroundService.getListByTUserBackground(entity);
			if (entity == null) {
				TUserBackground entity2 = new TUserBackground();
				entity2.setBackgroundCode(GUIDGenerator.getUUID32());
				entity2.setPlatformCode(platformCode);
				entity2.setLoginPlatformCode(loginPlatformCode);
				entity2.setUserId(userId);
				entity2.setCreateTime(sdff.format(new Date()));
				entity2.setUpdateTime(sdff.format(new Date()));
				tUserBackgroundService.insertObj(entity2);
				entity = entity2;
			}
			List<UserBackgroundPic> list = userBackgroundPicService
					.selectByUserBackgroundCode(entity.getBackgroundCode());
			if (list != null && list.size() > 0) {
				JSONArray jsonArray = new JSONArray();
				for (UserBackgroundPic userBackgroundPic : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("resourceId", userBackgroundPic.getResourceId());
					jsonObject.put("url", userBackgroundPic.getDownUrl());
					jsonObject.put("md5", userBackgroundPic.getMd5());
					jsonObject.put("stretch", userBackgroundPic.getStreach());
					// jsonObject.put("sortNum",
					// userBackgroundPic.getSortNum());
					jsonObject.put("name", userBackgroundPic.getResourceName());
					jsonObject.put("thumbnail", userBackgroundPic.getThumbnail());
					jsonObject.put("createTime", userBackgroundPic.getCreateTime());
					jsonArray.add(jsonObject);
				}
				result.put("data", jsonArray);
			} else {
				JSONArray jsonArray = new JSONArray();
				result.put("data", jsonArray);
			}
			result.put("result", Constants.SUCCESS_CODE);
			result.put("resultDesc", "success");
		} catch (BusinessException e) {
			result.put("result", Constants.FAIL_CODE);
			result.put("resultDesc", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result.put("result", Constants.FAIL_CODE);
			result.put("resultDesc", e.getMessage());
			e.printStackTrace();
		}
		CommonFunction.writeResp(response, JSONObject.fromObject(result).toString());
	}

	/**
	 * 用户新增自定义背景图片，用户在工具-背景页面添加自定义背景，上传图片。
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "uploadPic", method = RequestMethod.POST)
	@ResponseBody
	public void uploadPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<>();
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Map<String, Object> paramMap = new HashMap<>();
				String userId = multiRequest.getParameter("userId");
				String platformCode = multiRequest.getParameter("platformCode");
				String loginPlatformCode = multiRequest.getParameter("loginPlatformCode");
				if (StringUtils.isEmpty(multiRequest.getParameter("stretch"))) {
					throw new BusinessException("参数stretch不能为空");
				}
				// if(StringUtils.isEmpty(multiRequest.getParameter("sortNum"))){
				// throw new BusinessException("参数sortNum不能为空");
				// }
				Integer streach = Integer.parseInt(multiRequest.getParameter("stretch"));
				// Integer sortNum =
				// Integer.parseInt(multiRequest.getParameter("sortNum"));
				paramMap.put("userId", userId);
				paramMap.put("platformCode", platformCode);
				paramMap.put("loginPlatformCode", loginPlatformCode);
				paramMap.put("streach", streach);
				// paramMap.put("sortNum", sortNum);
				// 必填字段检查
				CommonFunction.checkParam(paramMap);
				Iterator<String> iter = multiRequest.getFileNames();
				if (!iter.hasNext()) {
					throw new BusinessException("请上传图片");
				}
				while (iter.hasNext()) {
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						String picName = (int)((Math.random()*9+1)*1000)+".jpg";
						if (myFileName.trim() != "") {
							/* 定义上传路径tomocat路劲webapps中 */
							String filePath1 = new File(request.getSession().getServletContext().getRealPath("/"))
									.getParent();
							/* 定义上传路径tomocat路劲webapps中创建文件夹名字，如：imgFile */
							String fileName = "/imgFile/" + sdf.format(new Date()) + "/";
							/*
							 * filePath是
							 * tomocat路劲webapps中创建文件夹imgFile;如E:\apache-tomcat-8
							 * .0.41\webapps\imgFile
							 */
							File filePath = new File(filePath1 + fileName);
							if (!filePath.exists()) {
								filePath.mkdirs();
							}
							/*
							 * path是
							 * E:\apache-tomcat-8.0.41\webapps\imgFile\Koala.jpg
							 */
							String getTime = new Date().getTime() + "";
							String path = filePath.getAbsolutePath() + "/" + getTime + picName;
							/* 服务器中上传图片的路劲，用于存入数据库 */
							// uploadName=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+fileName
							// + picName;
							file.transferTo(new File(path));
							File filePicName = new File(path);
							System.out.println(filePicName + ">>>>>>>>>>>>>>>>>>>");
							String md5 = FileMDFive.getFileMD5(filePicName);
							String ak = SysConfig.getStrValue("baidu.bos.ak");
							String sk = SysConfig.getStrValue("baidu.bos.sk");
							String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
							String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
							String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
							String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
							Calendar cal = Calendar.getInstance();
							StringBuffer bosStringBuffer = new StringBuffer();
							bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
									.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH))
									.append(cal.get(Calendar.DAY_OF_MONTH)).append("/").append(cal.getTime().getTime());

							String bosPath = bosStringBuffer.toString();

							BosClientConfiguration config = new BosClientConfiguration();
							config.setCredentials(new DefaultBceCredentials(ak, sk));
							BosClient client = new BosClient(config);

							client.putObject(bucketname, bosStringBuffer + picName, filePicName);// 上传到百度云
							String bosAddress = baiduBosUrl + bosStringBuffer + picName;// 图标地址
							System.out.println(bosAddress);

							ImageUtil.changeImge(filePicName, 192, 108);
							bosPath = bosPath + "/thumbnail/";
							client.putObject(bucketname, bosPath + picName, filePicName);// 上传缩略图
							String thumbnail = baiduBosUrl + bosPath + picName;

							TUserBackground entity = new TUserBackground();
							entity.setBackgroundCode(GUIDGenerator.getUUID32());
							entity.setPlatformCode(platformCode);
							entity.setLoginPlatformCode(loginPlatformCode);
							entity.setUserId(userId);
							entity.setCreateTime(sdff.format(new Date()));
							entity.setUpdateTime(sdff.format(new Date()));

							UserBackgroundPic userBackgroundPic = new UserBackgroundPic();
							userBackgroundPic.setResourceId(GUIDGenerator.getUUID32());
							userBackgroundPic.setUserBackgroundCode(entity.getBackgroundCode());
							userBackgroundPic.setMd5(md5);
							userBackgroundPic.setResourceName(picName);
							userBackgroundPic.setDownUrl(bosAddress);
							userBackgroundPic.setStreach(streach);
							// userBackgroundPic.setSortNum(sortNum);
							userBackgroundPic.setCreateTime(sdff.format(new Date()));
							userBackgroundPic.setThumbnail(thumbnail);

							TUserBackground tUserBackground = tUserBackgroundService.getListByTUserBackground(entity);
							if (tUserBackground != null) {
								userBackgroundPic.setUserBackgroundCode(tUserBackground.getBackgroundCode());
								userBackgroundPicService.insert(userBackgroundPic);
							} else {
								tUserBackgroundService.insert(entity, userBackgroundPic);
							}
							result.put("result", Constants.SUCCESS_CODE);
							result.put("resultDesc", "success");
							result.put("resourceId", userBackgroundPic.getResourceId());
							result.put("md5", md5);
							result.put("resourceName", picName);
							result.put("url", bosAddress);
							result.put("thumbnail", thumbnail);
						} else {
							throw new BusinessException("图片没有名称");
						}
					} else {
						throw new BusinessException("图片无效");
					}
				}
			} else {
				throw new BusinessException("数据类型不是byte[]");
			}
		} catch (BusinessException e) {
			result.put("result", Constants.FAIL_CODE);
			result.put("resultDesc", e.getMessage());
			e.printStackTrace();
		} catch (IllegalStateException e) {
			result.put("result", Constants.FAIL_CODE);
			result.put("resultDesc", e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			result.put("result", Constants.FAIL_CODE);
			result.put("resultDesc", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			result.put("result", Constants.FAIL_CODE);
			result.put("resultDesc", e.getMessage());
			e.printStackTrace();
		}
		CommonFunction.writeResp(response, JSONObject.fromObject(result).toString());

	}

	/**
	 * 根据接口参数，对图片背景进行删除。调用这个接口时，后台应该要根据这个接口去删除服务器上的实体文件.
	 * 
	 * @param request
	 * @param response
	 * @param body（platformCode，loginPlatformCode，userId，backgroundCode）
	 */
	@RequestMapping(value = "deleteUserBackGround", method = RequestMethod.POST)
	@ResponseBody
	public void deleteUserBackGround(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map respMap = new HashMap();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			JSONObject reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
			// 参数检验
			if (!CommonFunction.isNotNull(reqJson)) {
				throw new BusinessException("参数不存在");
			}
			paramMap.put("userId", reqJson.get("userId"));
			paramMap.put("platformCode", reqJson.get("platformCode"));
			paramMap.put("loginPlatformCode", reqJson.get("loginPlatformCode"));
			paramMap.put("resourceId", reqJson.get("resourceId"));
			// 必填字段检查
			CommonFunction.checkParam(paramMap);
			String backgroundCode = paramMap.get("resourceId").toString();
			userBackgroundPicService.delete(backgroundCode);
			respMap.put("result", Constants.SUCCESS_CODE);
			respMap.put("resultDesc", "success");
		} catch (BusinessException e) {
			respMap.put("result", Constants.FAIL_CODE);
			respMap.put("resultMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			respMap.put("result", Constants.FAIL_CODE);
			respMap.put("resultMsg", e.getMessage());
			e.printStackTrace();
		}
		CommonFunction.writeResp(response, JSONObject.fromObject(respMap).toString());
	}

}
