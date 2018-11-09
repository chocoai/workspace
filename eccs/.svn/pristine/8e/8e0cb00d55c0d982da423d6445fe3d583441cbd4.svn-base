package com.smart.web.action.t_liregistration;

import java.io.File;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.T_file;
import com.smart.model.T_liregistration;
import com.smart.service.T_fileService;
import com.smart.service.T_liregistrationService;
import com.smart.util.DateUtils;
import com.smart.util.FileRepository;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_liregistrationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private T_liregistrationService liregistrationService;
	@Autowired
	private T_fileService t_fileService;
	private T_file t_file;
	private T_liregistration liregistration;
	private String Stringid;// 证照id字符串
	private String deptid;// 保管部门id
	private String uname;
	private Date date;

	private File file;
	private String fileFileName;
	private String file_id;

	/**
	 * 列表and条件查询
	 */
	@Action("list")
	public String list() {
		liregistration = liregistration == null ? new T_liregistration()
				: liregistration;
		Page<T_liregistration> pageBean = liregistrationService.getPage(pageNo,
				10, liregistration.getLicname(), liregistration.getLicnumber(),
				liregistration.getLicstatus());
		put("pageBean", pageBean);
		return "list";
	}

	/**
	 * 新建
	 * 
	 * @return
	 */
	@Action("new")
	public String news() {
		if (liregistration != null && liregistration.getId() != null) {
			liregistration = liregistrationService.get(liregistration.getId());
			put("liregistration", liregistration);
			List<T_file> list = t_fileService.getList(liregistration.getId(),
					4);
			put("list", list);
			// 证照编号判断
			List<T_liregistration> liregistrationno = liregistrationService
					.getAlls();
			String strno = "";
			for (int i = 0; i < liregistrationno.size(); i++) {
				if (liregistration.getLicnumber() != liregistrationno.get(i)
						.getLicnumber()) {
					if (strno != "") {
						strno = strno + ",,,"
								+ liregistrationno.get(i).getLicnumber();
					} else {
						strno = liregistrationno.get(i).getLicnumber();
					}
				}
			}
			put("invos", strno);
		} else {
			List<T_liregistration> liregistrationno = liregistrationService
					.getAlls();
			String strno = "";
			for (int i = 0; i < liregistrationno.size(); i++) {
				if (strno != "") {
					strno = strno + ",,,"
							+ liregistrationno.get(i).getLicnumber();
				} else {
					strno = liregistrationno.get(i).getLicnumber();
				}
			}
			put("invos", strno);
		}
		return "new";
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String saves() {
		if (liregistration != null && liregistration.getId() != null) {
			T_liregistration px = liregistrationService
					.get(liregistration.getId());
			px.setLicname(liregistration.getLicname());
			px.setLicnumber(liregistration.getLicnumber());
			px.setLicstatus(liregistration.getLicstatus());
			px.setPredate(liregistration.getPredate());
			px.setPrebranch(liregistration.getPrebranch());
			px.setAnndate(liregistration.getAnndate());
			px.setAnnbranch(liregistration.getAnnbranch());
			px.setEffdate(liregistration.getEffdate());
			px.setFilsystem(liregistration.getFilsystem());
			px.setDept_id(liregistration.getDept_id());
			px.setKuser_id(liregistration.getKuser_id());
			px.setCharacteristic(liregistration.getCharacteristic());
			px.setRecord(liregistration.getRecord());
			px.setMaterial(liregistration.getMaterial());
			px.setExplain(liregistration.getExplain());
			liregistrationService.update(px);
		} else {
			liregistration.setUser_id(this.getUser());
			liregistration.setRtime(new Date());
			liregistrationService.save(liregistration);
		}
		if (file_id != null) {
			String[] fileid = file_id.split(",");
			for (int i = 0; i < fileid.length; i++) {
				T_file px = t_fileService
						.get(Integer.parseInt(fileid[i].trim()));
				px.setType_id(liregistration.getId());
				t_fileService.update(px);
			}
		}
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		String[] a = getStringid().split(",");
		for (int i = 0; i < a.length; i++) {
			liregistration = liregistrationService.get(Integer.parseInt(a[i]));
			liregistration.setStatus(-1);

			liregistrationService.update(liregistration);
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		liregistration = liregistrationService.get(liregistration.getId());
		put("liregistration", liregistration);
		List<T_file> lists = t_fileService.getList(liregistration.getId(), 4);
		put("lists", lists);
		return "show";
	}

	/**
	 * 选择人员
	 * 
	 * @return
	 */
	@Action("selectUser")
	public String selectUser() {

		return "selectUser";
	}

	/**
	 * 过期提醒
	 * 
	 * @return
	 */
	@Action("zzlist")
	public String zzlist() {
		liregistration = liregistration == null ? new T_liregistration()
				: liregistration;
		Page<T_liregistration> pageBean = liregistrationService.getPages(pageNo,
				10, liregistration.getLicname(), liregistration.getLicnumber(),
				liregistration.getLicstatus());
		// date=new Date();
		put("pageBean", pageBean);
		return "zzlist";
	}

	/**
	 * 删除附件
	 * 
	 * @param id
	 * @return
	 */
	@Action("deletefile")
	public String deletefile() {
		if (Stringid != null) {
			String[] str = Stringid.split(",");
			for (int i = 0; i < str.length; i++) {
				t_file = t_fileService.get(Integer.parseInt(str[i].trim()));
				file = new File(t_file.getPath());
				file.delete();
				t_file.setStatus(-1);
				t_fileService.update(t_file);
			}
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 文件上传保存
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("uploadFile")
	public String uploadFile() throws Exception {
		String filedir = "t_liregistration/" + this.getUser().getId() + "/";
		FileRepository fileRepository = new FileRepository();
		String root = fileRepository.storeByExt(filedir, fileFileName, file);

		T_file fis = new T_file();
		fis.setSize(file.getTotalSpace());
		fis.setName(fileFileName);
		fis.setPath(root);
		fis.setUser(this.getUser());
		fis.setRtime(DateUtils.getCurrentTime());
		fis.setRemarks(t_file.getRemarks());
		fis.setType(4);// 证照登记
		t_fileService.save(fis);

		JSONObject json = new JSONObject();
		json.put("id", fis.getId());
		json.put("name", fis.getName());
		writeJson(json.toString());
		return null;
	}

	/*--------------------get和set----------------------------*/
	public T_liregistration getLiregistration() {
		return liregistration;
	}

	public void setLiregistration(T_liregistration liregistration) {
		this.liregistration = liregistration;
	}

	public String getStringid() {
		return Stringid;
	}

	public void setStringid(String stringid) {
		Stringid = stringid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public T_file getT_file() {
		return t_file;
	}

	public void setT_file(T_file t_file) {
		this.t_file = t_file;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

}
