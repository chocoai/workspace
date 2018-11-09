package com.yhcrt.iHealthCloud.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.SysInterfaceLogWithBLOBs;
import com.yhcrt.iHealthCloud.entity.SysUser;
import com.yhcrt.iHealthCloud.mapper.DoctorMapper;
import com.yhcrt.iHealthCloud.mapper.EmployeeMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.SysInterfaceLogMapper;
import com.yhcrt.iHealthCloud.mapper.SysUserMapper;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.PropertiesUtil;
import com.yhcrt.iHealthCloud.util.RequestUtils;
import com.yhcrt.iHealthCloud.util.UUIDGenerator;

@Controller
@RequestMapping("/uploadServices")
public class UploadController {

	private static Logger Log = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private SysInterfaceLogMapper interfaceLogMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private DoctorMapper doctorMapper;
	
	

	@RequestMapping(value = "", method = { RequestMethod.POST, RequestMethod.GET })
	public void upolad(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String contentType = request.getHeader("Content-Type");
		Log.info(" Request Content-Type  :  {}", contentType);

		SysInterfaceLogWithBLOBs interfaceLog = new SysInterfaceLogWithBLOBs();
		String callTime = DateUtils.getCurrentTime();

		JSONObject jsonObj = new JSONObject();
		
		try {
			List<String> filePaths = multipartFileUpload(request);
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObj.put(Const.TAG_RMK, "");
			jsonObj.put(Const.TAG_BIZ, JSON.toJSON(filePaths));
		} catch (MaxUploadSizeExceededException e) {
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObj.put(Const.TAG_RMK, Constants.ExceptionMsg.MAX_UPLOAD_SIZE_EXCEEDED);
			e.printStackTrace();
			interfaceLog.setExceptionMsg(e.getMessage());
		} catch (Exception e) {
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObj.put(Const.TAG_RMK, Constants.ExceptionMsg.UPLOAD_FAILED);
			e.printStackTrace();
			interfaceLog.setExceptionMsg(e.getMessage());
		} finally {
			// 输出结果
			PrintWriter out = setResponseEncodingToUTF8andGetWriter(response);
			out.print(jsonObj.toJSONString());
			out.flush();
			out.close();
			interfaceLog.setCid(UUIDGenerator.getId());
			interfaceLog.setCallTime(callTime);
			interfaceLog.setCallIp(RequestUtils.getIpAddr(request));
			interfaceLog.setCallUrl(request.getRequestURL().toString());
			interfaceLog.setReturnValue(jsonObj.toJSONString());
			interfaceLog.setReplyTime(DateUtils.getCurrentTime());
			interfaceLogMapper.insert(interfaceLog);
		}

	}

