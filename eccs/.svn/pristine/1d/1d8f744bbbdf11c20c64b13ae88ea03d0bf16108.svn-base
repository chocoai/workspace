package com.smart.web.action.t_invoice;

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

import com.smart.model.Contract;
import com.smart.model.Project;
import com.smart.model.T_Invoice;
import com.smart.service.ContractService;
import com.smart.service.ProjectService;
import com.smart.service.T_InvoiceService;
import com.smart.service.T_ReceivablesService;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_InvoiceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private T_InvoiceService invoiceService;

	@Autowired
	private T_ReceivablesService receivablesService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ContractService contractService;

	private T_Invoice invoice;

	private Project project;

	private Contract contract;

	private String Stringid;// id字符串组-删除

	private String cancelid;// id字符串组-作废

	private String rtimec;// 开始时间-统计查询

	private String rtimej;// 结束时间-统计查询

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		// List<Project> list = projectService.getAll();
		// put("list", list);
		if (invoice != null && invoice.getId() != null) {
			invoice = invoiceService.get(invoice.getId());
			double pay = 0.00;
			List<Contract> lists = contractService
					.getList(invoice.getProject().getId());
			for (int i = 0; i < lists.size(); i++) {
				if (lists.get(i).getPayAmount() != null) {
					pay = (Double) lists.get(i).getPayAmount();
				}
			}
			put("pay", pay);
			// 累计开票
			double aay = 0.00;
			List<?> aa = invoiceService.getgrands(invoice.getInvoiceDate(),
					invoice.getProject().getId());
			for (int i = 0; i < aa.size(); i++) {
				aay = (Double) aa.get(i);
			}
			put("aay", aay);
			// 未开票
			double bay = pay - aay;
			put("bay", bay);
			put("invoice", invoice);

			// 发票编号判断
			// List<T_Invoice> invoiceno=invoiceService.getAlls();
			// String strno=""; for(int i=0;i<invoiceno.size();i++){
			// if(invoice.getInvoiceNo()!=invoiceno.get(i).getInvoiceNo()){
			// if(strno!=""){ strno=strno+",,,"+invoiceno.get(i).getInvoiceNo();
			// }else{ strno=invoiceno.get(i).getInvoiceNo(); } } }
			// put("invos",strno);

		} else {
			invoice = new T_Invoice();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			invoice.setInvoiceNo("FP" + sdf.format(date)); // 自动生成发票编号
			put("invoice", invoice);

			// List<T_Invoice> invoiceno=invoiceService.getAlls(); String
			// strno=""; for(int i=0;i<invoiceno.size();i++){ if(strno!=""){
			// strno=strno+",,,"+invoiceno.get(i).getInvoiceNo(); }else{
			// strno=invoiceno.get(i).getInvoiceNo(); } } put("invos",strno);

		}
		return "new";
	}

	/**
	 * 查看
	 */
	@Action("show")
	public String show() {
		invoice = invoiceService.get(invoice.getId());
		put("invoice", invoice);
		// 实际应收
		double pay = 0.00;
		List<Contract> lists = contractService
				.getList(invoice.getProject().getId());
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getPayAmount() != null) {
				pay = lists.get(i).getPayAmount();
			}
		}
		put("pay", pay);
		// 累计开票
		double aay = 0.00;
		List<?> aa = invoiceService.getgrands(invoice.getInvoiceDate(),
				invoice.getProject().getId());
		for (int i = 0; i < aa.size(); i++) {
			aay = (Double) aa.get(i);
		}
		put("aay", aay);
		// 未开票
		double bay = pay - aay;
		put("bay", bay);
		return "show";
	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	@Action("print")
	public String print() {
		invoice = invoiceService.get(invoice.getId());
		put("invoice", invoice);
		// 实际应收
		double pay = 0.00;
		List<Contract> lists = contractService
				.getList(invoice.getProject().getId());
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getPayAmount() != null) {
				pay = lists.get(i).getPayAmount();
			}
		}
		put("pay", pay);
		// 累计开票
		double aay = 0.00;
		List<?> aa = invoiceService.getgrands(invoice.getInvoiceDate(),
				invoice.getProject().getId());
		for (int i = 0; i < aa.size(); i++) {
			aay = (Double) aa.get(i);
		}
		put("aay", aay);
		// 未开票
		double bay = pay - aay;
		put("bay", bay);
		return "print";
	}

	/**
	 * 删除
	 */
	@Action("delete")
	public String delete() {
		String[] id = Stringid.split(",");
		for (int i = 0; i < id.length; i++) {
			invoice = invoiceService.get(Integer.parseInt(id[i]));
			invoice.setStatus(-1);
			invoiceService.update(invoice);
		}

		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 作废
	 */
	@Action("cancel")
	public String cancel() {
		String[] id = cancelid.split(",");
		for (int i = 0; i < id.length; i++) {
			invoice = invoiceService.get(Integer.parseInt(id[i]));
			invoice.setFistatu(2);
			invoice.setCanceluser_id(this.getUser());
			invoice.setCanceltime(new Date());
			invoiceService.update(invoice);
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 选择项目
	 */
	@Action("choose")
	public String choose() {
		project = project == null ? new Project() : project;
		Page<Project> pageBean = projectService.getPage(getPageNo(), 10,
				project.getNo(), project.getName(), null);// 获取签订合同的项目
		for (int i = 0; i < pageBean.getList().size(); i++) {
			List<?> aa = invoiceService
					.getList(pageBean.getList().get(i).getId());
			for (int y = 0; y < aa.size(); y++) {
				// 累计发票
				pageBean.getList().get(i).setTotalinvoice((Double) aa.get(y));
			}
			List<Contract> list = contractService
					.getList(pageBean.getList().get(i).getId());
			for (int z = 0; z < list.size(); z++) {
				// 实际
				Double Receivables = 0.0;
				if (list.get(z).getPayAmount() != null) {
					Receivables = list.get(z).getPayAmount();
				}
				pageBean.getList().get(i).setReceivables(Receivables);
			}
			// 未开发票
			if (pageBean != null && pageBean.getList() != null
					&& pageBean.getList().get(i) != null
					&& pageBean.getList().get(i).getReceivables() != null
					&& pageBean.getList().get(i).getTotalinvoice() != null) {
				pageBean.getList().get(i)
						.setNoinvoice((Double) pageBean.getList().get(i)
								.getReceivables()
								- pageBean.getList().get(i).getTotalinvoice());
			}
		}
		put("pageBean", pageBean);
		return "selectProjet";
	}

	/**
	 * 保存
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {

		if (invoice != null && invoice.getId() != null) {
			T_Invoice px = invoiceService.get(invoice.getId());
			px.setProject(invoice.getProject());
			px.setPayCompany(invoice.getPayCompany());
			px.setInvoiceNo(invoice.getInvoiceNo());
			px.setInvoiceType(invoice.getInvoiceType());
			px.setInvoiceAmount(invoice.getInvoiceAmount());
			px.setInvoiceDate(invoice.getInvoiceDate());
			px.setInvoiceContent(invoice.getInvoiceContent());
			px.setRemark(invoice.getRemark());
			px.setInvoiceUser(invoice.getInvoiceUser());
			px.setUserDate(invoice.getUserDate());
			invoiceService.update(px);
		} else {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String rdate = format.format(date);
			if (invoice != null) {
				invoice.setUser(this.getUser());
				invoice.setRdate(rdate);
				invoice.setFistatu(1);
				invoice.setReStatus(1);
				invoiceService.save(invoice);
			}
		}
		return "list";
	}

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		// 发票累计收款
		List<?> dzlist = receivablesService.getsumAccount();
		List<Contract> clist = contractService.getAll();
		Object[] obj = new Object[2];
		invoice = invoice == null ? new T_Invoice() : invoice;
		project = project == null ? new Project() : project;
		int tpid = 0;
		int cpid = 0;
		int id = 0;
		Page<T_Invoice> pageBean = invoiceService.getPage(getPageNo(), 10,
				project.getName(), invoice.getInvoiceNo(), project.getNo(),
				invoice.getReStatus(), null, null);
		for (int i = 0; i < pageBean.getList().size(); i++) {
			tpid = pageBean.getList().get(i).getProject().getId();
			id = pageBean.getList().get(i).getId();
			// 项目应收款
			for (int j = 0; j < clist.size(); j++) {
				cpid = clist.get(j).getProject().getId();
				if (tpid == cpid && clist.get(j).getPayAmount() != null) {
					pageBean.getList().get(i)
							.setrAccount(clist.get(j).getPayAmount());
				}
			}
			// 累计已开票
			List<?> list = invoiceService.getgrand(
					pageBean.getList().get(i).getInvoiceDate(),
					pageBean.getList().get(i).getProject().getId());
			for (int ixx = 0; ixx < list.size(); ixx++) {
				Double a = (Double) list.get(ixx);
				pageBean.getList().get(i).setcAccount(a);
			}
			// 已收款总额
			for (int k = 0; k < dzlist.size(); k++) {
				obj = (Object[]) dzlist.get(k);
				if (id == Integer.parseInt(obj[0].toString())) {
					pageBean.getList().get(i)
							.setaAccount(Double.parseDouble(obj[1].toString()));
				}
			}
		}
		put("pageBean", pageBean);
		return "list";
	}

	/**
	 * 报表
	 * 
	 * @param id
	 * @return
	 */
	@Action("report")
	public String report() {
		// 发票累计收款
		List<Contract> clist = contractService.getAll();
		invoice = invoice == null ? new T_Invoice() : invoice;
		project = project == null ? new Project() : project;

		int tpid = 0;
		int cpid = 0;
		Page<T_Invoice> pageBean = invoiceService.getPage(getPageNo(), 10,
				project.getName(), invoice.getInvoiceNo(), project.getNo(),
				invoice.getReStatus(), getRtimec(), getRtimej());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			tpid = pageBean.getList().get(i).getProject().getId();
			// 项目应收款
			for (int j = 0; j < clist.size(); j++) {
				cpid = clist.get(j).getProject().getId();
				if (tpid == cpid) {
					if (clist.get(j).getPayAmount() != null) {
						pageBean.getList().get(i)
								.setrAccount(clist.get(j).getPayAmount());
					}
				}
			}
			// 累计已开票
			List<?> list = invoiceService.getgrand(
					pageBean.getList().get(i).getInvoiceDate(),
					pageBean.getList().get(i).getProject().getId());
			for (int ixx = 0; ixx < list.size(); ixx++) {
				Double a = (Double) list.get(ixx);
				pageBean.getList().get(i).setcAccount(a);
			}
			// 未开票总额
			pageBean.getList().get(i)
					.setwAccount(pageBean.getList().get(i).getrAccount()
							- pageBean.getList().get(i).getcAccount());
		}
		put("pageBean", pageBean);
		return "report";
	}

	/**
	 * 打印报表
	 * 
	 * @param id
	 * @return
	 */
	@Action("reportPrint")
	public String reportPrint() {
		// 发票累计收款
		List<Contract> clist = contractService.getAll();
		invoice = invoice == null ? new T_Invoice() : invoice;
		project = project == null ? new Project() : project;

		int tpid = 0;
		int cpid = 0;
		List<T_Invoice> list = invoiceService.getAll(project.getName(),
				invoice.getInvoiceNo(), project.getNo(), invoice.getReStatus(),
				getRtimec(), getRtimej());
		for (int i = 0; i < list.size(); i++) {
			tpid = list.get(i).getProject().getId();
			// 项目应收款
			for (int j = 0; j < clist.size(); j++) {
				cpid = clist.get(j).getProject().getId();
				if (tpid == cpid) {
					if (clist.get(j).getPayAmount() != null) {
						list.get(i).setrAccount(clist.get(j).getPayAmount());
					}
				}
			}
			// 累计已开票
			List<?> list1 = invoiceService.getgrand(
					list.get(i).getInvoiceDate(),
					list.get(i).getProject().getId());
			for (int ixx = 0; ixx < list1.size(); ixx++) {
				Double a = (Double) list1.get(ixx);
				list.get(i).setcAccount(a);
			}
			// 未开票总额
			list.get(i).setwAccount(
					list.get(i).getrAccount() - list.get(i).getcAccount());
		}
		put("list", list);
		return "reportPrint";
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	@Action("excel")
	public String excel() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// String rdate=format.format(date);
		invoice = invoice == null ? new T_Invoice() : invoice;
		project = project == null ? new Project() : project;
		Page<T_Invoice> pageBean = invoiceService.getPage(getPageNo(),
				getPageSize(), project.getName(), invoice.getInvoiceNo(),
				project.getNo(), invoice.getReStatus(), getRtimec(),
				getRtimej());
		// 项目累计开票票
		// List<?> aclist = invoiceService.getcAccount();
		// 发票累计收款
		// List<?> dzlist = receivablesService.getsumAccount();
		List<Contract> clist = contractService.getAll();
		int tpid = 0;
		int cpid = 0;

		List<T_Invoice> list = pageBean.getList();
		for (int i = 0; i < list.size(); i++) {
			tpid = list.get(i).getProject().getId();
			// 项目应收款
			for (int j = 0; j < clist.size(); j++) {
				cpid = clist.get(j).getProject().getId();
				if (tpid == cpid) {
					if (clist.get(j).getPayAmount() != null) {
						list.get(i).setrAccount(clist.get(j).getPayAmount());
					}
				}
			}
			// 累计已开票
			List<?> listss = invoiceService.getgrand(
					list.get(i).getInvoiceDate(),
					list.get(i).getProject().getId());
			for (int ixx = 0; ixx < listss.size(); ixx++) {
				Double a = (Double) listss.get(ixx);
				list.get(i).setcAccount(a);
			}
			// 未开票总额
			list.get(i).setwAccount(
					list.get(i).getrAccount() - list.get(i).getcAccount());
		}
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("发票管理列表");

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
		cell.setCellValue("项目编号");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("项目名称");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("实际应收总额(元)");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("累计已开票(元)");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("未开票总额(元)");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("发票编号");
		cell.setCellStyle(style);

		cell = row.createCell(7);
		cell.setCellValue("开票总额(元)");
		cell.setCellStyle(style);

		cell = row.createCell(8);
		cell.setCellValue("开票日期");
		cell.setCellStyle(style);

		cell = row.createCell(9);
		cell.setCellValue("收费状态");
		cell.setCellStyle(style);

		cell = row.createCell(10);
		cell.setCellValue("项目负责人");
		cell.setCellStyle(style);

		cell = row.createCell(11);
		cell.setCellValue("项目类别");
		cell.setCellStyle(style);

		cell = row.createCell(12);
		cell.setCellValue("状态");
		cell.setCellStyle(style);
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((short) (i + 1));
			T_Invoice tApply = list.get(i);
			for (int j = 0; j < 13; j++) {
				cell = row.createCell(j);
				cell.setCellStyle(style);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if (j == 0) {
					cell.setCellValue(i + 1);
				} else if (j == 1) {
					if (tApply.getProject() != null
							&& tApply.getProject().getNo() != null) {
						cell.setCellValue(tApply.getProject().getNo());
					}

				} else if (j == 2) {
					if (tApply.getProject() != null
							&& tApply.getProject().getName() != null)
						cell.setCellValue(tApply.getProject().getName());//
				} else if (j == 3) {
					if (tApply.getrAccount() != 0)
						cell.setCellValue(tApply.getrAccount());//
				} else if (j == 4) {
					if (tApply.getcAccount() != 0)
						cell.setCellValue(tApply.getcAccount());
				} else if (j == 5) {
					if (tApply.getwAccount() != 0) {
						cell.setCellValue(tApply.getwAccount());
					}
				} else if (j == 6) {
					if (tApply.getInvoiceNo() != null)
						cell.setCellValue(tApply.getInvoiceNo());
				} else if (j == 7) {
					if (tApply.getInvoiceAmount() != 0)
						cell.setCellValue(tApply.getInvoiceAmount());
				} else if (j == 8) {
					if (tApply.getInvoiceDate() != null) {
						cell.setCellValue(
								format.format(tApply.getInvoiceDate()));//
					}
				} else if (j == 9) {
					if (tApply.getReStatus() != null) {
						if (tApply.getReStatus() == 1)
							cell.setCellValue("未回款");
						if (tApply.getReStatus() == 2)
							cell.setCellValue("已回款");
						if (tApply.getReStatus() == 3)
							cell.setCellValue("部分回款");
					}
				} else if (j == 10) {
					if (tApply.getProject() != null
							&& tApply.getProject().getRecordName() != null) {
						cell.setCellValue(tApply.getProject().getRecordName());
					}
				} else if (j == 11) {
					if (tApply.getProject() != null
							&& tApply.getProject().getProjectType() != null
							&& tApply.getProject().getProjectType()
									.getName() != null) {
						cell.setCellValue(
								tApply.getProject().getProjectType().getName());
					}
				} else if (j == 12) {
					if (tApply.getFistatu() != null) {
						if (tApply.getFistatu() == 1)
							cell.setCellValue("正常");
						if (tApply.getFistatu() == 2)
							cell.setCellValue("作废");
					}
				}
			}
		}
		OutputStream fout = null;
		try {
			HttpServletResponse response = getHttpServletResponse();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(("发票信息.xls").getBytes("GB2312"), "iso8859-1"));
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
		// write("1"); //ajax请求用write返回数据
		return null;

	}

	/*----------------------------get和set--------------------------*/

	public T_Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(T_Invoice invoice) {
		this.invoice = invoice;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getStringid() {
		return Stringid;
	}

	public void setStringid(String stringid) {
		Stringid = stringid;
	}

	public String getCancelid() {
		return cancelid;
	}

	public void setCancelid(String cancelid) {
		this.cancelid = cancelid;
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

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
