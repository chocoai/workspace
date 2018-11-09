package com.smart.web.action.t_fire;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.smart.model.Contract;
import com.smart.model.Project;
import com.smart.model.T_Invoice;
import com.smart.model.T_Receivables;
import com.smart.service.ContractService;
import com.smart.service.T_InvoiceService;
import com.smart.service.T_ReceivablesService;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_fireAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private T_InvoiceService invoiceService;

	@Autowired
	private T_ReceivablesService receivablesService;

	@Autowired
	private ContractService contractService;

	private T_Receivables receivables;

	private T_Invoice invoice;

	private Project project;

	private String id;// 添加收款-查询

	private String fireId;// 编辑

	private String rtimec;// 统计-查询

	private String rtimej;// 统计-查询

	private String projectname;// 项目名称-统计查询

	private String invoiceNo;// 发票编号-统计查询

	private String projectno;// 项目编号-统计查询

	private String payCompany;// 付款单位-统计查询

	/**
	 * 新建
	 * 
	 * @return
	 */
	@Action("new")
	public String news() {
		invoice = invoiceService.get(Integer.parseInt(getId()));
		put("invoice", invoice);
		// 实际应收金额
		double pay = 0;
		List<Contract> list = contractService
				.getList(invoice.getProject().getId());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPayAmount() != null) {
				pay = list.get(i).getPayAmount();
			}

		}
		put("pay", pay);
		// 累计已开票
		double aay = 0.00;
		List<?> aa = invoiceService.getgrands(invoice.getInvoiceDate(),
				invoice.getProject().getId());
		for (int i = 0; i < aa.size(); i++) {
			aay = (Double) aa.get(i);
		}
		put("aay", aay);
		double bby = pay - aay;
		put("bby", bby);
		if (getFireId() != null) {
			// 累计到账金额
			receivables = receivablesService.get(Integer.parseInt(getFireId()));
			List<?> relist = receivablesService.getCumulatives(
					receivables.getArrivalDate(), invoice.getId());
			double cby = 0.00;
			for (int i = 0; i < relist.size(); i++) {
				cby = (Double) relist.get(i);
			}
			put("cby", cby);
			// 未到账
			double dby = invoice.getInvoiceAmount() - cby;
			put("dby", dby);

			// 新增
		} else {
			// 累计到账金额
			List<?> relist = receivablesService.getCumulative(new Date(),
					invoice.getId());
			double cby = 0.00;
			for (int i = 0; i < relist.size(); i++) {
				cby = (Double) relist.get(i);
			}
			put("cby", cby);
			// 未到账
			double dby = invoice.getInvoiceAmount() - cby;
			put("dby", dby);
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
		if (receivables.getId() != null) {
			T_Receivables rs = receivablesService.get(receivables.getId());
			rs.setArrivalAmount(receivables.getArrivalAmount());
			rs.setArrivalDate(receivables.getArrivalDate());
			rs.setRemark(receivables.getRemark());
			receivablesService.update(rs);
		} else {
			receivables.setInvoice(
					invoiceService.get(receivables.getInvoice().getId()));
			receivables.setUser(this.getUser());
			receivables.setRdate(new Date());
			receivablesService.save(receivables);
		}
		// 回款处理
		invoice = invoiceService.get(receivables.getInvoice().getId());
		List<?> list = receivablesService.getByIdAccount(invoice.getId());
		Double total = (Double) list.get(0);
		if ((Double) total > 0 && (Double) total < invoice.getInvoiceAmount()) {
			invoice.setReStatus(3);// 部分回款
		}
		if ((Double) total >= invoice.getInvoiceAmount()) {
			invoice.setReStatus(2);// 已回款
		}

		// Object[] objects=new Object[2];
		// List list=receivablesService.getsumAccount();
		// for(int i=0;i<list.size();i++){
		// objects=(Object[]) list.get(i);
		// }
		// if((Double)objects[1]>0 &&
		// (Double)objects[1]<invoice.getInvoiceAmount()){
		// invoice.setReStatus(3);//部分回款
		// }
		// if((Double)objects[1]>=invoice.getInvoiceAmount()){
		// invoice.setReStatus(2);//已回款
		// }
		invoiceService.update(invoice);
		return "list";
	}

	/**
	 * 收账明细
	 * 
	 * @param id
	 * @return
	 */
	@Action("adetail")
	public void adetail() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<T_Receivables> list = receivablesService
				.getList(Integer.parseInt(getId().trim()));
		Integer fistatu = invoiceService.get(Integer.parseInt(getId().trim()))
				.getFistatu();
		for (int i = 0; i < list.size(); i++) {
			// 当前发票累计收款金额
			List<?> lse = receivablesService.getCumulative(
					list.get(i).getArrivalDate(),
					list.get(i).getInvoice().getId());
			list.get(i).setCumulatives(0.00);
			for (int j = 0; j < lse.size(); j++) {
				list.get(i).setCumulatives(
						list.get(i).getInvoice().getInvoiceAmount()
								- (Double) lse.get(j));
			}
		}
		JSONObject jsonObject = new JSONObject();
		List<Double> Cumulatives = new ArrayList<Double>();
		List<String> invoiceNos = new ArrayList<String>();
		List<String> payCompanys = new ArrayList<String>();
		List<Integer> invoid = new ArrayList<Integer>();
		List<Double> invoiceAmounts = new ArrayList<Double>();
		List<Double> arrivalAmounts = new ArrayList<Double>();
		List<String> arrivalDates = new ArrayList<String>();
		List<String> users = new ArrayList<String>();
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			invoiceNos.add(list.get(i).getInvoice().getInvoiceNo());
			payCompanys.add(list.get(i).getInvoice().getPayCompany());
			invoiceAmounts.add(list.get(i).getInvoice().getInvoiceAmount());
			arrivalAmounts.add(list.get(i).getArrivalAmount());
			arrivalDates.add(format.format(list.get(i).getArrivalDate()));
			users.add(list.get(i).getUser().getName());
			ids.add(list.get(i).getId());
			invoid.add(list.get(i).getInvoice().getId());
			// 累计到账金额
			List<?> relist = receivablesService.getCumulative(
					list.get(i).getArrivalDate(),
					list.get(i).getInvoice().getId());
			double cby = 0.00;
			for (int j = 0; j < relist.size(); j++) {
				cby = (Double) relist.get(j);
			}
			// 未到账
			double dby = list.get(i).getInvoice().getInvoiceAmount() - cby;
			Cumulatives.add(dby);
		}
		jsonObject.put("invoiceNos", invoiceNos);
		jsonObject.put("payCompanys", payCompanys);
		jsonObject.put("invoiceAmounts", invoiceAmounts);
		jsonObject.put("arrivalAmounts", arrivalAmounts);
		jsonObject.put("arrivalDates", arrivalDates);
		jsonObject.put("users", users);
		jsonObject.put("ids", ids);
		jsonObject.put("invoid", invoid);
		jsonObject.put("Cumulatives", Cumulatives);
		jsonObject.put("fistatu", fistatu);
		writeJson(jsonObject.toString());
	}

	/**
	 * 查看
	 * 
	 * @param id
	 * @return
	 */
	@Action("show")
	public String show() {
		receivables = receivablesService.get(receivables.getId());
		put("receivables", receivables);
		return "show";
	}

	/**
	 * 报表
	 * 
	 * @param id
	 * @return
	 */
	@Action("report")
	public String report() {
		Page<T_Invoice> pageBean = invoiceService.getPages(pageNo, 10,
				getProjectname(), getInvoiceNo(), getProjectno(),
				getPayCompany(), rtimec, rtimej);
		for (int i = 0; i < pageBean.getList().size(); i++) {
			pageBean.getList().get(i).setReceivableslist(receivablesService
					.getList(pageBean.getList().get(i).getId()));
			List<?> list = receivablesService
					.getAccount(pageBean.getList().get(i).getId());
			for (int j = 0; j < list.size(); j++) {
				// 合计
				pageBean.getList().get(i).setaAccount((Double) list.get(j));
			}
			// 当前应收

			for (int j = 0; j < pageBean.getList().get(i).getReceivableslist()
					.size(); j++) {
				List<?> cum = receivablesService.getCumulative(
						pageBean.getList().get(i).getReceivableslist().get(j)
								.getArrivalDate(),
						pageBean.getList().get(i).getId());
				for (int y = 0; y < cum.size(); y++) {
					// 当前累计收款
					pageBean.getList().get(i).getReceivableslist().get(j)
							.setCumulatives((Double) cum.get(y));
				}
				// 当前未收款
				pageBean.getList().get(i).getReceivableslist().get(j)
						.setReceivable(pageBean.getList().get(i)
								.getInvoiceAmount()
								- pageBean.getList().get(i).getReceivableslist()
										.get(j).getCumulatives());
			}

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
		List<T_Invoice> list = invoiceService.getPages(getProjectname(),
				getInvoiceNo(), getProjectno(), getPayCompany(), rtimec,
				rtimej);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setReceivableslist(
					receivablesService.getList(list.get(i).getId()));
			List<?> list1 = receivablesService.getAccount(list.get(i).getId());
			for (int j = 0; j < list1.size(); j++) {
				// 合计
				list.get(i).setaAccount((Double) list1.get(j));
			}
			// 当前应收

			for (int j = 0; j < list.get(i).getReceivableslist().size(); j++) {
				List<?> cum = receivablesService.getCumulative(list.get(i)
						.getReceivableslist().get(j).getArrivalDate(),
						list.get(i).getId());
				for (int y = 0; y < cum.size(); y++) {
					// 当前累计收款
					list.get(i).getReceivableslist().get(j)
							.setCumulatives((Double) cum.get(y));
				}
				// 当前未收款
				list.get(i).getReceivableslist().get(j)
						.setReceivable(list.get(i).getInvoiceAmount()
								- list.get(i).getReceivableslist().get(j)
										.getCumulatives());
			}

		}
		put("list", list);
		return "reportPrint";
	}

	/**
	 * 删除
	 */
	@Action("delete")
	public String delete() {
		receivables = receivablesService.get(receivables.getId());
		invoice = invoiceService.get(receivables.getInvoice().getId());
		receivables.setStatus(-1);
		receivablesService.update(receivables);
		// 回款处理
		List<?> list = receivablesService.getByIdAccount(invoice.getId());
		Double total = (Double) (list.get(0) != null ? list.get(0) : 0.00);
		if ((Double) total > 0 && (Double) total < invoice.getInvoiceAmount()) {
			invoice.setReStatus(3);// 部分回款
		}
		if ((Double) total >= invoice.getInvoiceAmount()) {
			invoice.setReStatus(2);// 已回款
		}
		if ((Double) total <= 0) {
			invoice.setReStatus(1);// 未回款
		}
		invoiceService.update(invoice);
		write("1");
		return null;
	}

	/**
	 * 列表and条件查询
	 */
	@Action("list")
	public String list() {

		// 项目累计开票票
		// List<?> aclist=invoiceService.getcAccount();
		// 发票累计收款
		List<?> dzlist = receivablesService.getsumAccount();
		List<Contract> clist = contractService.getAll();
		Object[] obj = new Object[2];
		invoice = invoice == null ? new T_Invoice() : invoice;
		project = project == null ? new Project() : project;

		int tpid = 0;// 项目id
		int cpid = 0;// 合同id
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
		List<T_Receivables> list = receivablesService.getAll();
		put("list", list);
		return "list";
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	@Action("excel")
	public String excel() {
		invoice = invoice == null ? new T_Invoice() : invoice;
		Page<T_Invoice> pageBean = invoiceService.getPages(pageNo, 10,
				getProjectname(), getInvoiceNo(), getProjectno(),
				getPayCompany(), rtimec, rtimej);
		List<T_Invoice> list = pageBean.getList();
		int indexs = 0;
		for (int i = 0; i < list.size(); i++) {
			if (receivablesService.getList(list.get(i).getId()) != null
					&& receivablesService.getList(list.get(i).getId())
							.size() != 0) {
				indexs += receivablesService.getList(list.get(i).getId())
						.size();
				list.get(i).setReceivableslist(
						receivablesService.getList(list.get(i).getId()));
				List<?> zlist = receivablesService
						.getAccount(list.get(i).getId());
				for (int j = 0; j < zlist.size(); j++) {
					// 合计
					list.get(i).setaAccount((Double) zlist.get(j));
				}
				// 当前应收

				for (int j = 0; j < list.get(i).getReceivableslist()
						.size(); j++) {
					List<?> cum = receivablesService
							.getCumulative(
									list.get(i).getReceivableslist().get(j)
											.getArrivalDate(),
									list.get(i).getId());
					for (int y = 0; y < cum.size(); y++) {
						// 当前累计收款
						list.get(i).getReceivableslist().get(j)
								.setCumulatives((Double) cum.get(y));
					}
					// 当前未收款
					list.get(i).getReceivableslist().get(j)
							.setReceivable(list.get(i).getInvoiceAmount()
									- list.get(i).getReceivableslist().get(j)
											.getCumulatives());
				}
			} else {
				list.get(i).setReceivableslist(null);
				indexs++;
			}
		}
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("收款管理列表");

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
		cell.setCellValue("项目类型");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("发票编号");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("付款单位");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("开票金额(元)");
		cell.setCellStyle(style);

		cell = row.createCell(7);
		cell.setCellValue("合计");
		cell.setCellStyle(style);

		cell = row.createCell(8);
		cell.setCellValue("应收金额(元)");
		cell.setCellStyle(style);

		cell = row.createCell(9);
		cell.setCellValue("到账金额(元)");
		cell.setCellStyle(style);

		cell = row.createCell(10);
		cell.setCellValue("到账日期");
		cell.setCellStyle(style);

		cell = row.createCell(11);
		cell.setCellValue("登记人");
		cell.setCellStyle(style);

		T_Invoice tApply = new T_Invoice();
		// 先创建单元格

		for (int i = 1; i <= indexs; i++) {
			row = sheet.createRow((short) (i));
			for (int j = 0; j < 12; j++) {
				row.createCell(j);
				HSSFCell tmp_cell = row.createCell(j);
				tmp_cell.setCellStyle(style);
			}
		}

		int end = 0;
		for (int i = 0; i < list.size(); i++) {
			tApply = list.get(i);
			List<?> contactList = tApply.getReceivableslist();

			if (contactList != null && contactList.size() > 0) {

				int start = end + 1;

				if (i == 0)
					end = i + 1 + list.get(i).getReceivableslist().size() - 1;
				else
					end = start + list.get(i).getReceivableslist().size() - 1;
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
						if (tApply.getProject() != null) {
							cell.setCellValue(tApply.getProject().getNo());
						}

					} else if (j == 2) {
						if (tApply.getProject() != null) {
							cell.setCellValue(tApply.getProject().getName());
						}
					} else if (j == 3) {
						if (tApply.getProject() != null && tApply.getProject()
								.getProjectType() != null) {
							cell.setCellValue(tApply.getProject()
									.getProjectType().getName());
						}
					} else if (j == 4) {
						cell.setCellValue(tApply.getInvoiceNo());
					} else if (j == 5) {
						cell.setCellValue(tApply.getPayCompany());
					} else if (j == 6) {
						cell.setCellValue(tApply.getInvoiceAmount());
					} else if (j == 7) {
						cell.setCellValue(tApply.getaAccount());
					}
				} // end for 11

				for (int k = 0; k < contactList.size(); k++) {
					for (int j = 8; j < 12; j++) {
						cell = sheet.getRow(start + k).getCell(j);
						cell.setCellStyle(style);
						if (j == 8) {
							if (tApply.getReceivableslist() != null) {
								cell.setCellValue(tApply.getReceivableslist()
										.get(k).getReceivable());
							}
						} else if (j == 9) {
							if (tApply.getReceivableslist() != null) {
								cell.setCellValue(tApply.getReceivableslist()
										.get(k).getArrivalAmount());
							}
						} else if (j == 10) {
							if (tApply.getReceivableslist() != null) {
								cell.setCellValue(tApply.getReceivableslist()
										.get(k).getArrivalDate());
							}
						} else if (j == 11) {
							if (tApply.getReceivableslist() != null
									&& tApply.getReceivableslist().get(k)
											.getUser() != null) {
								cell.setCellValue(tApply.getReceivableslist()
										.get(k).getUser().getName());
							}
						}
					}
				} // end for -k

			} else {
				// 正常写单元格数据
				int start = end + 1;

				if (i == 0 && list != null && list.get(i) != null
						&& list.get(i).getReceivableslist() != null) {
					end = i + 1 + list.get(i).getReceivableslist().size() - 1;
				} else {
					end = start;
				}
				for (int j = 0; j < 11; j++) {

					cell = sheet.getRow(start).getCell(j);
					cell.setCellStyle(style);
					if (j == 0) {
						cell.setCellValue(i + 1);
					} else if (j == 1) {
						if (tApply.getProject() != null) {
							cell.setCellValue(tApply.getProject().getNo());
						}
					} else if (j == 2) {
						if (tApply.getProject() != null) {
							cell.setCellValue(tApply.getProject().getName());
						}
					} else if (j == 3) {
						if (tApply.getProject() != null && tApply.getProject()
								.getProjectType() != null) {
							cell.setCellValue(tApply.getProject()
									.getProjectType().getName());
						}
					} else if (j == 4) {
						cell.setCellValue(tApply.getInvoiceNo());
					} else if (j == 5) {
						cell.setCellValue(tApply.getPayCompany());
					} else if (j == 6) {
						cell.setCellValue(tApply.getInvoiceAmount());
					} else if (j == 7) {
						cell.setCellValue(tApply.getaAccount());
					}
				} // end for 11

				for (int j = 11; j < 15; j++) {
					cell = sheet.getRow(start).getCell(j);
					// cell.setCellStyle(style);
					if (j == 8) {

						cell.setCellValue("");

					} else if (j == 9) {
						cell.setCellValue("");
					} else if (j == 10) {
						cell.setCellValue("");
					} else if (j == 11) {
						cell.setCellValue("");
					}
				} // end for -k

			}

		} // end-for
		OutputStream fout = null;
		try {
			HttpServletResponse response = getHttpServletResponse();
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(
							("收款信息.xls").getBytes("GB2312"), "ISO-8859-1"));
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public T_Receivables getReceivables() {
		return receivables;
	}

	public void setReceivables(T_Receivables receivables) {
		this.receivables = receivables;
	}

	public String getFireId() {
		return fireId;
	}

	public void setFireId(String fireId) {
		this.fireId = fireId;
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

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getProjectno() {
		return projectno;
	}

	public void setProjectno(String projectno) {
		this.projectno = projectno;
	}

	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

}
