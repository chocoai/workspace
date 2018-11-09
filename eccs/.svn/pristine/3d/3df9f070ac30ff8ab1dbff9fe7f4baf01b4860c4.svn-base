package com.smart.web.action.t_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.T_file;
import com.smart.service.T_fileService;
import com.smart.web.action.BaseAction;

/**
 * 
 * 文件上传
 * 
 */
@ParentPackage("control-user")
public class t_fileAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private T_fileService t_fileService;
	private T_file t_file;

	/*
	 * 下载附件
	 */
	@Action("xiazai")
	public String xiazai() {
		if (t_file != null && t_file.getId() != null) {
			t_file = t_fileService.get(t_file.getId());
			String filePath = t_file.getPath();// 文件路径
			String fileName = t_file.getName();// 文件名

			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				File file = new File(getAttachDir() + filePath);
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + fileName);
				String len = String.valueOf(file.length());
				response.setHeader("Content-Length", len);
				OutputStream os = response.getOutputStream();
				FileInputStream in = new FileInputStream(file);
				byte[] b = new byte[1024];
				int n;
				while ((n = in.read(b)) != -1) {
					os.write(b, 0, n);
				}
				in.close();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Action("filett_cult")
	public String filett_cult() { // 人力资源-培训管理

		return "filett_cult";
	}

	@Action("filet_lire")
	public String filet_lire() { // 证照管理-证照登记

		return "filet_lire";
	}

	@Action("filet_reso")
	public String filet_reso() { // 人力资源-信息登记

		return "filet_reso";
	}

	@Action("filet_cus")
	public String filet_cus() { // 客户管理

		return "filet_cus";
	}

	public T_file getT_file() {
		return t_file;
	}

	public void setT_file(T_file t_file) {
		this.t_file = t_file;
	}

}
