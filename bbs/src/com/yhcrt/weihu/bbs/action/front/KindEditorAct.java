package com.yhcrt.weihu.bbs.action.front;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.sf.json.JSONObject;

@Controller
public class KindEditorAct {
	
	@RequestMapping("/kindEditor/imgUpload.jhtml")
	@ResponseBody
	public void test(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		//定义允许上传的文件扩展名
//		HashMap<String, String> extMap = new HashMap<String, String>();
//		extMap.put("image", "gif,jpg,jpeg,png,bmp");
//		extMap.put("flash", "swf,flv");
//		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
//		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
//		//最大文件大小
//		long maxSize = 1000000;
		
//		if(!ServletFileUpload.isMultipartContent(request)){
//			response.getWriter().println(getError("请选择文件。"));
//			return;
//		}
		//检查目录
//		File uploadDir = new File(savePath);
//		if(!uploadDir.isDirectory()){
//			response.getWriter().println(getError("上传目录不存在。"));
//			return;
//		}
		//检查目录写权限
//		if(!uploadDir.canWrite()){
//			response.getWriter().println(getError("上传目录没有写权限。"));
//			return;
//		}
//		String dirName = request.getParameter("dir");
//		if (dirName == null) {
//			dirName = "image";
//		}
//		if(!extMap.containsKey(dirName)){
//			response.getWriter().println(getError("目录名不正确。"));
//			return;
//		}
		String s = request.getSession().getServletContext().getRealPath("/");
		String[] arr = s.split("\\\\");
		StringBuffer savePath = new StringBuffer();
		for(int i=0; i<arr.length-1; i++){
			savePath.append(arr[i]+"\\");
		}
		savePath.append("upload/postImg/");
		String saveUrl = "upload/postImg/";
		//创建文件夹
		//savePath += dirName + "/";
		File saveDirFile = new File(savePath.toString());
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath.append(ymd + "/");
		saveUrl += ymd+"/";
		File dirFile = new File(savePath.toString());
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		byte[] fis = null;
		FileOutputStream fos = null;
		try {
			MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
			MultipartFile imgFile  =  multipartRequest.getFile("imgFile");
			String imgName = imgFile.getOriginalFilename();
			if(imgName.lastIndexOf(".")!=-1){
				String suffix = imgName.substring(imgName.lastIndexOf(".")+1);
	            String ip = request.getLocalAddr();
	            String port = request.getLocalPort()+"";
				if(suffix.equals("jpg") || suffix.equals("png") || suffix.equals("jpeg") 
						|| suffix.equals("gif") || suffix.equals("bmp")){
					String newName = new Date().getTime()+"."+suffix;
					File destFile = new File(savePath.toString(), newName);
					fis = imgFile.getBytes();
					fos = new FileOutputStream(destFile);
		            for (int i=0; i<fis.length; i++) {
		                fos.write(fis[i]);
		            }
					json.put("error", 0);
					json.put("url", "http://"+ip+":"+port+"/"+saveUrl+"/"+newName);
				}else{
					response.getWriter().println(getError("该类型不允许上传。"));
					return ;
				}
			}else{
				response.getWriter().println(getError("上传失败。"));
				return ;
			}
		} catch (Exception e) {
		    e.printStackTrace();
		    response.getWriter().println(getError("上传失败。"));
			response.getWriter().write(json.toString());
		} finally {
	        if (fos != null) {
	            try {
	                fos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		response.getWriter().write(json.toString());
		return ;
	}
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}
}