	/**
	 * 将response的字符集设为utf-8并返回response的writer
	 * 
	 * @param HttpServletResponse
	 *            response
	 * @return PrintWriter
	 * @throws IOException
	 */
	private PrintWriter setResponseEncodingToUTF8andGetWriter(HttpServletResponse response) throws IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		return response.getWriter();
	}

	/**
	 * 小程序头像上传接口
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@Consumes("multipart/form-data")
	@RequestMapping("/xcx/uploadPicture")
	public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SysInterfaceLogWithBLOBs interfaceLog = new SysInterfaceLogWithBLOBs();
		String callTime = DateUtils.getCurrentTime();
		JSONObject jsonObj = new JSONObject();
		String filePaths = "";
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			String requestUrl = request.getRequestURL().toString();
			String urlPrefix = requestUrl.replace(request.getRequestURI(), "");
			MultipartFile uploadFile = multipartRequest.getFile("ufile");
			if (!uploadFile.isEmpty()) {
				String originalFilesName = uploadFile.getOriginalFilename();
				String realPath = request.getSession().getServletContext().getRealPath("/");
				String contextPath = request.getContextPath().substring(1);
				String rootPath = PropertiesUtil.getProperty("/config.properties", "attach.root.dir");
				// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
				int hashcode = originalFilesName.hashCode();
				int dir1 = hashcode & 0xf; // 0--15
				int dir2 = (hashcode & 0xf0) >> 4; // 0-15
				String dir = rootPath + "/" + dir1 + "/" + dir2;
				String destPath = realPath.replaceAll(contextPath, dir) + originalFilesName;
				File file = new File(destPath);
				// 如果目录不存在
				if (!file.exists()) {
					// 创建目录
					file.mkdirs();
				}
				uploadFile.transferTo(file);
				filePaths = urlPrefix + dir + "/" + originalFilesName;
			}
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObj.put(Const.TAG_RMK, "");
			jsonObj.put(Const.TAG_BIZ, JSON.toJSON(filePaths));
		} catch (MaxUploadSizeExceededException e) {
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObj.put(Const.TAG_RMK, Constants.ExceptionMsg.MAX_UPLOAD_SIZE_EXCEEDED);
			e.printStackTrace();
			interfaceLog.setExceptionMsg(e.getMessage());
		} catch (Exception e) {
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObj.put(Const.TAG_RMK, Constants.ExceptionMsg.UPLOAD_FAILED);
			e.printStackTrace();
			interfaceLog.setExceptionMsg(e.getMessage());
		} finally {
			// 输出结果
			PrintWriter out = setResponseEncodingToUTF8andGetWriter(response);
			out.print(jsonObj.toJSONString());
			out.flush();
			out.close();
			interfaceLog.setCid(UUIDGenerator.getId());
			interfaceLog.setCallTime(callTime);
			interfaceLog.setCallIp(RequestUtils.getIpAddr(request));
			interfaceLog.setCallUrl(request.getRequestURL().toString());
			interfaceLog.setReturnValue(jsonObj.toJSONString());
			interfaceLog.setReplyTime(DateUtils.getCurrentTime());
			interfaceLogMapper.insert(interfaceLog);
		}
	}

	@ResponseBody
	@Consumes("multipart/form-data")
	@RequestMapping("/setProfilePhoto")
	public void setProfilePhoto(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		try {
			String userId = request.getParameter("user_id");
			if (!StringUtils.isNotBlank(userId)) {
				throw new Exception("user_id不能为空");
			}
			SysUser user = sysUserMapper.selectByPrimaryKey(Integer.parseInt(userId));
			if (user == null) {
				throw new Exception("用户未找到,请检查user_id是否有误");
			}
			List<String> filePaths = multipartFileUpload(request);
			if (filePaths.isEmpty()) {
				throw new Exception("请选择你要上传的文件");
			}

			if (user.getUserType() == Constants.UserType.USER_TYPE_MEMBER) {
				memberMapper.setProfilePhotoByUserId(user.getUserId().toString(), filePaths.get(0));
			} else if (user.getUserType() == Constants.UserType.USER_TYPE_EMP) {
				employeeMapper.setProfilePhotoByUserId(user.getUserId().toString(), filePaths.get(0));
			} else {
				doctorMapper.setProfilePhotoByUserId(user.getUserId().toString(), filePaths.get(0));
			}

			jsonObject.put("isSuccess", 1);
			jsonObject.put("desc", "");
			jsonObject.put("profilePhotoUrl", filePaths.get(0));
		} catch (Exception e) {
			jsonObject.put("isSuccess", 0);
			jsonObject.put("desc", e.getMessage());
			jsonObject.put("profilePhotoUrl", "");
			e.printStackTrace();
		} finally {
			response.setContentType("application/json; charset=UTF-8");
			try {
				response.getWriter().write(jsonObject.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public List<String> multipartFileUpload(HttpServletRequest request) throws Exception {
		List<String> filePaths = new ArrayList<String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		String requestUrl = request.getRequestURL().toString();
		String urlPrefix = requestUrl.replace(request.getRequestURI(), "");
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile uploadFile = multipartRequest.getFile(fileName);
			if (!uploadFile.isEmpty()) {
				String originalFilesName = uploadFile.getOriginalFilename();
				String realPath = request.getSession().getServletContext().getRealPath("/");
				String contextPath = request.getContextPath().substring(1);
				String rootPath = PropertiesUtil.getProperty("/config.properties", "attach.root.dir");
				// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
				int hashcode = originalFilesName.hashCode();
				int dir1 = hashcode & 0xf; // 0--15
				int dir2 = (hashcode & 0xf0) >> 4; // 0-15
				String dir = rootPath + "/" + dir1 + "/" + dir2;
				String destPath = realPath.replaceAll(contextPath, dir) + originalFilesName;
				File file = new File(destPath);
				// 如果目录不存在
				if (!file.exists()) {
					// 创建目录
					file.mkdirs();
				}
				uploadFile.transferTo(file);
				filePaths.add(urlPrefix + dir + "/" + originalFilesName);
			}
		}
		return filePaths;
	}

}
