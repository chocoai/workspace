package com.smart.web.action.t_reso;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.T_file;
import com.smart.model.T_hrcertificate;
import com.smart.model.T_hreducation;
import com.smart.model.T_hremployee;
import com.smart.model.t_hrentryrecord;
import com.smart.service.T_fileService;
import com.smart.service.T_hrcertificateService;
import com.smart.service.T_hreducationService;
import com.smart.service.T_hremployeeService;
import com.smart.service.t_hrentryrecordService;
import com.smart.util.DateUtils;
import com.smart.util.FileRepository;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_resoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private T_hremployeeService hremployeeService;
	@Autowired
	private T_hrcertificateService hrcertificateService;
	@Autowired
	private T_hreducationService hreducationService;
	@Autowired
	private t_hrentryrecordService hrentryrecordService;

	@Autowired
	private T_fileService t_fileService;
	private T_file t_file;
	private T_hremployee t_hremployee;
	private T_hrcertificate t_hrcertificate;
	private T_hreducation t_hreducation;
	private t_hrentryrecord t_hrentryrecord;
	private String college;// 毕业院校-保存
	private String education;// 学历-保存
	private String gdate;// 毕业时间-保存
	private String name;// 证件名称-保存
	private String no;// 证书编号-保存
	private String major;// 专业-保存
	private String grade;// 等级-保存
	private String issuing_unit;// 签发单位-保存
	private String idate;// 签发日期-保存
	private String validity_period;// 有效期-保存
	private String remark;// 备注-保存
	private String Stringid;// id字符串-删除
	private String shoolname;// 毕业院校-查询
	private String dimissionid;// id-离职
	private String becomeid;// id-转正
	private String characteristic;// 标识-转正and离职
	private String yeeid;// id-编辑
	private String fdate;// 转正日期
	private String qdate;// 离职日期

	private File file;
	private String fileFileName;
	private String file_id;

	// 学历编辑
	private String t_hreducationid;
	private String t_hreducationcollege;
	private String t_hreducationeducation;
	private String t_hreducationgdate;

	// 证书编辑
	private String t_hrcertificateid;
	private String t_hrcertificatename;
	private String t_hrcertificateno;
	private String t_hrcertificatemajor;
	private String t_hrcertificategrade;
	private String t_hrcertificateissuing_unit;
	private String t_hrcertificateidate;
	private String t_hrcertificatevalidity_period;
	private String t_hrcertificateremark;

	private String zsid;// 证书id-删除
	private String xlid;// 学历id-删除

	/**
	 * 列表and条件查询
	 */
	@Action("list")
	public String list() {
		t_hremployee = t_hremployee == null ? new T_hremployee() : t_hremployee;
		Page<T_hremployee> pageBean = hremployeeService.getPage(getPageNo(), 10,
				t_hremployee.getName(), getShoolname(), t_hremployee.getPhone(),
				t_hremployee.getSex());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			if (hreducationService.getList(pageBean.getList().get(i).getId())
					.size() == 0) {
				pageBean.getList().get(i).setT_hreducation(null);
			} else {
				pageBean.getList().get(i).setT_hreducation(hreducationService
						.getList(pageBean.getList().get(i).getId()).get(0));
			}
		}
		put("pageBean", pageBean);
		return "list";
	}

	/**
	 * 报表report
	 */
	@Action("report")
	public String report() {
		t_hremployee = t_hremployee == null ? new T_hremployee() : t_hremployee;
		Page<T_hremployee> pageBean = hremployeeService.getPage(getPageNo(), 10,
				t_hremployee.getName(), getShoolname(), t_hremployee.getPhone(),
				t_hremployee.getSex());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			pageBean.getList().get(i).setHrcelist(hrcertificateService
					.getList(pageBean.getList().get(i).getId()));
			pageBean.getList().get(i).setHredlist(hreducationService
					.getLists(pageBean.getList().get(i).getId()));
		}
		put("pageBean", pageBean);
		return "report";
	}

	/**
	 * 打印报表
	 */
	@Action("reportPrint")
	public String reportPrint() {
		t_hremployee = t_hremployee == null ? new T_hremployee() : t_hremployee;
		Page<T_hremployee> pageBean = hremployeeService.getPage(getPageNo(), 10,
				t_hremployee.getName(), getShoolname(), t_hremployee.getPhone(),
				t_hremployee.getSex());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			pageBean.getList().get(i).setHrcelist(hrcertificateService
					.getList(pageBean.getList().get(i).getId()));
			pageBean.getList().get(i).setHredlist(hreducationService
					.getLists(pageBean.getList().get(i).getId()));
		}
		put("pageBean", pageBean);
		return "reportPrint";
	}

	@Action("new")
	public String news() {
		if (t_hremployee != null && t_hremployee.getId() != null) {
			t_hremployee = hremployeeService.get(t_hremployee.getId());
			put("t_hremployee", t_hremployee);
			t_hrentryrecord = hrentryrecordService.getList(t_hremployee.getId())
					.get(0);
			put("t_hrentryrecord", t_hrentryrecord);
			List<T_hrcertificate> listcate = hrcertificateService
					.getList(t_hremployee.getId());
			put("listcate", listcate);
			List<T_hreducation> listtion = hreducationService
					.getLists(t_hremployee.getId());
			put("listtion", listtion);
			List<T_file> list = t_fileService.getList(t_hremployee.getId(), 3);
			put("list", list);
		}
		return "new";
	}

	/**
	 * 修改个人资料
	 * 
	 * @return
	 */
	@Action("revise")
	public String revise() {
		T_hremployee t_hremployee = hremployeeService
				.getSysCode(getUser().getId());
		if (t_hremployee != null) {
			t_hremployee = hremployeeService.get(t_hremployee.getId());
			put("t_hremployee", t_hremployee);
			t_hrentryrecord = hrentryrecordService.getList(t_hremployee.getId())
					.get(0);
			put("t_hrentryrecord", t_hrentryrecord);
			List<T_hrcertificate> listcate = hrcertificateService
					.getList(t_hremployee.getId());
			put("listcate", listcate);
			List<T_hreducation> listtion = hreducationService
					.getLists(t_hremployee.getId());
			put("listtion", listtion);
			List<T_file> list = t_fileService.getList(t_hremployee.getId(), 3);
			put("list", list);
		}
		return "personal";
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction"),
			@Result(name = "revise", location = "revise", type = "redirectAction") })
	public String save() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String rctime = format.format(date);
		if (t_hremployee.getId() == null) {
			t_hremployee.setUser_id(this.getUser());
			t_hremployee.setRtime(rctime);
			t_hremployee.setState(0);
			hremployeeService.save(t_hremployee);
		} else {
			T_hremployee px = hremployeeService.get(t_hremployee.getId());
			px.setName(t_hremployee.getName());
			px.setDept_id(t_hremployee.getDept_id());
			px.setSex(t_hremployee.getSex());
			px.setBirth(t_hremployee.getBirth());
			px.setPhone(t_hremployee.getPhone());
			px.setMajor(t_hremployee.getMajor());
			px.setEmg_contact(t_hremployee.getEmg_contact());
			px.setEmail(t_hremployee.getEmail());
			px.setId_card(t_hremployee.getId_card());
			px.setSys_account(t_hremployee.getSys_account());
			px.setSort_no(t_hremployee.getSort_no());
			px.setQq(t_hremployee.getQq());
			px.setWe_chat(t_hremployee.getWe_chat());
			px.setMsn(t_hremployee.getMsn());
			px.setOther(t_hremployee.getOther());
			px.setRemark(t_hremployee.getRemark());
			px.setUser_id(this.getUser());
			px.setRtime(rctime);
			hremployeeService.update(px);
		}
		if (t_hrentryrecord.getId() == null) {
			t_hrentryrecord.setT_hremployee(t_hremployee);
			hrentryrecordService.save(t_hrentryrecord);
		} else {
			t_hrentryrecord py = hrentryrecordService
					.get(t_hrentryrecord.getId());
			py.setEdate(t_hrentryrecord.getEdate());
			py.setPost(t_hrentryrecord.getPost());
			py.setField(t_hrentryrecord.getField());
			py.setLabor_date(t_hrentryrecord.getLabor_date());
			py.setSs_base(t_hrentryrecord.getSs_base());
			py.setSs_date(t_hrentryrecord.getAf_date());
			py.setAf_base(t_hrentryrecord.getAf_base());
			py.setAf_date(t_hrentryrecord.getAf_date());
			py.setArchives(t_hrentryrecord.getArchives());
			/*
			 * 由于保存的页面没有这两个属性，所以其值为空，若在此进行设置， 那么之前已经有了的数据会被之后的修改而覆盖为null,
			 * 这两个属性的值是在list页面中传进来的，所以解决办法有两种： 1.此处的设置值可以不需要
			 * 2.在页面中加上这两个属性，并设置为只读，以确保该值无法被修改
			 */
			// py.setFdate(t_hrentryrecord.getFdate());
			// py.setQdate(t_hrentryrecord.getQdate());
			py.setT_hremployee(t_hremployee);
			hrentryrecordService.update(py);
		}
		String[] ids = {};
		String[] collegelist = {};
		String[] educationlist = {};
		String[] gdatelist = {};
		/*-----新增学历----*/
		if (college != null) {
			collegelist = college.split(",");
			educationlist = education.split(",");
			gdatelist = gdate.split(",");
			for (int i = 0; i < collegelist.length; i++) {
				T_hreducation li = new T_hreducation();
				li.setCollege(collegelist[i].trim());
				li.setEducation(educationlist[i].trim());
				try {
					li.setGdate(format.parse(gdatelist[i].trim()));
				} catch (ParseException e) {

				}
				li.setT_hremployee(t_hremployee);
				hreducationService.save(li);
			}
		}
		String[] idz = {};
		String[] namelist = {};
		String[] nolist = {};
		String[] majorlist = {};
		String[] gradelist = {};
		String[] issuing_unitlist = {};
		String[] idatelist = {};
		String[] validity_periodlist = {};
		String[] remarklist = {};
		/*-----新增证书----*/
		if (name != null) {
			namelist = name.split(",");
			nolist = no.split(",");
			majorlist = major.split(",");
			gradelist = grade.split(",");
			issuing_unitlist = issuing_unit.split(",");
			idatelist = idate.split(",");
			validity_periodlist = validity_period.split(",");
			remarklist = remark.split(",");
			for (int i = 0; i < namelist.length; i++) {
				T_hrcertificate li = new T_hrcertificate();
				li.setName(namelist[i].trim());
				li.setNo(nolist[i].trim());
				li.setMajor(majorlist[i].trim());
				li.setGrade(gradelist[i].trim());
				li.setIssuing_unit(issuing_unitlist[i].trim());
				try {
					li.setIdate(format.parse(idatelist[i].trim()));
				} catch (ParseException e) {
					//
				}
				li.setValidity_period(validity_periodlist[i].trim());
				li.setRemark(remarklist[i].trim());
				li.setT_hremployee(t_hremployee);
				hrcertificateService.save(li);
			}
		}
		// 编辑学历
		if (!StringUtil.isBlank(t_hreducationid)) {
			ids = t_hreducationid.split(",");
			collegelist = t_hreducationcollege.split(",");
			educationlist = t_hreducationeducation.split(",");
			gdatelist = t_hreducationgdate.split(",");
			for (int i = 0; i < ids.length; i++) {
				T_hreducation ld = new T_hreducation();
				ld.setId(Integer.parseInt(ids[i].trim()));
				ld.setCollege(collegelist[i].trim());
				ld.setEducation(educationlist[i].trim());
				ld.setT_hremployee(t_hremployee);
				try {
					ld.setGdate(format.parse(gdatelist[i].trim()));
				} catch (ParseException e) {

				}
				hreducationService.update(ld);
			}
		}
		// 编辑证书
		if (!StringUtil.isBlank(t_hrcertificateid)) {
			idz = t_hrcertificateid.split(",");
			namelist = t_hrcertificatename.split(",");
			nolist = t_hrcertificateno.split(",");
			majorlist = t_hrcertificatemajor.split(",");
			gradelist = t_hrcertificategrade.split(",");
			issuing_unitlist = t_hrcertificateissuing_unit.split(",");
			idatelist = t_hrcertificateidate.split(",");
			validity_periodlist = t_hrcertificatevalidity_period.split(",");
			remarklist = t_hrcertificateremark.split(",");
			for (int i = 0; i < idz.length; i++) {
				T_hrcertificate li = new T_hrcertificate();
				li.setId(Integer.parseInt(idz[i].trim()));
				li.setName(namelist[i].trim());
				li.setNo(nolist[i].trim());
				li.setMajor(majorlist[i].trim());
				li.setGrade(gradelist[i].trim());
				li.setIssuing_unit(issuing_unitlist[i].trim());
				li.setT_hremployee(t_hremployee);
				try {
					li.setIdate(format.parse(idatelist[i].trim()));
				} catch (ParseException e) {

				}
				li.setValidity_period(validity_periodlist[i].trim());
				li.setRemark(remarklist[i].trim());
				hrcertificateService.update(li);
			}
		}
		if (file_id != null) {
			String[] fileid = file_id.split(",");
			for (int i = 0; i < fileid.length; i++) {
				T_file px = t_fileService
						.get(Integer.parseInt(fileid[i].trim()));
				px.setType_id(t_hremployee.getId());
				t_fileService.update(px);
			}
		}
		if (!StringUtil.isBlank(get("type")) && get("type").equals("revise")) {
			return "revise";
		} else {
			return "list";
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action("delete")
	public String delete() {
		String[] a = getStringid().split(",");
		for (int i = 0; i < a.length; i++) {
			t_hremployee = hremployeeService.get(Integer.parseInt(a[i]));
			t_hremployee.setStatus(-1);
			hremployeeService.update(t_hremployee);
		}
		write("1"); // ajax请求用write返回数据
		return null;
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
	 * 查看
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		// 信息登记
		t_hremployee = hremployeeService.get(t_hremployee.getId());
		put("t_hremployee", t_hremployee);
		List<t_hrentryrecord> list = hrentryrecordService
				.getList(t_hremployee.getId());
		for (int i = 0; i < list.size(); i++) {
			t_hrentryrecord = list.get(i);
		}
		put("t_hrentryrecord", t_hrentryrecord);
		// 学历
		List<T_hreducation> t_hreducationlist = hreducationService
				.getLists(t_hremployee.getId());
		put("t_hreducationlist", t_hreducationlist);
		// 证书
		List<T_hrcertificate> t_hrcertificatelist = hrcertificateService
				.getList(t_hremployee.getId());
		put("t_hrcertificatelist", t_hrcertificatelist);
		List<T_file> lists = t_fileService.getList(t_hremployee.getId(), 3);
		put("lists", lists);
		return "show";
	}

	/**
	 * 删除学历and证书
	 * 
	 * @return
	 */
	@Action("deletess")
	public String deletess() {
		if (getZsid() != null) {
			String[] zs = getZsid().split(",");
			for (int i = 0; i < zs.length; i++) {
				t_hrcertificate = hrcertificateService
						.get(Integer.parseInt(zs[i]));
				t_hrcertificate.setStatus(-1);
				hrcertificateService.update(t_hrcertificate);
			}
		}
		if (getXlid() != null) {
			String[] xl = getXlid().split(",");
			for (int i = 0; i < xl.length; i++) {
				t_hreducation = hreducationService.get(Integer.parseInt(xl[i]));
				t_hreducation.setStatus(-1);
				hreducationService.update(t_hreducation);
			}
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 离职and转正
	 * 
	 * @return
	 */
	@Action("dimission")
	public String dimission() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String s = format.format(date);
		if (Integer.parseInt(characteristic) == 1) {// 转正
			t_hremployee = hremployeeService
					.get(Integer.parseInt(getBecomeid()));
			t_hremployee.setState(1);
			hremployeeService.update(t_hremployee);
			t_hrentryrecord = hrentryrecordService
					.getList(Integer.parseInt(getBecomeid())).get(0);
			t_hrentryrecord.setFuser(this.getUser());
			t_hrentryrecord.setFdate(getFdate());
			t_hrentryrecord.setFrtime(s);
			hrentryrecordService.update(t_hrentryrecord);
		}
		if (Integer.parseInt(characteristic) == 2) {// 离职
			t_hremployee = hremployeeService
					.get(Integer.parseInt(getDimissionid()));
			t_hremployee.setState(2);
			hremployeeService.update(t_hremployee);
			t_hrentryrecord = hrentryrecordService
					.getList(Integer.parseInt(getDimissionid())).get(0);
			t_hrentryrecord.setQrtime(s);
			t_hrentryrecord.setQuser(this.getUser());
			t_hrentryrecord.setQdate(getQdate());
			hrentryrecordService.update(t_hrentryrecord);
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

		String filedir = "t_reso/" + this.getUser().getId() + "/";
		FileRepository fileRepository = new FileRepository();
		String root = fileRepository.storeByExt(filedir, fileFileName, file);

		T_file fis = new T_file();
		fis.setSize(file.getTotalSpace());
		fis.setName(fileFileName);
		fis.setPath(root);
		fis.setUser(this.getUser());
		fis.setRtime(DateUtils.getCurrentTime());
		fis.setRemarks(t_file.getRemarks());
		fis.setType(3);// 信息登记
		t_fileService.save(fis);
		JSONObject json = new JSONObject();
		json.put("id", fis.getId());
		json.put("name", fis.getName());
		writeJson(json.toString());
		return null;
	}

	@Action("excel")
	public String excel() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		t_hremployee = t_hremployee == null ? new T_hremployee() : t_hremployee;
		Page<T_hremployee> pageBean = hremployeeService.getPage(getPageNo(),
				getPageSize(), t_hremployee.getName(), getShoolname(),
				t_hremployee.getPhone(), t_hremployee.getSex());
		int indexs = 0;
		List<T_hremployee> list = pageBean.getList();
		for (int i = 0; i < list.size(); i++) {
			if (hrcertificateService.getList(list.get(i).getId()) != null
					&& hrcertificateService.getList(list.get(i).getId())
							.size() != 0) {
				list.get(i).setHrcelist(
						hrcertificateService.getList(list.get(i).getId()));
				indexs += hrcertificateService.getList(list.get(i).getId())
						.size();
			} else {
				list.get(i).setHrcelist(null);
				indexs++;
			}
			if (hreducationService.getList(list.get(i).getId()).size() == 0) {
				list.get(i).setT_hreducation(null);
			} else {
				list.get(i).setT_hreducation(
						hreducationService.getList(list.get(i).getId()).get(0));// 最高学历
			}

		}
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("人员信息一览-列表");

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		row.setHeight((short) 600);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setWrapText(true);
		sheet.setColumnWidth(0, 100 * 20);
		sheet.setColumnWidth(1, 100 * 40);
		sheet.setColumnWidth(2, 100 * 40);
		sheet.setColumnWidth(3, 100 * 40);
		sheet.setColumnWidth(4, 100 * 40);
		sheet.setColumnWidth(5, 100 * 40);
		sheet.setColumnWidth(6, 100 * 40);
		sheet.setColumnWidth(7, 100 * 40);
		sheet.setColumnWidth(8, 100 * 40);
		sheet.setColumnWidth(9, 100 * 40);
		sheet.setColumnWidth(10, 100 * 40);
		sheet.setColumnWidth(11, 100 * 40);
		sheet.setColumnWidth(12, 100 * 40);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("性别");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("出生年月");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("专业");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("学历");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("毕业院校");
		cell.setCellStyle(style);

		cell = row.createCell(7);
		cell.setCellValue("联系电话");
		cell.setCellStyle(style);

		cell = row.createCell(8);
		cell.setCellValue("分配部门");
		cell.setCellStyle(style);

		cell = row.createCell(9);
		cell.setCellValue("人员信息备注");
		cell.setCellStyle(style);

		cell = row.createCell(10);
		cell.setCellValue("证书名称");
		cell.setCellStyle(style);

		cell = row.createCell(11);
		cell.setCellValue("证书编号");
		cell.setCellStyle(style);

		cell = row.createCell(12);
		cell.setCellValue("等级");
		cell.setCellStyle(style);

		cell = row.createCell(13);
		cell.setCellValue("专业");
		cell.setCellStyle(style);

		cell = row.createCell(14);
		cell.setCellValue("签发单位");
		cell.setCellStyle(style);

		cell = row.createCell(15);
		cell.setCellValue("签发日期");
		cell.setCellStyle(style);

		cell = row.createCell(16);
		cell.setCellValue("证书备注");
		cell.setCellStyle(style);

		T_hremployee tApply = new T_hremployee();

		for (int i = 1; i <= indexs; i++) {
			row = sheet.createRow((short) (i));
			for (int j = 0; j < 17; j++) {
				HSSFCell tmp_cell = row.createCell(j);
				tmp_cell.setCellStyle(style);
			}
		}

		int end = 0;
		for (int i = 0; i < list.size(); i++) {
			tApply = list.get(i);
			List<T_hrcertificate> contactList = tApply.getHrcelist();

			if (contactList != null && contactList.size() > 0) {

				int start = end + 1;

				if (i == 0)
					end = i + 1 + list.get(i).getHrcelist().size() - 1;
				else
					end = start + list.get(i).getHrcelist().size() - 1;
				// 合并公共部分单元格
				for (int j = 0; j < 11; j++) {

					CellRangeAddress cra = new CellRangeAddress(start, end, j,
							j);
					sheet.addMergedRegion(cra);
					cell = sheet.getRow(start).getCell(j);
					cell.setCellStyle(style);
					if (j == 0) {
						cell.setCellValue(i + 1);
					} else if (j == 1) {
						cell.setCellValue(tApply.getName());
					} else if (j == 2) {
						if (tApply.getSex() == 1) {
							cell.setCellValue("男");
						}
						if (tApply.getSex() == 2) {
							cell.setCellValue("女");
						}
					} else if (j == 3) {
						cell.setCellValue(format.format(tApply.getBirth()));
					} else if (j == 4) {
						cell.setCellValue(tApply.getMajor());
					} else if (j == 5) {
						if (tApply.getT_hreducation() != null)
							cell.setCellValue(
									tApply.getT_hreducation().getEducation());
					} else if (j == 6) {
						if (tApply.getT_hreducation() != null)
							cell.setCellValue(
									tApply.getT_hreducation().getCollege());
					} else if (j == 7) {
						cell.setCellValue(tApply.getPhone());
					} else if (j == 8) {
						if (tApply.getDept_id() != null)
							cell.setCellValue(tApply.getDept_id().getName());
					} else if (j == 9) {
						cell.setCellValue(tApply.getRemark());
					}
				} // end for 11

				for (int k = 0; k < contactList.size(); k++) {
					for (int j = 10; j < 17; j++) {
						cell = sheet.getRow(start + k).getCell(j);
						cell.setCellStyle(style);
						if (j == 10) {
							if (tApply.getHrcelist() != null) {
								cell.setCellValue(
										tApply.getHrcelist().get(k).getName());
							}
						} else if (j == 11) {
							if (tApply.getHrcelist() != null) {
								cell.setCellValue(
										tApply.getHrcelist().get(k).getNo());
							}
						} else if (j == 12) {
							if (tApply.getHrcelist() != null) {
								cell.setCellValue(
										tApply.getHrcelist().get(k).getGrade());
							}
						} else if (j == 13) {
							if (tApply.getHrcelist() != null) {
								cell.setCellValue(
										tApply.getHrcelist().get(k).getMajor());
							}
						} else if (j == 14) {
							if (tApply.getHrcelist() != null) {
								cell.setCellValue(tApply.getHrcelist().get(k)
										.getIssuing_unit());

							}
						} else if (j == 15) {
							if (tApply.getHrcelist() != null) {
								cell.setCellValue(format.format(tApply
										.getHrcelist().get(k).getIdate()));

							}
						} else if (j == 16) {
							if (tApply.getHrcelist() != null) {
								cell.setCellValue(tApply.getHrcelist().get(k)
										.getRemark());

							}
						}
					} // end for -k
				}
			} else {
				// 不包含学历证书的

				int start = end + 1;

				if (i == 0 && list != null && list.get(i) != null
						&& list.get(i).getHrcelist() != null) {
					end = i + 1 + list.get(i).getHrcelist().size() - 1;
				} else {
					end = start;
				}
				// 合并公共部分单元格
				for (int j = 0; j < 11; j++) {

					cell = sheet.getRow(start).getCell(j);
					cell.setCellStyle(style);
					if (j == 0) {
						cell.setCellValue(i + 1);
					} else if (j == 1) {
						cell.setCellValue(tApply.getName());
					} else if (j == 2) {
						if (tApply.getSex() == 1) {
							cell.setCellValue("男");
						}
						if (tApply.getSex() == 2) {
							cell.setCellValue("女");
						}
					} else if (j == 3) {
						if (tApply.getBirth() != null)
							cell.setCellValue(format.format(tApply.getBirth()));
					} else if (j == 4) {
						cell.setCellValue(tApply.getMajor());
					} else if (j == 5) {
						if (tApply.getT_hreducation() != null)
							cell.setCellValue(
									tApply.getT_hreducation().getEducation());
					} else if (j == 6) {
						if (tApply.getT_hreducation() != null)
							cell.setCellValue(
									tApply.getT_hreducation().getCollege());
					} else if (j == 7) {
						cell.setCellValue(tApply.getPhone());
					} else if (j == 8) {
						if (tApply.getDept_id() != null)
							cell.setCellValue(tApply.getDept_id().getName());
					} else if (j == 9) {
						cell.setCellValue(tApply.getRemark());
					}
				} // end for 11

				for (int j = 10; j < 17; j++) {
					cell = sheet.getRow(start).getCell(j);
					cell.setCellStyle(style);
					if (j == 10) {

						cell.setCellValue("");

					} else if (j == 11) {
						cell.setCellValue("");
					} else if (j == 12) {
						cell.setCellValue("");
					} else if (j == 13) {
						cell.setCellValue("");
					} else if (j == 14) {
						cell.setCellValue("");
					} else if (j == 15) {

					} else if (j == 16) {
						cell.setCellValue("");
					}
				} // end for -k

			} // end-if contactlist.size=0

		}
		OutputStream fout = null;
		try {
			HttpServletResponse response = getHttpServletResponse();
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(
							("人员信息一览.xls").getBytes("GB2312"), "iso8859-1"));
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			fout = response.getOutputStream();
			wb.write(fout);
			wb.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/*--------------------get和set----------------------------*/
	public T_hremployee getT_hremployee() {
		return t_hremployee;
	}

	public void setT_hremployee(T_hremployee t_hremployee) {
		this.t_hremployee = t_hremployee;
	}

	public T_hrcertificate getT_hrcertificate() {
		return t_hrcertificate;
	}

	public void setT_hrcertificate(T_hrcertificate t_hrcertificate) {
		this.t_hrcertificate = t_hrcertificate;
	}

	public T_hreducation getT_hreducation() {
		return t_hreducation;
	}

	public void setT_hreducation(T_hreducation t_hreducation) {
		this.t_hreducation = t_hreducation;
	}

	public t_hrentryrecord getT_hrentryrecord() {
		return t_hrentryrecord;
	}

	public void setT_hrentryrecord(t_hrentryrecord t_hrentryrecord) {
		this.t_hrentryrecord = t_hrentryrecord;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getGdate() {
		return gdate;
	}

	public void setGdate(String gdate) {
		this.gdate = gdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getIssuing_unit() {
		return issuing_unit;
	}

	public void setIssuing_unit(String issuing_unit) {
		this.issuing_unit = issuing_unit;
	}

	public String getIdate() {
		return idate;
	}

	public void setIdate(String idate) {
		this.idate = idate;
	}

	public String getValidity_period() {
		return validity_period;
	}

	public void setValidity_period(String validity_period) {
		this.validity_period = validity_period;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStringid() {
		return Stringid;
	}

	public void setStringid(String stringid) {
		Stringid = stringid;
	}

	public String getShoolname() {
		return shoolname;
	}

	public void setShoolname(String shoolname) {
		this.shoolname = shoolname;
	}

	public String getDimissionid() {
		return dimissionid;
	}

	public void setDimissionid(String dimissionid) {
		this.dimissionid = dimissionid;
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getBecomeid() {
		return becomeid;
	}

	public void setBecomeid(String becomeid) {
		this.becomeid = becomeid;
	}

	public String getYeeid() {
		return yeeid;
	}

	public void setYeeid(String yeeid) {
		this.yeeid = yeeid;
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

	public String getT_hreducationid() {
		return t_hreducationid;
	}

	public void setT_hreducationid(String t_hreducationid) {
		this.t_hreducationid = t_hreducationid;
	}

	public String getT_hreducationcollege() {
		return t_hreducationcollege;
	}

	public void setT_hreducationcollege(String t_hreducationcollege) {
		this.t_hreducationcollege = t_hreducationcollege;
	}

	public String getT_hreducationeducation() {
		return t_hreducationeducation;
	}

	public void setT_hreducationeducation(String t_hreducationeducation) {
		this.t_hreducationeducation = t_hreducationeducation;
	}

	public String getT_hreducationgdate() {
		return t_hreducationgdate;
	}

	public void setT_hreducationgdate(String t_hreducationgdate) {
		this.t_hreducationgdate = t_hreducationgdate;
	}

	public String getT_hrcertificateid() {
		return t_hrcertificateid;
	}

	public void setT_hrcertificateid(String t_hrcertificateid) {
		this.t_hrcertificateid = t_hrcertificateid;
	}

	public String getT_hrcertificatename() {
		return t_hrcertificatename;
	}

	public void setT_hrcertificatename(String t_hrcertificatename) {
		this.t_hrcertificatename = t_hrcertificatename;
	}

	public String getT_hrcertificateno() {
		return t_hrcertificateno;
	}

	public void setT_hrcertificateno(String t_hrcertificateno) {
		this.t_hrcertificateno = t_hrcertificateno;
	}

	public String getT_hrcertificatemajor() {
		return t_hrcertificatemajor;
	}

	public void setT_hrcertificatemajor(String t_hrcertificatemajor) {
		this.t_hrcertificatemajor = t_hrcertificatemajor;
	}

	public String getT_hrcertificategrade() {
		return t_hrcertificategrade;
	}

	public void setT_hrcertificategrade(String t_hrcertificategrade) {
		this.t_hrcertificategrade = t_hrcertificategrade;
	}

	public String getT_hrcertificateissuing_unit() {
		return t_hrcertificateissuing_unit;
	}

	public void setT_hrcertificateissuing_unit(
			String t_hrcertificateissuing_unit) {
		this.t_hrcertificateissuing_unit = t_hrcertificateissuing_unit;
	}

	public String getT_hrcertificateidate() {
		return t_hrcertificateidate;
	}

	public void setT_hrcertificateidate(String t_hrcertificateidate) {
		this.t_hrcertificateidate = t_hrcertificateidate;
	}

	public String getT_hrcertificatevalidity_period() {
		return t_hrcertificatevalidity_period;
	}

	public void setT_hrcertificatevalidity_period(
			String t_hrcertificatevalidity_period) {
		this.t_hrcertificatevalidity_period = t_hrcertificatevalidity_period;
	}

	public String getT_hrcertificateremark() {
		return t_hrcertificateremark;
	}

	public void setT_hrcertificateremark(String t_hrcertificateremark) {
		this.t_hrcertificateremark = t_hrcertificateremark;
	}

	public String getZsid() {
		return zsid;
	}

	public void setZsid(String zsid) {
		this.zsid = zsid;
	}

	public String getXlid() {
		return xlid;
	}

	public void setXlid(String xlid) {
		this.xlid = xlid;
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

	public String getFdate() {
		return fdate;
	}

	public void setFdate(String fdate) {
		this.fdate = fdate;
	}

	public String getQdate() {
		return qdate;
	}

	public void setQdate(String qdate) {
		this.qdate = qdate;
	}

}
