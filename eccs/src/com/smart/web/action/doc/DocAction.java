package com.smart.web.action.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Doc;
import com.smart.model.Uptype;
import com.smart.service.DocService;
import com.smart.service.UptypeService;
import com.smart.util.FileRepository;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class DocAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DocService docService;

	@Autowired
	private UptypeService uptypeservice;

	private Doc doc;

	private String qtime = null;

	private String ztime = null;

	private Uptype uptype;

	private int id;

	private String fileFileName;

	private String remark;

	private String uptypeid;

	private File file;

	private String ids;

	private String mix;

	@Action("list")
	public String list() {
		doc = doc == null ? new Doc() : doc;
		List<Uptype> upty = uptypeservice.getList((int) this.getUser().getId());
		if (id == 0) {
			if (upty.size() != 0) {
				id = upty.get(0).getId();
			}
		}
		put("upty", upty);// 设置类型
		Page<Doc> pageBean = docService.getPage(getPageNo(), getPageSize(), mix,
				id, this.getUser().getId());
		put("pageBean", pageBean);// 根据类型查找文件
		uptype = uptypeservice.get(id);
		// uptype=uptypeservice.get(id);
		return "list";
	}

	@Action("new")
	public String _new() {
		doc = doc == null ? new Doc() : doc;
		List<Uptype> upty = uptypeservice.getList((int) this.getUser().getId());
		put("upty", upty);
		return "new";
	}

	@Action("newuptype")
	public String newuptype() {
		uptype = uptype == null ? new Uptype() : uptype;
		return "newuptype";
	}

	@Action(value = "saveuptype", results = {
			@Result(name = "newuptype", location = "newuptype", type = "redirectAction") })
	public String saveuptype() {
		if (id != 0) {// 更新
			Uptype olduptype = uptypeservice.get(id);
			Uptype pduan = uptypeservice.check(uptype.getName(),
					this.getUser().getId());
			if (!uptype.getName().trim().equals("")
					&& uptype.getName() != null) {
				if (pduan != null) {
					write("1");
				} else {
					olduptype.setName(uptype.getName());
					uptypeservice.update(olduptype);
				}
			} else {
				write("2");
			}

		} else {
			Uptype pduan = uptypeservice.check(uptype.getName(),
					this.getUser().getId());
			if (!uptype.getName().trim().equals("")
					&& uptype.getName() != null) {
				if (pduan != null) {
					write("1");
				} else {
					uptype.setUser(getUser());
					uptypeservice.save(uptype);
				}
			} else {
				write("2");
			}

		}
		return null;
	}

	@Action("uploadFile")
	public String uploadFile() throws IOException {
		Uptype uptype1 = uptypeservice.get(Integer.parseInt(uptypeid));

		String filedir = "doc/" + this.getUser().getId() + "/"
				+ uptype1.getName() + "/";
		FileRepository fileRepository = new FileRepository();
		String root = fileRepository.storeByExt(filedir, fileFileName, file);

		Doc doc1 = new Doc();
		Doc pduan = docService.check(fileFileName, this.getUser().getId(),
				uptype1.getId());
		if (pduan != null) {
			write("2");
		} else {
			doc1.setUptype(uptype1);
			doc1.setUser(this.getUser());
			doc1.setName(fileFileName);
			doc1.setRemark(remark);
			long size = file.length();// 217679
			size = size / 1024;
			doc1.setSize(String.valueOf(size));
			doc1.setAddress(root);
			docService.save(doc1);
			write("1");
		}
		return null;// ajax请求用write
	}

	@Action("edituptype")
	public String edituptype() {
		uptype = uptypeservice.get(id);
		return "newuptype";
	}

	@Action("edit")
	public String edit() {
		return "new";
	}

	@Action("show")
	public String show() {
		return "show";
	}

	@Action("delete")
	public String delete() {
		doc = docService.get(Integer.parseInt(ids));
		doc.setStatus(-1);
		file = new File(doc.getAddress());
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
		docService.update(doc);
		write("1");
		return null;
	}

	@Action("xiazai")
	public String xiazai() {
		Doc doc;
		doc = docService.get(Integer.parseInt(ids));
		String filePath = doc.getAddress();// 文件路径
		String fileName = doc.getName();// 文件名
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
		return null;
	}

	@Action("deleteuptype")
	public String deleteuptype() {
		uptype = uptypeservice.get(id);
		uptype.setStatus(-1);
		List<Doc> de = docService.find(id);
		for (Doc d : de) {
			file = new File(d.getAddress());
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				file.delete();
			}
			d.setStatus(-1);
			docService.update(d);
		}
		uptypeservice.update(uptype);
		write("1");
		return null;
	}

	public String upload() {
		return null;
	}

	public Doc getDoc() {
		return doc;
	}

	public void setDoc(Doc doc) {
		this.doc = doc;
	}

	public String getQtime() {
		return qtime;
	}

	public void setQtime(String qtime) {
		this.qtime = qtime;
	}

	public String getZtime() {
		return ztime;
	}

	public void setZtime(String ztime) {
		this.ztime = ztime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Uptype getUptype() {
		return uptype;
	}

	public void setUptype(Uptype uptype) {
		this.uptype = uptype;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUptypeid() {
		return uptypeid;
	}

	public void setUptypeid(String uptypeid) {
		this.uptypeid = uptypeid;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMix() {
		return mix;
	}

	public void setMix(String mix) {
		this.mix = mix;
	}

}
