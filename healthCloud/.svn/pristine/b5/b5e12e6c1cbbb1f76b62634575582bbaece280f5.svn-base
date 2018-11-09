package com.yhcrt.healthcloud.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 
 * @author rpf
 *
 */
public class UploadUtils {

	/**
	 * 上传文件
	 * 
	 * @author rpf
	 * @param fileName表单中对应的文件名
	 * @param businessType上传的文件所属的业务类型
	 * @return 文件保存位置的相对路径(相对于tomcat的根路径)
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadFile(HttpServletRequest request, String fileName, String businessType) {
		ServletContext servletContext = request.getSession().getServletContext();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(servletContext);
		if (!multipartResolver.isMultipart(request)) {
			return "";
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取表单中提交的文件，fileName表单中对应的文件名
		CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest.getFile(fileName);
		if (orginalFile == null || orginalFile.isEmpty()) {
			return "";
		}
		String originalFileName = orginalFile.getOriginalFilename();
		String realPath = new File(servletContext.getRealPath("/")).getParent();
		String rootPath = PropertiesUtil.getProperty("/config.properties", "attach.root.dir");
		String relativePath = rootPath + businessType + "/" + DateUtil.dateToString8(new Date());
		File file = new File(realPath + relativePath);
		// 如果文件不存在就创建目录
		if (!file.exists()) {
			file.mkdirs();
		}
		final String destFileName = UUIDGenerator.getId()
				+ originalFileName.substring(originalFileName.lastIndexOf("."));
		// 目标文件路径
		final String destFilePath = realPath + relativePath + "/" + destFileName;
		// 目标文件
		final File destFile = new File(destFilePath);
		// 上传源文件
		try {
			orginalFile.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		String pathPrefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String saveDir = pathPrefix + relativePath + "/" + destFileName;
		return saveDir;
	}

}
