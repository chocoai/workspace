package com.yhcrt.demo.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class UploadService {
	public static String upload(HttpServletRequest request) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					String picName = ""+sdf.format(new Date())+".jpg";
					if (myFileName.trim() != "") {
						/* 定义上传路径tomocat路劲webapps中   */
						String filePath1 = new File(request.getSession().getServletContext().getRealPath("/"))
								.getParent();
						/* 定义上传路径tomocat路劲webapps中创建文件夹名字，如：imgFile   */
						String fileName = "/imgFile/"+sdf.format(new Date())+"/";
						/* filePath是 tomocat路劲webapps中创建文件夹imgFile;如E:\apache-tomcat-8.0.41\webapps\imgFile */
						File filePath = new File(filePath1 + fileName);
						System.out.println(filePath1+">>>>>>>>>>>>>>>>>>>");
						System.out.println(filePath+">>>>>>>>>>>>>>>>>>>");
						if (!filePath.exists()) {
							filePath.mkdirs();
						}
						/*path是   E:\apache-tomcat-8.0.41\webapps\imgFile\Koala.jpg */
						String path = filePath.getAbsolutePath() + "\\" + picName;
						/* 服务器中上传图片的路劲，用于存入数据库 */
						String uploadName=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+fileName + picName;
						try {
							file.transferTo(new File(path));
							return uploadName;
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return null;
	}
}
