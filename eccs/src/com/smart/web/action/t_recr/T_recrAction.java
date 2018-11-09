package com.smart.web.action.t_recr;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Dept;

import com.smart.model.T_hropinion;
import com.smart.model.T_hrrecruitment;
import com.smart.model.T_hrrecruitmentitem;
import com.smart.model.User;
import com.smart.service.T_hropinionService;
import com.smart.service.T_hrrecruitmentService;
import com.smart.service.T_hrrecruitmentitemService;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_recrAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private T_hropinionService t_hropinionService;

	@Autowired
	private T_hrrecruitmentService t_hrrecruitmentService;

	@Autowired
	private T_hrrecruitmentitemService t_hrrecruitmentitemService;

	private T_hropinion t_hropinion;

	private T_hrrecruitment t_hrrecruitment;

	private T_hrrecruitmentitem t_hrrecruitmentitem;

	private String rtimec;// 开始时间-查询

	private String rtimej;// 结束时间-查询

	private String Stringid;// id-删除

	private String dept_id;// 需求部门-查询

	private String user_id;// 登记人-查询

	private String major;// 招聘专业

	private String post;// 岗位

	private String no;// 人数

	private String remark;// 备注

	// 编辑
	private String t_hrrecruitmentitemid;

	private String t_hrrecruitmentitemmajor;

	private String t_hrrecruitmentitempost;

	private String t_hrrecruitmentitemno;

	private String t_hrrecruitmentitemremark;

	/**
	 * 列表and条件查询
	 */
	@Action("list")
	public String list() {
		t_hrrecruitment = t_hrrecruitment == null ? new T_hrrecruitment()
				: t_hrrecruitment;
		// 处理部门所有人可以看到
		Page<T_hrrecruitment> pageBean = t_hrrecruitmentService.getPage(
				getPageNo(), 10, getDept_id(), getUser_id(), rtimec, rtimej,
				this.getMyDeptId(), this.getUser().getId());
		put("pageBean", pageBean);
		put("dept", this.getMyDept().getId());
		return "list";
	}

	/**
	 * 报表report
	 */
	@Action("report")
	public String report() {
		t_hrrecruitment = t_hrrecruitment == null ? new T_hrrecruitment()
				: t_hrrecruitment;
		Page<T_hrrecruitment> pageBean = t_hrrecruitmentService.getPage(pageNo,
				pageSize, getDept_id(), getUser_id(), rtimec, rtimej, null,
				null);
		put("pageBean", pageBean);
		return "report";
	}

	/**
	 * 新建
	 * 
	 * @return
	 */
	@Action("new")
	public String news() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (t_hrrecruitment != null && t_hrrecruitment.getId() != null) {
			t_hrrecruitment = t_hrrecruitmentService
					.get(t_hrrecruitment.getId());
			put("t_hrrecruitment", t_hrrecruitment);
			List<T_hrrecruitmentitem> list = t_hrrecruitmentitemService
					.getList(t_hrrecruitment.getId());
			put("list", list);
			t_hropinion = t_hrrecruitment.getT_hropinion();
			put("t_hropinion", t_hropinion);
		} else {
			String rctime = format.format(date);
			put("rctime", rctime);
			Dept dept = this.getMyDept();
			put("dept", dept);
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
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String rctime = format.format(date);
		// 新增
		if (t_hrrecruitment != null && t_hrrecruitment.getId() == null) {
			t_hrrecruitment.setUser_id(this.getUser());
			t_hrrecruitment.setRdate(rctime);
			t_hrrecruitment.setDept_id(this.getMyDept());
			t_hrrecruitmentService.save(t_hrrecruitment);

		}
		// 编辑
		if (t_hrrecruitment != null && t_hrrecruitment.getId() != null) {
			T_hrrecruitment px = t_hrrecruitmentService
					.get(t_hrrecruitment.getId());
			px.setDemand(t_hrrecruitment.getDemand());
			px.setRemark(t_hrrecruitment.getRemark());
			px.setHdept_id(t_hrrecruitment.getHdept_id());
			t_hrrecruitmentService.update(px);
		}
		// 新增
		String[] idss = {};
		String[] majors = {};
		String[] posts = {};
		String[] nos = {};
		String[] remarks = {};
		if (!StringUtil.isBlank(major)) {
			majors = major.split(",");
			posts = post.split(",");
			nos = no.split(",");
			remarks = remark.split(",");
			for (int i = 0; i < majors.length; i++) {
				t_hrrecruitmentitem = new T_hrrecruitmentitem();
				t_hrrecruitmentitem.setT_hrrecruitment(t_hrrecruitment);
				t_hrrecruitmentitem.setMajor(majors[i].trim());
				t_hrrecruitmentitem.setPost(posts[i].trim());
				t_hrrecruitmentitem.setNo(Integer.parseInt(nos[i].trim()));
				t_hrrecruitmentitem.setRemark(remarks[i].trim());
				t_hrrecruitmentitemService.save(t_hrrecruitmentitem);
			}
		}
		if (!StringUtil.isBlank(t_hrrecruitmentitemid)) {
			idss = t_hrrecruitmentitemid.split(",");
			majors = t_hrrecruitmentitemmajor.split(",");
			posts = t_hrrecruitmentitempost.split(",");
			nos = t_hrrecruitmentitemno.split(",");
			remarks = t_hrrecruitmentitemremark.split(",");
			for (int i = 0; i < idss.length; i++) {
				T_hrrecruitmentitem px = t_hrrecruitmentitemService
						.get(Integer.parseInt(idss[i].trim()));
				px.setMajor(majors[i].trim());
				px.setPost(posts[i].trim());
				px.setNo(Integer.parseInt(nos[i].trim()));
				px.setRemark(remarks[i].trim());
				t_hrrecruitmentitemService.update(px);
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
	@Action("deletecls")
	public String deletecls() {
		String[] str = Stringid.split(",");
		for (int i = 0; i < str.length; i++) {
			t_hrrecruitmentitem = t_hrrecruitmentitemService
					.get(Integer.parseInt(str[i].trim()));
			t_hrrecruitmentitem.setStatus(-1);
			t_hrrecruitmentitemService.update(t_hrrecruitmentitem);
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		String[] str = Stringid.split(",");
		for (int i = 0; i < str.length; i++) {
			t_hrrecruitment = t_hrrecruitmentService
					.get(Integer.parseInt(str[i]));
			t_hrrecruitment.setStatus(-1);
			t_hrrecruitmentService.update(t_hrrecruitment);
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 查看
	 * 
	 * @param id
	 * @return
	 */
	@Action("show")
	public String show() {
		if (t_hrrecruitment != null && t_hrrecruitment.getId() != null) {
			t_hrrecruitment = t_hrrecruitmentService
					.get(t_hrrecruitment.getId());
			put("t_hrrecruitment", t_hrrecruitment);
			List<T_hrrecruitmentitem> list = t_hrrecruitmentitemService
					.getList(t_hrrecruitment.getId());
			put("list", list);
			t_hropinion = t_hrrecruitment.getT_hropinion();
			put("t_hropinion", t_hropinion);
		}
		return "show";
	}

	/**
	 * 处理页面
	 * 
	 * @param id
	 * @return
	 */
	@Action("manage")
	public String manage() {
		t_hrrecruitment = t_hrrecruitmentService.get(t_hrrecruitment.getId());
		put("t_hrrecruitment", t_hrrecruitment);
		List<T_hrrecruitmentitem> list = t_hrrecruitmentitemService
				.getList(t_hrrecruitment.getId());
		put("list", list);
		if ((t_hrrecruitment.getHdept_id().getId())
				.equals(this.getMyDeptId())) {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String rctime = format.format(date);
			put("rctime", rctime);
			User user = this.getUser();
			put("user", user);
			put("t_hrrecruitment", t_hrrecruitment);
			return "manage";
		}
		return null;
	}

	/**
	 * 处理保存
	 * 
	 * @param id
	 * @return
	 */
	@Action(value = "managesave", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String managesave() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String rctime = format.format(date);
		t_hrrecruitment = t_hrrecruitmentService.get(t_hrrecruitment.getId());
		if (t_hrrecruitment.getT_hropinion() == null
				&& (t_hrrecruitment.getHdept_id().getId())
						.equals(this.getMyDeptId())) {
			t_hropinion.setUser_id(this.getUser());
			t_hropinion.setCdate(rctime);
			t_hropinionService.save(t_hropinion);
			t_hrrecruitment.setT_hropinion(t_hropinion);
			t_hrrecruitmentService.update(t_hrrecruitment);
		}
		return "list";
	}

	@Action("excel")
	public String excel() {
		// String rdate=format.format(date);
		t_hrrecruitment = t_hrrecruitment == null ? new T_hrrecruitment()
				: t_hrrecruitment;
		Page<T_hrrecruitment> pageBean = t_hrrecruitmentService.getPage(pageNo,
				getPageSize(), getDept_id(), getUser_id(), rtimec, rtimej, null,
				null);
		List<T_hrrecruitment> list = pageBean.getList();
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("培训计划列表");

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
		cell.setCellValue("需求部门");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("登记人");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("登记时间");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("人员需求岗位描述");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("备注");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("处理时间");
		cell.setCellStyle(style);

		cell = row.createCell(7);
		cell.setCellValue("处理意见");
		cell.setCellStyle(style);

		cell = row.createCell(8);
		cell.setCellValue("意见明细");
		cell.setCellStyle(style);
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((short) (i + 1));
			T_hrrecruitment tApply = list.get(i);
			for (int j = 0; j < 9; j++) {
				cell = row.createCell(j);
				cell.setCellStyle(style);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if (j == 0) {
					cell.setCellValue(i + 1);
				} else if (j == 1) {
					if (tApply.getDept_id() != null
							&& tApply.getDept_id().getName() != null) {
						cell.setCellValue(tApply.getDept_id().getName());
					}

				} else if (j == 2) {
					if (tApply.getUser_id() != null
							&& tApply.getUser_id().getName() != null)
						cell.setCellValue(tApply.getUser_id().getName());//
				} else if (j == 3) {
					if (tApply.getRdate() != null)
						cell.setCellValue(tApply.getRdate());//
				} else if (j == 4) {
					if (tApply.getDemand() != null)
						cell.setCellValue(tApply.getDemand());
				} else if (j == 5) {
					if (tApply.getRemark() != null)
						cell.setCellValue(tApply.getRemark());
				} else if (j == 6) {
					if (tApply.getT_hropinion() != null
							&& tApply.getT_hropinion().getCdate() != null)
						cell.setCellValue(tApply.getT_hropinion().getCdate());
				} else if (j == 7) {
					if (tApply.getT_hropinion() != null
							&& tApply.getT_hropinion().getOpinion() != null) {
						if (tApply.getT_hropinion().getOpinion() == 1) {
							cell.setCellValue("同意");// ----
						}
						if (tApply.getT_hropinion().getOpinion() == 2) {
							cell.setCellValue("不同意");
						}
					}
				} else if (j == 8) {
					if (tApply.getT_hropinion() != null
							&& tApply.getT_hropinion().getDetail() != null) {
						cell.setCellValue(tApply.getT_hropinion().getDetail());
					}
				}
			}
		}
		OutputStream fout = null;
		try {
			HttpServletResponse response = getHttpServletResponse();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(("招聘信息.xls").getBytes("GB2312"), "iso8859-1"));
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
	public T_hropinion getT_hropinion() {
		return t_hropinion;
	}

	public void setT_hropinion(T_hropinion t_hropinion) {
		this.t_hropinion = t_hropinion;
	}

	public T_hrrecruitment getT_hrrecruitment() {
		return t_hrrecruitment;
	}

	public void setT_hrrecruitment(T_hrrecruitment t_hrrecruitment) {
		this.t_hrrecruitment = t_hrrecruitment;
	}

	public T_hrrecruitmentitem getT_hrrecruitmentitem() {
		return t_hrrecruitmentitem;
	}

	public void setT_hrrecruitmentitem(
			T_hrrecruitmentitem t_hrrecruitmentitem) {
		this.t_hrrecruitmentitem = t_hrrecruitmentitem;
	}

	public String getRtimec() {
		return rtimec;
	}

	public void setRtimec(String rtimec) {
		this.rtimec = rtimec;
	}

	public String getRtimej() {
		return rtimej;
	}

	public void setRtimej(String rtimej) {
		this.rtimej = rtimej;
	}

	public String getStringid() {
		return Stringid;
	}

	public void setStringid(String stringid) {
		Stringid = stringid;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getT_hrrecruitmentitemid() {
		return t_hrrecruitmentitemid;
	}

	public void setT_hrrecruitmentitemid(String t_hrrecruitmentitemid) {
		this.t_hrrecruitmentitemid = t_hrrecruitmentitemid;
	}

	public String getT_hrrecruitmentitemmajor() {
		return t_hrrecruitmentitemmajor;
	}

	public void setT_hrrecruitmentitemmajor(String t_hrrecruitmentitemmajor) {
		this.t_hrrecruitmentitemmajor = t_hrrecruitmentitemmajor;
	}

	public String getT_hrrecruitmentitempost() {
		return t_hrrecruitmentitempost;
	}

	public void setT_hrrecruitmentitempost(String t_hrrecruitmentitempost) {
		this.t_hrrecruitmentitempost = t_hrrecruitmentitempost;
	}

	public String getT_hrrecruitmentitemno() {
		return t_hrrecruitmentitemno;
	}

	public void setT_hrrecruitmentitemno(String t_hrrecruitmentitemno) {
		this.t_hrrecruitmentitemno = t_hrrecruitmentitemno;
	}

	public String getT_hrrecruitmentitemremark() {
		return t_hrrecruitmentitemremark;
	}

	public void setT_hrrecruitmentitemremark(String t_hrrecruitmentitemremark) {
		this.t_hrrecruitmentitemremark = t_hrrecruitmentitemremark;
	}

}
